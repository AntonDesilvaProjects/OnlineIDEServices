package com.onlineide.dto.request;

import com.onlineide.constants.ProjectItemType;

public class NewProjectItemRequest {

    private ProjectItemType itemType;
    private String itemDetail;
    private String inProject;
    private String inPackage;
    private String itemName;

    public ProjectItemType getItemType() {
        return itemType;
    }

    public void setItemType(ProjectItemType itemType) {
        this.itemType = itemType;
    }

    public String getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(String itemDetail) {
        this.itemDetail = itemDetail;
    }

    public String getInProject() {
        return inProject;
    }

    public void setInProject(String inProject) {
        this.inProject = inProject;
    }

    public String getInPackage() {
        return inPackage;
    }

    public void setInPackage(String inPackage) {
        this.inPackage = inPackage;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
