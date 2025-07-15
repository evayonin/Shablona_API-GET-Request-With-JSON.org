// GET = turning the JSON string we get into an object
// POST = turning the object into a JSON String we can post 

//בבקשות פוסט שולחים מידע אל השרת ולכן יש להמיר את האובייקט המקומי למחרוזת גייסון שתואמת למבנה שהשרת מצפה לו ולשלוח אותה כגוף הבקשה.
// לעומת זאת, בבקשת גט מקבלים מהשרת מידע בפורמט גייסון, ויש להמיר את המחרוזת שהתקבלה חזרה לאובייקט תואם בקוד כדי שנוכל לעבוד איתו בצורה נוחה. 
//כלומר, בפוסט עושים המרה מאובייקט לגייסון, ובגט עושים המרה מגייסון לאובייקט.
package com.example;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        String apiUrl = ""; // הלינק לשרת שנקבל

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString());

        String body = response.body();
        // if we dont add the <String> in response, we can add .asString() after
        // response.body()

        System.out.println(body); // we have to check if it's an array or just object parameters

        JSONObject jsonObject = new JSONObject(body);
        Object neededParam = jsonObject.getObjectType("neededParam-String-Key"); // להשלים ולתקן את הטייפ לפי מה שהמפתח
                                                                                 // מחזיר
        // for example:
        String text = jsonObject.getString("text");
        int idNumber = jsonObject.getInt("id");
        // ....

        // OR:

        JSONArray jsonArray = new JSONArray(body);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject JsonObject = jsonArray.getJSONObject(i);
            Object NeededParam = JsonObject.getObjectType("neededParam-String-Key"); // להשלים ולתקן את הטייפ לפי מה
                                                                                     // שהמפתח מחזיר
            // ....
        }

    }
}