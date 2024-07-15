import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.trelloAPI.services.APIManager;
import org.trelloAPI.services.ReportManager;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.trelloAPI.services.BoardsManager.*;

public class integrationFlowTests extends APIManager {
    public integrationFlowTests() throws IOException {
    }
    @Test
    public void testMoveCardBetweenLists() {
        ReportManager.createTest("Move cards between lists");
        // Step 1: Create a new board
        trelloBoard = createNewBoard(firstBoardRequestData);
        ReportManager.assertEqualsAndLog("Assert on board's name",
                firstBoardRequestData.getBoardName(),
                trelloBoard.getName());
        // Step 2: Add two lists to the created board
        toTest = createNewList("To Test", trelloBoard.getID());
        ReportManager.assertEqualsAndLog("Assert on 'To Test' list's name",
                "To Test",
                toTest.getName());
        tested = createNewList("Tested", trelloBoard.getID());
        ReportManager.assertEqualsAndLog("Assert on 'Tested' list's name",
                "Tested",
                tested.getName());
        // Step 3: Add a card to the first list
        task1 = createNewCard("Task 1", toTest.getID());
        ReportManager.assertEqualsAndLog("Assert on 'Task 1' card's name",
                "Task 1",
                task1.getName());
        ReportManager.assertEqualsAndLog("Assert on 'Task 1' card's list",
                toTest.getID(),
                task1.getParentListID());
        // Step 4: Move the card to the second list
        task1.moveToList(tested.getID());
        ReportManager.assertEqualsAndLog("Assert on 'Task 1' card's new list",
                tested.getID(),
                task1.getParentListID());
        trelloBoard.delete();
        ReportManager.logMessage(Status.INFO,"Move Card Between Lists is Complete");
    }
    @Test
    public void testMoveListBetweenBoards() {
        ReportManager.createTest("Move lists between boards");
        // Step 1: Create two boards
        trelloBoard = createNewBoard(firstBoardRequestData);
        ReportManager.assertEqualsAndLog("Assert on first board's name",
                firstBoardRequestData.getBoardName(),
                trelloBoard.getName());
        trelloBoard2 = createNewBoard(secondBoardRequestData);
        ReportManager.assertEqualsAndLog("Assert on second board's name",
                secondBoardRequestData.getBoardName(),
                trelloBoard2.getName());
        // Step 2: Add a list to the first board
        toTest = createNewList("To Test", trelloBoard.getID());
        ReportManager.assertEqualsAndLog("Assert on 'To Test' list's name",
                "To Test",
                toTest.getName());
        // Step 3: Add a card to the list
        task1 = createNewCard("Task 1", toTest.getID());
        ReportManager.assertEqualsAndLog("Assert on 'Task 1' card's name",
                "Task 1",
                task1.getName());
        ReportManager.assertEqualsAndLog("Assert on 'Task 1' card's list",
                toTest.getID(),
                task1.getParentListID());
        // Step 4: Move the list to the second board
        toTest.moveToBoard(trelloBoard2.getID());
        ReportManager.assertEqualsAndLog("Assert on 'To Test' list's new board",
                trelloBoard2.getID(),
                toTest.getParentBoardID());
        // Step 5: Delete all boards
        trelloBoard.delete();
        trelloBoard2.delete();
        ReportManager.logMessage(Status.INFO,"Move List Between Boards is Complete");
    }
}
