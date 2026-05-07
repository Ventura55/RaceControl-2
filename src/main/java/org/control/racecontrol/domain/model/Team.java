package org.control.racecontrol.domain.model;

public class Team {
    // Esto es un comentario
    private long id;
    private String name;
    private String engineSupplier;

    public Team(String name, String engineSupplier) {
        this.id = id;
        this.name = name;
        this.engineSupplier = engineSupplier;
    }

    public Team() {

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

    public String getEngineSupplier() {
        return engineSupplier;
    }

    public void setEngineSupplier(String engineSupplier) {
        this.engineSupplier = engineSupplier;
    }
}
