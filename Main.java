package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {
	
	public static void main (String args[]) throws Exception {
		
		createTable();
		login login = new login();
		login.run();
		//get();
		
	}

	public static Connection getConnection() throws Exception{

		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://currentc.cdzawpkv8dzt.us-east-2.rds.amazonaws.com:3306/currentC";
			String username = "kostas004";
			String password = "!Rlhnkfl1";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected");
			
			return conn;
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
		
		return null;
	}
	
	public static void createTable() throws Exception{
		Connection con = getConnection();
		try {
	
PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS Users(id int NOT NULL AUTO_INCREMENT, firstName varchar(255),"
		+ " lastName varchar(255), PRIMARY KEY(id))");
		create.executeUpdate();
		
		}catch(Exception e) {
			System.out.println(e);
			}
			finally {System.out.println("Function Complete!");}
	}
	

	public static void insertUser(String first, String last, String username, String password) throws Exception{
		try {
		Connection con = getConnection();
		PreparedStatement insert = con.prepareStatement("INSERT INTO Users(firstName, lastName) VALUES ('"+first+"', '"+last+"')");
		insert.executeUpdate();
		
		userCredentialTable(first,  last,  username,  password);
		//userTable();
		
		} catch(Exception e) {System.out.println(e);}
		finally {
			System.out.println("User Insertion Complete!");
		}
	}
//////////////////////--------------------------SELECTING USERS INFO -------------------------------///////////////////////////////////
	public static ArrayList<String> get () throws Exception {
		try {
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			ResultSet results = stmt.executeQuery("SELECT firstName, lastName FROM Users");
			
			ArrayList<String> array = new ArrayList<String>();
			
			while(results.next()) {
				System.out.print(results.getString("firstName"));
				System.out.print(" ");
				System.out.println(results.getString("lastName"));
				array.add(results.getString("lastName"));
			}
			
			System.out.println("All Records Have Been Selected!");
			return array;
		}catch(Exception e) {System.out.println(e);}
		
		return null;
	}
//////////////////////--------------------------USER'S CURRENCIES TABLE -------------------------------///////////////////////////////////	
	public static void userTable() throws Exception{
	Connection con = getConnection();
	PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS Currencies(id int NOT NULL AUTO_INCREMENT, currency int,"
			+ "rate double, dateUpdated int, PRIMARY KEY(id))");
	create.executeUpdate();
	
	PreparedStatement insert = con.prepareStatement("INSERT INTO Currencies(xml)"
			+ "SELECT * FROM OPENROWSET (BULK, 'https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml', SINGLE_BLOB) AS x ");
	insert.executeUpdate();
	}
	
//////////////////////--------------------------USER'S CREDENTIALS TABLE -------------------------------///////////////////////////////////
	public static void userCredentialTable(String first, String last, String username, String password) throws Exception{
		try {
			Connection con = getConnection();
PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS Credentials(id int, firstName varchar(255), lastName varchar(255),"
					+ " username varchar(255), password varchar(255), FOREIGN KEY(id) REFERENCES Users(id))");
					create.executeUpdate();
					
PreparedStatement insert = con.prepareStatement("INSERT INTO Credentials(firstName, lastName, username, password) VALUES"
					+ " ('"+first+"', '"+last+"', '"+username+"', '"+password+"')");
			insert.executeUpdate();
			}
		catch(Exception e) {System.out.println(e);}
			finally {
				System.out.println("Insert Completed!");
				}
	}
	
}


