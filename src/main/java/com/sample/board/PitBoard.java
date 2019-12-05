package com.sample.board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.sample.board.processor.BoardAdmin;

public class PitBoard extends JFrame {

	/**
	 * Serial Version UID 
	 * 
	 */
	private static final long serialVersionUID = -8026515097862663735L;
	
	/* JPanel for Board */
	private ImagePanel jContentPane = null;
	private JPanel jTopPanel;
	private JPanel jCenterContentPane = null;
	/* JLabels for the game */
	private JLabel jLeftLabel = null;
	private JLabel jRightLabel = null;
	private JLabel jTurnLabel;
	/* JButtons for the board */
	private JButton jButtonStart;
	private JButton jButton00 = null;
	private JButton jButton10 = null;
	private JButton jButton20 = null;
	private JButton jButton30 = null;
	private JButton jButton40 = null;
	private JButton jButton50 = null;
	private JButton jButton01 = null;
	private JButton jButton11 = null;
	private JButton jButton21 = null;
	private JButton jButton31 = null;
	private JButton jButton41 = null;
	private JButton jButton51 = null;

	BoardAdmin board;
	
	public static void main(final String[] args) throws IOException {
		new PitBoard().setVisible(true);
	}

	public PitBoard() {
		board = new BoardAdmin();
		initGame();
	}

