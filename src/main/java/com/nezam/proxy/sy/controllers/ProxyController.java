package com.nezam.proxy.sy.controllers;

import com.nezam.proxy.sy.auth.MyUserDetails;
import com.nezam.proxy.sy.models.Proxy;
import com.nezam.proxy.sy.models.Role;
import com.nezam.proxy.sy.models.User;
import com.nezam.proxy.sy.repository.ProxyRepository;
import com.nezam.proxy.sy.repository.RoleRepository;
import com.nezam.proxy.sy.repository.UserRepository;
import com.nezam.proxy.sy.services.ProxyService;
import com.nezam.proxy.sy.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Controller

@RequestMapping("/")
public class ProxyController {
    private static final Logger logger = LoggerFactory.getLogger(ProxyController.class);

    @Autowired
    private ProxyService service;

    @Autowired
    private ProxyRepository repository;

    @Autowired
    private UserRepository userRepository;

//    @GetMapping(value = "/my-profile")
// // @PreAuthorize("hasRole('ROLE_USER')")
//    public String userProfile(Authentication authentication,Model model){
//        User loggedUser = ((MyUserDetails) authentication.getPrincipal()).getUser();
//        User user = repository.findById(loggedUser.getId()).get();
//        model.addAttribute("user", user);
//        model.addAttribute("myProfile","USER");
//        return "users/user-update";
//        //return "users/user-view";
//    }
//    @PostMapping(value = "/my-profile")
//    public String updateUserProfile(Authentication authentication,Model model,@Valid @ModelAttribute("user") User user, BindingResult bindingResult,RedirectAttributes redirectAttributes){
//        User old = ((MyUserDetails) authentication.getPrincipal()).getUser();
//       // User updatedUser = this.service.update(userModel,old.getId()).getBody();
//
//       // model.addAttribute("user",updatedUser);
//       // model.addAttribute("mode","Update");
//        //model.addAttribute("userModel", new User());
//       // model.addAttribute("message", "Current record updated successfully");
//
//        if(user.getPassword()==null){
//            user.setPassword(old.getPassword());
//        }
//
//        if(!old.getUsername().equals(user.getUsername())){
//            User usernameExist = this.repository.findByUsername(user.getUsername());
//            if(usernameExist != null){
//                ObjectError error = new ObjectError("globalError", "A user with current username: "+user.getUsername() + " already exist");
//                bindingResult.addError(error);
//            }
//        }
//
//
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("myProfile","USER");
//            return "users/user-update";
//        }
//
//        if(!old.getPassword().equals(user.getPassword()))
//        {
//            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        }
//
//        user.setId(old.getId());
//        user.setRoles(old.getRoles());
//        repository.save(user);
//
//        redirectAttributes.addFlashAttribute("message", "The record updated Successfully");
//        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
//        return "redirect:/my-profile";
//
//
//    }
////
@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value={"/proxies"})
    public String getAllProxies(Model model){
        List<Proxy> proxies = service.findAll().getBody();
        model.addAttribute("proxies", proxies);
        return "proxies/proxies-view";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value={"/proxies/create"})
    public String create(Model model){
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("proxyModel", new Proxy());
        model.addAttribute("proxyStatues",  new ArrayList<Proxy.proxyStatues>(EnumSet.allOf(Proxy.proxyStatues.class)));
        return "proxies/proxy-create";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value={"/proxies/create"})
    public String processCreate(Model model,@Valid @ModelAttribute("proxyModel") Proxy proxy, BindingResult bindingResult,RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {
            List<User> users = userRepository.findAll();
            model.addAttribute("users", users);
            return "proxies/proxy-create";
        }

        repository.save(proxy);

        redirectAttributes.addFlashAttribute("message", "New proxy created Successfully");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/proxies";
    }
//
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @GetMapping(value={"/users/update/{id}"})
//    public String update(Model model,@PathVariable Long id){
//        List<Role> roles = roleRepository.findAll();
//        User user = this.repository.findById(id).get();
//        logger.info(id.toString());
//        logger.info(user.getId().toString());
//        logger.info(user.getUsername());
//        model.addAttribute("roles", roles);
//        model.addAttribute("user", user);
//        return "users/user-update";
//    }
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @PostMapping(value={"/users/update/{id}"})
//    public String processUpdate(Model model,@PathVariable Long id,@Valid @ModelAttribute("user") User user, BindingResult bindingResult,RedirectAttributes redirectAttributes){
//        User old = this.repository.findById(id).get();
//        if(user.getPassword()==null){
//            user.setPassword(old.getPassword());
//        }
//
//        if(!old.getUsername().equals(user.getUsername())){
//            User usernameExist = this.repository.findByUsername(user.getUsername());
//            if(usernameExist != null){
//                ObjectError error = new ObjectError("globalError", "A user with current username: "+user.getUsername() + " already exist");
//                bindingResult.addError(error);
//            }
//        }
//
//
//        if (bindingResult.hasErrors()) {
//            List<Role> roles = roleRepository.findAll();
//            model.addAttribute("roles", roles);
//            return "users/user-update";
//        }
//
//        if(!old.getPassword().equals(user.getPassword()))
//        {
//            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        }
//
//        repository.save(user);
//
//        redirectAttributes.addFlashAttribute("message", "The record updated Successfully");
//        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
//        return "redirect:/users";
//    }
//
//
////    @PostMapping(value="/users/{id}")
////    public String update(Model model, @PathVariable Long id, @ModelAttribute User userModel){
////        User user = this.service.update(userModel,id).getBody();
////        model.addAttribute("user", user);
////        //model.addAttribute("userModel", new User());
////        model.addAttribute("message", "Current record updated successfully");
////        return "redirect:/users/" + user.getId();
////    }
//
//    @PreAuthorize("hasRole('ROLE_DELETE') and hasRole('ROLE_ADMIN')")
//    @GetMapping(value="/users/delete/{id}")
//    public String delete(Model model, @PathVariable Long id){
//        this.service.delete(id);
//        model.addAttribute("message", "Selected record with id:"+id+" deleted successfully");
//        return "redirect:/users/";
//    }
//



}
