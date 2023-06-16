package com.javarush.model.repository;

import com.javarush.domian.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Task, Integer> {
}
