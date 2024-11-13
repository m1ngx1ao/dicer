package contestants.m1ngx1ao;

import java.util.HashMap;
import java.util.Map;

import dicer.Rules;

public class M1ngX1aoLoss {
	private int diceSides;
	private boolean mustUseUpAllRetains;
	private Map<State, Double> lossForState = new HashMap<State, Double>();

	public M1ngX1aoLoss(Rules rules) {
		this.diceSides = rules.diceSides;
		this.mustUseUpAllRetains = rules.mustUseUpAllRetains;
	}

	public double get(int pointsLeft, int throwsLeft, int retainsLeft) {
		if (mustUseUpAllRetains) {
			if (throwsLeft == 0 && retainsLeft != 0) {
				return 100000;
			}
		}
		if (retainsLeft == 0 || (throwsLeft == 0 && !mustUseUpAllRetains)) {
			return Math.abs(pointsLeft);
		}
		State s = new State(pointsLeft, throwsLeft, retainsLeft);
		if (this.lossForState.containsKey(s)) {
			return this.lossForState.get(s);
		}
		double lossAfterNotRetained = get(
			s.pointsLeft,
			s.throwsLeft - 1,
			s.retainsLeft
		);
		double result = 0.0;
		for (int diceSide = 1; diceSide <= diceSides; diceSide++) {
			double lossAfterRetained = get(
				s.pointsLeft - diceSide,
				s.throwsLeft - 1,
				s.retainsLeft - 1
			);
			result += Math.min(lossAfterNotRetained, lossAfterRetained);
		}
		result /= diceSides;
		this.lossForState.put(s, result);
		return result;
	}
}