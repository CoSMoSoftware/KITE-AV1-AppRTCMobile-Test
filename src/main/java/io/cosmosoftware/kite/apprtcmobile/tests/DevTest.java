package io.cosmosoftware.kite.apprtcmobile.tests;

import static io.cosmosoftware.kite.util.TestUtils.readJsonFile;
import io.cosmosoftware.kite.exception.KiteTestException;
import java.net.MalformedURLException;
import org.openqa.selenium.WebDriver;
import org.webrtc.kite.WebDriverFactory;
import org.webrtc.kite.config.client.Client;
import org.webrtc.kite.config.paas.Paas;


public class DevTest {

    public static void main(String[] args) throws MalformedURLException, KiteTestException {
        Client client = new Client(readJsonFile("\\GitHub\\KITE-AppRTCMobile-Test\\configs\\dev.config.json")
                .getJsonArray("clients").getJsonObject(0));
        client.setPaas(new Paas("http://192.168.1.133:4444/wd/hub"));
        WebDriver driver = WebDriverFactory.createWebDriver(client, "", "");
        System.out.println("Created");
        driver.quit();
    }
}
