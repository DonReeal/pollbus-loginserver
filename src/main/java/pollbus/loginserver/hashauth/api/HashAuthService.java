package pollbus.loginserver.hashauth.api;

import io.baratine.core.Result;

public interface HashAuthService {

	/**
	 * Generates a hashed version of a password using salt;
	 * Salt is prepended to the the result String.
	 * 
	 * @param plaintext - only UTF-8 encoded values will be hashed
	 * @param hashedResult - UTF-8 encoded hashed value with the used salt prepended
	 */
	void createHash(String plaintext, Result<String> hashedResult);
	
	/**
	 * Checks if the plaintext-hash equals the passed hashedValue
	 * 
	 * @param plaintext - only UTF8 encoded plaintexts can yield in a result#complete(true)
	 * @param previouslyHashedValue
	 * @param result
	 */
	void checkHash(String plaintext, String previouslyHashedValue, Result<Boolean> result);

}