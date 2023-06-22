package com.javarush.controller;

import com.javarush.domian.Status;
import com.javarush.domian.Task;
import com.javarush.model.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("")
public class IndexController {
    private final TaskService taskService;
    private int pageNumber;
    private int sizePages;
    public IndexController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String index(Model model, Task task, @PageableDefault Pageable pageable) {
        Page<Task> taskPage = taskService.getTodoListRepository().findAll(pageable);

        model.addAttribute("allTasks", taskPage.getContent());
        model.addAttribute("page", taskPage);

        model.addAttribute("allStatuses", Status.values());
        model.addAttribute("newTask", task);

        if (taskService.getEditTask() != null) {
            model.addAttribute("editTaskId", taskService.getEditTask().getId());
            model.addAttribute("editTask", taskService.getEditTask());
        }

        model.addAttribute("pageNumber", pageNumber);
        pageNumber = taskPage.getNumber();
        sizePages = taskPage.getTotalPages() - 1;

        return "index";
    }

    @PostMapping("/create")
    public String create(Task task) {
        taskService.updateTask(task);
        return "redirect:/?page=" + sizePages;
    }

    @PostMapping("/save")
    public String save(Task task) {
        taskService.updateTask(task);
        taskService.setEditTask(null);
        return "redirect:/?page=" + pageNumber;
    }

    @PostMapping("/delete")
    public String delete(Task task) {
        taskService.deleteTask(task);
        return "redirect:/?page=" + pageNumber;
    }

    @PostMapping("/edit")
    public String edit(Task task) {
        taskService.setEditTask(task);
        return "redirect:/?page=" + pageNumber;
    }
}
