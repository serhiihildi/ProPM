package com.hildi.propm.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserTest {

    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    private static final String EMAIL = "john.doe@example.com";
    private static final String PASSWORD = "password123";

    @InjectMocks
    private User user;

    @Mock
    private Role role;

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("Test constructor")
    void testConstructor() {
        User newUser = new User(1L, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, null);
        assertThat(newUser.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(newUser.getLastName()).isEqualTo(LAST_NAME);
        assertThat(newUser.getEmail()).isEqualTo(EMAIL);
        assertThat(newUser.getPassword()).isEqualTo(PASSWORD);
    }

    @Test
    @DisplayName("Test validation with valid data")
    void testValidationWithValidData() {
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("Test validation with invalid email")
    void testValidationWithInvalidEmail() {
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setEmail("invalid-email");
        user.setPassword(PASSWORD);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat(violations.size()).isEqualTo(1);
    }

}
