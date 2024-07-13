package org.trelloAPI.services;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.trelloAPI.models.boardsAPI.Board;
import org.trelloAPI.models.boardsAPI.createBoardData;
import org.trelloAPI.models.boardsAPI.getBoardData;
import org.trelloAPI.models.listsAPI.List;
import org.trelloAPI.models.listsAPI.getListData;

import static org.trelloAPI.services.APIManager.userReqSpecs;

public class BoardsManager {
    public static Board createNewBoard(createBoardData boardRequestData){
        Response resp = userReqSpecs
                .body(boardRequestData)
                .contentType(ContentType.JSON)
                .post("boards");
        return new Board(userReqSpecs, resp.then().statusCode(200).extract().as(getBoardData.class));
    }
    public static List createNewList(String listName, String parentBoardID){
        Response resp = userReqSpecs
                .queryParam("name", listName)
                .queryParam("idBoard", parentBoardID)
                .contentType(ContentType.JSON)
                .post("lists");
        return new List(userReqSpecs, resp.then().statusCode(200).extract().as(getListData.class));
    }
}
