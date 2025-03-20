package com.ELS.eLibrary.util.constatnt;




public enum Role {


    ADMIN("ROLE_ADMIN"),LIBRARIAN("ROLE_LIBRARIAN"),STUDENT("ROLE_STUDENT"),USER("ROLE_USER");
    

    private final String authority;

    Role(String authority) {
        this.authority = authority;
    }

	public String getAuthority() {
		return authority;
	}
     
}
