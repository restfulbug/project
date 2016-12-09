package com.spark.network.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spark.network.entity.LeaveForm;
import com.spark.network.entity.RepairForm;
import com.spark.network.entity.RepairFormState;
import com.spark.network.entity.Role;
import com.spark.network.entity.User;
import com.spark.network.form.BaseForm;
import com.spark.network.form.CommentVO;
import com.spark.network.form.TaskVO;
import com.spark.network.repository.LeaveFormRepository;
import com.spark.network.repository.RepairFormRepository;
import com.spark.network.service.ILeaveFormService;
import com.spark.network.service.IProcessService;
import com.spark.network.service.IRepairFormStateService;
import com.spark.network.service.IRoleService;
import com.spark.network.service.IUserService;
import com.spark.network.util.DateUtil;

@Service(value = "processService")
public class ProcessServiceImpl implements IProcessService {

	@Resource(name = "runtimeService")
	private RuntimeService runtimeService;
	@Resource(name = "historyService")
	private HistoryService historyService;
	@Resource(name = "repositoryService")
	private RepositoryService repositoryService;
	@Resource(name = "taskService")
	private TaskService taskService;
	@Resource(name = "identityService")
	private IdentityService identityService;
	@Resource(name = "leaveFormService")
	private ILeaveFormService leaveFormService;
	@Resource(name = "processEngine")
	private ProcessEngine processEngine;
	@Resource(name = "userService")
	private IUserService userService;
	@Resource(name = "roleService")
	private IRoleService roleService;
	@Resource(name = "leaveFormRepository")
	private LeaveFormRepository leaveFormRepository;
	@Resource(name = "repairFormRepository")
	private RepairFormRepository repairFormRepository;
	@Resource(name = "processEngineConfiguration")
	private ProcessEngineConfiguration processEngineConfiguration;
	@Resource(name = "repairFormStateService")
	private IRepairFormStateService repairFormStateService;
  
	@Transactional
	public ProcessInstance startLeave(LeaveForm leaveForm) {
		BaseForm baseForm = new BaseForm();
		User user = userService.getById(leaveForm.getUser().getId());
		Map<String, Object> vars = new HashMap<String, Object>();
		List<User> admins = new ArrayList<User>();
		List<String> adminIds = new ArrayList<String>();
		admins = userService.getByRoleName("admin");
		for (User user2 : admins) {
			String userId = user2.getId()+"";
			adminIds.add(userId);
		}
		vars.put("userId", user.getId()+"");
		vars.put("adminIds",adminIds);
		vars.put("arg", baseForm);
		ProcessInstance pi = this.runtimeService.startProcessInstanceByKey("Leave", vars);
		Task firstTask = this.taskService.createTaskQuery()
				.processInstanceId(pi.getId()).singleResult();
		baseForm.setTitle(user.getUserName() + "的请假申请");
		//baseForm.setBusinessType("请假申请");
		baseForm.setProcessStatus("待管理员审批");
		baseForm.setProId(pi.getId());
		taskService.setAssignee(firstTask.getId(),leaveForm.getUser().getId()+"");
		taskService.complete(firstTask.getId(), vars);
		leaveForm.setApplyTime(new Date());
		leaveForm.setProcessInstanceId(pi.getId().toString());
		leaveFormRepository.save(leaveForm);
		return pi;
	}
	@Transactional
	public ProcessInstance startRep(RepairForm repairForm) {
		BaseForm baseForm = new BaseForm();
		User user = userService.getById(repairForm.getUser().getId());
		Map<String, Object> vars = new HashMap<String, Object>();
		List<User> admins = new ArrayList<User>();
		List<User> users = new ArrayList<User>();
		List<User> staffs = new ArrayList<User>();
		List<String> adminIds = new ArrayList<String>();
		List<String> userIds = new ArrayList<String>();
		List<String> staffIds = new ArrayList<String>();
		List<String> adminAndStaffIds = new ArrayList<String>();
		admins = userService.getByRoleName("admin");
		staffs = userService.getByRoleName("staff");
		users = userService.getByRoleName("user");
		for (User user2 : admins) {
			String userId = user2.getId()+"";
			adminIds.add(userId);
			adminAndStaffIds.add(userId);
		}
		for (User user2 : staffs) {
			String userId = user2.getId()+"";
			staffIds.add(userId);
			adminAndStaffIds.add(userId);
		}
		
		for ( User user2 : users) {
			String userId = user2.getId()+"";
			userIds.add(userId);
		}
		vars.put("userId", user.getId()+"");
		vars.put("adminIds",adminIds);
		vars.put("userIds",userIds);
		vars.put("staffIds",staffIds);
		vars.put("adminAndStaffIds",adminAndStaffIds);
		vars.put("arg", baseForm);
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("RepairProcess", vars);
		Task firstTask = this.taskService.createTaskQuery()
				.processInstanceId(pi.getId()).singleResult();
		baseForm.setTitle(user.getUserName() + "的报修申请");
		//baseForm.setBusinessType("报修申请");
		baseForm.setProcessStatus("待管理员审批");
		baseForm.setProId(pi.getId());
		taskService.setAssignee(firstTask.getId(),repairForm.getUser().getId()+"");
		taskService.complete(firstTask.getId(), vars);
		repairForm.setCreatDate(new Date());
		repairForm.setProcessInstanceId(pi.getId().toString());
		repairForm.setRepairState(repairFormStateService.getByStateName("待审核"));
		repairFormRepository.save(repairForm);
		return pi;
	}

