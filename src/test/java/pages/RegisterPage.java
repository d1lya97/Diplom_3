package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends BasePage {

    @FindBy(xpath = "//input[@name='name']")
    private WebElement nameField;

    @FindBy(xpath = "//label[text()='Email']/following-sibling::input")
    private WebElement emailField;

    @FindBy(xpath = "//input[@name='Пароль']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[text()='Зарегистрироваться']")
    private WebElement submitButton;

    @FindBy(xpath = "//p[contains(@class,'input__error')]")
    private WebElement errorMessage;

    @FindBy(xpath = "//a[text()='Войти']")
    private WebElement loginLink;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Заполнить форму регистрации: имя {name}, email {email}")
    public void register(String name, String email, String password) {
        wait.until(ExpectedConditions.visibilityOf(nameField)).sendKeys(name);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    @Step("Получить текст сообщения об ошибке")
    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText();
    }

    @Step("Клик по ссылке «Войти»")
    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }
}