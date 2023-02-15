package com.itheima.service.impl;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.http.HttpSession;
import java.util.List;

public class BrandServiceImpl implements BrandService {

    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<Brand> selectAll() {

        SqlSession session = factory.openSession();
        BrandMapper brandMapper = session.getMapper(BrandMapper.class);
        List<Brand> brands = brandMapper.selectAll();
        session.close();

        return brands;
    }




//添加数据
    @Override
    public void add(Brand brand) {
        SqlSession session = factory.openSession();
        BrandMapper brandMapper = session.getMapper(BrandMapper.class);
        brandMapper.add(brand);
        session.commit();
        session.close();
    }


//@Override
//public void add(Brand brand) {
//    SqlSession session =factory.openSession();
//    BrandMapper mapper = session.getMapper(BrandMapper.class);
//    mapper.add(brand);
//    session.commit();
//    session.close();
//}







    //批量删除
    @Override
    public void deleteByIds(int[] ids) {
        SqlSession session = factory.openSession();
        BrandMapper brandMapper = session.getMapper(BrandMapper.class);
        brandMapper.deleteByIds(ids);
        session.commit();
        session.close();
    }




    //分页查询
    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        SqlSession session = factory.openSession();
        BrandMapper brandMapper = session.getMapper(BrandMapper.class);
        int begin = ( currentPage - 1) * pageSize;
        int size = pageSize;
        List<Brand> rows = brandMapper.selectByPage(begin,size);
        int totalCount = brandMapper.selectTotalCount();
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        session.close();
        return pageBean;
    }




    // 条件分页查询
    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        SqlSession session = factory.openSession();
        BrandMapper brandMapper = session.getMapper(BrandMapper.class);
        int begin = ( currentPage - 1) * pageSize;
        int size = pageSize;

        //处理brand条件，模糊表达式
        String brandName = brand.getBrandName();
        if(brandName != null && brandName.length() > 0){
            brand.setBrandName("%" + brandName + "%");
        }
        String companyName = brand.getCompanyName();
        if(companyName != null && companyName.length() > 0){
            brand.setCompanyName("%" + companyName + "%");
        }


        List<Brand> rows = brandMapper.selectByPageAndCondition(begin,size,brand);
        int totalCount = brandMapper.selectTotalCount();
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        session.close();
        return pageBean;
    }



}
