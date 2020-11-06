package common;

import java.lang.reflect.*;
import java.util.*;

import common.exceptions.*;
import core.*;
import core.reflect.*;

/**
 * Implementation of the Silver reflection library
 * 
 * @author krame505
 */
public final class Reflection {
	/**
	 * Extract a runtime type representation of any object.
	 * 
	 * @param o The object to extract the type. 
	 * @return The type of the object.
	 */
	public static TypeRep getType(final Object o) {
		if(o instanceof Integer) {
			return new BaseTypeRep("Integer");
		} else if(o instanceof Float) {
			return new BaseTypeRep("Float");
		} else if(o instanceof Boolean) {
			return new BaseTypeRep("Boolean");
		} else if(o instanceof Typed){
			return ((Typed)o).getType();
		} else if(o instanceof Thunk) {
			throw new SilverInternalError("Runtime type of an unevaluated Thunk should never be demanded.");
		} else {
			// Not an internal error, since foreign types not implementing Typed will trigger this
			throw new SilverError("Runtime type checking of object requires class " + o.getClass().getName() + " to implement Typed.");
		}
	}
	
	/**
	 * Create a Silver Maybe<String> object containing an extracted string representation of the
	 * runtime type of an object, if available.
	 * 
	 * @param o The object to extract the type. 
	 * @return just the extracted type representation, if the object is typed, or else nothing().
	 */
	public static NMaybe reflectTypeName(final Object o) {
		String result;
		if(o instanceof Integer) {
			result = "Integer";
		} else if(o instanceof Float) {
			result = "Float";
		} else if(o instanceof Boolean) {
			result = "Boolean";
		} else if(o instanceof Typed){
			result = ((Typed)o).getType().toString();
		} else if(o instanceof Thunk) {
			throw new SilverInternalError("Runtime type of an unevaluated Thunk should never be demanded.");
		} else {
			return new Pnothing();
		}
		return new Pjust(new StringCatter(result));
	}
	
	/**
	 * Implementation of the reflect operation for an arbitrary type.
	 * 
	 * @param o The object to reflect.
	 * @return The reflected AST.
	 */
	public static NAST reflect(final Object o) {
		if(o instanceof Node) {
			Node n = (Node)o;
			NASTs children = new PnilAST();
			for (int i = n.getNumberOfChildren() - 1; i >= 0; i--) {
				Object value = reflect(n.getChild(i));
				children = new PconsAST(value, children);
			}
			String[] annotationNames = n.getAnnoNames();
			NNamedASTs annotations = new PnilNamedAST();
			for (int i = annotationNames.length - 1; i >= 0; i--) {
				String name = annotationNames[i];
				Object value = reflect(n.getAnno(name));
				annotations = new PconsNamedAST(new PnamedAST(new StringCatter(name), value), annotations);
			}
			return new PnonterminalAST(new StringCatter(n.getName()), children, annotations);
		} else if(o instanceof Terminal) {
			Terminal t = (Terminal)o;
			return new PterminalAST(new StringCatter(t.getName()), t.lexeme, t.location);
		} else if(o instanceof ConsCell) {
			return new PlistAST(reflectList((ConsCell)o));
		} else if(o instanceof StringCatter) {
			return new PstringAST((StringCatter)o);
		} else if(o instanceof Integer) {
			return new PintegerAST((Integer)o);
		} else if(o instanceof Float) {
			return new PfloatAST((Float)o);
		} else if(o instanceof Boolean) {
			return new PbooleanAST((Boolean)o);
		} else {
			return new PanyAST(o);
		}
	}
	private static NASTs reflectList(final ConsCell l) {
		if (!l.nil()) {
			return new PconsAST(reflect(l.head()), reflectList(l.tail()));
		} else {
			return new PnilAST();
		}
	}
	
	/**
	 * Implementation of reification with error checking.
	 * 
	 * @param resultType The type of tree to be constructed.
	 * @param ast The AST to reify.
	 * @return An Either<String a> object containing either an error message or a constructed object. 
	 */
	public static NEither reifyChecked(final TypeRep resultType, final NAST ast) {
		try {
			return new Pright(reify(resultType, ast));
		} catch (SilverException e) {
			Throwable rootCause = SilverException.getRootCause(e);
			if (rootCause instanceof SilverError) {
				return new Pleft(new StringCatter("Reification error at " + ReifyTraceException.getASTRepr(e) + ":\n" + rootCause.getMessage()));
			} else {
				throw e;
			}
		}
	}
	
