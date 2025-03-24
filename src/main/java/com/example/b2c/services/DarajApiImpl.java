/*
package com.example.b2c.services;
import com.example.b2c.config.MpesaCongif;
import com.example.b2c.dto.AccessTknrsp;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.example.b2c.utils.*;
import java.io.IOException;


public class DarajApiImpl implements DarajApi{


    private  MpesaCongif mpesaConfig;
    private  OkHttpClient okHttpClient;
    private  ObjectMapper objectMapper;

    public void DarajaApiImpl(MpesaCongif mpesaConfig, OkHttpClient okHttpClient, ObjectMapper objectMapper) {
        this.mpesaConfig = mpesaConfig;
        this.okHttpClient = okHttpClient;
        this.objectMapper = objectMapper;
    }

    private static final String AUTHORIZATION_HEADER_STRING = "Authorization";
    private static final String BASIC_AUTH_STRING = "Basic";
    private static final String CACHE_CONTROL_HEADER = "Cache-Control";
    private static final String CACHE_CONTROL_HEADER_VALUE = "no-cache";

    */
/**
     * @return Returns Daraja API Access Token Response
     *//*

    @Override
    public AccessTknrsp getAccessToken() {

        // get the Base64 rep of consumerKey + ":" + consumerSecret
        String encodedCredentials = HelperUtility.toBase64String(String.format("%s:%s", mpesaConfig.getConsumerKey(),
                mpesaConfig.getConsumerSecret()));

        Request request = new Request.Builder()
                .url(String.format("%s?grant_type=%s", mpesaConfig.getOauthEndpoint(), mpesaConfig.getGrantType()))
                .get()
                .addHeader(AUTHORIZATION_HEADER_STRING, String.format("%s %s", BASIC_AUTH_STRING, encodedCredentials))
                .addHeader(CACHE_CONTROL_HEADER, CACHE_CONTROL_HEADER_VALUE)
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            assert response.body() != null;

            // use Jackson to Decode the ResponseBody ...
            return objectMapper.readValue(response.body().string(), AccessTknrsp.class);
        } catch (IOException e) {
//            log.error(String.format("Could not get access token. -> %s", e.getLocalizedMessage()));
            return null;
        }
    }
}*/
package com.example.b2c.services;

import com.example.b2c.config.MpesaCongif;
import com.example.b2c.dto.*;
import com.example.b2c.utils.HelperUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.util.Objects;
import com.example.b2c.utils.Constants.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import static com.example.b2c.utils.Constants.*;


@Service
public class DarajApiImpl implements DarajApi {

    private final MpesaCongif mpesaConfig;
    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;

    @Autowired
    public DarajApiImpl(MpesaCongif mpesaConfig, OkHttpClient okHttpClient, ObjectMapper objectMapper) {
        this.mpesaConfig = mpesaConfig;
        this.okHttpClient = okHttpClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public AccessTknrsp getAccessToken() {
        String encodedCredentials = HelperUtility.toBase64String(
                String.format("%s:%s", mpesaConfig.getConsumerKey(), mpesaConfig.getConsumerSecret())
        );

        Request request = new Request.Builder()
                .url(String.format("%s?grant_type=%s", mpesaConfig.getOauthEndpoint(), mpesaConfig.getGrantType()))
                .get()
                .addHeader("Authorization", "Basic " + encodedCredentials)
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            assert response.body() != null;
            return objectMapper.readValue(response.body().string(), AccessTknrsp.class);
        } catch (IOException e) {
            throw new RuntimeException("Could not get access token: " + e.getMessage(), e);
        }
    }


    @Override
    public RegisterUrlResponse registerUrl() {
        AccessTknrsp accessTokenResponse = getAccessToken();

        RegisterUrlRequest registerUrlRequest = new RegisterUrlRequest();
        registerUrlRequest.setConfirmationURL(mpesaConfig.getConfirmationURL());
        registerUrlRequest.setResponseType(mpesaConfig.getResponseType());
        registerUrlRequest.setShortCode(mpesaConfig.getShortCode());
        registerUrlRequest.setValidationURL(mpesaConfig.getValidationURL());


        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE,
                Objects.requireNonNull(HelperUtility.toJson(registerUrlRequest)));

