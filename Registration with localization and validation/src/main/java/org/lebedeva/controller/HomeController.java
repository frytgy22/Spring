package org.lebedeva.controller;

import lombok.extern.slf4j.Slf4j;
import org.lebedeva.dto.DtoUser;
import org.lebedeva.model.Gender;
import org.lebedeva.model.User;
import org.lebedeva.repository.Repository;
import org.lebedeva.validate.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class HomeController {

    private final Repository<User, Integer> repository;
    private final UserValidator userValidator;

    @Autowired
    public HomeController(Repository<User, Integer> repository, UserValidator userValidator) {
        this.repository = repository;
        this.userValidator = userValidator;
    }

    @GetMapping
    public String registration(Model model) {
        log.info("INFO registration()");
        model.addAttribute("dtoUser", new DtoUser());
        return "registration";
    }

    @PostMapping
    public String registration(@ModelAttribute("dtoUser") @Validated DtoUser dtoUser, BindingResult result) {
        userValidator.validate(dtoUser, result);
        if (result.hasErrors()) {
            return "registration";
        }
        try {
            log.info("INFO registration(), dtoUser: {}", dtoUser);

            int id = repository.save(User.builder()
                    .login(dtoUser.getLogin())
                    .password(dtoUser.getPassword())
                    .email(dtoUser.getEmail())
                    .phone(dtoUser.getPhone())
                    .gender(Gender.valueOf(dtoUser.getGender()))
                    .build());
            return "redirect:/info/" + id;

        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
            return "registration";
        }
    }

    @GetMapping("info/{id}")
    public String info(@PathVariable int id, Model model) {
        try {
            log.info("INFO info()");

            if (repository.findById(id).isPresent()) {
                model.addAttribute("user", repository.findById(id).get());
            }
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
        }
        return "index";
    }
}
