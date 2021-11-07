package contestants.trivial;

import dicer.Contestant;
import dicer.Rules;

public class TrivialContestant implements Contestant {
	int targetPoints;
	
	@Override
	public void setup(Rules r) {
		targetPoints = r.targetPoints;
	}

	@Override
	public boolean toBeRetained(int currentPoints, int throwsLeft, int retainsLeft, int diceResult) {
		return currentPoints + diceResult <= targetPoints;
	}
}
