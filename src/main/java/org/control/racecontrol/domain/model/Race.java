package org.control.racecontrol.domain.model;

public class Race {
    private long id;
    private String name;
    private int totalLaps;

    public Race(String name, int totalLaps) {
        this.id = id;
        this.name = name;
        this.totalLaps = totalLaps;
    }

    public Race() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalLaps() {
        return totalLaps;
    }

    public void setTotalLaps(int totalLaps) {
        this.totalLaps = totalLaps;
    }
}
