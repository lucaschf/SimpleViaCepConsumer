package tsi.ws.viacepconsumer.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import org.springframework.http.HttpStatus;
import tsi.ws.viacepconsumer.model.ZipCode;
import tsi.ws.viacepconsumer.service.ViaCepService;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    @FXML
    private ProgressBar progressBar;

    @FXML
    private TextField zipcodeTextField;

    @FXML
    private Button fetchButton;

    @FXML
    private TextField publicPlaceTextField;

    @FXML
    private TextField neighborhoodTextField;

    @FXML
    private TextField stateTextField;

    @FXML
    private TextField localityTextField;

    @FXML
    private TextField IbgeTextField;

    private final ViaCepService service = new ViaCepService();

    @FXML
    void fetch() {
        fetchZipCode();
    }

    private void fetchZipCode() {
        clearFields();
        inProgress(true);

        try {
            var response = service.getZipCodeInfo(zipcodeTextField.getText());

            if (response.getStatusCode() == HttpStatus.OK) {
                populateFields(response.getBody());
            } else
                showError(response.getStatusCode().getReasonPhrase());
        } catch (Exception e) {
            showError(e.getMessage());
        } finally {
            inProgress(false);
        }
    }

    private void showError(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Via Cep");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);

        alert.showAndWait();
    }

    private void populateFields(ZipCode zipCode) {
        if (zipCode == null) {
            showError("No data found");
            return;
        }

        localityTextField.setText(zipCode.getLocalidade());
        IbgeTextField.setText(zipCode.getIbge());
        stateTextField.setText(zipCode.getUf());
        publicPlaceTextField.setText(zipCode.getLogradouro());
        neighborhoodTextField.setText(zipCode.getBairro());
    }

    private void clearFields() {
        stateTextField.setText("");
        publicPlaceTextField.setText("");
        neighborhoodTextField.setText("");
        IbgeTextField.setText("");
        localityTextField.setText("");
    }

    private void inProgress(boolean inProgress) {
        zipcodeTextField.setEditable(!inProgress);
        fetchButton.setVisible(!inProgress);
        progressBar.setVisible(inProgress);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        progressBar.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
    }
}