package com.webserver.controller;

import com.webserver.domain.UserInfo;
import com.webserver.domain.UserInfoRepository;
import org.apache.commons.mail.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import static com.webserver.util.EmailService.sendVerifyCode;

@RestController
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    Map<String, UserInfo> userInfoMap = new ConcurrentHashMap<>();
    Map<String, String> verifyCodeMap = new ConcurrentHashMap<>();

    @Autowired
    UserInfoRepository userInfoRepository;


    @PostMapping("/hero/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserInfo userInfo) throws IOException, URISyntaxException {
        logger.info("login {}",userInfo);
        UserInfo userInfo4check = userInfoRepository.findOne(userInfo.getAccount());
        if(Objects.isNull(userInfo4check)||!userInfo4check.getPassword().equals(userInfo.getPassword())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok("123456");

    }

    @PostMapping("/hero/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserInfo userInfo, @RequestParam String verifyCode) throws IOException, URISyntaxException {
        logger.info("register {}",userInfo);
        if (!verifyCode.equalsIgnoreCase(verifyCodeMap.get(userInfo.getAccount()))){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userInfoRepository.save(userInfo);
        return ResponseEntity.ok("");

    }

    @GetMapping("/hero/verifyCode")
    public ResponseEntity<?> verifyCode(@Valid @RequestParam String account) throws IOException, URISyntaxException {
        logger.info("verifyCode {}",account);
        String verifyCode = generateVerifyCode();
        verifyCodeMap.put(account, verifyCode);
        try {
            sendVerifyCode(account,verifyCode);
        } catch (EmailException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok("");

    }

    private static char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    private String generateVerifyCode() {
        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode = new StringBuffer();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            String code = String.valueOf(codeSequence[random.nextInt(36)]);

            // 将产生的四个随机数组合在一起。
            randomCode.append(code);
        }
        return randomCode.toString();
    }

}
