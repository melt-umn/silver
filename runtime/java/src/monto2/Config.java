package monto;

import java.util.List;

public class Config {
	private List<ProductDependency> dependencies;
	private List<ProductDescription> products;
	private String brokerAddr;
	private String description;
	private String label;
	private String serviceId;

	public Config(String brokerAddr, String description, String serviceId, String label, List<ProductDescription> products, List<ProductDependency> dependencies) {
		this.brokerAddr = brokerAddr;
		this.dependencies = dependencies;
		this.description = description;
		this.label = label;
		this.products = products;
		this.serviceId = serviceId;
	}

	public List<ProductDependency> getDependencies() { return this.dependencies; }
	public List<ProductDescription> getProducts() { return this.products; }
	public String getBrokerAddr() { return this.brokerAddr; }
	public String getServiceDescription() { return this.description; }
	public String getServiceId() { return this.serviceId; }
	public String getServiceLabel() { return this.label; }
}
