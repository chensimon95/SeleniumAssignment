import org.junit.*;
import org.openqa.selenium.*;
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
import java.util.*;  


public class SeleniumTest {
    public WebDriver driver;
    public WebDriverWait wait;
    
    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    
    @Test
    public void testOpenPage() {
        Login openPage = new Login(this.driver);
        String bodyText = openPage.getBodyText();
        Assert.assertTrue(bodyText.contains("Amazon Drive"));
    }

    @Test
    public void testLogin() {
        Login loginpage = new Login(this.driver);
        ResultPage result = loginpage.doLogin("chensimon1211@gmail.com","xxmff14");
        String bodyText = result.getBodyText();
        Assert.assertTrue(bodyText.contains("Amazon Drive"));
    }

    @Test
    public void testLogout() {
        Login loginpage = new Login(this.driver);
        ResultPage result = loginpage.doLogin("chensimon1211@gmail.com","xxmff14");
        String bodyText = result.getBodyText();
        Assert.assertTrue(bodyText.contains("Amazon Drive"));
        Logout logout = new Logout(result.getDriver());
        ResultPage result2 = logout.doLogout();
        result2.waitAndReturnElement(By.className("a-link-nav-icon")).click();
        String bodyText2 = result2.getBodyText();
        Assert.assertTrue(bodyText2.contains("Amazon Drive"));
    }

    @Test
    public void testSearch(){
        Search searchTest = new Search(this.driver);
        ResultPage result = searchTest.search("Laptops");
        String bodyText = result.getBodyText();
        Assert.assertTrue(bodyText.contains("results for"));
        Search searchTest2 = new Search(result.getDriver());
        ResultPage result2 = searchTest2.search("milk");
        bodyText = result2.getBodyText();
        Assert.assertTrue(bodyText.contains("Horizon Organic"));

    }

    @Test
    public void testGetTitle(){
        Title title = new Title(this.driver);
        String titleText = title.titleText();
        Assert.assertTrue(titleText.contains("Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more"));
    }

    @Test
    public void testDropDown(){
        Search searchTest = new Search(this.driver);
        ResultPage result = searchTest.search("Laptops");
        DropDown drop = new DropDown(result.getDriver());
        List<String> result1 = drop.droptext();
        Assert.assertTrue(result1.get(0).contains("Featured"));
        Assert.assertTrue(result1.get(1).contains("Price: Low to High"));
        Assert.assertTrue(result1.get(2).contains("Price: High to Low"));
    }

    @Test
    public void testBackButton(){
        Search searchTest = new Search(this.driver);
        ResultPage result = searchTest.search("Laptops");
        String bodyText = result.getBodyText();
        Assert.assertTrue(bodyText.contains("results for"));
        result.driver.navigate().back();
        Title title = new Title(result.driver);
        String titleText = title.titleText();
        Assert.assertTrue(titleText.contains("Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more"));
    }

    @Test
    public void testCookie(){
        CookieUse cookietest = new CookieUse(this.driver);
        Set<Cookie> cookies = cookietest.getCookies();
        ResultPage result = cookietest.useCookies(cookies);
        String bodyText = result.getBodyText();

        //If use cookies login success, there will have my username
        Assert.assertTrue(bodyText.contains("Chen"));
    }

    @Test
    public void testMultiplePage(){
        List<String> subUrls = new ArrayList<>();
        MultiplePage mul = new MultiplePage(this.driver);
        Search searchTest = new Search(this.driver);
        ResultPage result = searchTest.search("Laptops");
        subUrls.add(mul.getSubUrl(result.driver));
        result = searchTest.search("Milk");
        subUrls.add(mul.getSubUrl(result.driver));
        result = searchTest.search("shampoo");
        subUrls.add(mul.getSubUrl(result.driver));
        mul.openPages(subUrls);
    }

    @Test
    public void testDragDrop(){
        UploadFile up = new UploadFile(this.driver);
        ResultPage result = up.UploadImage();
    }
    
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}

