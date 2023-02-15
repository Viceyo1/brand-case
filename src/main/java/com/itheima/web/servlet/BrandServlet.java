package com.itheima.web.servlet;


import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{
    private BrandService brandService = new BrandServiceImpl();



    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Brand> brands = brandService.selectAll();
        String brandStr = JSON.toJSONString(brands);
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write(brandStr);
    }



    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //1.接受品牌数据
        BufferedReader br = req.getReader();
        String param = br.readLine();//jason字符串

        //转为Brand对象
        Brand brand = JSON.parseObject(param,Brand.class);

        //2.调用Service添加
        brandService.add(brand);

        //3.响应成功标识
        resp.getWriter().write("success");
    }

















































//批量删除
    public void deleteByIds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //1.接受品牌数据
        BufferedReader br = req.getReader();
        String param = br.readLine();//jason字符串

        //转为Brand对象
        int[] ints = JSON.parseObject(param,int[].class);

        //2.调用Service添加
        brandService.deleteByIds(ints);

        //3.响应成功标识
        resp.getWriter().write("success");
    }






    //分页查询
    public void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       String _currentPage = req.getParameter("currentPage");
       String _pageSize = req.getParameter("pageSize");
       int currentPage = Integer.parseInt(_currentPage);
       int pageSize = Integer.parseInt(_pageSize);
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage,pageSize);
        String jsonString = JSON.toJSONString(pageBean);
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write(jsonString);
    }






    //条件分页查询
    public void selectByPageAndCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        BufferedReader br = req.getReader();
        String param = br.readLine();//jason字符串

        //转为Brand对象
        Brand brand = JSON.parseObject(param,Brand.class);

        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage,pageSize,brand);


        String jsonString = JSON.toJSONString(pageBean);
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write(jsonString);
}









































}
