package com.sample.board.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BoardAdmin {

	public boolean playflag;
	boolean isFinished = false;
	public List<Integer> playerOneBoard = new ArrayList<>();
	public List<Integer> playerTwoBoard = new ArrayList<>();

	public BoardAdmin() {
		initBoard();

	}

	private void initBoard() {
		IntStream.range(0, 6).forEach(ind -> {
			playerOneBoard.add(6);
		});
		playerOneBoard.add(0);

		IntStream.range(7, 13).forEach(ind -> {
			playerTwoBoard.add(6);
		});
		playerTwoBoard.add(0);
		playflag = true;
		printSetup();
	}

	public boolean makeMove(int indexToMoveFrom) {

		boolean gameOverFlag = false;
		if (playflag) {
			if (indexToMoveFrom > 5 || indexToMoveFrom < 0) {
				System.out.println("PlayerTwo's turn at " + indexToMoveFrom);
				return true;
			}
			gameOverFlag = move(indexToMoveFrom, playerOneBoard, playerTwoBoard);

		} else {
			if (indexToMoveFrom > 12 || indexToMoveFrom < 7) {
				System.out.println("PlayerOne's turn at " + indexToMoveFrom);
				return true;
			}
			gameOverFlag = move(indexToMoveFrom - 7, playerTwoBoard, playerOneBoard);
		}

		if (gameOverFlag) {

			int playerOneCount = 0;
			int playerTwoCount = 0;
			for (int i = 0; i < 6; ++i) {
				playerOneCount += playerOneBoard.get(i);
				playerTwoCount += playerTwoBoard.get(i);
			}
			if (playerTwoCount == 0 || playerOneCount == 0) {
				finishGame(playerTwoCount, playerOneCount);
				return false;
			}
		}
		return true;
	}

	private boolean move(int index, List<Integer> currentPlayer, List<Integer> otherPlayer) {
		boolean checkIfGameIsFinished = false;
		int stonesInPit = currentPlayer.get(index);
		// Check for an empty Pit
		if (stonesInPit == 0) {
			System.out.println("Pit is empty");
			return checkIfGameIsFinished;
		}

		currentPlayer.set(index, 0);
		int newIndex = index + 1;

		for (int i = stonesInPit; i > 0; --i) {
			if (newIndex < 7) {
				currentPlayer.set(newIndex, currentPlayer.get(newIndex) + 1);
				newIndex++;
			} else if (newIndex < 13) {
				otherPlayer.set(newIndex - 7, otherPlayer.get(newIndex - 7) + 1);
				newIndex++;
			} else // looped around
			{
				currentPlayer.set(0, currentPlayer.get(0) + 1);
				newIndex = 1;
			}
		}
		newIndex--;

		// Check if last stone ends in opposite pit of empty pit
		if (newIndex < 6 && currentPlayer.get(newIndex) == 1) {
			currentPlayer.set(6, currentPlayer.get(6) + otherPlayer.get(5 - newIndex) + 1);
			currentPlayer.set(newIndex, 0);
			otherPlayer.set(5 - newIndex, 0);
			checkIfGameIsFinished = true;
		}

		if (newIndex != 6) {
			playflag = !playflag;

		}
		printSetup();
		return checkIfGameIsFinished || index == 5;

	}

	private void finishGame(int topBonus, int bottomBonus) {
		int topScore = playerTwoBoard.get(6) + topBonus;
		int bottomScore = playerOneBoard.get(6) + bottomBonus;
		playerTwoBoard.set(6, topScore);
		playerOneBoard.set(6, bottomScore);
		isFinished = true;
	}

	/**
	 * Print the setup of current game
	 */
	private void printSetup() {
		System.out.print("\n  ");
		for (int i = 0; i < 6; ++i) {
			System.out.print(playerOneBoard.get(i) + " ");
		}
		System.out.println();
		System.out.print(playerTwoBoard.get(6) + "\t\t" + playerOneBoard.get(6) + "\n  ");
		for (int i = 5; i > -1; --i) {
			System.out.print(playerTwoBoard.get(i) + " ");
		}

		if (playflag)
			System.out.println("\n Play PlayerOne ");
		else
			System.out.println("\n Play PlayerTwo  ");

	}
}