module tsi.ws.viacepconsumer {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires spring.boot;
    requires spring.context;
    requires spring.web;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;

    opens tsi.ws.viacepconsumer to javafx.fxml;
    opens tsi.ws.viacepconsumer.controller;
    opens tsi.ws.viacepconsumer.model;

    exports tsi.ws.viacepconsumer;
}