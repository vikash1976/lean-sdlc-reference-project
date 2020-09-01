package id.test.springboottesting.model;

/**
 * @author e1077326
 *
 */
public class User {

    
    private Long id; 
	
    private String email;
    
    private String entryKey;
    
	private String name;
   
    public User(Long id, String email, String entryKey, String name) {
		super();
		this.id = id;
		this.email = email;
		this.entryKey = entryKey;
		this.name = name;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getEntryKey() {
		return entryKey;
	}


	public void setEntryKey(String entryKey) {
		this.entryKey = entryKey;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	

}
