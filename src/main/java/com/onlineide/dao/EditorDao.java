package com.onlineide.dao;

import com.onlineide.dto.EditorContentDto;

public interface EditorDao {
    public EditorContentDto getFileContent(String fileNodeId );
    public boolean saveEditorContent( String fileNodeId, String fileContent );
}
