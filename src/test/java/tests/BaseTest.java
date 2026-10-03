package tests;

import api.UserApiClient;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.Response;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;
import pages.MainPage;
import pages.PasswordRecoveryPage;
import pages.RegisterPage;

import java.time.Duration;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class BaseTest {

    protected static final String BASE = "https://stellarburgers.education-services.ru/";

    protected WebDriver driver;
    protected MainPage mainPage;
    protected LoginPage loginPage;
    protected RegisterPage registerPage;
    protected PasswordRecoveryPage recoveryPage;

    protected UserApiClient userApiClient;
    protected User user;
    protected String accessToken;

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");

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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(BASE);

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        recoveryPage = new PasswordRecoveryPage(driver);

        userApiClient = new UserApiClient();
        user = new User(
                "test" + System.currentTimeMillis() + "@yandex.ru",
                "Password123!",
                "Tester"
        );

        Response regResponse = userApiClient.register(user);
        regResponse.then()
                .statusCode(200)
                .body("success", is(true))
                .body("accessToken", notNullValue());

        accessToken = regResponse.then().extract().path("accessToken");
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userApiClient.delete(accessToken);
        }
        if (driver != null) {
            driver.quit();
        }
    }
}