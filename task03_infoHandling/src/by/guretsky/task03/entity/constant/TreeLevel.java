package by.guretsky.task03.entity.constant;

public enum TreeLevel {
    TEXT(0),
    PARAGRAPH(1),
    SENTENCE(2),
    LEXEME(3),
    WORD(4),
    EXPRESSION(5),
    PUNCTUATION(6),
    SYMBOLS(7);

    private int level;

    TreeLevel(final int componentLevel) {
        level = componentLevel;
    }

    public int getLevel() {
        return level;
    }
}
