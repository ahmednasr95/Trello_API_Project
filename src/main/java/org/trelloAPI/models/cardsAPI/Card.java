package org.trelloAPI.models.cardsAPI;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import org.trelloAPI.models.listsAPI.getListData;

import static io.restassured.RestAssured.given;

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
    public getCardData getInfo(){
        return given().spec(requestSpecification)
                .pathParam("id",ID)
                .get("cards/{id}")
                .then().statusCode(200).extract().as(getCardData.class);
    }
    public void changeName(String name){
        Response resp = given().log().all().spec(requestSpecification)
                .pathParam("id",ID)
                .queryParam("name",name)
                .put("cards/{id}");
        this.name = resp.then().statusCode(200).extract().jsonPath().getString("name");
    }

    public void archive(){
        Response resp = given().spec(requestSpecification)
                .pathParam("id", ID)
                .queryParam("closed", true)
                .put("cards/{id}");
        this.isArchived = resp.then().statusCode(200).extract().jsonPath().getBoolean("closed");
    }
}
