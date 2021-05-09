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

class Login extends Base {
    private By gologin = By.id("nav-link-accountList");
    private By username = By.id("ap_email");
    private By password = By.name("password");

    public Login(WebDriver driver){
        super(driver);
        this.driver.get("https://www.amazon.com/?language=en_US");
    }

    public ResultPage doLogin(String un, String pw){
        this.waitAndReturnElement(gologin).click();
        this.waitAndReturnElement(username).sendKeys(un + "\n");
        this.waitAndReturnElement(password).sendKeys(pw + "\n");

        return new ResultPage(this.driver);
    }

}