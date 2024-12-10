package com.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder (setterPrefix = "set")
public class UserAuthBody {

	    @JsonProperty("EmailId")
	    private String EmailId;

	    @JsonProperty("Password")
	    private String Password;

	    @JsonProperty("FingerPrint")
	    private String FingerPrint;

}
