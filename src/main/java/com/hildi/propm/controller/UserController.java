package com.hildi.propm.controller;

import com.hildi.propm.model.dto.UserDto;
import com.hildi.propm.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView("user/usersList");
        modelAndView.addObject("users", userService.getAllUsers());
        return modelAndView;
    }

    @GetMapping("/new")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("user/createUser");
        modelAndView.addObject("user", new UserDto());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView create(@ModelAttribute("user") @Valid UserDto userDto, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("user/createUser");
            modelAndView.addObject("user", userDto);
            return modelAndView;
        }

        userService.createUser(userDto);
        return new ModelAndView("redirect:/users/all");
    }

    @GetMapping("/{id}")
    public ModelAndView getById(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("user/userDetails");
        modelAndView.addObject("user", userService.getUserById(id));
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("user/editUser");
        modelAndView.addObject("user", userService.getUserById(id));
        return modelAndView;
    }

    @PostMapping("/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, @ModelAttribute("user") @Valid UserDto userDto, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("user/editUser");
            modelAndView.addObject("user", userDto);
            return modelAndView;
        }

        userService.updateUser(id, userDto);
        return new ModelAndView("redirect:/users/" + id);
    }

    @PostMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ModelAndView("redirect:/users/all");
    }
}
