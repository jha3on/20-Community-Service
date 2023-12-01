package app.community.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
    ENABLED("ENABLED"), DISABLED("DISABLED");

    private final String value;

    public static UserStatus of(String value) {
        for (UserStatus status : values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }

        return null;
    }
}