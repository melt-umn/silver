package monto;

import com.google.gson.JsonElement;
import java.util.List;

public class Product {
	public final JsonElement contents;
	public final String language;
	public final String name;
	public final List<MetaItem> meta;
	public final String path;

	public Product(String language, String name, String path, JsonElement contents, List<MetaItem> meta) {
		this.language = language;
		this.name = name;
		this.path = path;
		this.contents = contents;
		this.meta = meta;
	}
}
