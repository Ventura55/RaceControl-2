package org.control.racecontrol.domain.model;

import java.util.ArrayList;
import java.util.List;

public class FantasyRoster {
    private Long id;
    private String username;
    private List<Integer> driverIds = new ArrayList<>();

    public FantasyRoster(Long id, String username, List<Integer> driverIds) {
        this.id = id;
        this.username = username;
        this.driverIds = driverIds;
    }

    public FantasyRoster() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Integer> getDriverIds() {
        return driverIds;
    }

    public void setDriverIds(List<Integer> driverIds) {
        this.driverIds = driverIds;
    }
}
