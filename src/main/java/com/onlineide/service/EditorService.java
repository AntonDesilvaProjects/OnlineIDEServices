package com.onlineide.service;

import com.onlineide.dao.EditorDao;
import com.onlineide.dto.EditorContentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorService {

    @Autowired
    EditorDao editorDao;

    public EditorContentDto getFileContent(String fileId)
    {
        return editorDao.getFileContent( fileId );
    }

    public boolean saveEditorContent(EditorContentDto saveRequest)
    {
        return editorDao.saveEditorContent( saveRequest.getFileId(), saveRequest.getFileContent() );
    }

}
