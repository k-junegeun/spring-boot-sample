package me.june.springbootsample.event;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class EventController {

    private final Map<Long, Event> events = new ConcurrentHashMap<>();

    private final AtomicLong idStore = new AtomicLong();

    @PostMapping("/events")
    public String createEvent(@ModelAttribute Event event) {
        events.put(idStore.getAndIncrement(), event);
        return "success";
    }

    @GetMapping("/events")
    public List<Event> getEvents() {
        return events.values().stream().toList();
    }
}
