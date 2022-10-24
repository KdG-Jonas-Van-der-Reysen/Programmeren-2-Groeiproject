package be.kdg.reflection;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface CanRun
{
    String value() default "dummy";
}

