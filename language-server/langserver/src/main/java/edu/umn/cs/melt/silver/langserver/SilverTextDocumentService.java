package edu.umn.cs.melt.silver.langserver;

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
import org.eclipse.lsp4j.SemanticTokensParams;
import org.eclipse.lsp4j.TextDocumentContentChangeEvent;
import org.eclipse.lsp4j.jsonrpc.CompletableFutures;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.TextDocumentService;

import silver.compiler.composed.Default.Parser_silver_compiler_composed_Default_svParse;

public class SilverTextDocumentService implements TextDocumentService {
    LanguageClient client;
    Map<String, String> fileContents = new HashMap<>();
    Map<String, Integer> fileVersions = new HashMap<>();

    public void setClient(LanguageClient client) {
        this.client = client;
    }

    @Override
    public void didOpen(DidOpenTextDocumentParams params) {
        //System.err.println("Opened " + params);
        String uri = params.getTextDocument().getUri();
        fileContents.put(uri, params.getTextDocument().getText());
        fileVersions.put(uri, params.getTextDocument().getVersion());
    }

    @Override
    public void didChange(DidChangeTextDocumentParams params) {
        //System.err.println("Changed " + params);
        String uri = params.getTextDocument().getUri();
        for (TextDocumentContentChangeEvent change : params.getContentChanges()) {
            fileContents.put(uri, change.getText());
            fileVersions.put(uri, params.getTextDocument().getVersion());
        }
    }

    @Override
    public void didClose(DidCloseTextDocumentParams params) {
        //System.err.println("Closed " + params);
        fileContents.remove(params.getTextDocument().getUri());
    }

    @Override
    public void didSave(DidSaveTextDocumentParams params) {
        //System.err.println("Saved " + params);
    }

    public static final List<String> tokenTypes = Arrays.asList(new String[] {
        "namespace", "type", "interface", "class", "typeParameter", "parameter", "variable", "function",
        "keyword", "modifier", "comment", "string", "number", "regexp", "operator"
    });
    public static final List<String> tokenModifiers = Arrays.asList(new String[] {
        "declaration", "definition", "documentation", "defaultLibrary"
    });

    private CopperSemanticTokenEncoder<?> semanticTokenEncoder =
        new CopperSemanticTokenEncoder<>(Parser_silver_compiler_composed_Default_svParse::new, tokenTypes, tokenModifiers);

    @Override
    public CompletableFuture<SemanticTokens> semanticTokensFull(SemanticTokensParams params) {
        System.err.println(params);
        String uri = params.getTextDocument().getUri();
        return CompletableFutures.computeAsync((cancelChecker) -> {
            List<Integer> tokens;
            int requestVersion;
            // Recompute tokens if the file is changed while tokens are being computed
            do {
                requestVersion = fileVersions.get(uri);
                if (fileContents.containsKey(uri)) {
                    tokens = semanticTokenEncoder.parseTokens(fileContents.get(uri));
                } else {
                    tokens = new ArrayList<Integer>();
                }
            } while (requestVersion != fileVersions.get(uri));
            return new SemanticTokens(tokens);
        });
    }
}
