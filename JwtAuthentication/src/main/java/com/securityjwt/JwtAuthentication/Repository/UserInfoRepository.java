package com.securityjwt.JwtAuthentication.Repository;

import com.securityjwt.JwtAuthentication.Document.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends MongoRepository<UserInfo, String> {

}
