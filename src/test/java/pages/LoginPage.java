package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@name='name']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@name='Пароль']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[text()='Войти']")
    private WebElement submitButton;

    @FindBy(xpath = "//a[text()='Зарегистрироваться']")
    private WebElement registerLink;

    @FindBy(xpath = "//a[text()='Восстановить пароль']")
    private WebElement recoverLink;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввести email {email}")
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.clear();
        emailField.sendKeys(email);
    }

    @Step("Ввести пароль")
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    @Step("Нажать кнопку «Войти»")
    public void clickSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
    }

    @Step("Залогиниться под email {email}")
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickSubmit();
        wait.until(ExpectedConditions.not(
                ExpectedConditions.urlContains("/login")));
    }

    @Step("Клик по ссылке «Зарегистрироваться»")
    public void clickRegisterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }

    @Step("Клик по ссылке «Восстановить пароль»")
    public void clickRecoverLink() {
        wait.until(ExpectedConditions.elementToBeClickable(recoverLink)).click();
    }
}