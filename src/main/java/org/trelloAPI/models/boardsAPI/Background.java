package org.trelloAPI.models.boardsAPI;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Background {
    private final RequestSpecification requestSpecification;
    private final String boardID;
    protected String background;
    public Background(RequestSpecification reqSpec, String boardID) {
        this.requestSpecification = reqSpec;
        this.boardID = boardID;
    }
    public void changeColor(boardBackgroundColor color){
        requestSpecification
                .pathParam("id",boardID)
                .queryParam("prefs/background",color)
                .contentType(ContentType.JSON)
                .put("/boards/{id}")
                .then().statusCode(200);
    }
    public String getBackground(){
        return requestSpecification
                .body("")
                .pathParam("id",boardID)
                .get("/boards/{id}")
                .then().statusCode(200)
                .extract().body().jsonPath().getString("prefs.background");
    }
}