        Request request = new Request.Builder()
                .url(mpesaConfig.getRegisterUrlEndpoint())
                .post(body)
                .addHeader("Authorization", String.format("%s %s", BEARER_AUTH_STRING, accessTokenResponse.getAccessToken()))
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();

            assert response.body() != null;
            // use Jackson to Decode the ResponseBody ...
            return objectMapper.readValue(response.body().string(), RegisterUrlResponse.class);

        } catch (IOException e) {
//            log.error(String.format("Could not register url -> %s", e.getLocalizedMessage()));
            return null;
        }
    }

    /*@Override
    public SimulateTransactionResponse simulateC2BTransaction(SimulateTransactionRequest simulateTransactionRequest) {
        AccessTknrsp accessTokenResponse = getAccessToken();
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE,
                Objects.requireNonNull(HelperUtility.toJson(simulateTransactionRequest)));

        Request request = new Request.Builder()
                .url(mpesaConfig.getSimulateTransactionEndpoint())
                .post(body)
                .addHeader(AUTHORIZATION_HEADER_STRING, String.format("%s %s", BEARER_AUTH_STRING, accessTokenResponse.getAccessToken()))
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            assert response.body() != null;
            // use Jackson to Decode the ResponseBody ...

            return objectMapper.readValue(response.body().string(), SimulateTransactionResponse.class);
        } catch (IOException e) {
//            log.error(String.format("Could not simulate C2B transaction -> %s", e.getLocalizedMessage()));
            return null;
        }

    }*/

