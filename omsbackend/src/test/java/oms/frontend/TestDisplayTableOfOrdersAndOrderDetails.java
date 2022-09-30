package oms.frontend;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Set;

public class TestDisplayTableOfOrdersAndOrderDetails {

    private static WebDriver driver;

    @BeforeAll
    static void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("http://localhost:4200/");
        Thread.sleep(1000);
        driver.findElement(By.className("google-login-btn")).click();
        Thread.sleep(1000);

        String mainwindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();

        for(String window: windows) {
            if(window.equals(mainwindow) == false) {
                driver.switchTo().window(window);
            }
        }
        driver.findElement(By.id("identifierId")).sendKeys("nisumuitesting");
        Thread.sleep(1000);
        driver.findElement(By.id("identifierNext")).click();
        Thread.sleep(1000);
        driver.findElement(By.name("password")).sendKeys("passWord!");
        Thread.sleep(1000);
        driver.findElement(By.id("passwordNext")).click();
        Thread.sleep(4000);
        driver.switchTo().window(mainwindow);
    }

    @Test
    void testDisplayTableOfOrder() throws Exception{
        Boolean checkTableExist = driver.findElement(By.className("ng-star-inserted")).isDisplayed();
        Assertions.assertTrue(checkTableExist);
    }

    @Test
    void testOrderDetails() throws Exception{
        driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[1]")).click();
        Thread.sleep(1000);
        Boolean checkOrderDetailsExist = driver.getPageSource().contains("Order ID : 1");
        Assertions.assertTrue(checkOrderDetailsExist);
    }

    @AfterAll
    static void tearDown() throws Exception {
        driver.quit();
    }

}
