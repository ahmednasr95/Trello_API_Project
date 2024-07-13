package org.trelloAPI.models.listsAPI;

import io.restassured.specification.RequestSpecification;
import lombok.Getter;

public class List {
    @Getter
    private final String ID;
    @Getter
    private String name;
    @Getter
    private String parentBoardID;
    private final RequestSpecification requestSpecification;
    public List(RequestSpecification reqSpec, getListData listData) {
        this.ID = listData.getID();
        this.name = listData.getName();
        this.parentBoardID = listData.getParentBoardID();
        this.requestSpecification = reqSpec;
    }

    public getListData getInfo(){
        return requestSpecification
                .body("")
                .pathParam("id",ID)
                .get("lists/{id}")
                .then().statusCode(200).extract().as(getListData.class);
    }
    public void changeName(String name){
        requestSpecification
                .body("")
                .pathParam("id",ID)
                .queryParam("name",name)
                .put("lists/{id}")
                .then().statusCode(200);
        this.name = name;
    }

    public void archive(){
        requestSpecification
                .body("")
                .pathParam("id", ID)
                .queryParam("closed", true)
                .put("lists/{id}")
                .then().statusCode(200);
    }
    public void moveToBoard(String boardID){
        requestSpecification
                .body("")
                .pathParam("id", ID)
                .queryParam("idBoard", boardID)
                .put("lists/{id}")
                .then().statusCode(200);
        this.parentBoardID = boardID;
    }

    public void delete(){
        requestSpecification
                .body("")
                .pathParam("id", ID)
                .delete("lists/{id}")
                .then().statusCode(200);
    }
}
