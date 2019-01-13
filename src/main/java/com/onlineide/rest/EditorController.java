package com.onlineide.rest;

import com.onlineide.dto.request.EditorContentSaveRequest;
import com.onlineide.dto.response.BaseServiceResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/editor")
public class EditorController {

    @RequestMapping(method= RequestMethod.POST)
    public BaseServiceResponse saveEditorContent(@RequestBody EditorContentSaveRequest request)
    {
        return null;
    }

}
