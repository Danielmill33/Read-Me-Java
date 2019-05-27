
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//Data Access Object Reviewer
public class ERReviewerDAO {
	public Connection conn;
	//constructor
	ERReviewerDAO() throws Exception{
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
	//convert the row into reviewer object
	private ERReviewer convertRowToReviewer(ResultSet rst) throws SQLException{
		//parse row and creates an object
		String email=rst.getString("email_address");
		String name= rst.getString("first_name");
		String lastName= rst.getString("last_name");
		String reviewerFeedBack= rst.getString("author_feedback");
		String commiteeFeedBack= rst.getString("commitee_feedback");
		String phone=rst.getString("phone_number");
		String affili= rst.getString("affiliation");
		//object creating
		ERReviewer toReturn = new ERReviewer(email, name, lastName, reviewerFeedBack, commiteeFeedBack, phone, affili);
		//return object reference
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
	//search reviewer by name
	public List<ERReviewer> searchReviewerByName(String name) throws SQLException{
		//array to return
		List<ERReviewer> result_list= new ArrayList<>();
		//prepared statement and result-set objects
		PreparedStatement stm=null;
		ResultSet result = null;
		//try block
		try {
			name+="%";
			//prepare statement ? parameter 1 lastName
			stm=conn.prepareStatement("SELECT * FROM reviewer WHERE first_name LIKE ? OR last_name LIKE ?");
			//parameters
			stm.setString(1, name);
			stm.setString(2, name);
			result= stm.executeQuery();
			//adding rows to the array list to return
			while(result.next()) {
				ERReviewer tempReviewer = convertRowToReviewer(result);
				result_list.add(tempReviewer);
			}
			//return
			return result_list;
		//close connection finally statement	
		}finally {
			
		}
	}
	//search reviewer by topic
	public List<ERReviewer> searchReviewerByTopic(String topic) throws SQLException{
		//array to return
		List<ERReviewer> result_list= new ArrayList<>();
		//prepared statement and result-set objects
		PreparedStatement stm=null;
		ResultSet result = null;
		//try block
		try {
			topic+="%";
			//prepare statement ? parameter 1 lastName
			stm=conn.prepareStatement("SELECT reviewer.email_address, reviewer.first_name, reviewer.last_name, "
					+ "reviewer.author_feedback, reviewer.commitee_feedback, reviewer.phone_number, reviewer.affiliation "
					+ "FROM reviewer INNER JOIN topic ON reviewer.email_address=topic.reviewer_email "
					+ "WHERE topic.topic_name LIKE ?;");
			//parameters
			stm.setString(1, topic);
			result= stm.executeQuery();
			//adding rows to the array list to return
			while(result.next()) {
				ERReviewer tempReviewer = convertRowToReviewer(result);
				result_list.add(tempReviewer);
			}
			//return
			return result_list;
		//close connection finally statement	
		}finally {
			
		}
	}
	//get all reviewers
	public List<ERReviewer> getAllReviewers() throws Exception{
		//array to return
		List<ERReviewer> list_reviewer= new ArrayList<>();
		//statement and result-set objects
		Statement stm = null;
		ResultSet result = null;
		
		try {
			stm= conn.createStatement();
			result= stm.executeQuery("SELECT * FROM reviewer");
			//displaying reviewers
			while(result.next()) {
				ERReviewer tempReviewer=convertRowToReviewer(result);
				list_reviewer.add(tempReviewer);
			}
			//return statement
			return list_reviewer;
		}
		finally {
			//close the connection
			close(stm, result);
		}
	}
}
