package monto;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import common.ConsCell;
import common.DecoratedNode;
import common.IOToken;
import common.NodeFactory;
import common.StringCatter;
import core.NMaybe;
import core.NIOVal;
import core.Pjust;
import core.Pnothing;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import lib.json.NJson;
import lib.monto.Init;
import lib.monto.NConfig;
import lib.monto.NDependency;
import lib.monto.NMontoMessage;
import lib.monto.NProduct;
import lib.monto.NProductDescription;
import lib.monto.NRequest;
import lib.monto.NRequirement;
import lib.monto.NSource;
import lib.monto.PproductDependency;
import lib.monto.Prequest;
import lib.monto.Prequirement;
import lib.monto.Psource;
import lib.monto.PsourceDependency;

public class FFI {
	private static Gson gson = new Gson();

	public static List<String> runCallback(NodeFactory<NIOVal> callback, Request req) {
		NRequest nReq = NRequestFromRequest(req);
		NIOVal messageIOVal = callback.invoke(new Object[] {
			nReq,
			IOToken.singleton,
		}, new Object[] {});
		return toIOVal(cons -> ConsCellToList(FFI::NMontoMessageToString, (ConsCell) cons), messageIOVal);
	}

	private static <S, T> List<T> ConsCellToList(Function<S, T> convert, ConsCell cons) {
		List<T> list = new ArrayList<T>();
		while(!cons.nil()) {
			list.add(convert.apply((S) cons.head()));
			cons = cons.tail();
		}
		return list;
	}
	public static Config NConfigToConfig(NConfig config) {
		DecoratedNode dn = config.decorate();
		return new Config(
			StringCatterToString((StringCatter) dn.synthesized(Init.lib_monto_brokerAddr__ON__lib_monto_Config)),
			StringCatterToString((StringCatter) dn.synthesized(Init.lib_monto_description__ON__lib_monto_Config)),
			StringCatterToString((StringCatter) dn.synthesized(Init.lib_monto_serviceId__ON__lib_monto_Config)),
			StringCatterToString((StringCatter) dn.synthesized(Init.lib_monto_label__ON__lib_monto_Config)),
			ConsCellToList(FFI::NProductDescriptionToProductDescription, (ConsCell) dn.synthesized(Init.lib_monto_products__ON__lib_monto_Config)),
			ConsCellToList(FFI::NDependencyToProductDependency, (ConsCell) dn.synthesized(Init.lib_monto_dependencies__ON__lib_monto_Config)));
	}
	private static String NMontoMessageToString(NMontoMessage message) {
		NJson json = (NJson) message.decorate().synthesized(Init.lib_monto_json__ON__lib_monto_MontoMessage);
		StringCatter str = (StringCatter) json.decorate().synthesized(lib.json.Init.lib_json_json__ON__lib_json_Json);
		return StringCatterToString(str);
	}
	private static ProductDependency NDependencyToProductDependency(NDependency dep) {
		DecoratedNode dn = dep.decorate();
		if(dep instanceof PsourceDependency) {
			return new ProductDependency(
				StringCatterToString((StringCatter) dn.synthesized(Init.lib_monto_language__ON__lib_monto_Dependency)));
		} else if(dep instanceof PproductDependency) {
			return new ProductDependency(
				StringCatterToString((StringCatter) dn.synthesized(Init.lib_monto_serviceId__ON__lib_monto_Dependency)),
				StringCatterToString((StringCatter) dn.synthesized(Init.lib_monto_product__ON__lib_monto_Dependency)),
				StringCatterToString((StringCatter) dn.synthesized(Init.lib_monto_language__ON__lib_monto_Dependency)));
		} else {
			throw new RuntimeException("ProductDependency must be created with either the lib:monto:sourceDependency or lib:monto:productDependency productions!");
		}
	}
	private static ProductDescription NProductDescriptionToProductDescription(NProductDescription dsc) {
		DecoratedNode dn = dsc.decorate();
		return new ProductDescription(
			StringCatterToString((StringCatter) dn.synthesized(Init.lib_monto_product__ON__lib_monto_ProductDescription)),
			StringCatterToString((StringCatter) dn.synthesized(Init.lib_monto_language__ON__lib_monto_ProductDescription)));
	}
	private static String StringCatterToString(StringCatter string) {
		return string.toString();
	}
	private static <S, T> T toIOVal(Function<S, T> convert, NIOVal io) {
		DecoratedNode dn = io.decorate();
		return convert.apply((S) dn.synthesized(core.Init.core_iovalue__ON__core_IOVal));
	}

	private static <T, U> ConsCell ConsCellFromList(Function<T, U> convert, List<T> list) {
		ConsCell cons = ConsCell.nil;
		for(int i = list.size() - 1; i >= 0; i--)
			cons = new ConsCell(convert.apply(list.remove(i)), cons);
		return cons;
	}
	private static NRequest NRequestFromRequest(Request req) {
		return new Prequest(
			StringFromStringCatter(req.getServiceId()),
			NSourceFromSource(req.getSource()),
			ConsCellFromList(FFI::NRequirementFromRequirement, req.getRequirements()));
	}
	private static NRequirement NRequirementFromRequirement(Requirement req) {
		return new Prequirement(
			new Integer(req.getId()),
			StringFromStringCatter(req.getContents()),
			StringFromStringCatter(req.getLanguage()),
			NSourceFromSource(req.getSource()));
	}
	private static NSource NSourceFromSource(Source src) {
		NMaybe logicalName;
		if(src.getLogicalName() != null)
			logicalName = new Pjust(StringFromStringCatter(src.getLogicalName()));
		else
			logicalName = new Pnothing();
		return new Psource(
			StringFromStringCatter(src.getPhysicalName()),
			logicalName);
	}
	private static StringCatter StringFromStringCatter(String string) {
		return new StringCatter(string);
	}
}
