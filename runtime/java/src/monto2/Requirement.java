package monto;

import com.google.gson.JsonElement;

public class Requirement {
	private String contents;
	private String language;
	private Source source;
	private int id;

	public Requirement(String contents, String language, Source source, int id) {
		this.contents = contents;
		this.language = language;
		this.source = source;
		this.id = id;
	}

	public String getContents() { return this.contents; }
	public String getLanguage() { return this.language; }
	public Source getSource() { return this.source; }
	public int getId() { return this.id; }
}
