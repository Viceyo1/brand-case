package com.itheima.mapper;

import com.itheima.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BrandMapper {

    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();


    @Insert("insert  into tb_brand values (null, #{brandName}, #{companyName}, #{ordered}, #{description}, #{status})")
   void add(Brand brand);





















//批量删除
    void deleteByIds(@Param("ids") int[] ids);
































//分页查询
    @Select("select * from tb_brand limit #{begin} , #{size}")
    @ResultMap("brandResultMap")
    List<Brand> selectByPage(@Param("begin") int begin,@Param("size") int size);


    @Select("select count(*) from tb_brand")
    int selectTotalCount();



    //多条件分页查询

    List<Brand> selectByPageAndCondition(@Param("begin") int begin,@Param("size") int size, @Param("brand")Brand brand);


    int selectTotalCountByCondition(Brand brand);




}
