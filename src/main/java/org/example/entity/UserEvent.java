package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.telegram.telegrambots.meta.api.objects.Update;
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class UserEvent {
    private Update update;
    private String userName;
    private String text;

}

