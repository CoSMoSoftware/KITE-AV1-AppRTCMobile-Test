package io.cosmosoftware.kite.apprtcmobile.steps;

import io.cosmosoftware.kite.exception.KiteTestException;
import io.cosmosoftware.kite.apprtcmobile.Coordinator;
import io.cosmosoftware.kite.apprtcmobile.pages.MainPage;
import io.cosmosoftware.kite.interfaces.Runner;
import io.cosmosoftware.kite.steps.TestStep;
import org.webrtc.kite.tests.TestRunner;

public class ConnectClientStep extends TestStep {

    private final MainPage mainPage;
    private final String roomId;
    private final Coordinator coordinator;
    private final Runner runner;

    public ConnectClientStep(MainPage mainPage, Runner runner, Coordinator coordinator, String roomId) {
        super(runner);
        this.runner = runner;
        this.mainPage = mainPage;
        this.coordinator = coordinator;
        this.roomId = roomId;
    }

    @Override
    protected void step() throws KiteTestException {
        mainPage.setRoom(this.roomId);
        mainPage.joinRoom();
        this.coordinator.addParticipant(this.runner, Integer.toString(((TestRunner)this.runner).getId()));
    }

    @Override
    public String stepDescription() {
        return "Connect client to server";
    }

}