	private void initGame() {
		this.setContentPane(getJContentPane());
		this.setTitle("PitBoardGame");
		this.setSize(600, 240);
		this.setLocationRelativeTo(null);

	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jRightLabel = new JLabel();
			jRightLabel.setForeground(Color.red);
			jRightLabel.setText("               ");
			jLeftLabel = new JLabel();
			jLeftLabel.setForeground(Color.red);
			jLeftLabel.setText("               ");
			jTopPanel = new JPanel();
			GroupLayout jTopLabelLayout = new GroupLayout((JComponent) jTopPanel);
			jTopPanel.setLayout(jTopLabelLayout);
			jTopLabelLayout.setVerticalGroup(jTopLabelLayout.createSequentialGroup()
					.addGroup(jTopLabelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(getJButtonStart(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE,
									GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGroup(jTopLabelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(getJTurnLabel(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE,
									GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)));
			jTopLabelLayout.setHorizontalGroup(jTopLabelLayout.createSequentialGroup().addContainerGap()
					.addGroup(jTopLabelLayout.createParallelGroup()
					.addGap(34)
					.addGroup(jTopLabelLayout.createParallelGroup()
							.addGroup(jTopLabelLayout.createSequentialGroup()
									.addComponent(getJTurnLabel(), GroupLayout.PREFERRED_SIZE, 221,
											GroupLayout.PREFERRED_SIZE)
									.addGap(0, 0, Short.MAX_VALUE))
							.addGroup(GroupLayout.Alignment.LEADING,
									jTopLabelLayout.createSequentialGroup()
											.addComponent(getJButtonStart(), GroupLayout.PREFERRED_SIZE, 119,
													GroupLayout.PREFERRED_SIZE)
											.addGap(0, 102, Short.MAX_VALUE))).addGap(67)));
			
			jTopPanel.setBorder(BorderFactory.createEmptyBorder(10, -1, 10, -1));
			try {
				jContentPane = new ImagePanel(javax.imageio.ImageIO.read(new java.io.File("2games buttons.jpg")),
						ImagePanel.SCALED);
			} catch (Exception e) {
				jContentPane = new ImagePanel(null, ImagePanel.SCALED);
			}
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(jTopPanel, BorderLayout.NORTH);
			jTopPanel.setPreferredSize(new java.awt.Dimension(573, 79));
			jContentPane.add(jLeftLabel, BorderLayout.WEST);
			jContentPane.add(jRightLabel, BorderLayout.EAST);
			jContentPane.add(getJCenterContentPane(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * Initialize Start Button
	 * @return
	 */
	private JButton getJButtonStart() {
		if (jButtonStart == null) {
			jButtonStart = new JButton();
			jButtonStart.setText("Start Game");
			jButtonStart.addActionListener(new GameActionListener());
		}
		return jButtonStart;
	}

	/* Game ActionListener Class */

	public class GameActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
				final JButton src = (JButton) e.getSource();
				boolean gameEnd = false;
				if (src == jButton00) {
					gameEnd = board.makeMove(0);
				} else if (src == jButton10) {
					gameEnd = board.makeMove(1);
				} else if (src == jButton20) {
					gameEnd = board.makeMove(2);
				} else if (src == jButton30) {
					gameEnd = board.makeMove(3);
				} else if (src == jButton40) {
					gameEnd = board.makeMove(4);
				} else if (src == jButton50) {
					gameEnd = board.makeMove(5);
				} else if (src == jButton51) {
					gameEnd = board.makeMove(7);
				} else if (src == jButton41) {
					gameEnd = board.makeMove(8);
				} else if (src == jButton31) {
					gameEnd = board.makeMove(9);
				} else if (src == jButton21) {
					gameEnd = board.makeMove(10);
				} else if (src == jButton11) {
					gameEnd = board.makeMove(11);
				} else if (src == jButton01) {
					gameEnd = board.makeMove(12);
				} else if (src == jButtonStart) {
					jButtonStart.setEnabled(false);
					gameEnd = true;
				}
				printBoard(!gameEnd);
			}
	}
	
	private void printBoard(final boolean gameEnd) {
		if (jButtonStart.isEnabled()) {
			jButton00.setEnabled(false);
			jButton10.setEnabled(false);
			jButton20.setEnabled(false);
			jButton30.setEnabled(false);
			jButton40.setEnabled(false);
			jButton50.setEnabled(false);

			jButton01.setEnabled(false);
			jButton11.setEnabled(false);
			jButton21.setEnabled(false);
			jButton31.setEnabled(false);
			jButton41.setEnabled(false);
			jButton51.setEnabled(false);

			return;
		}
		jLeftLabel.setText(" \t" + board.playerTwoBoard.get(6) + " \t");
		jRightLabel.setText(" \t" + board.playerOneBoard.get(6) + " \t");

		jButton00.setText(board.playerOneBoard.get(0).toString());
		jButton10.setText(board.playerOneBoard.get(1).toString());
		jButton20.setText(board.playerOneBoard.get(2).toString());
		jButton30.setText(board.playerOneBoard.get(3).toString());
		jButton40.setText(board.playerOneBoard.get(4).toString());
		jButton50.setText(board.playerOneBoard.get(5).toString());

		jButton01.setText(board.playerTwoBoard.get(5).toString());
		jButton11.setText(board.playerTwoBoard.get(4).toString());
		jButton21.setText(board.playerTwoBoard.get(3).toString());
		jButton31.setText(board.playerTwoBoard.get(2).toString());
		jButton41.setText(board.playerTwoBoard.get(1).toString());
		jButton51.setText(board.playerTwoBoard.get(0).toString());

		if (gameEnd) {
			jTurnLabel.setText("");

			jButton00.setText("0");
			jButton10.setText("0");
			jButton20.setText("0");
			jButton30.setText("0");
			jButton40.setText("0");
			jButton50.setText("0");

			jButton01.setText("0");
			jButton11.setText("0");
			jButton21.setText("0");
			jButton31.setText("0");
			jButton41.setText("0");
			jButton51.setText("0");

			jButton00.setEnabled(false);
			jButton10.setEnabled(false);
			jButton20.setEnabled(false);
			jButton30.setEnabled(false);
			jButton40.setEnabled(false);
			jButton50.setEnabled(false);

			jButton01.setEnabled(false);
			jButton11.setEnabled(false);
			jButton21.setEnabled(false);
			jButton31.setEnabled(false);
			jButton41.setEnabled(false);
			jButton51.setEnabled(false);
			
			String winner = board.playerTwoBoard.get(6) > board.playerOneBoard.get(6) ? "PLAYER 2"
					: "PLAYER 1";
			if (board.playerTwoBoard.get(6) != board.playerOneBoard.get(6))
				JOptionPane.showMessageDialog(null, "Game Over !!! " + winner
						+ " wins!");
			else
				JOptionPane.showMessageDialog(null,
						"Game Over!!! It's a tie...");
		} else {
			if (board.playflag) {
				jTurnLabel.setText("Turn: PLAYER 1");
			}else {
				jTurnLabel.setText("Turn: PLAYER 2");
			}

			jButton00.setEnabled(board.playflag);
			jButton10.setEnabled(board.playflag);
			jButton20.setEnabled(board.playflag);
			jButton30.setEnabled(board.playflag);
			jButton40.setEnabled(board.playflag);
			jButton50.setEnabled(board.playflag);

			jButton01.setEnabled(!board.playflag);
			jButton11.setEnabled(!board.playflag);
			jButton21.setEnabled(!board.playflag);
			jButton31.setEnabled(!board.playflag);
			jButton41.setEnabled(!board.playflag);
			jButton51.setEnabled(!board.playflag);

		}
	}
	
	private JPanel getJCenterContentPane() {
		if (jCenterContentPane == null) {
			final GridBagConstraints gridBagConstraints00 = new GridBagConstraints();
			gridBagConstraints00.gridx = 0;
			gridBagConstraints00.gridy = 0;
			gridBagConstraints00.insets.set(10, 10, 10, 10);
			gridBagConstraints00.fill = GridBagConstraints.BOTH;
			gridBagConstraints00.weightx = 1;
			gridBagConstraints00.weighty = 1;

			final GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 1;
			gridBagConstraints10.gridy = 0;
			gridBagConstraints10.insets.set(10, 10, 10, 10);
			gridBagConstraints10.fill = GridBagConstraints.BOTH;
			gridBagConstraints10.weightx = 1;
			gridBagConstraints10.weighty = 1;

			final GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			gridBagConstraints20.gridx = 2;
			gridBagConstraints20.gridy = 0;
			gridBagConstraints20.insets.set(10, 10, 10, 10);
			gridBagConstraints20.fill = GridBagConstraints.BOTH;
			gridBagConstraints20.weightx = 1;
			gridBagConstraints20.weighty = 1;

			final GridBagConstraints gridBagConstraints30 = new GridBagConstraints();
			gridBagConstraints30.gridx = 3;
			gridBagConstraints30.gridy = 0;
			gridBagConstraints30.insets.set(10, 10, 10, 10);
			gridBagConstraints30.fill = GridBagConstraints.BOTH;
			gridBagConstraints30.weightx = 1;
			gridBagConstraints30.weighty = 1;

			final GridBagConstraints gridBagConstraints40 = new GridBagConstraints();
			gridBagConstraints40.gridx = 4;
			gridBagConstraints40.gridy = 0;
			gridBagConstraints40.insets.set(10, 10, 10, 10);
			gridBagConstraints40.fill = GridBagConstraints.BOTH;
			gridBagConstraints40.weightx = 1;
			gridBagConstraints40.weighty = 1;

			final GridBagConstraints gridBagConstraints50 = new GridBagConstraints();
			gridBagConstraints50.gridx = 5;
			gridBagConstraints50.gridy = 0;
			gridBagConstraints50.insets.set(10, 10, 10, 10);
			gridBagConstraints50.fill = GridBagConstraints.BOTH;
			gridBagConstraints50.weightx = 1;
			gridBagConstraints50.weighty = 1;

			final GridBagConstraints gridBagConstraints01 = new GridBagConstraints();
			gridBagConstraints01.gridx = 0;
			gridBagConstraints01.gridy = 1;
			gridBagConstraints01.insets.set(10, 10, 10, 10);
			gridBagConstraints01.fill = GridBagConstraints.BOTH;
			gridBagConstraints01.weightx = 1;
			gridBagConstraints01.weighty = 1;

			final GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 1;
			gridBagConstraints11.gridy = 1;
			gridBagConstraints11.insets.set(10, 10, 10, 10);
			gridBagConstraints11.fill = GridBagConstraints.BOTH;
			gridBagConstraints11.weightx = 1;
			gridBagConstraints11.weighty = 1;

			final GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.gridx = 2;
			gridBagConstraints21.gridy = 1;
			gridBagConstraints21.insets.set(10, 10, 10, 10);
			gridBagConstraints21.fill = GridBagConstraints.BOTH;
			gridBagConstraints21.weightx = 1;
			gridBagConstraints21.weighty = 1;

			final GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.gridx = 3;
			gridBagConstraints31.gridy = 1;
			gridBagConstraints31.insets.set(10, 10, 10, 10);
			gridBagConstraints31.fill = GridBagConstraints.BOTH;
			gridBagConstraints31.weightx = 1;
			gridBagConstraints31.weighty = 1;

			final GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
			gridBagConstraints41.gridx = 4;
			gridBagConstraints41.gridy = 1;
			gridBagConstraints41.insets.set(10, 10, 10, 10);
			gridBagConstraints41.fill = GridBagConstraints.BOTH;
			gridBagConstraints41.weightx = 1;
			gridBagConstraints41.weighty = 1;

			final GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
			gridBagConstraints51.gridx = 5;
			gridBagConstraints51.gridy = 1;
			gridBagConstraints51.insets.set(10, 10, 10, 10);
			gridBagConstraints51.fill = GridBagConstraints.BOTH;
			gridBagConstraints51.weightx = 1;
			gridBagConstraints51.weighty = 1;

			jCenterContentPane = new JPanel();
			jCenterContentPane.setLayout(new GridBagLayout());
			jCenterContentPane.add(getJButton00(), gridBagConstraints00);
			jCenterContentPane.add(getJButton10(), gridBagConstraints10);
			jCenterContentPane.add(getJButton20(), gridBagConstraints20);
			jCenterContentPane.add(getJButton30(), gridBagConstraints30);
			jCenterContentPane.add(getJButton40(), gridBagConstraints40);
			jCenterContentPane.add(getJButton50(), gridBagConstraints50);

			jCenterContentPane.add(getJButton01(), gridBagConstraints01);
			jCenterContentPane.add(getJButton11(), gridBagConstraints11);
			jCenterContentPane.add(getJButton21(), gridBagConstraints21);
			jCenterContentPane.add(getJButton31(), gridBagConstraints31);
			jCenterContentPane.add(getJButton41(), gridBagConstraints41);
			jCenterContentPane.add(getJButton51(), gridBagConstraints51);

		}
		return jCenterContentPane;
	}
	
	/**
	 * These method initializes jButton
	 * All the buttons used in the board
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton00() {
		if (jButton00 == null) {
			jButton00 = new JButton();
			jButton00.addActionListener(new GameActionListener());
		}
		return jButton00;
	}
	
	private JButton getJButton10() {
		if (jButton10 == null) {
			jButton10 = new JButton();
			jButton10.addActionListener(new GameActionListener());
		}
		return jButton10;
	}

	private JButton getJButton20() {
		if (jButton20 == null) {
			jButton20 = new JButton();
			jButton20.addActionListener(new GameActionListener());
		}
		return jButton20;
	}

	private JButton getJButton30() {
		if (jButton30 == null) {
			jButton30 = new JButton();
			jButton30.addActionListener(new GameActionListener());
		}
		return jButton30;
	}

	private JButton getJButton40() {
		if (jButton40 == null) {
			jButton40 = new JButton();
			jButton40.addActionListener(new GameActionListener());
		}
		return jButton40;
	}

	private JButton getJButton50() {
		if (jButton50 == null) {
			jButton50 = new JButton();
			jButton50.addActionListener(new GameActionListener());
		}
		return jButton50;
	}

	private JButton getJButton01() {
		if (jButton01 == null) {
			jButton01 = new JButton();
			jButton01.addActionListener(new GameActionListener());
		}
		return jButton01;
	}

	private JButton getJButton11() {
		if (jButton11 == null) {
			jButton11 = new JButton();
			jButton11.addActionListener(new GameActionListener());
		}
		return jButton11;
	}

	private JButton getJButton21() {
		if (jButton21 == null) {
			jButton21 = new JButton();
			jButton21.addActionListener(new GameActionListener());
		}
		return jButton21;
	}

	private JButton getJButton31() {
		if (jButton31 == null) {
			jButton31 = new JButton();
			jButton31.addActionListener(new GameActionListener());
		}
		return jButton31;
	}

	private JButton getJButton41() {
		if (jButton41 == null) {
			jButton41 = new JButton();
			jButton41.addActionListener(new GameActionListener());
		}
		return jButton41;
	}

	private JButton getJButton51() {
		if (jButton51 == null) {
			jButton51 = new JButton();
			jButton51.addActionListener(new GameActionListener());
		}
		return jButton51;
	}
	
	/**
	 * Initialize the label's
	 * 
	 * @return JLabel
	 */
	private JLabel getJTurnLabel() {
		if (jTurnLabel == null) {
			jTurnLabel = new JLabel();
			jTurnLabel.setForeground(Color.RED);
			jTurnLabel.setText("Turn: -");
		}
		return jTurnLabel;
	}
	
}
