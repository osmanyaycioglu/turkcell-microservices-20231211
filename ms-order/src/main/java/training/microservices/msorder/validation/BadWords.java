package training.microservices.msorder.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
@Constraint(validatedBy = { BadWordsImpl.class})
public @interface BadWords {
    public String[] value();

    String message() default "{jakarta.validation.constraints.BadWords.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
