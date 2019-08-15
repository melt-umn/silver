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
	    NORMAL, MAINFUNCTION, FFI, OTHER
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
				return new core.PoriginOriginInfo(null, this.lhs.wrapInLink(), this.rulesAsSilverList(), false);

			case MAINFUNCTION:
				return new core.PotherOriginInfo(null, new common.StringCatter("Main Function"), ConsCell.nil);

			default:
				return new core.PbogusOriginInfo(null);
		}
	}

	public <T extends Node> T attrAccessCopy(T arg) {
		switch (this.variety) {
			case NORMAL:
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