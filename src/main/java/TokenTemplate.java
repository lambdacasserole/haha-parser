import java.util.regex.Pattern;

public class TokenTemplate {
    private Pattern pattern;
    private TokenType type;
    private boolean ignored;

    public TokenTemplate(Pattern pattern, TokenType type, boolean ignored) {
        this.pattern = pattern;
        this.type = type;
        this.ignored = ignored;
    }

    public TokenTemplate(String pattern, TokenType type, boolean ignored) {
        this(Pattern.compile(pattern), type, ignored);
    }

    public TokenTemplate(String pattern, TokenType type) {
        this(Pattern.compile(pattern), type, false);
    }

    public Pattern getPattern() {
        return pattern;
    }

    public TokenType getType() {
        return type;
    }

    public boolean isIgnored() {
        return ignored;
    }
}
