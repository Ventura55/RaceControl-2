package org.control.racecontrol.domain.model;

public class RaceInsight {
    private String raceId;
    private String aiSummary;
    private String criticalNote;
    private boolean isFallback;

    public RaceInsight(String raceId, String aiSummary, String criticalNote, boolean isFallback) {
        this.raceId = raceId;
        this.aiSummary = aiSummary;
        this.criticalNote = criticalNote;
        this.isFallback = isFallback;
    }

    public RaceInsight() {
    }

    public String getRaceId() {
        return raceId;
    }

    public void setRaceId(String raceId) {
        this.raceId = raceId;
    }

    public String getAiSummary() {
        return aiSummary;
    }

    public void setAiSummary(String aiSummary) {
        this.aiSummary = aiSummary;
    }

    public String getCriticalNote() {
        return criticalNote;
    }

    public void setCriticalNote(String criticalNote) {
        this.criticalNote = criticalNote;
    }

    public boolean isFallback() {
        return isFallback;
    }

    public void setFallback(boolean fallback) {
        isFallback = fallback;
    }
}
