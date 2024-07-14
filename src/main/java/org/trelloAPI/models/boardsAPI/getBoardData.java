package org.trelloAPI.models.boardsAPI;

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
    @JsonProperty("idMemberCreator")
    private String boardCreatorID;
    @JsonProperty("prefs")
    private boardPrefs boardPrefs;
    private labelNames labelNames;
    private String memberships;
    private String powerUps;
    @JsonProperty("message")
    private String errorMessage;
}
