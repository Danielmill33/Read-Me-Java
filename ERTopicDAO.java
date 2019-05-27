
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ERTopicDAO {
	public Connection conn;
	//constructor
	ERTopicDAO() throws Exception{
		//database URL
		String url="jdbc:mysql://localhost:3306/conference_reviews?user=root&password=Mateo234!";
		try {
		//Connection object to the database with the URL
		conn= DriverManager.getConnection(url);

		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}
	//convert the row into Topic object
	private ERTopic convertRowToTopic(ResultSet rst) throws SQLException{
		//parse row and creates an object
		long id=rst.getLong("id");
		String topicName= rst.getString("topic_name");
		String email= rst.getString("reviewer_email");
		ERTopic toReturn = new ERTopic(id, topicName, email);
		//return object
		return toReturn;
	}
	//close connection
	private static void close(Statement st, ResultSet rs) throws SQLException {
		closeConnection(null, st, rs);
	}
	//close connection
	private static void closeConnection(Connection c, Statement st, ResultSet rt) throws SQLException{
		//closing result-set
		if(rt!=null)
			rt.close();
		//closing statement
		if(st!=null)
			st.close();
		if(c!=null)
			c.close();
	}
	//search Topic
	public List<ERTopic> searchTopic(String name) throws SQLException{
		//array to return
		List<ERTopic> result_list= new ArrayList<>();
		//prepared statement and result-set objects
		PreparedStatement stm=null;
		ResultSet result = null;
		//try block
		try {
			name+="%";
			//prepare statement ? parameter 1 lastName
			stm=conn.prepareStatement("SELECT * FROM Topic WHERE topic_name like ?");
			//parameters
			stm.setString(1, name);
			result= stm.executeQuery();
			//adding rows to the array list to return
			while(result.next()) {
				ERTopic tempTopic = convertRowToTopic(result);
				result_list.add(tempTopic);
			}
			//return
			return result_list;
		//close connection finally statement	
		}finally {
			
		}
	}
	//get all Topics
	public List<ERTopic> getAllTopics() throws Exception{
		//array to return
		List<ERTopic> list_Topics= new ArrayList<>();
		//statement and result-set objects
		Statement stm = null;
		ResultSet result = null;
		
		try {
			stm= conn.createStatement();
			result= stm.executeQuery("SELECT * FROM Topic");
			//displaying Topics
			while(result.next()) {
				ERTopic tempTopic=convertRowToTopic(result);
				list_Topics.add(tempTopic);
			}
			//return statement
			return list_Topics;
		}
		finally {
			//close the connection
			close(stm, result);
		}
	}
	
}