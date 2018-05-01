package Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;

public class TestingContact {

    private WebDriver driver;

    @DataProvider(name = "validInput")
    public Object[][] validInput(){ return new Object[][]{{"nour","adel","gmail@gmail.com","hello"}}; }

    @BeforeMethod
    public void openLoginPage(){ driver.get("http://localhost/library/contact_form.php");}

    @BeforeTest
    public void start(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\LENOVO\\Desktop\\assignment2\\libs\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test(dataProvider = "validInput")
    public void contact(String fname, String lname, String email, String message){

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.findElement(By.name("first_name")).sendKeys(fname);
        driver.findElement(By.name("last_name")).sendKeys(lname);
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("message")).sendKeys(message);

        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div/div[5]/div/button")).click();
        assertEquals(driver.getCurrentUrl(),"http://localhost/library/success2.php");
    }

    @AfterTest
    public void close(){ driver.close(); }
}
