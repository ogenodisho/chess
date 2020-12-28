package com.ogen.chess;

import java.util.Optional;

import com.ogen.chess.piece.ChessPiece;

public class Square {

	private final Coordinate coordinate;
	private Optional<ChessPiece<?>> chessPiece;
	
	private Square(final Coordinate coordinate, final Optional<ChessPiece<?>> chessPiece) {
		this.coordinate = coordinate;
		this.chessPiece = chessPiece;
	}
	
	public static Square of(final int x, final int y, ChessPiece<?> chessPiece) {
		return new Square(new Coordinate(x, y), Optional.ofNullable(chessPiece));
	}
	
	public static Square of(final Coordinate coordinate, ChessPiece<?> chessPiece) {
		return new Square(coordinate, Optional.ofNullable(chessPiece));
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public Optional<ChessPiece<?>> getChessPiece() {
		return chessPiece;
	}
	
	public void print() {
		if (this.chessPiece.isPresent()) {
			System.out.print((char) this.chessPiece.get().getUnicodeRepr());
		} else {
			System.out.print(".");
		}
	}
	
	@Override
	public String toString() {
		if (this.chessPiece.isPresent()) {
			return this.chessPiece.get().toString();
		}
		return ".";
	}

}
