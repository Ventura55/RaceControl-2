package org.control.racecontrol.domain.model;

public class RaceResult {
    private Long id;
    private long idRace;
    private int idDriver;
    private Integer gridPosition;
    private int finalPosition;
    private boolean fastestLap;
    private int points;
    private Status status;

    public RaceResult() {

    }

    public enum Status {
        FINISHED, DNF, DSQ
    }

    public RaceResult(Long id, long idRace, int idDriver, Integer gridPosition, int finalPosition, boolean fastestLap, int points, Status status) {
        this.id = id;
        this.idRace = idRace;
        this.idDriver = idDriver;
        this.gridPosition = gridPosition;
        this.finalPosition = finalPosition;
        this.fastestLap = fastestLap;
        this.points = points;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getGridPosition() {
        return gridPosition;
    }

    public void setGridPosition(Integer gridPosition) {
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
