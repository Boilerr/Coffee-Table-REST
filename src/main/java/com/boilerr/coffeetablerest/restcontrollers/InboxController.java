package com.boilerr.coffeetablerest.restcontrollers;

import com.boilerr.coffeetablerest.ResponseHandler;
import com.boilerr.coffeetablerest.dto.InboxMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;

@RestController
public class InboxController {

        InboxMessage[] inboxMessage = new InboxMessage[]{
                new InboxMessage(1, "2016-11-678 06:43:19.77", "some msg"),
                new InboxMessage(1, "2016-11-16 06:43:19.77", "someyrtyrt msg"),
                new InboxMessage(1, "2016-11-16 06:43:19.77", "some ret45645msg"),
                new InboxMessage(1, "2016-11-17 06:43:19.77", "somefghfgh msg"),
                new InboxMessage(1, "2016-11-16 06:43:19.77", "some msg"),
                new InboxMessage(1, "2016-11-16 06:43:19.77", "some45645 msg"),
                new InboxMessage(1, "2016-11-20 06:43:19.77", "some m456sg"),
                new InboxMessage(1, "2016-11-16 06:43:19.77", "some456 msg"),
                new InboxMessage(1, "2016-11-16 06:43:19.77", "some msg"),
                new InboxMessage(1, "2016-11-16 06:43:19.77", "some 456456msg"),
                new InboxMessage(1, "2016-11-556 06:43:19.77", "some ffffmsg"),
                new InboxMessage(1, "2016-11-16 06:43:19.77", "some8888 msg"),
        };

        @CrossOrigin(origins = "http://localhost:4200")
        @RequestMapping("/api/inbox")
        public ResponseEntity<Object> color(@RequestParam(value = "name", defaultValue = "Blue") String name) {
            Date date = new Date();
            System.out.println(new Timestamp(date.getTime()) + "    request to: /api/inbox");
            return ResponseHandler.generateResponse(HttpStatus.OK, false, "Success", inboxMessage);
        }
    }
