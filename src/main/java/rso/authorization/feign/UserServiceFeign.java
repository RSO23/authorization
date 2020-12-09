package rso.authorization.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import rso.authorization.dto.AuthDto;

@FeignClient(name = "user-catalogue")
public interface UserServiceFeign
{

    @GetMapping(value = "/user/auth/{email}")
    AuthDto getAuthDto(@PathVariable String email);

//    @GetMapping(value = "/user/auth/{username}", headers = {"${gateway.http.auth-token-header-name}=${gateway.http.auth-token}"})
//    AuthDto getAuthDto(@PathVariable String username);

}
