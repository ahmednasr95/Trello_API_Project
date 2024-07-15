import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.trelloAPI.models.listsAPI.POJO.getListData;
import org.trelloAPI.services.APIManager;
import org.trelloAPI.services.ReportManager;

import java.io.IOException;

import static org.trelloAPI.services.BoardsManager.createNewBoard;
import static org.trelloAPI.services.BoardsManager.createNewList;

public class listTests extends APIManager {
    public listTests() throws IOException {
    }

    @Test
    public void listCRUD(){
        ReportManager.createTest("Test List (Create Retrieve Update Delete) operations");
        trelloBoard = createNewBoard(firstBoardRequestData);
        ReportManager.logMessage(Status.INFO, "A new board is created");
        toTest = createNewList("To-Test",trelloBoard.getID());
        ReportManager.assertEqualsAndLog("Assert on 'To Test' list's name",
                "To-Test",
                toTest.getName());
        toTest.changeName("Testing");
        ReportManager.assertEqualsAndLog("Assert on 'To Test' list's new name 'Testing",
                "Testing",
                toTest.getName());
        getListData listData = toTest.getInfo();
        ReportManager.assertEqualsAndLog("Assert on 'Testing' list's board ID",
                trelloBoard.getID(),
                listData.getParentBoardID());
        toTest.archive();
        ReportManager.assertEqualsAndLog("Assert on 'Testing' list's archival status",
                true,
                toTest.getIsArchived());
        trelloBoard.delete();
        ReportManager.logMessage(Status.INFO,"Card (Create Retrieve Update Delete) operations test complete");
    }
}
