package pollbus.loginserver.hashauth.bcrypt;

import javax.inject.Inject;

import io.baratine.core.Lookup;
import io.baratine.core.Result;
import io.baratine.core.Service;

import pollbus.loginserver.hashauth.api.HashAuthService;

@Service("public:///hashauth/bcrypt")
public class HashAuthServiceBCrypt implements HashAuthService {

	@Inject @Lookup("/workers:hashauth/bcrypt")
	private HashAuthService worker;

	@Override
	public void createHash(String pw, Result<String> hashedResult) {
		worker.createHash(pw, hashedResult);
	}

	@Override
	public void checkHash(String plaintext, String previouslyHashedValue, Result<Boolean> result) {
		worker.checkHash(plaintext, previouslyHashedValue, result);
	}
	
}
