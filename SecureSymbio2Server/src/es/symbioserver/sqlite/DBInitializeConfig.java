package es.symbioserver.sqlite;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBInitializeConfig {

	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	public void initialize(){
		try {
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
		
			
			//CREATE TABLE User
			/////////////////////////////////////////////////////////////
			statement.execute("DROP TABLE IF EXISTS User");
			statement.executeUpdate(
					"CREATE TABLE User("+
					"ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"NAME CHAR(50),"+
					"ROLE CHAR(50),"+
					"EMAIL CHAR(50),"+
					"PASSWORD CHAR(50),"+
					"ENABLED INT"+
					")");
			
			statement.executeUpdate(
				"INSERT INTO User(name,role,email,password,enabled) VALUES"+	
				"('user1','USER','prueba@prueba.com','user1',1)"
			);
			/////////////////////////////////////////////////////////////
			
			//CREATE TABLE Posts
			/////////////////////////////////////////////////////////////
			
			statement.execute("DROP TABLE IF EXISTS Posts");
			statement.executeUpdate(
					"CREATE TABLE Posts ("+
					"ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"TITLE CHAR(30),"+
					"CONTENT CHAR(150),"+
					"USERID INTEGER,"+
					"DATE CHAR(22)," +
					"FOREIGN KEY (USERID) REFERENCES USER(ID)"+
					")");
			
			statement.executeUpdate(
					"INSERT INTO Posts (title,content,userid,date) VALUES" + 
					"('post one','this post is number one',1,'1978-01-01 01:01:01')"
			);
			
			/////////////////////////////////////////////////////////////
			
			statement.close();
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
