package edu.umn.cs.melt.ableC.abstractsyntax.env;

public class CAfunctiondefs extends common.CollectionAttribute {

	public CAfunctiondefs(final int index) {
		super(index);
	}

	public Object eval(common.DecoratedNode context) {
		common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
		for(int i = 0; i < this.getPieces().size(); i++){
			result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
		}
		return result;
	}

}
