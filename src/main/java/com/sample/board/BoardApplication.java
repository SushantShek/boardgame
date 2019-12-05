package com.sample.board;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sample.board.processor.BoardAdmin;

//@SpringBootApplication
public class BoardApplication {

	public static void main(String[] args) {
//		SpringApplication.run(BoardApplication.class, args);
		BoardAdmin board = new BoardAdmin();
		Scanner scanner = new Scanner(System.in); // Create a Scanner object
		System.out.println("Enter Pit Number");

		while (scanner.hasNext()) {
			String pitNumber = scanner.nextLine(); // Read user input
			board.makeMove(Integer.parseInt(pitNumber));
		}
		scanner.close();
	}

}
