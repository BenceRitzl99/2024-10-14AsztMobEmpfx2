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
        conn.close();        
    }

    public void updateEmployee(Employee emp) {
        try {
            tryUpdateEmployee(emp);
        } catch (SQLException e) {
            System.err.println("Hiba! A dolgozó módosítása nem sikerült!");
            System.err.println(e.getMessage());
        }
    }
    private void tryUpdateEmployee(Employee emp) throws SQLException {
        Connection conn = database.connect();
        String sql = """
                update employees
                set
                name = ?,
                city = ?,
                salary = ?
                where
                id = ? 
                """;
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, emp.getName());
        pstmt.setString(2, emp.getCity());
        pstmt.setDouble(3, emp.getSalary());
        pstmt.setInt(4, emp.getId());
        pstmt.executeUpdate();
        conn.close();
    }

    public void deleteEmployee(int id) {
        try {
            tryDeleteEmployee(id);
        } catch (SQLException e) {
            System.err.println("Hiba! A dolgozó törlése nem sikerült!");
            System.err.println(e.getMessage());
        }
    }
    private void tryDeleteEmployee(int id) throws SQLException {
        Connection conn = database.connect();
        String sql = "delete from employees where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        conn.close();
    }
}