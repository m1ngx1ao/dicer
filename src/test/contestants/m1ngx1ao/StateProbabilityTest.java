package test.contestants.m1ngx1ao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import contestants.m1ngx1ao.*;
import dicer.Rules;
import dicer.Rules.Goal;

public class StateProbabilityTest {
	static M1ngX1aoProbability sp;
	private int diceSides = 6;
	private double oneThrowPos = 1.0/this.diceSides;
	
	@BeforeAll
	static void setUpBeforeClass(){
	}
	
	@BeforeEach
	void setup(){
		sp = new M1ngX1aoProbability(new Rules(15, 6, 10, 6, false, Goal.obtainTargetPoints));
	}

	private void assertProbability(double expected, State s) {
		final double precision = 1000000;
		assertEquals(Math.round(expected * precision), Math.round(sp.get(s.pointsLeft, s.throwsLeft, s.retainsLeft) * precision));
	}

	@Test
	void onTargetOutOfThrowsSure() {
		this.assertProbability(1.0, new State(0, 0, 3));
	}
	
	@Test
	void offTargetOutOfThrowsImpossible() {
		this.assertProbability(0.0, new State(1, 0, 3));
	}

	@Test
	void onTargetOutOfRetainsSure() {
		this.assertProbability(1.0, new State(0, 5, 0));
	}

	@Test
	void offTargetOutOfRetainsImpossible() {
		this.assertProbability(0.0, new State(2, 5, 0));
	}

	@Test
	void nearTargetOneThrowLeftPossible() {
		this.assertProbability(oneThrowPos, new State(2, 1, 6));
	}

	@Test
	void nearTargetTwoThrowLeftPossible() {
		this.assertProbability(oneThrowPos + (1 - oneThrowPos) * oneThrowPos, new State(2, 2, 6));
	}

}
