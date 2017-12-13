package silver.translation.java.core;

public class CAvalueWeaving extends common.CollectionAttribute {

	public CAvalueWeaving(final int index) {
		super(index);
	}

	public Object eval(common.DecoratedNode context) {
		common.StringCatter result = (common.StringCatter)this.getBase().eval(context);
		for(int i = 0; i < this.getPieces().size(); i++){
			result = new common.StringCatter(result, (common.StringCatter)this.getPieces().get(i).eval(context));
		}
		return result;
	}

}
