package org.lebedeva.controller;

import org.lebedeva.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class HomeController {
    public static final String URL = "https://reqres.in/api/users?per_page=100";
    private final RestTemplate restTemplate;

    @Autowired
    public HomeController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String index(Model model) {
        Page page = restTemplate.getForObject(URL, Page.class);

        if (page != null && page.getUsers() != null) {
            model.addAttribute("users", page.getUsers());
        }
        return "index";
    }
}
