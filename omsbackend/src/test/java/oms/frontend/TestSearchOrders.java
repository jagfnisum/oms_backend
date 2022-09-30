package oms.frontend;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class TestSearchOrders {

    static WebDriver driver;

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
    void test() throws Exception{
        driver.findElement(By.name("Search")).sendKeys("Shipped");
        String shippedString = driver.findElement(By.xpath("//order-table/div/table/tbody/tr[1]/td[6]")).getText();
        Assertions.assertEquals("Shipped", shippedString);
        Thread.sleep(1000);

        driver.findElement(By.name("Search")).clear();
        Thread.sleep(1000);

        driver.findElement(By.name("Search")).sendKeys("Delivered");
        Thread.sleep(1000);
        String deliveredString = driver.findElement(By.xpath("//order-table/div/table/tbody/tr[1]/td[6]")).getText();
        Assertions.assertEquals("Delivered", deliveredString);

    }

    @AfterEach
    void tearDown() throws Exception {
//        driver.quit();
    }

}
