package fa.training.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import fa.training.dao.CustomerDao;
import fa.training.entities.Customer;

public class CustomerDaoImpl extends DBContext implements CustomerDao {
	public List<Customer> getAll() throws SQLException {
		String sqlQuery = "SELECT CustomerId, CustomerName FROM Customer";
		List<Customer> customers = new ArrayList<Customer>();
		try (Connection conn = getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerId(rs.getInt(1));
				customer.setCustomerName(rs.getString(2));
				customers.add(customer);
			}
		}
		return customers.size() > 0 ? customers : null;
	}

	@Override
	public void insertCustomer(String customerName) throws SQLException {
		String sqlQuery = "INSERT INTO Customer VALUES (?)";
		try (Connection conn = getConnection()) {
			PreparedStatement statement = conn.prepareStatement(sqlQuery);
			statement.setString(1, customerName);
			statement.executeUpdate();
		}
	}

	@Override
	public Customer getCustomerById(int id) throws SQLException {
		String sqlQuery = "SELECT CustomerId, CustomerName FROM Customer WHERE CustomerId = ?";
		try (Connection conn = getConnection()) {
			PreparedStatement statement = conn.prepareStatement(sqlQuery);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return new Customer(rs.getInt(1), rs.getString(2));
			}
		}
		return null;
	}

	@Override
	public void updateCustomer(Customer customer) throws SQLException {
		String sqlQuery = "UPDATE Customer SET CustomerName = ? WHERE CustomerId = ?";
		try (Connection conn = getConnection()) {
			PreparedStatement statement = conn.prepareStatement(sqlQuery);
			statement.setString(1, customer.getCustomerName());
			statement.setInt(2, customer.getCustomerId());
			statement.executeUpdate();
		}
	}

	@Override
	public boolean deleteCustomer(int id) throws SQLException {
		String sqlQuery = "EXEC SP_Customer_DeleteCustomer ?";
		try (Connection conn = getConnection()) {
			CallableStatement statement = conn.prepareCall(sqlQuery);
			statement.setInt(1, id);
			return statement.executeUpdate() == 1 ? true : false;
		}
	}
}
