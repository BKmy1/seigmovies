package com.example.seigmovies.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import com.example.seigmovies.entity.User;
import com.example.seigmovies.service.UserService;
import com.example.seigmovies.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    //验证码
    private LineCaptcha lineCaptcha;

//    @GetMapping(value = "/login/index")
//    public String goLogin() {
//        return "login/index";
//    }

    @GetMapping("/login")
    public String login() {
        return "login";
//        return "backVideo/index";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "/backVideo/welcome";
    }

    @GetMapping("/toIndex")
    public String toIndex() {
        return "backVideo/index";
    }

    @GetMapping(value = "/register/index")
    public String goRegister() {
        return "/register/index";
    }

    @PostMapping("/login/doLogin")
    public String doLogin(HttpSession session, HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkS = request.getParameter("checkStatus");
        password = MD5.encrypt(password);
        System.out.println(username + "," + password + "," + checkS);
        User user = userService.selectUser(username, password);
        session.setAttribute("user",user);
        if ("1".equals(checkS)){
            session.setMaxInactiveInterval(24*60*60);
        }
        System.out.println("用户登录成功" + user);
        return "redirect:/index";
    }

    @GetMapping("login/doLogout")
    public String doLogout(HttpSession session){
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        session.removeAttribute("user");
        return "redirect:/index";
    }

    /**
     * 获取验证码
     */
    @GetMapping("/register/getCode")
    public void getCode(HttpServletResponse response) {
        // 随机生成 4 位验证码
        RandomGenerator randomGenerator = new RandomGenerator("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ", 4);
        // 定义图片的显示大小
        lineCaptcha = CaptchaUtil.createLineCaptcha(110, 36);
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        try {
            // 调用父类的 setGenerator() 方法，设置验证码的类型
            lineCaptcha.setGenerator(randomGenerator);
            // 输出到页面
            lineCaptcha.write(response.getOutputStream());
            // 关闭流
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/register/doRegister")
    public String doRegister(Model model, HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String code = request.getParameter("code");
        code = code.toLowerCase();
        System.out.println(username + "," + password + "," + role);
        password = MD5.encrypt(password);
        int roleId = 0;
        if (!"admin".equals(role)) {
            roleId = 1;
        }
        String captcha = lineCaptcha.getCode();
        captcha = captcha.toLowerCase();
        System.out.println(captcha + "," + code);
        if (!captcha.equals(code)) {
//            model.addAttribute("code","1");
//            model.addAttribute("codeMsg","验证码不正确");
            return "register/index";
        }
        User user = new User();
        String uuid = UUID.randomUUID().toString();
        String userId = uuid.substring(0, 8);
        user.setUser_id(userId);
        user.setAccount(username);
        user.setPassword(password);
        user.setAvatar("http://124.220.158.140/images/avatar.png");
        user.setRole_id(roleId);
        int i = userService.addUser(user);
        System.out.println("管理员添加成功！");
        return "redirect:/login/index";
    }

}
