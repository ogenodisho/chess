package com.ogen.chess;

public enum Player {
	BLACK,
	WHITE;
	
	public static boolean areOpposite(Player one, Player two) {
		return Boolean.logicalXor(one == WHITE, two == WHITE);
	}
}
