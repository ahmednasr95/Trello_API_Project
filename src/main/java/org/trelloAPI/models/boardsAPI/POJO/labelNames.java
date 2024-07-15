package org.trelloAPI.models.boardsAPI.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class labelNames {
    @JsonProperty("green")
    protected String greenLabelName;
    @JsonProperty("red")
    protected String redLabelName;
}
