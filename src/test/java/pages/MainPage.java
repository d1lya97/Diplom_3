package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {

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
        super(driver);
    }

    @Step("Клик по кнопке «Войти в аккаунт» на главной")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Клик по кнопке «Личный кабинет»")
    public void clickAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(accountButton)).click();
    }

    @Step("Клик по разделу «Булки»")
    public void clickBunsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsTab)).click();
    }

    @Step("Клик по разделу «Соусы»")
    public void clickSaucesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesTab)).click();
    }

    @Step("Клик по разделу «Начинки»")
    public void clickFillingsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsTab)).click();
    }

    @Step("Проверить, что раздел {tabName} активен")
    public boolean isTabActive(String tabName) {
        // Проверяем класс на прямом родителе span
        By parentLocator = By.xpath("//span[text()='" + tabName + "']/parent::div");
        if (hasActiveClass(parentLocator)) {
            return true;
        }
        // Проверяем класс на родителе родителя
        By grandParentLocator = By.xpath("//span[text()='" + tabName + "']/parent::div/parent::div");
        return hasActiveClass(grandParentLocator);
    }

    private boolean hasActiveClass(By locator) {
        try {
            wait.until(ExpectedConditions.attributeContains(locator, "class", "tab_tab_type_current"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверить, что отображается заголовок «Соберите бургер»")
    public boolean isTitleDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(title)).isDisplayed();
    }
}