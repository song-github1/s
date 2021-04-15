package com.song.PetShop.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.song.PetShop.bean.Commodity;
import com.song.PetShop.bean.CommodityExample;
import com.song.PetShop.dao.CommodityMapper;
import com.song.PetShop.util.CosUploadUtil;

@Controller
public class IndexAction {
	@Resource
	CommodityMapper cm;
	
	@GetMapping({"/","index","index.do"})
	public String index(Model m){
		CommodityExample ce=new CommodityExample();
		ce.createCriteria().andTemp1EqualTo("1");
		List<Commodity>clist=cm.selectByTwelve(ce);
		for(int i=0;i<clist.size();i++){
			String cphoto=clist.get(i).getCphoto();
			clist.get(i).setCphoto(CosUploadUtil.getUrl(cphoto));
			System.out.println(clist.get(i).getCphoto());
		}
		m.addAttribute("clist", clist);
		return "index";
	}
}
