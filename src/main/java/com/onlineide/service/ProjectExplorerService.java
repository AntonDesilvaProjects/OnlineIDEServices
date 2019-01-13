package com.onlineide.service;

import com.onlineide.constants.ProjectItemType;
import com.onlineide.dao.ProjectExplorerDao;
import com.onlineide.dto.Node;
import com.onlineide.dto.request.NewProjectItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ProjectExplorerService {

    @Autowired
    ProjectExplorerDao projectExplorerDao;

    public String createProjectItem(NewProjectItemRequest newItemRequest )
    {
        String newItemId = null;
        if( isNewProjectItemRequestValid(newItemRequest)) {
            newItemId = projectExplorerDao.insertNewProjectItem( newItemRequest.getItemType(),
                                                        newItemRequest.getItemDetail(),
                                                        newItemRequest.getInProject(),
                                                        newItemRequest.getInPackage(),
                                                        newItemRequest.getItemName() );
        }
        return newItemId;
    }

    public List<Node> getAllProjectItems()
    {
        return projectExplorerDao.getAllProjectItems();
    }

    public boolean deleteProjectItem(String projectItemId )
    {
        return projectExplorerDao.deleteProjectItem( projectItemId ) > 0 ? true : false;
    }

    private boolean isNewProjectItemRequestValid(NewProjectItemRequest request)
    {
        if( request == null )
            return false;

        if(ProjectItemType.PROJECT == request.getItemType())
        {
            //project name and type must be present
            return !StringUtils.isEmpty( request.getItemDetail() ) && !StringUtils.isEmpty( request.getItemName() );
        }
        else if(ProjectItemType.PACKAGE == request.getItemType())
        {
            //package name, type, project must be present
            return !StringUtils.isEmpty( request.getItemDetail() ) && !StringUtils.isEmpty( request.getItemName() ) && !StringUtils.isEmpty( request.getInProject() );
        }
        else if(ProjectItemType.FILE == request.getItemType())
        {
            //file name and type must be present
            return !StringUtils.isEmpty( request.getItemDetail() ) && !StringUtils.isEmpty( request.getItemName() );
        }
        return false;
    }
}
