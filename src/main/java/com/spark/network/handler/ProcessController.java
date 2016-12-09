package com.spark.network.handler;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.Flags.Flag;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spark.network.entity.LeaveForm;
import com.spark.network.entity.RepairForm;
import com.spark.network.entity.Role;
import com.spark.network.entity.User;
import com.spark.network.form.BaseForm;
import com.spark.network.form.CommentVO;
import com.spark.network.form.TaskVO;
import com.spark.network.service.ILeaveFormService;
import com.spark.network.service.IProcessService;
import com.spark.network.service.IRepairFormService;
import com.spark.network.service.IRepairFormStateService;
import com.spark.network.service.IRoleService;
import com.spark.network.service.IUserService;
import com.spark.network.util.inputToByte;

@Controller
public class ProcessController {

	@Resource(name = "userService")
	private IUserService userService;
	@Resource(name = "roleService")
	private IRoleService roleService;
	@Resource(name = "processService")
	private IProcessService processService;
	@Resource(name = "leaveFormService")
	private ILeaveFormService leaveFormService;
	@Resource(name = "repairFormService")
	private IRepairFormService repairFormService;
	@Resource(name = "taskService")
	private TaskService taskService;
	@Resource(name = "repairFormStateService")
	private IRepairFormStateService repairFormStateService;
	@Resource(name = "runtimeService")
	private RuntimeService runtimeService;

	// 读取登录用户的全部请假申请
	@RequestMapping(value = "/listProcessInstanceLea")
	public String listProcessInstance(HttpServletRequest request,Map<String, Object> map) {
		User user = (User) request.getSession().getAttribute("user");
		List<BaseForm> baseForms = processService.listLeaveFrom(user.getId());
		map.put("baseForms", baseForms);
		return "lea/apply";
	}
	
	@RequestMapping(value = "/viewHisComment/{proId}")
	public String viewHisComment(@PathVariable(value="proId") String proId,Map<String, Object> map) {
		List<CommentVO> comments = processService.findCommentByPId(proId);
		map.put("comments", comments);
		return "hiscomment";
	}
	// 读取登录用户的全部报修申请
	@RequestMapping(value = "/listProcessInstanceRep")
	public String listProcessInstanceRep(HttpServletRequest request,Map<String, Object> map) {
		User user = (User) request.getSession().getAttribute("user");
		List<BaseForm> baseForms = processService.listRepairForm(user.getId());
		map.put("baseForms", baseForms);
		return "rep/repapply";
	}
	// 列取请假待办任务
	@RequestMapping(value = "/process-listLeaTask/{TaskType}")
	public String listLeaTask(HttpServletRequest request, Map<String, Object> map,@PathVariable(value="TaskType") String taskType) {
		User user = (User) request.getSession().getAttribute("user");
		List<TaskVO> tasks = null;
			 if (taskType.equals("candidate")) {
				  tasks  = processService.listLeaTasks(user.getId()+"");
				} else if (taskType.equals("assignee")) {
				 tasks= processService.listLeaAssigneeTasks(user.getId()+"");
				}
			 map.put("tasks", tasks);
			 map.put("taskType", taskType);
		    return "lea/task";
		}
	
