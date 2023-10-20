package com.aircjm.java.base.markdown;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ProseMirrorVO {


    @JsonProperty("type")
    private String type;
    @JsonProperty("content")
    private List<Content> content;

    @NoArgsConstructor
    @Data
    public static class Content {
        @JsonProperty("type")
        private String type;
        @JsonProperty("content")
        private List<ContentDTO> content;
    }

    @NoArgsConstructor
    @Data
    public static class ContentDTO {
        @JsonProperty("type")
        private String type;
        @JsonProperty("text")
        private String text;
        @JsonProperty("marks")
        private List<MarksDTO> marks;
    }


    @NoArgsConstructor
    @Data
    public static class MarksDTO {
        @JsonProperty("type")
        private String type;
    }
}
