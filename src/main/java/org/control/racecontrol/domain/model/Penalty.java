package org.control.racecontrol.domain.model;

public class Penalty {
    private long id;
    private long idRaceResult;
    private int pentaltySeconds;
    private String reason;

    public Penalty(long id, long idRaceResult, int pentaltySeconds, String reason) {
        this.id = id;
        this.idRaceResult = idRaceResult;
        this.pentaltySeconds = pentaltySeconds;
        this.reason = reason;
    }

    public Penalty() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdRaceResult() {
        return idRaceResult;
    }

    public void setIdRaceResult(long idRaceResult) {
        this.idRaceResult = idRaceResult;
    }

    public int getPentaltySeconds() {
        return pentaltySeconds;
    }

    public void setPentaltySeconds(int pentaltySeconds) {
        this.pentaltySeconds = pentaltySeconds;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
