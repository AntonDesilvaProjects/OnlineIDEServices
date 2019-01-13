package com.onlineide.constants;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.StringUtils;

public enum ProjectItemType {
    @JsonProperty("project")
    PROJECT("PROJECT"),
    @JsonProperty("package")
    PACKAGE("PACKAGE"),
    @JsonProperty("file")
    FILE("FILE");

    private String text;

    ProjectItemType(String text)
    {
        this.text = text;
    }

    public static ProjectItemType fromString( String str )
    {
        if(!StringUtils.isEmpty(str))
        {
            for( ProjectItemType type : ProjectItemType.values() )
                if( type.text.equalsIgnoreCase(str))
                    return type;
        }
        throw new IllegalArgumentException("No ProjectItemType with the text value '" + str + "'" );
    }
}
