package edu.umn.cs.melt.silver.langserver;

import java.util.function.Supplier;

import common.AppTypeRep;
import common.BaseTypeRep;
import common.FunctionTypeRep;
import common.NodeFactory;
import common.OriginContext;
import common.SilverCopperParser;
import common.exceptions.TraceException;
import common.Util;
import silver.core.NParseResult;

/**
* A wrapper to treat a Silver-generated Copper parser as a Silver function.
* 
* TODO: This class should move to a generic utility library for use in
* implementing LSP servers from languages written in Silver.
* 
* @author krame505
* @see https://microsoft.github.io/language-server-protocol/specifications/lsp/3.17/specification/#textDocument_semanticTokens
*/
public class CopperParserNodeFactory extends NodeFactory<NParseResult> {
    private Supplier<? extends SilverCopperParser<?>> parserFactory;

    public CopperParserNodeFactory(Supplier<? extends SilverCopperParser<?>> parserFactory) {
        this.parserFactory = parserFactory;
    }

    @Override
    public AppTypeRep getType() {
        return new AppTypeRep(new AppTypeRep(new AppTypeRep(new FunctionTypeRep(2, new String[] {}), new BaseTypeRep("String")), new BaseTypeRep("String")), new AppTypeRep(new BaseTypeRep("silver:core:ParseResult"), new BaseTypeRep("silver:compiler:definition:core:Root")));
    }

    @Override
    public NParseResult invoke(OriginContext originCtx, Object[] args, Object[] namedArgs) {
		try {
            return Util.callCopperParser(parserFactory.get(), args[0], args[1]);
		} catch(Throwable t) {
			throw new TraceException("Error while invoking Copper parser", t);
		}
    }
    
}
