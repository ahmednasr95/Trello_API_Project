package org.trelloAPI.models.cardsAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import org.trelloAPI.models.listsAPI.getListData;

public class Card {
    @Getter
    private String ID;
    @Getter
    private Boolean isArchived;
    @Getter
    private String parentBoardID;
    @Getter
    private String parentListID;
    @Getter
    private String[] membersID;
    @Getter
    private String name;
    RequestSpecification requestSpecification;
    public Card(RequestSpecification reqSpec, getCardData cardData) {
        this.ID = cardData.getID();
        this.isArchived = cardData.getIsArchived();
        this.parentBoardID = cardData.getParentBoardID();
        this.parentListID = cardData.getParentListID();
        this.membersID = cardData.getMembersID();
        this.name = cardData.getName();
        this.requestSpecification = reqSpec;
    }
}
