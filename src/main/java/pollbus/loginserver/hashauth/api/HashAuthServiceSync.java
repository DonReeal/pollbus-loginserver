package pollbus.loginserver.hashauth.api;

public interface HashAuthServiceSync {
	
	/**
	 * Generates a hashed version of a password using salt; Salt is prepended to
	 * the the result String.
	 * 
	 * @param plaintext - only UTF-8 encoded values will be hashed
	 * @return hashedResult - UTF-8 encoded hashed value with the used salt prepended
	 */
	public String createHash(String plaintext);

	/**
	 * Checks if the plaintext-hash equals the passed hashedValue
	 * 
	 * @param plaintext - only UTF8 encoded plaintexts can yield in a result#complete(true)
	 * @param previouslyHashedValue
	 * @return if the passed hashedValue originates from the passed plaintext
	 */
	public boolean checkHash(String plaintext, String previouslyHashedValue);
	
}
