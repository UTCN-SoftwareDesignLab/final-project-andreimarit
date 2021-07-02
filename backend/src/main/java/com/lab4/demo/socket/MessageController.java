//package com.lab4.demo.socket;
//
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.web.util.HtmlUtils;
//
//public class MessageController {
//
//    @MessageMapping("/message")
//    @SendTo("/socket/greeting")
//    public Greeting greeting(MessageModel message) throws Exception {
//        Thread.sleep(1000); // simulated delay
//        return new Greeting("patient with name" + HtmlUtils.htmlEscape(message.getPatient()) + "has an appointment at" + HtmlUtils.htmlEscape(message.getDate()) + "!");
//    }
//}
