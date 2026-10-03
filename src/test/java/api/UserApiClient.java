package api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.User;

import static io.restassured.RestAssured.given;

public class UserApiClient {

    private static final String BASE = "https://stellarburgers.education-services.ru/api";

    @Step("Создать пользователя через API: {user.email}")
    public Response register(User user) {
        return given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(BASE + "/auth/register");
    }

    @Step("Удалить пользователя по accessToken")
    public Response delete(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .when()
                .delete(BASE + "/auth/user");
    }
}