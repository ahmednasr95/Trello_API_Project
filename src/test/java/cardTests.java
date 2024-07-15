import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.trelloAPI.models.cardsAPI.POJO.getCardData;
import org.trelloAPI.services.APIManager;
import org.trelloAPI.services.ReportManager;

import java.io.IOException;

import static org.trelloAPI.services.BoardsManager.*;

public class cardTests extends APIManager {
    public cardTests() throws IOException {
    }

    @Test
    public void cardCRUD(){
        ReportManager.createTest("Test Card (Create Retrieve Update Delete) operations");
        trelloBoard = createNewBoard(firstBoardRequestData);
        ReportManager.logMessage(Status.INFO, "A new board is created");
        toTest = createNewList("To-Test", trelloBoard.getID());
        ReportManager.logMessage(Status.INFO, "A new list is created");
        task1 = createNewCard("task1", toTest.getID());
        ReportManager.assertEqualsAndLog("Assert on 'Task 1' card's name",
                "task1",
                task1.getName());
        task1.changeName("task2");
        ReportManager.assertEqualsAndLog("Assert on 'Task 1' card's new name 'Task 2",
                "task2",
                task1.getName());
        getCardData cardData = task1.getInfo();
        ReportManager.assertEqualsAndLog("Assert on 'Task 2' card's board ID",
                trelloBoard.getID(),
                cardData.getParentBoardID());
        task1.archive();
        ReportManager.assertEqualsAndLog("Assert on 'Task 2' card's archival status",
                true,
                task1.getIsArchived());
        trelloBoard.delete();
        ReportManager.logMessage(Status.INFO,"List (Create Retrieve Update Delete) operations test complete");
    }
}
