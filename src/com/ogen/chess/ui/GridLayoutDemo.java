package com.ogen.chess.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.*;

import com.ogen.chess.ChessBoard;
import com.ogen.chess.Coordinate;
import com.ogen.chess.Player;
import com.ogen.chess.piece.ChessPiece;
 
public class GridLayoutDemo extends JFrame {
	
	final ChessBoard chessBoard = new ChessBoard();
	
	Optional<Coordinate> clickedSquare = Optional.empty();

	private static final long serialVersionUID = -1590145707094778440L;
	
	GridLayout experimentLayout = new GridLayout(0,8);
     
    public GridLayoutDemo(String name) {
        super(name);
        setResizable(false);
    }
     
    public void addComponentsToPane(final Container pane) {
        final JPanel compsToExperiment = new JPanel();
        compsToExperiment.setLayout(experimentLayout);
        compsToExperiment.setPreferredSize(new Dimension(800, 800));
        for (int y = 7; y >= 0; y--) {
			for (int x = 0; x < 8; x++) {
				final int currX = x;
				final int currY = y;
				Optional<ChessPiece<?>> chessPiece = chessBoard.getSquare(x, y).get().getChessPiece();
				if (chessPiece.isEmpty()) {
					JButton button = new JButton();
					button.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							final Coordinate clickedSquare = new Coordinate(currX, currY);
							if (!GridLayoutDemo.this.clickedSquare.isEmpty()) {
								chessBoard.move(GridLayoutDemo.this.clickedSquare.get(), clickedSquare).ifPresentOrElse(s -> {
									JOptionPane.showMessageDialog(null, s);
								}, () -> {
									pane.removeAll();
									pane.revalidate();
									pane.repaint();
									addComponentsToPane(pane);
								});
							}
							GridLayoutDemo.this.clickedSquare = Optional.empty();
						}
					});
					compsToExperiment.add(button);
					continue;
				}
				JButton button = new JButton(String.valueOf((char) chessPiece.get().getUnicodeRepr()));
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						final Coordinate clickedSquare = new Coordinate(currX, currY);
						if (!GridLayoutDemo.this.clickedSquare.isEmpty()) {
							final Coordinate clickedCoordinate = GridLayoutDemo.this.clickedSquare.get();
							final ChessPiece<?> previouslyClickedPiece = chessBoard.getSquare(clickedCoordinate.getX(), clickedCoordinate.getY()).get().getChessPiece().get();
							final ChessPiece<?> currentlyClickedPiece = chessBoard.getSquare(currX, currY).get().getChessPiece().get();
							if (Player.areOpposite(previouslyClickedPiece.getPlayer(), currentlyClickedPiece.getPlayer())) {
								chessBoard.move(GridLayoutDemo.this.clickedSquare.get(), clickedSquare).ifPresentOrElse(s -> {
									JOptionPane.showMessageDialog(null, s);
								}, () -> {
									pane.removeAll();
									pane.revalidate();
									pane.repaint();
									addComponentsToPane(pane);
								});
								GridLayoutDemo.this.clickedSquare = Optional.empty();
							} else {
								GridLayoutDemo.this.clickedSquare = Optional.of(clickedSquare);
							}
						} else {
							GridLayoutDemo.this.clickedSquare = Optional.of(clickedSquare);
						}
					}
				});
				compsToExperiment.add(button);
			}
        }
        pane.add(compsToExperiment);
    }
     
    /**
     * Create the GUI and show it.  For thread safety,
     * this method is invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        GridLayoutDemo frame = new GridLayoutDemo("GridLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
     
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
