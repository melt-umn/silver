package edu.umn.cs.melt.ide.util.cst;

public class DefinitionFinder implements INodeFinder {

	@Override
	public common.Node find(Object root, String[] nodes, int index, int endIndex){
		core.NMaybe maybe = null;
		
		NonTerminalFinder finder = new NonTerminalFinder(nodes, index, endIndex);
		
		Object found = CSTVisitingService.accept(root, finder) ? finder.getFound() : null;
		
		if(found!=null){//We found it!
			//System.out.println("NT=" + getName(found));
			maybe = new core.Pjust(found);
		} else {
		    maybe = new core.Pnothing(); 
		}

		return maybe;
	}

    /*
	private String getName(Object found) {
		if(found instanceof common.Node){
			return ((common.Node)found).getName();
		} else if(found instanceof common.DecoratedNode){
			return "(decorated) " + ((common.DecoratedNode)found).undecorate().getName();
		}
		
		return "<<UNKNOWN>>";
	}
    */
}
