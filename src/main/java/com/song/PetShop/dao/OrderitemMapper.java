package com.song.PetShop.dao;

import com.song.PetShop.bean.Orderitem;
import com.song.PetShop.bean.OrderitemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderitemMapper {
    long countByExample(OrderitemExample example);

    int deleteByExample(OrderitemExample example);

    int deleteByPrimaryKey(Integer otid);

    int insert(Orderitem record);

    int insertSelective(Orderitem record);

    List<Orderitem> selectByExample(OrderitemExample example);

    Orderitem selectByPrimaryKey(Integer otid);

    int updateByExampleSelective(@Param("record") Orderitem record, @Param("example") OrderitemExample example);

    int updateByExample(@Param("record") Orderitem record, @Param("example") OrderitemExample example);

    int updateByPrimaryKeySelective(Orderitem record);

    int updateByPrimaryKey(Orderitem record);
}