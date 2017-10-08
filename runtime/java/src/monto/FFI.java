package monto;

import common.ConsCell;
import common.DecoratedNode;
import common.NodeFactory;
import common.StringCatter;
import core.NPair;
import core.NPair;
import java.util.List;
import java.util.function.Function;
import silver.support.monto.Init;
import silver.support.monto.NService;
import silver.support.monto.products.Pproduct;
import silver.support.monto.products.PproductIdentifier;

/**
 * FFI Helpers.
 */
public class FFI {
	public static Pair<String, Integer> onRequest(NService service, ProductIdentifier ident, List<Product> depList) {
		DecoratedNode dn = service.decorate();
		NodeFactory<NPair> onRequest = (NodeFactory<NPair>) dn.synthesized(Init.silver_support_monto_onRequest__ON__silver_support_monto_Service);
		return PairFromNPair(onRequest.invoke(new Object[] {
			new PproductIdentifier(
				new StringCatter(ident.name),
				new StringCatter(ident.language),
				new StringCatter(ident.path)),
			ConsCellFromList(FFI::PproductFromProduct, depList),
		}, new Object[] {}));
	}

	public static Pair<String, Boolean> doNegotiation(NService service, ServiceBrokerNegotiation sbn) {
		throw new RuntimeException("TODO");
	}

	private static <T, U> ConsCell ConsCellFromList(Function<T, U> convert, List<T> list) {
		ConsCell cons = ConsCell.nil;
		for(int i = list.size() - 1; i >= 0; i--)
			cons = new ConsCell(convert.apply(list.remove(i)), cons);
		return cons;
	}
	private static <T, U> Pair<T, U> PairFromNPair(NPair pair) {
		DecoratedNode dn = pair.decorate();
		return new Pair(
			(T) dn.synthesized(core.Init.core_fst__ON__core_Pair),
			(U) dn.synthesized(core.Init.core_snd__ON__core_Pair));
	}
	private static Pproduct PproductFromProduct(Product product) {
		throw new RuntimeException("TODO");
	}
}
