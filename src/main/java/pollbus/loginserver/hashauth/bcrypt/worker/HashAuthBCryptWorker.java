package pollbus.loginserver.hashauth.bcrypt.worker;

import io.baratine.core.Result;

import pollbus.loginserver.bcrypt.BCrypt;
import pollbus.loginserver.hashauth.api.HashAuthService;

@lombok.Builder
public class HashAuthBCryptWorker implements HashAuthService {
	
	private final int logRounds;
	
	@Override
	public void createHash(String pw, Result<String> result) {		
		BCrypt.gensalt(logRounds);
		String salt = BCrypt.gensalt();
		String hashedValue = BCrypt.hashpw(pw, salt);
		result.complete(hashedValue);
	}
	
	@Override
	public void checkHash(String plaintext, String previouslyHashedValue,  Result<Boolean> result){
		result.complete(BCrypt.checkpw(plaintext, previouslyHashedValue));
	}

}
