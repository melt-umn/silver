package silver_features;

public class CAcolFun extends common.CollectionAttribute {

	public CAcolFun(final int index) {
		super(index);
	}

	public Object eval(common.DecoratedNode context) {
		core.NMaybe result = (core.NMaybe)this.getBase().eval(context);
		for(int i = 0; i < this.getPieces().size(); i++){
			result = core.PorElse.invoke(result, (core.NMaybe)this.getPieces().get(i).eval(context));
		}
		return result;
	}

}
