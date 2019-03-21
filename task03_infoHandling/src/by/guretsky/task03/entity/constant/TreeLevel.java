package by.guretsky.task03.entity.constant;

/**
 * Enum class for select text component level.
 *
 * @author ilyaguretsky
 */
public enum TreeLevel {
    /**
     * Text level.
     */
    TEXT(0),
    /**
     * Paragraph level.
     */
    PARAGRAPH(1),
    /**
     * Sentence level.
     */
    SENTENCE(2),
    /**
     * Lexeme level.
     */
    LEXEME(3),
    /**
     * Word level.
     */
    WORD(4),
    /**
     * Expression level.
     */
    EXPRESSION(5),
    /**
     * Punctuation mark level.
     */
    PUNCTUATION(6),
    /**
     * Symbol level.
     */
    SYMBOLS(7);

    /**
     * Constant value.
     */
    private int level;

    /**
     * Constructor - to create constant with parameters.
     *
     * @param componentLevel level
     */
    TreeLevel(final int componentLevel) {
        level = componentLevel;
    }

    /**
     * Level field getter.
     *
     * @return level
     */
    public int getLevel() {
        return level;
    }
}
