package daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    // CRUD - Retrieve
    @Override
    public Employee get(int id) throws SQLException {

        Connection con = Database.getConnection();
        Employee employee = null;

        String sql = "SELECT id, employee_id, first_name, last_name, dept_id FROM employees WHERE id = ? or first_name=?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, id);
        ps.setString(2, "Rick");

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int oid = rs.getInt("id");
            int employeeId = rs.getInt("employee_id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            int deptId = rs.getInt("dept_id");

            employee = new Employee(oid, employeeId, firstName, lastName, deptId);
        }

        Database.closeResultSet(rs);
        Database.closePreparedStatement(ps);
        Database.closeConnection(con);

        return employee;
    }

    // CRUD - Retrieve all
    @Override
    public List<Employee> getAll() throws SQLException {

        Connection con = Database.getConnection();
        String sql = "SELECT id,employee_id,first_name,last_name,dept_id FROM employees";

        List<Employee> employees = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt("id");
            int employeeId = rs.getInt("employee_id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            int deptId = rs.getInt("dept_id");

            employees.add(new Employee(id, employeeId, firstName, lastName, deptId));
        }

        return employees;
    }

    // CRUD - CREATE/INSERT
    @Override
    public int save(Employee employee) throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }

    // CRUD - CREATE/INSERT
    @Override
    public int insert(Employee employee) throws SQLException {
        Connection con = Database.getConnection();

        String sql = "INSERT INTO employees (employee_id, first_name, last_name, dept_id) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, employee.getEmployeeId());
        ps.setString(2, employee.getFirstName());
        ps.setString(3, employee.getLastName());
        ps.setInt(4, employee.getDeptId());

        int result = ps.executeUpdate();

        Database.closePreparedStatement(ps);
        Database.closeConnection(con);
        return result;
    }

    // CRUD - UPDATE
    @Override
    public int update(Employee employee) throws SQLException {

        Connection con = Database.getConnection();
        String sql = "UPDATE employees set employee_id=?, first_name=?, last_name=?,dept_id=? WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, employee.getEmployeeId());
        ps.setString(2, employee.getFirstName());
        ps.setString(3, employee.getLastName());
        ps.setInt(4, employee.getDeptId());
        ps.setInt(5, employee.getId());

        int result = ps.executeUpdate();

        Database.closePreparedStatement(ps);
        Database.closeConnection(con);

        return result;
    }

    // CRUD - DELETE
    @Override
    public int delete(Employee employee) throws SQLException {
        Connection con = Database.getConnection();

        String sql = "DELETE FROM employees WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, employee.getId());

        int result = ps.executeUpdate();

        Database.closePreparedStatement(ps);
        Database.closeConnection(con);
        return result;
    }

}
