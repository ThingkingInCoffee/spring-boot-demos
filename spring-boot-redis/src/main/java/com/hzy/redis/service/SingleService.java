package com.hzy.redis.service;

public interface SingleService {

    void connect();

    Integer getValid(String param);

    Integer getPreValid(String param);

    Integer setValid(String param, Integer val);

    Integer setValid(String param);

}
