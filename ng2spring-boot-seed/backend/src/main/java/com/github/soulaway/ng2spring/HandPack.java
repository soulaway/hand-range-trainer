package com.github.soulaway.ng2spring;

/**
 * ♣♢ ♡♠ A-2
 * X  X  XXXX
 * 0  0  0000 - A♠
 *  ...
 * 1  1  1100 - 2♣
 */

public enum HandPack {
	// green 00 U+2660 ♠
	gA {
		@Override
		public Integer getCode() {return 0;}
		@Override
		public char getColor() {return GREEN;}
		@Override
		public String getValue() {return "A";}
	},
	gK {
		@Override
		public Integer getCode() {return 1;}
		@Override
		public char getColor() {return GREEN;}
		@Override
		public String getValue() {return "K";}
	},
	gQ {
		@Override
		public Integer getCode() {return 2;}
		@Override
		public char getColor() {return GREEN;}
		@Override
		public String getValue() {return "Q";}
	},
	gJ {
		@Override
		public Integer getCode() {return 3;}
		@Override
		public char getColor() {return GREEN;}
		@Override
		public String getValue() {return "J";}
	},
	g10 {
		@Override
		public Integer getCode() {return 4;}
		@Override
		public char getColor() {return GREEN;}
		@Override
		public String getValue() {return "10";}
	},
	g9 {
		@Override
		public Integer getCode() {return 5;}
		@Override
		public char getColor() {return GREEN;}
		@Override
		public String getValue() {return "9";}
	},
	g8 {
		@Override
		public Integer getCode() {return 6;}
		@Override
		public char getColor() {return GREEN;}
		@Override
		public String getValue() {return "8";}
	},
	g7 {
		@Override
		public Integer getCode() {return 7;}
		@Override
		public char getColor() {return GREEN;}
		@Override
		public String getValue() {return "7";}
	},
	g6 {
		@Override
		public Integer getCode() {return 8;}
		@Override
		public char getColor() {return GREEN;}
		@Override
		public String getValue() {return "6";}
	},
	g5 {
		@Override
		public Integer getCode() {return 9;}
		@Override
		public char getColor() {return GREEN;}
		@Override
		public String getValue() {return "5";}
	},
	g4 {
		@Override
		public Integer getCode() {return 10;}
		@Override
		public char getColor() {return GREEN;}
		@Override
		public String getValue() {return "4";}
	},
	g3 {
		@Override
		public Integer getCode() {return 11;}
		@Override
		public char getColor() {return GREEN;}
		@Override
		public String getValue() {return "3";}
	},
	g2 {
		@Override
		public Integer getCode() {return 12;}
		@Override
		public char getColor() {return GREEN;}
		@Override
		public String getValue() {return "2";}
	},
	// red U+2661 ♡
	rA {
		@Override
		public Integer getCode() {return 16;}
		@Override
		public char getColor() {return RED;}
		@Override
		public String getValue() {return "A";}
	},
	rK {
		@Override
		public Integer getCode() {return 17;}
		@Override
		public char getColor() {return RED;}
		@Override
		public String getValue() {return "K";}
	},
	rQ {
		@Override
		public Integer getCode() {return 18;}
		@Override
		public char getColor() {return RED;}
		@Override
		public String getValue() {return "Q";}
	},
	rJ {
		@Override
		public Integer getCode() {return 19;}
		@Override
		public char getColor() {return RED;}
		@Override
		public String getValue() {return "J";}
	},
	r10 {
		@Override
		public Integer getCode() {return 20;}
		@Override
		public char getColor() {return RED;}
		@Override
		public String getValue() {return "10";}
	},
	r9 {
		@Override
		public Integer getCode() {return 21;}
		@Override
		public char getColor() {return RED;}
		@Override
		public String getValue() {return "9";}
	},
	r8 {
		@Override
		public Integer getCode() {return 22;}
		@Override
		public char getColor() {return RED;}
		@Override
		public String getValue() {return "8";}
	},
	r7 {
		@Override
		public Integer getCode() {return 23;}
		@Override
		public char getColor() {return RED;}
		@Override
		public String getValue() {return "7";}
	},
	r6 {
		@Override
		public Integer getCode() {return 24;}
		@Override
		public char getColor() {return RED;}
		@Override
		public String getValue() {return "6";}
	},
	r5 {
		@Override
		public Integer getCode() {return 25;}
		@Override
		public char getColor() {return RED;}
		@Override
		public String getValue() {return "5";}
	},
	r4 {
		@Override
		public Integer getCode() {return 26;}
		@Override
		public char getColor() {return RED;}
		@Override
		public String getValue() {return "4";}
	},
	r3 {
		@Override
		public Integer getCode() {return 27;}
		@Override
		public char getColor() {return RED;}
		@Override
		public String getValue() {return "3";}
	},
	r2 {
		@Override
		public Integer getCode() {return 28;}
		@Override
		public char getColor() {return RED;}
		@Override
		public String getValue() {return "2";}
	},
	// yellow U+2662 ♢
	yA {
		@Override
		public Integer getCode() {return 32;}
		@Override
		public char getColor() {return YELLOW;}
		@Override
		public String getValue() {return "A";}
	},
	yK {
		@Override
		public Integer getCode() {return 33;}
		@Override
		public char getColor() {return YELLOW;}
		@Override
		public String getValue() {return "K";}
	},
	yQ {
		@Override
		public Integer getCode() {return 34;}
		@Override
		public char getColor() {return YELLOW;}
		@Override
		public String getValue() {return "Q";}
	},
	yJ {
		@Override
		public Integer getCode() {return 35;}
		@Override
		public char getColor() {return YELLOW;}
		@Override
		public String getValue() {return "J";}
	},
	y10 {
		@Override
		public Integer getCode() {return 36;}
		@Override
		public char getColor() {return YELLOW;}
		@Override
		public String getValue() {return "10";}
	},
	y9 {
		@Override
		public Integer getCode() {return 37;}
		@Override
		public char getColor() {return YELLOW;}
		@Override
		public String getValue() {return "9";}
	},
	y8 {
		@Override
		public Integer getCode() {return 38;}
		@Override
		public char getColor() {return YELLOW;}
		@Override
		public String getValue() {return "8";}
	},
	y7 {
		@Override
		public Integer getCode() {return 39;}
		@Override
		public char getColor() {return YELLOW;}
		@Override
		public String getValue() {return "7";}
	},
	y6 {
		@Override
		public Integer getCode() {return 40;}
		@Override
		public char getColor() {return YELLOW;}
		@Override
		public String getValue() {return "6";}
	},
	y5 {
		@Override
		public Integer getCode() {return 41;}
		@Override
		public char getColor() {return YELLOW;}
		@Override
		public String getValue() {return "5";}
	},
	y4 {
		@Override
		public Integer getCode() {return 42;}
		@Override
		public char getColor() {return YELLOW;}
		@Override
		public String getValue() {return "4";}
	},
	y3 {
		@Override
		public Integer getCode() {return 43;}
		@Override
		public char getColor() {return YELLOW;}
		@Override
		public String getValue() {return "3";}
	},
	y2 {
		@Override
		public Integer getCode() {return 44;}
		@Override
		public char getColor() {return YELLOW;}
		@Override
		public String getValue() {return "2";}
	},
	// black U+2663 ♣
	bA {
		@Override
		public Integer getCode() {return 48;}

		@Override
		public char getColor() {return BLACK;}

		@Override
		public String getValue() {return "A";}
	},
	bK {
		@Override
		public Integer getCode() {return 49;}
		@Override
		public char getColor() {return BLACK;}
		@Override
		public String getValue() {return "K";}
	},
	bQ {
		@Override
		public Integer getCode() {return 50;}
		@Override
		public char getColor() {return BLACK;}
		@Override
		public String getValue() {return "Q";}
	},
	bJ {
		@Override
		public Integer getCode() {return 51;}
		@Override
		public char getColor() {return BLACK;}
		@Override
		public String getValue() {return "J";}
	},
	b10 {
		@Override
		public Integer getCode() {return 52;}
		@Override
		public char getColor() {return BLACK;}
		@Override
		public String getValue() {return "10";}
	},
	b9 {
		@Override
		public Integer getCode() {return 53;}
		@Override
		public char getColor() {return BLACK;}
		@Override
		public String getValue() {return "9";}
	},
	b8 {
		@Override
		public Integer getCode() {return 54;}
		@Override
		public char getColor() {return BLACK;}
		@Override
		public String getValue() {return "8";}
	},
	b7 {
		@Override
		public Integer getCode() {return 55;}
		@Override
		public char getColor() {return BLACK;}
		@Override
		public String getValue() {return "7";}
	},
	b6 {
		@Override
		public Integer getCode() {return 56;}
		@Override
		public char getColor() {return BLACK;}
		@Override
		public String getValue() {return "6";}
	},
	b5 {
		@Override
		public Integer getCode() {return 57;}
		@Override
		public char getColor() {return BLACK;}
		@Override
		public String getValue() {return "5";}
	},
	b4 {
		@Override
		public Integer getCode() {return 58;}
		@Override
		public char getColor() {return BLACK;}
		@Override
		public String getValue() {return "4";}
	},
	b3 {
		@Override
		public Integer getCode() {return 59;}
		@Override
		public char getColor() {return BLACK;}
		@Override
		public String getValue() {return "3";}
	},
	b2 {
		@Override
		public Integer getCode() {return 60;}
		@Override
		public char getColor() {return BLACK;}
		@Override
		public String getValue() {return "2";}
	};
	
	private static final char GREEN = '♠';
	private static final char RED = '♡';
	private static final char YELLOW = '♢';
	private static final char BLACK = '♣';
	
	public static HandPack getByCode(Integer code){
		for (HandPack hp : HandPack.values()){
			if (hp.getCode() == code){
				return hp;
			}
		}
		throw new IllegalArgumentException("No such card with code " + code);
	}
	
	public abstract Integer getCode();
	public abstract char getColor();
	public abstract String getValue();
}
