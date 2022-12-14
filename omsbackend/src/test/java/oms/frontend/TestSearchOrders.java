package oms.frontend;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Set;
import java.util.List;


public class TestSearchOrders {
    static WebDriver driver;

    @BeforeAll
    static void setUp() throws Exception {

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
//        driver.findElement(By.xpath("//*[@id=\"mat-select-value-1\"]")).click();
//        Thread.sleep(1000);
//        driver.findElement(By.xpath("//*[@id=\"mat-option-4\"]/span")).click();

    }

    @Test
    void searchOrder() throws Exception{
        String testInput = "Del";
        int count =0;
        driver.findElement(By.tagName("input")).sendKeys(testInput);
        Thread.sleep(1000);
            List<WebElement> rows_table = driver.findElements(By.tagName("tr"));
            //To calculate no of rows In table.
            rows_table.remove(0);
            int rows_count = rows_table.size();

            //Loop will execute till the last row of table.
            for (int row=0; row<rows_count; row++){
                //To locate columns(cells) of that specific row.
                List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
                //To calculate no of columns(cells) In that specific row.
                int columns_count = Columns_row.size();

                //Loop will execute till the last cell of that specific row.
                for (int column=0; column<columns_count; column++) {
                    //To retrieve text from that specific cell.
                    String celtext = Columns_row.get(column).getText();
                    System.out.print(celtext + " ");
                    if (celtext.contains(testInput)) {
                        count++;
                        System.out.println("found");
                    }
//
                }
            }
        System.out.println(count);
        Assertions.assertEquals(rows_count,count);
    }

    @AfterEach
    void tearDown() throws Exception {
        driver.quit();
    }

}