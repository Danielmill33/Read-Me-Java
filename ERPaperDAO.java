
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


//Data Access Object Paper Table
public class ERPaperDAO {

	public Connection conn;
	//constructor
	ERPaperDAO() throws Exception{
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
	private ERAuthor convertRowToAuthor(ResultSet rst) throws SQLException{
		//parse row and creates an object
		String email=rst.getString("email_address");
		String name= rst.getString("first_name");
		String lastName= rst.getString("last_name");
		ERAuthor toReturn = new ERAuthor(email, name, lastName);
		//return object
		return toReturn;
	}
	//convert the row into Paper object
	private ERPaper convertRowToPaper(ResultSet rst) throws SQLException{
		//parse row and creates an object
		long id=rst.getLong("id");
		String title= rst.getString("title");
		String paper_abstract= rst.getString("abstract");
		String fileName= rst.getString("file_name");
		String email= rst.getString("author_email");
		ERPaper toReturn = new ERPaper(id, title, paper_abstract, fileName, email);
		//return object
		return toReturn;
	}
	//close connection	
	private static void close(Statement st) throws SQLException {
		close(st, null);
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
	//search Paper by title
	public List<ERPaper> searchPaperByTitle(String title) throws SQLException{
		//array to return
		List<ERPaper> result_list= new ArrayList<>();
		//prepared statement and result-set objects
		PreparedStatement stm=null;
		ResultSet result = null;
		//try block
		try {
			title+="%";
			//prepare statement ? parameter 1 lastName
			stm=conn.prepareStatement("SELECT * FROM Paper WHERE title like ?");
			//parameters
			stm.setString(1, title);
			result= stm.executeQuery();
			//adding rows to the array list to return
			while(result.next()) {
				ERPaper tempPaper = convertRowToPaper(result);
				result_list.add(tempPaper);
			}
			//return
			return result_list;
		//close connection finally statement	
		}finally {
			
		}
	}
	//search paper by author
	public HashMap<ERPaper, ERAuthor> searchPaperByAuthorEmail(String authorEmail) throws SQLException{
		//array to return
		HashMap<ERPaper, ERAuthor> result_map= new HashMap<>();
		//prepared statement and result-set objects
		PreparedStatement stm=null;
		ResultSet result = null;
		//try block
		try {
			authorEmail+="%";
			//prepare statement ? parameter 1 lastName
			stm=conn.prepareStatement("SELECT * FROM Paper "
					+ "INNER JOIN Author ON Paper.author_email=Author.email_address "
					+ "WHERE Paper.author_email LIKE ?");
			//parameters
			stm.setString(1, authorEmail);
			result= stm.executeQuery();
			//adding rows to the array list to return
			while(result.next()) {
				ERPaper tempPaper = convertRowToPaper(result);
				ERAuthor tempAuthor=convertRowToAuthor(result);
				result_map.put(tempPaper, tempAuthor);
			}
			//return
			return result_map;
		//close connection finally statement	
		}finally {
		}
	}
	//search paper by author name
	public List<ERPaper> searchPaperByAuthorName(String name) throws SQLException{
		//array to return
		List<ERPaper> result_list= new ArrayList<>();
		//prepared statement and result-set objects
		PreparedStatement stm=null;
		ResultSet result = null;
		//try block
		try {
			name+="%";
			//prepare statement ? parameter 1 lastName
			stm=conn.prepareStatement("SELECT Paper.id, Paper.title, Paper.abstract, "
					+ "Paper.file_name, Paper.author_email "
					+ "FROM Paper INNER JOIN Author ON Paper.author_email=Author.email_address "
					+ "WHERE Author.first_name LIKE ? OR Author.last_name LIKE ?");
			//parameters
			stm.setString(1, name);
			stm.setString(2, name);
			result= stm.executeQuery();
			//adding rows to the array list to return
			while(result.next()) {
				ERPaper tempPaper = convertRowToPaper(result);
				result_list.add(tempPaper);
			}
			//return
			return result_list;
		//close connection finally statement	
		}finally {
			
		}
	}
	//get all Papers
	public List<ERPaper> getAllPapers() throws Exception{
		//array to return
		List<ERPaper> list_Papers= new ArrayList<>();
		//statement and result-set objects
		Statement stm = null;
		ResultSet result = null;
		
		try {
			stm= conn.createStatement();
			result= stm.executeQuery("SELECT * FROM Paper");
			//displaying Papers
			while(result.next()) {
				ERPaper tempPaper=convertRowToPaper(result);
				list_Papers.add(tempPaper);
			}
			//return statement
			return list_Papers;
		}
		finally {
			//close the connection
			close(stm, result);
		}
	}
	//add Paper
	public void addPaper(ERPaper newPaper, ERAuthor newAuthor) throws SQLException {
		PreparedStatement stm= null;
		PreparedStatement stmAuthor=null;
		try {
			//prepare statement
			stmAuthor=conn.prepareStatement("INSERT INTO Author "
					+ "(email_address, first_name, last_name) "
					+ "VALUES (?, ?, ?)");
			stmAuthor.setString(1, newAuthor.getAuthorEmail());
			stmAuthor.setString(2, newAuthor.getFirstName());
			stmAuthor.setString(3, newAuthor.getFirstName());
			
			stm=conn.prepareStatement("INSERT INTO Paper "
					+ "(id, title, abstract, file_name, author_email) "
					+ "VALUES (?, ?, ?, ?, ?)");
			stm.setLong(1, newPaper.getPaperId());
			stm.setString(2, newPaper.getPaperTitle());
			stm.setString(3, newPaper.getPaperAbstract());
			stm.setString(4, newPaper.getFileName());
			//author.primary key -> foreign key
			stm.setString(5, newAuthor.getAuthorEmail());
			//execute SQL
			stmAuthor.executeUpdate();
			stm.executeUpdate();
		}
		finally {
			close(stm);
		}
	}
	//get count of papers
	public int countOfPapers() throws SQLException {
		int count=0;
		//prepared statement and result-set objects
		Statement stm=null;
		ResultSet result = null;
		//try block
		try {
			//create statement
			stm=conn.createStatement();
			//MYSQL statement
			String sql= "SELECT * FROM Paper;";
			//result set
			result=stm.executeQuery(sql);
			//counting rows
		    while (result.next()) {
		         count++;
		    } 
			return count;
		//close connection finally statement	
		}finally {
			
		}
	}
}