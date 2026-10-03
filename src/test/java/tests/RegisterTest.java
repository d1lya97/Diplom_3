package tests;

import io.qameta.allure.Description;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RegisterTest extends BaseTest {

    @Test
    @Description("Успешная регистрация нового пользователя")
    public void testSuccessfulRegistration() {
        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();
        registerPage.registerSuccess(
                "NewTester",
                "new" + System.currentTimeMillis() + "@yandex.ru",
                "Password123!"
        );
        assertTrue("Регистрация не прошла, остались на /register",
                registerPage.isRegistrationSuccessful());
    }

    @Test
    @Description("Ошибка при пароле короче 6 символов")
    public void testShortPasswordError() {
        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();
        registerPage.register(
                "NewTester",
                "short" + System.currentTimeMillis() + "@yandex.ru",
                "123"
        );
        String error = registerPage.getErrorMessage();
        assertTrue("Ожидалась ошибка про пароль, получили: " + error,
                error.toLowerCase().contains("пароль"));
    }
}