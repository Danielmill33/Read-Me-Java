public class ERReviewer {
	//data members of reviewer
	private String email_address;
	private String first_name;
	private String last_name;
	private String author_feedback;
	private String commitee_feedback;
	private String phone_number;
	private String affiliation;
	//constructor with parameters
	ERReviewer(String email, String fName, String lName, String au_fe, String co_fe, String phone, String afli){
		this.email_address=email;
		this.first_name= fName;
		this.last_name= lName;
		this.author_feedback= au_fe;
		this.commitee_feedback=co_fe;
		this.phone_number=phone;
		this.affiliation= afli;
	}
	//getters
	String getEmail() {return this.email_address;}
	String getFirstName() {return this.first_name;}
	String getLastName() {return this.last_name;}
	String getAuthorFeedBack() {return this.author_feedback;}
	String getCommiteeFeedBack() {return this.commitee_feedback;}
	String getPhoneNumber() {return this.phone_number;}
	String getAffiliation() {return this.affiliation;}
	//setters
	void setEmail(String email) {this.email_address= email;}
	void setFirstName(String name) {this.first_name= name;}
	void setLastName(String lastName) {this.last_name= lastName;}
	void setAuthorFeedBack(String authorFeedBack) {this.author_feedback= authorFeedBack;}
	void setCommiteeFeedBack(String commiteeFeedBack) {this.commitee_feedback= commiteeFeedBack;}
	void setPhoneNumber(String phone) {this.phone_number= phone;}
	void setAffiliation(String affli) {this.affiliation= affli;}
	//toString
	@Override
	public String toString() {
		String result="****************************************\n";
		result+="Email:\t\t"+getEmail()+"\n";
		result+="First name:\t"+getFirstName()+"\n";
		result+="Last name:\t"+getLastName()+"\n";
		return result;
	}
	
}