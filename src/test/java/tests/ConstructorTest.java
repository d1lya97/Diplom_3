package tests;

import io.qameta.allure.Description;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest {

    @Test
    @Description("Переход к разделу «Булки»")
    public void testBunsTab() {
        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();
        assertTrue(mainPage.isTabActive("Булки"));
    }

    @Test
    @Description("Переход к разделу «Соусы»")
    public void testSaucesTab() {
        mainPage.clickSaucesTab();
        assertTrue(mainPage.isTabActive("Соусы"));
    }

    @Test
    @Description("Переход к разделу «Начинки»")
    public void testFillingsTab() {
        mainPage.clickFillingsTab();
        assertTrue(mainPage.isTabActive("Начинки"));
    }
}