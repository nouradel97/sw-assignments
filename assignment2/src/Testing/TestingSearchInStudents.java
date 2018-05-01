package Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;

public class TestingSearchInStudents {

    private WebDriver driver;

    @DataProvider(name = "validInput")
    public Object[][] validInput(){ return new Object[][]{{"Islam"}}; }

    @DataProvider(name = "invalidInput")
    public Object[][] invalidInput() { return new Object[][]{{"nour"}};}

    @BeforeMethod
    public void openLoginPage(){

        driver.get("http://localhost/library/member/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(56, TimeUnit.SECONDS);

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
    public void validTest(String name){

        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/ul/li[2]/a")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/ul/li[2]/ul/li[1]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"example_filter\"]/label/input")).sendKeys(name);

        boolean found = false;
        List<WebElement> rows = driver.findElement(By.xpath("//*[@id=\"example\"]/tbody")).findElements(By.tagName("tr"));

        for(int i=0; i<rows.size(); i++) {

            List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
            for (WebElement w : cols) {
                if(w.getText().toLowerCase().contains(name.toLowerCase())) {
                    found = true;
                    break;
                }
            }
        }
        assertEquals(found,true);
    }

    @Test(dataProvider = "invalidInput")
    public void invalidTest(String name){

        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/ul/li[2]/a")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/ul/li[2]/ul/li[1]/a")).click();

        driver.findElement(By.xpath("//*[@id=\"example_filter\"]/label/input")).sendKeys(name);
        assertEquals(driver.findElement(By.xpath("//*[@id=\"example\"]/tbody/tr/td")).getText(),"No matching records found");
    }

    @AfterTest
    public void close(){ driver.close(); }
}
