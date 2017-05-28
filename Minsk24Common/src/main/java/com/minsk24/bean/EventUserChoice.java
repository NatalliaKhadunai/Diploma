package com.minsk24.bean;

public enum EventUserChoice {
    WILL_GO("Will go"), WONT_GO("Won't go");

    private String value;
    EventUserChoice(String value) {
        this.value = value;
    }

    public static EventUserChoice getFromValue(String value) {
        for (EventUserChoice euc : EventUserChoice.values()) {
            if (euc.value.equals(value)) return euc;
        }
        throw new IllegalArgumentException("No such event user choice");
    }
}
