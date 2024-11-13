package contestants.m1ngx1ao;

import java.util.HashMap;
import java.util.Map;

import dicer.Rules;

public class M1ngX1aoProbability {
	private int diceSides;
	private boolean mustUseUpAllRetains;
	private Map<State, Double> probForState = new HashMap<State, Double>();

	public M1ngX1aoProbability(Rules rules) {
		this.diceSides = rules.diceSides;
		this.mustUseUpAllRetains = rules.mustUseUpAllRetains;
	}

	public double get(int pointsLeft, int throwsLeft, int retainsLeft) {
		if (throwsLeft < 0 || pointsLeft < 0 || (pointsLeft > 0 && (throwsLeft == 0 || retainsLeft == 0))) {
			return 0.0;
		}
		if (mustUseUpAllRetains) {
			if (throwsLeft == 0 && retainsLeft != 0) {
				return 0.0;
			}
		}
		if (pointsLeft == 0 && (retainsLeft == 0 || !mustUseUpAllRetains)) {
			return 1.0;
		}
		State s = new State(pointsLeft, throwsLeft, retainsLeft);
		if (this.probForState.containsKey(s)) {
			return this.probForState.get(s);
		}
		double probAfterNotRetained = get(
			s.pointsLeft,
			s.throwsLeft - 1,
			s.retainsLeft
		);
		double result = 0.0;
		for (int diceSide = 1; diceSide <= diceSides; diceSide++) {
			double probAfterRetained = get(
				s.pointsLeft - diceSide,
				s.throwsLeft - 1,
				s.retainsLeft - 1
			);
			result += Math.max(probAfterNotRetained, probAfterRetained);
		}
		result /= diceSides;
		this.probForState.put(s, result);
		return result;
	}

}