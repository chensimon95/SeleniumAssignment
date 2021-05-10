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


class Search extends Base {
    private By searchBar = By.xpath("//div[@class='nav-search-field ']/input");
    private By searchButton = By.id("nav-search-submit-text");

    public Search(WebDriver driver){
        super(driver);
        this.driver.get("https://www.amazon.com/?language=en_US");
    }

    public ResultPage search(String sear){
        this.waitAndReturnElement(searchBar).sendKeys(sear);
        this.waitAndReturnElement(searchButton).click();

        return new ResultPage(this.driver);
    }

}
