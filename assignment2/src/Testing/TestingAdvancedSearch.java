package Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;

public class TestingAdvancedSearch {

    private WebDriver driver;

    @DataProvider(name = "validInput")
    public Object[][] validInput(){

        return new Object[][]{{"Computer Graphics","Donald Hearn & Pauline Baker"}}; }

    @DataProvider(name = "invalidInput")
    public Object[][] invalidInput() {

        return new Object[][]{{"Computer Graphics", ""},
            {"computer", "Donald Hearn & Pauline Baker"},
            {"Computer Graphics","Donald"},
            {"", ""}};
    }

    @BeforeMethod
    public void openLoginPage(){

        driver.get("http://localhost/library/member/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);

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

    @Test(dataProvider = "validInput")
    public void validTest(String title, String auther){

        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/ul/li[5]/a")).click();

        driver.findElement(By.xpath("//*[@id=\"inputEmail\"]")).sendKeys(title);
        driver.findElement(By.xpath("//*[@id=\"inputPassword\"]")).sendKeys(auther);

        driver.findElement(By.xpath("//*[@id=\"myModal\"]/div[2]/form/div[3]/div/button")).click();

        boolean found = true;
        List<WebElement> titles = driver.findElements(By.xpath("//*[@id=\"example\"]/tbody/tr/td[2]"));
        List<WebElement> authers = driver.findElements(By.xpath("//*[@id=\"example\"]/tbody/tr/td[4]"));

        for(int i=0; i<authers.size(); i++){

            System.out.println(titles.get(i).getText() + "  " + authers.get(i).getText());
            if(!titles.get(i).getText().toLowerCase().contains(title.toLowerCase()) ||
                    !authers.get(i).getText().toLowerCase().contains(auther.toLowerCase())) {
                found = false;
                break;
            }
        }
        assertEquals(found,true);
    }

    @Test(dataProvider = "invalidInput")
    public void invalidTest(String title, String auther){

        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/ul/li[5]/a")).click();

        driver.findElement(By.xpath("//*[@id=\"inputEmail\"]")).sendKeys(title);
        driver.findElement(By.xpath("//*[@id=\"inputPassword\"]")).sendKeys(auther);

        driver.findElement(By.xpath("//*[@id=\"myModal\"]/div[2]/form/div[3]/div/button")).click();

        assertEquals(driver.findElement(By.xpath("//*[@id=\"example\"]/tbody/tr/td")).getText(),"No matching records found");
    }

    @AfterTest
    public void close(){ driver.close(); }
}
