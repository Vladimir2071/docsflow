package lva.docsflow.catalogsDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import lva.docsflow.catalogs.*;

import java.sql.SQLException;
import java.sql.Statement;

public class LoadProductsToDB {

	private Connection conn;

	public LoadProductsToDB(Connection conn) throws SQLException {
		this.conn = conn;
	}

	// load array objects Product
	public int insertArrProducts(ArrayList<Products> products) throws SQLException {
		
		if (countProducts() != 0) {
			// need truncate table products
				
			try {
				Statement st = conn.createStatement();
				st.executeUpdate ("TRUNCATE TABLE Products");
            }
            catch (SQLException e) {
            	e.getStackTrace();
            }
		}
		
		String querySQL = "INSERT products VALUES (?,?,?,?,?)";
		PreparedStatement pStatement = conn.prepareStatement(querySQL);
		
		int count = 0;
		int batchSize = 100;
		long startTime = System.currentTimeMillis();
		//for (Iterator iterator = products.iterator(); iterator.hasNext();) {
		//	Product product = (Product) iterator.next();
		//for (int count = 0; count < products.size(); count++) {
		//	Product product = products.get(count);
		for (Products product : products) {
			
			pStatement.setString(1, product.getCode());
			pStatement.setString(2, product.getName());
			pStatement.setInt(3, product.getQty());
			pStatement.setFloat(4, product.getVoleme());
			pStatement.setFloat(5, product.getWeight());
			
			pStatement.addBatch();
			
			count++;
            if (count % batchSize == 0) {
                pStatement.executeBatch();
            }
			
			//pStatement.executeUpdate();
		}
		pStatement.executeBatch();
		
		
	    long endTime = System.currentTimeMillis();
	    long elapsedTime = (endTime - startTime); //in seconds
	    System.out.println("Total time required to execute is :" + elapsedTime);

		return 1;
	}

	public int countProducts() throws SQLException {
		String querySQL = "SELECT count(*) FROM products";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(querySQL);

		int count = 0;

		while (rs.next()) {
			count = rs.getInt(1);
		}

		return count;
	}
}
