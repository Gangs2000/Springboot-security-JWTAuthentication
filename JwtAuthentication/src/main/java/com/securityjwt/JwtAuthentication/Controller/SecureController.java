package com.securityjwt.JwtAuthentication.Controller;

import com.securityjwt.JwtAuthentication.Document.UserInfo;
import com.securityjwt.JwtAuthentication.Exception.GlobalException;
import com.securityjwt.JwtAuthentication.Repository.UserInfoRepository;
import com.securityjwt.JwtAuthentication.Service.JwtUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jwt")
public class SecureController {

    @Autowired UserInfoRepository userInfoRepository;
    @Autowired JwtUtilService jwtUtilService;
    @Autowired AuthenticationManager authenticationManager;

    @GetMapping("/secure-api/one")
    public String secureAPIOne(){
        return "API 1 - Secure Page has been rendered successfully after JWT authentication process";
    }

    @GetMapping("/secure-api/two")
    public String SecureAPITwo(){
        return "API 2 - Secure Page has been rendered successfully after JWT authentication process";
    }

    @GetMapping("/secure-api/three")
    public String SecureAPIThree(){
        return "API 3 - Secure Page has been rendered successfully after JWT authentication process";
    }

    @PostMapping(path="/authentication",consumes = "application/json")
    public String jwtTokenGenerator(@RequestBody UserInfo userInfo) throws GlobalException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userInfo.getEmailId(), userInfo.getPassword()));
        }
        catch(GlobalException ex){
            throw new GlobalException("Invalid user ID or password..");
        }
        return jwtUtilService.generateToken(userInfo.getEmailId());
    }

    @PostMapping("/add-new-user")
    public ResponseEntity<UserInfo> addNewUser(@RequestBody UserInfo userInfo){
        return new ResponseEntity<>(userInfoRepository.save(userInfo), HttpStatus.CREATED);
    }
}
