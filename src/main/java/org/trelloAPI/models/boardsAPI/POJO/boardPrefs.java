package org.trelloAPI.models.boardsAPI.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class boardPrefs {
    @JsonProperty("background")
    protected String boardBackground;
    protected String invitations;
}
