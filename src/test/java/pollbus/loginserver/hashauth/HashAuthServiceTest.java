package pollbus.loginserver.hashauth;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import io.baratine.core.Lookup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.caucho.junit.ConfigurationBaratine;
import com.caucho.junit.RunnerBaratine;

import pollbus.loginserver.hashauth.api.HashAuthServiceSync;
import pollbus.loginserver.hashauth.bcrypt.HashAuthServiceBCrypt;
import pollbus.loginserver.hashauth.testingconf.HashAuthBCryptWorkerBean_SingleWorker;


@RunWith(RunnerBaratine.class)
@ConfigurationBaratine(
		services={ HashAuthServiceBCrypt.class, HashAuthBCryptWorkerBean_SingleWorker.class}, 
		port=8989 /*,logLevel="INFO" */)
public class HashAuthServiceTest {
	
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Inject @Lookup("/hashauth")
	private HashAuthServiceSync endpoint;
	
	

	@Test
	public void serviceIsBootstrapped() {
		String pw = "Alligator_3";
		String hashedValue = endpoint.createHash(pw);
		assertThat(endpoint.checkHash(pw, hashedValue), is(true));
		assertThat(endpoint.checkHash("Ailigator_3", hashedValue), is(false));
	}
	
	
	// TODO: following needs to be moved to an integration test
	// this simulates a single client 
	// - calling the endpoint, 
	// - awaiting the result and 
	// calling again etc ...
	// 
	// -> will only use one thread of the endpoint
	int repeatTimes;
	long started;
	long finished;

	@Test
	public void aSingleCallTakesLessThan200MSOnAverage() throws InterruptedException {
		repeatTimes = 100;
		long started = System.currentTimeMillis();
		runHashpw(repeatTimes);
		long finished = System.currentTimeMillis();
		long dt = finished - started;
		
		double perRequestAvg = dt/repeatTimes;		
		log.info("== QUERY_TO_REPLY_AVG() (n={}, no_network_latency): {} ms", repeatTimes,  perRequestAvg);
		assertThat(perRequestAvg, lessThan(200D));
	}
	
	
	private void runHashpw(int times) {
		for(int i = 0; i < times; i++)
			endpoint.createHash("askjdf8293hrwaetfg7zbheqawgfz1h3rwqaU!R§W!QFZ(RH)U§");		
	}

	

	


}
