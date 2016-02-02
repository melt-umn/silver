package monto;

public class MontoProduct {
    private String source;
    private String product;
    private String language;
    private String contents;

    public MontoProduct(String source, String product, String language, String contents) {
        this.source = source;
        this.product = product;
        this.language = language;
        this.contents = contents;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
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
}