	// 列取报修待办任务
	@RequestMapping(value = "/process-listRepTask/{TaskType}")
	public String listRepTask(HttpServletRequest request, Map<String, Object> map,@PathVariable(value="TaskType") String taskType) {
		User user = (User) request.getSession().getAttribute("user");
		List<TaskVO> tasks = null;
		if (taskType.equals("candidate")) {
			tasks  = processService.listRepTasks(user.getId()+"");
		} else if (taskType.equals("assignee")) {
			tasks= processService.listRepAssigneeTasks(user.getId()+"");
		}
		map.put("tasks", tasks);
		map.put("taskType", taskType);
		return "rep/task";
	}
	// 列取管理员派发任务
	@RequestMapping(value = "/process-listDisTask")
	public String listDisTask(HttpServletRequest request, Map<String, Object> map) {
		  User user = (User) request.getSession().getAttribute("user");
		       List<TaskVO> tasks = null;
			   tasks  = processService.listDisTasks(user.getId()+"");
				List<User> users = userService.getByRoleName("staff");
				List<RepairForm> forms = new ArrayList<RepairForm>();
				for (TaskVO task : tasks) {
					ProcessInstance pi = processService.getProcessInstance(task.getTaskId());
					RepairForm repairForm = repairFormService.getByProId(pi.getId());
					forms.add(repairForm);
				}
				map.put("users", users);
				map.put("reps", forms);
				map.put("tasks", tasks);
				return "rep/adminAssignTask";
	}
	
