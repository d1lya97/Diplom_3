package tests;

import io.qameta.allure.Description;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class LoginTest extends BaseTest {

    @Test
    @Description("Вход через кнопку «Войти в аккаунт» на главной")
    public void testLoginFromMain() {
        mainPage.clickLoginButton();
        loginPage.login(user.getEmail(), user.getPassword());
        assertFalse("Логин не прошёл, остались на /login",
                driver.getCurrentUrl().contains("login"));
    }

    @Test
    @Description("Вход через кнопку «Личный кабинет»")
    public void testLoginFromAccount() {
        mainPage.clickAccountButton();
        loginPage.login(user.getEmail(), user.getPassword());
        assertFalse("Логин не прошёл, остались на /login",
                driver.getCurrentUrl().contains("login"));
    }

    @Test
    @Description("Вход через кнопку в форме регистрации")
    public void testLoginFromRegisterForm() {
        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();
        registerPage.clickLoginLink();
        loginPage.login(user.getEmail(), user.getPassword());
        assertFalse("Логин не прошёл, остались на /login",
                driver.getCurrentUrl().contains("login"));
    }

    @Test
    @Description("Вход через кнопку в форме восстановления пароля")
    public void testLoginFromRecoveryForm() {
        mainPage.clickLoginButton();
        loginPage.clickRecoverLink();
        recoveryPage.clickLoginLink();
        loginPage.login(user.getEmail(), user.getPassword());
        assertFalse("Логин не прошёл, остались на /login",
                driver.getCurrentUrl().contains("login"));
    }
}