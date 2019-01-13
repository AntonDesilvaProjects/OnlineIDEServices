package com.onlineide.rest;

import com.onlineide.dto.Node;
import com.onlineide.dto.request.NewProjectItemRequest;
import com.onlineide.dto.response.BaseServiceResponse;
import com.onlineide.dto.response.Error;
import com.onlineide.dto.response.NewItemDto;
import com.onlineide.service.ProjectExplorerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rest/projectExplorer")
public class ProjectExplorerController {

    @Autowired
    ProjectExplorerService projectExplorerService;


    @RequestMapping(method= RequestMethod.GET)
    public BaseServiceResponse<List<Node>> getProjectExplorerProjects()
    {
        //gets all the projects
        BaseServiceResponse<List<Node>> response = new BaseServiceResponse<>();
        try {
            List<Node> projectItems = projectExplorerService.getAllProjectItems();
            response.setResponse(projectItems);
            response.setSuccess(Boolean.TRUE);
        }
        catch(Exception e)
        {
            response.setSuccess(Boolean.FALSE);
            response.setError( new Error( null, e.getMessage()));
        }
        return response;
    }

    @RequestMapping(method=RequestMethod.POST)
    public BaseServiceResponse<NewItemDto> createProjectItem(@RequestBody NewProjectItemRequest newProjectItemRequest)
    {
        //handles the creation of new items(projects, packages, files)
        BaseServiceResponse<NewItemDto> response = new BaseServiceResponse<>();
        try {
            String itemId = projectExplorerService.createProjectItem(newProjectItemRequest);
            if (!StringUtils.isEmpty(itemId)) {
                response.setResponse(new NewItemDto(itemId));
                response.setSuccess(Boolean.TRUE);
            }
            else
            {
                response.setSuccess(Boolean.FALSE);
                response.setError( new Error( null, "Unable to create new item!"));
            }
        }
        catch(Exception e)
        {
            response.setSuccess(Boolean.FALSE);
            response.setError( new Error( null, e.getMessage()));
        }
        return response;
    }

    @RequestMapping(method=RequestMethod.DELETE)
    public BaseServiceResponse<Boolean> deleteProjectItem(@RequestParam("itemId") String projectItemId )
    {
        //handles the deletion of project item ids
        BaseServiceResponse<Boolean> response = new BaseServiceResponse<>();
        try {
            response.setResponse( projectExplorerService.deleteProjectItem( projectItemId ) );
            response.setSuccess(Boolean.TRUE);
        }
        catch(Exception e)
        {
            response.setSuccess(Boolean.FALSE);
            response.setError( new Error( null, e.getMessage()));
        }
        return response;
    }
}
