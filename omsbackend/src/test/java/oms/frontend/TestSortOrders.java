package oms.frontend;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSortOrders {
    WebDriver driver;

    @BeforeEach
    void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @Test
    void test() throws Exception{
        driver.get("http://localhost:4200/");
        Thread.sleep(3000);
    }

    @AfterEach
    void tearDown() throws Exception {
        driver.quit();
    }
}
