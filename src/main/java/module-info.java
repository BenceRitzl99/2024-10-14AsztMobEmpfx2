module lan.zold {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive java.sql;

    opens lan.zold to javafx.fxml;
    exports lan.zold;
}
