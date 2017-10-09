package monto;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import common.ConsCell;
import common.DecoratedNode;
import common.NodeFactory;
import common.StringCatter;
import core.NMaybe;
import core.NPair;
import core.Pjust;
import core.Pnothing;
import core.Ppair;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import lib.json.NJson;
import lib.json.PjsonArray;
import lib.json.PjsonBoolean;
import lib.json.PjsonFloat;
import lib.json.PjsonNull;
import lib.json.PjsonObject;
import lib.json.PjsonString;
import silver.support.monto.Init;
import silver.support.monto.NService;
import silver.support.monto.negotiation.NProtocolVersion;
import silver.support.monto.negotiation.NServiceBrokerNegotiation;
import silver.support.monto.negotiation.NSoftwareVersion;
import silver.support.monto.negotiation.PprotocolVersion;
import silver.support.monto.negotiation.PserviceBrokerNegotiation;
import silver.support.monto.negotiation.PsoftwareVersion;
import silver.support.monto.products.NMetaItem;
import silver.support.monto.products.PmetaItem;
import silver.support.monto.products.Pproduct;
import silver.support.monto.products.PproductIdentifier;
import silver.support.monto.products.PproductValue;

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
		DecoratedNode dn = service.decorate();
		NodeFactory<NPair> doNegotiation = (NodeFactory<NPair>) dn.synthesized(Init.silver_support_monto_doNegotiation__ON__silver_support_monto_Service);
		NServiceBrokerNegotiation sbn2 = NServiceBrokerNegotiationFromServiceBrokerNegotiation(sbn);
		return PairFromNPair(doNegotiation.invoke(new Object[] {sbn2}, new Object[] {}));
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
		NJson json = NJsonFromJsonElement(product.contents);
		PproductValue value = new PproductValue(new StringCatter(product.name), json);
		ConsCell metas = ConsCellFromList(FFI::NMetaItemFromMetaItem, product.meta);
		return new Pproduct(value, metas,
			new StringCatter(product.language),
			new StringCatter(product.path));
	}
	private static NJson NJsonFromJsonElement(JsonElement el) {
		if(el.isJsonNull()) {
			return new PjsonNull();
		} else if(el.isJsonArray()) {
			JsonArray arr = el.getAsJsonArray();
			List<JsonElement> list = new ArrayList();
			arr.forEach(list::add);
			ConsCell cc = ConsCellFromList(FFI::NJsonFromJsonElement, list);
			return new PjsonArray(cc);
		} else if(el.isJsonObject()) {
			JsonObject obj = el.getAsJsonObject();
			List<NPair> list = new ArrayList();
			obj.entrySet().iterator().forEachRemaining(e -> {
				String k = e.getKey();
				JsonElement v = e.getValue();
				list.add(new Ppair(new StringCatter(k), NJsonFromJsonElement(v)));
			});
			ConsCell cc = ConsCellFromList(x -> x, list);
			return new PjsonObject(cc);
		} else {
			JsonPrimitive prim = el.getAsJsonPrimitive();
			if(prim.isBoolean()) {
				return new PjsonBoolean(prim.getAsBoolean());
			} else if(prim.isNumber()) {
				return new PjsonFloat(prim.getAsNumber().doubleValue());
			} else if(prim.isString()) {
				return new PjsonString(new StringCatter(prim.getAsString()));
			} else {
				throw new RuntimeException("TODO NJsonFromJsonElement(" + prim + ")");
			}
		}
	}
	private static NMetaItem NMetaItemFromMetaItem(MetaItem meta) {
		return new PmetaItem(new StringCatter(meta.service),
			new StringCatter(meta.type),
			new StringCatter(meta.value));
	}
	private static NProtocolVersion NProtocolVersionFromProtocolVersion(ProtocolVersion v) {
		return new PprotocolVersion(v.major, v.minor, v.patch);
	}
	private static NSoftwareVersion NSoftwareVersionFromSoftwareVersion(SoftwareVersion v) {
		return new PsoftwareVersion(new StringCatter(v.id),
			MaybeFromNullable(StringCatter::new, v.name),
			MaybeFromNullable(StringCatter::new, v.vendor),
			MaybeFromNullable(x -> x, v.major),
			MaybeFromNullable(x -> x, v.minor),
			MaybeFromNullable(x -> x, v.patch));
	}
	private static NServiceBrokerNegotiation NServiceBrokerNegotiationFromServiceBrokerNegotiation(ServiceBrokerNegotiation sbn) {
		return new PserviceBrokerNegotiation(
			NProtocolVersionFromProtocolVersion(sbn.monto),
			NSoftwareVersionFromSoftwareVersion(sbn.broker),
			ConsCellFromList(StringCatter::new, sbn.extensions));
	}
	private static <T, U> NMaybe MaybeFromNullable(Function<T, U> f, T t) {
		if(t == null)
			return new Pnothing();
		else
			return new Pjust(f.apply(t));
	}
}
