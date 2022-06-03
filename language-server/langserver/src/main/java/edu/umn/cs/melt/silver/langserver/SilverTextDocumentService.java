package edu.umn.cs.melt.silver.langserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.eclipse.lsp4j.DidChangeTextDocumentParams;
import org.eclipse.lsp4j.DidCloseTextDocumentParams;
import org.eclipse.lsp4j.DidOpenTextDocumentParams;
import org.eclipse.lsp4j.DidSaveTextDocumentParams;
import org.eclipse.lsp4j.SemanticTokens;
import org.eclipse.lsp4j.SemanticTokensDelta;
import org.eclipse.lsp4j.SemanticTokensDeltaParams;
import org.eclipse.lsp4j.SemanticTokensEdit;
import org.eclipse.lsp4j.SemanticTokensParams;
import org.eclipse.lsp4j.TextDocumentContentChangeEvent;
import org.eclipse.lsp4j.TextDocumentIdentifier;
import org.eclipse.lsp4j.TextDocumentItem;
import org.eclipse.lsp4j.jsonrpc.CompletableFutures;
import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.eclipse.lsp4j.services.TextDocumentService;

import silver.compiler.composed.Default.Parser_silver_compiler_composed_Default_svParse;

public class SilverTextDocumentService implements TextDocumentService {
    Map<String, String> fileContents = new HashMap<>();

    @Override
    public void didOpen(DidOpenTextDocumentParams didOpenTextDocumentParams) {
        System.err.println("Opened " + didOpenTextDocumentParams);
        fileContents.put(didOpenTextDocumentParams.getTextDocument().getUri(), didOpenTextDocumentParams.getTextDocument().getText());
    }

    @Override
    public void didChange(DidChangeTextDocumentParams didChangeTextDocumentParams) {
        System.err.println("Changed " + didChangeTextDocumentParams);
        for (TextDocumentContentChangeEvent change : didChangeTextDocumentParams.getContentChanges()) {
            fileContents.put(didChangeTextDocumentParams.getTextDocument().getUri(), change.getText());
        }
    }

    @Override
    public void didClose(DidCloseTextDocumentParams didCloseTextDocumentParams) {
        //System.err.println("Closed " + didCloseTextDocumentParams);
        fileContents.remove(didCloseTextDocumentParams.getTextDocument().getUri());
    }

    @Override
    public void didSave(DidSaveTextDocumentParams didSaveTextDocumentParams) {
        //System.err.println("Saved " + didSaveTextDocumentParams);
    }

    public static final List<String> tokenTypes = Arrays.asList(new String[] {
        "namespace", "type", "interface", "typeParameter", "parameter", "variable", "function",
        "keyword", "modifier", "comment", "string", "number", "regexp", "operator"
    });
    public static final List<String> tokenModifiers = Arrays.asList(new String[] {
        "declaration", "definition", "documentation", "defaultLibrary"
    });

    private CopperSemanticTokenEncoder<?> semanticTokenEncoder =
        new CopperSemanticTokenEncoder<>(new Parser_silver_compiler_composed_Default_svParse(), tokenTypes, tokenModifiers);
    
    private SemanticTokens getSemanticTokens(TextDocumentIdentifier document) {
        String uri = document.getUri();
        List<Integer> tokens;
        if (fileContents.containsKey(uri)) {
            tokens = semanticTokenEncoder.parseTokens(fileContents.get(uri));
        } else {
            tokens = new ArrayList<Integer>();
        }
        System.err.println("Tokens for " + uri + ": " + tokens);
        return new SemanticTokens(tokens);
    }

    @Override
    public CompletableFuture<SemanticTokens> semanticTokensFull(SemanticTokensParams params) {
        System.err.println(params);
        return CompletableFutures.computeAsync((cancelChecker) -> getSemanticTokens(params.getTextDocument()));
    }
}
