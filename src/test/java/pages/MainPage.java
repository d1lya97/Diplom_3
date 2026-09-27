package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriverWait wait;

    @FindBy(xpath = "//button[text()='Войти в аккаунт']")
    private WebElement loginButton;

    @FindBy(xpath = "//p[text()='Личный Кабинет']/parent::a")
    private WebElement accountButton;

    @FindBy(xpath = "//span[text()='Булки']/parent::div")
    private WebElement bunsTab;

    @FindBy(xpath = "//span[text()='Соусы']/parent::div")
    private WebElement saucesTab;

    @FindBy(xpath = "//span[text()='Начинки']/parent::div")
    private WebElement fillingsTab;

    @FindBy(xpath = "//h1[text()='Соберите бургер']")
    private WebElement title;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    public void clickAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(accountButton));
        accountButton.click();
    }

    public void clickBunsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsTab));
        bunsTab.click();
    }

    public void clickSaucesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesTab));
        saucesTab.click();
    }

    public void clickFillingsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsTab));
        fillingsTab.click();
    }

    public boolean isTitleDisplayed() {
        return title.isDisplayed();
    }
}