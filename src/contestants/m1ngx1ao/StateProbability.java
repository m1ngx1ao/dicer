package contestants.m1ngx1ao;

import java.lang.Math;
import java.util.Map;
import java.util.HashMap;

public class StateProbability {
	private Map<State, Double> probForState = new HashMap<State, Double>();

	public double get(State s) {
		if (s.hasWon()) {
			return 1.0;
		}
		if (s.hasLost()) {
			return 0.0;
		}
		if (this.probForState.containsKey(s)) {
			return this.probForState.get(s);
		}
		double probAfterNotRetained = get(new State(
			s.pointsLeft,
			s.throwsLeft - 1,
			s.retainsLeft,
			s.diceSides
		));
		double result = 0.0;
		for (int diceSide = 1; diceSide <= s.diceSides; diceSide++) {
			double probAfterRetained = get(new State(
				s.pointsLeft - diceSide,
				s.throwsLeft - 1,
				s.retainsLeft - 1,
				s.diceSides
			));
			result += Math.max(probAfterNotRetained, probAfterRetained);
		}
		result /= s.diceSides;
		this.probForState.put(s, result);
		return result;
	}
}
