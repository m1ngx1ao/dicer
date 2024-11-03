package contestants.m1ngx1ao;

import dicer.Contestant;
import dicer.Rules;

public class M1ngX1aoContestant implements Contestant {
	private int diceSides;
	private int targetPoints;
	private StateProbability sp;

	@Override
	public void setup(Rules rules) {
		this.diceSides = rules.diceSides;
		this.targetPoints = rules.targetPoints;
		this.sp = new StateProbability();
	}

	@Override
	public boolean toBeRetained(int currentPoints, int throwsLeft, int retainsLeft, int diceResult) {
		State sRetained = new State(
			this.targetPoints - currentPoints - diceResult,
			throwsLeft - 1,
			retainsLeft - 1,
			this.diceSides
		);
		State sNotRetained = new State(
			this.targetPoints - currentPoints,
			throwsLeft - 1,
			retainsLeft,
			this.diceSides
		);
		return sp.get(sRetained) > sp.get(sNotRetained);
	}
}