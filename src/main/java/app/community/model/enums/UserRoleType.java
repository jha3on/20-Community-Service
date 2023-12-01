package app.community.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@AllArgsConstructor
public enum UserRoleType implements GrantedAuthority {
    ROLE_USER("ROLE_USER"), ROLE_SYSOP("ROLE_SYSOP");

    private final String value;

    public static UserRoleType of(String value) {
        for (UserRoleType type : values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }

        return null;
    }

    @Override
    public String getAuthority() {
        return this.value;
    }
}