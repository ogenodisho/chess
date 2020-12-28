package com.ogen.chess;

public enum RankFile {
	a8(0, 7), b8(1, 7), c8(2, 7), d8(3, 7), e8(4, 7), f8(5, 7), g8(6, 7), h8(7, 7),
	a7(0, 6), b7(1, 6), c7(2, 6), d7(3, 6), e7(4, 6), f7(5, 6), g7(6, 6), h7(7, 6),
	a6(0, 5), b6(1, 5), c6(2, 5), d6(3, 5), e6(4, 5), f6(5, 5), g6(6, 5), h6(7, 5),
	a5(0, 4), b5(1, 4), c5(2, 4), d5(3, 4), e5(4, 4), f5(5, 4), g5(6, 4), h5(7, 4),
	a4(0, 3), b4(1, 3), c4(2, 3), d4(3, 3), e4(4, 3), f4(5, 3), g4(6, 3), h4(7, 3),
	a3(0, 2), b3(1, 2), c3(2, 2), d3(3, 2), e3(4, 2), f3(5, 2), g3(6, 2), h3(7, 2),
	a2(0, 1), b2(1, 1), c2(2, 1), d2(3, 1), e2(4, 1), f2(5, 1), g2(6, 1), h2(7, 1),
	a1(0, 0), b1(1, 0), c1(2, 0), d1(3, 0), e1(4, 0), f1(5, 0), g1(6, 0), h1(7, 0),
	i9(-1, -1);
	
	private final int x;
	private final int y;
	
	private RankFile(final int x, final int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public static RankFile fromXY(final int x, final int y) {
		switch (y) {
		case 0:
			switch (x) {
			case 0:
				return RankFile.a1;
			case 1:
				return RankFile.b1;
			case 2:
				return RankFile.c1;
			case 3:
				return RankFile.d1;
			case 4:
				return RankFile.e1;
			case 5:
				return RankFile.f1;
			case 6:
				return RankFile.g1;
			case 7:
				return RankFile.h1;
			}
		case 1:
			switch (x) {
			case 0:
				return RankFile.a2;
			case 1:
				return RankFile.b2;
			case 2:
				return RankFile.c2;
			case 3:
				return RankFile.d2;
			case 4:
				return RankFile.e2;
			case 5:
				return RankFile.f2;
			case 6:
				return RankFile.g2;
			case 7:
				return RankFile.h2;
			}
		case 2:
			switch (x) {
			case 0:
				return RankFile.a3;
			case 1:
				return RankFile.b3;
			case 2:
				return RankFile.c3;
			case 3:
				return RankFile.d3;
			case 4:
				return RankFile.e3;
			case 5:
				return RankFile.f3;
			case 6:
				return RankFile.g3;
			case 7:
				return RankFile.h3;
			}
		case 3:
			switch (x) {
			case 0:
				return RankFile.a4;
			case 1:
				return RankFile.b4;
			case 2:
				return RankFile.c4;
			case 3:
				return RankFile.d4;
			case 4:
				return RankFile.e4;
			case 5:
				return RankFile.f4;
			case 6:
				return RankFile.g4;
			case 7:
				return RankFile.h4;
			}
		case 4:
			switch (x) {
			case 0:
				return RankFile.a5;
			case 1:
				return RankFile.b5;
			case 2:
				return RankFile.c5;
			case 3:
				return RankFile.d5;
			case 4:
				return RankFile.e5;
			case 5:
				return RankFile.f5;
			case 6:
				return RankFile.g5;
			case 7:
				return RankFile.h5;
			}
		case 5:
			switch (x) {
			case 0:
				return RankFile.a6;
			case 1:
				return RankFile.b6;
			case 2:
				return RankFile.c6;
			case 3:
				return RankFile.d6;
			case 4:
				return RankFile.e6;
			case 5:
				return RankFile.f6;
			case 6:
				return RankFile.g6;
			case 7:
				return RankFile.h6;
			}
		case 6:
			switch (x) {
			case 0:
				return RankFile.a7;
			case 1:
				return RankFile.b7;
			case 2:
				return RankFile.c7;
			case 3:
				return RankFile.d7;
			case 4:
				return RankFile.e7;
			case 5:
				return RankFile.f7;
			case 6:
				return RankFile.g7;
			case 7:
				return RankFile.h7;
			}
		case 7:
			switch (x) {
			case 0:
				return RankFile.a8;
			case 1:
				return RankFile.b8;
			case 2:
				return RankFile.c8;
			case 3:
				return RankFile.d8;
			case 4:
				return RankFile.e8;
			case 5:
				return RankFile.f8;
			case 6:
				return RankFile.g8;
			case 7:
				return RankFile.h8;
			}
		}
		return RankFile.i9;
	}
}
