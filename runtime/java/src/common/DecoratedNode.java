package common;

import java.io.FileNotFoundException;
import java.util.Arrays;

import common.exceptions.MissingDefinitionException;
import common.exceptions.MissingInheritedDefinitionException;
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
	 * The DecoratedNode for a partially decorated reference is here being decorated with additional inherited attributes. (May be null)
	 */
	protected final DecoratedNode base;
	
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
	 * The cache of the exception thrown in a previous attempt to evaluate the forward,
	 * or null if evaluation has not previously failed.
	 * Either a MissingInheritedDefinitionException, or a TraceException leading to one.
	 */
	protected SilverException forwardFailed;
	/**
	 * The cache of the exception thrown in a previous attempt to evaluate an inherited attribute,
	 * or null if evaluation has not previously failed.
	 * Either a MissingInheritedDefinitionException, or a TraceException leading to one.
	 */
	protected SilverException[] inheritedFailed;
	/**
	 * The cache of the exception thrown in a previous attempt to evaluate an synthesized attribute,
	 * or null if evaluation has not previously failed.
	 * Either a MissingInheritedDefinitionException, or a TraceException leading to one.
	 */
	protected SilverException[] synthesizedFailed;
	/**
	 * The cache of the exception thrown in a previous attempt to evaluate a local attribute,
	 * or null if evaluation has not previously failed.
	 * Either a MissingInheritedDefinitionException, or a TraceException leading to one.
	 */
	protected SilverException[] localFailed;

	
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
	 * @param base  The decorated node whose attribute values should be used as defaults.
	 * 
	 * @see Node#decorate(DecoratedNode, DecoratedNode)
	 * @see Node#decorate(DecoratedNode, Lazy[])
	 */
	DecoratedNode(
			final int cc, final int ic, final int sc, final int lc,
			final Node self, final DecoratedNode parent,
			final Lazy[] inhs, final DecoratedNode forwardParent, final DecoratedNode base) {
		this.self = self;
		this.parent = parent;
		this.originCtx = parent!=null?parent.originCtx:null;
		this.inheritedAttributes = inhs;
		this.forwardParent = forwardParent;
		this.base = base;
		
		// create caches
		this.childrenValues =    (cc > 0) ? new Object[cc] : null;
		this.inheritedValues =   (ic > 0) ? new Object[ic] : null;
		this.synthesizedValues = (sc > 0) ? new Object[sc] : null;
		this.localValues =       (lc > 0) ? new Object[lc] : null;
		
		// STATS: Uncomment to enable statistics
		//Statistics.dnSpawn(self!=null?self.getClass():TopNode.class);
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
		this.base = null;
		
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
	 * @return The {@link Node} this decorates.
	 */
	public final Node undecorate() {
		return self;
	}
	
	/**
	 * Decorate this (partially decorated) node with additional inherited attributes.
	 * 
	 * @param parent The DecoratedNode creating this one. (Whether this is a child or a local (or other) of that node.)
	 * @param inhs A map from attribute names to Lazys that define them.  These Lazys will be supplied with 'parent' as their context for evaluation.
	 * @return A DecoratedNode with the additional attributes supplied, referencing this DecoratedNode as 'base'.
	 */
	@Override
	public DecoratedNode decorate(final DecoratedNode parent, final Lazy[] inhs) {
		return new DecoratedNode(self.getNumberOfChildren(),
				                 self.getNumberOfInhAttrs(),
				                 self.getNumberOfSynAttrs(),
				                 self.getNumberOfLocalAttrs(),
				                 self, parent, inhs, null, this);
	}

	/**
	 * Decorate this forward node with additional inherited attributes provided by fwdParent.
	 * This should only be used in evalForward for a DecoratedNode created by DecoratedNode.decorate().
	 * 
	 * @param parent The "true parent" of this node (same as the fwdParent's parent) 
	 * @param fwdParent The DecoratedNode that forwards to the one we are about to create. We will pass inherited attribute access requests to this node.
	 * @return A DecoratedNode with the specified forwardParent, referencing this DecoratedNode as 'base'.
	 */
	@Override
	public DecoratedNode decorate(final DecoratedNode parent, final DecoratedNode fwdParent) {
		return new DecoratedNode(self.getNumberOfChildren(),
                                 self.getNumberOfInhAttrs(),
                                 self.getNumberOfSynAttrs(),
                                 self.getNumberOfLocalAttrs(),
                                 self, parent, null, fwdParent, this);
	}

	/**
	 * Returns the child of this DecoratedNode, without potentially decorating it.
	 * 
	 * <p>Warning: While it is technically safe to mix calls to {@link #childAsIs} and {@link #childDecorated}
	 * this behavior should not be relied upon, as it may change later.
	 * 
	 * @param child The number of the child to obtain.
	 * @return The unmodified value of the child.
	 */
	public Object childAsIs(final int child) {
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
		final Decorable d = (Decorable)self.getChild(child);
		final Lazy[] inhs = self.getChildInheritedAttributes(child);
		if(base != null) {
			if(d instanceof Node) {
				// We are decorating d, an undecorated Node.
				// Decorate d, using base's corresponding decorated child as the base.
				return ((Node)d).decorate(this, inhs, base.childDecorated(child));
			} else {
				// We are decorating d, a DecoratedNode.
				// We already have a base (d), but we also want to default to the inhs on base's decorated child.
				// Decorate d, using inherited Lazys that default to evaluating on base's decorated child.
				return d.decorate(this, defaultInhs(base.childDecorated(child), inhs));
			}
		} else {
			return d.decorate(this, inhs);
		}
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
			o = evalLocalAsIs(attribute);
			
			assert(o != null);

			// CACHE : comment out to disable caching for local attributes
			// not recommended due to IO objects (IOString, etc)
			this.localValues[attribute] = o;
		}
		return o;
	}
	
	/**
	 * Slow path to eval a local, kept separate for inlining reasons.
	 */
	private final Object evalLocalAsIs(final int attribute) {
		if(localFailed != null && localFailed[attribute] != null) {
			throw localFailed[attribute];
		}
		DecoratedNode baseDecorated = null;
		if(base != null && !base.hasLocalError(attribute)) {
			try {
				// System.err.println("TRACE: " + getDebugID() + " trying base for local '" + self.getNameOfLocalAttr(attribute));
				Object o = base.evalLocalAsIs(attribute);
				if(o instanceof DecoratedNode) {
					// The local is a decorated node.
					// We need to compute it on the new tree and decorate with the inherited attributes from here. 
					baseDecorated = (DecoratedNode)o;
				} else {
					// The local is not a decorated node, we can safely return it immediately.
					return o;
				}
			} catch(Throwable t) {
				if (SilverException.getRootCause(t) instanceof MissingInheritedDefinitionException) {
					// System.err.println("TRACE: " + getDebugID() + " failed base for local '" + self.getNameOfLocalAttr(attribute));
					// We hit a missing inherited attribute. Fall back to evaluating on the new tree.
					// Note that this CAN happen if the base tree actually has a missing inherited equation!
					// This can lead to strange runtime error messages, as we might then report a
					// missing equation on the new tree, but in these cases the actual missing equation
					// should have been caught by the flow analysis.
				} else {
					// No need to cache the error, since it is fatal
					throw new TraceException("While evaling local '" + self.getNameOfLocalAttr(attribute) + "' via partially decorated reference in " + getDebugID(), t);
				}
			}
		}
		try {
			if(baseDecorated != null) {
				final Lazy[] inhs = new Lazy[baseDecorated.inheritedAttributes.length];
				final DecoratedNode baseDec = baseDecorated;
				for(int i = 0; i < inhs.length; i++) {
					final int inhAttribute = i;
					inhs[i] = (context) -> baseDec.inherited(inhAttribute);
				}
				return ((DecoratedNode)self.getLocal(attribute).eval(this)).decorate(this, inhs);
			} else {
				return self.getLocal(attribute).eval(this);
			}
		} catch(Throwable t) {
			throw handleLocalError(attribute, t);
		}
	}
	private final boolean hasLocalError(final int attribute) {
		return localFailed != null && localFailed[attribute] != null;
	}
	
	/**
	 * Error generation code for local attribute evaluation.
	 * Kept separate so as not to impact inlining decisions.
	 */
	private final SilverException handleLocalError(final int attribute, final Throwable t) {
		// Rather than checking in the fast path, we try to reconstruct what went wrong in the slow path.
		if(self.getLocal(attribute) == null) {
			return new MissingDefinitionException("Local '" + self.getNameOfLocalAttr(attribute) + "' not defined in " + getDebugID());
		} else {
			if(localFailed == null) localFailed = new SilverException[localValues.length];
			localFailed[attribute] = new TraceException("While evaling local '" + self.getNameOfLocalAttr(attribute) + "' in " + getDebugID(), t);
			return localFailed[attribute];
		}
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
		final Decorable d = (Decorable)evalLocalAsIs(attribute);
		final Lazy[] inhs = self.getLocalInheritedAttributes(attribute);
		if (base != null) {
			try {
				// System.err.println("TRACE: " + getDebugID() + " trying base for decorated local '" + self.getNameOfLocalAttr(attribute));
				if(d instanceof Node) {
					// We are decorating d, an undecorated Node.
					// Decorate d, using base's corresponding decorated local as the base.
					return ((Node)d).decorate(this, inhs, base.localDecorated(attribute));
				} else {
					// We are decorating d, a DecoratedNode.
					// We already have a base (d), but we also want to default to the inhs on base's decorated local.
					// Decorate d, using inherited Lazys that default to evaluating on base's decorated local.
					return d.decorate(this, defaultInhs(base.localDecorated(attribute), inhs));
				}
			} catch(Throwable t) {
				if (SilverException.getRootCause(t) instanceof MissingInheritedDefinitionException) {
					// We hit a missing inherited attribute when calling base.localDecorated().
					// That just means that we don't have a base when decorating the local.
				} else {
					// No need to cache the error, since it is fatal
					throw new TraceException("While evaling local '" + self.getNameOfLocalAttr(attribute) + "' via partially decorated reference in " + getDebugID(), t);
				}
			}
		}
		return d.decorate(this, inhs);
	}

	/**
	 * Obtain a synthesized attribute from this DecoratedNode. First, look for definitions on this node,
	 * and if that fails, request it from whatever we forward to, if anything.
	 * 
	 * @param attribute The full name of the attribute.
	 * @return The value of the attribute.
	 */
	public Object synthesized(final int attribute) {
		// common.Util.stackProbe();
		// System.err.println("TRACE: " + getDebugID() + " demanding syn attribute: " + self.getNameOfSynAttr(attribute));
		
		Object o = this.synthesizedValues[attribute];
		if(o == null) {
			o = evalSyn(attribute);
			
			assert(o != null);

			// CACHE : comment out to disable caching for synthesized attributes
			this.synthesizedValues[attribute] = o;
		}
		return o;
	}
	
	private final Object evalSyn(final int attribute) {
		// TODO: Try to break this up into < 37 byte methods?
		if(hasSynError(attribute)) {
			throw synthesizedFailed[attribute];
		}

		if(base != null && !base.hasSynError(attribute)) {
			try {
				// System.err.println("TRACE: " + getDebugID() + " trying base for syn '" + self.getNameOfSynAttr(attribute));
				return base.synthesized(attribute);
			} catch(Throwable t) {
				if (SilverException.getRootCause(t) instanceof MissingInheritedDefinitionException) {
					// System.err.println("TRACE: " + getDebugID() + " failed base for syn '" + self.getNameOfSynAttr(attribute));
					// We hit a missing inherited attribute. Fall back to evaluating on the new tree.
					// Note that this CAN happen if the base tree actually has a missing inherited equation!
					// This can lead to strange runtime error messages, as we might then report a
					// missing equation on the new tree, but in these cases the actual missing equation
					// should have been caught by the flow analysis.
				} else {
					// No need to cache the error, since it is fatal
					throw new TraceException("While evaling syn '" + self.getNameOfSynAttr(attribute) + "' via partially decorated reference in " + getDebugID(), t);
				}
			}
		}
		
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
				throw cacheSynError(attribute, new TraceException("While evaling syn '" + self.getNameOfSynAttr(attribute) + "' in " + getDebugID(), t));
			}
		} else if(self.hasForward()) {
			try {
				return forward().synthesized(attribute);
			} catch(Throwable t) {
				throw cacheSynError(attribute, new TraceException("While evaling syn via forward in " + getDebugID(), t));
			}
		} else {
			l = self.getDefaultSynthesized(attribute);
			if(l != null) {
				try {
					return l.eval(this);
				} catch(Throwable t) {
					throw cacheSynError(attribute, new TraceException("While evaling default for '" + self.getNameOfSynAttr(attribute) + "' in " + getDebugID(), t));
				}
			} else {
				// No need to cache the error, since it is fatal
				throw new MissingDefinitionException("Synthesized attribute '" + self.getNameOfSynAttr(attribute) + "' not defined in " + getDebugID());
			}
		}
	}
	private final SilverException cacheSynError(final int attribute, final SilverException e) {
		if(synthesizedFailed == null) synthesizedFailed = new SilverException[synthesizedValues.length];
		synthesizedFailed[attribute] = e;
		return e;
	}
	private final boolean hasSynError(final int attribute) {
		return synthesizedFailed != null && synthesizedFailed[attribute] != null;
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
		if(hasForwardError()) {
			throw forwardFailed;
		}
		Node forwardNode;
		try {
			forwardNode = self.evalForward(this);
		} catch(Throwable t) {
			forwardFailed = new TraceException("While evaling forward equation in " + getDebugID(), t);
			throw forwardFailed;
		}
		if(base != null && !base.hasForwardError()) {
			try {
				// System.err.println("TRACE: " + getDebugID() + " trying base for forward");
				return forwardNode.decorate(parent, this, base.forward());
			} catch(Throwable t) {
				if (SilverException.getRootCause(t) instanceof MissingInheritedDefinitionException) {
					// System.err.println("TRACE: " + getDebugID() + " failed base for forward");
					// We hit a missing inherited attribute when calling base.forward(). 
					// That just means that we don't have a base when decorating forwardNode.
				} else {
					// No need to cache the error, since it is fatal
					throw new TraceException("While evaling forward via partially decorated reference ' in " + getDebugID(), t);
				}
			}
		}
		return forwardNode.decorate(parent, this);
	}
	private final boolean hasForwardError() {
		return forwardFailed != null;
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
		return o;
	}
	
	private final Object evalInhSomehow(final int attribute) {
		if(hasInhError(attribute)) {
			throw inheritedFailed[attribute];
		}

		if(base != null && !base.hasInhError(attribute)) {
			try {
				// System.err.println("TRACE: " + getDebugID() + " trying base for inh '" + self.getNameOfInhAttr(attribute));
				return base.inherited(attribute);
			} catch(Throwable t) {
				if (SilverException.getRootCause(t) instanceof MissingInheritedDefinitionException) {
					// System.err.println("TRACE: " + getDebugID() + " failed base for inh '" + self.getNameOfInhAttr(attribute));
					// We hit a missing inherited attribute. Fall back to evaluating on the new tree.
					// Note that this CAN happen if the base tree actually has a missing inherited equation!
					// This can lead to strange runtime error messages, as we might then report a
					// missing equation on the new tree, but in these cases the actual missing equation
					// should have been caught by the flow analysis.
				} else {
					throw cacheInhError(attribute, new TraceException("While evaling inh '" + self.getNameOfInhAttr(attribute) + "' via partially decorated reference ' in " + getDebugID(), t));
				}
			}
		}
		if(forwardParent == null)
			return evalInhHere(attribute);
		else
			return evalInhViaFwdP(attribute);
	}
	private final Object evalInhViaFwdP(final int attribute) {
		try {
			return forwardParent.inheritedForwarded(attribute);
		} catch(Throwable t) {
			throw cacheInhError(attribute, handleInhFwdPError(attribute, t));
		}
	}
	private final SilverException handleInhFwdPError(final int attribute, Throwable t) {
		//This seems impossible since we're checking if forwardParent==null earlier up there!
		//if(forwardParent == null) {
		//	return new MissingInheritedDefinitionException("Inherited attribute '" + self.getNameOfInhAttr(attribute) + "' not provided to " + getDebugID() + " by " + parent.getDebugID());
		//}
		return new TraceException("While evaling inh via forward in " + getDebugID(), t);
	}
	private final Object evalInhHere(final int attribute) {
		try {
			return inheritedAttributes[attribute].eval(parent);
		} catch(Throwable t) {
			throw cacheInhError(attribute, handleInhHereError(attribute, t));
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
			return new MissingInheritedDefinitionException("Inherited attribute '" + self.getNameOfInhAttr(attribute) + "' not provided to " + getDebugID() + " by " + parent.getDebugID());
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
			throw cacheInhError(attribute, handleInhFwdError(attribute, t));
		}
	}
	
	private final SilverException handleInhFwdError(final int attribute, Throwable t) {
		return new TraceException("While evaling inh '" + self.getNameOfInhAttr(attribute) + "' for forward in " + getDebugID(), t);
	}

	private final SilverException cacheInhError(final int attribute, final SilverException e) {
		if(inheritedFailed == null) inheritedFailed = new SilverException[inheritedValues.length];
		inheritedFailed[attribute] = e;
		return e;
	}
	private final boolean hasInhError(final int attribute) {
		return inheritedFailed != null && inheritedFailed[attribute] != null;
	}
	
	/**
	 * Create an array of inherited attribute Lazys that first default to another tree's inherited
	 * attributes.
	 * 
	 * @param base  The tree whose attributes to default to.
	 * @param inhs  The attribute equations for the new decoration site.
	 * @return  An array of Lazys to be used as the inherited attributes for the new decoration site.
	 */
	private final Lazy[] defaultInhs(final DecoratedNode base, final Lazy[] inhs) {
		final Lazy[] fallbackInhs = new Lazy[inhs.length];
		for(int i = 0; i < inhs.length; i++) {
			if(base.inheritedAttributes != null && base.inheritedAttributes[i] != null || base.base != null) {
				// We have an inherited equation for the attribute on base.
				// It may or may not have its dependencies.
				final int attribute = i;
				if(inhs[i] != null) {
					// We also have an inherited equation for the new decoration site.
					// Try base first, then fall back to the new decoration site's attributes.
					fallbackInhs[i] = (context) -> {
						try {
							return base.inherited(attribute);
						} catch(Throwable t) {
							if (SilverException.getRootCause(t) instanceof MissingInheritedDefinitionException) {
								// We hit a missing inherited attribute. Fall back to evaluating on the new tree.
								// Note that this CAN happen if the base tree actually has a missing inherited equation!
								// This can lead to strange runtime error messages, as we might then report a
								// missing equation on the new tree, but in these cases the actual missing equation
								// should have been caught by the flow analysis.
							} else {
								// No need to cache the error, since it is fatal
								throw new TraceException("While evaling inh '" + base.self.getNameOfInhAttr(attribute) + "' via partially decorated reference ' in " + getDebugID(), t);
							}
						}
						return inhs[attribute].eval(context);
					};
				} else {
					// We only have an inherited equation on base.  Demand it.
					fallbackInhs[i] = (context) -> {
						try {
							return base.inherited(attribute);
						} catch(Throwable t) {
							throw new TraceException("While evaling inh '" + base.self.getNameOfInhAttr(attribute) + "' via partially decorated reference ' in " + getDebugID(), t);
						}
					};
				}
			} else {
				// We only have an inherited equation on the new decoration site, or no equation at all (inhs[i] == null).
				// Just use that, in either case.
				fallbackInhs[i] = inhs[i];
			}
		}
		return fallbackInhs;
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
