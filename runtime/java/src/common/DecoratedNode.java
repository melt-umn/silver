package common;

import common.exceptions.MissingDefinitionException;
import common.exceptions.TraceException;

/**
 * This is the <strike>Stack</strike>Heap Frame and primary data structure of Silver.
 * 
 * <p>Here be dragons, unfortunately. Don't modify at all without running it past me.
 * 
 * @author tedinski
 * @see Node
 */
public class DecoratedNode {
	// TODO list:
	// - Delete parent / forwardParent. Or coalesce them (only NEED for debugging purposes, if inh become thunks!)
	// - Delete forwardValue (make it a local/production attribute)
	// - merge inheritedAttributes into inheritedValues

	/**
	 * The "undecorated" form of this DecoratedNode. (Never null)
	 * 
	 * @see #undecorate()
	 */
	protected final Node self;
	/**
	 * The node that forwards to this one. (May be null)
	 * 
	 * @see #inheritedForwarded(String)
	 */
	protected final DecoratedNode forwardParent;
	/**
	 * The DecoratedNode to use as a context for evaluating inherited attributes. (Never null except for TopNode)
	 * 
	 * <p> May be actual parent. Or production this is a local in. Or just a production that
	 * called 'decorate' to create this one.
	 * 
	 * @see TopNode
	 */
	protected final DecoratedNode parent;
	
	/**
	 * The cache of children (e.g. the DecoratedNodes)
	 * 
	 * @see #child(int)
	 */
	protected final Object[] childrenValues;
	/**
	 * The cache of the forward node.
	 * 
	 * @see #forward()
	 */
	protected DecoratedNode forwardValue;
	/**
	 * The cache of the inherited attribute supplied to this node.
	 * 
	 * @see #inherited(String)
	 * @see #inheritedAttributes
	 */
	protected final Object[] inheritedValues;
	/**
	 * The cache of the synthesized attributes on this node.
	 * 
	 * @see #synthesized(String)
	 * @see #local(String)
	 */
	protected final Object[] synthesizedValues;

	/**
	 * The inherited attributes supplied to this DecoratedNode, to be evaluated with context 'parent'.
	 * 
	 * @see #inherited(String)
	 * @see #inheritedValues
	 */
	protected final Lazy[] inheritedAttributes;

	/**
	 * A cache of the values of local attributes on this node. (incl. locals and prod)
	 */
	protected final Object[] localValues;

	
	/**
	 * Construct a decorated form of a Node. Called only via Node.
	 * 
	 * <p> 'self' and 'parent' are not null (except for TopNode)
	 * 
	 * <p> Only one or none of 'inhs' and 'forwardParent' should be supplied.
	 * 
	 * @param cc  Child count
	 * @param ic  Inherited attribute count
	 * @param sc  Synthesized attribute count
	 * @param lc  Local/prod attribute count
	 * @param self  The Node to decorate.
	 * @param parent  The DecoratedNode creating this one, to evaluate inhs in.
	 * @param inhs  The inherited attributes to decorate this node with.
	 * @param forwardParent  The node to request inherited attributes from instead of using 'inhs'.
	 * 
	 * @see Node#decorate(DecoratedNode, DecoratedNode)
	 * @see Node#decorate(DecoratedNode, Lazy[])
	 */
	DecoratedNode(
			final int cc, final int ic, final int sc, final int lc,
			final Node self, final DecoratedNode parent,
			final Lazy[] inhs, final DecoratedNode forwardParent) {
		this.self = self;
		this.parent = parent;
		this.inheritedAttributes = inhs;
		this.forwardParent = forwardParent;
		
		// create caches
		this.childrenValues =    (cc > 0) ? new Object[cc] : null;
		this.inheritedValues =   (ic > 0) ? new Object[ic] : null;
		this.synthesizedValues = (sc > 0) ? new Object[sc] : null;
		this.localValues =       (lc > 0) ? new Object[lc] : null;
		
		// STATS: Uncomment to enable statistics
		//Statistics.dnSpawn(self!=null?self.getClass():TopNode.class);
	}
	
	// STATS: Uncomment to enable statistics
	//@Override
	//protected void finalize() throws Throwable {
	//	Statistics.dnFinal(self!=null?self.getClass():TopNode.class);
	//}

	/**
	 * @return The {@link Node} this decorates.
	 */
	public final Node undecorate(){
		return self;
	}

	
	// TODO: The AsIs/Decorated distinction could go away and we could go for storing
	// information in Node on whether it's decorable.
	
	
	/**
	 * Returns the child of this DecoratedNode, without potentially decorating it.
	 * 
	 * <p>Warning: While it is technically safe to mix calls to {@link #childAsIs} and {@link #childDecorated}
	 * this behavior should not be relied upon, as it may change later.
	 * 
	 * @param child The number of the child to obtain.
	 * @return The unmodified value of the child.
	 */
	public Object childAsIs(final int child){
		return self.getChild(child);
	}
	
