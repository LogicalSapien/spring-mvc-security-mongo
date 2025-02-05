package com.logicalsapien.controller;

import com.logicalsapien.entity.Galaxy;
import com.logicalsapien.service.GalaxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class GalaxyController {

    @Autowired
    private GalaxyService galaxyService;

    @GetMapping("/list-galaxies")
    public String showGalaxies(ModelMap model) {
        model.addAttribute("galaxies", galaxyService.getAllGalaxies());
        model.addAttribute("content", "list-galaxies");
        return "index";
    }

    @GetMapping("/add-galaxy")
    public String showSignUpForm(Galaxy galaxy, ModelMap model) {
        model.addAttribute("content", "add-galaxy");
        return "index";
    }

    @PostMapping("/add-galaxy")
    public String addGalaxy(@Valid Galaxy galaxy, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("content", "add-galaxy");
            return "index";
        }

        galaxyService.saveGalaxy(galaxy);
        return "redirect:/list-galaxies";
    }

    @GetMapping("/update-galaxy/{id}")
    public String showUpdateForm(@PathVariable("id") String id, ModelMap model) {
        Galaxy galaxy = galaxyService.getById(id);

        model.addAttribute("galaxy", galaxy);
        model.addAttribute("content", "update-galaxy");
        return "index";
    }

    @PostMapping("/update-galaxy/{id}")
    public String updateGalaxy(@PathVariable("id") String id, @Valid Galaxy galaxy,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            galaxy.setId(id);
            model.addAttribute("content", "update-galaxy");
            return "index";
        }

        galaxyService.saveGalaxy(galaxy);
        return "redirect:/list-galaxies";
    }

    @GetMapping("/delete-galaxy/{id}")
    public String deleteGalaxy(@PathVariable("id") String id, Model model) {
        galaxyService.deleteGalaxy(id);
        return "redirect:/list-galaxies";
    }
}