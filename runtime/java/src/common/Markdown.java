package common;

import common.javainterop.ConsCellCollection;
import java.util.ArrayList;
import java.util.List;
import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.FencedCodeBlock;
import org.commonmark.node.SourceSpan;
import org.commonmark.parser.IncludeSourceSpans;
import org.commonmark.parser.Parser;
import silver.core.Ppair;

/**
 * The Markdown class contains some utilities that are too groty to put inline
 * in the compiler.
 *
 * @author remexre
 */
public final class Markdown {
  public static ConsCell extractCodeBlocks(String markdown) {
    ArrayList<Ppair> out = new ArrayList<Ppair>();
    Parser.builder()
        .includeSourceSpans(IncludeSourceSpans.BLOCKS)
        .build()
        .parse(markdown)
        .accept(new AbstractVisitor() {
          public void visit(FencedCodeBlock node) {
            List<SourceSpan> spans = node.getSourceSpans();
            out.add(Ppair.rtConstruct(
                null, new StringCatter(node.getInfo()),
                Ppair.rtConstruct(null,
                                  new Integer(spans.get(0).getLineIndex()),
                                  new StringCatter(node.getLiteral()))));
          }
        });
    return ConsCellCollection.fromIterator(out.iterator());
  }
}
