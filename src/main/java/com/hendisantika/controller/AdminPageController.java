package com.hendisantika.controller;

import com.hendisantika.Service.DisponibiliteService;
import com.hendisantika.Service.EmployerService;
import com.hendisantika.Service.FonctionService;
import com.hendisantika.entity.Disponibilite;
import com.hendisantika.entity.Employer;
import com.hendisantika.entity.Fonction;
import com.hendisantika.entity.User;
import com.hendisantika.repository.EmployerRepo;
import com.hendisantika.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-adminlte3
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 25/11/20
 * Time: 08.41
 */
@Controller
public class AdminPageController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployerService service;
    @Autowired
    private FonctionService fonctionservice;
    @Autowired
    private DisponibiliteService disponibiliteService;
    @Autowired
    private EmployerRepo eRepo;

    @GetMapping({"/user/list", "/admin/user"})
    public String listUser() {
        return "user-list";
    }

    @GetMapping("/user/listemployes")
    public String listUser2(Model model) {
        List<Employer> listemployers = service.listAll();
        model.addAttribute("listemployers", listemployers);
        model.addAttribute("users", userRepository.findAll());
        return "user-list2";
    }

    @GetMapping("/user/add")
    public String showFormUser(Model model) {

        List<Fonction> listfonction = fonctionservice.listAll();
        model.addAttribute("listfonction", listfonction);
        List<Disponibilite> listdisponibilite = disponibiliteService.listAll();
        model.addAttribute("listdisponibilite", listdisponibilite);
        model.addAttribute("employer", new Employer());
        model.addAttribute("fonction", new Fonction());
        model.addAttribute("disponibilite", new Disponibilite());

        return "user-add";
    }
    @RequestMapping(value = "/saved", method = RequestMethod.POST)
    public String saveEmp(@ModelAttribute("employer") Employer emp, RedirectAttributes redirAttrs) {
        redirAttrs.addFlashAttribute("message", "Employe bien ajouter.");
        service.save(emp);

        return "redirect:/user/listemployes";
    }
    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam Long employeeId, RedirectAttributes redirAttrs) {
        redirAttrs.addFlashAttribute("empsupprimer", "Employe bien supprimer.");
        eRepo.deleteById(employeeId);
        return "redirect:/user/listemployes";
    }
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditemployer(@PathVariable(name = "id") Long id, Model model) {
        ModelAndView mav = new ModelAndView("/user-add");
        Optional<Employer> emp = service.get(id);
        mav.addObject("employer", emp);
        List<Disponibilite> listdisponibilite = disponibiliteService.listAll();
        model.addAttribute("listdisponibilite", listdisponibilite);
        List<Fonction> listfonction = fonctionservice.listAll();
        model.addAttribute("listfonction", listfonction);
        return mav;

    }


}
