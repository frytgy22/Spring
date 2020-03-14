package org.lebedeva.validate;

import org.lebedeva.dto.DtoUser;
import org.lebedeva.model.User;
import org.lebedeva.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private final Repository<User, Integer> repository;

    @Autowired
    public UserValidator(Repository<User, Integer> repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == DtoUser.class;
    }

    @Override
    public void validate(Object o, Errors errors) {
        DtoUser user = (DtoUser) o;

        if (repository.userExists(user.getEmail())) {
            errors.rejectValue("email", "user.email.duplicate");
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "user.confirmPassword");
        }
    }
}
