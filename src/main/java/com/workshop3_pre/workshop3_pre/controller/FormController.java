package com.workshop3_pre.workshop3_pre.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.workshop3_pre.workshop3_pre.model.User;
import com.workshop3_pre.workshop3_pre.service.impl.ContactService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class FormController {

    @Autowired
    private ContactService contactService;


    @GetMapping("/")
    public String createStudentForm(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "index";
    }

    
    @PostMapping("/contact")
    public String contactPage(@Valid @ModelAttribute("user") User user,BindingResult result,Model model,
    RedirectAttributes redirectAttributes,HttpServletResponse response) {

        if (contactService.checkIfNameExists(user.getName())) {
            FieldError err = new FieldError("user", "name", "Name already exists");
            result.addError(err);
            // return "index";
        }
        if (contactService.checkIfEmailExists(user.getEmail())) {
            ObjectError err = new ObjectError("globalError","Email already exists");
            result.addError(err);
        }
        if (result.hasErrors()) {
            return "index";
        }
        

        if (result.hasErrors()) {
            return "index";
        }
        model.addAttribute("user",user);
        //user.setId("hello!");
        
        contactService.saveUser(user);
    

        redirectAttributes.addFlashAttribute("message","User created successfully!");
        
        return "redirect:/success";
    }
    @GetMapping("/success")
    public String successPage() {
        return "success_page";
    }

    @GetMapping("/contact/{id}")
    public String individualContactPage(@PathVariable String id,Model model){

        if (contactService.checkUserExist(id)==false) {
            return "404_page";
        }
        User user = contactService.getUserById(id);
        model.addAttribute("user",user);
        System.out.println(user.getName());
        return "individual_contact_page";

    }
    @GetMapping("/contacts")
    public String contactsPage(Model model) {
       
        List<User> userList = contactService.getAllUsers();
        System.out.println("Controller list has size: " + userList.size());

        model.addAttribute("userList",userList);
        return "contacts_page";
    }
    //to add delete

    @GetMapping("/delete/{userid}")
    public String deleteUser(@PathVariable("userid") String id) {
        
        contactService.deleteUser(id);
        return "redirect:/contacts";

    }
    
    @GetMapping("/update/{userid}")
    public String showUpdatePage(@PathVariable("userid") String id,Model model) {
        User userToUpdate = contactService.getUserById(id);
        model.addAttribute("user",userToUpdate);
        return "updateuser_page";
    }

    @GetMapping("/updatesuccess")
    public String showUpdateSuccessPage() {
        return "updatesuccess_page";
    }

    
    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("user") User user,BindingResult result,Model model,
    RedirectAttributes redirectAttributes,HttpServletResponse response) {
        if (result.hasErrors()) {
            return "updateuser_page";
        }
        model.addAttribute("user",user);
        //user.setId("hello!");
        
        contactService.saveUser(user);
    

        redirectAttributes.addFlashAttribute("message","User has been updated successfully!");
        
        return "redirect:/updatesuccess";
    }
     
    
}
