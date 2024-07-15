import com.aventstack.extentreports.Status;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.trelloAPI.services.APIManager;
import org.trelloAPI.services.ReportManager;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class negativeTests extends APIManager {
    public negativeTests() throws IOException {
    }
    @Test
    public void testWithInvalidApiKey() {
        ReportManager.createTest("Test sending invalid API key");
        Response response = given()
                .baseUri(config.getProperty("baseURL"))
                .basePath(config.getProperty("basePath"))
                .body(STR."""
                    {
                        "name":"testBoard",
                        "key":"nasdsadasdas",
                        "token":"\{config.getProperty("APIToken")}"
                    }
                      """)
                .contentType(ContentType.JSON)
                .post("boards");
        ReportManager.assertEqualsAndLog("Assert on response status code",
                401, response.getStatusCode());
        ReportManager.assertEqualsAndLog("Assert on response status message",
                "invalid key", response.body().print());
        ReportManager.logMessage(Status.INFO,"Sending invalid API key test is Complete");
    }

    @Test
    public void testCreateBoardWithInvalidData() {
        ReportManager.createTest("Test sending missing required data");
        Response response = given().spec(userReqSpecs)
                .contentType(ContentType.JSON)
                .post("boards");
        ReportManager.assertEqualsAndLog("Assert on response status code",
                400, response.getStatusCode());
        String errorMessage = response.jsonPath().getString("message");
        ReportManager.assertTrueAndLog("Assert on response status message",
                "invalid value for name", errorMessage.contains("invalid value for name"));
        ReportManager.logMessage(Status.INFO,"Sending missing required data test is Complete");
    }

}
