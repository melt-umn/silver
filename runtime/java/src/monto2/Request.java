package monto;

import com.google.gson.JsonElement;
import java.util.List;

public class Request {
	private Source source;
	private String service_id;
	private List<Requirement> requirements;

	public Request(Source source, String serviceId, List<Requirement> requirements) {
		this.source = source;
		this.service_id = serviceId;
		this.requirements = requirements;
	}

	public Source getSource() { return this.source; }
	public String getServiceId() { return this.service_id; }
	public List<Requirement> getRequirements() { return this.requirements; }
}
