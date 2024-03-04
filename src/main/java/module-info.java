module Zeeslag {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires javafx.media;

    opens Zeeslag to javafx.fxml;
    opens Zeeslag.Fx.Model to javafx.fxml;
    exports Zeeslag;
    exports Zeeslag.Core.Game;
    opens Zeeslag.Core.Game to javafx.fxml;
    exports Zeeslag.Core.Player;
    opens Zeeslag.Core.Player to javafx.fxml;
    exports Zeeslag.Core.Piece;
    opens Zeeslag.Core.Piece to javafx.fxml;
    exports Zeeslag.Core.Dimension;
    opens Zeeslag.Core.Dimension to javafx.fxml;
    exports Zeeslag.Core.Coord;
    opens Zeeslag.Core.Coord to javafx.fxml;
    exports Zeeslag.Core.Bomb;
    opens Zeeslag.Core.Bomb to javafx.fxml;
    exports Zeeslag.Core.Board;
    opens Zeeslag.Core.Board to javafx.fxml;
    opens Zeeslag.Fx.Manager to javafx.fxml;
}
