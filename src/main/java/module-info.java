module com.musicplaylist.musicplaylist_javadb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.musicplaylist to javafx.fxml;
    opens com.musicplaylist.model to javafx.base;
    exports com.musicplaylist;
}