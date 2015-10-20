package monto;

public class MontoMessage {
    private String source;
    private String language;
    private String contents;
    private String selections;

    public MontoMessage(String _source, String _language, String _contents, String _selections) {
            source = _source;
            language = _language;
            contents = _contents;
            selections = _selections;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getSelections() {
        return selections;
    }

    public void setSelections(String selections) {
        this.selections = selections;
    }
}
