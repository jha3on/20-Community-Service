package app.community.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChatMessageType {
    JOIN("JOIN"),
    TALK("TALK"),
    QUIT("QUIT");

    private final String value;

    public static ChatMessageType of(String value) {
        for (ChatMessageType type : values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }

        return null;
    }
}