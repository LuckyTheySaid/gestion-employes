package com.hendisantika.controller;

import com.hendisantika.Service.FonctionService;
import com.hendisantika.entity.Fonction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class FonctionController {


    @Autowired
    private FonctionService fonctionservice;


    @GetMapping("/test")
    public String test(Model model){
        model.addAttribute("fonction", new Fonction());
        return "test";
    }


    @GetMapping("/user/addfonction")
    public String ajouterfonction(Model model){
        model.addAttribute("fonction", new Fonction());
        return "user-addfonction";
    }
    @RequestMapping(value = "/ajouterfonction", method = RequestMethod.POST)
    public String savefct(@ModelAttribute("fonction") Fonction fon, RedirectAttributes redirAttrs) {
        redirAttrs.addFlashAttribute("fonajouter", "Fonction bien ajouter.");
        fonctionservice.save(fon);

        return "redirect:/user-addfonction";
    }
}
