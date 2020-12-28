package com.ogen.chess.piece;

import java.util.HashSet;
import java.util.Set;

import com.ogen.chess.ChessBoard;
import com.ogen.chess.Coordinate;
import com.ogen.chess.Move;
import com.ogen.chess.Player;

public class Bishop extends ChessPiece<Bishop> {

	public Bishop(final Player player) {
		super(ChessPiece.Type.BISHOP, player);
	}

	@Override
	public Set<Move> getCapableMoves(final Coordinate currentPosition, final ChessBoard chessBoard) {
		final Set<Move> moves = new HashSet<>();
		addDiagonalMoves(this, currentPosition, chessBoard, moves);
		return moves;
	}

	@Override
	public Bishop getPiece() {
		return this;
	}
	
	@Override
	public int getUnicodeRepr() {
		return this.getPlayer() == Player.WHITE ? 9815 : 9821;
	}

}
