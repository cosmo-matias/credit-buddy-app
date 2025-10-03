module br.com.ducosmo.creditbuddy {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens br.com.ducosmo.creditbuddy to javafx.fxml;
    exports br.com.ducosmo.creditbuddy;
}