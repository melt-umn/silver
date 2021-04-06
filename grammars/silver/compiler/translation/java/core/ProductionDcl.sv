grammar silver:compiler:translation:java:core;

import silver:compiler:driver;

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  local className :: String = "P" ++ id.name;

  top.setupInh := body.setupInh;
  top.initProd := s"\t\t${makeName(top.grammarName)}.${className}.initProductionAttributeDefinitions();\n";
  top.postInit := s"\t\tcommon.Decorator.applyDecorators(${fnnt}.decorators, ${className}.class);\n";

  top.initWeaving := s"\tpublic static int ${localVar} = 0;\n";
  top.valueWeaving := body.valueWeaving;

  local localVar :: String = "count_local__ON__" ++ makeIdName(fName);
  local ntName :: String = namedSig.outputElement.typerep.typeName;

  local fnnt :: String = makeNTName(ntName);
  local wantsTracking :: Boolean = typeWantsTracking(namedSig.outputElement.typerep, top.config, top.env);

  local ntDeclPackage :: String = implode(".", init(explode(".", fnnt)));
  local typeNameSnipped :: String = last(explode(":", namedSig.outputElement.typerep.typeName));

  local dupX :: (String ::= NamedSignatureElement String) =
    (\x::NamedSignatureElement gc::String -> 
        (if x.typerep.transType == "Object" then s"(${gc} instanceof common.TrackedNode?((common.TrackedNode)${gc}).duplicate(null, notes):${gc})"
            else if !x.typerep.tracked then gc
            else gc++".duplicate(null, notes)"));

  local dupChild :: (String ::= NamedSignatureElement) =
    (\x::NamedSignatureElement -> dupX(x, s"getChild_${x.elementName}()"));

  local dupAnno :: (String ::= NamedSignatureElement) =
    (\x::NamedSignatureElement -> dupX(x, s"getAnno_${makeIdName(x.elementName)}()"));

  local copyChild :: (String ::= NamedSignatureElement) =
    (\x::NamedSignatureElement -> s"child_${x.elementName}");

  local copyAnno :: (String ::= NamedSignatureElement) =
    (\x::NamedSignatureElement -> s"anno_${makeIdName(x.elementName)}");

  local commaIfKids :: String = if length(namedSig.inputElements)!=0 then "," else "";
  local commaIfAnnos :: String = if length(namedSig.namedInputElements)!=0 then "," else "";
  local commaIfKidsOrAnnos :: String = if length(namedSig.inputElements)!=0 || length(namedSig.namedInputElements)!=0 then "," else "";
  local commaIfKidsAndAnnos :: String = if length(namedSig.inputElements)!=0 && length(namedSig.namedInputElements)!=0 then "," else "";

  local contexts::Contexts = foldContexts(namedSig.contexts);
  contexts.boundVariables = namedSig.freeVariables;

  top.genFiles := [pair(className ++ ".java", s"""
package ${makeName(top.grammarName)};

import silver.core.*;

// ${ntName}
// ${ns.unparse}
public final class ${className} extends ${fnnt} {

${makeIndexDcls(0, namedSig.inputElements)}

    public static final Class<?> childTypes[] = {${implode(",", map(makeChildTypes, namedSig.inputElements))}};

    public static final int num_local_attrs = Init.${localVar};
    public static final String[] occurs_local = new String[num_local_attrs];

    public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[${fnnt}.num_inh_attrs];

    public static final common.Lazy[] synthesizedAttributes = new common.Lazy[${fnnt}.num_syn_attrs];
    public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[${toString(length(namedSig.inputElements))}][];

    public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
    public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

${namedSig.inhOccursIndexDecls}

    public static final int[] childInhContextTypeVars = {${implode(",", namedSig.childTypeVarElems)}};
    public static final int[] localInhContextTypeVars = new int[num_local_attrs];

    static {
${namedSig.childStatic}
    }

    public ${className}(${if wantsTracking then "final NOriginInfo origin"++commaIfKidsOrAnnos else ""} ${namedSig.javaSignature}) {
        super(${if wantsTracking then "origin"++commaIfAnnos else ""}${implode(", ", map((.annoRefElem), namedSig.namedInputElements))});
${implode("", map(makeChildAssign, namedSig.inputElements))}
${contexts.contextInitTrans}
    }

${if !null(namedSig.contexts) then "//no rtConstruct because contexts" else s"""
    public static ${className} rtConstruct(final NOriginInfo origin ${commaIfKidsOrAnnos} ${namedSig.javaSignature}) {
        return new ${className}(${if wantsTracking then "origin"++commaIfKids else ""} ${implode(", ", map((\x::NamedSignatureElement -> "c_"++makeIdName(x.elementName)), namedSig.inputElements))} ${commaIfKidsAndAnnos} ${implode(", ", map((\x::NamedSignatureElement -> "a_"++makeIdName(x.elementName)), namedSig.namedInputElements))});
    }
"""}

${namedSig.childDecls}

${contexts.contextMemberDeclTrans}

	@Override
	public Object getChild(final int index) {
		switch(index) {
${implode("", map(makeChildAccessCase, namedSig.inputElements))}
            default: return null;
        }
    }

    @Override
    public Object getChildLazy(final int index) {
        switch(index) {
${implode("", map(makeChildAccessCaseLazy, namedSig.inputElements))}
            default: return null;
        }
    }

    @Override
    public final int getNumberOfChildren() {
        return ${toString(length(namedSig.inputElements))};
    }

    @Override
    public common.Lazy getSynthesized(final int index) {
        return synthesizedAttributes[index];
    }

    @Override
    public common.Lazy[] getLocalInheritedAttributes(final int key) {
${flatMap(makeInhOccursContextAccess(namedSig.freeVariables, namedSig.contextInhOccurs, "localInhContextTypeVars", "localInheritedAttributes", "getLocal", _), namedSig.inhOccursContextTypes)}
        return localInheritedAttributes[key];
    }

    @Override
    public common.Lazy[] getChildInheritedAttributes(final int key) {
${flatMap(makeInhOccursContextAccess(namedSig.freeVariables, namedSig.contextInhOccurs, "childInhContextTypeVars", "childInheritedAttributes", "getChild", _), namedSig.inhOccursContextTypes)}
        return childInheritedAttributes[key];
    }

    @Override
    public boolean hasForward() {
        return ${(if null(body.uniqueSignificantExpression) then "false" else "true")};
    }

    @Override
    public common.Node evalForward(final common.DecoratedNode context) {
        ${if null(body.uniqueSignificantExpression) 
          then s"throw new common.exceptions.SilverInternalError(\"Production ${fName} erroneously claimed to forward\")"
          else s"return ((common.Node)${head(body.uniqueSignificantExpression).translation}${if wantsTracking && !top.config.noRedex  then s".duplicateForForwarding(context.undecorate(), \"${substitute("\"", "\\\"", hackUnparse(head(body.uniqueSignificantExpression).location))}\")" else ""})"};
    }

    @Override
    public common.Lazy getForwardInheritedAttributes(final int index) {
        return forwardInheritedAttributes[index];
    }

    @Override
    public common.Lazy getLocal(final int key) {
        return localAttributes[key];
    }

    @Override
    public final int getNumberOfLocalAttrs() {
        return num_local_attrs;
    }

    @Override
    public final String getNameOfLocalAttr(final int index) {
        return occurs_local[index];
    }

    @Override
    public String getName() {
        return "${fName}";
    }

    ${otImpl}
    
    @Override
    public final common.TypeRep getType() {
${makeTyVarDecls(2, namedSig.typerep.freeVariables)}
        
        ${implode("\n\t\t", map(makeChildUnify(fName, _), namedSig.inputElements))}
        
        return ${namedSig.outputElement.typerep.transFreshTypeRep};
    }

    static void initProductionAttributeDefinitions() {
${body.translation}
    }

    public static ${className} reify(
        final silver.core.NAST origAST,
        final common.ConsCell rules,
        final common.TypeRep resultType,
        final silver.core.NAST[] childASTs,
        final String[] annotationNames,
        final silver.core.NAST[] annotationASTs) {
        assert annotationNames.length == annotationASTs.length;
${makeAnnoIndexDcls(0, namedSig.namedInputElements)}
${makeTyVarDecls(2, namedSig.typerep.freeVariables)}

        common.TypeRep givenType = ${namedSig.outputElement.typerep.transFreshTypeRep};
        if (!common.TypeRep.unify(resultType, givenType)) {
            throw new common.exceptions.SilverError("reify is constructing " + resultType.toString() + ", but found " + givenType.toString() + " production ${fName} AST.");
        }
        
        if (childASTs.length != ${toString(length(namedSig.inputElements))}) {
            throw new common.exceptions.SilverError("Production ${fName} expected ${toString(length(namedSig.inputElements))} child(ren), but got " + childASTs.length + ".");
        }
        
        String[] expectedAnnotationNames = new String[] {${implode(", ", map((.annoNameElem), annotationsForNonterminal(namedSig.outputElement.typerep, top.env)))}};
        if (!java.util.Arrays.equals(annotationNames, expectedAnnotationNames)) {
            throw new common.exceptions.SilverError("Production ${fName} expected " + common.Util.namesToString(expectedAnnotationNames, "no") + " annotation(s), but got " + common.Util.namesToString(annotationNames, "none") + ".");
        }
        
        ${implode("\n\t\t", map(makeChildReify(fName, length(namedSig.inputElements), _), namedSig.inputElements))}
        ${implode("\n\t\t", map(makeAnnoReify(fName, _), namedSig.namedInputElements))}
    
        ${if !null(namedSig.contexts) then s"""throw new common.exceptions.SilverError("Production ${fName} containes type contexts, which are not supported by reify"); // TODO""" else
            s"""return new ${className}(${if wantsTracking then "new silver.core.PoriginOriginInfo(common.OriginsUtil.SET_FROM_REIFICATION_OIT, origAST, rules, true)"++commaIfKidsOrAnnos else ""} ${namedSig.refInvokeTrans});"""}
    }

	${if null(namedSig.contexts) then s"public static final common.NodeFactory<${fnnt}> factory = new Factory();" else ""}

	public static final class Factory extends common.NodeFactory<${fnnt}> {
${contexts.contextMemberDeclTrans}

		public Factory(${contexts.contextParamTrans}) {
${contexts.contextInitTrans}
		}
	
		@Override
        public final ${fnnt} invoke(final common.OriginContext originCtx, final Object[] children, final Object[] annotations) {
            return new ${className}(${implode(", ", (if wantsTracking then [newConstructionOriginUsingCtxRef] else []) ++ map(\ c::Context -> decorate c with {boundVariables = namedSig.freeVariables;}.contextRefElem, namedSig.contexts) ++ unpackChildren(0, namedSig.inputElements) ++ unpackAnnotations(0, namedSig.namedInputElements))});
        }
		
        @Override
        public final common.AppTypeRep getType() {
${makeTyVarDecls(3, namedSig.typerep.freeVariables)}
			return ${namedSig.typerep.transFreshTypeRep};
		}
		
		@Override
		public final String toString() {
			return "${top.grammarName}:${id.name}";
		}
	};

}
""")];

  local otImpl :: String = if wantsTracking then s"""
    @Override
    public ${fnnt} duplicate(Object redex, Object notes) {
        if (redex == null || ${if top.config.noRedex then "true" else "false"}) {
            return new ${className}(new PoriginOriginInfo(common.OriginsUtil.SET_AT_NEW_OIT, this, notes, true) ${commaIfKids}
                ${implode(", ", map(dupChild, namedSig.inputElements))} ${commaIfAnnos} ${implode(", ", map(dupAnno, namedSig.namedInputElements))});
        } else {
            return new ${className}(new PoriginAndRedexOriginInfo(common.OriginsUtil.SET_AT_NEW_OIT, this, notes, redex, notes, true) ${commaIfKids}
                ${implode(", ", map(dupChild, namedSig.inputElements))} ${commaIfAnnos} ${implode(", ", map(dupAnno, namedSig.namedInputElements))});
        }
    }

    @Override
    public ${fnnt} duplicate(common.OriginContext oc) {
        return this.duplicate(oc.lhs, oc.rulesAsSilverList());
    }

    @Override
    public ${fnnt} copy(Object newRedex, Object newRule) {
        Object origin, originNotes, newlyConstructed;
        Object roi = this.origin;
        if (roi instanceof PoriginOriginInfo) {
            PoriginOriginInfo oi = (PoriginOriginInfo)roi;
            origin = oi.getChild_origin();
            originNotes = oi.getChild_originNotes();
            newlyConstructed = oi.getChild_newlyConstructed();
        } else if (roi instanceof PoriginAndRedexOriginInfo) {
            PoriginAndRedexOriginInfo oi = (PoriginAndRedexOriginInfo)roi;
            origin = oi.getChild_origin();
            originNotes = oi.getChild_originNotes();
            newlyConstructed = oi.getChild_newlyConstructed();
        } else {
            return this;
        }

        if (newRedex instanceof common.DecoratedNode) newRedex = ((common.DecoratedNode)newRedex).undecorate();

        Object redex = ((common.Node)newRedex);
        Object redexNotes = newRule;
        return new ${className}(new PoriginAndRedexOriginInfo(common.OriginsUtil.SET_AT_ACCESS_OIT, origin, originNotes, redex, redexNotes, newlyConstructed) ${commaIfKids}
            ${implode(", ", map(copyChild, namedSig.inputElements))} ${commaIfAnnos} ${implode(", ", map(copyAnno, namedSig.namedInputElements))});
    }

    @Override
    public ${fnnt} duplicateForForwarding(Object redex, String note) {
        return new ${className}(new PoriginOriginInfo(common.OriginsUtil.SET_AT_FORWARDING_OIT, this, new common.ConsCell(new silver.core.PoriginDbgNote(new common.StringCatter(note)), common.ConsCell.nil), true) ${commaIfKids}
            ${implode(", ", map(copyChild, namedSig.inputElements))} ${commaIfAnnos} ${implode(", ", map(copyAnno, namedSig.namedInputElements))});
    }

    """ else "";

  -- main function signature check TODO: this should probably be elsewhere!
  top.errors <-
    if id.name == "main"
    then [err(top.location, "main should be a function!")]
    else [];
}
