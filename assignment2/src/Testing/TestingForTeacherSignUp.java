package Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;

public class TestingForTeacherSignUp {

    private WebDriver driver;

    @DataProvider(name = "validInput")
    public Object[][] validInput(){ return new Object[][]{{"Ahmed","Hossam","ahmed22","Giza","011111","123456"}}; }

    @DataProvider(name = "invalidInput")
    public Object[][] invalidInput(){

        return new Object[][]{{"yasmin","sayed","jahidd26","Giza","011111","123456"},
                {"yasmin","sayed","yasmin21","Giza","011111",""},
                {"yasmin","","yasmin21","Giza","011111","123456"},
                {"","sayed","yasmin21","Giza","011111","123456"},
                {" "," "," "," "," "," "},
                {"yasmin","sayed","","Giza","011111","123456"},
                {"yasmin","sayed","yasmin21","Giza","contact","123456"}};
    }

    @BeforeMethod
    public void openPage(){

        driver.get("http://localhost/library/teacher_form.php");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    }

    @BeforeTest
    public void start(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\LENOVO\\Desktop\\assignment2\\libs\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test(dataProvider = "validInput")
    public void validTest(String fname, String lname, String username,
                          String address, String contact, String password){

        driver.findElement(By.name("firstname")).clear();
        driver.findElement(By.name("firstname")).sendKeys(fname);

        driver.findElement(By.name("lastname")).clear();
        driver.findElement(By.name("lastname")).sendKeys(lname);

        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys(username);

        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div/div/div[4]/div/select/option[1]")).click();

        driver.findElement(By.name("address")).clear();
        driver.findElement(By.name("address")).sendKeys(address);

        driver.findElement(By.name("contact")).clear();
        driver.findElement(By.name("contact")).sendKeys(contact);

        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(password);

        driver.findElement(By.name("submit")).click();
        assertEquals(driver.getCurrentUrl(),"http://localhost/library/success.php");
    }

    @Test(dataProvider = "invalidInput")
    public void invalidTest(String fname, String lname, String username,
                            String address, String contact, String password){

        driver.findElement(By.name("firstname")).clear();
        driver.findElement(By.name("firstname")).sendKeys(fname);

        driver.findElement(By.name("lastname")).clear();
        driver.findElement(By.name("lastname")).sendKeys(lname);

        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys(username);

        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div/div/div[4]/div/select/option[2]")).click();

        driver.findElement(By.name("address")).clear();
        driver.findElement(By.name("address")).sendKeys(address);

        driver.findElement(By.name("contact")).clear();
        driver.findElement(By.name("contact")).sendKeys(contact);

        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(password);

        driver.findElement(By.name("submit")).click();
        assertEquals(driver.getCurrentUrl(),"http://localhost/library/teacher_form.php");
    }

    @AfterTest
    public void close(){ driver.close(); }
}
