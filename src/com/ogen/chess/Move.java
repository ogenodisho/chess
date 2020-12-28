package com.ogen.chess;

import java.util.Set;

public class Move {
	
	final Set<Square> results;
	
	public static Move of(final Square... squares) {
		return new Move(Set.of(squares));
	}
	
	private Move(final Set<Square> results) {
		this.results = results;
	}
	
	private Move(final Set<Square> results, final Runnable sideEffect) {
		this.results = results;
	}
	
	public Set<Square> getSquares() {
		return this.results;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		this.results.forEach(result -> sb.append(result.toString()));
		return sb.toString();
	}
	
	public boolean contains(final Coordinate coordinate) {
		for (Square s : results) {
			if (s.getCoordinate().equals(coordinate)) {
				return true;
			}
		}
		return false;
	}

}
