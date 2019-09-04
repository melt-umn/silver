package monto;

import com.google.gson.JsonElement;

public class Message {
	private String tag;
	private JsonElement contents;

	public Message(String tag, JsonElement contents) {
		this.tag = tag;
		this.contents = contents;
	}

	public String getTag() { return this.tag; }
	public JsonElement getContents() { return this.contents; }

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("tag = ");
		sb.append(this.tag);
		sb.append("\ncontents = ");
		sb.append(this.contents);
		return sb.toString();
	}
}
