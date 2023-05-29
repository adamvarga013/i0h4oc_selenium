import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.Iterator;

import java.net.URL;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;


import org.junit.*;

public class SeleniumTest {

    private WebDriver driver;
    private String email;
    private String password;

    @Before
    public void setup() throws MalformedURLException, 
                               java.io.FileNotFoundException, 
                               java.io.IOException,
                               org.json.simple.parser.ParseException
    {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("config.json"));
        JSONObject jsonObject = (JSONObject) obj;

        this.email = (String) jsonObject.get("email");
        this.password = (String) jsonObject.get("password");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        this.driver.manage().window().maximize();
    }

    @After
    public void close() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }

    //@Test
    public void testMainPageLoaded(){
        MainPage mainPage = new MainPage(this.driver);
        System.out.println(mainPage.getTitle());
        assertTrue(mainPage.hasLoaded());
    }

    //@Test
    public void testLogin(){
        MainPage mainPage = new MainPage(this.driver);
        mainPage.login(this.email, this.password);
    }

    //@Test
    public void testLogout(){
        MainPage mainPage = new MainPage(this.driver);
        mainPage.login(this.email, this.password);
        mainPage.logout();
    }

    @Test
    public void searchAndOpenArticleAfterLogin() {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.login(this.email, this.password);

        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-modal-open='#search']")));

        SearchResultPage searchResultPage = mainPage.search("Playstation");
        searchResultPage.openFirstArticle();
    }
}