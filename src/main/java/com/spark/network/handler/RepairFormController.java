package com.spark.network.handler;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spark.network.entity.FaultType;
import com.spark.network.entity.RepairForm;
import com.spark.network.service.IBuildingService;
import com.spark.network.service.IDictionaryService;
import com.spark.network.service.IFaultLevelService;
import com.spark.network.service.IFaultTypeService;
import com.spark.network.service.IProcessService;
import com.spark.network.service.IRepairFormService;
import com.spark.network.service.IRepairFormStateService;
import com.spark.network.service.IUserService;

@Controller
public class RepairFormController {

	@Resource(name = "repairFormService")
	private IRepairFormService repairFormService;
	@Resource(name = "userService")
	private IUserService userService;
	@Resource(name = "dictionaryService")
	private IDictionaryService dictionaryService;
	@Resource(name = "faultLevelService")
	private IFaultLevelService faultLevelService;
	@Resource(name = "faultTypeService")
	private IFaultTypeService faultTypeService;
	@Resource(name = "repairFormStateService")
	private IRepairFormStateService repairFormStateService;

	@Resource(name = "repositoryService")
	private RepositoryService repositoryService;
	@Resource(name = "runtimeService")
	private RuntimeService runtimeService;
	@Resource(name = "taskService")
	private TaskService taskService;
	@Resource(name="buildingService")
	private IBuildingService buildingService;
	@Resource(name="processService")
	private IProcessService processService;

	@ModelAttribute
	public void getRepairForm(
			@RequestParam(value = "id", required = false) Integer id,
			Map<String, Object> map) {
		if (id != null) {
			RepairForm repairForm = repairFormService.getById(id);
			repairForm.setRepairState(null);
			repairForm.setFaultLevel(null);
			repairForm.setFaultType(null);
			repairForm.setStaff(null);
			repairForm.setDictionary(null);
			repairForm.setBuilding(null);
			map.put("repairForm", repairForm);
		}
	}

	@RequestMapping(value = "/rep{id}", method = RequestMethod.PUT)
	public String update(RepairForm repairForm) {
		repairFormService.save(repairForm);
		return "redirect:/reps";
	}

	@RequestMapping(value = "/rep/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id) {
		repairFormService.delete(id);
		return "redirect:/reps";
	}

	// 表单回显
	@RequestMapping(value = "/rep/{id}", method = RequestMethod.GET)
	public String input(@PathVariable("id") Integer id, Map<String, Object> map) {
		RepairForm repairForm = repairFormService.getById(id);
		map.put("repairForm", repairForm);
		map.put("users", userService.getAll());
		map.put("positions", dictionaryService.getByKeyWord("区域类型"));
		map.put("faultTypes", faultTypeService.getAll());
		map.put("faultLevels", faultLevelService.getALL());
		map.put("repairFormStates", repairFormStateService.getAll());
		map.put("buildings", buildingService.getALL());
		return "rep/input";
	}

	// 进入添加页面
	@RequestMapping(value = "/rep", method = RequestMethod.GET)
	public String add(Map<String, Object> map,HttpServletRequest request) {
		map.put("positions", dictionaryService.getByKeyWord("区域类型"));
		map.put("repairForm", new RepairForm());
		map.put("user", request.getSession().getAttribute("user"));
		map.put("faultTypes", faultTypeService.getAll());
		map.put("faultLevels", faultLevelService.getALL());
		map.put("buildings", buildingService.getALL());
		return "/rep/input";
	}
	//ajax传给前端解决小方案
	@ResponseBody
	@RequestMapping(value = "/solution", produces = "text/html;charset=UTF-8")
	public String getSolution(Integer faultTypeId) {
		FaultType faultType = faultTypeService.getById(faultTypeId);
		String solution = faultType.getSolution();
		return solution;
	}
	
	@RequestMapping(value = "/rep", method = RequestMethod.POST)
	public String save(RepairForm repairForm) {
		processService.startRep(repairForm);
		return "redirect:/listProcessInstanceRep";
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public String getAll(Map<String, Object> map) {
		map.put("repairments", repairFormService.findAllRepairform());
		return "rep/input";
	}

	@RequestMapping("/reps")
	public String list(
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNoStr,
			Map<String, Object> map) {
		int pageNo = 1;
		try {
			// 对 pageNo 的校验
			pageNo = Integer.parseInt(pageNoStr);
			if (pageNo < 1) {
				pageNo = 1;
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		Page<RepairForm> page = repairFormService.getPage(pageNo, 8);
		map.put("page", page);
		return "rep/list";
	}
	
	
	
	
	
	@RequestMapping("rep/{userName}")
	public String getByUserName(@PathVariable("userName")String userName
			,Map<String, Object>map,@RequestParam(value="pageNo",defaultValue="1",required=false)Integer pageNo){
		Integer pageSize=8;
		Page<RepairForm> page=null;
		if (pageNo>0) {
			PageRequest pageable=new PageRequest(pageNo-1, pageSize);
			page=repairFormService.getPageByUserName(userName, pageable);
		}
		map.put("page", page);
		return "rep/list";
	}
	
	@RequestMapping("createDate/{id}")
	public String createDateBefore(@PathVariable(value="id")Integer id,Map<String, Object>map,@RequestParam(value="pageNo",defaultValue="1",required=false)Integer pageNo){
		Integer pageSize=8;
		Page<RepairForm> page=null;
		if (pageNo>0) {
			Date date=new Date();
			date=new Date((long) ((long)date.getTime()-id*1000.0*60*60*24));
			PageRequest pageable=new PageRequest(pageNo-1, pageSize);
			page=repairFormService.getByCreatDateBefore(date, pageable);
		}
		map.put("page", page);
		
		
		return "rep/list";
	}

	
	
		
}
