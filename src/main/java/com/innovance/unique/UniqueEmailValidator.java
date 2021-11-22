package com.innovance.unique;

import com.innovance.entity.User;
import com.innovance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(final String email,
                           final ConstraintValidatorContext constraintValidatorContext) {
        User user = userRepository.findByEmail(email);
        return user == null;
    }
}
