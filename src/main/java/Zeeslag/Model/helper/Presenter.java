package Zeeslag.Model.helper;

import javafx.scene.Node;

public interface Presenter {
    /**
     * Represents the Presenter in the MVP structure
     * Includes methods for to grab the Stage.
     * @return
     */
    Node getView();
}
