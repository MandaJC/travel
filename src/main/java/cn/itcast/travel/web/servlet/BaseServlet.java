package cn.itcast.travel.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {
    /**
     * 重写service方法，完成不同访问的分发，就像HttpServlet中的service方法会将get、post、delete等请求分发不同的方法处理一样
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("baseservlet的service方法被执行了");
        //通过反射
        //1.获取请求路径
        String uri = req.getRequestURI(); //    /travel/user/active  不会有请求参数，即?开始的内容
//        System.out.println("请求uri："+uri);
        //2.获取方法名称
        String methodName = uri.substring(uri.lastIndexOf('/') + 1);
//        System.out.println("方法名称："+methodName);
        //3.获取方法对象Method
//        System.out.println(this);//this-谁调用我，我代表谁-UserServlet的对象
        try {
            //忽略访问权限修饰符来获取方法，因为之前的add和find是protected方法-getDeclaredMethod
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //4.执行方法
            //暴力反射
//            method.setAccessible(true);
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 直接将传入的对象序列化为json，并写回客户端
     * @param obj
     */
    public void writeValue(HttpServletResponse response, Object obj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), obj);//返回序列化json
    }

    /**
     * 将传入的对象序列化为json，返回给调用者
     * @param obj
     * @return
     */
    public String writeValueAsString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}
