package com.song.PetShop.web;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
public class ForgetPassAction {
	@Resource
	private UserMapper um;
	@Autowired
	private JavaMailSender mailSender;
	@Value("${mail.fromMail.addr}")
	private String from;
	private int code;
	
	@GetMapping("forgetpass")
	public String index(Model m){
		
		return "forgetpass";
	}
	
	@ResponseBody
	@PostMapping("fgetcode")
	public int getCode(User user){
		Random random = new Random();
		code = random.nextInt(9999);
		System.out.println(code);
    	try {
    		SimpleMailMessage message=new SimpleMailMessage();
        	message.setFrom(from);
        	System.out.println(from);
        	message.setTo(user.getUemail());
        	System.out.println(user.getUemail());
        	message.setSubject("petty找回密码");
        	message.setText("您的邮箱验证码为："+code+"，请尽快输入以免失效");
            mailSender.send(message);
    		System.out.println(code);
    		return 1;
		} catch (Exception e) {
			return 0;
		}
	}
	
	@ResponseBody
	@PostMapping("dofpass")
	public int fpass(User user,String forcode){
		System.out.println("1");
		UserExample ue=new UserExample();
		ue.createCriteria().andUemailEqualTo(user.getUemail());
		List<User> ulist=um.selectByExample(ue);
		if(ulist.size()==0){
			return 2;
		}else if(ulist.size()>0){
			if(Integer.valueOf(forcode)==code){
				user.setUpass(Md5.getMD5(user.getUpass()));
				UserExample ue2=new UserExample();
				ue2.createCriteria().andUemailEqualTo(user.getUemail());
				um.updateByExampleSelective(user, ue2);
				return 1;
			}else{
				return 3;
			}
			
		}else{
			return 0;
		}
		
	}
}
