package com.example.b2c.services;
import com.example.b2c.dto.AccessTknrsp;
import com.example.b2c.dto.B2CTransactionSyncResponse;
import com.example.b2c.dto.InternalB2CTransactionRequest;
import com.example.b2c.dto.RegisterUrlResponse;


public interface DarajApi {

    AccessTknrsp getAccessToken();


    RegisterUrlResponse registerUrl();
    B2CTransactionSyncResponse performB2CTransaction(InternalB2CTransactionRequest internalB2CTransactionRequest);

}
