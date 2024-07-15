package org.trelloAPI.models.boardsAPI.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class createBoardData {
    @JsonProperty("name")
    private String boardName;
    @JsonProperty("prefs_background")
    private String boardBackground;
    private String powerUps;
}
