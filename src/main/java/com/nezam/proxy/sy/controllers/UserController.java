package com.nezam.proxy.sy.controllers;

import com.nezam.proxy.sy.domains.User;
import com.nezam.proxy.sy.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;

    @GetMapping(value={"/users"})
    public String getAll(Model model){
        System.out.println("**********getAll************");
         List<User> users = service.findAll().getBody();
         model.addAttribute("users", users);
        return "users/users-view";
    }


    @GetMapping(value={"/users/create"})
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getCreateForm(Model model){
        logger.info("getCreateForm Start");
        model.addAttribute("mode","Create");
        model.addAttribute("requestType","POST");
        return "users/user-view";
    }

    @GetMapping(value="/users/{id}")
    //@PreAuthorize("hasRole('ROLE_USER')")
    public String getById(Model model, @PathVariable Long id){
        logger.info("getById Start");
        User obj = this.service.findById(id).getBody();
        model.addAttribute("user", obj);
        model.addAttribute("mode","Update");
        model.addAttribute("requestType","PUT");
        return "users/user-view";
    }



    @PostMapping(value="/users")
   // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String create(Model model,@ModelAttribute User userModel){
        logger.info("getById Start");
        ResponseEntity<User> responseEntity = this.service.create(userModel);
       // if(responseEntity.getStatusCodeValue()==201){
            User obj = responseEntity.getBody();
            model.addAttribute("user", obj);
           // request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.FOUND);
            return "redirect:/users/" + obj.getId();
       // }


    }

    @PostMapping(value="/users/{id}")
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String update(Model model, @PathVariable Long id, @ModelAttribute User userModel){
        logger.info("update Start");
        User obj = this.service.update(userModel,id).getBody();
        model.addAttribute("user", obj);
        model.addAttribute("userModel", new User());

        return "redirect:/users/" + obj.getId();
    }

}
