import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage extends PageBase{

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    void openFirstArticle(){
        By articleLocator = By.xpath("(//main//ul[@class='list-unstyled']//h4//a)[1]");
        WebElement articaleElement = waitAndReturnElement(articleLocator);

        String articleUrl = articaleElement.getAttribute("href");
        driver.get(articleUrl);

        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//main[@id='page-content']//div[contains(@class, 'content')]//img)[1]")));
    }
    
}
