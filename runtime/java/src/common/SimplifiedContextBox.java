package common;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class SimplifiedContextBox {

    // 4 sections

    // 1. Tree Order
    public int translation_x;
    public int higher_order_y;

    // 2. Text Syntax
    public String text_syntax;
    public String syntax_to_highlight;

    // 3. Productions Visited
    public Production prods_visited[];

    // 4. Interesting Features
    public Feature features[];
}