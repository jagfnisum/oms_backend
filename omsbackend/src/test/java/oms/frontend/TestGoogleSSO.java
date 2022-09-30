package oms.frontend;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Set;
public class TestGoogleSSO {
    static WebDriver driver;

    @Test
    static void Login() throws Exception {

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        ChromeOptions handlingSSL = new ChromeOptions();
        handlingSSL.setAcceptInsecureCerts(false);
        driver = new ChromeDriver(handlingSSL);
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
        driver.findElement(By.id("identifierId")).sendKeys("nisumtester1@gmail.com");
        Thread.sleep(3000);
        driver.findElement(By.id("identifierNext")).click();
        Thread.sleep(5000);
        driver.findElement(By.name("password")).sendKeys("NisumTesting1!");
        Thread.sleep(1000);
        driver.findElement(By.id("passwordNext")).click();
        Thread.sleep(4000);
        driver.switchTo().window(mainwindow);
        Thread.sleep(1000);
        String TableShown = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/thead/tr/th[1]/div/div[1]")).getText();
        Assertions.assertEquals("Orderid", TableShown);
//        driver.findElement(By.xpath("//*[@id=\"mat-select-value-1\"]")).click();
//        Thread.sleep(1000);
//        driver.findElement(By.xpath("//*[@id=\"mat-option-4\"]/span")).click();

    }
    @AfterEach
    void tearDown() throws Exception {
        driver.quit();
    }
}
