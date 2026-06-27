package com.example.JavaApi.service;

import com.example.JavaApi.dto.BfhlRequest;
import com.example.JavaApi.dto.BfhlResponse;

public interface BfhlService {
    BfhlResponse processData(BfhlRequest request);
}
