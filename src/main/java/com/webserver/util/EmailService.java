package com.webserver.util;


import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EmailService
{

        public static void main(String[] args) throws EmailException {

            Email email = new SimpleEmail();
            email.setHostName("smtp.qq.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("331471400@qq.com", "knsxrmukfysycaeb"));
            email.setSSL(true);
            email.setFrom("331471400@qq.com");
            email.setSubject("TestMail");
            email.setMsg("This is a test mail ... :-)");
            email.addTo("2416999861@qq.com");
            email.send();
        }

        public static void sendVerifyCode(String account,String verifyCode)throws EmailException {

            Email email = new SimpleEmail();
            email.setHostName("smtp.qq.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("331471400@qq.com", "knsxrmukfysycaeb"));
            email.setSSL(true);
            email.setFrom("331471400@qq.com");
            email.setSubject("验证码");
            email.setMsg(verifyCode);
            email.addTo(account);
            email.send();
        }

}