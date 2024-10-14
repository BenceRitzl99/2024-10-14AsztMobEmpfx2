package lan.zold;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateController {

    EmployeeSource employeeSource;

    @FXML
    private TextField cityField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField salaryField;

    @FXML
    void onClickAddButton(ActionEvent event) {
        startAddEmployee();
        
        
    }

    private void startAddEmployee() {
        System.out.println("Hozzáadás indul...");
        Employee emp = new Employee();
        emp.setName(nameField.getText());
        emp.setCity(cityField.getText());
        String salaryStr = salaryField.getText();
        Double salary = Double.parseDouble(salaryStr);
        emp.setSalary(salary);
        employeeSource = new EmployeeSource(new Sqlite());
        employeeSource.addEmployee(emp);
    }

    @FXML
    void onClickBackButton(ActionEvent event) {
        App.setRoot("mainScene");
    }

    @FXML
    void onClickExitButton(ActionEvent event) {
        Platform.exit();
    }



}
