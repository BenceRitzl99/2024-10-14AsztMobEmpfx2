package lan.zold;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployeeSource {
    Database database;
    public EmployeeSource(Database database) {
        this.database = database;
    }
    public ArrayList<Employee> getEmployees() {
        try {
            return tryGetEmployees();
        } catch (SQLException e) {
            System.err.println("Hiba! A lekérdezés sikertelen!");
            System.err.println(e.getMessage());
            return null;
        }
    }

    private ArrayList<Employee> tryGetEmployees() throws SQLException{
        Connection conn = database.connect();
        String sql = "select * from employees";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        ArrayList<Employee> empList = new ArrayList<>();

        while(rs.next()) {
            Employee emp = new Employee();
            emp.setId(rs.getInt("id"));
            emp.setName(rs.getString("name"));
            emp.setCity(rs.getString("city"));
            emp.setSalary(rs.getDouble("salary"));
            empList.add(emp);
        }
        return empList;
    }
    public void addEmployee(Employee employee) {
        try {
            tryAddEmployee(employee);
        } catch (SQLException e) {
            System.err.println("Hiba! Hozzáadás sikertelen!");
            System.err.println(e.getMessage());            
        }
    }

    public void tryAddEmployee(Employee employee) throws SQLException {
        Connection conn = database.connect();
        String sql = """
                insert into employees
                (name, city, salary)
                values
                (?, ?, ?)                
                """;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, employee.getName());
        ps.setString(2, employee.getCity());
        ps.setDouble(3, employee.getSalary());
        int records = ps.executeUpdate();
        System.err.println(records);        
    }
}
