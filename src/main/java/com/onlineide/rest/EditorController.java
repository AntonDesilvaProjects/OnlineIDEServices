package com.onlineide.rest;

import com.onlineide.dto.EditorContentDto;
import com.onlineide.dto.response.BaseServiceResponse;
import com.onlineide.dto.response.Error;
import com.onlineide.service.EditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/editor")
public class EditorController {

    @Autowired
    EditorService editorService;

    @RequestMapping(method= RequestMethod.GET)
    public BaseServiceResponse<EditorContentDto> getFileContent(@RequestParam("fileId") String fileId)
    {
        BaseServiceResponse<EditorContentDto> response = new BaseServiceResponse<>();
        try
        {
            response.setResponse( editorService.getFileContent(fileId) );
            response.setSuccess( Boolean.TRUE );
        }
        catch(Exception e )
        {
            response.setError(new Error(null, e.getMessage()));
            response.setSuccess( Boolean.FALSE );
        }
        return response;
    }

    @RequestMapping(method= RequestMethod.POST)
    public BaseServiceResponse<Boolean> saveEditorContent(@RequestBody EditorContentDto saveRequest )
    {
        BaseServiceResponse<Boolean> response = new BaseServiceResponse<>();
        try
        {
            response.setResponse( editorService.saveEditorContent(saveRequest) );
            response.setSuccess( Boolean.TRUE );
        }
        catch(Exception e )
        {
            response.setError(new Error(null, e.getMessage()));
            response.setSuccess( Boolean.FALSE );
        }
        return response;
    }

}
