package com.example.restapimvc.enums;

public enum NoticeMention {
    UPDATE("Please Update the App!"),
    DEFAULT("You are meeting an early stage of Rudder. Our community will get better with your opinions through “Contact Us”")
    ;
    public String body;

    NoticeMention(String body) {
        this.body = body;
    }
}
