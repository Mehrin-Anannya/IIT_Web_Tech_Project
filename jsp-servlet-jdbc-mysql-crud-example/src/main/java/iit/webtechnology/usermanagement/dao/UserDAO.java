package iit.webtechnology.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import iit.webtechnology.usermanagement.model.User;

public class UserDAO {
	private String jdbcURL = "jdbc:msql://localhost:3306/iit?useSSL=false";
	private String jdbcUserName = "root";
	private String jdbcPassword = "1234";
	
	
	private static final String INSERT_USERS_SQL = "Insert into Users" + "(name, email, country) VALUES " + "(?, ?, ?);";
	private static final String SELECT_USER_BY_ID = "select id, name, email, country from users where id = ?";
	private static final String SELECT_ALL_USERS = "select * from users";
	private static final String UPDATE_USERS_SQL = "update users set name = ?, email = ?, country = ? where id = ?;";
	private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
	
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return connection;
	}
//Create or insert user
	public void insertUser(User user) {
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT_USERS_SQL")){
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getCountry());
			preparedStatement.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
	}
 }
	//Update user
		public boolean updateUser(User user) {
			boolean rowsUpdated = false;
			try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("UPDATE_USERS_SQL")){
				preparedStatement.setString(1, user.getName());
				preparedStatement.setString(2, user.getEmail());
				preparedStatement.setString(3, user.getCountry());
				rowsUpdated = preparedStatement.executeUpdate() > 0;
			}catch(Exception e) {
				e.printStackTrace();
		}
		return rowsUpdated;
	 }
	//Select user by id
		public User selectUser(int id) {
			User user = null;
			//Step 1: Establishing a Connection
			try (Connection connection = getConnection();
				//Step 2: Create a statement using connection object 
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT_USER_BY_ID")){
				preparedStatement.setInt(1, id);
				System.out.println(preparedStatement);
				//Step 3: Execute the query or update query
				ResultSet resultSet = preparedStatement.executeQuery();
				
				//Step 4: Process the ResultSet object
				while(resultSet.next()) {
					String name = resultSet.getString("name");
					String email = resultSet.getString("email");
					String country = resultSet.getString("country");
					user = new User(name, email, country);
				}
			}catch(Exception e) {
				e.printStackTrace();
		}
		return user;
	 }
		
		//Select user by id
				public List<User> selectAllUsers() {
					List<User> users = new ArrayList<>();
					//Step 1: Establishing a Connection
					try (Connection connection = getConnection();
						//Step 2: Create a statement using connection object 
						PreparedStatement preparedStatement = connection.prepareStatement("SELECT_USER_BY_ID")){
						System.out.println(preparedStatement);
						//Step 3: Execute the query or update query
						ResultSet resultSet = preparedStatement.executeQuery();
						
						//Step 4: Process the ResultSet object
						while(resultSet.next()) {
							String name = resultSet.getString("name");
							String email = resultSet.getString("email");
							String country = resultSet.getString("country");
							users.add(new User(name, email, country));
						}
					}catch(Exception e) {
						e.printStackTrace();
				}
				return users;
			 }
		public boolean deleteUser(int id) throws SQLException{
			boolean rowDeleted;
			try(Connection connection = getConnection();
					//Step 2: Create a statement using connection object 
					PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERS_SQL);){
					preparedStatement.setInt(1, id);
					//Step 3: Execute the query or update query
					rowDeleted = preparedStatement.executeUpdate() > 0;
			}
					
			return rowDeleted;
		}
}
