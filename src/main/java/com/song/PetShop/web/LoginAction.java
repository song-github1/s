package com.song.PetShop.web;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.song.PetShop.bean.User;
import com.song.PetShop.bean.UserExample;
import com.song.PetShop.dao.UserMapper;
import com.song.PetShop.util.Md5;

@Controller
public class LoginAction {
	@Resource
	private UserMapper um;
	
	@GetMapping("login")
	public String login(){
		return "login";
	}
	
	@ResponseBody
	@PostMapping("dologin")
	public int doLogin(User user,Model m,HttpSession sess){
		user.setUpass(Md5.getMD5(user.getUpass()));
		UserExample ue=new UserExample();
		ue.createCriteria().andUemailEqualTo(user.getUemail()).andUpassEqualTo(user.getUpass());
		List<User> ulist=um.selectByExample(ue);
		if(ulist.size()>0){
			System.out.println(ulist.get(0).getUname());
			m.addAttribute("user",ulist.get(0));
			sess.setAttribute("user", ulist.get(0));
			return 1;
		}
		return 0;
		
	}
	
	@GetMapping("logout")
	public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();//获取当前session
		if(session!=null){
			session.removeAttribute("user");
			response.sendRedirect("/login");
		}
		
	}
}
