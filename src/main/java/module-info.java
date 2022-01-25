module com.musicplaylist.musicplaylist_javadb {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.musicplaylist to javafx.fxml;
    exports com.musicplaylist;
}