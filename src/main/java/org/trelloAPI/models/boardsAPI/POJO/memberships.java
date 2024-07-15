package org.trelloAPI.models.boardsAPI.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.trelloAPI.models.Enums.memberType;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class memberships {
    @JsonProperty("idMember")
    private String ID;
    @JsonProperty("id")
    private String membershipID;
    @JsonProperty("memberType")
    private memberType type;
}
