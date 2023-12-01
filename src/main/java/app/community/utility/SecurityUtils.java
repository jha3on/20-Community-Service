package app.community.utility;

import app.community.model.User;
import app.community.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Optional;

public class SecurityUtils {
    public static CustomUserDetails getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.ofNullable(principal)
                .filter(obj -> obj instanceof CustomUserDetails)
                .map(CustomUserDetails.class::cast)
                .orElseGet(CustomUserDetails::new);
    }

    public static User convertUserDetailsToUser(CustomUserDetails userDetails) {
        return User.builder()
                .id(userDetails.getId())
                .email(userDetails.getEmail())
                .name(userDetails.getName())
                .phone(userDetails.getPhone())
                .nickname(userDetails.getNickname())
                .userRoleType(userDetails.getUserRoleType())
                .userStatus(userDetails.getUserStatus())
                .createdAt(userDetails.getCreatedAt())
                .updatedAt(userDetails.getUpdatedAt())
                .build();
    }
}