import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends PageBase{
    public MainPage(WebDriver driver){
        super(driver);
        this.driver.get("https://prohardver.hu/index.html");
    }

    public String getTitle(){
        WebElement titleElement = this.driver.findElement(By.xpath("html//head//title"));

        return titleElement.getAttribute("innerHTML"); // getText has issues
    }

    public Boolean hasLoaded(){
        return (getBodyText().length() > 0);
    }

    public void login(String email, String password){
        By loginModalLocator = By.xpath("//button[contains(@data-modal-open, 'muvelet/hozzaferes/belepes')]");
        WebElement userIconElement = waitAndReturnElement(loginModalLocator);
        userIconElement.click();

        By emailInputLocator = By.xpath("//input[@type='email']");
        WebElement emailInputElement = waitAndReturnElement(emailInputLocator);
        emailInputElement.sendKeys(email);

        By passwordInputLocator = By.xpath("//input[@type='password']");
        WebElement passwordInputElement = waitAndReturnElement(passwordInputLocator);
        passwordInputElement.sendKeys(password);

        By submitButtonLocator = By.xpath("//div[@class='form-group']//button[@type='submit']");
        WebElement submitButtonElement = waitAndReturnElement(submitButtonLocator);
        submitButtonElement.click();
    }

    public void logout(){
        By userLocator = By.xpath("//nav[@id='header-sticky']//a[@data-toggle]");
        WebElement userElement = waitAndReturnElement(userLocator);
        userElement.click();

        By logoutButtonLocator = By.xpath("//nav[@id='header-sticky']//li//a[contains(@href, 'kilepes')]");
        WebElement loginButtonElement = waitAndReturnElement(logoutButtonLocator);
        loginButtonElement.click();
    }

    public SearchResultPage search(String query){
        By searchLocator = By.xpath("//button[@data-modal-open='#search']");
        WebElement searchElement = waitAndReturnElement(searchLocator);
        searchElement.click();

        By inputLocator = By.xpath("//form[contains(@action, 'keres')]//input[@name='stext']");
        WebElement inputElement = waitAndReturnElement(inputLocator);
        inputElement.sendKeys(query);

        By submitLocator = By.xpath("//form[contains(@action, 'keres')]//button");
        WebElement submitElement = waitAndReturnElement(submitLocator);
        submitElement.click();

        return new SearchResultPage(driver);
    }
}
