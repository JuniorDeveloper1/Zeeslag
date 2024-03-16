package Zeeslag.Model.helper;

public interface MVPView {
    /**
     * Defines the contract for a view class in the MVP structure.
     * Includes methods for  initializing and laying out nodes.
     */
    void initialize();
    void initializeNodes();
    void layoutNodes();
}
