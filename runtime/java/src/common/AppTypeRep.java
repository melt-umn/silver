package common;

import java.util.*;

/**
 * Representation of an applied Silver type, used for run-time type checking in reification.  
 * 
 * @author krame505
 */
public class AppTypeRep extends TypeRep {
	/**
	 * The contstructor and argument types.
	 */
	public final TypeRep cons, arg;
	
	/**
	 * Create a AppTypeRep
	 * 
	 * @param cons The applied type.
	 * @param arg The argument type.
	 */
	public AppTypeRep(final TypeRep cons, final TypeRep arg) {
		this.cons = cons;
		this.arg = arg;
	}
	
	@Override
	protected final boolean unifyPartial(final TypeRep other) {
		return other instanceof AppTypeRep && unify(cons, ((AppTypeRep)other).cons) && unify(arg, ((AppTypeRep)other).arg);
	}
	
	@Override
	public final String typeName() {
		return cons.typeName();
	}
	
	@Override
	public final String toString() {
		List<TypeRep> args = new LinkedList<>();
		TypeRep a = this;
		for (; a instanceof AppTypeRep; a = ((AppTypeRep)a).cons) {
			args.add(0, ((AppTypeRep)a).arg);
		}
		Iterator<TypeRep> it = args.iterator();
		String result = "";
		if (a instanceof BaseTypeRep && ((BaseTypeRep)a).name.equals("[]")) {
			result = "[" + it.next() + "]";
		} else if (a instanceof FunctionTypeRep) {
			FunctionTypeRep fn = (FunctionTypeRep)a;
			String argsToString = "";
			if (fn.params > 0 && it.hasNext()) {
				argsToString += it.next();
			}
			int i;
			for (i = 1; i < fn.params && it.hasNext(); i++) {
				argsToString += " " + it.next();
			}
			for (; i < fn.params; i++) {
				argsToString += " _";
			}
			for (i = 0; i < fn.namedParams.length && it.hasNext(); i++) {
				argsToString += "; " + fn.namedParams[i] + "::" + it.next();
			}
			for (; i < fn.namedParams.length; i++) {
				argsToString += "; " + fn.namedParams[i] + "::_";
			}
			String resultToString = it.hasNext()? it.next().toString() : "_";
			result = "(" + resultToString + " ::= " + argsToString + ")";
		} else {
			result = a.toString();
		}
		if (it.hasNext()) {
			result += "<" + it.next();
			while (it.hasNext()) {
				result += " " + it.next();
			}
			result += ">";
		}
		return result;
	}
}
