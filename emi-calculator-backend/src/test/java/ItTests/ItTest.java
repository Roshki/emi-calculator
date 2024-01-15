package ItTests;
import com.emicalculatorbackend.emicalculator.model.EmiDataResponse;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.*;

@ExtendWith(SpringExtension.class)

public class ItTest {

    String bodyCorrect =
            """
                    {
                    "loanTermInYears" : "6",
                    "loanValue" : "1000",
                    "yearlyInterestRate" : "12"
                    }
                    """;
    String bodyWrong =
            """
                    {
                    "loanTermInYears" : "0",
                    "loanValue" : "1000",
                    "yearlyInterestRate" : "0"
                    }
                    """;

    EmiDataResponse responseError = EmiDataResponse.builder()
            .emi(0)
            .message("leading to division by zero. Verify you inputs!")
            .build();
    EmiDataResponse responseSuccess = EmiDataResponse.builder()
            .emi(1000.0)
            .message("The result is successfully calculated and saved")
            .build();


    @Test
    public void givenUrl_thenCorrect() {
        EmiDataResponse emiResponse = given()
                .header("content-type", "application/json")
                .body(bodyCorrect)
                .when()
                .request("POST", "/calculate-emi")
                .then()
                .statusCode(200).extract().as(EmiDataResponse.class);
        Assertions.assertEquals(responseSuccess.getEmi(), emiResponse.getEmi());
        Assertions.assertEquals(responseSuccess.getMessage(), emiResponse.getMessage());
    }

    @Test
    public void givenUrl_thenWrongInput() {
        val emiResponse = given()
                .header("content-type", "application/json")
                .body(bodyWrong)
                .when()
                .request("POST", "/calculate-emi")
                .then()
                .statusCode(500).extract().as(EmiDataResponse.class);
        Assertions.assertEquals(responseError.getMessage(), emiResponse.getMessage());
    }

}
