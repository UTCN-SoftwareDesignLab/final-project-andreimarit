package com.lab4.demo;

public class UrlMapping {
    public static final String API_PATH = "/api";
    public static final String PATIENT = API_PATH + "/patient";
    public static final String PRODUCT = API_PATH + "/product";
    public static final String DISCOUNT = API_PATH + "/discount";
    public static final String CART = API_PATH + "/cart";

    public static final String CONSULTATIONS = API_PATH + "/consultation";
    public static final String EXPORT_REPORT = "/export/{type}";
    public static final String EMAILSEND = "/email";
    public static final String SMSSEND = "/sms";

    public static final String AUTH = API_PATH + "/auth";
    public static final String SIGN_IN = "/sign-in";
    public static final String SIGN_UP = "/sign-up";

    public static final String USER = API_PATH + "/user";
    public static final String PASSWORD = "";
    public static final String SELL = "/sell";
    public static final String TAG = "/tag";
    public static final String ID = "/{id}";

}
