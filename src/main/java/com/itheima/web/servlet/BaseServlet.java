package com.itheima.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *             Servlet代码优化
 */

//替换HttpServlet，根据请求最后一段路径来进行方法分发
public class BaseServlet extends HttpServlet {

    //根据请求最后一段路径来进行方法分发

    @Override                //注意：需要的是有HttpServlet的方法
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求路径
        String uri = req.getRequestURI();//获取出的uri：/brand-case/brand/selectAll
        //2.获取最后一段路径，即方法名
        int index = uri.lastIndexOf("/");
        String methodName = uri.substring(index+1);

        //3.执行方法
        //3.1 获取BrandServlet字节码对象 Class
        Class<? extends BaseServlet> cls =  this.getClass();
        //3.2 获取方法Method对象
        try {
            Method method = cls.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            //3.3 执行方法
            try {
                method.invoke(this,req,resp);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }





}
