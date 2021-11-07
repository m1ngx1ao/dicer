package dicer;

import java.util.LinkedList;
import java.util.List;

import contestants.trivial.*;

//SOME IMPORTS

//SOME IMPORTS

public class GameMasterFactory {
	public static GameMaster get(Rules rules) {
		List<Contestant> contestants = new LinkedList<Contestant>();
		contestants.add(new TrivialContestant());
		//SOME CONTESTANTS
		
		//SOME CONTESTANTS
		return new GameMaster(rules, contestants);
	}
}
