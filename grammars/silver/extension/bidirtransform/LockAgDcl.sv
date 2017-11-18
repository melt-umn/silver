grammar silver:extension:bidirtransform;

imports silver:translation:java:core only genFiles, setupInh, initProd, initValues, postInit, initWeaving, valueWeaving;
--imports silver:modification:impide only ideSpecs;
imports silver:modification:copper_mda only mdaSpecs;
imports silver:extension:doc:core only docs, docsHeader, docsSplit, docsNoDoc, docDcls;
--imports silver:composed:idetest only foldableRanges;

synthesized attribute isLock::Boolean occurs on Def;

abstract production lockAGDcls
top::AGDcl ::= h::AGDcl t::AGDcl
{
    top.pp = h.pp ++ "\n" ++ t.pp;

    --top.defs = [lockDef()] ++ h.defs ++ [lockDef()] ++ t.defs ++ [lockDef()];
    top.defs = [];

    top.errors := h.errors ++ t.errors;
    top.moduleNames = [];--h.moduleNames ++ t.moduleNames;

    --top.foldableRanges = h.foldableRanges ++ t.foldableRanges;
    -- top.syntaxAst = h.syntaxAst ++ t.syntaxAst;
    -- top.parserSpecs = h.parserSpecs ++ t.parserSpecs;
    -- top.flowDefs = h.flowDefs ++ t.flowDefs;
    -- top.docs := h.docs ++ t.docs;
    -- top.docsHeader = if "" == h.docsHeader
    --                 then t.docsHeader
    --                 else h.docsHeader;

    -- top.docsSplit = if "" == h.docsSplit
    --                 then t.docsSplit
    --                 else h.docsSplit;

    -- top.docsNoDoc = h.docsNoDoc || t.docsNoDoc;
    -- top.docDcls := h.docDcls ++ t.docDcls;
    -- top.mdaSpecs = h.mdaSpecs ++ t.mdaSpecs;
    --top.ideSpecs = h.ideSpecs ++ t.ideSpecs;
    -- top.genFiles := h.genFiles ++ t.genFiles;
    -- top.setupInh := h.setupInh ++ t.setupInh;
    -- top.initProd := h.initProd ++ t.initProd;
    -- top.initValues := h.initValues ++ t.initValues;
    -- top.postInit := h.postInit ++ t.postInit;
    -- top.initWeaving := h.initWeaving ++ t.initWeaving;
    -- top.valueWeaving := h.valueWeaving ++ t.valueWeaving;
}

aspect default production
top::Def ::= 
{
    top.isLock = false;
}

abstract production lockDef
top::Def ::=
{
    top.isLock = true;
}

-- -- Also could try this:
-- abstract production lockDefs
-- top::Defs ::= a::Defs b::Defs
--{
--  top.isLock = true
--  forwards to cons(a,b);
--}