package lan.zold;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;




public class IndexController {

    EmployeeSource employeeSource;

    @FXML
    private TableView<Employee> empTable;

    @FXML
    private TableColumn<Employee, Integer> idCol;

    @FXML
    private TableColumn<Employee, String> cityCol;

    @FXML
    private TableColumn<Employee, String> nameCol;

    @FXML
    private TableColumn<Employee, Double> salaryCol;

    @FXML
    private TextField cityField;

    @FXML
    private TextField idField;

    @FXML
    private TextField salaryField;

    @FXML
    private TextField nameField;

    @FXML
    void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        salaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));

        employeeSource = new EmployeeSource(new Sqlite());
       
        setEmpTable();


       
    }

    private void setEmpTable(){
        ArrayList<Employee> emps = employeeSource.getEmployees();
        ObservableList<Employee> empList = FXCollections.observableArrayList(emps);
        empTable.setItems(empList);
        
    }


    @FXML
    void OnClickExitButton(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void onClickBackButton(ActionEvent event) {

        App.setRoot("mainScene");

    }

    @FXML
    void OnClickModifyButton(ActionEvent event) {
        Employee emp = new Employee();
        emp.setId(Integer.parseInt(idField.getText()));
        emp.setName(nameField.getText());
        emp.setCity(cityField.getText());
        emp.setSalary(Double.parseDouble(salaryField.getText()));
        
        employeeSource.updateEmployee(emp);
        setEmpTable();
        idField.setText("");
        nameField.setText("");
        cityField.setText("");
        salaryField.setText("");
    }

    @FXML
    void OnClickDeleteButton(ActionEvent event) {
       Employee emp = empTable.getSelectionModel().getSelectedItem();
       employeeSource.deleteEmployee(emp.getId());
       setEmpTable();


    }


    @FXML
    void onMouseClick(MouseEvent event) {
        if(event.getClickCount() == 2) {
            
            Employee emp = empTable.getSelectionModel().getSelectedItem();
            idField.setText(emp.getId().toString());
            nameField.setText(emp.getName());
            cityField.setText(emp.getCity());
            salaryField.setText(emp.getSalary().toString());
        }

    }



}
