public class ERTopic {
	//data members
	private long id;
	private String name;
	private String reviewer_email;
	//constructor with parameters
	ERTopic(long identification, String topicName, String email){
		this.id=identification;
		this.name= topicName;
		this.reviewer_email= email;
	}
	//getters
	long getTopicId() {return this.id;}
	String getTopicName() {return this.name;}
	String getReviewerEmail() {return this.reviewer_email;}
	//setters
	void setTopicId(long newId) {this.id= newId;}
	void setTopicName(String newTopicName) {this.name= newTopicName;}
	void setReviewerEmail(String email) {this.reviewer_email= email;}
	//toString
	@Override
	public String toString() {
		String result="****************************************\n";
		result+="ID:\t\t\t"+getTopicId()+"\n";
		result+="Topic:\t\t"+getTopicName()+"\n";
		result+="Reviewer Email:\t"+getReviewerEmail()+"\n";
		return result;
	}
	
}
