package contestants.tensor;

import java.util.HashMap;
import java.util.Map;

import dicer.Rules;

public class ProbabilityTensor {
	int targetPoints;
	int maxThrows;
	int maxRetains;
	boolean mustUseUpAllRetains;
	Map<State, Double> tensor;

	public ProbabilityTensor(Rules rules) {
		this.targetPoints = rules.targetPoints;
		this.maxThrows = rules.maxThrows;
		this.maxRetains = rules.maxRetains;
		this.mustUseUpAllRetains = rules.mustUseUpAllRetains;
		tensor = new HashMap<State, Double>();
		for (int retainsLeft = 1; retainsLeft <= maxRetains; retainsLeft++) {
			for (int throwsLeft = 1; throwsLeft <= maxThrows; throwsLeft++) {
				for (int currentPoints = targetPoints - 1; currentPoints >= 0; currentPoints--) {
					double p = 0;
					for (int diceResult = 1; diceResult <= rules.diceSides; diceResult++) {
						double probIfNotRetained = get(currentPoints, throwsLeft - 1, retainsLeft);
						double probIfRetained = get(currentPoints + diceResult, throwsLeft - 1, retainsLeft - 1);
						p += Math.max(probIfNotRetained, probIfRetained);
					}
					tensor.put(new State(currentPoints, throwsLeft, retainsLeft), p / rules.diceSides);
				}
			}
		}
	}

	public double get(int currentPoints, int throwsLeft, int retainsLeft) {
		if (currentPoints == targetPoints && (!mustUseUpAllRetains || retainsLeft == 0)) {
			return 1;
		}
		if (throwsLeft <= 0 || throwsLeft > maxThrows || retainsLeft <= 0 || retainsLeft > maxRetains
				|| currentPoints > targetPoints
				|| (retainsLeft > 0 && currentPoints == targetPoints && mustUseUpAllRetains)) {
			return 0;
		}
		return tensor.get(new State(currentPoints, throwsLeft, retainsLeft));
	}

	@Override
	public String toString() {
		String r = "";
		for (State s : tensor.keySet()) {
			r += s + " -> " + tensor.get(s) + "\n";
		}
		return r;
	}
}
