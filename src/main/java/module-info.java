module com.bullet.bulletjournal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.bullet.bulletjournal to javafx.fxml;
    exports com.bullet.bulletjournal;
    exports com.bullet.bulletjournal.Controllers;
    opens com.bullet.bulletjournal.Controllers to javafx.fxml;
}