package com.ogen.chess.piece;

import java.util.Set;

import com.ogen.chess.ChessBoard;
import com.ogen.chess.Coordinate;
import com.ogen.chess.Move;
import com.ogen.chess.Player;
import com.ogen.chess.Square;

public abstract class ChessPiece<T extends ChessPiece<T>> {
	
	private final Type type;
	private final Player player;
	private boolean hasMoved = false;
	
	public enum Type {
		PAWN,
		ROOK,
		KNIGHT,
		BISHOP,
		QUEEN,
		KING;
	}
	
	public ChessPiece(final Type type, final Player player) {
		this.type = type;
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}
	
	public Type getType() {
		return type;
	}
	
	public boolean hasMoved() {
		return this.hasMoved;
	}
	
	public void setHasMoved(final boolean hasMoved) {
		this.hasMoved = hasMoved;
	}
	
	public abstract Set<Move> getCapableMoves(final Coordinate currentSquare, final ChessBoard chessBoard);
	
	public void postMove(final Move move) {
		setHasMoved(true);
	}
	
	public abstract T getPiece();
	
	public abstract int getUnicodeRepr();
	
	/**
	 * Adds a move for a given position to the set.
	 * Return true iff the caller shouldn't continue adding moves for a position in the current direction.
	 * @param currentPiece
	 * @param currentPosition
	 * @param chessBoard
	 * @param moves
	 * @param targetX
	 * @param targetY
	 * @return
	 */
	public static boolean addMoveForPosition(final ChessPiece<?> currentPiece, final Coordinate currentPosition, final ChessBoard chessBoard, final Set<Move> moves, final int targetX, final int targetY) {
		boolean killMove = false;
		if (chessBoard.getSquare(targetX, targetY).isPresent()) {
			Square square = chessBoard.getSquare(targetX, targetY).get();
			Move move = Move.of(Square.of(currentPosition, null), Square.of(targetX, targetY, currentPiece));
			if (square.getChessPiece().isPresent()) {
				ChessPiece<?> chessPiece = square.getChessPiece().get();
				if (Player.areOpposite(currentPiece.getPlayer(), chessPiece.getPlayer())) {
					moves.add(move);
					killMove = true;
				} else {
					return true;
				}
			} else {
				moves.add(move);
			}
		}
		return killMove;
	}
	
	public static void addPerpendicularMoves(final ChessPiece<?> currentPiece, final Coordinate currentPosition, final ChessBoard chessBoard, final Set<Move> moves) {
		final int currX = currentPosition.getX();
		final int currY = currentPosition.getY();
		// right
		for (int i = currX + 1; i < 8; i++) {
			if (addMoveForPosition(currentPiece, currentPosition, chessBoard, moves, i, currY)) {
				break;
			}
		}
		// left
		for (int i = currX - 1; i >= 0; i--) {
			if (addMoveForPosition(currentPiece, currentPosition, chessBoard, moves, i, currY)) {
				break;
			}
		}
		// up
		for (int j = currY + 1; j < 8; j++) {
			if (addMoveForPosition(currentPiece, currentPosition, chessBoard, moves, currX, j)) {
				break;
			}
		}
		// down
		for (int j = currY - 1; j >= 0; j--) {
			if (addMoveForPosition(currentPiece, currentPosition, chessBoard, moves, currX, j)) {
				break;
			}
		}
	}
	
	public static void addDiagonalMoves(final ChessPiece<?> currentPiece, final Coordinate currentPosition, final ChessBoard chessBoard, final Set<Move> moves) {
		final int currX = currentPosition.getX();
		final int currY = currentPosition.getY();
		// top-right
		for (int i = currX + 1, j = currY + 1; i < 8 && j < 8; i++, j++) {
			if (addMoveForPosition(currentPiece, currentPosition, chessBoard, moves, i, j)) {
				break;
			}
		}
		// bottom-right
		for (int i = currX + 1, j = currY - 1; i < 8 && j >= 0; i++, j--) {
			if (addMoveForPosition(currentPiece, currentPosition, chessBoard, moves, i, j)) {
				break;
			}
		}
		// bottom-left
		for (int i = currX - 1, j = currY - 1; i >= 0 && j >= 0; i--, j--) {
			if (addMoveForPosition(currentPiece, currentPosition, chessBoard, moves, i, j)) {
				break;
			}
		}
		// top-left
		for (int i = currX - 1, j = currY + 1; i >= 0 && j < 8; i--, j++) {
			if (addMoveForPosition(currentPiece, currentPosition, chessBoard, moves, i, j)) {
				break;
			}
		}
	}

	@Override
	public String toString() {
		final String firstLetter = this.type.name().substring(0, 1);
		return this.player == Player.WHITE ? firstLetter.toUpperCase() : firstLetter.toLowerCase();
	}

}
