package io.cosmosoftware.kite.apprtcmobile;

import io.cosmosoftware.kite.interfaces.Runner;

import java.util.*;

public class Coordinator {

    private final int participantCount;
    private HashMap<Runner, String> participantMap = new HashMap<>();
    private List<String> participants = new ArrayList<>();
    private int checkPassed = 0;
    public Coordinator(int participantCount) {
        this.participantCount = participantCount;
    }

    public synchronized boolean allParticipantsConnected() {
        return this.participants.size() == this.participantCount;
    }

    public synchronized boolean allParticipantsFinishedCheck() {
            return checkPassed == this.participantCount;
    }

    public synchronized int getParticipantSize() { return this.participants.size(); }

    public synchronized void addParticipant(Runner runner, String id) {
        this.participantMap.put(runner, id);
        this.participants.add(id);
    }
    public synchronized  void removeParticipant(Runner runner, String id) {
        this.participantMap.remove(runner, id);
        this.participants.remove(id);
    }

    public void addToCheckPassed() {
        this.checkPassed +=1;
    }

}
