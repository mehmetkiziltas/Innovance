package com.innovance.unique;

import com.innovance.entity.User;
import com.innovance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
@Component
public class UniqueIdentityNumberValidator implements ConstraintValidator<UniqueIdentityNumber, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(final String identityNumber,
                           final ConstraintValidatorContext constraintValidatorContext) {
        User user = userRepository.findByIdentityNumber(identityNumber);
        return user == null;
    }
}
