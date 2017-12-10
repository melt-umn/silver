package edu.umn.cs.melt.ableC.abstractsyntax.env;

public class CAlocaldefs extends common.CollectionAttribute {

	public CAlocaldefs(final int index) {
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
