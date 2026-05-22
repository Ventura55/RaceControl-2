package org.control.racecontrol.domain.model;

import java.util.List;

public class RaceDataSnapshot {
    private String raceName;
    private List<String> podiumPilots;
    private List<String> penalties;

    public RaceDataSnapshot(String raceName, List<String> podiumPilots, List<String> penalties) {
        this.raceName = raceName;
        this.podiumPilots = podiumPilots;
        this.penalties = penalties;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public List<String> getPodiumPilots() {
        return podiumPilots;
    }

    public void setPodiumPilots(List<String> podiumPilots) {
        this.podiumPilots = podiumPilots;
    }

    public List<String> getPenalties() {
        return penalties;
    }

    public void setPenalties(List<String> penalties) {
        this.penalties = penalties;
    }
}
