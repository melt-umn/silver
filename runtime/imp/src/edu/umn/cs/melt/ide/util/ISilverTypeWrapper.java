package edu.umn.cs.melt.ide.util;

/**
 * Class implementing this interface should wrap class properly 
 * in Silver's core type.
 * <p>
 * The purpose of introducing this is entirely due to technical restriction.
 * We cannot refer to any Silver core classes from IDE runtime, and has to 
 * delegate any behavior involving such access to the IDE plug-in, to which 
 * these classes are visible.
 * <p>
 * The argument with name object and type <code>Object</code> passed along to 
 * any method defined in this interface can be anything of
 * <pre>
 *   {@link common.Node} or its subclass, 
 *   {@link common.DecoratedNode}, 
 *   {@link common.ConsCell}, 
 *   {@link common.StringCatter},
 *   {@link java.lang.Integer}, 
 *   {@link java.lang.Boolean}</pre>
 */
public interface ISilverTypeWrapper {

//	/**
//	 * Wrap the argument into an IOVal&lt;Maybe&lt;X&gt;&gt;
//	 * <p>
//	 * When a returned type is defined as such, it by convention means that
//	 * if a <code>nothing</code> is returned, an error has occurred. 
//	 * @param io IO token
//	 * @param obj the object to be wrapped into <code>Maybe</code>
//	 * @return IOVal&lt;Maybe&lt;X&gt;&gt;
//	 */
//	Object wrapInIOValMaybe(Object io, Object obj);

	/**
	 * Wrap the argument into an Maybe&lt;X&gt;
	 * <p>
	 * When a returned type is defined as such, it by convention means that
	 * if a <code>nothing</code> is returned, an error has occurred. 
	 * 
	 * @param obj the object to be wrapped into <code>Maybe</code>
	 * @return IOVal&lt;Maybe&lt;X&gt;&gt;
	 */
	Object wrapInMaybe(Object obj);
	
	/**
	 * Wrap the argument into an IOVal&lt;X&gt;
	 * 
	 * @param io IO token
	 * @param obj the object to be wrapped into <code>IOVal</code>
	 * @return IOVal&lt;X&gt;
	 */
	Object wrapInIOVal(Object io, Object obj);
	
}
