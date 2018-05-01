package Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;

public class SearchWithoutLogin {

    private WebDriver driver;

    @DataProvider(name = "input")
    public Object[][] input(){ return new Object[][]{{"2002","2005"}}; }

    @BeforeMethod
    public void openPage(){ driver.get("http://localhost/library/member/date_search_form.php"); }

    @BeforeTest
    public void start(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\LENOVO\\Desktop\\assignment2\\libs\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test(dataProvider = "input")
    public void main(String start, String end) {

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        driver.findElement(By.name("from")).clear();
        driver.findElement(By.name("from")).sendKeys(start);

        driver.findElement(By.name("to")).clear();
        driver.findElement(By.name("to")).sendKeys(end);

        driver.findElement(By.xpath("//*[@id=\"myModal2\"]/div[2]/form/div[3]/div/button")).click();
        assertEquals(driver.getCurrentUrl(),"http://localhost/library/member/index.php");
    }

    @AfterTest
    public void close(){ driver.close(); }
}
