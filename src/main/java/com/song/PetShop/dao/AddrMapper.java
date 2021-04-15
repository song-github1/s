package com.song.PetShop.dao;

import com.song.PetShop.bean.Addr;
import com.song.PetShop.bean.AddrExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AddrMapper {
    long countByExample(AddrExample example);

    int deleteByExample(AddrExample example);

    int deleteByPrimaryKey(Integer aid);

    int insert(Addr record);

    int insertSelective(Addr record);

    List<Addr> selectByExample(AddrExample example);

    Addr selectByPrimaryKey(Integer aid);

    int updateByExampleSelective(@Param("record") Addr record, @Param("example") AddrExample example);

    int updateByExample(@Param("record") Addr record, @Param("example") AddrExample example);

    int updateByPrimaryKeySelective(Addr record);

    int updateByPrimaryKey(Addr record);
}