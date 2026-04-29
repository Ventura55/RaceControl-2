package org.control.racecontrol.domain.model;

public class RaceResultResponse {
    private final RaceResult raceResult;
    private final Driver driver;
    private final Team team;

    public RaceResultResponse(RaceResult raceResult, Driver driver, Team team) {
        this.raceResult = raceResult;
        this.driver = driver;
        this.team = team;
    }

    public RaceResult getRaceResult() {
        return raceResult;
    }

    public Driver getDriver() {
        return driver;
    }

    public Team getTeam() {
        return team;
    }
}