//    @Override
//    public B2CTransactionSyncResponse performB2CTransaction(InternalB2CTransactionRequest internalB2CTransactionRequest) {
//        AccessTknrsp accessTokenResponse = getAccessToken();
////        log.info(String.format("Access Token: %s", accessTokenResponse.getAccessToken()
//        ;
//
//        B2CTransactionRequest b2CTransactionRequest = new B2CTransactionRequest();
//
//        b2CTransactionRequest.setCommandID(internalB2CTransactionRequest.getCommandID());
//        b2CTransactionRequest.setAmount(internalB2CTransactionRequest.getAmount());
//        b2CTransactionRequest.setPartyB(internalB2CTransactionRequest.getPartyB());
//        b2CTransactionRequest.setRemarks(internalB2CTransactionRequest.getRemarks());
//        b2CTransactionRequest.setOccassion(internalB2CTransactionRequest.getOccassion());
//
//        // get the security credentials ...
//        try {
//            b2CTransactionRequest.setSecurityCredential(HelperUtility.getSecurityCredentials(mpesaConfig.getB2cInitiatorPassword()));
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (NoSuchPaddingException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalBlockSizeException e) {
//            throw new RuntimeException(e);
//        } catch (CertificateException e) {
//            throw new RuntimeException(e);
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        } catch (BadPaddingException e) {
//            throw new RuntimeException(e);
//        } catch (InvalidKeyException e) {
//            throw new RuntimeException(e);
//        } catch (NoSuchProviderException e) {
//            throw new RuntimeException(e);
//        }
//
////        log.info(String.format("Security Creds: %s", b2CTransactionRequest.getSecurityCredential())
////        );
//
//        // set the result url ...
//        b2CTransactionRequest.setResultURL(mpesaConfig.getB2cResultUrl());
//        b2CTransactionRequest.setQueueTimeOutURL(mpesaConfig.getB2cQueueTimeoutUrl());
//        b2CTransactionRequest.setInitiatorName(mpesaConfig.getB2cInitiatorName());
//        b2CTransactionRequest.setPartyA(mpesaConfig.getShortCode());
//
//        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE,
//                Objects.requireNonNull(HelperUtility.toJson(b2CTransactionRequest)));
//
//        Request request = new Request.Builder()
//                .url(mpesaConfig.getB2cTransactionEndpoint())
//                .post(body)
//                .addHeader(AUTHORIZATION_HEADER_STRING, String.format("%s %s", BEARER_AUTH_STRING, accessTokenResponse.getAccessToken()))
//                .build();
//
//        try {
//            Response response = okHttpClient.newCall(request).execute();
//
//            assert response.body() != null;
//
//            return objectMapper.readValue(response.body().string(), B2CTransactionSyncResponse.class);
//        } catch (IOException e) {
////            log.error(String.format("Could not perform B2C transaction ->%s", e.getLocalizedMessage()));
//            return null;
//        }
//
//    }
@Override
public B2CTransactionSyncResponse performB2CTransaction(InternalB2CTransactionRequest internalB2CTransactionRequest) {
    // Get access token
    AccessTknrsp accessTokenResponse = getAccessToken();
    if (accessTokenResponse == null || accessTokenResponse.getAccessToken() == null) {
        throw new RuntimeException("Failed to retrieve access token");
    }
    System.out.println("Access Token: " + accessTokenResponse.getAccessToken());

    // Create and populate the B2C transaction request
    B2CTransactionRequest b2CTransactionRequest = new B2CTransactionRequest();
    b2CTransactionRequest.setCommandID(internalB2CTransactionRequest.getCommandID());
    b2CTransactionRequest.setAmount(internalB2CTransactionRequest.getAmount());
    b2CTransactionRequest.setPartyB(internalB2CTransactionRequest.getPartyB());
    b2CTransactionRequest.setRemarks(internalB2CTransactionRequest.getRemarks());
    b2CTransactionRequest.setOccassion(internalB2CTransactionRequest.getOccassion());

    // Get the security credentials
    try {
        String securityCredential = HelperUtility.getSecurityCredentials(mpesaConfig.getB2cInitiatorPassword());
        if (securityCredential == null || securityCredential.isEmpty()) {
            throw new RuntimeException("Failed to generate security credentials");
        }
        b2CTransactionRequest.setSecurityCredential(securityCredential);
        System.out.println("Security Credential: " + securityCredential);
    } catch (Exception e) {
        throw new RuntimeException("Error generating security credentials: " + e.getMessage(), e);
    }

    // Set the result and timeout URLs
    b2CTransactionRequest.setResultURL(mpesaConfig.getB2cResultUrl());
    b2CTransactionRequest.setQueueTimeOutURL(mpesaConfig.getB2cQueueTimeoutUrl());
    b2CTransactionRequest.setInitiatorName(mpesaConfig.getB2cInitiatorName());
    b2CTransactionRequest.setPartyA(mpesaConfig.getShortCode());

    // Convert the request object to JSON
    String jsonRequest = HelperUtility.toJson(b2CTransactionRequest);
    if (jsonRequest == null) {
        throw new RuntimeException("Failed to serialize B2CTransactionRequest");
    }
    System.out.println("B2C Transaction Request JSON: " + jsonRequest);

    RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, jsonRequest);

    // Create HTTP request
    Request request = new Request.Builder()
            .url(mpesaConfig.getB2cTransactionEndpoint())
            .post(body)
            .addHeader(AUTHORIZATION_HEADER_STRING, String.format("Bearer %s", accessTokenResponse.getAccessToken()))
            .build();

    // Send request and handle response
    try (Response response = okHttpClient.newCall(request).execute()) {
        if (response.body() == null) {
            throw new RuntimeException("Empty response body from B2C transaction API");
        }

        String responseBody = response.body().string();
        System.out.println("B2C Transaction Response: " + responseBody);

        return objectMapper.readValue(responseBody, B2CTransactionSyncResponse.class);
    } catch (IOException e) {
        throw new RuntimeException("Error performing B2C transaction: " + e.getMessage(), e);
    }
}


}
