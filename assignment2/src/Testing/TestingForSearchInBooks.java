package Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class TestingForSearchInBooks {

    private WebDriver driver;

    @DataProvider(name = "validInputForBooks")
    public Object[][] validBookInput(){ return new Object[][]{{"computer"}}; }

    @DataProvider(name = "invalidInputForBooks")
    public Object[][] invalidBookInput(){ return new Object[][]{{"games"}}; }

    @DataProvider(name = "validInputForNewBooks")
    public Object[][] validNewBookInput(){ return new Object[][]{{"Operating System"}}; }

    @DataProvider(name = "invalidInputNewForBooks")
    public Object[][] invalidNewBookInput(){ return new Object[][]{{"games"}}; }

    @DataProvider(name = "validInputForOldBooks")
    public Object[][] validForOldInput(){ return new Object[][]{{"computer"}}; }

    @DataProvider(name = "invalidInputForOldBooks")
    public Object[][] invalidForOldInput(){ return new Object[][]{{"games"}}; }

    @BeforeMethod
    public void openLoginPage(){

        driver.get("http://localhost/library/member/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

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

    @Test(dataProvider = "validInputForBooks")
    public void validTestInBooks(String book){

        boolean found = false;

        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"example_filter\"]/label/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"example_filter\"]/label/input")).sendKeys(book);

        List<WebElement> rows = driver.findElement(By.xpath("//*[@id=\"example\"]/tbody")).findElements(By.tagName("tr"));

        for(int i=0; i<rows.size(); i++) {

            List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
            for (WebElement w : cols) {
                if(w.getText().toLowerCase().contains(book.toLowerCase())) {
                    found = true;
                    break;
                }
            }
        }
        assertEquals(found,true);
    }

    @Test(dataProvider = "validInputForNewBooks")
    public void validTestInNewBooks(String book){

        boolean found = false;

        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/ul/li[4]/a")).click();
        driver.findElement(By.xpath("/html/body/div[7]/div/div/div/ul/li[2]/a")).click();

        driver.findElement(By.xpath("//*[@id=\"example_filter\"]/label/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"example_filter\"]/label/input")).sendKeys(book);

        List<WebElement> rows = driver.findElement(By.xpath("//*[@id=\"example\"]/tbody")).findElements(By.tagName("tr"));

        for(int i=0; i<rows.size(); i++) {

            List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
            for (WebElement w : cols) {
                if(w.getText().toLowerCase().contains(book.toLowerCase())) {
                    found = true;
                    break;
                }
            }
        }
        assertEquals(found,true);
    }

    @Test(dataProvider = "validInputForOldBooks")
    public void validTestInOldBooks(String book){

        boolean found = false;

        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/ul/li[4]/a")).click();
        driver.findElement(By.xpath("/html/body/div[7]/div/div/div/ul/li[3]/a")).click();

        driver.findElement(By.xpath("//*[@id=\"example_filter\"]/label/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"example_filter\"]/label/input")).sendKeys(book);

        List<WebElement> rows = driver.findElement(By.xpath("//*[@id=\"example\"]/tbody")).findElements(By.tagName("tr"));

        for(int i=0; i<rows.size(); i++) {

            List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
            for (WebElement w : cols) {
                if(w.getText().toLowerCase().contains(book.toLowerCase())) {
                    found = true;
                    break;
                }
            }
        }
        assertEquals(found,true);
    }

    @Test(dataProvider = "invalidInputForBooks")
    public void invalidTestInBook(String book){

        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/ul/li[4]/a")).click();

        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/ul/li[4]/a")).click();
        driver.findElement(By.xpath("/html/body/div[7]/div/div/div/ul/li[2]/a")).click();

        driver.findElement(By.xpath("//*[@id=\"example_filter\"]/label/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"example_filter\"]/label/input")).sendKeys(book);

        assertEquals(driver.findElement(By.xpath("//*[@id=\"example\"]/tbody/tr/td")).getText(),"No matching records found");
    }

    @Test(dataProvider = "invalidInputNewForBooks")
    public void invalidTestInNewBook(String book){

        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/ul/li[4]/a")).click();
        driver.findElement(By.xpath("/html/body/div[7]/div/div/div/ul/li[2]/a")).click();

        driver.findElement(By.xpath("//*[@id=\"example_filter\"]/label/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"example_filter\"]/label/input")).sendKeys(book);

        assertEquals(driver.findElement(By.xpath("//*[@id=\"example\"]/tbody/tr/td")).getText(),"No matching records found");
    }

    @Test(dataProvider = "invalidInputForOldBooks")
    public void invalidTestInOldBook(String book){

        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/ul/li[4]/a")).click();
        driver.findElement(By.xpath("/html/body/div[7]/div/div/div/ul/li[3]/a")).click();

        driver.findElement(By.xpath("//*[@id=\"example_filter\"]/label/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"example_filter\"]/label/input")).sendKeys(book);

        assertEquals(driver.findElement(By.xpath("//*[@id=\"example\"]/tbody/tr/td")).getText(),"No matching records found");
    }

    @AfterTest
    public void close(){ driver.close(); }
}
