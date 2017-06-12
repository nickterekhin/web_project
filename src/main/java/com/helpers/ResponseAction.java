package com.helpers;

/**
 * Created by Nick on 12.06.17.
 */
public enum ResponseAction {
    ADD("add"),
    EDIT("edit"),
    DELETE("delete"),
    LIST("list");

    private String action;
    ResponseAction(String action) {
        this.action = action;
    }
    static public ResponseAction getResponseAction(String pType) {
        for (ResponseAction type: ResponseAction.values()) {
            if (type.getAction().equals(pType)) {
                return type;
            }
        }
        return LIST;
    }

    public String getAction() {
        return action;
    }
}
