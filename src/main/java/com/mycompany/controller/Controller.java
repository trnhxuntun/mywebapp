package com.mycompany.controller;

import com.mycompany.Model.User;
/*import com.mycompany.dto.Request;*/
import com.mycompany.repository.CourseRepository;
import com.mycompany.repository.UserRepository;
import com.mycompany.service.UserService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
public class Controller {
    @Autowired
    private UserRepository urepo;
    @Autowired
    private CourseRepository crepo;
    @Autowired
    private UserService service;
    @GetMapping("/findall")
    public List<User> findall(){
        return (List<User>) urepo.findAll();
    }


}
