package com.onlineide.dto.response;

public class NewItemDto {

    private String itemId;

    public NewItemDto(){}

    public NewItemDto(String itemId) {
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
