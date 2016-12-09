package com.spark.network.handler;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spark.network.entity.Menu;
import com.spark.network.entity.Role;
import com.spark.network.entity.User;
import com.spark.network.service.IRoleService;
import com.spark.network.service.IUserService;
import com.spark.network.util.ValidateCode;

@Controller
public class LoginController {

	@Resource(name="userService")
	private IUserService userService;
	
	private String loginMsg;
	// 用户登录
	    @RequestMapping(value="/login")
		public String login(User currUser,Map<String, Object>map,HttpServletRequest request,HttpSession session) {
		String code = (String) session.getAttribute("validateCode");
		String submitCode = WebUtils.getCleanParam(request,"validateCode");
		if (StringUtils.isEmpty(submitCode) || !StringUtils.equals(code,submitCode.toLowerCase())) {
			this.loginMsg="验证码错误";
			request.getSession().setAttribute("loginMsg", loginMsg);
			return "redirect:/";
		}
		Subject user = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(currUser.getAccount(),currUser.getPassword());
		token.setRememberMe(true);
		try {
			user.login(token);
			
		}catch (AuthenticationException e) {
			this.loginMsg="账户或密码错误";
			request.getSession().setAttribute("loginMsg", loginMsg);
			System.out.println(e.getMessage());
			token.clear();
			return "redirect:/";
		}
		User user2 = userService.getByAccount(currUser.getAccount());
		request.getSession().setAttribute("user", user2);
		user2.setLoginIp(request.getRemoteAddr().toString());
		user2.setLoginDate(new Date());
		userService.save(user2);
		return "index";
		}
	    @RequestMapping(value = "/validateCode")
		public void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
			response.setHeader("Cache-Control", "no-cache");
			String verifyCode = ValidateCode.generateTextCode(ValidateCode.TYPE_NUM_ONLY, 4, null);
			request.getSession().setAttribute("validateCode", verifyCode);
			response.setContentType("image/jpeg");
			BufferedImage bim = ValidateCode.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);
			ImageIO.write(bim, "JPEG", response.getOutputStream());
		}
	    @RequestMapping(value = "/system/index")
	    public String index(HttpServletRequest request,Map<String, Object>map){
	    	User user = (User) request.getSession().getAttribute("user");
	    	List<Menu> menus2 = new ArrayList<>();
		    List<Role> roles =	(List<Role>) user.getRoles();
		    for (Role role : roles) {
				List<Menu> menus = (List<Menu>) role.getMenus();
				menus2.addAll(menus);
			}
		    map.put("role", roles.get(0));
		    map.put("menus", menus2);
		    return "system/nav";
	    }
	    @RequestMapping(value = "/system/main")
	    public String main(){
	    	return "system/main";
	    }
	    @RequestMapping(value = "/system/top")
	    public String top(){
	    	return "system/top";
	    }
}
