package com.sinem;

import com.sinem.dto.ActivateRequestDto;
import com.sinem.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class MailServiceApplication {
   // @Autowired
    //EmailSenderService service;

    public static void main(String[] args) {
        SpringApplication.run(MailServiceApplication.class,args);
    }
    /**@EventListener(ApplicationReadyEvent.class)
    public void SendMail(){
        ActivateRequestDto dto=ActivateRequestDto.builder()
                .email("s.k.donmez123@gmail.com")
                .activatedCode("xx-yyy")
                .build();
        service.sendActivateCode(dto);

    }*/



}