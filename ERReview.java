
public class ERReview {
	//data members
	private long id;
	private String recommendation;
	private int meritScore;
	private int relevanceScore;
	private int originalityScore;
	private long paperId;
	private String reviewerEmail;
	//constructor with parameters
	ERReview(long id, String recommendation, int merit, int relevance, int originality, long paper_id, String reviewer_email){
		this.id= id;
		this.recommendation= recommendation;
		this.meritScore=merit;
		this.relevanceScore= relevance;
		this.originalityScore= originality;
		this.paperId= paper_id;
		this.reviewerEmail= reviewer_email;
	}
	//getters
	long getReviewId() {return this.id;}
	String getRecommendation() {return this.recommendation;}
	int getMeritScore() {return this.meritScore;}
	int getRelevanceScore() {return this.relevanceScore;}
	int getOriginalityScore() {return this.originalityScore;}
	long getPaperIdReference() {return this.paperId;}
	String getReviewerEmailReference() {return this.reviewerEmail;}
	//setters
	void setReviewId(long id) {this.id=id;}
	void setRecommendation(String newRecommendation) {this.recommendation= newRecommendation;}
	void setMeritScore(int score) {this.meritScore= score;}
	void setRelevanceScore(int score) {this.relevanceScore= score;}
	void setOriginalityScore(int score) {this.originalityScore= score;}
	//to string method
	@Override
	public String toString() {
		String result="****************************************\n";
		result+="ID:\t\t"+getReviewId()+"\n";
		result+="Topic:\t"+getRecommendation()+"\n";
		result+="Merit: "+getMeritScore()+" Relevance: "+getRelevanceScore()+" Orinality: "+getOriginalityScore()+"\n";
		result+="Paper id: "+getPaperIdReference()+ " Reviewer: "+getReviewerEmailReference();
		return result;
	}
	
}
