grammar silver:translation:java:core;

aspect production noWrapperNonterminalDcl
top::AGDcl ::= quals::NTDeclQualifiers 'nonterminal' id::Name tl::BracketedOptTypeExprs nm::NonterminalModifiers ';'
{
  local className :: String = "N" ++ id.name;
  
  local inhVar :: String = "count_inh__ON__" ++ id.name;
  local synVar :: String = "count_syn__ON__" ++ id.name;
  
  local myAnnos :: [NamedSignatureElement] =
    annotationsForNonterminal(nonterminalType(fName, tl.types), top.env);

  local commaIfAnnos :: String = if length(myAnnos)!=0 then "," else "";
  local wantsTracking :: Boolean = typeWantsTracking(nonterminalType(fName, tl.types), top.config, top.env);
  -- Origins TODO: ^this probably shouldn't be required; c/f the fallback to tracking-mode
  
  top.initWeaving := s"""
	public static int ${inhVar} = 0;
	public static int ${synVar} = 0;""";
  
  top.genFiles := [pair(className ++ ".java", s"""
package ${makeName(top.grammarName)};

import java.util.*;
import core.*;

public abstract class ${className} extends common.Node${
  (if null(myAnnos) then "" else 
    " implements " ++ implode(", ", map(makeAnnoClassName, map((.elementName), myAnnos)))
  )} {

	public static final int num_inh_attrs = Init.${inhVar};
	public static final int num_syn_attrs = Init.${synVar};

	public static final String[] occurs_inh = new String[num_inh_attrs];
	public static final String[] occurs_syn = new String[num_syn_attrs];
	public static final LinkedList<common.Decorator> decorators = new LinkedList<common.Decorator>();

	public static final common.Lazy[] defaultSynthesizedAttributes = new common.Lazy[num_syn_attrs];

	protected ${className}(final NOriginInfo origin ${commaIfAnnos} ${implode(", ", map((.annoSigElem), myAnnos))}) {
		super(${if wantsTracking then "origin" else "null"});
		${if !wantsTracking then "if (origin!=null && System.getProperty(\"silver.origins.rtwarn\")!=null) System.err.println(\"Origins Warn: Threw away OI (\"+getName()+\".<init>/?)\");" else ""}
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
		${sconcat(map((.annoLookupElem), myAnnos))}{
			throw new common.exceptions.SilverInternalError("Invalid annotation " + name);
		}
	}
}
""")];

}
