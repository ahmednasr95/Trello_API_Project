
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.trelloAPI.models.Enums.boardBackgroundColor;
import org.trelloAPI.models.boardsAPI.POJO.createBoardData;
import org.trelloAPI.services.APIManager;
import org.trelloAPI.services.ReportManager;

import java.io.IOException;

import static org.trelloAPI.services.BoardsManager.*;
import static org.trelloAPI.utils.JSONReader.readJsonFromFile;

public class boardTests extends APIManager {
    createBoardData boardRequestData = readJsonFromFile("src/main/resources/Data/firstBoardSpecs.json"
            ,createBoardData.class);
    public boardTests() throws IOException {
    }
    @Test
    public void boardCRUD(){
        ReportManager.createTest("Test Board (Create Retrieve Update Delete) operations");
        trelloBoard = createNewBoard(boardRequestData);
        ReportManager.assertEqualsAndLog("Assert on 'Trello Board' board's background",
                boardRequestData.getBoardBackground(),
                trelloBoard.background.getBackground());
        trelloBoard.greenLabel.changeName("Done");
        ReportManager.assertEqualsAndLog("Assert on 'Trello Board' board's green label's name",
                "Done",
                trelloBoard.greenLabel.getName());
        trelloBoard.redLabel.changeName("Blocked");
        ReportManager.assertEqualsAndLog("Assert on 'Trello Board' board's red label's name",
                "Blocked",
                trelloBoard.redLabel.getName());
        trelloBoard.background.changeColor(boardBackgroundColor.BLUE);
        ReportManager.assertEqualsAndLog("Assert on 'Trello Board' board's new background",
                boardBackgroundColor.BLUE.toString(),
                trelloBoard.background.getBackground());
        trelloBoard.delete();
        ReportManager.logMessage(Status.INFO,"Board (Create Retrieve Update Delete) operations test complete");
    }
}
