package com.ogen.chess.piece;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import com.ogen.chess.ChessBoard;
import com.ogen.chess.Coordinate;
import com.ogen.chess.Move;
import com.ogen.chess.Player;
import com.ogen.chess.Square;

public class Pawn extends ChessPiece<Pawn> {
	
	private Optional<Integer> doubleStepTurn = Optional.empty();

	public Pawn(final Player player) {
		super(ChessPiece.Type.PAWN, player);
	}
	
	@Override
	public Set<Move> getCapableMoves(final Coordinate currentPosition, final ChessBoard chessBoard) {
		final Set<Move> moves = new HashSet<>();
		final int currX = currentPosition.getX();
		final int currY = currentPosition.getY();
		final boolean isWhite = this.getPlayer() == Player.WHITE;
		
		// forward move
		addMoveForPosition(this, currentPosition, chessBoard, moves, currX, isWhite ? currY + 1 : currY - 1);
		
		// double step
		chessBoard.getSquare(currX, isWhite ? currY + 2 : currY - 2).ifPresent(square -> {
			if (square.getChessPiece().isEmpty() && !this.hasMoved()) {
				moves.add(Move.of(Square.of(currentPosition, null), Square.of(currX, isWhite ? currY + 2 : currY - 2, this)));
			}
		});
		
		// left kill
		chessBoard.getSquare(currX - 1, isWhite ? currY + 1 : currY - 1).ifPresent(square -> {
			square.getChessPiece().ifPresent(c -> {
				if (Player.areOpposite(this.getPlayer(), c.getPlayer())) {
					addMoveForPosition(this, currentPosition, chessBoard, moves, currX - 1, isWhite ? currY + 1 : currY - 1);
				}
			});
		});
		
		// left en passant
		chessBoard.getSquare(currX - 1, currY).ifPresent(square -> {
			square.getChessPiece().ifPresent(c -> {
				if (c.getType() == Type.PAWN && Player.areOpposite(this.getPlayer(), c.getPlayer())) {
					Pawn p = (Pawn) c.getPiece();
					if (p.doubleStepTurn.isPresent() && p.doubleStepTurn.get().equals(ChessBoard.TURN - 1)) {
						// at this point, there is a an opposite-colored pawn directly adjacent to the chess
						// piece and it has double stepped in the last turn, therefore we can en passant here
						moves.add(Move.of(Square.of(currentPosition, null), Square.of(currX - 1, isWhite ? currY + 1 : currY - 1, this), Square.of(currX - 1, currY, null)));
					}
				}
			});
		});
		
		// right kill
		chessBoard.getSquare(currX + 1, isWhite ? currY + 1 : currY - 1).ifPresent(square -> {
			square.getChessPiece().ifPresent(c -> {
				if (Player.areOpposite(this.getPlayer(), c.getPlayer())) {
					addMoveForPosition(this, currentPosition, chessBoard, moves, currX + 1, isWhite ? currY + 1 : currY - 1);
				}
			});
		});
		
		// right en passant
		chessBoard.getSquare(currX + 1, currY).ifPresent(square -> {
			square.getChessPiece().ifPresent(c -> {
				if (c.getType() == Type.PAWN && Player.areOpposite(this.getPlayer(), c.getPlayer())) {
					// check if pawn has just moved twice in the last turn
					Pawn p = (Pawn) c.getPiece();
					if (p.doubleStepTurn.isPresent() && p.doubleStepTurn.get().equals(ChessBoard.TURN - 1)) {
						// at this point, there is a an opposite-colored pawn directly adjacent to the chess
						// piece and it has double stepped in the last turn, therefore we can en passant here
						moves.add(Move.of(Square.of(currentPosition, null), Square.of(currX + 1, isWhite ? currY + 1 : currY - 1, this), Square.of(currX + 1, currY, null)));
					}
				}
			});
		});
		
		return moves;
	}

	@Override
	public Pawn getPiece() {
		return this;
	}

	@Override
	public void postMove(Move move) {
		super.postMove(move);
		if (move.getSquares().size() != 2) {
			return;
		}
		Iterator<Square> squareIt = move.getSquares().iterator();
		Square one = squareIt.next();
		Square two = squareIt.next();
		if (Math.abs(one.getCoordinate().getY() - two.getCoordinate().getY()) == 2) {
			this.doubleStepTurn = Optional.of(ChessBoard.TURN);
		}
	}

	public Optional<Integer> getDoubleStepTurn() {
		return doubleStepTurn;
	}

	@Override
	public int getUnicodeRepr() {
		return this.getPlayer() == Player.WHITE ? 9817 : 9823;
	}

}
