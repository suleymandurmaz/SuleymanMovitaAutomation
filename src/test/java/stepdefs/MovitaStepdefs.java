package stepdefs;


import Base.BaseMovita;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pageObjects.Mov_10;

import java.util.Map;
import java.util.Set;

public class MovitaStepdefs extends BaseMovita {

    @Given("users navigate to {string}")
    public void usersNavigateTo(String url) {
        driver.get(url);
    }

    @When("users clicked the movita logo")
    public void usersClickedTheMovitaLogo() {
        click(llogo);
    }

    @And("Mobile Vehicle Tracking System shoould be visible")
    public void mobileVehicleTrackingSystemShoouldBeVisible() {
        visible(lMobilVasıtaTurkish);
    }

    @Then("should be success")
    public void shouldBeSuccess() {
        getScreenshot("Mobil Vasıta");
    }

    @Given("user clicks the enter button")
    public void userClicksTheEnterButton() {
        click(lEnterButton);
    }

    @And("user clicks the Turkish language button")
    public void userClicksTheTurkishLanguageButton() {
        click(lTurkishLang);
    }

    @And("user String verify Türkce → “Mobil Vasita izleme takip Sistemi” yazisini verify et")
    public void userStringVerifyTürkceMobilVasitaIzlemeTakipSistemiYazisiniVerifyEt() {
        visible(lMobilVasıtaTurkish);
    }

    @And("user clicks the english language button")
    public void userClicksTheEnglishLanguageButton() {
        click(lEnglishLang);
    }

    @And("user String verify English → “Mobile Vehicle Tracking System” yazisini verify et")
    public void userStringVerifyEnglishMobileVehicleTrackingSystemYazisiniVerifyEt() {
        visible(lMobilVasıtaEnglish);
        getScreenshot("Mobile Vehicle Tracking System");
    }

    @When("user hover over the homepage title and title color should be changeable")
    public void userHoverOverTheHomepageTitleAndTitleColorShouldBeChangeable() {

        WebElement element = driver.findElement(lAnaSayfa);
        String beforeColor = element.getCssValue("color");
        hoverAll(lAnaSayfa);
        String afterColor = element.getCssValue("color");
        Assert.assertNotEquals(beforeColor, afterColor);
    }

    @And("home title should be clickable")
    public void homeTitleShouldBeClickable() {
        click(lAnaSayfa);
    }

    @When("users hover over on the {string}")
    public void usersHoverOverOnThe(String mainTitle) {
        hoverOver(homePageMenu(mainTitle));
    }

    @And("hover over the  {string}")
    public void hoverOverThe(String subTitle) {
        hoverOver(MainSubtitle(subTitle));
    }

    @And("The user {string} the subTitle")
    public void theUserTheSubTitle(String text) {
        click(MainSubtitle(text));
    }

    @And("The {string} and{string} colours should be change")
    public void theAndColoursShouldBeChange(String mainTitle, String subTitle) {
        assertChangeColorMainTitle(mainTitle);
        assertChangeColorSubTitle(subTitle);
    }

    @Then("verify the subtitle with {string}")
    public void verifyTheSubtitleWith(String text) {
        visibleVerifyWithSubtitle(text);
    }

    @When("user clicks the login button")
    public void userClicksTheLoginButton() {
        click(homePageMenu("GİRİŞ Yap"));
    }

    @And("user sends <username> and <password>")
    public void userSendsUsernameAndPassword(DataTable table) {
        Map<String, String> map = table.asMap();
        String username = map.get("username");
        String password = map.get("password");
        sendKeys(Mov_10.loginFormİnput("username"), username);
        sendKeys(Mov_10.loginFormİnput("password"), password);

    }

    @And("user clicks login form login button")
    public void userClicksLoginFormLoginButton() {
        click(lLoginFormSubmitButton);
    }


    @And("verify with map")
    public void verifyWithMap() {
        visible(Mov_10.lVerifyMap);
    }

