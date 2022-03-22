grammar silver:compiler:translation:java:core;

aspect production nonterminalDcl
top::AGDcl ::= quals::NTDeclQualifiers 'nonterminal' id::Name tl::BracketedOptTypeExprs nm::NonterminalModifiers ';'
{
  local className :: String = "N" ++ id.name;
  
  local inhVar :: String = "count_inh__ON__" ++ id.name;
  local synVar :: String = "count_syn__ON__" ++ id.name;
  
  local myAnnos :: [NamedSignatureElement] =
    annotationsForNonterminal(nonterminalType(fName, map((.kindrep), tl.types), quals.tracked), top.env);

  local commaIfAnnos :: String = if length(myAnnos)!=0 then "," else "";
  local wantsTracking :: Boolean = typeWantsTracking(nonterminalType(fName, map((.kindrep), tl.types), quals.tracked), top.config, top.env);

  top.initProd := s"\t\tcommon.RTTIManager.registerNonterminal(${className}.nonterminalton);\n\n";
  top.initWeaving := s"""
	public static int ${inhVar} = 0;
	public static int ${synVar} = 0;""";

  local interfaces::[String] = map(makeAnnoName, map((.elementName), myAnnos)) ++ if wantsTracking then ["common.Tracked"] else [];
  
  top.genFiles := [pair(className ++ ".java", s"""
package ${makeName(top.grammarName)};

import java.util.*;
import silver.core.*;

public abstract class ${className} extends common.Node ${
  (if null(interfaces) then "" else 
    " implements " ++ implode(", ", interfaces)
  )} {

	public static final int num_inh_attrs = Init.${inhVar};
	public static final int num_syn_attrs = Init.${synVar};

	public static final String[] occurs_inh = new String[num_inh_attrs];
	public static final String[] occurs_syn = new String[num_syn_attrs];

	public static final common.Lazy[] defaultSynthesizedAttributes = new common.Lazy[num_syn_attrs];

	protected ${className}(${implode(", ",
			(if wantsTracking then ["final NOriginInfo origin"] else []) ++
			map((.annoSigElem), myAnnos))}) {
		${if wantsTracking then "this.origin = origin;" else ""}
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

	public static final common.RTTIManager.Nonterminalton<${className}> nonterminalton = new Nonterminalton();

  public static final class Nonterminalton extends common.RTTIManager.Nonterminalton<${className}> {
      public String getName(){ return "${top.grammarName}:${id.name}"; }
      public String[] getOccursInh() { return ${className}.occurs_inh; }
  }

	${otImpl}
}
""")];

  local otImpl::String = if wantsTracking then s"""
  	protected final silver.core.NOriginInfo origin;

	public final silver.core.NOriginInfo getOrigin() {
		return this.origin;
	}
""" else "";

}
