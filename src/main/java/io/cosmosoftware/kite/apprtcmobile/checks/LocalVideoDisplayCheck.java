package io.cosmosoftware.kite.apprtcmobile.checks;

import io.cosmosoftware.kite.exception.KiteTestException;
import io.cosmosoftware.kite.interfaces.Runner;
import io.cosmosoftware.kite.apprtcmobile.Coordinator;
import io.cosmosoftware.kite.apprtcmobile.pages.MainPage;
import io.cosmosoftware.kite.report.Status;
import io.cosmosoftware.kite.steps.TestStep;
import io.cosmosoftware.kite.util.ReportUtils;

import static io.cosmosoftware.kite.entities.Timeouts.DEFAULT_TIMEOUT;
import static io.cosmosoftware.kite.entities.Timeouts.ONE_SECOND_INTERVAL;
import static io.cosmosoftware.kite.util.TestUtils.*;

public class LocalVideoDisplayCheck extends TestStep {
    private final Coordinator coordinator;
    private final MainPage mainPage;
    public LocalVideoDisplayCheck(MainPage mainPage, Runner runner, Coordinator coordinator) {
        super(runner);
        this.mainPage = mainPage;
        this.coordinator = coordinator;
    }

  @Override
  protected void step() throws KiteTestException {
        try {
          for (int elapsed = 0; elapsed < DEFAULT_TIMEOUT; elapsed += ONE_SECOND_INTERVAL) {
            if (coordinator.allParticipantsConnected()) {
              waitAround(4000);
              String videoCheck = videoCheckByBytes(webDriver, this.mainPage.getRemoteVideoRectangle());
              this.reporter.screenshotAttachment(
                  this.report,
                  this.getClassName(),
                  ReportUtils.saveScreenshotPNG(
                      this.webDriver, this.mainPage.getRemoteVideoRectangle()));
              this.reporter.textAttachment(this.report, "Video Check", videoCheck, "plain");
              this.coordinator.addToCheckPassed();
              return;
            }
            waitAround(ONE_SECOND_INTERVAL);
          }
        } catch (Exception e) {
            this.coordinator.addToCheckPassed();
            throw e;
        }
      this.coordinator.addToCheckPassed();
      throw new KiteTestException("Some participants failed to connect to the room before timeout", Status.FAILED);
  }

    @Override
    public String stepDescription() {
        return "Video checking";
    }
}

