import io.restassured.response.Response;

import org.testng.Assert;
import org.trelloAPI.models.boardsAPI.Board;
import org.trelloAPI.models.boardsAPI.boardBackgroundColor;
import org.trelloAPI.models.boardsAPI.createBoardData;
import org.trelloAPI.models.boardsAPI.getBoardData;
import org.testng.annotations.Test;
import org.trelloAPI.models.listsAPI.List;
import org.trelloAPI.models.listsAPI.getListData;
import org.trelloAPI.services.APIManager;

import java.io.IOException;

import static org.trelloAPI.services.BoardsManager.createNewBoard;
import static org.trelloAPI.services.BoardsManager.createNewList;
import static org.trelloAPI.utils.JSONReader.readJsonFromFile;

public class boardTests extends APIManager {

    static getBoardData createBoardResponseData;
    createBoardData boardRequestData = readJsonFromFile("src/main/resources/Data/boardSpecs.json"
            , createBoardData.class);
    Response resp;
    Board trelloBoard;
    List toTestList;
    public boardTests() throws IOException {
    }
    @Test
    public void boardCRUD(){
        trelloBoard = createNewBoard(boardRequestData);
        Assert.assertEquals(trelloBoard.background.getBackground(),boardRequestData.getBoardBackground());
        trelloBoard.greenLabel.changeName("Done");
        trelloBoard.redLabel.changeName("Blocked");
        trelloBoard.background.changeColor(boardBackgroundColor.BLUE);
        getBoardData boardData = trelloBoard.getInfo();
        Assert.assertEquals(boardBackgroundColor.BLUE, boardData.getBoardPrefs().getBoardBackground());
        trelloBoard.delete();
    }

    @Test
    public void listCRUD(){
        trelloBoard = createNewBoard(boardRequestData);
        toTestList = createNewList("To-Test",trelloBoard.getID());
        toTestList.changeName("Testing");
        toTestList.archive();
        getListData listData = toTestList.getInfo();
        Assert.assertEquals(true,listData.getIsArchived());
        toTestList.delete();
    }
/*    @Test(priority = 0)
    public void createNewBoard(){
        resp = userReqSpecs
                      .body(boardRequestData)
                      .contentType(ContentType.JSON)
                      .post("boards");
        createBoardResponseData = resp.then().statusCode(200).extract().as(getBoardData.class);
        resp.prettyPrint();
    }*/

/*    @Test(priority = 1)
    public void validateCreatedBoardOptions(){
        resp = userReqSpecs
                .pathParam("id",createBoardResponseData.getBoardID())
                .get("/boards/{id}");
       getBoardData getBoardResponseData = resp.then().statusCode(200).extract().as(getBoardData.class);
       SoftAssert softAssert = new SoftAssert();
       softAssert.assertEquals(getBoardResponseData.getBoardPrefs().getBoardBackground(),boardRequestData.getBoardBackground());
    }

    @Test(priority = 2)
    public void updateBoardBackgroundColor(){
        String newBackgroundColor = "red";
        resp = userReqSpecs
                .pathParam("id",createBoardResponseData.getBoardID())
                .queryParam("prefs/background",newBackgroundColor)
                .contentType(ContentType.JSON)
                .put("/boards/{id}");
        resp.then().statusCode(200);
    }
    @Test(priority = 3)
    public void deleteBoard(){
        resp = userReqSpecs
                .pathParam("id",createBoardResponseData.getBoardID())
                .delete("/boards/{id}");
        resp.then().statusCode(200);
    }*/
}
