package com.onlineide.dao;

import com.onlineide.constants.ProjectItemType;
import com.onlineide.dto.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProjectExplorerDaoImpl implements ProjectExplorerDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParamJdbcTemplate;

    @Override
    public String insertNewProjectItem(ProjectItemType itemType, String itemDetail, String inProjectId, String inPackageId, String itemName) {

        String insertQuery = "INSERT INTO nodes ( id, user_id, parent_id, name, type, details, content, created_time, updated_time ) VALUES ( node_id_seq.NEXTVAL,:userId,:parentId,:name,:type,:details, null, SYSDATE, SYSDATE )";
        String itemId = null;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        //get the parent id - if the new item belongs to a package, use package id
        //else use the project id
        String parentId = null;
        if(!StringUtils.isEmpty(inPackageId))
            parentId = inPackageId;
        else if(!StringUtils.isEmpty(inProjectId))
            parentId = inProjectId;

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", "0000");
        params.addValue("name", itemName );
        params.addValue("type", itemType.toString() );
        params.addValue("details", itemDetail );
        params.addValue("parentId", parentId);

        int insertCt = namedParamJdbcTemplate.update( insertQuery, params, keyHolder, new String[]{ "id" } );
        if( insertCt == 1 )
            itemId = keyHolder.getKey().toString();

        return itemId;
    }
    @Override
    public List<Node> getAllProjectItems()
    {
        List<Node> nodeList = collectProjectItemsIntoList(null );
        return nodeList;
    }
    /*
        Recursively traverses the project tree and collects the nodes.

        @param parentId used as the 'root' node
        @return List<Node> list of node object which are children of the passed
        parent node.
    */
    private List<Node> collectProjectItemsIntoList( String parentId )
    {
        //TODO: Need to filter based on user id
        String nullParentQuery = "SELECT * FROM nodes WHERE parent_id IS NULL";
        String parentQuery = "SELECT * FROM nodes WHERE parent_id = :parentId";
        String query = StringUtils.isEmpty(parentId) ? nullParentQuery : parentQuery;

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("parentId", parentId);

        List<Node> nodeList = namedParamJdbcTemplate.query( query, params, new Node() );

        for(Node node : nodeList )
        {
            node.setChildren( collectProjectItemsIntoList( node.getId() ));
            node.setAdditionalProperties( getPropertiesForNode(node.getId()) );
            node.setLeaf( node.getChildren() == null || node.getChildren().isEmpty()  );
        }

        return nodeList;
    }
    /*
        Gets the properties of a node
    */
    private Map<String,String> getPropertiesForNode( String nodeId )
    {
        String query = "SELECT m.prop_nm, p.prop_value FROM nodes n, node_properties p, node_prop_mst m WHERE n.id = p.node_id AND p.prop_id = m.prop_id AND n.id = :nodeId";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nodeId", nodeId );
        Map<String,String> propertyMap = namedParamJdbcTemplate.query(query, params, (ResultSet r) -> {
            Map<String,String> props = null;
            while( r.next() ) {
                if( props == null )
                    props = new HashMap<>();
                props.put(r.getString("prop_nm"), r.getString("prop_value"));
            }
            return props;
        });
        return propertyMap;
    }

    @Override
    public int deleteProjectItem( String nodeId )
    {
        String query = "DELETE FROM nodes WHERE id IN (:nodeIds)";
        List<String> nodeIdsToDelete = getChildrenNodes( nodeId );
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nodeIds", nodeIdsToDelete );
        int deleteCt = namedParamJdbcTemplate.update( query, params );
        return deleteCt;
    }

    /*
        Returns a list of children nodes for the given node
    */
    private List<String> getChildrenNodes( String nodeId )
    {
        List<String> results = new ArrayList<>();
        String query = "SELECT id FROM nodes WHERE parent_id = :nodeId";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nodeId", nodeId );
        List<String> childrenNodes = namedParamJdbcTemplate.query( query, params, (ResultSet r, int rowNum)->{ return r.getString("id");} );
        for( String id : childrenNodes ) {
            results.addAll(getChildrenNodes(id));
        }
        results.add( nodeId ); //add current node to the list
        return results;
    }
}
