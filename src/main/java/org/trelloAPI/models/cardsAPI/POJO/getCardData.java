package org.trelloAPI.models.cardsAPI.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class getCardData {
    @JsonProperty("id")
    private String ID;
    @JsonProperty("closed")
    private Boolean isArchived;
    @JsonProperty("idBoard")
    private String parentBoardID;
    @JsonProperty("idList")
    private String parentListID;
    @JsonProperty("idMembers")
    private String[] membersID;
    private String name;
}
