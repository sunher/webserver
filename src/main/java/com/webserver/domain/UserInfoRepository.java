package com.webserver.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserInfoRepository extends MongoRepository<UserInfo, String>, UserInfoRepositoryCustom {



}
