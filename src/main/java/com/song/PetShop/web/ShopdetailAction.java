package com.song.PetShop.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.song.PetShop.bean.Commodity;
import com.song.PetShop.bean.CommodityExample;
import com.song.PetShop.bean.Favorite;
import com.song.PetShop.bean.FavoriteExample;
import com.song.PetShop.bean.Type;
import com.song.PetShop.bean.TypeExample;
import com.song.PetShop.bean.User;
import com.song.PetShop.dao.CommodityMapper;
import com.song.PetShop.dao.FavoriteMapper;
import com.song.PetShop.dao.TypeMapper;
import com.song.PetShop.util.CosUploadUtil;

@Controller
public class ShopdetailAction {
	@Resource
	CommodityMapper cm;
	@Resource
	FavoriteMapper fm;
	
	
	@GetMapping("shopdetail_{cid}")
	public String toShop(Model m,@PathVariable("cid") Integer cid,HttpSession sess){
		User user=(User) sess.getAttribute("user");
		if(user!=null){
			FavoriteExample fe=new FavoriteExample();
			fe.createCriteria().andCidEqualTo(cid).andUidEqualTo(user.getUid());
			List<Favorite> flist=fm.selectByExample(fe);
			m.addAttribute("flist", flist);
		}
		
		
		CommodityExample ce=new CommodityExample();
		ce.createCriteria().andCidEqualTo(cid);
		List<Commodity> clist=cm.selectByExample(ce);
		for(int i=0;i<clist.size();i++){
			String cphoto=clist.get(i).getCphoto();
			clist.get(i).setCphoto(CosUploadUtil.getUrl(cphoto));
			
			StringBuilder sb = new StringBuilder(CosUploadUtil.getUrl(cphoto));
			sb.replace(sb.lastIndexOf("/"), sb.lastIndexOf("!"), "/550x710");
			clist.get(i).setCphoto2(CosUploadUtil.getUrl(cphoto));
			System.out.println(clist.get(i).getCphoto());
			System.out.println(clist.get(i).getCphoto2());
		}
		
		List<Commodity>clist2=cm.selectByThree(null);
		for(int i=0;i<clist2.size();i++){
			String cphoto=clist2.get(i).getCphoto();
			clist2.get(i).setCphoto(CosUploadUtil.getUrl(cphoto));
			System.out.println(clist2.get(i).getCphoto());
		}
		
		CommodityExample ce2=new CommodityExample();
		ce2.createCriteria().andTidEqualTo(clist.get(0).getTid());
		List<Commodity>clist3=cm.selectByThree(ce2);
		for(int i=0;i<clist3.size();i++){
			if(clist3.get(i).getCid()==cid){
				clist3.remove(i);
			}
			
			String cphoto=clist3.get(i).getCphoto();
			clist3.get(i).setCphoto(CosUploadUtil.getUrl(cphoto));
			System.out.println(clist3.get(i).getCphoto());
			
			
			if(user==null){
				clist3.get(i).setFavorite(null);
			}else{
				Integer uid=user.getUid();
				System.out.println(uid);
				List<Favorite> flist=clist3.get(i).getFavorite();
				for(int j=0;j<flist.size();j++){
					if(flist.get(j).getUid()!=uid){
						flist.remove(j);
					}
				}
			}
		}
		m.addAttribute("clist", clist);
		m.addAttribute("clist2", clist2);
		m.addAttribute("clist3", clist3);
		return "shopdetail";
	}
	
}
