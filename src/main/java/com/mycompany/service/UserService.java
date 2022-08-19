package com.mycompany.service;

import com.mycompany.Model.Course;
import com.mycompany.Model.User;
import com.mycompany.repository.CourseRepository;
import com.mycompany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired private UserRepository repo;
    @Autowired private CourseRepository repon;

    public  List<User> listall(){
        return  (List<User>) repo.findAll();
    }

    public List<Course> getlist(){return (List<Course>) repon.findAll(); }
    public User save(User user) {
        repo.save(user);
        return user;
    }
    public void delete(Integer id){
        repo.deleteById(id);
    }
    public User getid(Integer id){
        return repo.findById(id).get();
    }

    public List<User> findall(){
        return (List<User>) repo.findAll();
    }
    public boolean checklogin(String username,String password){
        Optional<User> optionalUser= repo.findByEmail(username);
        if(optionalUser.isPresent() && optionalUser.get().getPassword().equals(password)){
            return true;
        }
        return false;
    }
    public boolean checktoken(String username){
        Optional<User> optionalUser= repo.findByEmail(username);
        if(optionalUser.isPresent()){
            return true;
        }
        return false;
    }

}
