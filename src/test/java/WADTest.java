import io.appium.java_client.windows.WindowsDriver;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.HomePage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import static jdk.nashorn.internal.objects.NativeString.trim;

public class WADTest {
    public static WindowsDriver chromeSession = null;
    public static WebDriverWait cWait;
    Actions chrAct = new Actions(chromeSession);
    public List<WebElement> tabs;
    public int i;
    WebElement adrSearch;
    WebElement newTab;

    public static String getDate() {
        LocalDate date = LocalDate.now();
        return date.toString();
    }

    @BeforeClass
    public static void setUp() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("app", "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
            capabilities.setCapability("platformName", "Windows");
            capabilities.setCapability("deviceName", "WindowsPC");
            chromeSession = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
            chromeSession.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            cWait = new WebDriverWait(chromeSession, 3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @AfterClass
//    @AfterMethod
//    public void cleanApp() {
//        notepadSession.quit();
//        setUp();
//    }

    @AfterClass
    public static void tearDown() {

//        chromeSession.quit();
    }

    @Test
    public void choiceTV() throws InterruptedException, NullPointerException {
        WebElement adrSearch = chromeSession.findElementByName("Адресная строка и строка поиска");
        adrSearch.clear();
        adrSearch.sendKeys("market.yandex.ru/catalog--elektronika/54440");      // http://tkso.ru"
        adrSearch.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        System.out.println("Before Market");        // Возврат к HomePage
        chromeSession.findElementByAccessibilityId("logoPartMarket").click();
//        chromeSession.findElementByXPath("//Hyperlink[@AutomationId='logoPartMarket']").click();
        Thread.sleep(2000);
        makeScreenshot("market");

        // "/Pane[@Name=\"Рабочий стол 1\"][@ClassName=\"#32769\"]/Pane[@Name=\"Электроника — купить на Яндекс Маркете - Google Chrome\"][@ClassName=\"Chrome_WidgetWin_1\"]/Document[@Name=\"Электроника — купить на Яндекс Маркете\"][@ClassName=\"Chrome_RenderWidgetHostHWND\"]/Group[position()=2]/MenuItem[@AutomationId=\"catalogPopupButton\"][@Name=\"Каталог\"]"
        System.out.println("Before Каталог");
        chromeSession.findElementByXPath("//MenuItem[@Name='Каталог']").click();
        Thread.sleep(2000);
        makeScreenshot("catalog");

        System.out.println("Before Электроника");
    //        chrAct.moveToElement(chromeSession.findElementByXPath("//TabItem[@Name='Электроника']"))
    //                .build().perform();
//        chromeSession.findElementByXPath("//Hyperlink[@Name='Электроника']").click();
        WebElement el_nika = chromeSession.findElementByName("Электроника");
        chrAct.moveToElement(el_nika).build().perform();
//        chrAct.doubleClick(el_nika).build().perform();
        makeScreenshot("electronika");
        Thread.sleep(2000);


        System.out.println("Befor телевизоры");
        chromeSession.findElementByName("Искать товары").sendKeys("Телевизоры" + Keys.ENTER);
        Thread.sleep(6000);
        System.out.println("Before Samsung");
        chromeSession.findElementByXPath("//Text[@Name='Samsung']").click();
        System.out.println("Before LG");
        chromeSession.findElementByXPath("//Text[@Name='LG']").click();
        System.out.println("Before min price");
            // \"]/Group[@AutomationId=\"search-filters\"]/Edit[starts-with(@AutomationId,\"textfield\")][@Name=\"от 13 600\"]"
        chromeSession.findElementByXPath(
                "//Group[@AutomationId='search-filters']/Edit[contains(@Name,'от ')]")
                .sendKeys("20000");
        adrSearch.sendKeys(Keys.ENTER);
        makeScreenshot("TVs");

        System.out.println("Before teliki");
        // \"]/Group[@AutomationId=\"main\"][@Name=\"Результаты поиска\"]/Document[position()=49]"
        List<WebElement> teliks = chromeSession.findElementsByXPath(
                "//Text[contains(@Name,'Телевизор')]");
        System.out.println(teliks+" = "+teliks.size());
        // "//Tooltip[contains(@Name,'Телевизор')]");
        //        "//Group[@Name='Результаты поиска']/Document/Text[contains(@Name,'Телевизор']");
        // /Group[@AutomationId=\"main\"][@Name=\"Результаты поиска\"]/Document[position()=51]/Hyperlink[@Name=\"38 990 ₽\"]/Text[@Name=\"38 990\"]"
        // /Group[@AutomationId=\"main\"][@Name=\"Результаты поиска\"]/Document[position()=49]/Hyperlink[@Name=\"28 088 ₽\"]/Text[@Name=\"28 088\"]"
        // /Group[@AutomationId=\"main\"][@Name=\"Результаты поиска\"]/Document[position()=49]/Hyperlink[@Name=\"28 088 ₽\"]/Text[@Name=\"28 088\"]"
        // /Group[@AutomationId=\"main\"][@Name=\"Результаты поиска\"]/Document[position()=50]/Hyperlink[@Name=\"38 990 ₽\"]/Text[@Name=\"38 990\"]"
        System.out.println("Before prices");
        List<WebElement> prices = chromeSession
                .findElementsByXPath("//Document/Hyperlink/Group/Text[0]");
        System.out.println(prices);
        // "//Group[@Name='Результаты поиска']/Document/Hyperlink/Text");   [Contains(@Name,'₽')]

    }
    @Test
    public void choiceTVTest() throws InterruptedException, NullPointerException {
        WebElement adrSearch = chromeSession.findElementByName("Адресная строка и строка поиска");
        adrSearch.clear();
        adrSearch.sendKeys("market.yandex.ru/catalog--elektronika/54440");      // http://tkso.ru"
        adrSearch.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        System.out.println("Before Market");        // Возврат к HomePage
        chromeSession.findElementByAccessibilityId("logoPartMarket").click();
//        chromeSession.findElementByXPath("//Hyperlink[@AutomationId='logoPartMarket']").click();
        Thread.sleep(2000);
        makeScreenshot("market");

        // "/Pane[@Name=\"Рабочий стол 1\"][@ClassName=\"#32769\"]/Pane[@Name=\"Электроника — купить на Яндекс Маркете - Google Chrome\"][@ClassName=\"Chrome_WidgetWin_1\"]/Document[@Name=\"Электроника — купить на Яндекс Маркете\"][@ClassName=\"Chrome_RenderWidgetHostHWND\"]/Group[position()=2]/MenuItem[@AutomationId=\"catalogPopupButton\"][@Name=\"Каталог\"]"
        System.out.println("Before Каталог");
        chromeSession.findElementByXPath("//MenuItem[@Name='Каталог']").click();
        Thread.sleep(2000);
        makeScreenshot("catalog");

        System.out.println("Before Электроника");
        //        chrAct.moveToElement(chromeSession.findElementByXPath("//TabItem[@Name='Электроника']"))
        //                .build().perform();
//        chromeSession.findElementByXPath("//Hyperlink[@Name='Электроника']").click();
        WebElement el_nika = chromeSession.findElementByName("Электроника");
        chrAct.moveToElement(el_nika).build().perform();
        el_nika.click();
//        chrAct.doubleClick(el_nika).build().perform();
        makeScreenshot("electronika");
        Thread.sleep(2000);
    }


    private void makeScreenshot(String testName) {
        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yy");
        String sDate = ft.format(new Date());
        try {
            File screenshot = ((TakesScreenshot) chromeSession)
                    .getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("screenShots\\screenShot_"+testName+sDate+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return screenshotBytes;
    }

}