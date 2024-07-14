package org.trelloAPI.models.boardsAPI;

import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import org.trelloAPI.models.statusCode;

import static io.restassured.RestAssured.given;

public class Board {
    @Getter
    private final String ID;
    @Getter
    private final String name;
    @Getter
    private final String boardCreatorID;
    @Getter
    private final String memberships;
    @Getter
    private final String powerUps;
    public Background background;
    public Label redLabel;
    public Label greenLabel;
    RequestSpecification requestSpecification;
    public Board(RequestSpecification reqSpec, getBoardData boardData) {
        this.requestSpecification = reqSpec;
        this.ID = boardData.getBoardID();
        this.name = boardData.getBoardName();
        this.boardCreatorID = boardData.getBoardCreatorID();
        this.memberships = boardData.getMemberships();
        this.powerUps = boardData.getPowerUps();
        background = new Background(reqSpec, this.ID);
        background.background = boardData.getBoardPrefs().getBoardBackground();
        redLabel = new Label(reqSpec, this.ID, "red");
        redLabel.name = boardData.getLabelNames().getRedLabelName();
        greenLabel = new Label(reqSpec, this.ID, "green");
        greenLabel.name = boardData.getLabelNames().getGreenLabelName();
    }
    public getBoardData getInfo(){
        return given().spec(requestSpecification)
                .pathParam("id", ID)
                .get("boards/{id}")
                .then().statusCode(200).extract().as(getBoardData.class);
    }

    public void delete(){
        given().spec(requestSpecification)
                .pathParam("id",ID)
                .delete("boards/{id}")
                .then().statusCode(200);
    }

    public void addMember(String memberID, memberType type){
        given().spec(requestSpecification)
                .pathParam("id", ID)
                .pathParam("idMmeber", memberID)
                .queryParam("type", type)
                .put("boards/{id}/members/{idMemeber}")
                .then().statusCode(200);
    }

    public void updateMemberType(String memberID, memberType newType){
        given().spec(requestSpecification)
                .pathParam("id", ID)
                .pathParam("idMmeber", memberID)
                .queryParam("type", newType)
                .put("boards/{id}/memberships/{idMemeber}")
                .then().statusCode(200);
    }

    public void deleteMember(String memberID){
        
    }
}
