package Selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestAmason {

    protected WebDriver driver;

    @BeforeTest
    public void setup() throws IOException{
        System.out.println("Before");

        System.setProperty("webdriver.chrome.driver", "D:\\study\\chromedriver_win32\\chromedriver.exe");

        WebDriverManager.chromedriver().setup();
        //System.setProperty("webdriver.chrome.driver", "D:\\study\\chromedriver_win32\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
    public void testSearch(){

            driver.get("https://www.amazon.com/");
            WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
            searchBox.getText();
            searchBox.sendKeys("Shoes");
            searchBox.click();
            //searchBox.getRect();

            WebElement searchElem = driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
            searchElem.click();

            WebElement womenShoes = driver.findElement(By.xpath("//*[@id=\"n/679377011\"]/span/a"));
            womenShoes.click();
            WebElement onList = driver.findElement(By.xpath("//*[@id=\"n/679377011\"]/span/span[contains(@class,'a-size-base a-color-base a-text-bold')]"));
            Assert.assertTrue(onList.getAttribute("class").contains("a-text-bold"),"Failed to select Women's Walking Shoes option");

   //     catch (Exception e){
  //          System.out.println("Women's Walking Shoes option in not found");
  //      }

        //span[contains(text(),"Women's Walking Shoes")]
        //input[@id='679377011']
        //WebElement element = driver.findElement(By.partialLinkText("Shoes"));
        //searchBox.getRect();
    }

    @AfterTest
    public void tearDown(){
        //service.stop();
        driver.quit();
    }


}
