package com.ogen.chess.piece;

import java.util.HashSet;
import java.util.Set;

import com.ogen.chess.ChessBoard;
import com.ogen.chess.Coordinate;
import com.ogen.chess.Move;
import com.ogen.chess.Player;

public class Knight extends ChessPiece<Knight> {

	public Knight(final Player player) {
		super(ChessPiece.Type.KNIGHT, player);
	}
	
	@Override
	public Set<Move> getCapableMoves(final Coordinate currentPosition, final ChessBoard chessBoard) {
		final Set<Move> moves = new HashSet<>();
		final int currX = currentPosition.getX();
		final int currY = currentPosition.getY();
		addMoveForPosition(this, currentPosition, chessBoard, moves, currX + 1, currY + 2);
		addMoveForPosition(this, currentPosition, chessBoard, moves, currX + 2, currY + 1);
		addMoveForPosition(this, currentPosition, chessBoard, moves, currX + 2, currY - 1);
		addMoveForPosition(this, currentPosition, chessBoard, moves, currX + 1, currY - 2);
		addMoveForPosition(this, currentPosition, chessBoard, moves, currX - 1, currY - 2);
		addMoveForPosition(this, currentPosition, chessBoard, moves, currX - 2, currY - 1);
		addMoveForPosition(this, currentPosition, chessBoard, moves, currX - 2, currY + 1);
		addMoveForPosition(this, currentPosition, chessBoard, moves, currX - 1, currY + 2);
		return moves;
	}

	@Override
	public Knight getPiece() {
		return this;
	}
	
	@Override
	public int getUnicodeRepr() {
		return this.getPlayer() == Player.WHITE ? 9816 : 9822;
	}

}
