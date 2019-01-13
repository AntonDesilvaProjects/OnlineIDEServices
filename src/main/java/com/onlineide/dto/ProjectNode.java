package com.onlineide.dto;

import com.onlineide.constants.ProjectItemType;

public class ProjectNode extends Node {

    public ProjectNode()
    {
        this.setType(ProjectItemType.PROJECT);
    }

}
