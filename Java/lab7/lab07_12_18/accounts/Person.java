package lab07_12_18.accounts;

/* invariant
 * firstName!=null && secondName!=null && spouse!=this
 * p1.spouse!=p2 || p2.spouse==p1 && (p1==p2) == (p1.socialSN==p2.socialSN)
 */

public class Person implements Client {
	private static long availableSSN;
	private final String firstName;
	private final String secondName;
	private final long socialSN;
	private Person spouse; // optional :)

	private static void checkBothNonNull(Object o1, Object o2) {
		if (o1 == null || o2 == null)
			throw new NullPointerException();
	}

	public static void join(Person p1, Person p2) {
		Person.checkBothNonNull(p1, p2);
		if (p1 == p2 || p1.spouse != null || p2.spouse != null)
			throw new IllegalArgumentException();
		p1.spouse = p2;
		p2.spouse = p1;
	}

	public static void divorce(Person p1, Person p2) {
		Person.checkBothNonNull(p1, p2);
		if (p1.spouse != p2)
			throw new IllegalArgumentException();
		p1.spouse = null;
		p2.spouse = null;
	}

	public Person(String firstName, String secondName) {
		Person.checkBothNonNull(firstName, secondName);
		this.firstName = firstName;
		this.secondName = secondName;
		this.socialSN = Person.availableSSN++;
	}

	/* (non-Javadoc)
	 * @see lab07_12_18.Client#getFirstName()
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/* (non-Javadoc)
	 * @see lab07_12_18.Client#getSecondName()
	 */
	public String getSecondName() {
		return this.secondName;
	}

	/* (non-Javadoc)
	 * @see lab07_12_18.Client#getSocialSecurity()
	 */
	public long getSocialSecurity() {
		return this.socialSN;
	}

	/* (non-Javadoc)
	 * @see lab07_12_18.Client#getSpouse()
	 */
	public Person getSpouse() {
		return this.spouse;
	}

	/* (non-Javadoc)
	 * @see lab07_12_18.Client#isSingle()
	 */
	public boolean isSingle() {
		return this.spouse == null;
	}
}
