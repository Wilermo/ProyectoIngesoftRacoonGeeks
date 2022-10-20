module view {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires jakarta.xml.bind;
    requires java.logging;
    requires java.desktop;

    exports view;
    opens view to javafx.fxml;
    exports model;
    opens  model to javafx.fxml;
  exports enumerados;
  opens enumerados to javafx.fxml;
    exports interfaceProgram.Global;
    opens interfaceProgram.Global to javafx.fxml;
    exports interfaceProgram;
    opens interfaceProgram to javafx.fxml;

}