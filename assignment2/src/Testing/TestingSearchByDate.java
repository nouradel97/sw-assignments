package Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;

public class TestingSearchByDate {

    private WebDriver driver;

    @DataProvider(name = "validInput")
    public Object[][] validInput(){ return new Object[][]{{"2015-09-07 00:00:00","2015-09-15 00:00:00"}}; }

    @DataProvider(name = "invalidInput")
    public Object[][] invalidInput(){ return new Object[][]{{"2015-13-07 00:00:00","2015-09-15 00:00:00"},
            {"2002","2005"}}; }

    @BeforeTest
    public void start(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\LENOVO\\Desktop\\assignment2\\libs\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @BeforeMethod
    public void openLoginPage(){

        driver.get("http://localhost/library/member/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("jahidd26");

        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("123456");

        driver.findElement(By.name("submit")).click();
    }

    @Test(dataProvider = "validInput")
    public void validTest(String start, String end){

        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/ul/li[6]/a")).click();
        driver.findElement(By.name("from")).sendKeys(start);
        driver.findElement(By.name("to")).sendKeys(end);

        driver.findElement(By.xpath("//*[@id=\"myModal2\"]/div[2]/form/div[3]/div/button")).click();

        boolean valid = true;

        List<WebElement> dates = driver.findElements(By.xpath("//*[@id=\"example\"]/tbody/tr/td[10]"));
        for(WebElement w: dates){
           if(w.getText().compareTo(start) < 0 || w.getText().compareTo(end) > 0) {
               valid = false;
               break;
           }
        }
        assertEquals(valid,true);
    }

    @Test(dataProvider = "invalidInput")
    public void invalidTest(String start, String end){

        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/ul/li[6]/a")).click();
        driver.findElement(By.name("from")).sendKeys(start);
        driver.findElement(By.name("to")).sendKeys(end);

        driver.findElement(By.xpath("//*[@id=\"myModal2\"]/div[2]/form/div[3]/div/button")).click();
        assertEquals(driver.findElement(By.xpath("//*[@id=\"example\"]/tbody/tr/td")).getText(),"No data available in table");
    }

    @AfterTest
    public void close(){ driver.close(); }

}
