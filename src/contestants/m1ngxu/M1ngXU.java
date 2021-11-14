package contestants.m1ngxu;

import dicer.Contestant;
import dicer.Rules;

public class M1ngXU implements Contestant {
	private boolean[][][][] bestResult;
	private Rules rules;

	@Override
	public void setup(Rules r) {
		rules = r;

		int v = 0;
		bestResult = new boolean[r.targetPoints][r.diceSides][r.maxRetains][r.maxThrows];
		boolean[][][][] t = new boolean[r.targetPoints][r.diceSides][r.maxRetains][r.maxThrows];
		for (int a = 0; a < r.targetPoints; a++) {
			for (int b = 0; b < r.diceSides; b++) {
				for (int c = 0; c < r.maxRetains; c++) {
					for (int d = 0; d < r.maxThrows; d++) {
						for (int isRetainedInt = 0; isRetainedInt < 2; isRetainedInt++) {
							t[a][b][c][d] = isRetainedInt == 1;
							int w = 0;
							for (int i = 0; i < 1000; i++) {
								int g = 0;
								int h = 0;
								for (int e = 0; e < r.maxThrows && h < r.maxRetains && g < r.targetPoints; e++) {
									int f = (int) (Math.random() * r.diceSides) + 1;
									if (t[g][f - 1][h][e]) {
										g += f;
										h++;
									}
								}
								if (g == r.targetPoints)
									w++;
							}
							if (w > v) {
								v = w;
								for (int aa = 0; aa < r.targetPoints; aa++) {
									for (int bb = 0; bb < r.diceSides; bb++) {
										for (int cc = 0; cc < r.maxRetains; cc++) {
											for (int dd = 0; dd < r.maxThrows; dd++) {
												bestResult[aa][bb][cc][dd] = t[aa][bb][cc][dd];
											}
										}
									}
								}
							}							
						}
					}
				}
			}
		}
	}

	@Override
	public boolean toBeRetained(int currentPoints, int throwsLeft, int retainsLeft, int diceResult) {
		if (throwsLeft <= 0 || retainsLeft <= 0 || currentPoints >= rules.targetPoints)
			return false;
		return bestResult[currentPoints][diceResult - 1][rules.maxRetains - retainsLeft][rules.maxThrows - throwsLeft];
	}
}
