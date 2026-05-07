package org.control.racecontrol.domain.model;

public class Driver {
    private int id;
    private String name;
    private long idTeam;

    public Driver(String name, long idTeam) {
        this.id = id;
        this.name = name;
        this.idTeam = idTeam;
    }

    public Driver() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(long idTeam) {
        this.idTeam = idTeam;
    }
}
