package app.community.utility;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Identification {
    enum Type { USER, POST, COMMENT, CHAT_ROOM }

    Type type() default Type.USER;
}