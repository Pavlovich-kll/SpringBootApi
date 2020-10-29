package com.example.sweate;

import com.example.sweate.domain.Message;
import com.example.sweate.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class GreetingController {

    @Autowired
    private MessageRepo messageRepo;


    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Map<String, Object> model
    ) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();

        model.put("messages", messages);
        return "main";
    }

    // @RequestParam выдергивает GET запрос из формочки, где кнопка "добавить" (если передаем постом),
    // либо из URL, если передаем значение в запросе в строке 22)
    @PostMapping
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        //сохраняем значения
        Message message = new Message(text, tag);
        messageRepo.save(message);
        //берем из репозитория, кладем в модель и отдаем пользователю
        Iterable<Message> messages = messageRepo.findAll();

        model.put("messages", messages);
        return "main";
    }

    //маппинг, по которому будут уходить данные
    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        // Iterable потоу что он общий знаменатель для возвращаемых значений методов findByTag() и findAll()
        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }
        model.put("messages", messages);
        return "main";
    }
}