package app.community.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PostType {
    NOTICE("NOTICE"),
    EVENT("EVENT"),
    TALK("TALK");

    private final String value;

    public static PostType of(String value) {
        for (PostType type : values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }

        return null;
    }
}