package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriverWait wait;

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
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
    }

    public void clickRegisterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink));
        registerLink.click();
    }

    public void clickRecoverLink() {
        wait.until(ExpectedConditions.elementToBeClickable(recoverLink));
        recoverLink.click();
    }
}