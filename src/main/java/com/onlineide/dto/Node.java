package com.onlineide.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.onlineide.constants.ProjectItemType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Node implements RowMapper<Node> {

    private String text;
    @JsonProperty("nodeId")
    private String id;
    private String parentId;
    @JsonProperty("nodeType")
    private ProjectItemType type;
    private String details;
    private List<Node> children;
    private boolean leaf;
    private Date updatedOn;
    private Date createdOn;

    private Map<String,String> additionalProperties;

    public Node() {
        this.leaf = true;
    }

    public Node(String text, String id, String parentId, ProjectItemType type, String details, List<Node> children, boolean leaf, Date updatedOn, Date createdOn, Map<String, String> additionalProperties) {
        this.text = text;
        this.id = id;
        this.parentId = parentId;
        this.type = type;
        this.details = details;
        this.children = children;
        this.leaf = leaf;
        this.updatedOn = updatedOn;
        this.createdOn = createdOn;
        this.additionalProperties = additionalProperties;
    }

    public Map<String, String> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, String> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    public void addChild(Node child)
    {
        this.getChildren().add( child );
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProjectItemType getType() {
        return type;
    }

    public void setType(ProjectItemType type) {
        this.type = type;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Nullable
    @Override
    public Node mapRow(ResultSet resultSet, int i) throws SQLException
    {
        Node node;
        ProjectItemType projectItemType = ProjectItemType.fromString(resultSet.getString("type"));

        if( ProjectItemType.FILE == projectItemType )
            node = new FileNode();
        else if( ProjectItemType.PACKAGE == projectItemType )
            node = new PackageNode();
        else if( ProjectItemType.PROJECT == projectItemType )
            node = new ProjectNode();
        else
            node = new Node();

        node.setId( resultSet.getString("id") );
        node.setParentId( resultSet.getString("parent_id"));
        node.setText( resultSet.getString("name") );
        node.setDetails( resultSet.getString("details") );
        node.setCreatedOn( resultSet.getDate("created_time") );
        node.setUpdatedOn( resultSet.getDate("updated_time") );

        return node;
    }
}
