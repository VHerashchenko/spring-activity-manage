package com.vh.activitymanage.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}