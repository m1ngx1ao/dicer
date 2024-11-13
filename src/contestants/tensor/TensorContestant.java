package contestants.tensor;

import dicer.Contestant;
import dicer.Rules;

public class TensorContestant implements Contestant {
	Contestant delegate;
	
	@Override
	public void setup(Rules rules) {
		switch (rules.goal) {
			case minimizeDistanceToTargetPoints:
				delegate = new Contestant() {
					LossTensor lt;
		
					@Override
					public void setup(Rules rules) {
						lt = new LossTensor(rules);
					}
		
					@Override
					public boolean toBeRetained(int currentPoints, int throwsLeft, int retainsLeft, int diceResult) {
						double lossIfNotRetained = lt.get(currentPoints, throwsLeft - 1, retainsLeft);
						double lossIfRetained = lt.get(currentPoints + diceResult, throwsLeft - 1, retainsLeft - 1);
						return lossIfNotRetained - 0.0000001 > lossIfRetained;
					}
				};
				break;
			case obtainTargetPoints:
				delegate = new Contestant() {
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
				};
				break;
		}
		delegate.setup(rules);
	}

	@Override
	public boolean toBeRetained(int currentPoints, int throwsLeft, int retainsLeft, int diceResult) {
		return delegate.toBeRetained(currentPoints, throwsLeft, retainsLeft, diceResult);
	}
}
