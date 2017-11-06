package com.e_at.eatlib.injection;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface From {
    int value();

    String click() default "";

    String longClick() default "";

    String itemClick() default "";

    String itemLongClick() default "";

    Select select() default @Select(selected = "");
}
