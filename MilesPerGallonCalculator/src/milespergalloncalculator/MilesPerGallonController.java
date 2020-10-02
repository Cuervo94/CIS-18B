package milespergalloncalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MilesPerGallonController {

    @FXML
    private TextField milesDriven;

    @FXML
    private TextField gallonsUsed;

    @FXML
    private TextField totalMPG;

    @FXML
    private void calculateButton (ActionEvent event) {
            BigDecimal miles = new BigDecimal(milesDriven.getText());
            BigDecimal gallon = new BigDecimal(gallonsUsed.getText());
            BigDecimal perGallon = miles.divide(gallon, RoundingMode.CEILING);
            totalMPG.setText(perGallon.toString());
            

        }
    

}
