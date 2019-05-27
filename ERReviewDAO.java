
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//Data Access Object review Table
public class ERReviewDAO {
	public Connection conn;
	//constructor
	ERReviewDAO() throws Exception{
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
	//convert the row into Review object
	private ERReview convertRowToReview(ResultSet rst) throws SQLException{
		//parse row and creates an object
		long id=rst.getLong("id");
		String recommendation= rst.getString("recommendation");
		int merit= rst.getInt("merit_score");
		int relevance= rst.getInt("relevance_score");
		int originality= rst.getInt("originality_score");
		long paperId= rst.getLong("paper_id");
		String reviewer_id= rst.getString("reviewer_id");
		ERReview toReturn = new ERReview(id, recommendation, merit, relevance, originality, paperId, reviewer_id);
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
	//search Reviews by title
	public List<ERReview> searchReviewByPaper(String title) throws SQLException{
		//array to return
		List<ERReview> result_list= new ArrayList<>();
		//prepared statement and result-set objects
		PreparedStatement stm=null;
		ResultSet result = null;
		//try block
		try {
			title+="%";
			//prepare statement ? parameter 1 lastName
			stm=conn.prepareStatement(" SELECT Review.id, Review.recommendation, Review.merit_score, "
					+ "Review.relevance_score, Review.originality_scoreReview.paper_id,  "
					+ "FROM Review INNER JOIN paper ON review.paper_id=paper.id WHERE Paper.title LIKE ?;");
			//parameters
			stm.setString(1, title);
			result= stm.executeQuery();
			//adding rows to the array list to return
			while(result.next()) {
				ERReview tempReview = convertRowToReview(result);
				result_list.add(tempReview);
			}
			//return
			return result_list;
		//close connection finally statement	
		}finally {
			
		}
	}
	//search reviews by reviewer
	public List<ERReview> searchReviewByReviewerName(String name) throws SQLException{
		//array to return
		List<ERReview> result_list= new ArrayList<>();
		//prepared statement and result-set objects
		PreparedStatement stm=null;
		ResultSet result = null;
		//try block
		try {
			name+="%";
			//prepare statement ? parameter 1 lastName
			stm=conn.prepareStatement("SELECT Review.id, Review.recommendation, Review.merit_score, "
					+ "Review.relevance_score, Review.originality_score, Review.paper_id, "
					+ "review.Reviewer_id "
					+ "FROM Review INNER JOIN Reviewer ON Review.reviewer_id=Reviewer.email_address "
					+ "WHERE Reviewer.first_name LIKE ? OR Reviewer.last_name LIKE ?;");
			//parameters
			stm.setString(1, name);
			stm.setString(2, name);
			result= stm.executeQuery();
			//adding rows to the array list to return
			while(result.next()) {
				ERReview tempReview = convertRowToReview(result);
				result_list.add(tempReview);
			}
			//return
			return result_list;
		//close connection finally statement	
		}finally {
			
		}
	}
	//search reviews by reviewer
	public List<ERReview> searchReviewByPaperIdANDpublished(long id) throws SQLException{
		//array to return
		List<ERReview> result_list= new ArrayList<>();
		//prepared statement and result-set objects
		PreparedStatement stm=null;
		ResultSet result = null;
		//try block
		try {
			//prepare statement ? parameter 1 lastName
			stm=conn.prepareStatement("SELECT *"
					+ "FROM Review "
					+ "WHERE paper_id=? AND recommendation='%published%'");
			//parameters
			stm.setLong(1, id);
			result= stm.executeQuery();
			//adding rows to the array list to return
			while(result.next()) {
				ERReview tempReview = convertRowToReview(result);
				result_list.add(tempReview);
			}
			//return
			return result_list;
		//close connection finally statement	
		}finally {
			
		}
	}
	//get all Reviews
	public List<ERReview> getAllReviews() throws Exception{
		//array to return
		List<ERReview> list_Reviews= new ArrayList<>();
		//statement and result-set objects
		Statement stm = null;
		ResultSet result = null;
		
		try {
			stm= conn.createStatement();
			result= stm.executeQuery("SELECT * FROM Review");
			//displaying Reviews
			while(result.next()) {
				ERReview tempReview=convertRowToReview(result);
				list_Reviews.add(tempReview);
			}
			//return statement
			return list_Reviews;
		}
		finally {
			//close the connection
			close(stm, result);
		}
	}
	
}