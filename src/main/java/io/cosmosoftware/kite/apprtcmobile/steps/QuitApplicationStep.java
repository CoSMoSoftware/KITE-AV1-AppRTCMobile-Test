package io.cosmosoftware.kite.apprtcmobile.steps;

import io.cosmosoftware.kite.exception.KiteTestException;
import io.cosmosoftware.kite.apprtcmobile.Coordinator;
import io.cosmosoftware.kite.apprtcmobile.pages.MainPage;
import io.cosmosoftware.kite.interfaces.Runner;
import io.cosmosoftware.kite.report.Status;
import io.cosmosoftware.kite.steps.TestStep;
import org.webrtc.kite.tests.TestRunner;

import static io.cosmosoftware.kite.entities.Timeouts.DEFAULT_TIMEOUT;
import static io.cosmosoftware.kite.entities.Timeouts.ONE_SECOND_INTERVAL;
import static io.cosmosoftware.kite.util.TestUtils.waitAround;

public class QuitApplicationStep extends TestStep {

    private final MainPage mainPage;
    private final Coordinator coordinator;
    private final Runner runner;

    public QuitApplicationStep(MainPage mainPage, Runner runner, Coordinator coordinator) {
        super(runner);
        this.runner = runner;
        this.mainPage = mainPage;
        this.coordinator = coordinator;
    }

    @Override
    protected void step() throws KiteTestException {
        for (int elapsed = 0; elapsed < DEFAULT_TIMEOUT; elapsed += ONE_SECOND_INTERVAL) {
          if (this.coordinator.allParticipantsFinishedCheck()) {
            if (mainPage.quitApplication()) {
              this.coordinator.removeParticipant(
                  this.runner, Integer.toString(((TestRunner) this.runner).getId()));
              return;
            } else {
              throw new KiteTestException("There was a problem quitting the application", Status.BROKEN);
            }
          }
          waitAround(ONE_SECOND_INTERVAL);
        }
        throw new KiteTestException("There was a problem quitting the application before timeout", Status.BROKEN);
    }

    @Override
    public String stepDescription() {
        return "Quit application";
    }

}
