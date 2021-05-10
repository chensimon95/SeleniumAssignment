import org.junit.*;
import java.util.*;
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

class CookieUse extends Base{

    public CookieUse(WebDriver driver){
        super(driver);
        //this.driver.get("https://www.amazon.com/?language=en_US");
    }

    public Set<Cookie> getCookies(){
        Login loginpage = new Login(this.driver);
        ResultPage result = loginpage.doLogin("chensimon1211@gmail.com","xxmff14");
        Set<Cookie> cookies = result.driver.manage().getCookies();
        //Logout. After open webpage and use cookies login
        Logout logout = new Logout(result.getDriver());
        ResultPage result2 = logout.doLogout();
        result2.waitAndReturnElement(By.className("a-link-nav-icon")).click();
        return cookies;
    }

    public ResultPage useCookies(Set<Cookie> cookies){
        for(Cookie cookie : cookies){
            this.driver.manage().addCookie(cookie);
        }

        this.driver.get("https://www.amazon.com/?language=en_US");
        return new ResultPage(this.driver);
    }

}