package com.spark.network.handler;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spark.network.entity.LeaveForm;
import com.spark.network.entity.User;
import com.spark.network.service.ILeaveFormService;
import com.spark.network.service.IProcessService;
import com.spark.network.service.IUserService;

@Controller
public class LeaveContrloller {
	
	@Resource(name="leaveFormService")
	private ILeaveFormService leaveFormService;
	@Resource(name="userService")
	private IUserService userService;
	@Resource(name="processService")
	private IProcessService processService;
	
	@RequestMapping(value="/lea{id}",method=RequestMethod.PUT)
	public String getLeaveForm( @RequestParam(value="id") Integer id){
		leaveFormService.getById(id);
		return "";
	}
	@RequestMapping(value="/lea{id}",method=RequestMethod.DELETE)
	public String deleteLeaveForm(@RequestParam(value="id") Integer id){
		leaveFormService.delete(id);
		return "";
	}
	@RequestMapping(value="/lea",method=RequestMethod.POST)
	public String addLeaveForm(LeaveForm leaveForm){
		processService.startLeave(leaveForm);
		return "redirect:/listProcessInstanceLea";
	}
	
	@RequestMapping(value="/leave")
	public String input(Map<String, Object> map){
		map.put("leaveForm", new LeaveForm());
		return"lea/input";
	}
	@RequestMapping(value = "/leas")
	public String list(@RequestParam(value="pageNo", required=false, defaultValue="1") String pageNoStr, Map<String, Object> map){
		int pageNo = 1;
		try {
			//对 pageNo 的校验
			pageNo = Integer.parseInt(pageNoStr);
			if(pageNo < 1){
				pageNo = 1;
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
			Page<LeaveForm> page = leaveFormService.getPage(pageNo, 8);
			map.put("page", page);
		return "lea/list";
	}
	
	
}
