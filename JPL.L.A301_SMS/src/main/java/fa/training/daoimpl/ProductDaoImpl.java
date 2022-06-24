package fa.training.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fa.training.dao.ProductDao;
import fa.training.entities.Product;

public class ProductDaoImpl extends DBContext implements ProductDao {
	@Override
	public Product getProductById(int productId) throws SQLException {
		String sqlQuery = "SELECT ProductId, ProductName, ListPrice FROM Product WHERE ProductId = ?";
		try(Connection conn = getConnection()){
			PreparedStatement statement = conn.prepareStatement(sqlQuery);
			statement.setInt(1, productId);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				return new Product(rs.getInt(1), rs.getString(2), rs.getDouble(3));
			}
		}
		return null;
	}
}
