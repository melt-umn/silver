package common;


import core.*;

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

		String r = "(" + ids(arg) + " ";
		if (arg instanceof Integer)
			r += "!Integer " + arg.toString();
		else if (arg instanceof Float)
			r += "!Float " + arg.toString();
		else if (arg instanceof Boolean)
			r += "!Boolean " + arg.toString();
		else if (arg instanceof StringCatter)
			r += "!String \""+arg.toString()+"\"";
		else if (arg instanceof Terminal){
			Terminal t = (Terminal) arg;
			r += "!Terminal "+t.getName()+" \""+t.lexeme+"\" "+t.location;
		} else if (arg instanceof Node) {
			Node n = (Node) arg;
			r += n.getName() + " (";
			for (int i=0; i<n.getNumberOfChildren(); i++){
				r += sexprifyObj(n.getChild(i)) + " ";
			}
			r+=")(";

			String[] annotationNames = n.getAnnoNames();
			for (int i = 0; i < annotationNames.length; i++) {
				String name = annotationNames[i];
				Object value = n.getAnno(name);
				r += "("+name+" "+sexprifyObj(value)+")";
			}
			r += ")";
		} else {
			r += "??? \""+arg.toString()+"\"";
		}

		r += ")";

		return r;
	}

	public static StringCatter sexprify(final Object arg) {
		return new StringCatter(sexprifyObj(arg));
	}
}
