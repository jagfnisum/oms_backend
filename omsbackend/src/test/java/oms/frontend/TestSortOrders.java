package oms.frontend;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Set;

public class TestSortOrders {
    static WebDriver driver;

    @BeforeAll
    static void setUp() throws Exception {

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_copy");
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
        driver.findElement(By.id("identifierId")).sendKeys("nisumtester@gmail.com");
        Thread.sleep(3000);
        driver.findElement(By.id("identifierNext")).click();
        Thread.sleep(5000);
        driver.findElement(By.name("password")).sendKeys("Zjy123243");
        Thread.sleep(1000);
        driver.findElement(By.id("passwordNext")).click();
        Thread.sleep(4000);
        driver.switchTo().window(mainwindow);
        Thread.sleep(1000);
//        driver.findElement(By.xpath("//*[@id=\"mat-select-value-1\"]")).click();
//        Thread.sleep(1000);
//        driver.findElement(By.xpath("//*[@id=\"mat-option-4\"]/span")).click();

    }

    @Test
    void testSortOrderId() throws Exception{
        driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/thead/tr/th[1]/div")).click();
        Thread.sleep(1000);
        String s1 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[1]/td[1]")).getText();
        String s2 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[2]/td[1]")).getText();
        String s9 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[9]/td[1]")).getText();
        String s10 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[10]/td[1]")).getText();
        Assertions.assertTrue(Integer.parseInt(s1) <= Integer.parseInt(s2));
        Assertions.assertTrue(Integer.parseInt(s2) <= Integer.parseInt(s9));
        Assertions.assertTrue(Integer.parseInt(s9) <= Integer.parseInt(s10));
        driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/thead/tr/th[1]/div")).click();
        Thread.sleep(1000);
        s1 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[1]/td[1]")).getText();
        s2 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[2]/td[1]")).getText();
        s9 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[9]/td[1]")).getText();
        s10 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[10]/td[1]")).getText();
        Assertions.assertTrue(Integer.parseInt(s1) >= Integer.parseInt(s2));
        Assertions.assertTrue(Integer.parseInt(s2) >= Integer.parseInt(s9));
        Assertions.assertTrue(Integer.parseInt(s9) >= Integer.parseInt(s10));
    }

    @Test
    void testSortUserId() throws Exception{
        driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/thead/tr/th[2]/div")).click();
        Thread.sleep(1000);
        String s1 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[1]/td[2]")).getText();
        String s2 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[2]/td[2]")).getText();
        String s9 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[9]/td[2]")).getText();
        String s10 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[10]/td[2]")).getText();
        Assertions.assertTrue(Integer.parseInt(s1) <= Integer.parseInt(s2));
        Assertions.assertTrue(Integer.parseInt(s2) <= Integer.parseInt(s9));
        Assertions.assertTrue(Integer.parseInt(s9) <= Integer.parseInt(s10));
        driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/thead/tr/th[2]/div")).click();
        Thread.sleep(1000);
        s1 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[1]/td[2]")).getText();
        s2 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[2]/td[2]")).getText();
        s9 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[9]/td[2]")).getText();
        s10 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[10]/td[2]")).getText();
        Assertions.assertTrue(Integer.parseInt(s1) >= Integer.parseInt(s2));
        Assertions.assertTrue(Integer.parseInt(s2) >= Integer.parseInt(s9));
        Assertions.assertTrue(Integer.parseInt(s9) >= Integer.parseInt(s10));
    }

    @Test
    void testSortAddressId() throws Exception{
        driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/thead/tr/th[3]/div")).click();
        Thread.sleep(1000);
        String s1 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[1]/td[3]")).getText();
        String s2 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[2]/td[3]")).getText();
        String s9 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[9]/td[3]")).getText();
        String s10 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[10]/td[3]")).getText();
        Assertions.assertTrue(Integer.parseInt(s1) <= Integer.parseInt(s2));
        Assertions.assertTrue(Integer.parseInt(s2) <= Integer.parseInt(s9));
        Assertions.assertTrue(Integer.parseInt(s9) <= Integer.parseInt(s10));
        driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/thead/tr/th[3]/div")).click();
        Thread.sleep(1000);
        s1 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[1]/td[3]")).getText();
        s2 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[2]/td[3]")).getText();
        s9 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[9]/td[3]")).getText();
        s10 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[10]/td[3]")).getText();
        Assertions.assertTrue(Integer.parseInt(s1) >= Integer.parseInt(s2));
        Assertions.assertTrue(Integer.parseInt(s2) >= Integer.parseInt(s9));
        Assertions.assertTrue(Integer.parseInt(s9) >= Integer.parseInt(s10));
    }

