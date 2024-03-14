module Zeeslag {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens Zeeslag to javafx.fxml;
    exports Zeeslag;
}