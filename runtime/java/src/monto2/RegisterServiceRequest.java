package monto2;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a request to be registered as a server.
 */
public class RegisterServiceRequest {
	public String service_id;
	public String label;
	public String description;
	// TODO No options
	public List<ProductDescription> products = new ArrayList<>();
	public List<ProductDependency> dependencies = new ArrayList<>();
	public List<CommandDescription> commands = new ArrayList<>();
}
