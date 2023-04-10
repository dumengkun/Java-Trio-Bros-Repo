package com.trio.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TrioController {

    @Autowired
    private TrioClerk clerk;

    @GetMapping("/home")
    public String studentPage(Model model) {

        return "Home";
        
    }

    @GetMapping("/results")
    public String showStudentPage(Model model) {

        List<User> users = clerk.getAllUsers();
        model.addAttribute("searchPerson", new User());
        model.addAttribute("personOutput", users);
        
        return "Results";
        
    }

    @PostMapping("/results")
    public String showFilteredStudentPage(Model model, @ModelAttribute("searchPerson") User poi){

        if (poi.getEmail().isEmpty()) {
            List<User> users = clerk.findByName(poi.getName());
            model.addAttribute("personOutput", users);
            poi.setName(null);
        } else {
            User user = clerk.findByEmail(poi.getEmail());
            model.addAttribute("personOutput", user);
            poi.setEmail(null);
        }
        
        return "Results";

    }

    @GetMapping("/register")
    public String setStudentPage(Model model) {

        model.addAttribute("personInput", new User());

        return "Register";

    }

    @PostMapping("/register")
    public String getStudentPage(Model model, @ModelAttribute("personInput") User poi) {

        try {
            clerk.setStudent(poi); 
            model.addAttribute("message", "Registration successful!");
            model.addAttribute("personOutput", poi);
        } catch (Exception e) {
            model.addAttribute("message", "The same email address cannot be registered twice.");
            poi.setName("***");
            model.addAttribute("personOutput", poi);
        }

        return "Status";

    }

    @GetMapping("/update")
    public String updateStudentPage(Model model) {

        return "Update";

    }

    @PostMapping("/update")
    public String getUpdatedStudentPage(Model model, @RequestParam("emailInput") String email) {

        if (email.equals("all") || email.equals("All") || email.equals("ALL")) {
            List<User> users = clerk.getAllUsers();
            model.addAttribute("personOutput", users);
            model.addAttribute("message", "Users below are deleted");
            clerk.deleteAllUsers();
        } else {
            User user = clerk.findByEmail(email);
            if (user != null) {
                model.addAttribute("personOutput", user);
                model.addAttribute("message", "User below is deleted");
                clerk.deleteByEmail(email); 
            } else {
                model.addAttribute("message", "No such user");
            }
        }

        return "Status";

    }

}