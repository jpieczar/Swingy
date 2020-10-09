package game.model;

import javax.validation.*;
import javax.validation.constraints.*;
import java.util.Set;

public class ValidateThis {
    public ValidateThis(String name, int num) {
        setName(name);
        setNum(num);
    }
    public String getName() {
        return name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int i) {
        this.num = i;
    }

    @NotBlank (message = "Name cannot be blank.")
    @NotNull (message = "Name cannot be Null.")
    @Size (min = 2, max = 10, message = "Name has to be between 1 and 10 characters long.")
    private String name; // Hero name.

    @NotNull (message = "Class cannot be Null.")
    @Min(value = 0, message = "Too low for a class number {0 - 2}")
    @Max(value = 2, message = "Too high for a class number. {0 - 2}")
    private int num; // Hero class.

    public void validateValidator() throws ValidationException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory(); // Validator object
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<ValidateThis>> constraintViolations = validator.validate(this);
        if (constraintViolations.size() != 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("There was a problem creating a hero ... ");
            stringBuilder.append(constraintViolations.size() + " Error(s)");
            stringBuilder.append("\n");
            for (ConstraintViolation<ValidateThis> conVio : constraintViolations) {
                stringBuilder.append("ERROR -> ");
                stringBuilder.append(conVio.getMessage());
                stringBuilder.append("\n");
            }
            throw new ValidationException(stringBuilder.toString());
        }
    }
}
