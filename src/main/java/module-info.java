module org.example.fxcrud_practise {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.fxcrud_practise to javafx.fxml;  // controller package
    opens model to javafx.base;                        // REQUIRED for TableView reflection
    opens DAO to javafx.base;                          // optional but recommended

    exports org.example.fxcrud_practise;
    exports model;
    exports DAO;
}
