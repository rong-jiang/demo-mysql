package cn.mark.demomysql.model;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelField {
    String value() default "";

    String title();

    int type() default 0;

    int align() default 0;

    int sort() default 0;

    String dictType() default "";

    Class<?> fieldType() default Class.class;

    int[] groups() default {};
}

