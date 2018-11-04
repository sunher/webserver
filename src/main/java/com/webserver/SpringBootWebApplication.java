package com.webserver;

import com.webserver.domain.Domain;
import com.webserver.domain.DomainRepository;
import com.webserver.domain.UserInfo;
import com.webserver.domain.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.util.List;

@SpringBootApplication
public class SpringBootWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }

    @Bean
    CommandLineRunner xxx(UserInfoRepository userInfoRepository) {



        return args -> {
            UserInfo userInfo = new UserInfo();
            userInfo.setAccount("331471400@qq.com");
            userInfo.setAccountType("email");
            userInfo.setPassword("123456");
            userInfoRepository.save(userInfo);
            UserInfo obj = userInfoRepository.findOne(userInfo.getAccount());
            System.out.println(obj);

        };

    }


    @Bean
    CommandLineRunner init(DomainRepository domainRepository) {


        return args -> {
            Domain domain = new Domain();
            domain.setDisplayAds(true);
            domain.setDomain("xxx");
            domain.setId(7L);
            domainRepository.save(domain);
            Domain obj = domainRepository.findOne(7L);
            System.out.println("Domain"+obj);



//            Domain obj2 = domainRepository.findFirstByDomain("mkyong.com");
//            System.out.println(obj2);
//
//            obj2.setDisplayAds(true);
//            domainRepository.save(obj2);
//
//            int n = domainRepository.updateDomain("mkyong.com", true);
//            System.out.println("Number of records updated : " + n);
//
//            Domain obj3 = domainRepository.findOne(2000001L);
//            System.out.println(obj3);
//
//            Domain obj4 = domainRepository.findCustomByDomain("google.com");
//            System.out.println(obj4);
//
//            List<Domain> obj5 = domainRepository.findCustomByRegExDomain("google");
//            obj5.forEach(x -> System.out.println(x));

        };

    }

    //remove _class
        /*MappingMongoConverter converter =
                new MappingMongoConverter(mongoDbFactory, new MongoMappingContext());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));*/
    @Bean
    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory,
                                       MongoMappingContext context) {

        MappingMongoConverter converter =
                new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), context);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory, converter);

        return mongoTemplate;

    }

}
