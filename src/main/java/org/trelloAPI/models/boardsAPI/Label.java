package org.trelloAPI.models.boardsAPI;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;

import static io.restassured.RestAssured.given;


public class Label {
    @Getter
    @Setter
    protected String name;
    private String color;
    RequestSpecification requestSpecification;
    String boardID;
    public Label(RequestSpecification reqSpec, String boardID, String color) {
        this.requestSpecification = reqSpec;
        this.boardID = boardID;
        this.color = color;
    }
    public void changeName(String newName){
        Response resp = given().spec(requestSpecification)
                .pathParam("id",boardID)
                .queryParam(STR."labelNames/\{color}",newName)
                .put("/boards/{id}");
        this.name = resp.then().statusCode(200).extract().jsonPath().getString("name");

    }
}