	/**
	 * Implementation of the reify operation for an arbitrary type.
	 * 
	 * @param resultType The type of tree to be constructed.
	 * @param ast The AST to reify.
	 * @return The constructed object.
	 */
	public static Object reify(final TypeRep resultType, final NAST ast) {
		if (ast instanceof PnonterminalAST) {
			// Unpack production name
			final String prodName = ((StringCatter)ast.getChild(0)).toString();
			
			// Unpack children
			final List<NAST> childASTList = new ArrayList<>(5);
			for (NASTs current = (NASTs)ast.getChild(1); !(current instanceof PnilAST); current = (NASTs)current.getChild(1)) {
				childASTList.add((NAST)current.getChild(0));
			}
			final NAST[] childASTs = childASTList.toArray(new NAST[childASTList.size()]);
			
			// Unpack annotations
			class AnnotationEntry implements Comparable<AnnotationEntry> {
			    public final String name;
			    public final NAST ast;

			    public AnnotationEntry(String name, NAST ast) {
			        this.name = name;
			        this.ast = ast;
			    }

			    public int compareTo(AnnotationEntry other) {
			        return name.compareTo(other.name);
			    }
			}
			final List<AnnotationEntry> annotationASTList = new ArrayList<>();
			for (NNamedASTs current = (NNamedASTs)ast.getChild(2); !(current instanceof PnilNamedAST); current = (NNamedASTs)current.getChild(1)) {
				NNamedAST item = (NNamedAST)current.getChild(0);
				annotationASTList.add(new AnnotationEntry(item.getChild(0).toString(), (NAST)item.getChild(1)));
			}
			Collections.sort(annotationASTList);
			final String[] annotationNames = new String[annotationASTList.size()];
			final NAST[] annotationASTs = new NAST[annotationASTList.size()];
			for (int i = 0; i < annotationASTList.size(); i++) {
				annotationNames[i] = annotationASTList.get(i).name;
				annotationASTs[i] = annotationASTList.get(i).ast;
			}
			
			// Invoke the reify function for the appropriate production class
			final String[] path = prodName.split(":");
			path[path.length - 1] = "P" + path[path.length - 1];
			final String className = String.join(".", path);
			try {
				Method prodReify =
						((Class<Node>)Class.forName(className)).getMethod("reify", TypeRep.class, NAST[].class, String[].class, NAST[].class);
				return prodReify.invoke(null, resultType, childASTs, annotationNames, annotationASTs);
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
		} else if (ast instanceof PterminalAST) {
			// Unpack components
			final String terminalName = ((StringCatter)ast.getChild(0)).toString();
			final StringCatter lexeme = (StringCatter)ast.getChild(1);
			final NLocation location = (NLocation)ast.getChild(2);
			
			// Perform unification with the expected type
			if (!TypeRep.unify(resultType, new BaseTypeRep(terminalName))) {
				throw new SilverError("reify is constructing " + resultType.toString() + ", but found terminal " + terminalName + " AST.");
			}
			
			// Invoke the reify function for the appropriate terminal class
			final String[] path = terminalName.split(":");
			path[path.length - 1] = "T" + path[path.length - 1];
			final String className = String.join(".", path);
			try {
				Constructor<Terminal> terminalConstructor =
						((Class<Terminal>)Class.forName(className)).getConstructor(StringCatter.class, NLocation.class);
				return terminalConstructor.newInstance(lexeme, location);
			} catch (ClassNotFoundException e) {
				throw new SilverError("Undefined terminal " + terminalName);
			} catch (NoSuchMethodException | InstantiationException | IllegalAccessException e) {
				throw new SilverInternalError("Error constructing class " + className, e);
			} catch (InvocationTargetException e) {
				if (e.getTargetException() instanceof SilverException) {
					throw (SilverException)e.getTargetException();
				} else {
					throw new SilverInternalError("Error constructing class " + className, e.getTargetException());
				}
			}
		} else if (ast instanceof PlistAST) {
			final TypeRep paramType = new VarTypeRep();
			if (!TypeRep.unify(resultType, new ListTypeRep(paramType))) {
				throw new SilverError("reify is constructing " + resultType.toString() + ", but found list AST.");
			}
			return reifyList(paramType, (NASTs)ast.getChild(0));
		} else {
			Object givenObject = ast.getChild(0);
			
			// Construct the TypeRep correpsonding to the given object
			TypeRep givenType;
			if (ast instanceof PstringAST) {
				givenType = new BaseTypeRep("String");
			} else if (ast instanceof PintegerAST) {
				givenType = new BaseTypeRep("Integer");
			} else if (ast instanceof PfloatAST) {
				givenType = new BaseTypeRep("Float");
			} else if (ast instanceof PbooleanAST) {
				givenType = new BaseTypeRep("Boolean");
			} else if (ast instanceof PanyAST) {
				givenType = getType(givenObject);
			} else {
				throw new SilverInternalError("Unexpected AST production " + ast.getName());
			}
			// Perform unification with the expected type
			if (!TypeRep.unify(resultType, givenType)) {
				throw new SilverError("reify is constructing " + resultType.toString() + ", but found " + givenType.toString() + " AST.");
			}
			return givenObject;
		}
	}
	// Recursive helper to walk the ASTs tree and build a list
	private static ConsCell reifyList(final TypeRep resultParamType, final NASTs asts) {
		if (asts instanceof PconsAST) {
			Object head;
			try {
				head = reify(resultParamType, (NAST)asts.getChild(0));
			} catch (SilverException e) {
				throw new ConsReifyTraceException(true, e);
			}
			ConsCell tail;
			try {
				tail = reifyList(resultParamType, (NASTs)asts.getChild(1));
			} catch (SilverException e) {
				throw new ConsReifyTraceException(false, e);
			}
			return new ConsCell(head, tail);
		} else if (asts instanceof PnilAST) {
			return ConsCell.nil;
		} else {
			throw new SilverInternalError("Unexpected ASTs production " + asts.getName());
		}
	}
	
	/**
	 * Apply a function wrapped in AST to AST arguments.
	 * 
	 * @param fn An AnyAST containing the argument.
	 * @param args A list of Maybe<AST> arguments or holes for partial application.
	 * @param namedArgs A list of Pair<String Maybe<AST>> named arguments or holes for partial application.
	 * @return An Either<String a> object containing either an error message or the reflected result of calling the function.
	 */
	public static NEither applyAST(final NAST fn, final ConsCell args, final ConsCell namedArgs) {
		// Unpack function
		if (!(fn instanceof PanyAST) || !(fn.getChild(0) instanceof NodeFactory)) {
			return new Pleft(new StringCatter("Expected a function AST"));
		}
		NodeFactory<?> givenFn = (NodeFactory<?>)(fn.getChild(0));
		FunctionTypeRep fnType = givenFn.getType();
		
		// Unpack args
		final List<Integer> argIndexList = new ArrayList<>(5);
		final List<Object> argList = new ArrayList<>(5);
		int i = 0;
		for (ConsCell current = args; !current.nil(); current = current.tail()) {
			if (i >= fnType.params.length) {
				return new Pleft(new StringCatter("Expected only " + fnType.params.length + " arguments, but got " + args.length()));
			}
			final NMaybe item = (NMaybe)current.head();
			if (item instanceof Pjust) {
				argIndexList.add(i);
				try {
					argList.add(reify(fnType.params[i], (NAST)item.getChild(0)));
				} catch (SilverException e) {
					Throwable rootCause = SilverException.getRootCause(e);
					if (rootCause instanceof SilverError) {
						return new Pleft(new StringCatter("Reification error in argument " + i + " at " + ReifyTraceException.getASTRepr(e) + ":\n" + rootCause.getMessage()));
					} else {
						throw e;
					}
				}
			}
			i++;
		}
		if (i < fnType.params.length) {
			return new Pleft(new StringCatter("Expected " + fnType.params.length + " arguments, but got only " + i));
		}
		
		// Unpack named args
		final List<Integer> convertedIndexList = new ArrayList<>();
		final List<Integer> suppliedIndexList = new ArrayList<>();
		final List<Object> namedArgList = new ArrayList<>();
		final Object[] reorderedNamedArgs = new Object[fnType.namedParamNames.length];
		for (ConsCell current = namedArgs; !current.nil(); current = current.tail()) {
			final NPair entry = (NPair)current.head();
			final String name = entry.getChild(0).toString();
			int index = Arrays.asList(fnType.namedParamNames).indexOf(name);
			if (index == -1) {
				return new Pleft(new StringCatter("Unexpected named argument " + name));
			}
			final NMaybe item = (NMaybe)entry.getChild(1);
			if (item instanceof Pjust) {
				Object o;
				try {
					o = reify(fnType.namedParamTypes[index], (NAST)item.getChild(0));
				} catch (SilverException e) {
					Throwable rootCause = SilverException.getRootCause(e);
					if (rootCause instanceof SilverError) {
						return new Pleft(new StringCatter("Reification error in named argument " + name + " at " + ReifyTraceException.getASTRepr(e) + ":\n" + rootCause.getMessage()));
					} else {
						throw e;
					}
				}
				suppliedIndexList.add(index);
				namedArgList.add(o);
				reorderedNamedArgs[index] = o;
			} else {
				convertedIndexList.add(index);
			}
			i++;
		}
		
		Object result;
		if (argList.size() < fnType.params.length || namedArgList.size() < fnType.namedParamNames.length) {
			// Apply partial
			result = givenFn
					.invokePartial(argIndexList.stream().mapToInt(n -> n).toArray(), argList.toArray())
					.invokeNamedPartial(
							convertedIndexList.stream().mapToInt(n -> n).toArray(),
							suppliedIndexList.stream().mapToInt(n -> n).toArray(),
							namedArgList.toArray());
		} else {
			// Apply regular
			result = givenFn.invoke(argList.toArray(), reorderedNamedArgs);
		}
		return new Pright(reflect(result));
	}
}
