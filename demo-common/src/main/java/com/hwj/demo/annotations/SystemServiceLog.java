package com.hwj.demo.annotations;

import java.lang.annotation.*;

@Target({ ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited

public @interface SystemServiceLog {
    String description() default "";
}
