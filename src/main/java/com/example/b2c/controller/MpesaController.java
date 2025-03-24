package com.example.b2c.controller;
import com.example.b2c.dto.*;
import com.example.b2c.services.DarajApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MpesaController {

    private  DarajApi darajaApi;
    private  AcknowledgeResponse acknowledgeResponse;

    public MpesaController(DarajApi darajaApi) {
        this.darajaApi = darajaApi;
    }

    @GetMapping(path = "/token", produces = "application/json")
    public ResponseEntity<AccessTknrsp> getAccessToken() {
        return ResponseEntity.ok(darajaApi.getAccessToken());
    }

    @GetMapping(path = "/register-url", produces = "application/json")
    public ResponseEntity<RegisterUrlResponse> registerUrl() {
        return ResponseEntity.ok(darajaApi.registerUrl());
    }

    @PostMapping(path = "/validation", produces = "application/json")
    public ResponseEntity<AcknowledgeResponse> mpesaValidation(@RequestBody MpesaValidationResponse mpesaValidationResponse) {
        return ResponseEntity.ok(acknowledgeResponse);

    }

    @PostMapping("/b2c-transaction")
    public ResponseEntity<B2CTransactionSyncResponse> performB2CTransaction(@RequestBody InternalB2CTransactionRequest internalB2CTransactionRequest) {
        return ResponseEntity.ok(darajaApi.performB2CTransaction(internalB2CTransactionRequest));
    }

}
