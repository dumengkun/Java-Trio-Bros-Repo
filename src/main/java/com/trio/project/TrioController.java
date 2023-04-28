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

    @Autowired
    private TrioArtist artist;

    @Autowired
    private TrioRunner runner;

    @GetMapping("/")
    public String homePage(Model model) {

        return "index";
        
    }

    @GetMapping("/ifelse")
    public String ifelsePage()
    {
        return "ifelse";
    }

    @GetMapping("/boolean")
    public String booleanPage()
    {
        return "boolean";
    }

    @GetMapping("/forloop")
    public String forloopPage()
    {
        return "forloop";
    }

    @GetMapping("/array")
    public String arrayPage()
    {
        return "array";
    }

    @GetMapping("/runner")
    public String runnerPage()
    {
        return "runner";
    }

    @PostMapping("/runner")
    public String runnerPostPage(@RequestParam("code") String code, Model model)
    {
        String output;

        try 
        {
            output = runner.compileAndRunJavaCode(code);
        } 
        catch (Exception e) 
        {
            output = "Error: " + e.getMessage();
        }

        model.addAttribute("output", output);
        model.addAttribute("submittedCode", code);
        return "runner";
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
                model.addAttribute("myUser", poi);
                return "user-page";

            } catch (Exception e) {

                return artist.renderEmailPage(model, poi.getEmail());

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
        
        if (user != null && user.pwdChecker(guest.getPassword())) {

            model.addAttribute("myUser", user);
            return "user-page";

        } else {

           return artist.renderIdentificationPage(model);
            
        }

    }

    @PostMapping("/inote")
    public String userNotePostPage(Model model, @ModelAttribute("myUser") Volunteer poi) {

        Volunteer user = clerk.findByUserEmail(poi.getEmail());
        clerk.deleteByUserEmail(poi.getEmail());
        user.setNote(poi.getNote());
        clerk.setUser(user);

        model.addAttribute("myUser", user);
        return "user-page";

    }

    @PostMapping("/irunner")
    public String userRunnerPostPage(Model model, @ModelAttribute("myUser") Volunteer poi, @RequestParam("code") String code) {

        String output;

        try 
        {
            output = runner.compileAndRunJavaCode(code);
        } 
        catch (Exception e) 
        {
            output = "Error: " + e.getMessage();
        }

        model.addAttribute("myUser", poi);
        model.addAttribute("code", code);
        model.addAttribute("output", output);
        return "user-page";

    }

    @GetMapping("/update")
    public String updatePage(Model model, @ModelAttribute("myUser") Guest guest) {

        model.addAttribute("guest", guest);
        return "user-update";

    }

    @PostMapping("/update")
    public String updatePostPage(Model model, @ModelAttribute("guest") Guest guest) {

        Volunteer user = clerk.findByUserEmail(guest.getEmail());

        if (user != null && user.pwdChecker(guest.getPassword())) {

            user.setName(guest.getName());
    
            if (!guest.getNewEmail().isEmpty()) {

                Volunteer otherUser = clerk.findByUserEmail(guest.getNewEmail());

                if (otherUser != null) {

                    return artist.renderEmailPage(model, guest.getNewEmail());
                }

                user.setEmail(guest.getNewEmail());

            }

            if (!guest.getNewPassword().isEmpty()) {

                if (guest.pwdFormAlert()) {

                    return artist.renderPwdPage(model);

                }

                user.setPassword(guest.getNewPassword());
    
            }

            clerk.deleteByUserEmail(guest.getEmail());
            clerk.setUser(user);
            model.addAttribute("myUser", user);
            return "user-page";

        } else {

            return artist.renderIdentificationPage(model);
            
        }

    }

    @PostMapping("/delete")
    public String deletePostPage(Model model, @ModelAttribute("guest") Guest guest) {

        Volunteer user = clerk.findByUserEmail(guest.getEmail());

        if (user != null && user.pwdChecker(guest.getPassword())) {

            clerk.deleteByUserEmail(guest.getEmail());
            return artist.renderDeletePage(model, guest.getEmail());

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

}