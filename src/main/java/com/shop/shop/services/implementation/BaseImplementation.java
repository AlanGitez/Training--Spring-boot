package com.shop.shop.services.implementation;

import java.util.*;

public class BaseImplementation {

    protected Map<String, Object> generateResponse(boolean error, Object data){
        Map response = new HashMap();
        response.put("error", error);
        response.put("data", data);

        return response;
    }
}
