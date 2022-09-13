package com.mycompany.controller;

import com.mycompany.Model.User;
/*import com.mycompany.dto.Request;*/
import com.mycompany.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class UserController {
    @Autowired private UserService service;
    @GetMapping("/users")
    public String showuserlist(Model model,HttpSession session){
        List<User> listuser=service.listall();
        model.addAttribute("listuser",listuser);
        if(session.getAttribute("USERNAME") != null)
        return "redirect:login";
        else
            return "redirect:login";
    }
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user",new User());
        return "register";
    }
    @PostMapping("/users/register")
    public String registeruser(User user){
        service.save(user);
        return "redirect:login";
    }
    @GetMapping("/users/new")
    public String Addnewuser(Model model){
            model.addAttribute("user", new User());
            return "form";
    }
    @PostMapping("/users/save")
    public String saveuser(User user){
        service.save(user);
        return "redirect:/users";
    }
    @GetMapping("/users/delete/{id}")
    public String delete(@PathVariable Integer id){
        service.delete(id);
        return "redirect:/users";
    }
    @GetMapping("/users/edit/{id}")
    public String editform(@PathVariable Integer id,Model model){
       model.addAttribute("user",service.getid(id));
        return "chinhsua";
    }
    @RequestMapping("/login")
    public String showlogin(HttpSession session,@CookieValue(value="username",defaultValue = "nope")String username,Model model){
        if (username.compareTo("nope")!=0){
            String uname = Jwts.parser()
                    .parseClaimsJwt(username)
                    .getBody().get("username").toString();

            if (service.checktoken(uname))
            {
                List<User> listuser=service.listall();
                model.addAttribute("listuser",listuser);
                session.setAttribute("USERNAME", uname);
                return "users";
            }
        }
        if(session.getAttribute("USERNAME") != null){
            List<User> listuser=service.listall();
            model.addAttribute("listuser",listuser);
            return "users";
        }
        else {
            model.addAttribute("error","you need to login to your account");
            return "login";
        }

    }
    @PostMapping("checklogin")
    public String checklogin(HttpSession Session, HttpServletResponse respons, @RequestParam("username")String username, @RequestParam("password")String password, Model model) {
        if (service.checklogin(username, password)) {
            List<User> listuser = service.listall();
            model.addAttribute("listuser", listuser);
            Session.setAttribute("USERNAME", username);
            String token = Jwts.builder()
                    .claim("username", username)
                    .setSubject("tuan")
                    .setId(UUID.randomUUID().toString())
                    .compact();
            Cookie cookie=new Cookie("username",token);
            respons.addCookie(cookie);
                return "redirect:users";
        } else {
            model.addAttribute("error", "Email or password not exitst");

        }
        return "login";
    }
    @GetMapping("logout")
    public String logout(HttpSession session,HttpServletResponse response){
        Cookie cookie=new Cookie("username",null);
        cookie.setMaxAge(0);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        session.removeAttribute("USERNAME");
        return "redirect:login";
    }
    @GetMapping("/course")
    public String course(Model model){
        List<User> listuser=service.findall();
        model.addAttribute("listuser",listuser);
        return "courses";
    }
}
