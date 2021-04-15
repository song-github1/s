package com.song.PetShop.dao;

import com.song.PetShop.bean.Gallery;
import com.song.PetShop.bean.GalleryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GalleryMapper {
    long countByExample(GalleryExample example);

    int deleteByExample(GalleryExample example);

    int deleteByPrimaryKey(Integer gid);

    int insert(Gallery record);

    int insertSelective(Gallery record);

    List<Gallery> selectByExample(GalleryExample example);

    Gallery selectByPrimaryKey(Integer gid);

    int updateByExampleSelective(@Param("record") Gallery record, @Param("example") GalleryExample example);

    int updateByExample(@Param("record") Gallery record, @Param("example") GalleryExample example);

    int updateByPrimaryKeySelective(Gallery record);

    int updateByPrimaryKey(Gallery record);
}