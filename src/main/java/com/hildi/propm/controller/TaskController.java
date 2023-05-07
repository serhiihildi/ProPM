package com.hildi.propm.controller;

import com.hildi.propm.model.Task;
import com.hildi.propm.model.dto.TaskDto;
import com.hildi.propm.services.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/projects/{projectId}/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public ModelAndView getAll() {
        log.info("Getting all tasks");
        ModelAndView modelAndView = new ModelAndView("tasksList");
        modelAndView.addObject("tasks", taskService.getAllTasks());
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getTask(@PathVariable Long id) {
        log.info("Getting task with id {}", id);
        ModelAndView modelAndView = new ModelAndView("taskDetails");
        modelAndView.addObject("task", taskService.getTaskById(id));
        return modelAndView;
    }

    @GetMapping("/new")
    public ModelAndView newTaskForm() {
        log.info("Getting form for new task");
        ModelAndView modelAndView = new ModelAndView("newTask");
        modelAndView.addObject("task", new Task());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView createTask(@Valid TaskDto task, BindingResult bindingResult) {
        log.info("Creating new task");
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("newTask");
        } else {
            taskService.createTask(task);
            modelAndView.setViewName("redirect:/tasks");
        }
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editTaskForm(@PathVariable Long id) {
        log.info("Getting form to edit task with id {}", id);
        ModelAndView modelAndView = new ModelAndView("editTask");
        modelAndView.addObject("task", taskService.getTaskById(id));
        return modelAndView;
    }

    @PostMapping("/{id}")
    public ModelAndView editTask(@PathVariable Long id, @Valid TaskDto task, BindingResult bindingResult) {
        log.info("Editing task with id {}", id);
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("editTask");
            modelAndView.addObject("task", task);
        } else {
            taskService.updateTask(id, task);
            modelAndView.setViewName("redirect:/projects/{projectId}/tasks/all");
        }
        return modelAndView;
    }

    @PostMapping("/{id}/delete")
    public ModelAndView deleteTask(@PathVariable Long id) {
        log.info("Deleting task with id {}", id);
        taskService.deleteTask(id);
        return new ModelAndView("redirect:/projects/{projectId}/tasks");
    }
}
