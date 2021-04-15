package com.song.PetShop.dao;

import com.song.PetShop.bean.Commodity;
import com.song.PetShop.bean.CommodityExample;


import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommodityMapper {
	
	List<Commodity> selectByTwelve(CommodityExample example);
	
	List<Commodity> selectByCname(String cname);
	
	List<Commodity> selectByThree(CommodityExample example);
	
    long countByExample(CommodityExample example);

    int deleteByExample(CommodityExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(Commodity record);

    int insertSelective(Commodity record);

    List<Commodity> selectByExample(CommodityExample example);

    Commodity selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") Commodity record, @Param("example") CommodityExample example);

    int updateByExample(@Param("record") Commodity record, @Param("example") CommodityExample example);

    int updateByPrimaryKeySelective(Commodity record);

    int updateByPrimaryKey(Commodity record);
}