package silver.driver.util;

public class CApostOps extends common.CollectionAttribute {

	public CApostOps(final int index) {
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
