package com.example.b2c.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.xml.transform.Result;

@Data
public class B2CTransactionAsyncResponse{

    @JsonProperty("Result")
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
