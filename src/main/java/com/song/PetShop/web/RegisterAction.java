package com.song.PetShop.web;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.song.PetShop.bean.User;
import com.song.PetShop.bean.UserExample;
import com.song.PetShop.dao.UserMapper;
import com.song.PetShop.util.Md5;


@RestController
public class RegisterAction {
	@Resource
	private UserMapper um;
	@Autowired
	private JavaMailSender mailSender;
	@Value("${mail.fromMail.addr}")
	private String from;
	private int code;
	
	@PostMapping("getcode")
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
        	message.setSubject("petty注册");
        	message.setText("欢迎注册，您的邮箱验证码为："+code+"，请尽快输入以免失效");
            mailSender.send(message);
    		System.out.println(code);
    		return 1;
		} catch (Exception e) {
			return 0;
		}
	}
	
	@PostMapping("reg")
	public int reg(User user,String regcode){
		UserExample ue=new UserExample();
		ue.createCriteria().andUemailEqualTo(user.getUemail());
		List<User> ulist=um.selectByExample(ue);
		if(ulist.size()>0){
			return 2;
		}else if(ulist.size()==0){
			if(Integer.valueOf(regcode)==code){
				user.setUpass(Md5.getMD5(user.getUpass()));
				um.insert(user);
				return 1;
			}else{
				return 3;
			}
			
		}else{
			return 0;
		}
		
	}
	
}
