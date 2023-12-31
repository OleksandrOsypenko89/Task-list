package com.javarush.controller;

import com.javarush.domian.Status;
import com.javarush.domian.Task;
import com.javarush.model.TaskService;
import jakarta.servlet.http.HttpSession;
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
    private final HttpSession session;

    public IndexController(TaskService taskService, HttpSession session) {
        this.taskService = taskService;
        this.session = session;
    }

    @GetMapping
    public String index(Model model, Task task, @PageableDefault Pageable pageable) {
        Page<Task> taskPage = taskService.getTodoListRepository().findAll(pageable);

        model.addAttribute("allTasks", taskPage.getContent());
        model.addAttribute("page", taskPage);

        model.addAttribute("allStatuses", Status.values());
        model.addAttribute("newTask", task);

        if (session.getAttribute("editTask") != null) {
            Task editTask = (Task) session.getAttribute("editTask");
            model.addAttribute("editTaskId", editTask.getId());
            model.addAttribute("editTask", editTask);
        }

        session.setAttribute("pageNumber", taskPage.getNumber());
        session.setAttribute("sizePages", taskPage.getTotalPages() - 1);

        return "index";
    }

    @PostMapping("/create")
    public String create(Task task) {
        taskService.updateTask(task);
        return "redirect:/?page=" + session.getAttribute("sizePages");
    }

    @PostMapping("/save")
    public String save(Task task) {
        taskService.updateTask(task);
        session.removeAttribute("editTask");
        return "redirect:/?page=" + session.getAttribute("pageNumber");
    }

    @PostMapping("/delete")
    public String delete(Task task) {
        taskService.deleteTask(task);
        return "redirect:/?page=" + session.getAttribute("pageNumber");
    }

    @PostMapping("/edit")
    public String edit(Task task) {
        session.setAttribute("editTask", task);
        return "redirect:/?page=" + session.getAttribute("pageNumber");
    }
}
