package com.ogen.chess.piece;

import java.util.HashSet;
import java.util.Set;

import com.ogen.chess.ChessBoard;
import com.ogen.chess.Coordinate;
import com.ogen.chess.Move;
import com.ogen.chess.Player;

public class King extends ChessPiece<King> {

	public King(final Player player) {
		super(ChessPiece.Type.KING, player);
	}
	
	@Override
	public Set<Move> getCapableMoves(final Coordinate currentPosition, final ChessBoard chessBoard) {
		final Set<Move> moves = new HashSet<>();
		final int currX = currentPosition.getX();
		final int currY = currentPosition.getY();
		addMoveForPosition(this, currentPosition, chessBoard, moves, currX, currY + 1);
		addMoveForPosition(this, currentPosition, chessBoard, moves, currX + 1, currY + 1);
		addMoveForPosition(this, currentPosition, chessBoard, moves, currX + 1, currY);
		addMoveForPosition(this, currentPosition, chessBoard, moves, currX + 1, currY - 1);
		addMoveForPosition(this, currentPosition, chessBoard, moves, currX, currY - 1);
		addMoveForPosition(this, currentPosition, chessBoard, moves, currX - 1, currY - 1);
		addMoveForPosition(this, currentPosition, chessBoard, moves, currX - 1, currY);
		addMoveForPosition(this, currentPosition, chessBoard, moves, currX - 1, currY + 1);
		// TODO don't castle if the king is in check
		// TODO don't castle if any of the squares that the king moves through is under attack
		// black side castle
		if (!this.hasMoved()) {
			// left castle
			chessBoard.getSquare(0, 7).ifPresent(square -> {
				square.getChessPiece().ifPresent(leftRook -> {
//					Optional<Square> leftBlackQueenSquare = chessBoard.getSquare(3, 7);
//					Optional<Square> leftBlackBishopSquare = chessBoard.getSquare(2, 7);
//					Optional<Square> leftBlackKnightSquare = chessBoard.getSquare(1, 7);
					if (!leftRook.hasMoved()) {
						
					}
				});
			});
			// right castle
			chessBoard.getSquare(7, 7).ifPresent(square -> {
				square.getChessPiece().ifPresent(rightRook -> {
//					Optional<Square> rightBlackBishopSquare = chessBoard.getSquare(5, 7);
//					Optional<Square> rightBlackKnightSquare = chessBoard.getSquare(6, 7);
					if (!rightRook.hasMoved()) {
						
					}
				});
			});
		}

		return moves;
	}

	@Override
	public King getPiece() {
		return this;
	}
	
	@Override
	public int getUnicodeRepr() {
		return this.getPlayer() == Player.WHITE ? 9812 : 9818;
	}

}
