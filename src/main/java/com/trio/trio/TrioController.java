package com.trio.trio;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/ifelse")
    public String ifelse()
    {
        return "ifelse";
    }

    @GetMapping("/boolean")
    public String booleanPage()
    {
        return "boolean";
    }

    @GetMapping("/forloop")
    public String forloop()
    {
        return "forloop";
    }

    @GetMapping("/array")
    public String array()
    {
        return "array";
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
            output = service.compileAndRunJavaCode(code);
        } 
        catch (Exception e) 
        {
            output = "Error: " + e.getMessage();
        }

        model.addAttribute("output", output);
        model.addAttribute("submittedCode", code);

        return "runner";
    }
}