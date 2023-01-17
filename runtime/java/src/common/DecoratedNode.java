package common;

import common.exceptions.MissingDefinitionException;
import common.exceptions.SilverException;
import common.exceptions.TraceException;

/**
 * This is the <strike>Stack</strike>Heap Frame and primary data structure of Silver.
 * 
 * <p>Here be dragons, unfortunately. Don't modify at all without running it past me.
 * 
 * @author tedinski
 * @see Node
 */
public class DecoratedNode implements Decorable, Typed {
	// TODO list:
	// - Delete parent / forwardParent. Or coalesce them (only NEED for debugging purposes, if inh become thunks!)
	// - Delete forwardValue (make it a local/production attribute)
	// - merge inheritedAttributes into inheritedValues
	
	// Please note: the methods in this file have been refined to be quite small
	// because the JVM makes inlining decisions on a per-method basis (of course!)
	// So we try to keep the "slow paths" in a separate method, so the hot paths
	// can be inlined. (Things are designed around 37 bytes of bytecode as the limit.)

	// Error reporting note: we don't attempt to identify and report errors for
	// mis-generated code.  e.g. asking for synthesized attributes that
	// don't exist (syn#15 when there's only 10)
	// We only try to nicely report errors that are the fault of the user writing
	// broken code. (e.g. "no equation for syn")

	public OriginContext originCtx; //OriginContext of the "invocation" this is
	
	/**
	 * The "undecorated" form of this DecoratedNode. (Never null)
	 * 
	 * @see #undecorate()
	 */
	protected final Node self;
	/**
	 * The node that forwards to this one. (May be null)
	 * Not final, because we may set this when forwarding to a partially-decorated reference.
	 * 
	 * @see #inheritedForwarded(String)
	 */
	protected DecoratedNode forwardParent;
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
	 * Not final, because we make a copy of the array if this DecoratedNode is extra-decorated.
	 * 
	 * @see #inherited(String)
	 * @see #inheritedValues
	 */
	protected Lazy[] inheritedAttributes;

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
		this.originCtx = parent!=null?parent.originCtx:null;
		this.inheritedAttributes = inhs;
		this.forwardParent = forwardParent;
		
		// create caches
		this.childrenValues =    (cc > 0) ? new Object[cc] : null;
		this.inheritedValues =   (ic > 0) ? new Object[ic] : null;
		this.synthesizedValues = (sc > 0) ? new Object[sc] : null;
		this.localValues =       (lc > 0) ? new Object[lc] : null;
		
