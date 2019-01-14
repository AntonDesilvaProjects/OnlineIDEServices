package com.onlineide.dto;

public class EditorContentDto {

    private String fileId;
    private String fileContent;

    public EditorContentDto()
    {}

    public EditorContentDto(String fileId, String fileContent) {
        this.fileId = fileId;
        this.fileContent = fileContent;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }
}
