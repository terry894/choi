package com.newlecture.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.newlecture.web.ws.handler.ChatWebSocketHandler;

//@Configuration 컴포넌트스캔에는 컨피규레이션이 포함되어있다. 해도 무방 안해도 무방...
@ComponentScan(basePackages = "com.newlecture.web.ws.handler")
@EnableWebSocket
public class NewWebSocketConfig implements WebSocketConfigurer{
   
   @Autowired
   private ChatWebSocketHandler chatSocketHandler;

   @Override
   public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
      registry.addHandler(chatSocketHandler, "chat");
   }

}