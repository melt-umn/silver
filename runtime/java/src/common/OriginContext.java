package common;

import core.*;
import java.util.List;
import java.util.ArrayList;


/**
 * Implementation of "the stuff on the left of the turnstile" that needs to be
 *  passed along for origin tracking. This would be a value type if Java
 *  supported them.
 * 
 * @author louisg
 */
public final class OriginContext {
	public enum Variety {
	    NORMAL, MAINFUNCTION, FFI, REFLECTIVE, PARSERACTION, OTHER
	}

	public final Variety variety;
	public final Node lhs;
	public final List<NOriginLink> rules;

	private OriginContext(Variety variety, Node lhs, List<NOriginLink> rules) {
		this.variety = variety;
		this.lhs = lhs;
		this.rules = rules;
	}

	public static final OriginContext ENTRY_CONTEXT =
		new OriginContext(Variety.MAINFUNCTION, null, new ArrayList<NOriginLink>());

	public static final OriginContext FFI_CONTEXT =
		new OriginContext(Variety.FFI, null, new ArrayList<NOriginLink>());

	public static final OriginContext REFLECTION_CONTEXT =
		new OriginContext(Variety.REFLECTIVE, null, new ArrayList<NOriginLink>());

	public static final OriginContext PARSERACTION_CONTEXT =
		new OriginContext(Variety.PARSERACTION, null, new ArrayList<NOriginLink>());

	public OriginContext(Node lhs, List<NOriginLink> rules) {
		this.variety = Variety.NORMAL;
		this.lhs = lhs;
		this.rules = rules;
	}

	public OriginContext(OriginContext old, List<NOriginLink> newRules) {
		this.variety = old.variety;
		this.lhs = old.lhs;
		this.rules = new ArrayList<NOriginLink>();
		this.rules.addAll(old.rules);
		this.rules.addAll(newRules);
	}

	public ConsCell rulesAsSilverList() {
		return ConsCell.fromList(this.rules);
	}

	public NOriginInfo makeNewConstructionOrigin(boolean isContractum) {
		switch (this.variety) {
			case NORMAL:
				if (this.lhs == null) {
					System.err.println("Origins Warn: makeNewConstructionOrigin: variety == NORMAL but lhs == null!");
					return new core.PotherOriginInfo(null, new core.PotherBogusOIT(null), new common.StringCatter("??? variety == NORMAL but lhs == null!"), ConsCell.nil);
				}
				if (!(this.lhs instanceof Node)) {
					System.err.println("Origins Warn: attrAccessCopy: lhs not instanceof Node!");
					return new core.PotherOriginInfo(null, new core.PotherBogusOIT(null), new common.StringCatter("??? lhs not instanceof Node!"), ConsCell.nil);
				}
				return new core.PoriginOriginInfo(null, new core.PsetAtConstructionOIT(null), this.lhs.wrapInLink(), this.rulesAsSilverList(), false);

			case MAINFUNCTION:
				return new core.PotherOriginInfo(null, new core.PsetFromEntryOIT(null), new common.StringCatter("Main Function"), ConsCell.nil);
			
			case FFI:
				return new core.PotherOriginInfo(null, new core.PsetFromFFIOIT(null), new common.StringCatter("Called from FFI"), ConsCell.nil);

			case REFLECTIVE:
				return new core.PotherOriginInfo(null, new core.PsetFromReflectionOIT(null), new common.StringCatter("Called from Reflection"), ConsCell.nil);

			case PARSERACTION:
				return new core.PotherOriginInfo(null, new core.PsetFromParserActionOIT(null), new common.StringCatter("Called inside a parser action block"), ConsCell.nil);

			default:
				return new core.PotherOriginInfo(null, new core.PotherBogusOIT(null), new common.StringCatter("??? Unknown variety in OriginContext.makeNewConstructionOrigin: "+this.variety.toString()), ConsCell.nil);
		}
	}

	public <T extends Node> T attrAccessCopy(T arg) {
		switch (this.variety) {
			case NORMAL:
				if (this.lhs == null) {
					System.err.println("Origins Warn: attrAccessCopy: variety == NORMAL but lhs == null!");
					return arg;
				}
				if (!(this.lhs instanceof Node)) {
					System.err.println("Origins Warn: attrAccessCopy: lhs not instanceof Node!");
					return arg;
				}
				return (T)arg.copy(this.lhs, ConsCell.nil);

			default:
				return arg;
		}
	}

	public Object attrAccessCopyPoly(Object arg) {
		if (arg instanceof Node) return attrAccessCopy((Node)arg);
		return arg;
	}
}