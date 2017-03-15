package by.tasktracker.security;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Permission {
    Class<? extends PermissionChecker> value();
}
