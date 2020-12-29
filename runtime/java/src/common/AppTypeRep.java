package common;

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
	public final String toString() {
		if (arg instanceof BaseTypeRep && ((BaseTypeRep)cons).name.equals("List")) {
			return "[" + arg + "]";
		} else {
			String argsToString = arg.toString();
			for (TypeRep a = arg; a instanceof AppTypeRep; a = ((AppTypeRep)a).cons) {
				argsToString += " " + a.toString();
			}
			return cons + "<" + argsToString + ">";
		}
	}
}
