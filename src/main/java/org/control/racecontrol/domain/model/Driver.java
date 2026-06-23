package org.control.racecontrol.domain.model;

import java.math.BigDecimal;

public class Driver {
    private int id;
    private String name;
    private long idTeam;
    private BigDecimal marketValue;

    public Driver(String name, long idTeam, BigDecimal marketValue) {
        this.id = id;
        this.name = name;
        this.idTeam = idTeam;
        this.marketValue = marketValue;
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

    public BigDecimal getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
    }
}
