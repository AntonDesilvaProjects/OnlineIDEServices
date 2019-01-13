package com.onlineide.dto;

import com.onlineide.constants.ProjectItemType;

public class PackageNode extends Node {
    public PackageNode()
    {
        this.setType(ProjectItemType.PROJECT);
    }
}
