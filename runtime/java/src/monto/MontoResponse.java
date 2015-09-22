package monto;
import org.json.simple.JSONObject;

/*****
 * This is just a convenience class for putting raw strings into JSON format.
 * @author Charles Hofer
 *****/

public class MontoResponse {
	private String source;
	private String product;
	private String language;
	private String contents;
	
	public MontoResponse(String source, String product, String language,
			String contents) {
		this.source = source;
		this.product = product;
		this.language = language;
		this.contents = contents;
	}
	
	public String getSource() {
		return source;
	}
	
	public String getProduct() {
		return product;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public String getContents() {
		return contents;
	}
	
	public JSONObject toJSON() {
		JSONObject ret = new JSONObject();
		ret.put("source", source);
		ret.put("product", product);
		ret.put("language", language);
		ret.put("contents", contents);
		return ret;
	}
	
	public String toString() {
		return toJSON().toJSONString();
	}
}
