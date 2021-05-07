package tests;

import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class FirstSeleniumTest {
    public WebDriver driver;
    public WebDriverWait wait;
    
    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 10);
    }
    
    private WebElement waitAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }    
    
    @Test
    public void testSearch() {
        this.driver.get("https://www.inf.elte.hu/en/");
       
        Assert.assertTrue(this.waitAndReturnElement(By.className("footer-block")).getText().contains("2021 ELTE Faculty of Informatics"));
        
        this.waitAndReturnElement(By.className("search-bar-toggler")).click();
        
        this.waitAndReturnElement(By.name("search")).sendKeys("Students\n");

        WebElement bodyElemnet = this.waitAndReturnElement(By.tagName("body"));
        System.out.println("-------------------------------------");
        System.out.println(bodyElemnet.getText());
        Assert.assertTrue(bodyElemnet.getText().contains("found"));
        Assert.assertTrue(bodyElemnet.getText().contains("For Students"));
    }
    

    
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
