package test.contestants.m1ngx1ao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import contestants.m1ngx1ao.*;

public class StateProbabilityTest {
	static StateProbability sp;
	private int diceSides = 6;
	private double oneThrowPos = 1.0/this.diceSides;
	
	@BeforeAll
	static void setUpBeforeClass(){
	}
	
	@BeforeEach
	void setup(){
		sp = new StateProbability();
	}

	private void assertProbability(double expected, State s) {
		final double precision = 1000000;
		assertEquals(Math.round(expected * precision), Math.round(sp.get(s) * precision));
	}

	@Test
	void onTargetOutOfThrowsSure() {
		this.assertProbability(1.0, new State(0, 0, 3, this.diceSides));
	}
	
	@Test
	void offTargetOutOfThrowsImpossible() {
		this.assertProbability(0.0, new State(1, 0, 3, this.diceSides));
	}

	@Test
	void onTargetOutOfRetainsSure() {
		this.assertProbability(1.0, new State(0, 5, 0, this.diceSides));
	}

	@Test
	void offTargetOutOfRetainsImpossible() {
		this.assertProbability(0.0, new State(2, 5, 0, this.diceSides));
	}

	@Test
	void nearTargetOneThrowLeftPossible() {
		this.assertProbability(oneThrowPos, new State(2, 1, 6, this.diceSides));
	}

	@Test
	void nearTargetTwoThrowLeftPossible() {
		this.assertProbability(oneThrowPos + (1 - oneThrowPos) * oneThrowPos, new State(2, 2, 6, this.diceSides));
	}

}
