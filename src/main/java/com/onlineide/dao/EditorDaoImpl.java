package com.onlineide.dao;

import com.onlineide.dto.EditorContentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;

@Component
public class EditorDaoImpl implements EditorDao {

    @Autowired
    NamedParameterJdbcTemplate namedJdbcTemplate;

    @Override
    public EditorContentDto getFileContent(String fileNodeId) {
        String query = "SELECT id, content FROM nodes WHERE id = :nodeId";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nodeId", fileNodeId );
        EditorContentDto editorContent = namedJdbcTemplate.queryForObject( query, params, (ResultSet r, int rowNum)->{
           return new EditorContentDto( r.getString("id"), r.getString("content") );
        });
        return editorContent;
    }

    @Override
    public boolean saveEditorContent(String fileNodeId, String fileContent) {
        String query = "UPDATE nodes SET content = :content, updated_time = SYSDATE WHERE id = :nodeId";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nodeId", fileNodeId );
        params.addValue("content", fileContent );
        int rowCt = namedJdbcTemplate.update( query, params );
        return rowCt == 1;
    }
}
