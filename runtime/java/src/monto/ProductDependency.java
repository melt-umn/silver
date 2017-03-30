package monto;

public class ProductDependency {
	private String serviceID;
	private String product;
	private String language;

	public ProductDependency(String language) {
		this.serviceID = null;
		this.product = null;
		this.language = language;
	}
	public ProductDependency(String serviceID, String product, String language) {
		this.serviceID = serviceID;
		this.product = product;
		this.language = language;
	}
}
