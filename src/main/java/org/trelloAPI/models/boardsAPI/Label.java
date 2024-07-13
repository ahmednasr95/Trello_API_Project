package org.trelloAPI.models.boardsAPI;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;


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
        name = newName;
        requestSpecification
                .pathParam("id",boardID)
                .queryParam(STR."labelNames/\{color}",name)
                .contentType(ContentType.JSON)
                .put("/boards/{id}")
                .then().statusCode(200);
    }
}
