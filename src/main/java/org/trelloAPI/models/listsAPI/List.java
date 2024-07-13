package org.trelloAPI.models.listsAPI;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

import static io.restassured.RestAssured.given;

public class List {
    @Getter
    private final String ID;
    @Getter
    private String name;
    @Getter
    private String parentBoardID;
    @Getter
    private Boolean isArchived;
    private final RequestSpecification requestSpecification;
    public List(RequestSpecification reqSpec, getListData listData) {
        this.ID = listData.getID();
        this.name = listData.getName();
        this.parentBoardID = listData.getParentBoardID();
        this.isArchived = listData.getIsArchived();
        this.requestSpecification = reqSpec;
    }

    public getListData getInfo(){
        return given().spec(requestSpecification)
                .pathParam("id",ID)
                .get("lists/{id}")
                .then().statusCode(200).extract().as(getListData.class);
    }
    public void changeName(String name){
        Response resp = given().log().all().spec(requestSpecification)
                .pathParam("id",ID)
                .queryParam("name",name)
                .put("lists/{id}");
        this.name = resp.then().statusCode(200).extract().jsonPath().getString("name");
    }

    public void archive(){
        Response resp = given().spec(requestSpecification)
                .pathParam("id", ID)
                .queryParam("closed", true)
                .put("lists/{id}");
        this.isArchived = resp.then().statusCode(200).extract().jsonPath().getBoolean("closed");
    }
    public void moveToBoard(String boardID){
        Response resp = given().spec(requestSpecification)
                .pathParam("id", ID)
                .queryParam("idBoard", boardID)
                .put("lists/{id}");
        this.parentBoardID = resp.then().statusCode(200).extract().jsonPath().getString("idBoard");
    }
}
