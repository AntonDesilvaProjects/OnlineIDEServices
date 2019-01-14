package com.onlineide.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.onlineide.dto.Node;

import java.util.List;

public class ProjectExplorerTreeResponse extends BaseServiceResponse<List<Node>> {
    @JsonProperty("children")
    private List<Node> response;
}
