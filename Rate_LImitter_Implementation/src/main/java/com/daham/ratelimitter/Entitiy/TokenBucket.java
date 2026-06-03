package com.daham.ratelimitter.Entitiy;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TokenBucket {

    private int maxTokenPerMinute;
    private int availableTokens;
    private String key;
    private long lastUpdateTime;

    public boolean getToken(){
        if (System.currentTimeMillis() - lastUpdateTime >60_000 ){
            availableTokens = maxTokenPerMinute;
        }
        if(availableTokens >0){
            availableTokens -= 1;
            return true;
        }
        return false;
    }







}
