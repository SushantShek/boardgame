package com.sample.board.processor;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BoardAdminTest {
	
	BoardAdmin admin;

	@Before
	public void setUp() throws Exception {
		admin = new BoardAdmin();
	}
	
	@After
	public void tearDown() throws Exception {
		admin = null;
	}

	@Test
	public void testMakeMove_pitIsEmpty_player1() {
		admin.makeMove(1);
		assertTrue( admin.playerOneBoard.get(1)==0);
		
	}
	
	@Test(expected= IndexOutOfBoundsException.class)
	public void testMakeMove_InvalidPitID_ThrowsException() {
		admin.makeMove(8);
		assertTrue( admin.playerOneBoard.get(8)==6);
	}
	
	@Test
	public void testMakeMove_pitIsEmpty_Player2() {
		admin.makeMove(2);
		admin.makeMove(11);
		assertTrue( admin.playerTwoBoard.get(11-7)==0);
	}

	
}
