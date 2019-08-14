package common;


import core.*;
import common.exceptions.*;

/**
 * Implementation of helper functions on Nonterminals used by Origin-tracking code
 * 
 * @author louisg
 */
public final class Origins {
	public static Object debug(final Object arg) {
		System.out.println("\n-----  OTX DEBUG  -----\n");
		System.out.println("arg = " + arg + "\n");
		System.out.println("----- / OTX DEBUG -----\n\n");
		return arg;
	}

	private static String ids(final Object arg){
		return Integer.toString(System.identityHashCode(arg));
	}

	private static String sexprifyObj(Object arg) {
		if (arg instanceof DecoratedNode) arg = ((DecoratedNode)arg).undecorate();

		String r = "[" + ids(arg) + ", ";
		if (arg instanceof Integer)
			r += "'!Integer', " + arg.toString();
		else if (arg instanceof Float)
			r += "'!Float', " + arg.toString();
		else if (arg instanceof Boolean)
			r += "'!Boolean', " + arg.toString();
		else if (arg instanceof StringCatter)
			r += "'!String', \""+arg.toString().replace("\"", "\\\"")+"\"";
		else if (arg instanceof Terminal){
			Terminal t = (Terminal) arg;
			r += "'!Terminal', '"+t.getName()+"', \""+t.lexeme+"\", "+sexprifyObj(t.location);
		} else if (arg instanceof Node) {
			Node n = (Node) arg;
			r += "'" + n.getName() + "', [";
			for (int i=0; i<n.getNumberOfChildren(); i++){
				r += sexprifyObj(n.getChild(i));
				if (i!=n.getNumberOfChildren()-1) r+=", ";
			}
			r+="],[";

			String[] annotationNames = n.getAnnoNames();
			for (int i = 0; i < annotationNames.length; i++) {
				String name = annotationNames[i];
				Object value = n.getAnno(name);
				r += "('"+name+"', "+sexprifyObj(value)+")";
				if (i!=annotationNames.length-1) r+=",";
			}
			r += "],";
			r += sexprifyObj(n.origin);
		} else if (arg instanceof ConsCell){
			ConsCell cc = (ConsCell) arg;
			if (cc.nil()) {
				r += "'!List', []";
			}else{
				Object next;
				r += "'!List', [";
				while (!cc.nil()) {
					r += sexprifyObj(cc.head());
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
			r += "'???', \""+arg.toString()+"\"";
		}

		r += "]";

		return r;
	}

	public static StringCatter sexprify(final Object arg) {
		return new StringCatter(sexprifyObj(arg));
	}
}