	/**
	 * Returns the child of this DecoratedNode, decorating it with whatever inherited attributes
	 * this production has for it.
	 * 
	 * <p>Warning: While it is technically safe to mix calls to {@link #childAsIs} and {@link #childDecorated}
	 * this behavior should not be relied upon, as it may change later.
	 * 
	 * @param child The number of the child to obtain.
	 * @return The decorated value of the child.
	 */
	public DecoratedNode childDecorated(final int child){
		Object o = this.childrenValues[child]; 
		if(o == null) {
			o = self.getChild(child);
			// Thunk evaluation is now handled in Node, rather than here.
			if (o instanceof Node) {
				o = ((Node)o).decorate(this, self.getChildInheritedAttributes(child));
			}
			
			// CACHE : probably should not comment out child caching?
			this.childrenValues[child] = o;
		}
		return (DecoratedNode)o;
	}
	
	/**
	 * Get the value of a local, caching it for re-use.
	 * 
	 * <p>Warning: do not mix {@link #localAsIs} and {@link #localDecorated} on the same local attribute!
	 * 
	 * @param attribute The full name of the local to obtain.
	 * @return The value of the local.
	 */
	public Object localAsIs(final int attribute) {
		Object o = this.localValues[attribute];
		if(o == null) {
			Lazy l = self.getLocal(attribute);
			if(l == null) {
				throw new MissingDefinitionException("Local attribute '" + self.getNameOfLocalAttr(attribute) + "' is not defined in production '" + self.getName() + "'");
			}
			try {
				o = l.eval(this);
			} catch(Throwable t){
				throw new TraceException("Error while evaluating local attribute '" + self.getNameOfLocalAttr(attribute) + "' in production '" + self.getName() + "'", t);
			}
			
			// CACHE : comment out to disable caching for local attributes
			// not recommended due to IO objects (IOString, etc)
			this.localValues[attribute] = o;
		}
		return o;
	}

	/**
	 * Get the value of a local, caching it for re-use.
	 * 
	 * <p>Warning: do not mix {@link #localAsIs} and {@link #localDecorated} on the same local attribute!
	 * 
	 * @param attribute The full name of the local to obtain.
	 * @return The value of the local.
	 */
	public DecoratedNode localDecorated(final int attribute) {
		Object o = this.localValues[attribute];
		if(o == null) {
			Lazy l = self.getLocal(attribute);
			if(l == null) {
				throw new MissingDefinitionException("Local attribute '" + self.getNameOfLocalAttr(attribute) + "' is not defined in production '" + self.getName() + "'");
			}
			try {
				o = l.eval(this);
				o = ((Node)o).decorate(this, self.getLocalInheritedAttributes(attribute));
			} catch(Throwable t){
				throw new TraceException("Error while evaluating local attribute '" + self.getNameOfLocalAttr(attribute) + "' in production '" + self.getName() + "'", t);
			}
			
			// CACHE : comment out to disable caching for local attributes
			// not recommended due to IO objects (IOString, etc)
			this.localValues[attribute] = o;
		}
		return (DecoratedNode)o;
	}

	/**
	 * Obtain a synthesized attribute from this DecoratedNode. First, look for definitions on this node,
	 * and if that fails, request it from whatever we forward to, if anything.
	 * 
	 * @param attribute The full name of the attribute.
	 * @return The value of the attribute.
	 */
	public Object synthesized(final int attribute) {
		//System.err.println("TRACE: " + name + " demanding syn attribute: " + attribute);
		
		Object o = this.synthesizedValues[attribute];
		if(o == null) {
			Lazy l = self.getSynthesized(attribute);
			if(l != null) {
				try {
					o = l.eval(this);
				} catch(Throwable t) {
					throw new TraceException("Error while evaluating synthesized attribute '" + self.getNameOfSynAttr(attribute) + "' in production '" + self.getName() + "'", t);
				}
			} else if(forward() != null) {
				try {
					o = forward().synthesized(attribute);
				} catch(Throwable t) {
					throw new TraceException("Error attempting to fetch from forward in production " + self.getName() + ".", t);
				}
			} else {
				l = self.getDefaultSynthesized(attribute);
				if(l != null) {
					try {
						o = l.eval(this);
					} catch(Throwable t) {
						throw new TraceException("Error evaluating default attribute '" + self.getNameOfSynAttr(attribute) + "' in production '" + self.getName() + "'", t);
					}
				} else {
					throw new MissingDefinitionException("Synthesized attribute '" + self.getNameOfSynAttr(attribute) + "' is not defined in production '" + self.getName() + "'");	
				}
			}
			
			// CACHE : comment out to disable caching for synthesized attributes
			this.synthesizedValues[attribute] = o;
		}
		return o;
	}
	
