package com.trio.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TrioController {

    @Autowired
    private TrioClerk clerk;

    @Autowired
    private TrioArtist artist;

    @GetMapping("/home")
    public String homePage(Model model) {

        return "home";
        
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {

        return artist.renderSignupPage(model);

    }

    @PostMapping("/signup")
    public String signupPostPage(Model model, @ModelAttribute("newUser") Volunteer poi) {

        if (poi.pwdFormAlert()) {

            return artist.renderPwdPage(model);

        } else {

            try {

                poi.emailLowerCase();
                clerk.setUser(poi);
                return artist.renderSignupPostPage(model, poi);

            } catch (Exception e) {

                return artist.renderEmailPage(model, poi);

            }

        }

    }

    @GetMapping("/signin")
    public String signinPage(Model model) {

        return artist.renderSigninPage(model);

    }

    @PostMapping("/signin")
    public String signinPostPage(Model model, @ModelAttribute("guest") Guest guest) {

        Volunteer user = clerk.findByUserEmail(guest.getEmail());
        return artist.renderSigninPostPage(model, user, guest);

    }

    @GetMapping("/update")
    public String updatePage(Model model) {

        return artist.renderUpdatePage(model);

    }

    @PostMapping("/update")
    public String userPostPage(Model model, @ModelAttribute("guest") Guest guest) {

        Volunteer user = clerk.findByUserEmail(guest.getEmail());
        Volunteer otherUser = clerk.findByUserEmail(guest.getNewEmail());

        if (user != null && user.pwdChecker(guest.getPassword())) {
    
            if (!guest.getNewEmail().isEmpty()) {

                if (otherUser != null) {

                    return artist.renderEmailPage(model, otherUser);
                }

                user.setEmail(guest.getNewEmail());

            }

            if (!guest.getNewPassword().isEmpty()) {

                if (guest.pwdFormAlert()) {

                    return artist.renderPwdPage(model);

                }

                user.setPassword(guest.getNewPassword());
    
            }

            if (!guest.getName().isEmpty()) {
                
                user.setName(guest.getName());

            }

            if (!guest.getNote().isEmpty()) {
                
                user.setNote(guest.getNote());
            
            }

            clerk.deleteByUserEmail(guest.getEmail());
            clerk.setUser(user);
            model.addAttribute("myUser", user);
            return "user-page";

        } else {

            return artist.renderIdentificationPage(model);
            
        }

    }

    @GetMapping("/results")
    public String showStudentPage(Model model) {

        List<Volunteer> users = clerk.getAllUsers();
        model.addAttribute("searchPerson", new Volunteer());
        model.addAttribute("personOutput", users);
        
        return "admin/results";
        
    }

    @PostMapping("/results")
    public String showFilteredStudentPage(Model model, @ModelAttribute("searchPerson") Volunteer poi){

        if (poi.getEmail().isEmpty()) {
            List<Volunteer> users = clerk.findByUserName(poi.getName());
            model.addAttribute("personOutput", users);
            poi.setName(null);
        } else {
            Volunteer user = clerk.findByUserEmail(poi.getEmail());
            model.addAttribute("personOutput", user);
            poi.setEmail(null);
        }
        
        return "admin/results";

    }

    /*
    @GetMapping("/update")
    public String updateStudentPage(Model model) {

        return "update";

    }

    @PostMapping("/update")
    public String getUpdatedStudentPage(Model model, @RequestParam("emailInput") String email) {

        if (email.equals("all") || email.equals("All") || email.equals("ALL")) {
            List<Volunteer> users = clerk.getAllUsers();
            model.addAttribute("personOutput", users);
            model.addAttribute("message", "Users below are deleted");
            clerk.deleteAllUsers();
        } else {
            Volunteer user = clerk.findByUserEmail(email);
            if (user != null) {
                model.addAttribute("personOutput", user);
                model.addAttribute("message", "User below is deleted");
                clerk.deleteByUserEmail(email); 
            } else {
                model.addAttribute("message", "No such user");
            }
        }

        return "message";

    }

    */

}