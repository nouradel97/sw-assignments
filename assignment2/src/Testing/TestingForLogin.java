package Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;

public class TestingForLogin {

    private WebDriver driver;

    @DataProvider(name = "validInput")
    public Object[][] validInput(){ return new Object[][]{{"jahidd26","123456"},{"admin","admin"}}; }

    @DataProvider(name = "invalidInput")
    public Object[][] invalidInput(){ return new Object[][]{{"nour","1234567"},{"ABC","anything' OR '1'='1 "}}; }

    @BeforeMethod
    public void openLoginPage(){ driver.get("http://localhost/library/member/");}

    @BeforeTest
    public void start(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\LENOVO\\Desktop\\assignment2\\libs\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test(dataProvider = "validInput")
    public void validTest(String username, String password){

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys(username);

        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(password);

        driver.findElement(By.name("submit")).click();
        assertEquals(driver.getCurrentUrl(),"http://localhost/library/member/dashboard.php");
    }

    @Test(dataProvider = "invalidInput")
    public void invalidTest(String username, String password){

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys(username);

        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(password);

        driver.findElement(By.name("submit")).click();
        assertEquals(driver.getCurrentUrl(),"http://localhost/library/member/");
    }

    @AfterTest
    public void close(){ driver.close(); }
}
