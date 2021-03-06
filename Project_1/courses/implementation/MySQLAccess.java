package implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import com.mysql.jdbc.Driver;
import org.sqlite.*;

import java.util.*;

public class MySQLAccess {


	private CSVparser csvParser = new CSVparser();

	public ResultSet readDatabase(String query) {
		Connection connect = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {	

			// This will load the MySQL driver, each DB has its own driver
			Class.forName("org.sqlite.JDBC");

			// Setup connection with database    getConnection("database", "username", "password")
			connect = DriverManager.getConnection("jdbc:sqlite:resources/SQLite/courses.db");
			// Statements allow to issue SQL queries to the database
	        statement = connect.createStatement();

	        // Result set get the result of the SQL query
	        resultSet = statement.executeQuery(query);




		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return resultSet;
		}
	}

	public List<String> getStudents(String semester, String course) throws SQLException {
		Connection connect = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String word = "Banner_id";
		String[] parts = csvParser.parseCourse(course);
		resultSet = readDatabase("select Banner_id from cs374_anon where Term_Code = '"+semester+"' and Subject_Code = '"+parts[0]+"' and Course_number = '"+parts[1]+"'");
		return writeResultSet(resultSet, word);
	}



	public void writeMetaData(ResultSet resultSet) throws SQLException {
        // Now get some metadata from the database
        // Result set get the result of the SQL query

        System.out.println("The columns in the table are: ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
                System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
        }
    }

    public List<String> writeResultSet(ResultSet resultSet, String column_set) throws SQLException {
        // ResultSet is initially before the first data set
        //System.out.println("Hello");
        List<String> item = new ArrayList<String>();

        while (resultSet.next()) {
            
            item.add(resultSet.getString(column_set));
            //System.out.println(column_set + ": " + firstName);
        }
        return item;
    }

    public String writeString(ResultSet resultSet, String column_set) throws SQLException {
            return resultSet.getString(column_set);
    }

    // You need to close the resultSet
    private void close() {
    	Connection connect = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
        try {
                if (resultSet != null) {
                        resultSet.close();
                }

                if (statement != null) {
                        statement.close();
                }

                if (preparedStatement != null) {
                        preparedStatement.close();
                }

                if (connect != null) {
                        connect.close();
                }
        } catch (Exception e) {

        }
    }

}