    @Test
    void testSortCreditId() throws Exception{
        driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/thead/tr/th[4]/div")).click();
        Thread.sleep(1000);
        String s1 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[1]/td[4]")).getText();
        String s2 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[2]/td[4]")).getText();
        String s9 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[9]/td[4]")).getText();
        String s10 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[10]/td[4]")).getText();
        Assertions.assertTrue(Integer.parseInt(s1) <= Integer.parseInt(s2));
        Assertions.assertTrue(Integer.parseInt(s2) <= Integer.parseInt(s9));
        Assertions.assertTrue(Integer.parseInt(s9) <= Integer.parseInt(s10));
        driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/thead/tr/th[4]/div")).click();
        Thread.sleep(1000);
        s1 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[1]/td[4]")).getText();
        s2 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[2]/td[4]")).getText();
        s9 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[9]/td[4]")).getText();
        s10 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[10]/td[4]")).getText();
        Assertions.assertTrue(Integer.parseInt(s1) >= Integer.parseInt(s2));
        Assertions.assertTrue(Integer.parseInt(s2) >= Integer.parseInt(s9));
        Assertions.assertTrue(Integer.parseInt(s9) >= Integer.parseInt(s10));
    }

    @Test
    void testSortPrice() throws Exception{
        driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/thead/tr/th[7]/div")).click();
        Thread.sleep(1000);
        String s1 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[1]/td[7]")).getText();
        String s2 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[2]/td[7]")).getText();
        String s9 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[9]/td[7]")).getText();
        String s10 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[10]/td[7]")).getText();
        Assertions.assertTrue(Integer.parseInt(s1) <= Integer.parseInt(s2));
        Assertions.assertTrue(Integer.parseInt(s2) <= Integer.parseInt(s9));
        Assertions.assertTrue(Integer.parseInt(s9) <= Integer.parseInt(s10));
        driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/thead/tr/th[7]/div")).click();
        Thread.sleep(1000);
        s1 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[1]/td[7]")).getText();
        s2 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[2]/td[7]")).getText();
        s9 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[9]/td[7]")).getText();
        s10 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[10]/td[7]")).getText();
        Assertions.assertTrue(Integer.parseInt(s1) >= Integer.parseInt(s2));
        Assertions.assertTrue(Integer.parseInt(s2) >= Integer.parseInt(s9));
        Assertions.assertTrue(Integer.parseInt(s9) >= Integer.parseInt(s10));
    }

    @Test
    void testSortdateOrder() throws Exception{
        driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/thead/tr/th[5]/div")).click();
        Thread.sleep(1000);
        String s1 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[1]/td[5]")).getText();
        String s2 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[2]/td[5]")).getText();
        String s9 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[9]/td[5]")).getText();
        String s10 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[10]/td[5]")).getText();
        Assertions.assertTrue(s1.compareTo(s2) <= 0);
        Assertions.assertTrue(s2.compareTo(s9) <= 0);
        Assertions.assertTrue(s9.compareTo(s10) <= 0);
        driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/thead/tr/th[5]/div")).click();
        Thread.sleep(1000);
        s1 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[1]/td[5]")).getText();
        s2 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[2]/td[5]")).getText();
        s9 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[9]/td[5]")).getText();
        s10 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[10]/td[5]")).getText();
        Assertions.assertTrue(s1.compareTo(s2) >= 0);
        Assertions.assertTrue(s2.compareTo(s9) >= 0);
        Assertions.assertTrue(s9.compareTo(s10) >= 0);
    }

    @Test
    void testSortdateShip() throws Exception{
        driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/thead/tr/th[6]/div")).click();
        Thread.sleep(1000);
        String s1 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[1]/td[6]")).getText();
        String s2 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[2]/td[6]")).getText();
        String s9 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[9]/td[6]")).getText();
        String s10 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[10]/td[6]")).getText();
        Assertions.assertTrue(s1.compareTo(s2) <= 0);
        Assertions.assertTrue(s2.compareTo(s9) <= 0);
        Assertions.assertTrue(s9.compareTo(s10) <= 0);
        driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/thead/tr/th[6]/div")).click();
        Thread.sleep(1000);
        s1 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[1]/td[6]")).getText();
        s2 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[2]/td[6]")).getText();
        s9 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[9]/td[6]")).getText();
        s10 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[10]/td[6]")).getText();
        Assertions.assertTrue(s1.compareTo(s2) >= 0);
        Assertions.assertTrue(s2.compareTo(s9) >= 0);
        Assertions.assertTrue(s9.compareTo(s10) >= 0);
    }

    @Test
    void testSortStatus() throws Exception{
        driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/thead/tr/th[8]/div")).click();
        Thread.sleep(1000);
        String s1 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[1]/td[8]")).getText();
        String s2 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[2]/td[8]")).getText();
        String s9 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[9]/td[8]")).getText();
        String s10 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[10]/td[8]")).getText();
        Assertions.assertTrue(s1.compareTo(s2) <= 0);
        Assertions.assertTrue(s2.compareTo(s9) <= 0);
        Assertions.assertTrue(s9.compareTo(s10) <= 0);
        driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/thead/tr/th[8]/div")).click();
        Thread.sleep(1000);
        s1 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[1]/td[8]")).getText();
        s2 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[2]/td[8]")).getText();
        s9 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[9]/td[8]")).getText();
        s10 = driver.findElement(By.xpath("/html/body/app-root/order-table/div/table/tbody/tr[10]/td[8]")).getText();
        Assertions.assertTrue(s1.compareTo(s2) >= 0);
        Assertions.assertTrue(s2.compareTo(s9) >= 0);
        Assertions.assertTrue(s9.compareTo(s10) >= 0);
    }


    @AfterAll
    static void tearDown() throws Exception {
        driver.quit();
    }
}
