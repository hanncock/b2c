package com.example.B2C.controller;
import com.example.B2C.model.AccessToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

/*import com.africastalking.Callback;
import com.africastalking.SmsService;
import com.africastalking.sms.Message;
import com.africastalking.sms.Recipient;
import com.africastalking.AfricasTalking;*/

import java.util.List;
import java.io.IOException;


@RestController
public class MpesaCtrl {

    private ObjectMapper objectMapper = new ObjectMapper();
    OkHttpClient client = new OkHttpClient().newBuilder().build();
    AccessToken accessTokenResponse = new AccessToken();


    @GetMapping("/tokena")
    public String getToken() {
        Request request = new Request.Builder()
                .url("https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials")
                .get()
                .addHeader("Authorization", "Basic dVVJN0o3UWhGQTZiQTRCQlNYZzdCYm9taUdnQXhpT086cHZZY2pyRVB3YmViZURxeg==")
                .build();

        try {
            Response response = client.newCall(request).execute();

            if (!response.isSuccessful()) {
                throw new RuntimeException("Request failed: " + response.code() + " " + response.message());
            }

            if (response.body() == null) {
                throw new RuntimeException("Empty response from server");
            }

            String responseBody = response.body().string();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            String accessToken = jsonNode.get("access_token").asText();

            System.out.println("Extracted Access Token: " + accessToken); // Debugging
            return accessToken;

        } catch (IOException e) {
            throw new RuntimeException("Could not get access token: " + e.getMessage(), e);
        }
//        return
    }



    @GetMapping("/b2c-transaction")
    public ResponseEntity<Object> getTransaction(){

        ObjectMapper objectMapper = new ObjectMapper();

        String jsonPayload = "{"
                + "\"OriginatorConversationID\": \"ca8c947c-f751-4855-8611-c173cfb42\","
                + "\"InitiatorName\": \"testapi\","
                + "\"SecurityCredential\": \"KO6QewarUnMFU4KzQfiwPoSJFj1FFuJxQlHPHJ6QK85mzbAalu9nluSdD04Bo2dS8SffNAONX0RRMy0Y3XuMKyXABshYxfEs6dfKftJhEQqI5USgmVLgNe96T505OC2KfuW6OSpFJpxw9nykP+rnq7wWoRrBatAwk3tH8Sj6M21DZlbSOCDNtagTbauBJQcYkG3J9qZ43vZPCDwZaaNcIiP4jnfg83lMmhzfVzrCTxGgIhiBQFnZTu5NUo03f5RNhx/CHtYmwYJdfb1WNsOhBlWr/RsQW7L2+zYocSbBJ5A9t58XvyYCNofcRHF/F5IIgJk/FahXaFz4jPJq3eTonA==\","
                + "\"CommandID\": \"BusinessPayment\","
                + "\"Amount\": 10,"
                + "\"PartyA\": 600999,"
                + "\"PartyB\": 254700202696,"
                + "\"Remarks\": \"Test remarks\","
                + "\"QueueTimeOutURL\": \"https://mydomain.com/b2c/queue\","
                + "\"ResultURL\": \"https://mydomain.com/b2c/result\","
                + "\"occasion\": \"null\""
                + "}";

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"),
                jsonPayload
        );
        // Build the HTTP request
        Request request = new Request.Builder()
                .url("https://sandbox.safaricom.co.ke/mpesa/b2c/v1/paymentrequest")
                .post(body)
//                .addHeader("Authorization", "BEARER_AUTH_STRING "+getToken().toString())
                .addHeader("Authorization", "Bearer "+getToken().toString())

                .addHeader("Content-Type", "application/json")
                .build();

        // Execute the request
        OkHttpClient client = new OkHttpClient();
        try {
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            String responseBody = response.body().string();
            return ResponseEntity.ok(objectMapper.readValue(responseBody, Object.class)); // Convert JSON to Object
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request failed: " + e.getMessage());
        }
    }


    @PostMapping("/sendSMS")
    /* Import SDK classes */


    public class TestSendingWithSenderID {

        public static void main(String[] args) {
            /* Set your app credentials */
            String USERNAME = "MyAppUsername";
            String API_KEY = "MyAppAPIKey";

            /* Initialize SDK */
            AfricasTalking.initialize(USERNAME, API_KEY);

            /* Get the SMS service */
            SmsService sms = AfricasTalking.getService(AfricasTalking.SERVICE_SMS);

            /* Set the numbers you want to send to in international format */
            String[] recipients = new String[] {
                    "+254711XXXYYY", "+254702XXXYYY"
            };

            /* Set your message */
            String message = "We are lumberjacks. We sleep all day and code all night";

            /* Set your shortCode or senderId */
            String from = "XXXXX"; // or "ABCDE"

            /* That’s it, hit send and we’ll take care of the rest */
            try {
                List<Recipient> response = sms.send(message, from, recipients, true);
                for (Recipient recipient : response) {
                    System.out.print(recipient.number);
                    System.out.print(" : ");
                    System.out.println(recipient.status);
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }




}
