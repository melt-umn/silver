grammar silver:compiler:translation:java:core;

aspect production nonterminalDcl
top::AGDcl ::= quals::NTDeclQualifiers 'nonterminal' id::Name tl::BracketedOptTypeExprs nm::NonterminalModifiers ';'
{
  local className :: String = "N" ++ id.name;
  
  local inhVar :: String = "count_inh__ON__" ++ id.name;
  local synVar :: String = "count_syn__ON__" ++ id.name;
  
  local myAnnos :: [NamedSignatureElement] =
    annotationsForNonterminal(nonterminalType(fName, length(tl.types), quals.tracked), top.env);

  local commaIfAnnos :: String = if length(myAnnos)!=0 then "," else "";
  local wantsTracking :: Boolean = typeWantsTracking(nonterminalType(fName, length(tl.types), quals.tracked), top.config, top.env);

  top.initWeaving := s"""
	public static int ${inhVar} = 0;
	public static int ${synVar} = 0;""";
  
  top.genFiles := [pair(className ++ ".java", s"""
package ${makeName(top.grammarName)};

import java.util.*;
import silver.core.*;

public abstract class ${className} extends common.${if wantsTracking then "TrackedNode" else "Node"}${
  (if null(myAnnos) then "" else 
    " implements " ++ implode(", ", map(makeAnnoName, map((.elementName), myAnnos)))
  )} {

	public static final int num_inh_attrs = Init.${inhVar};
	public static final int num_syn_attrs = Init.${synVar};

	public static final String[] occurs_inh = new String[num_inh_attrs];
	public static final String[] occurs_syn = new String[num_syn_attrs];
	public static final LinkedList<common.Decorator> decorators = new LinkedList<common.Decorator>();

	public static final common.Lazy[] defaultSynthesizedAttributes = new common.Lazy[num_syn_attrs];

	protected ${className}(${if wantsTracking then "final NOriginInfo origin"++commaIfAnnos else ""} ${implode(", ", map((.annoSigElem), myAnnos))}) {
		${if wantsTracking then "super(origin)" else ""};
${implode("", map(makeAnnoAssign, myAnnos))}
	}

${implode("", map((.annoDeclElem), myAnnos))}

	@Override
	public final int getNumberOfInhAttrs() {
		return num_inh_attrs;
	}

	@Override
	public final int getNumberOfSynAttrs() {
		return num_syn_attrs;
	}

	@Override
	public final common.Lazy getDefaultSynthesized(final int index) {
		return defaultSynthesizedAttributes[index];
	}

	@Override
	public final String getNameOfInhAttr(final int index) {
		return occurs_inh[index];
	}
	
	@Override
	public final String getNameOfSynAttr(final int index) {
		return occurs_syn[index];
	}

	@Override
	public final String[] getAnnoNames() {
		return new String[]{${implode(", ", map((.annoNameElem), myAnnos))}};
	}
	
	@Override
	public final Object getAnno(final String name) {
		${concat(map((.annoLookupElem), myAnnos))}{
			throw new common.exceptions.SilverInternalError("Invalid annotation " + name);
		}
	}
}
""")];

}
