package com.reactive.reactive.todo.list.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.h2.tools.Server;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@Component
public class H2Console {

    private Server webServer;

    @Value("${webclientexample.postsapi.h2-console-port}")
    Integer h2ConsolePort;

    @EventListener(ContextRefreshedEvent.class)
    public void start() throws java.sql.SQLException {
        this.webServer = org.h2.tools.Server.createWebServer("-webPort", h2ConsolePort.toString(), "-tcpAllowOthers").start();
    }

    @EventListener(ContextClosedEvent.class)
    public void stop() {
        this.webServer.stop();
    }

}