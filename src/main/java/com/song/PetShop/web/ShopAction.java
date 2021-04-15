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
public class ShopAction {
	@Resource
	CommodityMapper cm;
	@Resource
	TypeMapper tm;
	@Resource
	FavoriteMapper fm;
	
	@GetMapping("shop")
	public String shop(Model m){
		
		return "shop";
	}
	
	@GetMapping("shop_{tid}")
	public String toShop(Model m,@PathVariable("tid") Integer tid,@RequestParam(name="page",required=true,defaultValue="1") Integer page,HttpSession sess){
		User user=(User) sess.getAttribute("user");
		CommodityExample ce=new CommodityExample();
		String word=null;//用于区分
		
		List<Commodity>clist2=cm.selectByThree(null);
		for(int i=0;i<clist2.size();i++){
			String cphoto=clist2.get(i).getCphoto();
			clist2.get(i).setCphoto(CosUploadUtil.getUrl(cphoto));
			System.out.println(clist2.get(i).getCphoto());
		}
		
		if(tid==11){
			ce.createCriteria().andTemp1EqualTo("1").andTidBetween(1, 3);
			//查询包含的类型
			Integer tid2=1;
			Type t=tm.selectByPrimaryKey(tid2);
			TypeExample te=new TypeExample();
			te.createCriteria().andVidEqualTo(t.getVid());
			List<Type> tlist=tm.selectByExample(te);
			m.addAttribute("tlist", tlist);
		}else if(tid==12){
			ce.createCriteria().andTemp1EqualTo("1").andTidBetween(4, 6);
			//查询包含的类型
			Integer tid2=4;
			Type t=tm.selectByPrimaryKey(tid2);
			TypeExample te=new TypeExample();
			te.createCriteria().andVidEqualTo(t.getVid());
			List<Type> tlist=tm.selectByExample(te);
			m.addAttribute("tlist", tlist);
		}else if(tid==13){
			ce.createCriteria().andTemp1EqualTo("1").andTidBetween(7, 8);
			//查询包含的类型
			Integer tid2=7;
			Type t=tm.selectByPrimaryKey(tid2);
			TypeExample te=new TypeExample();
			te.createCriteria().andVidEqualTo(t.getVid());
			List<Type> tlist=tm.selectByExample(te);
			m.addAttribute("tlist", tlist);
		}else if(tid==14){
			ce.createCriteria().andTemp1EqualTo("1").andTidNotBetween(1, 8);
			//查询包含的类型
			Integer tid2=0;
			Type t=tm.selectByPrimaryKey(tid2);
			TypeExample te=new TypeExample();
			te.createCriteria().andVidEqualTo(t.getVid());
			List<Type> tlist=tm.selectByExample(te);
			m.addAttribute("tlist", tlist);
		}else{
			ce.createCriteria().andTemp1EqualTo("1").andTidEqualTo(tid);
			//查询包含的类型
			Type t=tm.selectByPrimaryKey(tid);
			TypeExample te=new TypeExample();
			te.createCriteria().andVidEqualTo(t.getVid());
			List<Type> tlist=tm.selectByExample(te);
			m.addAttribute("tlist", tlist);
		}
		PageHelper.startPage(page, 6);
		List<Commodity>clist=cm.selectByExample(ce);
		for(int i=0;i<clist.size();i++){
			String cphoto=clist.get(i).getCphoto();
			clist.get(i).setCphoto(CosUploadUtil.getUrl(cphoto));
			System.out.println(clist.get(i).getCphoto());
			
			if(user==null){
				clist.get(i).setFavorite(null);
			}else{
				Integer uid=user.getUid();
				System.out.println(uid);
				List<Favorite> flist=clist.get(i).getFavorite();
				for(int j=0;j<flist.size();j++){
					if(flist.get(j).getUid()!=uid){
						flist.remove(j);
					}
				}
			}
		}
		PageInfo<Commodity> cinfo=new PageInfo<Commodity>(clist);
		m.addAttribute("cinfo", cinfo);
		m.addAttribute("tid", tid);
		m.addAttribute("word", word);
		m.addAttribute("clist2", clist2);
		return "shop";
	}
	
	@GetMapping("search")
	public String seach(Model m,@RequestParam(name="page",required=true,defaultValue="1") Integer page,@RequestParam(name="word") String word,HttpSession sess){
		User user=(User) sess.getAttribute("user");
		
		List<Commodity>clist2=cm.selectByThree(null);
		for(int i=0;i<clist2.size();i++){
			String cphoto=clist2.get(i).getCphoto();
			clist2.get(i).setCphoto(CosUploadUtil.getUrl(cphoto));
			System.out.println(clist2.get(i).getCphoto());
		}
		
		PageHelper.startPage(page, 6);
		List<Commodity>clist=cm.selectByCname(word);
		for(int i=0;i<clist.size();i++){
			String cphoto=clist.get(i).getCphoto();
			clist.get(i).setCphoto(CosUploadUtil.getUrl(cphoto));
			System.out.println(clist.get(i).getCphoto());
			
			if(user==null){
				clist.get(i).setFavorite(null);
			}else{
				Integer uid=user.getUid();
				System.out.println(uid);
				List<Favorite> flist=clist.get(i).getFavorite();
				for(int j=0;j<flist.size();j++){
					if(flist.get(j).getUid()!=uid){
						flist.remove(j);
					}
				}
			}
		}
		PageInfo<Commodity> cinfo=new PageInfo<Commodity>(clist);
		m.addAttribute("cinfo", cinfo);
		m.addAttribute("word", word);
		m.addAttribute("clist2", clist2);
		return "shop";
	}
	
	@ResponseBody
	@PostMapping("dofavorite")
	public int dofavorite(Model m,Integer cid,HttpSession sess){
		System.out.println(cid);
		User user=(User) sess.getAttribute("user");
		FavoriteExample fe=new FavoriteExample();
		fe.createCriteria().andCidEqualTo(cid).andUidEqualTo(user.getUid());
		List<Favorite> flist=fm.selectByExample(fe);
		if(flist.size()>0){
			fm.deleteByPrimaryKey(flist.get(0).getFid());
			return 0;
		}else{
			Favorite favo=new Favorite();
			favo.setCid(cid);
			favo.setUid(user.getUid());
			fm.insertSelective(favo);
			return 1;
		}
		
	}
}
