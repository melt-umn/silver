package common;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

import common.exceptions.*;

import core.reflect.*;

/**
 * ReifyFn implements the value of the <code>reify</code> expression.  
 * 
 * @param <T>  The return type of the function
 * @author krame505
 */
public abstract class ReifyFn<T> extends NodeFactory<T> {
	protected final TypeRep resultType;
	
	/**
	 * Create a ReifyFn.
	 * 
	 * @param resultType The type of tree to be constructed.
	 */
	public ReifyFn(TypeRep resultType) {
		this.resultType = resultType;
	}
	
	/**
	 * Invoke the reify operation.
	 * 
	 * @return The tree constructed.
	 */
	public final T invoke(final Object[] args, final Object[] namedArgs) {
		assert args.length == 1;
		assert namedArgs.length == 0;
		return (T)reify(resultType, (NAST)args[0]);
	}
	
	/**
	 * Implementation of the reify operation for an arbitrary type.
	 * 
	 * @param resultType The type of tree to be constructed.
	 * @param ast The tree to reify. 
	 */
	public static Object reify(final TypeRep resultType, final NAST ast) {
		if (ast.getName().equals("core:reflect:nonterminalAST")) {
			if (!(resultType instanceof BaseTypeRep)) {
				throw new SilverError("reify is constructing " + resultType.toString() + ", but found nonterminal AST.");
			}
			
			final String prodName = ((StringCatter)ast.getChild(0)).toString();
			final List<NAST> childASTs = new ArrayList<>(5);
			for (NASTs current = (NASTs)ast.getChild(1); !current.getName().equals("core:reflect:nilAST"); current = (NASTs)current.getChild(1)) {
				childASTs.add((NAST)current.getChild(0));
			}
			final Map<String, NAST> annotationASTs = new TreeMap<>();
			for (NNamedASTs current = (NNamedASTs)ast.getChild(2); !current.getName().equals("core:reflect:nilNamedAST"); current = (NNamedASTs)current.getChild(1)) {
				NNamedAST item = (NNamedAST)current.getChild(0);
				annotationASTs.put(item.getChild(0).toString(), (NAST)item.getChild(1));
			}

			// Invoke the reify function for the appropriate nonterminal class
			final String[] path = prodName.split(":");
			path[path.length - 1] = "P" + path[path.length - 1];
			final String className = String.join(".", path);
			try {
				return Class.forName(className).getMethod("reify", TypeRep.class, NAST.class).invoke((BaseTypeRep)resultType, childASTs, annotationASTs);
			} catch (ClassNotFoundException e) {
				throw new SilverError("Undefined production " + prodName);
			} catch (NoSuchMethodException | IllegalAccessException e) {
				throw new SilverInternalError("Error invoking reify for class " + className, e);
			} catch (InvocationTargetException e) {
				if (e.getTargetException() instanceof SilverException) {
					throw (SilverException)e.getTargetException();
				} else {
					throw new SilverInternalError("Error invoking reify for class " + className, e.getTargetException());
				}
			}
		} else if (ast.getName().equals("core:reflect:listAST")) {
			final TypeRep paramType = new VarTypeRep();
			if (!resultType.unify(new ListTypeRep(paramType), true)) {
				throw new SilverError("reify is constructing " + resultType.toString() + ", but found list AST.");
			}
			return reifyList(paramType, (NASTs)ast.getChild(0));
		} else {
			// Construct the TypeRep correpsonding to the given object
			TypeRep givenType;
			if (ast.getName().equals("core:reflect:stringAST")) {
				givenType = new BaseTypeRep("String");
			} else if (ast.getName().equals("core:reflect:integerAST")) {
				givenType = new BaseTypeRep("Integer");
			} else if (ast.getName().equals("core:reflect:floatAST")) {
				givenType = new BaseTypeRep("Float");
			} else if (ast.getName().equals("core:reflect:booleanAST")) {
				givenType = new BaseTypeRep("Boolean");
			} else if (ast.getName().equals("core:reflect:foreignAST")) {
				givenType = null; // TODO
			} else {
				throw new SilverInternalError("Unexpected AST production " + ast.getName());
			}
			// Perform unification with type vars as skolems
			if (!resultType.unify(givenType, false)) {
				throw new SilverError("reify is constructing " + resultType.toString() + ", but found " + givenType.toString() + " AST.");
			}
			return ast.getChild(0);
		}
	}
	// Recursive helper to walk the ASTs tree and build a list
	private static ConsCell reifyList(final TypeRep resultParamType, final NASTs asts) {
		if (asts.getName().equals("core:reflect:consAST")) {
			return new ConsCell(reify(resultParamType, (NAST)asts.getChild(0)), reifyList(resultParamType, (NASTs)asts.getChild(1)));
		} else if (asts.getName().equals("core:reflect:nilAST")) {
			return ConsCell.nil;
		} else {
			throw new SilverInternalError("Unexpected ASTs production" + asts.getName());
		}
	}
}
