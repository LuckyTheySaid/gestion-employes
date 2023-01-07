package com.hendisantika.controller;

import com.hendisantika.Service.DisponibiliteService;
import com.hendisantika.Service.EmployerService;
import com.hendisantika.Service.FonctionService;
import com.hendisantika.entity.Employer;
import com.hendisantika.entity.Fonction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private FonctionService service;
    @Autowired
    private EmployerService empservice;
    @Autowired
    private DisponibiliteService servicee;

    @GetMapping("/")
    public String index(Model model) {


        model.addAttribute("getdisponibleemp",empservice.demp());
        model.addAttribute("getindisponibleemp",empservice.indemp());
        model.addAttribute("getfonctioncount",service.fonctioncout());
        List<Employer> listemployers = empservice.listAll();
        model.addAttribute("listemployers", listemployers);
        System.out.println(empservice.demp());
        System.out.println(empservice.indemp());

        return "dashboard";
    }
}
