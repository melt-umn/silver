package monto;

import java.util.List;

public class BrokerRequest {
	public final ProductIdentifier request;
	public final List<Product> products;

	public BrokerRequest(ProductIdentifier request, List<Product> products) {
		this.request = request;
		this.products = products;
	}
}
