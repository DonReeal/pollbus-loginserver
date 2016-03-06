package pollbus.loginserver.hashauth.bcrypt.worker;

import io.baratine.core.Result;
import io.baratine.core.Service;
import io.baratine.core.Workers;

import pollbus.loginserver.hashauth.api.HashAuthService;

@Workers(4)
@Service("/workers:hashauth")
public final class HashAuthBCryptWorkerBean implements HashAuthService {

	private HashAuthBCryptWorker workerImplementation = HashAuthBCryptWorker.builder()
				.logRounds(3)
				.build();

	@Override
	public void createHash(String pw, Result<String> result) {
		workerImplementation.createHash(pw, result);
	}

	@Override
	public void checkHash(String plaintext, String previouslyHashedValue, Result<Boolean> result) {
		workerImplementation.checkHash(plaintext, previouslyHashedValue, result);
	}	

}