	@RequestMapping(value="process-performLea/{taskId}")
	public String performLea(@PathVariable(value="taskId") String taskId,Map<String, Object> map) {
		ProcessInstance pi = processService.getProcessInstance(taskId);
			LeaveForm leaveForm = leaveFormService.getByProId(pi.getProcessInstanceId());
			map.put("leaveForm", leaveForm);
			map.put("taskId", taskId);
			return "auditTaskLea";
	}
	@RequestMapping(value="process-performRep/{taskId}")
	public String performRep(@PathVariable(value="taskId") String taskId,Map<String, Object> map) {
		ProcessInstance pi = processService.getProcessInstance(taskId);
        RepairForm repairForm = repairFormService.getByProId(pi.getProcessInstanceId());
        List<CommentVO> comments = processService.findCommentByPId(pi.getId());
       Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
       if (task.getName().equals("用户填写评价")) {
    	    map.put("repairForm", repairForm);
			map.put("taskId", taskId);
			map.put("comments", comments);
			return "assert";
	}else {
		   map.put("taskflag", 0);
	       if (task.getName().equals("一般管理员审核")) {
			map.put("taskflag", 1);
		     }
	        map.put("repairForm", repairForm);
			map.put("taskId", taskId);
			map.put("comments", comments);
	}
		return "auditTaskRep";
	}
	// 管理员派发任务
	@RequestMapping(value = "/process-claimDis/{userId}/{pi}")
	public String claimDis(HttpServletRequest request,@PathVariable(value="userId") String userId,@PathVariable(value="pi") String pi) {
		User user = userService.getById(Integer.parseInt(userId));
		Task task = taskService.createTaskQuery().processInstanceId(pi).singleResult();
		RepairForm repairForm = repairFormService.getByProId(pi);
		repairForm.setStaff(user);
		repairFormService.save(repairForm);
		Map<String, Object> vars  = new HashMap<String, Object>();
		vars.put("taskAssigner", userId);
		BaseForm baseForm =	(BaseForm) runtimeService.getVariable(pi, "arg");
		baseForm.setProcessStatus("管理员派发"+repairForm.getUser().getUserName()+"的报修任务");
		runtimeService.setVariable(pi, "arg", baseForm);
		taskService.complete(task.getId(), vars);
		
		return "redirect:/process-listDisTask";
		
	}
	     // 领取请假任务
		@RequestMapping(value = "/process-claimLea/{taskId}")
		public String claimLea(HttpServletRequest request,@PathVariable(value="taskId") Integer taskId) {
			User user = (User) request.getSession().getAttribute("user");
			this.processService.claim(taskId.toString(), user.getId()+"");
		return "redirect:/process-listLeaTask/candidate";
		}
		// 领取报修任务
			@RequestMapping(value = "/process-claimRep/{taskId}")
			public String claimRep(HttpServletRequest request,@PathVariable(value="taskId") String taskId) {
				User user = (User) request.getSession().getAttribute("user");
				List<Role> roles = (List<Role>) user.getRoles();
				for (Role role : roles) {
					if (role.getRoleName().equals("staff")) {
						ProcessInstance pi = processService.getProcessInstance(taskId+"");
						RepairForm repairForm = repairFormService.getByProId(pi.getId());
						repairForm.setStaff(user);
						repairFormService.save(repairForm);
					}
				}
					processService.claim(taskId.toString(), user.getId()+"");
				return "redirect:/process-listRepTask/candidate";
				
		}
		//批准请假任务
		@RequestMapping(value = "/process-completeLea/{taskId}")
		public String completeLea(HttpServletRequest request,@PathVariable(value="taskId") Integer taskId,@RequestParam("content")String content) {
			User user = (User)request.getSession().getAttribute("user");
			processService.complete(taskId.toString(), content, user.getId()+"");
			return "redirect:/process-listLeaTask/assignee";
		}
		//批准报修任务
		@RequestMapping(value = "/process-completeRep/{taskId}")
		public String completeRep(HttpServletRequest request,@PathVariable(value="taskId") Integer taskId,@RequestParam("content")String content) {
			User user = (User)request.getSession().getAttribute("user");
			processService.complete(taskId.toString(),content, user.getId()+"");
			return "redirect:/process-listRepTask/assignee";
		}
		//评价
		@RequestMapping(value = "/process-assert/{taskId}")
		public String assertRep(HttpServletRequest request,@PathVariable(value="taskId") Integer taskId,@RequestParam("content")String content,@RequestParam("score")String score) {
			User user = (User)request.getSession().getAttribute("user");
			processService.complete(taskId.toString(),content, user.getId()+"");
			return "redirect:/process-listRepTask/assignee";
		}
		//驳回请假任务
		@RequestMapping(value = "/process-cancelAdjustLea/{taskId}")
		public String cancelAdjustLea(HttpServletRequest request,@PathVariable(value="taskId") Integer taskId,@RequestParam("content")String content) {
			User user = (User)request.getSession().getAttribute("user");
			processService.cancelAdjust(taskId.toString(),content,user.getId()+"");
			return "redirect:/process-listLeaTask/assignee";
		}
		//驳回报修任务
		@RequestMapping(value = "/process-cancelAdjustRep/{taskId}")
		public String cancelAdjustRep(HttpServletRequest request,@PathVariable(value="taskId") Integer taskId,@RequestParam("content")String content) {
			User user = (User)request.getSession().getAttribute("user");
			processService.cancelAdjust(taskId.toString(),content,user.getId()+"");
			return "redirect:/process-listRepTask/assignee";
		}
		//查看实时流程图
	@RequestMapping(value = "/getId/{id}")
	public String getId(@PathVariable(value="id") Integer id, Map<String, Object> map) {
		map.put("id", id);
		return "lea/diagram";
	}
		//查看流程图片
		@RequestMapping(value = "/getpngId/{id}")
		public String getpng(@PathVariable(value="id") String id, Map<String, Object> map) {
			map.put("id", id);
			return "png";
		
	}
	// 显示实时流程图
	@RequestMapping(value="/showdiagram/{id}")
		public String showDiagram(HttpServletRequest request,HttpServletResponse response,@PathVariable("id") Integer processInstanceId) {
			OutputStream out = null;
			try {
				InputStream is = processService.getDiagram(processInstanceId.toString());
				response.setContentType("multipart/form-data;charset=utf8");
	            out = response.getOutputStream();
	            out.write(inputToByte.getImgByte(is));
	            out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					out.close();
				} catch (Exception e) {
				}
			}
			return null;
		}
	// 显示流程图片
	@RequestMapping(value="/showpng/{depid}")
	public String showpng(HttpServletRequest request,HttpServletResponse response,@PathVariable("depid") String depid) {
		OutputStream out = null;
		try {
			InputStream is = processService.getpng(depid);
			response.setContentType("multipart/form-data;charset=utf8");
			out = response.getOutputStream();
			out.write(inputToByte.getImgByte(is));
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (Exception e) {
			}
		}
		return null;
	}
	
}
