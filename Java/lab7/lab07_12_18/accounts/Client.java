package lab07_12_18.accounts;

public interface Client {

	String getFirstName();

	String getSecondName();

	long getSocialSecurity();

	Person getSpouse();

	boolean isSingle();

}