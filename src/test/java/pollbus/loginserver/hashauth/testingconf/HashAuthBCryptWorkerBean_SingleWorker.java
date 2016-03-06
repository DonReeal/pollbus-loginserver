package pollbus.loginserver.hashauth.testingconf;

import io.baratine.core.Result;
import io.baratine.core.Service;
import io.baratine.core.Workers;

import pollbus.loginserver.hashauth.api.HashAuthService;
import pollbus.loginserver.hashauth.bcrypt.worker.HashAuthBCryptWorker;

@Workers(1)
@Service("/workers:hashauth/bcrypt")
public final class HashAuthBCryptWorkerBean_SingleWorker implements HashAuthService {

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
