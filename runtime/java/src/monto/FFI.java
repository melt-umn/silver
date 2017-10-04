package monto;

import com.google.gson.Gson;
import silver.support.monto.NService;

/**
 * FFI Helpers.
 */
public class FFI {
	private static Gson gson = new Gson;

	public static String onRequest(NService service, ProductDescriptor desc, List<Product> deps) {
		return null; // TODO
	}

	public static String getNegotiation(NService service) {
		return null; // TODO
	}
}
