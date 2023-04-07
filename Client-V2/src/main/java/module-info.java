module ru.posluh.clientserver.clientv2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires okhttp3;
    requires com.google.gson;

    exports ru.posluh.clientserver.clientv2;
    exports ru.posluh.clientserver.clientv2.controller;
    exports ru.posluh.clientserver.clientv2.entity;
    exports ru.posluh.clientserver.clientv2.response;
    exports ru.posluh.clientserver.clientv2.utils;

    opens ru.posluh.clientserver.clientv2 to javafx.fxml;
    opens ru.posluh.clientserver.clientv2.controller to javafx.fxml;
    opens ru.posluh.clientserver.clientv2.entity to com.google.gson;
    opens ru.posluh.clientserver.clientv2.response to com.google.gson;
}