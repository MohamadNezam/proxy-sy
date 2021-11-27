package com.nezam.proxy.sy.controllers;

import com.nezam.proxy.sy.auth.MyUserDetails;
import com.nezam.proxy.sy.models.Role;
import com.nezam.proxy.sy.models.User;
import com.nezam.proxy.sy.repository.RoleRepository;
import com.nezam.proxy.sy.repository.UserRepository;
import com.nezam.proxy.sy.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.management.relation.RoleStatus;
import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller

@RequestMapping("/")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder(11);

    @Autowired
    private UserService service;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository repository;
//
    @GetMapping(value = "/my-profile")
 // @PreAuthorize("hasRole('ROLE_USER')")
    public String userProfile(Authentication authentication,Model model){
        User loggedUser = ((MyUserDetails) authentication.getPrincipal()).getUser();
        User user = repository.findById(loggedUser.getId()).get();
        model.addAttribute("user", user);
        model.addAttribute("myProfile","USER");
        return "users/user-update";
        //return "users/user-view";
    }
    @PostMapping(value = "/my-profile")
    public String updateUserProfile(Authentication authentication,Model model,@Valid @ModelAttribute("user") User user, BindingResult bindingResult,RedirectAttributes redirectAttributes){
        User old = ((MyUserDetails) authentication.getPrincipal()).getUser();
       // User updatedUser = this.service.update(userModel,old.getId()).getBody();

       // model.addAttribute("user",updatedUser);
       // model.addAttribute("mode","Update");
        //model.addAttribute("userModel", new User());
       // model.addAttribute("message", "Current record updated successfully");

        if(user.getPassword()==null){
            user.setPassword(old.getPassword());
        }

        if(!old.getUsername().equals(user.getUsername())){
            User usernameExist = this.repository.findByUsername(user.getUsername());
            if(usernameExist != null){
                ObjectError error = new ObjectError("globalError", "A user with current username: "+user.getUsername() + " already exist");
                bindingResult.addError(error);
            }
        }


        if (bindingResult.hasErrors()) {
            model.addAttribute("myProfile","USER");
            return "users/user-update";
        }

        if(!old.getPassword().equals(user.getPassword()))
        {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }

        user.setId(old.getId());
        user.setRoles(old.getRoles());
        repository.save(user);

        redirectAttributes.addFlashAttribute("message", "The record updated Successfully");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/my-profile";


    }
//
@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value={"/users"})
    public String getAllUsers(Model model){
        List<User> users = service.findAll().getBody();
        model.addAttribute("users", users);

        return "users/users-view";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value={"/users/create"})
    public String create(Model model){
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("roles", roles);
        model.addAttribute("userModel", new User());
        return "users/user-create";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value={"/users/create"})
    public String processCreate(Model model,@Valid @ModelAttribute("userModel") User user, BindingResult bindingResult,RedirectAttributes redirectAttributes){
        User usernameExist = this.repository.findByUsername(user.getUsername());
        if(usernameExist != null){
            ObjectError error = new ObjectError("globalError", "A user with current username: "+user.getUsername() + " already exist");
            bindingResult.addError(error);
        }
        if (bindingResult.hasErrors()) {
            List<Role> roles = roleRepository.findAll();
            model.addAttribute("roles", roles);
            return "users/user-create";
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        repository.save(user);
        redirectAttributes.addFlashAttribute("message", "New record created Successfully");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/users";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value={"/users/update/{id}"})
    public String update(Model model,@PathVariable Long id){
        List<Role> roles = roleRepository.findAll();
        User user = this.repository.findById(id).get();
        logger.info(id.toString());
        logger.info(user.getId().toString());
        logger.info(user.getUsername());
        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        return "users/user-update";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value={"/users/update/{id}"})
    public String processUpdate(Model model,@PathVariable Long id,@Valid @ModelAttribute("user") User user, BindingResult bindingResult,RedirectAttributes redirectAttributes){
        User old = this.repository.findById(id).get();
        if(user.getPassword()==null){
            user.setPassword(old.getPassword());
        }

        if(!old.getUsername().equals(user.getUsername())){
            User usernameExist = this.repository.findByUsername(user.getUsername());
            if(usernameExist != null){
                ObjectError error = new ObjectError("globalError", "A user with current username: "+user.getUsername() + " already exist");
                bindingResult.addError(error);
            }
        }


        if (bindingResult.hasErrors()) {
            List<Role> roles = roleRepository.findAll();
            model.addAttribute("roles", roles);
            return "users/user-update";
        }

        if(!old.getPassword().equals(user.getPassword()))
        {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }

        repository.save(user);

        redirectAttributes.addFlashAttribute("message", "The record updated Successfully");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/users";
    }


//    @PostMapping(value="/users/{id}")
//    public String update(Model model, @PathVariable Long id, @ModelAttribute User userModel){
//        User user = this.service.update(userModel,id).getBody();
//        model.addAttribute("user", user);
//        //model.addAttribute("userModel", new User());
//        model.addAttribute("message", "Current record updated successfully");
//        return "redirect:/users/" + user.getId();
//    }

    @PreAuthorize("hasRole('ROLE_DELETE') and hasRole('ROLE_ADMIN')")
    @GetMapping(value="/users/delete/{id}")
    public String delete(Model model, @PathVariable Long id){
        this.service.delete(id);
        model.addAttribute("message", "Selected record with id:"+id+" deleted successfully");
        return "redirect:/users/";
    }




}
