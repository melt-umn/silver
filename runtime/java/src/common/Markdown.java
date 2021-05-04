package common;

import common.javainterop.ConsCellCollection;
import java.util.ArrayList;
import java.util.List;
import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.FencedCodeBlock;
import org.commonmark.node.SourceSpan;
import org.commonmark.parser.IncludeSourceSpans;
import org.commonmark.parser.Parser;
import silver.core.Ploc;
import silver.core.Ppair;

/**
 * The Markdown class contains some utilities that are too groty to put inline
 * in the compiler.
 *
 * @author remexre
 */
public final class Markdown {
  public static ConsCell extractCodeBlocks(String path, String markdown) {
    ArrayList<Ppair> out = new ArrayList<Ppair>();
    Parser.builder()
        .includeSourceSpans(IncludeSourceSpans.BLOCKS)
        .build()
        .parse(markdown)
        .accept(new AbstractVisitor() {
          public void visit(FencedCodeBlock node) {
            SourceSpan span = node.getSourceSpans().get(0);

            // TODO: SourceSpan only provides line/column, but it provides a
            // length in UTF-16 code units; do we have a utility for this?
            Ploc location = Ploc.rtConstruct(
                null, path, span.getLineIndex(), span.getColumnIndex(),
                span.getLineIndex(), span.getColumnIndex(), 0, 0);

            out.add(Ppair.rtConstruct(
                null, new StringCatter(node.getInfo()),
                Ppair.rtConstruct(null, location,
                                  new StringCatter(node.getLiteral()))));
          }
        });
    return ConsCellCollection.fromIterator(out.iterator());
  }
}
