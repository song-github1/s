package com.song.PetShop.dao;

import com.song.PetShop.bean.Variety;
import com.song.PetShop.bean.VarietyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VarietyMapper {
    long countByExample(VarietyExample example);

    int deleteByExample(VarietyExample example);

    int deleteByPrimaryKey(Integer vid);

    int insert(Variety record);

    int insertSelective(Variety record);

    List<Variety> selectByExample(VarietyExample example);

    Variety selectByPrimaryKey(Integer vid);

    int updateByExampleSelective(@Param("record") Variety record, @Param("example") VarietyExample example);

    int updateByExample(@Param("record") Variety record, @Param("example") VarietyExample example);

    int updateByPrimaryKeySelective(Variety record);

    int updateByPrimaryKey(Variety record);
}