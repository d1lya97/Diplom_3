package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

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
        PageFactory.initElements(driver, this);
    }

    public void register(String name, String email, String password) {
        nameField.sendKeys(name);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        submitButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public void clickLoginLink() {
        loginLink.click();
    }
}
