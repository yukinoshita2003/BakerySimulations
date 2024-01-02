module cakemaker.cakemaker {
    requires javafx.controls;
    requires javafx.fxml;


    opens cakemaker.core to javafx.fxml;
    exports cakemaker.core;
}