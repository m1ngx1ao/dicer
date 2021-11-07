package dicer;

import java.util.HashMap;
import java.util.Map;

public class Simulator {

	public static void main(String[] args) {
		final int NUMBER_OF_GAMES = 100000;
		Map<Contestant,Integer> winsByContestant = new HashMap<Contestant,Integer>();
		GameMaster gm = GameMasterFactory.get(new Rules(15, 6, 10, 6));
		for (int i = 0; i < NUMBER_OF_GAMES; i++) {
			GameMaster.GameResult result = gm.play();
			for (Contestant c : result.getWinners()) {
				Integer formerWins = winsByContestant.get(c);
				winsByContestant.put(c, 1 + (formerWins == null ? 0 : formerWins));
			}
		}
		
		System.out.println("Wins:");
		for (Contestant c : winsByContestant.keySet()) {
			System.out.println(c.getClass().getSimpleName() + ": "
					+ Math.round(100000.0 * winsByContestant.get(c) / NUMBER_OF_GAMES) / 1000.0 + "%");
		}
	}

}