    @And("user click raporlar menu")
    public void userClickRaporlarMenu() {
        click(Mov_10.lRaporlarMenu);
    }

    @And("user clicks the base tool report")
    public void userClicksTheBaseToolReport() {
        click(Mov_10.raporlarSubMenu("Araç Bazlı Rapor"));

    }

    @And("user clicks the detailed filter")
    public void userClicksTheDetailedFilter() {
        click(LDetaylıFiltre);
        bekle(500);
    }

    @And("user sends the detailed filter of sub menu")
    public void userSendsTheDetailedFilterOfSubMenu(DataTable table) {
        Map<String, String> map = table.asMap();
        sendKeys(Mov_10.detailedFilterSubMenu(map.get("tarih")), "2023.05.08");
        sendKeys(Mov_10.detailedFilterSubMenu(map.get("is baslama")), "2023.05.08");
        sendKeys(Mov_10.detailedFilterSubMenu(map.get("is bitis")), "2023.05.08");
        sendKeys(Mov_10.detailedFilterSubMenu(map.get("tarih")), "Mayıs");
        getScreenshot("tarih");
        sendKeys(Mov_10.detailedFilterSubMenu(map.get("is baslama")), "Haziran");
        getScreenshot("İşe Başlama");
        sendKeys(Mov_10.detailedFilterSubMenu(map.get("is bitis")), "Temmuz");
        getScreenshot("İş Bitiş");
    }


    @And("user clicks the result of the report")
    public void userClicksTheResultOfTheReport(DataTable table) {
        Map<String, String> map = table.asMap();

        click(Mov_10.resultOfReportWith(map.get("excel")));
        getScreenshot("exel");
        bekle(500);
        click(Mov_10.resultOfReportWith(map.get("pdf")));
        getScreenshot("pdf");
        bekle(500);
        click(Mov_10.resultOfReportWith(map.get("yazıcı")));
        getScreenshot("yazıcı");
        String mainWin = driver.getWindowHandle();
        Set<String> wins = driver.getWindowHandles();

        for (String win : wins) {
            if (!win.equalsIgnoreCase(mainWin)) {
                driver.switchTo().window(win);
                break;
            }
        }

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.TAB)
                .sendKeys(Keys.ENTER)
                .build();
        actions.perform();

        driver.switchTo().window(mainWin);


    }

    @And("user clicks form group of vehicle")
    public void userClicksFormGroupOfVehicle() {
        click(Mov_10.lAracDropDownMenu);

    }

    @And("user select vehicle")
    public void userSelectVehicle() {
        click(Mov_10.aracSelect("OFISTESTPRO"));
        click(Mov_10.lAracDropDownMenu);
    }


    @And("user clicks form group of start of date")
    public void userClicksFormGroupOfStartOfDate() {
        bekle(500);
        click(Mov_10.selectDateMenu("tarih1"));

    }


    @And("user select start of date")
    public void userSelectStartOfDate() {

        Select select = new Select(Mov_10.selectMonth());
       select.selectByValue("9");
        bekle(200);

        Select select1 = new Select(Mov_10.selectYear());
        select1.selectByValue("2023");
        bekle(200);

        click(By.xpath("//td[@class='available' and text()='9']"));
        bekle(1000);
    }

    @And("user clicks form group of finish of date")
    public void userClicksFormGroupOfFinishOfDate() {
        bekle(500);
        click(Mov_10.selectDateMenu("tarih2"));


    }
    @And("user select finish of date")
    public void userSelectFinishOfDate() {

        Select select=new Select(Mov_10.selectMonth(3));
        select.selectByValue("10");
        bekle(200);

        Select select1=new Select(Mov_10.selectYear());
        select1.selectByValue("2023");
        bekle(200);
        click(By.xpath("(//td[@class='available' and text()='10'])[3]"));
        bekle(1000);
    }

    @And("user clicks on create report")
    public void userClicksOnCreateReport() {
        click(Mov_10.raporlarSubMenu("Rapor Al"));
    }
}













