package org.trelloAPI.models.boardsAPI;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Background {
    private final RequestSpecification requestSpecification;
    private final String boardID;
    protected String background;
    public Background(RequestSpecification reqSpec, String boardID) {
        this.requestSpecification = reqSpec;
        this.boardID = boardID;
    }
    public void changeColor(boardBackgroundColor color){
        given().spec(requestSpecification)
                .pathParam("id",boardID)
                .queryParam("prefs/background",color)
                .put("/boards/{id}")
                .then().statusCode(200);
    }
    public String getBackground(){
        return given().spec(requestSpecification)
                .pathParam("id",boardID)
                .get("/boards/{id}")
                .then().statusCode(200)
                .extract().jsonPath().getString("prefs.background");
    }
}
