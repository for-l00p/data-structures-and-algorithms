package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


// 

public class RouletteTest{
	
	
	@Test
	public void placingABetShouldReflectInGameState(){
		
		int amount = 100;
		Player player = new Player(game);
		Outcome outcome = mock(Outcome.class);
		player.placeBet(amount, outcome);
		verify(game).add(amount, outcome);
	}


	@Test
	public void 


}