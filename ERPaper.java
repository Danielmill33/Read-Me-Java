
public class ERPaper {
	//data members
	private long id;
	private String title;
	private String paperAbstract;
	private String file_name;
	private String author_email;
	//constructor with parameters
	ERPaper(long identification, String paper_title, String paper_abs, String file, String authorEmail){
		this.id=identification;
		this.title= paper_title;
		this.paperAbstract= paper_abs;
		this.file_name= file;
		this.author_email=authorEmail;
	}
	//getters
	long getPaperId() {return this.id;}
	String getPaperTitle() {return this.title;}
	String getPaperAbstract() {return this.paperAbstract;}
	String getFileName() {return this.file_name;}
	String getAuthorEmail() {return this.author_email;}
	//setters
	void setPaperId(long newId) {this.id= newId;}
	void setPaperTittle(String newTitle) {this.title=newTitle;}
	void setPaperABstract(String abs) {this.paperAbstract= abs;}
	void setFileName(String fileName) {this.file_name= fileName;}
	void setAuthorEmail(String email) {this.author_email= email;}
	//to String method
	@Override
	public String toString() {
		String result="**********************PAPER******************\n";
		result+="Id:\t\t"+this.getPaperId()+"\n";
		result+="Title:\t\t"+this.getPaperTitle()+"\n";
		result+="Abstract:\t"+this.getPaperAbstract()+"\n";
		result+="File Name:\t"+this.getFileName()+"\n";
		return result;
	}
}