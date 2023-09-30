package org.example.cache;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.example.entity.UserEvent;

import java.util.ArrayDeque;
import java.util.Queue;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EventCache {
    private static EventCache instance;
    private Queue <UserEvent> cache = new ArrayDeque<>();

    public void addEvent(UserEvent userEvent){
        cache.add(userEvent);
    }
    public UserEvent getLastEvent(){
        return cache.poll();
    }

    public static EventCache getInstance() {
        if (instance == null){
            instance = new EventCache();
        }
        return instance;
    }
}
