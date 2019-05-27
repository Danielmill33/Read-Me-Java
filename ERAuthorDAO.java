
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//Data Access Object Author Table
public class ERAuthorDAO {

	public Connection conn;
	//constructor
	public ERAuthorDAO() throws Exception{
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
	//convert the row into Author object
	private ERAuthor convertRowToAuthor(ResultSet rst) throws SQLException{
		//parse row and creates an object
		String email=rst.getString("email_address");
		String name= rst.getString("first_name");
		String lastName= rst.getString("last_name");
		ERAuthor toReturn = new ERAuthor(email, name, lastName);
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
	//search author by last name
	public List<ERAuthor> searchAuthorByName(String name) throws SQLException{
		//array to return
		List<ERAuthor> result_list= new ArrayList<>();
		//prepared statement and result-set objects
		PreparedStatement stm=null;
		ResultSet result = null;
		//try block
		try {
			name=name+"%";
			//prepare statement ? parameter 1 lastName
			stm=conn.prepareStatement("SELECT * FROM author "
					+ "WHERE last_name LIKE ? OR first_name LIKE ?;");
			//parameters
			stm.setString(1, name);
			stm.setString(2, name);
			result= stm.executeQuery();
			//adding rows to the array list to return
			while(result.next()) {
				ERAuthor tempAuthor = convertRowToAuthor(result);
				result_list.add(tempAuthor);
			}
			//return
			return result_list;
		//close connection finally statement	
		}finally {
			
		}
	}
	//search author by email address
	public List<ERAuthor> searchAuthorByEmailAddress(String email) throws SQLException{
		//array to return
		List<ERAuthor> result_list= new ArrayList<>();
		//prepared statement and result-set objects
		PreparedStatement stm=null;
		ResultSet result = null;
		//try block
		try {
			email="%"+email+"%";
			//prepare statement ? parameter 1 lastName
			stm=conn.prepareStatement("SELECT * FROM author WHERE email_address like ?");
			//parameters
			stm.setString(1, email);
			result= stm.executeQuery();
			//adding rows to the array list to return
			while(result.next()) {
				ERAuthor tempAuthor = convertRowToAuthor(result);
				result_list.add(tempAuthor);
			}
			//return
			return result_list;
		//close connection finally statement	
		}finally {
			
		}
	}
	//get all authors
	public List<ERAuthor> getAllAuthors() throws Exception{
		//array to return
		List<ERAuthor> list_authors= new ArrayList<>();
		//statement and result-set objects
		Statement stm = null;
		ResultSet result = null;
		
		try {
			stm= conn.createStatement();
			result= stm.executeQuery("SELECT * FROM author");
			//displaying authors
			while(result.next()) {
				ERAuthor tempAuthor=convertRowToAuthor(result);
				list_authors.add(tempAuthor);
			}
			//return statement
			return list_authors;
		}
		finally {
			//close the connection
			close(stm, result);
		}
	}
	//add Author
	public void addAuthor(ERAuthor newAuthor)throws Exception{
		PreparedStatement stm= null;
		try {
			//prepare statement
			stm=conn.prepareStatement("INSERT INTO Author "
					+ "(first_name, last_name, email_address) "
					+ "VALUES (?, ?, ?)");
			stm.setString(1, newAuthor.getFirstName());
			stm.setString(2, newAuthor.getLastName());
			stm.setString(3, newAuthor.getAuthorEmail());
			//execute SQL
			stm.executeQuery();
		}
		finally {
			close(stm);
		}
	}
	//delete first Author
	public void deleteAuthor() throws Exception{
		List<ERAuthor> temp=this.getAllAuthors();
		//when there are authors
		if(!temp.isEmpty()) {
			//prepared statement
			PreparedStatement stm= conn.prepareStatement("DELETE FROM Author "
			 		+ "WHERE email_address=?;");
			//set string in the prepared statement
			stm.setString(1, temp.get(0).getAuthorEmail());
			//execute sql
			int rowsAffected=stm.executeUpdate();
			System.out.println("Rows Affected:\t"+rowsAffected);
			System.out.println("delete complete");
		}else
			System.out.println("No authors data");
	}
}