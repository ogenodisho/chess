package com.ogen.chess;

import java.util.Objects;

public class Coordinate {
	
	private final int x;
	private final int y;
	private final RankFile rankFile;
	
	public Coordinate(final int x, final int y) {
		this.x = x;
		this.y = y;
		this.rankFile = RankFile.fromXY(x, y);
	}
	
	public Coordinate(final RankFile rankFile) {
		this.x = rankFile.getX();
		this.y = rankFile.getY();
		this.rankFile = rankFile;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public RankFile getRankFile() {
		return rankFile;
	}
	
	@Override
	public String toString() {
		return String.format("%s(%d, %d)", this.rankFile, x, y);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(rankFile, x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Coordinate)) {
			return false;
		}
		Coordinate other = (Coordinate) obj;
		return rankFile == other.rankFile && x == other.x && y == other.y;
	}
	
}
