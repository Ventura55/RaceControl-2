package org.control.racecontrol.infrastructure.input.rest.dto.request;

import java.util.List;

public class GeminiRequest {
    private List<Content> contents;

    private List<SafetySetting> safetySettings;

    public GeminiRequest() {}

    public GeminiRequest(List<Content> contents) {
        this.contents = contents;
    }

    public GeminiRequest(List<Content> contents, List<SafetySetting> safetySettings) {
        this.contents = contents;
        this.safetySettings = safetySettings;
    }

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

    public static class Content {
        private List<Part> parts;

        public Content() {}

        public Content(List<Part> parts) {
            this.parts = parts;
        }

        public List<Part> getParts() {
            return parts;
        }

        public void setParts(List<Part> parts) {
            this.parts = parts;
        }
    }

    public static class Part {
        private String text;

        public Part() {}

        public Part(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static class SafetySetting {
        private String category;
        private String threshold;

        public SafetySetting() {}

        public SafetySetting(String category, String threshold) {
            this.category = category;
            this.threshold = threshold;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getThreshold() {
            return threshold;
        }

        public void setThreshold(String threshold) {
            this.threshold = threshold;
        }
    }
}
