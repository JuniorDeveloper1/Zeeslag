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

    opens Zeeslag to javafx.fxml;
    opens Zeeslag.fx.Model to javafx.fxml;
    exports Zeeslag;
    exports Zeeslag.modulesVerzinBetereNaamXd.Game;
    opens Zeeslag.modulesVerzinBetereNaamXd.Game to javafx.fxml;
    exports Zeeslag.modulesVerzinBetereNaamXd.Player;
    opens Zeeslag.modulesVerzinBetereNaamXd.Player to javafx.fxml;
    exports Zeeslag.modulesVerzinBetereNaamXd.Piece;
    opens Zeeslag.modulesVerzinBetereNaamXd.Piece to javafx.fxml;
    exports Zeeslag.modulesVerzinBetereNaamXd.Dimension;
    opens Zeeslag.modulesVerzinBetereNaamXd.Dimension to javafx.fxml;
    exports Zeeslag.modulesVerzinBetereNaamXd.Coord;
    opens Zeeslag.modulesVerzinBetereNaamXd.Coord to javafx.fxml;
    exports Zeeslag.modulesVerzinBetereNaamXd.Bomb;
    opens Zeeslag.modulesVerzinBetereNaamXd.Bomb to javafx.fxml;
    exports Zeeslag.modulesVerzinBetereNaamXd.Board;
    opens Zeeslag.modulesVerzinBetereNaamXd.Board to javafx.fxml;
    opens Zeeslag.fx to javafx.fxml;
    opens Zeeslag.fx.Manager to javafx.fxml;
}
