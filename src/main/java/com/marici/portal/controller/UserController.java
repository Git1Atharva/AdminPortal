package com.marici.portal.controller;

import com.marici.portal.model.User;
import com.marici.portal.repository.UserRepo;
import com.marici.portal.service.ServiceInterface;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class UserController {
    @Autowired
    UserRepo repo;

    @Autowired
    User user;

    @Autowired
    ServiceInterface service;

    // Login page mapping
    @GetMapping("/login")
    public String showLogin(){
        return "login";
    }
    // Update page mapping
    @GetMapping("/updateForm")
    public String showUpdate(){
            return "updateForm";
    }


    //Update Mapping
    @GetMapping("/updateUser")
    public String updateUser(@RequestParam int id, Model model,HttpSession session,RedirectAttributes redirectAttributes){
        User user=repo.findByuserId(id);
        //Passing data to update form
        String role= (String) session.getAttribute("role");
        //Validating user role
        if (role.equals("ADMIN")) {
            model.addAttribute("userData",user);
            return "/updateForm";
        }
        else {
            redirectAttributes.addFlashAttribute("wrongUpdate",true);
            return "redirect:/userData";
        }
    }



    //Accepting data from login
    @PostMapping("/login")
    public String process(@RequestParam String username, @RequestParam String password, Model model)
    {
        //Checking user credential
        if(service.login(username,password)){
            return "redirect:/userData";
        }
        else {
            model.addAttribute("error",true);
            return "login";
        }
    }

    // Add user page mapping
    @GetMapping("/addUser")
    public String addUser(HttpSession session,Model model,RedirectAttributes redirectAttributes) {
        String role= (String) session.getAttribute("role");
        //Validating user role
        if (role.equals("ADMIN")) {
            return "addUser";
        }
        else {
            redirectAttributes.addFlashAttribute("wrong",true);
            return "redirect:/userData";
        }
    }

    //Delete user page mapping
    @GetMapping("/deleteUser")
    public String delete(@RequestParam int id,RedirectAttributes redirectAttributes){

        if (service.deleteUser(id)) {
            redirectAttributes.addFlashAttribute("validUser",true);

        }else {
            redirectAttributes.addFlashAttribute("wrongUser",true);
             }
        return "redirect:/userData";
    }

    //User data mapping
    @GetMapping("/userData")
    public String showUserData(Model model){
        List<User> list=repo.findAll();
        //Passing data to the main page
        model.addAttribute("employeeList",list);
        return "index";
}

//Accepting data from update form
@PostMapping("/updateUser")
public String updateUser(@RequestParam int id,@RequestParam String username,@RequestParam String password,String role,RedirectAttributes redirectAttributes){
        if(service.updateUser(id,username,password,role)){
            redirectAttributes.addFlashAttribute("updated",true);
            return"redirect:/userData";
        }
        else {

            return "redirect:/updateUser";
        }
}

//Accepting data from Add user form
@PostMapping("/addUser")
public String addUser(@RequestParam String username,@RequestParam String password,@RequestParam String role,RedirectAttributes redirectAttributes){
        if(service.addUser(username,password,role)){
            redirectAttributes.addFlashAttribute("added",true);
            return "redirect:/userData";
        }
       else {
           return "redirect:/addUser";
        }
}
}
