package contestants.tensor;

import java.util.HashMap;
import java.util.Map;

import dicer.Rules;

public class LossTensor {
	int targetPoints;
	int maxThrows;
	int maxRetains;
	boolean mustUseUpAllRetains;
	Map<State, Double> tensor;

	public LossTensor(Rules rules) {
		this.targetPoints = rules.targetPoints;
		this.maxThrows = rules.maxThrows;
		this.maxRetains = rules.maxRetains;
		this.mustUseUpAllRetains = rules.mustUseUpAllRetains;
		tensor = new HashMap<State, Double>();
		for (int retainsLeft = 1; retainsLeft <= maxRetains; retainsLeft++) {
			for (int throwsLeft = 1; throwsLeft <= maxThrows; throwsLeft++) {
				for (int currentPoints = targetPoints + rules.diceSides * (maxRetains - retainsLeft); currentPoints >= 0; currentPoints--) {
					double loss = 0;
					for (int diceResult = 1; diceResult <= rules.diceSides; diceResult++) {
						double lossIfNotRetained = get(currentPoints, throwsLeft - 1, retainsLeft);
						double lossIfRetained = get(currentPoints + diceResult, throwsLeft - 1, retainsLeft - 1);
						loss += Math.min(lossIfNotRetained, lossIfRetained);
					}
					tensor.put(new State(currentPoints, throwsLeft, retainsLeft), loss / rules.diceSides);
				}
			}
		}
	}

	public double get(int currentPoints, int throwsLeft, int retainsLeft) {
		if (currentPoints == targetPoints && !mustUseUpAllRetains) {
			return 0;
		}
		if (throwsLeft < 0 || throwsLeft > maxThrows || retainsLeft < 0 || retainsLeft > maxRetains
				|| (throwsLeft < retainsLeft && mustUseUpAllRetains)) {
			return 100000; // sufficiently large for being averted, sufficiently small to avert overflows
		}
		if (throwsLeft == 0 || retainsLeft == 0) {
			return Math.abs(currentPoints - targetPoints);
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
