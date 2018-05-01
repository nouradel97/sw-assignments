package Testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;

public class GoToSuccessWithoutDoingAction {

    private WebDriver driver;

    @BeforeTest
    public void start(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\LENOVO\\Desktop\\assignment2\\libs\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void successWithoutAction() {

        driver.get("http://localhost/library/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        driver.get("http://localhost/library/success.php");
        assertEquals(driver.getCurrentUrl(),"http://localhost/library/member/index.php");
    }

    @AfterTest
    public void close(){ driver.close(); }
}
