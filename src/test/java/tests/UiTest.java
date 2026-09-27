package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;
import pages.MainPage;
import pages.PasswordRecoveryPage;
import pages.RegisterPage;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class UiTest {

    private static final String BASE = "https://stellarburgers.education-services.ru/";

    @Parameterized.Parameter
    public String browser;

    @Parameterized.Parameters(name = "browser={0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"chrome"},
                {"yandex"}
        });
    }

    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private PasswordRecoveryPage recoveryPage;

    @Before
    public void setUp() {
        if (browser.equals("yandex")) {
            System.setProperty("webdriver.chrome.driver",
                    "/Users/dilnoza.vosidi/IdeaProjects/Diplom_3/yandexdriver");
            ChromeOptions options = new ChromeOptions();
            options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
            driver = new ChromeDriver(options);
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.get(BASE);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        recoveryPage = new PasswordRecoveryPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    @Description("Успешная регистрация")
    public void testSuccessfulRegistration() {
        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();
        registerPage.register("Tester",
                "reg" + System.currentTimeMillis() + "@yandex.ru",
                "pass123");

        // Ждём, чтобы страница успела обновиться
        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        String url = driver.getCurrentUrl();
        System.out.println("URL после регистрации: " + url);

        // Пробуем прочитать сообщение об ошибке, если оно есть
        try {
            String error = registerPage.getErrorMessage();
            System.out.println("Сообщение об ошибке на /register: " + error);
        } catch (Exception e) {
            System.out.println("Сообщения об ошибке нет");
        }

        assertTrue("Регистрация не удалась, URL: " + url,
                url.contains("login") || url.endsWith("/"));
    }

    @Test
    @Description("Ошибка при пароле короче 6 символов")
    public void testShortPasswordError() {
        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();
        registerPage.register("Tester",
                "short" + System.currentTimeMillis() + "@yandex.ru",
                "123");

        String error = registerPage.getErrorMessage();
        assertTrue("Сообщение об ошибке не должно быть пустым", error.length() > 0);
    }

    @Test
    @Description("Вход через кнопку «Войти в аккаунт» на главной")
    public void testLoginFromMain() {
        mainPage.clickLoginButton();
        assertTrue(driver.getCurrentUrl().contains("login"));
    }

    @Test
    @Description("Вход через кнопку «Личный кабинет»")
    public void testLoginFromAccount() {
        mainPage.clickAccountButton();
        assertTrue(driver.getCurrentUrl().contains("login"));
    }

    @Test
    @Description("Вход через кнопку в форме регистрации")
    public void testLoginFromRegisterForm() {
        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();
        registerPage.clickLoginLink();
        assertTrue(driver.getCurrentUrl().contains("login"));
    }

    @Test
    @Description("Вход через кнопку в форме восстановления пароля")
    public void testLoginFromRecoveryForm() {
        mainPage.clickLoginButton();
        loginPage.clickRecoverLink();
        recoveryPage.clickLoginLink();
        assertTrue(driver.getCurrentUrl().contains("login"));
    }

    @Test
    @Description("Переход к разделу «Булки»")
    public void testBunsTab() {
        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();
        assertTrue(mainPage.isTitleDisplayed());
    }

    @Test
    @Description("Переход к разделу «Соусы»")
    public void testSaucesTab() {
        mainPage.clickSaucesTab();
        assertTrue(mainPage.isTitleDisplayed());
    }

    @Test
    @Description("Переход к разделу «Начинки»")
    public void testFillingsTab() {
        mainPage.clickFillingsTab();
        assertTrue(mainPage.isTitleDisplayed());
    }
}