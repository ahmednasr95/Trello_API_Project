package org.trelloAPI.models.Enums;

public enum memberType {
    ADMIN("admin"),
    NORMAL("normal"),
    OBSERVER("observer");
    private final String type;
    memberType(String type) {
        this.type = type;
    }
    @Override
    public String toString(){
        return type;
    }
}
