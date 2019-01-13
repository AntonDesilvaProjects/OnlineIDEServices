package com.onlineide.dao;

import com.onlineide.constants.ProjectItemType;
import com.onlineide.dto.Node;

import java.util.List;

public interface ProjectExplorerDao {
    public String insertNewProjectItem( ProjectItemType itemType, String itemDetail, String inProjectId, String inPackageId, String itemName );
    public List<Node> getAllProjectItems();
    public int deleteProjectItem( String projectItemId );
}
