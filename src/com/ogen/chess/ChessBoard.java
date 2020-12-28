package com.ogen.chess;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import com.ogen.chess.piece.Bishop;
import com.ogen.chess.piece.ChessPiece;
import com.ogen.chess.piece.King;
import com.ogen.chess.piece.Knight;
import com.ogen.chess.piece.Pawn;
import com.ogen.chess.piece.Queen;
import com.ogen.chess.piece.Rook;

public class ChessBoard {
	
	private Square[][] chessBoard = new Square[8][8];
	
	public static int TURN = 0;
	
	public ChessBoard() {
		populateChessBoard();
	}

	private void populateChessBoard() {
		populateChessBoardSquare(0, 0, new Rook(Player.WHITE));
		populateChessBoardSquare(1, 0, new Knight(Player.WHITE));
		populateChessBoardSquare(2, 0, new Bishop(Player.WHITE));
		populateChessBoardSquare(3, 0, new Queen(Player.WHITE));
		populateChessBoardSquare(4, 0, new King(Player.WHITE));
		populateChessBoardSquare(5, 0, new Bishop(Player.WHITE));
		populateChessBoardSquare(6, 0, new Knight(Player.WHITE));
		populateChessBoardSquare(7, 0, new Rook(Player.WHITE));
		for (int x = 0; x < 8; x++) {
			populateChessBoardSquare(x, 1, new Pawn(Player.WHITE));
		}
		for (int y = 2; y < 6; y++) {
			for (int x = 0; x < 8; x++) {
				populateChessBoardSquare(x, y, null);
			}
		}
		for (int x = 0; x < 8; x++) {
			populateChessBoardSquare(x, 6, new Pawn(Player.BLACK));
		}
		populateChessBoardSquare(0, 7, new Rook(Player.BLACK));
		populateChessBoardSquare(1, 7, new Knight(Player.BLACK));
		populateChessBoardSquare(2, 7, new Bishop(Player.BLACK));
		populateChessBoardSquare(3, 7, new Queen(Player.BLACK));
		populateChessBoardSquare(4, 7, new King(Player.BLACK));
		populateChessBoardSquare(5, 7, new Bishop(Player.BLACK));
		populateChessBoardSquare(6, 7, new Knight(Player.BLACK));
		populateChessBoardSquare(7, 7, new Rook(Player.BLACK));
	}
	
	private void populateChessBoardSquare(final int x, final int y, final ChessPiece<?> chessPiece) {
		chessBoard[x][y] = Square.of(x, y, chessPiece);
	}

	public Square[][] getChessBoard() {
		return chessBoard;
	}
	
	public Optional<Square> getSquare(final int x, final int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			return Optional.empty();
		}
		return Optional.of(chessBoard[x][y]);
	}
	
	public Optional<String> move(final Coordinate fromCoordinate, final Coordinate toCoordinate) {
		final Optional<ChessPiece<?>> fromChessPiece = this.chessBoard[fromCoordinate.getX()][fromCoordinate.getY()].getChessPiece();
		final Optional<ChessPiece<?>> toChessPiece = this.chessBoard[toCoordinate.getX()][toCoordinate.getY()].getChessPiece();
		if (fromChessPiece.isEmpty()) {
			return Optional.of(String.format("There's no piece to move at %s", fromCoordinate));
		}
		final Player currentPlayersTurn = TURN % 2 == 0 ? Player.WHITE : Player.BLACK;
		if (fromChessPiece.get().getPlayer() != currentPlayersTurn) {
			return Optional.of(String.format("It's %1$s's turn; that piece is not %1$s.", currentPlayersTurn));
		}
		if (toChessPiece.isPresent() && toChessPiece.get().getPlayer().equals(fromChessPiece.get().getPlayer())) {
			return Optional.of(String.format("Can't move to a square with your own piece in it", fromCoordinate));
		}
		Set<Move> capableMoves = fromChessPiece.get().getCapableMoves(fromCoordinate, this);
		Move desiredMove = null;
		boolean toCoordinateIsCapable = false;
		for (Move m : capableMoves) {
			if (m.contains(toCoordinate)) {
				toCoordinateIsCapable = true;
				desiredMove = m;
				break;
			}
		}
		if (toCoordinateIsCapable == false) {
			return Optional.of(String.format("%s isn't capable of moving to %s", fromChessPiece.get(), toCoordinate));
		}
		for (Square s : desiredMove.results) {
			final Coordinate c = s.getCoordinate();
			if (c.equals(fromCoordinate)) {
				fromChessPiece.get().postMove(desiredMove);
			}
			this.chessBoard[c.getX()][c.getY()] = s;
		}
		TURN++;
		return Optional.empty();
		// TODO
		// is the piece physically capable of moving there?
		// will moving there put your king in check?
		// move, set has moved, do the castle or promotion if necessary
		// is the enemy in check? if so, are they in check mate?
		// change player turn
	}
	
	public static void main(final String[] args) throws IOException {
		final ChessBoard chessBoard = new ChessBoard();
		printChessBoard(chessBoard);

		try(Scanner scanner = new Scanner(System.in)){
			while (true) {
				final String input = scanner.next();
				if (!Pattern.matches("[a-h][1-8]->[a-h][1-8]", input)) {
					System.out.println("bad input");
					continue;
				}
				final String[] move = input.split("->");
				final Coordinate fromCoordinate = new Coordinate(RankFile.valueOf(move[0]));
				final Coordinate toCoordinate = new Coordinate(RankFile.valueOf(move[1]));
				chessBoard.move(fromCoordinate, toCoordinate).ifPresentOrElse(System.out::println, () -> { printChessBoard(chessBoard); });
			}
		}
	}

	private static void printChessBoard(final ChessBoard chessBoard) {
		for (int y = 7; y >= 0; y--) {
			for (int x = 0; x < 8; x++) {
				chessBoard.getSquare(x, y).ifPresent(Square::print);
				System.out.print("\t");
			}
			System.out.println();
		}
	}

}
