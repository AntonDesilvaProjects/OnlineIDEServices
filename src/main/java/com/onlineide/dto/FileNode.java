package com.onlineide.dto;

import com.onlineide.constants.ProjectItemType;

public class FileNode extends Node {
    public FileNode()
    {
        this.setType(ProjectItemType.FILE);
    }
}
