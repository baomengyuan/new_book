package com.swjtu.web;


import com.google.gson.Gson;
import com.swjtu.pojo.User;
import com.swjtu.service.UserService;
import com.swjtu.service.impl.UserServiceImpl;
import com.swjtu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author baomengyuan
 * @create 2021-10-15 21:07
 */
public class UserServlet extends BaseServlet {
    private UserService userService=new UserServiceImpl();
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数
        User user=WebUtils.copyParamToBean(req.getParameterMap(),new User());
        //2.调用service处理业务
        User login = userService.login(new User(null, user.getUsername(), user.getPassword(), null));
        if(login==null){
            //把错误信息和回显的表单项信息，保存到Request域中
            req.setAttribute("msg","用户名或密码错误！");
            req.setAttribute("username",user.getUsername());
            //登陆失败，跳回登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else {
            //登陆成功
            //保存用户登录的信息到session域中
            req.getSession().setAttribute("user",login);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取 Session 中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
// 删除 Session 中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //1.获取请求的参数
        User user= WebUtils.copyParamToBean(req.getParameterMap(),new User());
        String code = req.getParameter("code");
        //2.检查验证码是否正确  要求验证码为 “abcde”
        if(token!=null&token.equalsIgnoreCase(code)){
            if(userService.existUsername(user.getUsername())){
                ///不可用，跳回注册页面
                System.out.println("用户名["+user.getUsername()+"]已经存在！");
                //把回显数据保存到request域中
                req.setAttribute("msg","用户名已存在！！！");
                req.setAttribute("username",user.getUsername());
                req.setAttribute("email",user.getEmail());
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
                //
            }else{
                //可用 调用service保存到数据库
                //跳转到注册成功页面
                userService.registerUser(new User(null,user.getUsername(),user.getPassword(),user.getEmail()));
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }else{
            //把回显数据保存到request域中
            req.setAttribute("msg","验证码错误！！！");
            req.setAttribute("username",user.getUsername());
            req.setAttribute("email",user.getEmail());
            //不正确
            System.out.println("验证码错误["+code+"]");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.销毁session中用户登录的信息
        req.getSession().invalidate();
        //2.重定向到首页
        resp.sendRedirect(req.getContextPath());
    }
    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求的参数username
        String username=req.getParameter("username");
        boolean existUsername = userService.existUsername(username);
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("existUsername",existUsername);
        Gson gson=new Gson();
        String json = gson.toJson(resultMap);
        System.out.println(json);
        resp.getWriter().write(json);
    }
}
