package com.laptrinhjavaweb.annotation;

import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Table {
	 String name() default "";
}
