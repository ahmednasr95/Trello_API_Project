package org.trelloAPI.models.boardsAPI;

import io.restassured.specification.RequestSpecification;
import lombok.Getter;

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
        return requestSpecification
                .body("")
                .pathParam("id", ID)
                .get("boards/{id}")
                .then().statusCode(200).extract().as(getBoardData.class);
    }

    public void delete(){
        requestSpecification
                .body("")
                .pathParam("id",ID)
                .delete("boards/{id}")
                .then().statusCode(200);
    }
}
