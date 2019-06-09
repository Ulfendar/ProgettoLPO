package interpreterL.parser;

public enum TokenType { // important: SKIP, IDENT, and NUM must have ordinals 1, 2 and 3 
	EOF, SKIP, IDENT, NUM, STR, STR_CONTENT, PRINT, LET, PLUS, TIMES, EQ, ASSIGN, OPEN_PAR,
	CLOSE_PAR, OPEN_PAIR, CLOSE_PAIR, STMT_SEP, EXP_SEP, OPEN_BLOCK, CLOSE_BLOCK, MINUS, NOT, AND, BOOL,
	IF, ELSE, FST, SND, CONC
}