		// STATS: Uncomment to enable statistics
		//Statistics.dnSpawn(self!=null?self.getClass():TopNode.class);
		// System.err.println("TRACE: " + (parent == null? "null" : parent.getDebugID()) + " creating decorated " + getDebugID());
	}
	/**
	 * Initialize a DecorateNode created by a FunctionNode.
	 * 
	 * @param cc number of children
	 * @param lc number of locals
	 * @param self The FunctionNode
	 * @see {@link FunctionNode#decorate()}
	 */
	DecoratedNode(final int cc, final int lc, final FunctionNode self) {
		// TODO: I added this constructor largely because I wanted to
		// see if I could make all this fit under the 37 byte inline limit.
		// This doesn't. :(  Leaving it, because it certainly doesn't hurt.
		this.self = self;
		this.parent = TopNode.singleton;
		this.inheritedAttributes = null;
		this.forwardParent = null;
		
		this.childrenValues = new Object[cc];
		this.inheritedValues = null;
		this.synthesizedValues = null;
		this.localValues = (lc > 0) ? new Object[lc] : null;
	}
	
	// STATS: Uncomment to enable statistics
	//@Override
	//protected void finalize() throws Throwable {
	//	Statistics.dnFinal(self!=null?self.getClass():TopNode.class);
	//}

	/**
	 * @return Turn this DecoratedNode back into an undecorated {@link Node}.
	 */
	public final Node undecorate() {
		if (self.isUnique) {
			// System.err.println("TRACE: undecorating " + getDebugID());
			return self.undecorate(this);
		} else {
			return self;
		}
	}

	/**
	 * Decorate this (partially decorated) node with additional inherited attributes.
	 * 
	 * @param parent The DecoratedNode extra-decorating this one. (Whether this is a child or a local (or other) of that node.)
	 * @param inhs A map from attribute names to Lazys that define them.  These Lazys will be supplied with 'parent' as their context for evaluation.
	 * @return A DecoratedNode with the additional attributes supplied, referencing this DecoratedNode as 'base'.
	 */
	@Override
	public DecoratedNode decorate(final DecoratedNode parent, final Lazy[] inhs) {
		// System.err.println("TRACE: " + parent.getDebugID() + " extra-decorating " + getDebugID());
		if (forwardParent != null) {
			throw new SilverException(parent.getDebugID() + " cannot decorate " + getDebugID() + " with inhs since it is the forward of " + forwardParent.getDebugID() + ".");
		}
		if (inhs != null) {
			inheritedAttributes = inheritedAttributes.clone();  // Avoid modifying the static inh array from the original parent Node
			for(int i = 0; i < inhs.length; i++) {
				final int attribute = i;
				if(inhs[attribute] != null) {
					if(inheritedAttributes[attribute] == null) {
						inheritedAttributes[attribute] = (context) -> inhs[attribute].eval(parent);
					} else {
						throw new SilverException(parent.getDebugID() + " cannot decorate " + getDebugID() + " with inh '" + self.getNameOfInhAttr(attribute) + "' as this attribute has already been provided by " + this.parent.getDebugID() + ".");
					}
				}
			}
		}
		return this;
	}

	/**
	 * Decorate this node with a forward parent.
	 * This node should not have been supplied with any inherited attributes!
	 * 
	 * @param parent The DecoratedNode creating this one. (Whether this is a child or a local (or other) of that node.)
	 * @param fwdParent The DecoratedNode that forwards to the one we are about to create. We will pass inherited attribute access requests to this node.
	 * @return A DecoratedNode with the attributes supplied.
	 */
	public DecoratedNode decorate(final DecoratedNode parent, final DecoratedNode fwdParent) {
		if (inheritedAttributes != null) {
			for(int attribute = 0; attribute < inheritedAttributes.length; attribute++) {
				if(inheritedAttributes[attribute] != null) {
					throw new SilverException(parent.getDebugID() + " cannot decorate " + getDebugID() + " via forwarding as it has already been provided with inh '" + self.getNameOfInhAttr(attribute) + "' by " + this.parent.getDebugID() + ".");
				}
			}
		}
		forwardParent = fwdParent;
		return this;
	}

	/**
	 * Accessor function to access the originally-decorated Node.
	 * The Node returned by this function must never be decorated - 
	 * e.g. for use by origin tracking or for debugging purposes.
	 * One should typically use {@link undecorate} instead to avoid duplicating any unique children.
	 * 
	 * @return The {@link Node} this decorates.
	 */
	public final Node getNode() {
		return self;
	}

	/**
	 * Returns the child of this DecoratedNode, without potentially decorating it.
	 * 
	 * <p>Warning: do not mix {@link #childAsIs} and {@link #childDecorated} on the same child!
	 * 
	 * @param child The number of the child to obtain.
	 * @return The unmodified value of the child.
	 */
	@SuppressWarnings("unchecked")
	public <T> T childAsIs(final int child) {
		return (T) self.getChild(child);
	}
	
	/**
	 * Returns the child of this DecoratedNode, decorating it with whatever inherited attributes
	 * this production has for it.
	 * 
	 * <p>Warning: do not mix {@link #childAsIs} and {@link #childDecorated} on the same child!
	 * 
	 * @param child The number of the child to obtain.
	 * @return The decorated value of the child.
	 */
	public DecoratedNode childDecorated(final int child) {
		Object o = this.childrenValues[child]; 
		if(o == null) {
			o = createDecoratedChild(child);
			
			assert(o != null);

			// CACHE : probably should not comment out child caching?
			this.childrenValues[child] = o;
		}
		return (DecoratedNode)o;
	}

	/**
	 * Create the DecoratedNode for a child.
	 * Separate function to keep {@link #childDecorated} small and inlineable.
	 * This is, after all, the "slow path."
	 */
	private final DecoratedNode createDecoratedChild(final int child) {
		return ((Decorable)self.getChild(child)).decorate(this, self.getChildInheritedAttributes(child));
	}

	/**
	 * Get the value of a local, caching it for re-use.
	 * 
	 * <p>Warning: do not mix {@link #localAsIs} and {@link #localDecorated} on the same local attribute!
	 * 
	 * @param attribute The full name of the local to obtain.
	 * @return The value of the local.
	 */
	@SuppressWarnings("unchecked")
	public <T> T localAsIs(final int attribute) {
		Object o = this.localValues[attribute];
		if(o == null) {
			o = evalLocalAsIs(attribute);
			
			assert(o != null);

			// CACHE : comment out to disable caching for local attributes
			// not recommended due to IO objects (IOString, etc)
			this.localValues[attribute] = o;
		}
		return (T) o;
	}
	
	/**
	 * Slow path to eval a local, kept separate for inlining reasons.
	 */
	private final Object evalLocalAsIs(final int attribute) {
		try {
			return self.getLocal(attribute).eval(this);
		} catch(Throwable t) {
			throw handleLocalError(attribute, t);
		}
	}
	
	/**
	 * Error generation code for local attribute evaluation.
	 * Kept separate so as not to impact inlining decisions.
	 */
	private final SilverException handleLocalError(final int attribute, final Throwable t) {
		// Rather than checking in the fast path, we try to reconstruct what went wrong in the slow path.
		if(self.getLocal(attribute) == null) {
			return new MissingDefinitionException("Local '" + self.getNameOfLocalAttr(attribute) + "' not defined in " + getDebugID());
		}
		return new TraceException("While evaling local '" + self.getNameOfLocalAttr(attribute) + "' in " + getDebugID(), t);
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
			o = evalLocalDecorated(attribute);
			
			assert(o != null);

			// CACHE : comment out to disable caching for local attributes
			// not recommended due to IO objects (IOString, etc)
			this.localValues[attribute] = o;
		}
		return (DecoratedNode)o;
	}
	
	/**
	 * Another case of keeping the slow paths out of here, so it can be inlined.
	 */
	private final DecoratedNode evalLocalDecorated(final int attribute) {
		return ((Decorable)evalLocalAsIs(attribute)).decorate(this, self.getLocalInheritedAttributes(attribute));
	}

	/**
	 * Obtain a synthesized attribute from this DecoratedNode. First, look for definitions on this node,
	 * and if that fails, request it from whatever we forward to, if anything.
	 * 
	 * @param attribute The full name of the attribute.
	 * @return The value of the attribute.
	 */
	@SuppressWarnings("unchecked")
	public <T> T synthesized(final int attribute) {
		// common.Util.stackProbe();
		// System.err.println("TRACE: " + getDebugID() + " demanding syn attribute: " + self.getNameOfSynAttr(attribute));
		
		Object o = this.synthesizedValues[attribute];
		if(o == null) {
			o = evalSyn(attribute);
			
			assert(o != null);

			// CACHE : comment out to disable caching for synthesized attributes
			this.synthesizedValues[attribute] = o;
		}
		return (T) o;
	}
	
	private final Object evalSyn(final int attribute) {
		// TODO: Try to break this up into < 37 byte methods?
		Lazy l = self.getSynthesized(attribute);
		if(l != null) {
			try {
				if(l instanceof CollectionAttribute) {
					// This is necessary to ensure collection
					// attribute equations in aspect default
					// productions work; see
					// https://github.com/melt-umn/silver/issues/290
					CollectionAttribute prodAttr = (CollectionAttribute)l;
					if(!prodAttr.appliedNTFix) {
						prodAttr.appliedNTFix = true;
						CollectionAttribute ntAttr = (CollectionAttribute)self.getDefaultSynthesized(attribute);
						if(ntAttr != null)
							prodAttr.getPieces().addAll(ntAttr.getPieces());
					}
				}
				return l.eval(this);
			} catch(Throwable t) {
				throw new TraceException("While evaling syn '" + self.getNameOfSynAttr(attribute) + "' in " + getDebugID(), t);
			}
		} else if(self.hasForward()) {
			try {
				return forward().synthesized(attribute);
			} catch(Throwable t) {
				throw new TraceException("While evaling syn '" + self.getNameOfSynAttr(attribute) + "' via forward in " + getDebugID(), t);
			}
		} else {
			l = self.getDefaultSynthesized(attribute);
			if(l != null) {
				try {
					return l.eval(this);
				} catch(Throwable t) {
					throw new TraceException("While evaling default for '" + self.getNameOfSynAttr(attribute) + "' in " + getDebugID(), t);
				}
			} else {
				throw new MissingDefinitionException("Synthesized attribute '" + self.getNameOfSynAttr(attribute) + "' not defined in " + getDebugID());
			}
		}
	}
	
	/**
	 * Get the forwarded-to DecoratedNode.  Cached.  There is no need for an "AsIs" vs "Decorated"
	 * variant of this function, since we always know it's a Node.
	 * 
	 * @return The DecoratedNode this one forwards to. (Crash if non-forwarding!)
	 */
	public DecoratedNode forward() {
		if(this.forwardValue == null) {
			// CACHE : should not comment out forward caching !
			this.forwardValue = evalForward();

			assert(this.forwardValue != null);
		}
		return this.forwardValue;
	}

	/**
	 * @return Either forward() or this.
	 */
	public DecoratedNode forwardOrThis() {
		return self.hasForward() ? forward() : this;
	}
	
	/**
	 * Also to keep the fast path small and inlineable.
	 */
	private final DecoratedNode evalForward() {
		try {
			return self.evalForward(this).decorate(parent, this);
		} catch(Throwable t) {
			throw handleFwdError(t);
		}
	}
	
	private final RuntimeException handleFwdError(Throwable t) {
		return new TraceException("While evaling forward equation in " + getDebugID(), t);
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
	@SuppressWarnings("unchecked")
	public <T> T inherited(final int attribute) {
		// common.Util.stackProbe();
		// System.err.println("TRACE: " + getDebugID() + " demanding inh attribute: " + self.getNameOfInhAttr(attribute));
		
		Object o = this.inheritedValues[attribute];
		if(o == null) {
			o = evalInhSomehow(attribute);
			
			assert(o != null);

			// CACHE : comment out to disable caching of inherited attributes
			// not recommended because it leads to a combinatoric explosion for environments
			// due to thunks keeping around references to previously computed environments that
			// we're recomputing
			this.inheritedValues[attribute] = o;
		}
		return (T) o;
	}
	
	private final Object evalInhSomehow(final int attribute) {
		if(forwardParent == null)
			return evalInhHere(attribute);
		else
			return evalInhViaFwdP(attribute);
	}
	private final Object evalInhViaFwdP(final int attribute) {
		try {
			return forwardParent.inheritedForwarded(attribute);
		} catch(Throwable t) {
			throw handleInhFwdPError(attribute, t); 
		}
	}
	private final SilverException handleInhFwdPError(final int attribute, Throwable t) {
		//This seems impossible since we're checking if forwardParent==null earlier up there!
		//if(forwardParent == null) {
		//	return new MissingDefinitionException("Inherited attribute '" + self.getNameOfInhAttr(attribute) + "' not provided to " + getDebugID() + " by " + parent.getDebugID());
		//}
		return new TraceException("While evaling inh '" + self.getNameOfInhAttr(attribute) + "' via forward in " + getDebugID(), t);
	}
	private final Object evalInhHere(final int attribute) {
		try {
			return inheritedAttributes[attribute].eval(parent);
		} catch(Throwable t) {
			throw handleInhHereError(attribute, t);
		}
	}
	private final SilverException handleInhHereError(final int attribute, Throwable t) {
		// We specifically have to check here for inheritedAttributes == null, because
		// that's what happens when we don't supply any inherited attributes...
		// That is, unlike the unconditional access earlier for inheritedValues[attribute]
		// (which could be null if *no inherited attributes occur at all* on this
		// node), this could be the result of correctly compiled, but wrongly written user
		// code.
		// Also, we need to check for attribute >= inheritedAttributes.length, because
		// with inherited occurs-on constraints don't know how big to make the array,
		// only the largest supplied attribute index, 
		// so the user omitting some inherited equations for attributes with higher indices
		// could mean the resulting array that we are passed is too short.  Sigh.
		if(inheritedAttributes == null || attribute >= inheritedAttributes.length || inheritedAttributes[attribute] == null) {
			return new MissingDefinitionException("Inherited attribute '" + self.getNameOfInhAttr(attribute) + "' not provided to " + getDebugID() + " by " + parent.getDebugID());
		}
		return new TraceException("While evaling inh '" + self.getNameOfInhAttr(attribute) + "' in " + getDebugID(), t);
	}
	
	/**
	 * Only called by {@link #inherited(String)}, when it doesn't have an inherited attribute,
	 * and it wants to request that value from this DecorateNode (its {@link #forwardParent}).
	 * 
	 * @param attribute The full name of the attribute.
	 * @return The value of the attribute.
	 */
	protected Object inheritedForwarded(final int attribute) {
		// common.Util.stackProbe();
		// System.err.println("TRACE: " + getDebugID() + " demanding FORWARDED inh attribute: " + self.getNameOfInhAttr(attribute));
		
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
			throw handleInhFwdError(attribute, t);
		}
	}
	
	private final SilverException handleInhFwdError(final int attribute, Throwable t) {
		return new TraceException("While evaling inh '" + self.getNameOfInhAttr(attribute) + "' for forward in " + getDebugID(), t);
	}

	// The following are very common types of thunks.
	// We put them here in the runtime to reduce generated jar sizes,
	// as many class files can start eating space like crazy.
	// Was something like 30% reduction, just from these...
	
	public final Object childDecoratedLazy(final int child) {
		if(childrenValues[child] != null)
			return childrenValues[child];
		
		return new Thunk<Object>(() -> this.childDecorated(child));
	}
	public final Object childUndecoratedLazy(final int child) {
		return new Thunk<Object>(() -> this.childDecorated(child).undecorate());
	}
	public final Object childAsIsLazy(final int child) {
		// childAsIs does not store in the childrenValues array, so...
		// Straight up use whatever thunk (or not!) is in the node.
		return self.getChildLazy(child);
	}
	public final Object localDecoratedLazy(final int index) {
		if(localValues[index] != null)
			return localValues[index];
		
		return new Thunk<Object>(() -> this.localDecorated(index));
	}
	public final Object localAsIsLazy(final int index) {
		if(localValues[index] != null)
			return localValues[index];

		return new Thunk<Object>(() -> this.localAsIs(index));
	}
	public final Object childDecoratedSynthesizedLazy(final int child, final int index) {
		Object v = childrenValues[child];
		if(v != null) {
			return ((DecoratedNode)v).contextSynthesizedLazy(index);
		}

		return new Thunk<Object>(() -> this.childDecorated(child).synthesized(index));
	}
	public final Object childAsIsSynthesizedLazy(final int child, final int index) {
		// For as-is children, we do not store in childrenValues
		Object v = self.getChildLazy(child);
		if(!(v instanceof Thunk)) {
			return ((DecoratedNode)v).contextSynthesizedLazy(index);
		}

		return new Thunk<Object>(() -> ((DecoratedNode)this.childAsIs(child)).synthesized(index));
	}
	public final Object contextSynthesizedLazy(final int index) {
		if(synthesizedValues[index] != null) {
			return synthesizedValues[index];
		}
		
		return new Thunk<Object>(() -> this.synthesized(index));
	}
	public final Object contextInheritedLazy(final int index) {
		if(inheritedValues[index] != null)
			return inheritedValues[index];
		
		return new Thunk<Object>(() -> this.inherited(index));
	}
	
	@Override
	public final DecoratedTypeRep getType() {
		return new DecoratedTypeRep(self.getType());
	}
	
	/**
	 * Debug ID. e.g. "node 'some:prod' (f32ca8, ParseFile.c:328:8)"
	 * 
	 * @return an identification string for this node.
	 */
	public final String getDebugID() {
		String qualifier;
		if(self == null) {
			return "<top>";
		} else if(self instanceof silver.core.Alocation) {
			DecoratedNode loc = ((silver.core.Alocation)self).getAnno_silver_core_location().decorate(TopNode.singleton, (Lazy[])null);
			String file = loc.synthesized(silver.core.Init.silver_core_filename__ON__silver_core_Location).toString();
			int line = (Integer)loc.synthesized(silver.core.Init.silver_core_line__ON__silver_core_Location);
			int col = (Integer)loc.synthesized(silver.core.Init.silver_core_column__ON__silver_core_Location);
			qualifier = ", " + file + ":" + Integer.toString(line) + ":" + Integer.toString(col);
		} else {
			qualifier = "";
		}
		return "'" + self.getName() + "' (" + Integer.toHexString(System.identityHashCode(this)) + qualifier + ")";
	}
	
	public final String toString() {
		return getDebugID();
	}
}
