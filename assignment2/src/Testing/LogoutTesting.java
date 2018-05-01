package Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;

public class LogoutTesting {

    private WebDriver driver;

    @BeforeMethod
    public void openLoginPage(){

        driver.get("http://localhost/library/member/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("jahidd26");

        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("123456");

        driver.findElement(By.name("submit")).click();
    }

    @BeforeTest
    public void start(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\LENOVO\\Desktop\\assignment2\\libs\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void logout(){
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/ul/li[7]/a")).click();
        assertEquals(driver.getCurrentUrl(),"http://localhost/library/index.php");
    }

    @AfterTest
    public void close(){ driver.close(); }
}
