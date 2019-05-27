
public class ERAuthor {
	//data members
		private String email;
		private String first_name;
		private String last_name;
		//constructor with parameters
		public ERAuthor(String email, String fName, String lName) {
			this.email=email;
			this.first_name=fName;
			this.last_name=lName;
		}
		//getters
		public String getFirstName() {return this.first_name;}
		public String getLastName() {return this.last_name;}
		public String getAuthorEmail() {return this.email;}
		//setters
		void setFirstName(String name) {this.first_name=name;}
		void setLastName(String lastName) {this.last_name= lastName;}
		void setAddress(String email) {this.email=email;}
		//to string method
		@Override
		public String toString() {
			String result="********************AUTHOR******************\n";
			result+="Email:\t\t"+getAuthorEmail()+"\n";
			result+="First name:\t"+getFirstName()+"\n";
			result+="Last name:\t"+getLastName()+"\n";
			return result;
		}

}