	/**
	 * Get the forwarded-to DecoratedNode.  Cached.  There is no need for an "AsIs" vs "Decorated"
	 * variant of this function, since we always know it's a Node.
	 * 
	 * @return The DecoratedNode this one forwards to, or null if this node does not forward.
	 */
	public DecoratedNode forward() {
		if(this.forwardValue == null) {
			try {
				final Node n = self.getForward(this);
				if(n == null)
					return null;
				// CACHE : should not comment out forward caching !
				this.forwardValue = n.decorate(parent, this);
			} catch(Throwable t) {
				throw new TraceException("Error evaluating forward node in production '" + self.getName() + "'", t);
			}
		}
		return this.forwardValue;
	}

	/**
	 * Get the value of an inherited attribute on this DN.  First, try the inherited attributes we were given.
	 * If that fails, ask our {@link #forwardParent}, if any, for whatever they may give us.
	 * 
	 * @param attribute The full name of the attribute.
	 * @return The value of the attribute.
	 * 
	 * @see #inheritedForwarded(String)
	 */
	public Object inherited(final int attribute) {
		//System.err.println("TRACE: " + name + " demanding inh attribute: " + attribute);
		
		Object o = this.inheritedValues[attribute];
		if(o == null) {
			Lazy l;
			// Note: inheritedAttributes is validly null here!  (forward nodes have no inherited attributes)
			if(this.inheritedAttributes == null || (l = this.inheritedAttributes[attribute]) == null) {
				if(forwardParent != null) {
					try {
						o = forwardParent.inheritedForwarded(attribute);
					} catch(Throwable t) {
						throw new TraceException("Inherited attribute '" + self.getNameOfInhAttr(attribute) + "' demanded by forward production'" + self.getName() +"'.", t);
					}
				} else {
					throw new MissingDefinitionException("Inherited attribute '" + self.getNameOfInhAttr(attribute) + "' not provided to '" + self.getName() + "' by '" + parent.self.getName() + "'" + (forwardParent==null?"":" (forwardParent: '" + forwardParent.self.getName() + "')"));
				}
			} else {
				try {
					o = l.eval(this.parent);
				} catch(Throwable t) {
					throw new TraceException("Error evaluating inherited attribute '" + self.getNameOfInhAttr(attribute) + "' in production '" + self.getName() + "'", t);
				}
			}
			
			// CACHE : comment out to disable caching of inherited attributes
			// not recommended because it leads to a combinatoric explosion for environments
			// due to thunks keeping around references to previously computed environments that
			// we're recomputing
			this.inheritedValues[attribute] = o;
		}
		return o;
	}
	
	/**
	 * Only called by {@link #inherited(String)}, when it doesn't have an inherited attribute,
	 * and it wants to request that value from this DecorateNode (its {@link #forwardParent}).
	 * 
	 * @param attribute The full name of the attribute.
	 * @return The value of the attribute.
	 */
	protected Object inheritedForwarded(final int attribute) {
		//System.err.println("TRACE: " + name + " demanding FORWARDED inh attribute: " + attribute);
		
		// No cache look up here. There is only one forward production. It will call this method
		// a maximum of once for each attribute, since it will cache the result.
		
		Lazy l = self.getForwardInheritedAttributes(attribute);
		if(l == null) {
			return inherited(attribute);
		}
		try {
			// No need for caching here, it'll be cached by the inherited() that called us
			return l.eval(this);
		} catch(Throwable t) {
			throw new TraceException("Error evaluating inherited attribute '" + self.getNameOfInhAttr(attribute) + "' for forward production in production '" + self.getName() + "'", t);
		}
	}

	public final Object childDecoratedLazy(final int child) {
		if(childrenValues[child] != null)
			return childrenValues[child];
		return new Thunk<Object>(this) {
			@Override
			public final Object doEval() {
				return context.childDecorated(child);
			}
		};
	}
	public final Object childAsIsLazy(final int child) {
		return new Thunk<Object>(this) {
			@Override
			public final Object doEval() {
				return context.childAsIs(child);
			}
		};
	}
	public final Object localDecoratedLazy(final int index) {
		if(localValues[index] != null)
			return localValues[index];
		return new Thunk<Object>(this) {
			@Override
			public final Object doEval() {
				return context.localDecorated(index);
			}
		};
	}
	public final Object localAsIsLazy(final int index) {
		return new Thunk<Object>(this) {
			@Override
			public final Object doEval() {
				return context.localAsIs(index);
			}
		};
	}
	public final Object childSynthesizedLazy(final int child, final int index) {
		if( childrenValues[child] != null && 
			((DecoratedNode)childrenValues[child]).synthesizedValues[index] != null)
		{
			return ((DecoratedNode)childrenValues[child]).synthesizedValues[index];
		}
		return new Thunk<Object>(this) {
			@Override
			public final Object doEval() {
				return context.childDecorated(child).synthesized(index);
			}
		};
	}
	public final Object contextInheritedLazy(final int index) {
		if( inheritedValues[index] != null)
			return inheritedValues[index];
		return new Thunk<Object>(this) {
			@Override
			public final Object doEval() {
				return context.inherited(index);
			}
		};
	}
}
