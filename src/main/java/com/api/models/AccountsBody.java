package com.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder (setterPrefix = "set")
public class AccountsBody {

	    @JsonProperty("UserName")
	    private String UserName;

	    @JsonProperty("EmailId")
	    private String EmailId;

	    @JsonProperty("Password")
	    private String Password;

	    @JsonProperty("RoleID")
	    private String RoleID;

	    @JsonProperty("UserType")
	    private int UserType;
}
