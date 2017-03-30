package monto;

import com.google.gson.JsonElement;

public class Source {
	private String physical_name;
	private String logical_name;

	public Source(String physical, String logical) {
		this.physical_name = physical;
		this.logical_name = logical;
	}

	public String getPhysicalName() { return this.physical_name; }
	public String getLogicalName() { return this.logical_name; }
}
