package rso.authorization.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthDto {

    @NotNull
    @ApiModelProperty(hidden = true)
    private Long id;
    @NotNull
    @NotBlank
    @ApiModelProperty(value = "Email", position = 1)
    private String email;
    @NotNull
    @NotBlank
    @ApiModelProperty(value = "Password", position = 2)
    private String password;
}
