package monto;

import java.util.List;

public class ServiceBrokerNegotiation {
	public final ProtocolVersion monto;
	public final SoftwareVersion broker;
	public final List<String> extensions;

	public ServiceBrokerNegotiation(ProtocolVersion monto, SoftwareVersion broker, List<String> extensions) {
		this.monto = monto;
		this.broker = broker;
		this.extensions = extensions;
	}
}
