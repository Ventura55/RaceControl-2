package org.control.racecontrol.domain.model;

public class RaceResult {
    private long id;
    private long idRace;
    private int idDriver;
    private int gridPosition;
    private int finalPosition;
    private boolean fastestLap;
    private int points;
    private Status status;

    public enum Status {
        FINISHED, DNF, DSQ
    }

    public RaceResult(long id, long idRace, int idDriver, int gridPosition, int finalPosition, boolean fastestLap, int points, Status status) {
        this.id = id;
        this.idRace = idRace;
        this.idDriver = idDriver;
        this.gridPosition = gridPosition;
        this.finalPosition = finalPosition;
        this.fastestLap = fastestLap;
        this.points = points;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdRace() {
        return idRace;
    }

    public void setIdRace(long idRace) {
        this.idRace = idRace;
    }

    public int getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(int idDriver) {
        this.idDriver = idDriver;
    }

    public int getGridPosition() {
        return gridPosition;
    }

    public void setGridPosition(int gridPosition) {
        this.gridPosition = gridPosition;
    }

    public int getFinalPosition() {
        return finalPosition;
    }

    public void setFinalPosition(int finalPosition) {
        this.finalPosition = finalPosition;
    }

    public boolean isFastestLap() {
        return fastestLap;
    }

    public void setFastestLap(boolean fastestLap) {
        this.fastestLap = fastestLap;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
