package com.example.sweate.repository;

import com.example.sweate.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Integer> {
    //метод для поиска в БД по тэгу
   List<Message> findByTag(String tag);
}
