package org.trelloAPI.models.listsAPI.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class getListData {
    @JsonProperty("id")
    private String ID;
    private String name;
    @JsonProperty("closed")
    private Boolean isArchived;
    @JsonProperty("idBoard")
    private String parentBoardID;
}
