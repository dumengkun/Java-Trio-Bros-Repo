package com.trio.project;

import org.springframework.ui.Model;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TrioArtist {

    public String renderSignupPage(Model model) {

        model.addAttribute("newUser", new Volunteer());
        return "sign-up";
        
    }

    public String renderPwdPage(Model model) {

        model.addAttribute("message", "Your password length is less than 6");
        return "message";

    }

    public String renderEmailPage(Model model, String email) {

        model.addAttribute("message", "The email address " + email + " is registered");
        return "message";

    }

    public String renderSigninPage(Model model) {

        model.addAttribute("guest", new Guest());
        return "sign-in";
        
    }

    public String renderIdentificationPage(Model model) {

        model.addAttribute("message", "Please check that you use the right email address and password");
        return "message";

    }

    public String renderDeletePage(Model model, String email) {

        model.addAttribute("message", "Account " + email + " is deleted");
        return "message";

    }
    
}
