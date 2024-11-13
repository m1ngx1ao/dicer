package contestants.m1ngx1ao;

import dicer.Contestant;
import dicer.Rules;

public class M1ngX1aoContestant implements Contestant {
	Contestant delegate;
	
	@Override
	public void setup(Rules rules) {
		switch (rules.goal) {
			case minimizeDistanceToTargetPoints:
				delegate = new Contestant() {
					M1ngX1aoLoss lc;
					
					@Override
					public void setup(Rules rules) {
						lc = new M1ngX1aoLoss(rules);
					}
					
					@Override
					public boolean toBeRetained(int currentPoints, int throwsLeft, int retainsLeft, int diceResult) {
						double lossRetained = lc.get(
							rules.targetPoints - currentPoints - diceResult,
							throwsLeft - 1,
							retainsLeft - 1
						);
						double lossNotRetained = lc.get(
							rules.targetPoints - currentPoints,
							throwsLeft - 1,
							retainsLeft
						);
						return lossRetained < lossNotRetained;
					}
				};
				break;
			case obtainTargetPoints:
				delegate = new Contestant() {
					M1ngX1aoProbability pc;
					
					@Override
					public void setup(Rules rules) {
						pc = new M1ngX1aoProbability(rules);
					}
					
					@Override
					public boolean toBeRetained(int currentPoints, int throwsLeft, int retainsLeft, int diceResult) {
						double probIfRetained = pc.get(rules.targetPoints - currentPoints - diceResult, throwsLeft - 1, retainsLeft - 1);
						double probIfNotRetained = pc.get(rules.targetPoints - currentPoints, throwsLeft - 1, retainsLeft);
						return probIfRetained > probIfNotRetained;
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