package common;


import silver.core.*;
import common.exceptions.*;
import java.util.*;

/**
 * Implementation of helper functions on Nonterminals used by Origin-tracking code
 * 
 * @author louisg
 */
public final class OriginsUtil {

	// Re-useable instances of the various OIT markers
	public static PsetAtConstructionOIT SET_AT_CONSTRUCTION_OIT = new PsetAtConstructionOIT();
	public static PsetAtNewOIT SET_AT_NEW_OIT = new PsetAtNewOIT();
	public static PsetAtForwardingOIT SET_AT_FORWARDING_OIT = new PsetAtForwardingOIT();
	public static PsetAtAccessOIT SET_AT_ACCESS_OIT = new PsetAtAccessOIT();
	public static PsetFromParserOIT SET_FROM_PARSER_OIT = new PsetFromParserOIT();
	public static PsetFromParserActionOIT SET_FROM_PARSER_ACTION_OIT = new PsetFromParserActionOIT();
	public static PsetFromFFIOIT SET_FROM_FFI_OIT = new PsetFromFFIOIT();
	public static PsetFromReflectionOIT SET_FROM_REFLECTION_OIT = new PsetFromReflectionOIT();
	public static PsetFromReificationOIT SET_FROM_REIFICATION_OIT = new PsetFromReificationOIT();
	public static PsetFromEntryOIT SET_FROM_ENTRY_OIT = new PsetFromEntryOIT();
	public static PsetInGlobalOIT SET_IN_GLOBAL_OIT = new PsetInGlobalOIT();

	// Sexperify code. Horrible ugly hack to serialize + spit out silver objects as python expressions
	private static String ids(final Object arg){
		return Integer.toString(System.identityHashCode(arg));
	}

	private static String pySanitize(String arg) {
		return arg.replace("\\","\\\\").replace("\"", "\\\"").replace("\n","\\n");
	}

	private static String sexprifyObj(List<String> seen, Object arg) {

		if (arg instanceof DecoratedNode) arg = ((DecoratedNode)arg).undecorate();

		String id = ids(arg);
		if (seen.contains(id)) return "[" + id + "]";
		seen.add(id);

		String r = "[" + id + ", ";
		if (arg instanceof Integer)
			r += "'!Integer', " + arg.toString();
		else if (arg instanceof Float)
			r += "'!Float', " + arg.toString();
		else if (arg instanceof Boolean)
			r += "'!Boolean', " + arg.toString();
		else if (arg instanceof StringCatter)
			r += "'!String', \""+pySanitize(arg.toString())+"\"";
		else if (arg instanceof Terminal){
			Terminal t = (Terminal) arg;
			r += "'!Terminal', '"+pySanitize(t.getName())+"', \""+pySanitize(t.lexeme.toString())+"\", "+sexprifyObj(seen, t.location);
		} else if (arg instanceof Node) {
			Node n = (Node) arg;
			r += "'" + pySanitize(n.getName()) + "', [";
			for (int i=0; i<n.getNumberOfChildren(); i++){
				r += sexprifyObj(seen, n.getChild(i));
				if (i!=n.getNumberOfChildren()-1) r+=",";
			}
			r+="],[";

			String[] annotationNames = n.getAnnoNames();
			for (int i = 0; i < annotationNames.length; i++) {
				String name = annotationNames[i];
				Object value = n.getAnno(name);
				r += "('"+pySanitize(name)+"', "+sexprifyObj(seen, value)+")";
				if (i!=annotationNames.length-1) r+=",";
			}
			r += "],";
			r += sexprifyObj(seen, getOriginOrNull(n));
		} else if (arg instanceof ConsCell){
			ConsCell cc = (ConsCell) arg;
			if (cc.nil()) {
				r += "'!List', []";
			}else{
				Object next;
				r += "'!List', [";
				while (!cc.nil()) {
					r += sexprifyObj(seen, cc.head());
					r += ",";
					next = cc.tail();
					if (next instanceof DecoratedNode) next = ((DecoratedNode)next).undecorate();
					if (!(next instanceof ConsCell)) {
						throw new SilverInternalError("ConsCell.tail() evaluated to not a ConsCell");
					}
					cc=(ConsCell)next;
				}
				r += "]";
			}
		} else if (arg == null){
			r += "'!Null'";
		} else {
			r += "'???', \""+pySanitize(arg.toString())+"\"";
		}

		r += "]";

		return r;
	}

	public static StringCatter sexprify(final Object arg) {
		List<String> seen = new ArrayList<String>();
		return new StringCatter(sexprifyObj(seen, arg));
	}

	public static NOriginInfo getOriginOrNull(final Object arg) {
		if (arg instanceof TrackedNode) return ((TrackedNode)arg).origin;
		return null;
	}

	// Implementation of the stdlib origins helpers

	public static silver.core.NMaybe polyGetOrigin(Object o) {
		if (o instanceof DecoratedNode) o = ((DecoratedNode)o).undecorate();
		NOriginInfo r = getOriginOrNull(o);
		if (r == null) return silver.core.Pnothing.rtConstruct(null);
		return silver.core.Pjust.rtConstruct(null, r);
	}

	public static silver.core.NMaybe getOriginLink(silver.core.NOriginInfo o) {
		if (o instanceof PoriginOriginInfo)
			return silver.core.Pjust.rtConstruct(null, ((PoriginOriginInfo)o).getChild_origin());

		if (o instanceof PoriginAndRedexOriginInfo)
			return silver.core.Pjust.rtConstruct(null, ((PoriginAndRedexOriginInfo)o).getChild_origin());

		return silver.core.Pnothing.rtConstruct(null);
	}

	// Misc helper

	public static<T> ArrayList<T> arrayListOfArray(T[] a) {
		ArrayList<T> l = new ArrayList<T>();
		Collections.addAll(l, a);
		return l;
	}

	public static Object duplicatePoly(Object x, OriginContext c) {
		if (x instanceof TrackedNode) return ((TrackedNode)x).duplicate(c);
		else return x;
	}
}
