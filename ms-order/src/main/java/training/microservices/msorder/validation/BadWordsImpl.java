package training.microservices.msorder.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BadWordsImpl implements ConstraintValidator<BadWords,String> {

    private BadWords anno;

    @Override
    public void initialize(final BadWords anno) {
        this.anno = anno;
    }

    @Override
    public boolean isValid(final String value,
                           final ConstraintValidatorContext context) {
        String[] valueLoc = anno.value();
        for (String stringLoc : valueLoc) {
            if (value.contains(stringLoc)){
                return false;
            }
        }
        return true;
    }
}
