package Service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationServiceTest
{

    @Test
    void validateEmail()
    {
        assertTrue(ValidationService.validateEmail("123@163.com"));
        assertTrue(ValidationService.validateEmail("abc@xyz.cn"));
        assertTrue(ValidationService.validateEmail("1_2@12.cc"));
        assertTrue(ValidationService.validateEmail("1-23@163.com"));
        assertTrue(ValidationService.validateEmail("123@1-63.com"));
        assertTrue(ValidationService.validateEmail("123@1_63.com"));

        assertFalse(ValidationService.validateEmail("1@32.cc"));
        assertFalse(ValidationService.validateEmail("12@3.cc"));
        assertFalse(ValidationService.validateEmail("12@33.c"));
        assertFalse(ValidationService.validateEmail("13"));
        assertFalse(ValidationService.validateEmail("%%#$"));
    }

    @Test
    void validateUserName()
    {
        assertFalse(ValidationService.validateUserName("12"));
        assertFalse(ValidationService.validateUserName("1234567890123456789012345678901"));

        assertTrue(ValidationService.validateUserName("123"));
        assertTrue(ValidationService.validateUserName("Admin"));
        assertTrue(ValidationService.validateUserName("冻葱Dictionary"));
    }

    @Test
    void validatePhone()
    {
        assertTrue(ValidationService.validatePhone("13912312345"));

        assertFalse(ValidationService.validatePhone("12345"));
        assertFalse(ValidationService.validatePhone("23912312345"));
    }
}
