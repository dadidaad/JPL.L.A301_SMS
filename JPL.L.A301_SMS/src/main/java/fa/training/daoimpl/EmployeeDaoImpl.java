package fa.training.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fa.training.dao.EmployeeDao;
import fa.training.entities.Employee;

public class EmployeeDaoImpl extends DBContext implements EmployeeDao {
	@Override
	public boolean insert(Employee employee) throws SQLException {
		String sqlQuery = "INSERT INTO Employee VALUES (?, ?, ?)";
		try(Connection conn = getConnection()){
			PreparedStatement statement = conn.prepareStatement(sqlQuery);
			statement.setString(1, employee.getEmployeeName());
			statement.setDouble(2, employee.getSalary());
			statement.setInt(3, employee.getSpvrId());
			return statement.executeUpdate() ==  1 ? true : false;
		}
	}
	
	public Employee getEmployeeById(int id) throws SQLException{
		String sqlQuery = "SELECT EmployeeId, EmployeeName, Salary, SupervisorId FROM Employee WHERE EmployeeId = ?";
		try(Connection conn = getConnection()){
			PreparedStatement statement = conn.prepareStatement(sqlQuery);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				return new Employee(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));
			}
		}
		return null;
	}
	
	@Override
	public List<Employee> getAll() throws SQLException {
		String sqlQuery = "SELECT EmployeeId, EmployeeName, Salary, SupervisorId FROM Employee";
		List<Employee> employees = new ArrayList<Employee>();
		try(Connection conn = getConnection()){
			PreparedStatement statement = conn.prepareStatement(sqlQuery);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				Employee employee = new Employee(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));
				employees.add(employee);
			}
		}
		return employees.size() > 0 ? employees : null;
	}
}
