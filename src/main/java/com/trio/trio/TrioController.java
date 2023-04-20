package com.trio.trio;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class TrioController
{
    private final TrioService service;

    public TrioController(TrioService service)
    {
        this.service = service;
    }

    @GetMapping("/")
    public String home()
    {
        return "index";
    }

    @GetMapping("/runner")
    public String showRunner()
    {
        return "runner";
    }

    @PostMapping("/runner")
    public String runCode(@RequestParam("code") String code, Model model)
    {
        String output;

        try 
        {
            output = service.execute(code);
        } 
        catch (Exception e) 
        {
            output = "Error: " + e.getMessage();
        }

        model.addAttribute("output", output);
        model.addAttribute("submittedCode", code);

        return "runner";
    }

    @GetMapping("/display")
    public String fetchAllPerson(Model model) 
    {
        List<Trio> persons = service.getAllResources();
        model.addAttribute("person", new Trio());
        model.addAttribute("people", persons);
        return "index";
    }

    @PostMapping("/createPerson")
    public String createPerson(@ModelAttribute Trio person) 
    {
        service.createPerson(person);
        return "redirect:/display";
    }

    @PostMapping("/deletePerson")
    public String deletePerson(@RequestParam("email") String email) 
    {
        service.deletePersonByEmail(email);
        return "redirect:/display";
    }

}