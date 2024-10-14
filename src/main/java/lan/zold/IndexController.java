package lan.zold;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;



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
    void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        salaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));

        employeeSource = new EmployeeSource(new Sqlite());
        
        ArrayList<Employee> emps = employeeSource.getEmployees();
        ObservableList<Employee> empList = FXCollections.observableArrayList(emps);

        empTable.setItems(empList);


        // empTable.getItems().add(new Employee(1, "John", "London", 1000.0));
        // empTable.getItems().add(new Employee(2, "Jane", "Paris", 2000.0));
        // empTable.getItems().add(new Employee(3, "Jack", "Wien", 3000.0));
    }


    @FXML
    void OnClickExitButton(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void onClickBackButton(ActionEvent event) {

        App.setRoot("mainScene");

    }

}
