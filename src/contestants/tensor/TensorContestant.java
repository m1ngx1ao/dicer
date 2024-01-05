package contestants.tensor;

import dicer.Contestant;
import dicer.Rules;

public class TensorContestant implements Contestant {
	ProbabilityTensor pt;
	
	@Override
	public void setup(Rules rules) {
		pt = new ProbabilityTensor(rules);
	}

	@Override
	public boolean toBeRetained(int currentPoints, int throwsLeft, int retainsLeft, int diceResult) {
		double probIfNotRetained = pt.get(currentPoints, throwsLeft - 1, retainsLeft);
		double probIfRetained = pt.get(currentPoints + diceResult, throwsLeft - 1, retainsLeft - 1);
		return probIfNotRetained + 0.0000001 < probIfRetained;
	}
}
