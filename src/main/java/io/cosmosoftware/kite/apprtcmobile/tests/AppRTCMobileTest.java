package io.cosmosoftware.kite.apprtcmobile.tests;

import io.cosmosoftware.kite.apprtcmobile.Coordinator;
import io.cosmosoftware.kite.apprtcmobile.checks.ParticipantLeftCheck;
import io.cosmosoftware.kite.apprtcmobile.pages.MainPage;
import io.cosmosoftware.kite.apprtcmobile.steps.ConnectClientStep;
import io.cosmosoftware.kite.apprtcmobile.checks.LocalVideoDisplayCheck;
import io.cosmosoftware.kite.apprtcmobile.steps.QuitApplicationStep;
import org.webrtc.kite.tests.KiteBaseTest;
import org.webrtc.kite.tests.TestRunner;

import java.util.Random;

public class AppRTCMobileTest extends KiteBaseTest {

    private final Random rand = new Random(System.currentTimeMillis());
    protected final String roomId = String.valueOf(Math.abs(rand.nextLong()));
    protected Coordinator coordinator;

    @Override
    protected void payloadHandling() {
        super.payloadHandling();
        this.coordinator = new Coordinator(this.tupleSize);
    }

    @Override
    protected void populateTestSteps(TestRunner testRunner) {
       MainPage mainPage = new MainPage(testRunner, payload.getJsonObject("coordinates"));
       testRunner.addStep(new ConnectClientStep(mainPage, testRunner, this.coordinator, this.roomId));
       testRunner.addStep(new LocalVideoDisplayCheck(mainPage, testRunner, this.coordinator));
      if (testRunner.getId() == 0) {
        testRunner.addStep(new ParticipantLeftCheck(mainPage, testRunner, this.coordinator));
      }
      testRunner.addStep(new QuitApplicationStep(mainPage, testRunner, this.coordinator));
    }
}