	// 查询请假申请
	@Transactional(readOnly=true)
	public List<BaseForm> listLeaveFrom(Integer userId) {
     
		List<LeaveForm> leas = leaveFormService.ListLeaveForm(userId);
		List<BaseForm> result = new ArrayList<BaseForm>();
		for (LeaveForm lea : leas) {
			List<HistoricVariableInstance> variableInstances = historyService.createHistoricVariableInstanceQuery()
					.processInstanceId(lea.getProcessInstanceId()).list();
			if (variableInstances != null&&variableInstances.size()> 0) {
				for (HistoricVariableInstance historicVariableInstance : variableInstances) {
					if (historicVariableInstance.getVariableName().equals("arg")) {
						BaseForm baseForm = (BaseForm) historicVariableInstance.getValue();
						baseForm.setProId(lea.getProcessInstanceId());
						result.add(baseForm);
					}
				}
			}
		}
		return result;
	}
	// 查询报修申请
	@Transactional(readOnly=true)
	public List<BaseForm> listRepairForm(Integer userId) {
		User user = userService.getById(userId);
		List<RepairForm> reps = repairFormRepository.getrepairFormByUser(user);
		List<BaseForm> result = new ArrayList<BaseForm>();
		BaseForm baseForm = null;
		for (RepairForm rep : reps) {
			String processInstanceId = rep.getProcessInstanceId() ;
			
			if (processInstanceId != null) {

			List<HistoricVariableInstance> hvi = historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).list();
			if (hvi!= null&hvi.size()>0) {
				for (HistoricVariableInstance historicVariableInstance : hvi) {
					if (historicVariableInstance.getVariableName().equals("arg")) {
						 baseForm = (BaseForm) historicVariableInstance.getValue();
					}
				}
				
			}
				baseForm.setProId(processInstanceId);
				result.add(baseForm);
			}
		}
		return result;
	}

	// 实时查看流程图
	@Transactional(readOnly=true)
	public InputStream getDiagram(String processInstanceId) {

		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
			BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
			ProcessDiagramGenerator processDiagramGenerator = new DefaultProcessDiagramGenerator();
			InputStream imageStream = processDiagramGenerator.generateDiagram(bpmnModel, "png", runtimeService.getActiveActivityIds(processInstanceId), new ArrayList(),        	
			processEngineConfiguration.getActivityFontName(), processEngineConfiguration.getLabelFontName(), null, 1.0);         
			 return imageStream;
		}
			
	@Transactional(readOnly=true)
	public List<TaskVO> listLeaTasks(String userId) {
		List<Task> deList = new ArrayList<Task>();
		List<Task> 	leaTasks =	taskService.createTaskQuery().taskCandidateUser(userId).list();	
		for (Task task : leaTasks) {
			if (task.getName().equals("一般管理员审核")||task.getName().equals("员工领取或一般管理员指派")) {
				deList.add(task);
			}
		}
	     leaTasks.removeAll(deList);
		return createTaskVoList(leaTasks);
	}
	@Transactional(readOnly=true)
	public List<TaskVO> listRepTasks(String userId) {
		List<Task> repTasks = taskService.createTaskQuery().taskCandidateUser(userId).list();	
		List<Task> deList = new ArrayList<Task>();
		User user = userService.getById(Integer.parseInt(userId));
		Role role = roleService.getRoleByRoleName("admin");
		if (user.getRoles().contains(role)) {
			for (Task task : repTasks) {
				if (task.getName().equals("一般管理员审批")||task.getName().equals("员工领取或一般管理员指派")) {
					deList.add(task);
				}
			}
		}
		
		else {
			for (Task task : repTasks) {
				if (task.getName().equals("一般管理员审批")) {
					deList.add(task);
				}
			}
		}
		repTasks.removeAll(deList);
		return createTaskVoList(repTasks);
	}
	
	private List<TaskVO> createTaskVoList(List<Task> tasks){
		List<TaskVO> result = new ArrayList<TaskVO>();
		for (Task task : tasks) {
			// 查询流程实例
			ProcessInstance pi = runtimeService
					.createProcessInstanceQuery()
					.processInstanceId(task.getProcessInstanceId())
					.singleResult();
			if (pi != null) {
				BaseForm arg = (BaseForm) runtimeService.getVariable(
						pi.getId(), "arg");
				TaskVO vo = new TaskVO();
				vo.setProcessInstanceId(task.getProcessInstanceId());
				vo.setRequestDate(arg.getRequestDate());
				vo.setTitle(arg.getProcessStatus());
				vo.setTaskName(task.getName());
				vo.setTaskId(task.getId());
				result.add(vo);
	}
		}
		return result;
		}	
	// 查询用户所受理的全部请假任务
	    @Transactional(readOnly=true)
		public List<TaskVO> listLeaAssigneeTasks(String userId) {
			List<Task> leaTasks = taskService.createTaskQuery().taskAssignee(userId).list();
			List<Task> deList = new ArrayList<Task>();
			for (Task task : leaTasks) {
				if (task.getName().equals("一般管理员审核")||task.getName().equals("员工领取或一般管理员指派")) {
					deList.add(task);
				}
			}
			leaTasks.removeAll(deList);
			return createTaskVoList(leaTasks);
	}
	    // 查询用户所受理的全部报修任务
	    @Transactional(readOnly=true)
	    public List<TaskVO> listRepAssigneeTasks(String userId) {
	    	List<Task> 	repTasks = taskService.createTaskQuery().taskAssignee(userId).list();
	    	List<Task> deList = new ArrayList<Task>();
	    	for (Task task : repTasks) {
				if (task.getName().equals("一般管理员审批")) {
					deList.add(task);
				}
			}
	    	repTasks.removeAll(deList);
	    	return createTaskVoList(repTasks);
	    }
	    // 领取任务
	    public void claim(String taskId,String userId) {
	    	ProcessInstance pi = getProcessInstance(taskId);
	    	String proId = pi.getId();
	    	Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
	    	if (task.getName().equals("员工领取或一般管理员指派")) {
	    		RepairForm repairForm = repairFormRepository.getByprocessInstanceId(proId);
				RepairFormState repairState = repairFormStateService.getByStateName("维修中");
				repairForm.setRepairState(repairState);
				repairFormRepository.save(repairForm);
				
			}
	    	taskService.claim(taskId, userId);
	    	
	    }
		
		 public ProcessInstance getProcessInstance(String taskId) {
			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			// 根据任务查询流程实例
			ProcessInstance pi =runtimeService.createProcessInstanceQuery()
					.processInstanceId(task.getProcessInstanceId()).singleResult();
			return pi;
		}
		 
		// 审批批准任务
			public void complete(String taskId,String content,String userId) {
				ProcessInstance pi = getProcessInstance(taskId);
			Task task =	taskService.createTaskQuery().taskId(taskId).singleResult();
		String proId = pi.getId();
			Map<String, Object> vars  = new HashMap<String, Object>();
			if (task.getName().equals("一般管理员审核")) {
				RepairForm repairForm = repairFormRepository.getByprocessInstanceId(proId);
				RepairFormState repairState = repairFormStateService.getByStateName("审核通过");
				repairForm.setRepairState(repairState);
				repairFormRepository.save(repairForm);
				vars.put("outcome", "批准");
				BaseForm baseForm =	(BaseForm) runtimeService.getVariable(proId, "arg");
				baseForm.setProcessStatus("一般管理员审批通过");
				vars.put("arg", baseForm);
				
			}
			if (task.getName().equals("一般管理员审批")) {
				vars.put("outcome", "批准");
				BaseForm baseForm =	(BaseForm) runtimeService.getVariable(proId, "arg");
				baseForm.setProcessStatus("一般管理员审批通过");
				vars.put("arg", baseForm);
				
			}
			if (task.getName().equals("员工领取或一般管理员指派")) {
				RepairForm repairForm = repairFormRepository.getByprocessInstanceId(proId);
				RepairFormState repairState = repairFormStateService.getByStateName("维修中");
				repairForm.setRepairState(repairState);
				repairFormRepository.save(repairForm);
				vars.put("taskAssigner", userId);//设置员工维修任务受理人
			}
			
			if (task.getName().equals("员工维修")) {
				RepairForm repairForm = repairFormRepository.getByprocessInstanceId(proId);
				RepairFormState repairState = repairFormStateService.getByStateName("维修结束");
				repairForm.setRepairState(repairState);
				repairFormRepository.save(repairForm);
			}
			Authentication.setAuthenticatedUserId(userId);//添加评论人id
			taskService.addComment(taskId, pi.getId(), content);
			taskService.complete(taskId, vars);
			}
			
			// 审批驳回
			public void cancelAdjust(String taskId,String content,String userId) {
				ProcessInstance pi = getProcessInstance(taskId);
				Map<String, Object> vars = new HashMap<String, Object>();
				vars.put("outcome", "驳回");
				Task task =	 taskService.createTaskQuery().taskId(taskId).singleResult();
				String proId = getProcessInstance(taskId).getId();
				if (task.getName().equals("一般管理员审核")) {
					RepairForm repairForm = repairFormRepository.getByprocessInstanceId(proId);
					RepairFormState repairState = repairFormStateService.getByStateName("驳回");
					repairForm.setRepairState(repairState);
					repairFormRepository.save(repairForm);
				}
				BaseForm baseForm =	(BaseForm) runtimeService.getVariable(proId, "arg");
				baseForm.setProcessStatus("一般管理员驳回");
				vars.put("arg", baseForm);
			    Authentication.setAuthenticatedUserId(userId);
				taskService.addComment(taskId, pi.getId(), content);
				taskService.complete(taskId, vars);

			}
			@Override
			public List<TaskVO> listDisTasks(String userId) {
				List<Task> repTasks = taskService.createTaskQuery().taskCandidateUser(userId).list();	
				List<Task> addList = new ArrayList<Task>();
				for (Task task : repTasks) {
					if (task.getName().equals("员工领取或一般管理员指派")) {
						addList.add(task);
					}
				}
				return createTaskVoList(addList);
			}
			/**获取批注信息，传递的是当前任务ID，获取历史任务ID对应的批注*/
			@Override
			public List<Comment> findCommentByTaskId(String taskId) {
				List<Comment> list = new ArrayList<Comment>();
				Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
				String processInstanceId = task.getProcessInstanceId();
				list = taskService.getProcessInstanceComments(processInstanceId);
				return list;
			}
			
			public List<CommentVO> findCommentByPId(String pi) {
					List<CommentVO> result = new ArrayList<CommentVO>();
					List<Comment> comments = taskService.getProcessInstanceComments(pi);
					for (Comment c : comments) {
						User u = userService.getById(Integer.parseInt(c.getUserId()));
						CommentVO vo = new CommentVO();
						vo.setContent(c.getFullMessage());
						vo.setTime(DateUtil.getDateString(c.getTime()));
						vo.setUserName(u.getUserName());
						result.add(vo);
					}
					return result;
				}
			@Override
			public InputStream getpng(String depid) {
				List<String> list = repositoryService.getDeploymentResourceNames(depid);
				String resourceName = "";
				if (list!=null && list.size()>0) {
					for (String name : list) {
						if (name.indexOf(".png")>=0) {
							resourceName = name;
						}
					}
				}
			InputStream	 in = repositoryService.getResourceAsStream(depid, resourceName);
				return in;
			}
}

