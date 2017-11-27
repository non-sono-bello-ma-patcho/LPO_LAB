import java.util.Random;

public class PersonClass{
	private String firstname;
	private String secondname;
	private long socialsecurity;
	private PersonClass partner;

	// constructors:
	public PersonClass(String Fname, String Sname, long SSecurity, PersonClass Par){
		this.firstname = Fname;
		this.secondname = Sname;
		this.socialsecurity = SSecurity;
		this.partner = Par;		
	}

	public String getfirstname(){
		return this.firstname;
	}

	public String getsecondname(){
		return this.secondname;
	}

	public long getsocialsecurity(){
		return this.socialsecurity;
	}

	public PersonClass getPartner(){
		return this.partner;
	}

	public boolean isSingle(){
		return this.partner == null;
	}

	public static boolean join(PersonClass p1, PersonClass p2){
		if(p1!=p2 && p2.isSingle() && p1.isSingle()){
			p1.partner = p2;
			p2.partner = p1;
			return true;
		}
		return false;
	}

	public static boolean divorce(PersonClass p1, PersonClass p2){
		if(p1==p2.getPartner() && p2==p1.getPartner()){
			p1.partner = null;
			p2.partner = null;
			return true;
		}
		return false;
	}
}
