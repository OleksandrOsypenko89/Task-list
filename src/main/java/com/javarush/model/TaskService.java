package com.javarush.model;

import com.javarush.domian.Task;
import com.javarush.model.repository.TodoRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor
public class TaskService {
    private TodoRepository todoListRepository;

    private Task editTask;

    @Autowired
    public TaskService(TodoRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }
    public void deleteTask(Task task) {
        todoListRepository.delete(task);
    }

    public void updateTask(Task task) {
        todoListRepository.save(task);
    }
}
