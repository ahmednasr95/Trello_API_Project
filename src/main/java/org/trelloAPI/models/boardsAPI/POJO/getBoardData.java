package org.trelloAPI.models.boardsAPI.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class getBoardData {
    @JsonProperty("id")
    private String boardID;
    @JsonProperty("name")
    private String boardName;
    @JsonProperty("prefs")
    private org.trelloAPI.models.boardsAPI.POJO.boardPrefs boardPrefs;
    private org.trelloAPI.models.boardsAPI.POJO.labelNames labelNames;
    private String powerUps;
    @JsonProperty("message")
    private String errorMessage;
}
