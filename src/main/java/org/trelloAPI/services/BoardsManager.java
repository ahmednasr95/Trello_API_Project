package org.trelloAPI.services;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.trelloAPI.models.boardsAPI.Board;
import org.trelloAPI.models.boardsAPI.createBoardData;
import org.trelloAPI.models.boardsAPI.getBoardData;
import org.trelloAPI.models.cardsAPI.Card;
import org.trelloAPI.models.cardsAPI.getCardData;
import org.trelloAPI.models.listsAPI.List;
import org.trelloAPI.models.listsAPI.getListData;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.patch;
import static org.trelloAPI.services.APIManager.userReqSpecs;

public class BoardsManager {
    public static Board createNewBoard(createBoardData boardRequestData){
        Response resp = given().spec(userReqSpecs)
                .body(boardRequestData)
                .contentType(ContentType.JSON)
                .post("boards");
        return new Board(userReqSpecs, resp.then().statusCode(200).extract().as(getBoardData.class));
    }
    public static List createNewList(String listName, String parentBoardID){
        Response resp = given().spec(userReqSpecs)
                .body(STR."""
                        {
                            "name":"\{listName}",
                            "idBoard":"\{parentBoardID}"
                        }
                        """)
                .contentType(ContentType.JSON)
                .post("lists");
        return new List(userReqSpecs, resp.then().statusCode(200).extract().as(getListData.class));
    }
    public static Card createNewCard(String cardName, String parentListID){
        Response resp = given().spec(userReqSpecs)
                .body(STR."""
                        {
                            "name":"\{cardName}",
                            "idBoard":"\{parentListID}"
                        }
                        """)
                .contentType(ContentType.JSON)
                .post("cards");
        return new Card(userReqSpecs, resp.then().statusCode(200).extract().as(getCardData.class));
    }
}
