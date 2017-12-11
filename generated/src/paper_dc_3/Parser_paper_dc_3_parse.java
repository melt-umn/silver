/*
 * Built at Mon Dec 11 16:01:57 UTC 2017
 * by Copper version 0.7.2,
 *      build 20170816-1437
 */
package paper_dc_3;


import java.util.ArrayList;
import java.util.List;


public class Parser_paper_dc_3_parse extends edu.umn.cs.melt.copper.runtime.engines.single.SingleDFAEngine<paper_dc_3.NRoot_c,edu.umn.cs.melt.copper.runtime.logging.CopperParserException>
{
    protected String formatError(String error)
    {
    	   String location = "";
        location += "line " + virtualLocation.getLine() + ", column " + virtualLocation.getColumn();
        if(currentState.pos.getFileName().length() > 40) location += "\n         ";
        location += " in file " + virtualLocation.getFileName();
        location += "\n         (parser state: " + currentState.statenum + "; real character index: " + currentState.pos.getPos() + ")";
        return "Error at " + location + ":\n  " + error;
    }
    protected void reportError(String message)
    throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
    {
        throw new edu.umn.cs.melt.copper.runtime.logging.CopperParserException(message);
    }
    protected void reportSyntaxError()
    throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
    {
    java.util.ArrayList<String> expectedTerminalsReal = bitVecToRealStringList(getShiftableSets()[currentState.statenum]);
    java.util.ArrayList<String> expectedTerminalsDisplay = bitVecToDisplayStringList(getShiftableSets()[currentState.statenum]);
    java.util.ArrayList<String> matchedTerminalsReal = bitVecToRealStringList(disjointMatch.terms);
    java.util.ArrayList<String> matchedTerminalsDisplay = bitVecToDisplayStringList(disjointMatch.terms);
    throw new edu.umn.cs.melt.copper.runtime.logging.CopperSyntaxError(virtualLocation,currentState.pos,currentState.statenum,expectedTerminalsReal,expectedTerminalsDisplay,matchedTerminalsReal,matchedTerminalsDisplay);
    }
    public static enum Terminals implements edu.umn.cs.melt.copper.runtime.engines.CopperTerminalEnum
    {
        paper_dc_3_Dash_t(1),
        paper_dc_3_Hat_t(2),
        paper_dc_3_IntLit_t(3),
        paper_dc_3_LParen_t(4),
        paper_dc_3_LineComment_P(5),
        paper_dc_3_Plus_t(6),
        paper_dc_3_RParen_t(7),
        paper_dc_3_Slash_t(8),
        paper_dc_3_Star_t(9),
        paper_dc_3_WhiteSpace_t(10);

        private final int num;
        Terminals(int num) { this.num = num; }
        public int num() { return num; }
    }

    public void pushToken(Terminals t,String lexeme)
    {
        java.util.BitSet ts = new java.util.BitSet();
        ts.set(t.num());
        tokenBuffer.offer(new edu.umn.cs.melt.copper.runtime.engines.single.scanner.SingleDFAMatchData(ts,currentState.pos,currentState.pos,lexeme,new java.util.LinkedList<edu.umn.cs.melt.copper.runtime.engines.single.scanner.SingleDFAMatchData>()));
    }
    public void setupEngine()
    {
    }
    public int transition(int state,char ch)
    {
         return delta[state][cmap[ch]];
    }
    public class Semantics extends edu.umn.cs.melt.copper.runtime.engines.single.semantics.SingleDFASemanticActionContainer<edu.umn.cs.melt.copper.runtime.logging.CopperParserException>
    {
        public common.DecoratedNode context;

        public Semantics()
        throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            runInit();
        }

        public void error(edu.umn.cs.melt.copper.runtime.io.InputPosition pos,java.lang.String message)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            reportError("Error at " + pos.toString() + ":\n  " + message);
        }

        public void runDefaultTermAction()
        throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
        }
        public void runDefaultProdAction()
        throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
        }
        public void runInit()
        throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            
        reset();
                  context = common.TopNode.singleton;
        }
        public java.lang.Object runSemanticAction(edu.umn.cs.melt.copper.runtime.io.InputPosition _pos,java.lang.Object[] _children,int _prod)
        throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            this._pos = _pos;
            this._children = _children;
            this._prod = _prod;
            this._specialAttributes = new edu.umn.cs.melt.copper.runtime.engines.semantics.SpecialParserAttributes(virtualLocation);
            java.lang.Object RESULT = null;
            switch(_prod)
            {
            case 17:
                RESULT = runSemanticAction_17();
                break;
            case 18:
                RESULT = runSemanticAction_18();
                break;
            case 19:
                RESULT = runSemanticAction_19();
                break;
            case 20:
                RESULT = runSemanticAction_20();
                break;
            case 21:
                RESULT = runSemanticAction_21();
                break;
            case 22:
                RESULT = runSemanticAction_22();
                break;
            case 23:
                RESULT = runSemanticAction_23();
                break;
            case 24:
                RESULT = runSemanticAction_24();
                break;
            case 25:
                RESULT = runSemanticAction_25();
                break;
            default:
        runDefaultProdAction();
                 break;
            }
            return RESULT;
        }
        public java.lang.Object runSemanticAction(edu.umn.cs.melt.copper.runtime.io.InputPosition _pos,edu.umn.cs.melt.copper.runtime.engines.single.scanner.SingleDFAMatchData _terminal)
        throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            this._pos = _pos;
            this._terminal = _terminal;
            this._specialAttributes = new edu.umn.cs.melt.copper.runtime.engines.semantics.SpecialParserAttributes(virtualLocation);
            String lexeme = _terminal.lexeme;
            java.lang.Object RESULT = null;
            switch(_terminal.firstTerm)
            {
            case 1:
                RESULT = runSemanticAction_1(lexeme);
                break;
            case 2:
                RESULT = runSemanticAction_2(lexeme);
                break;
            case 3:
                RESULT = runSemanticAction_3(lexeme);
                break;
            case 4:
                RESULT = runSemanticAction_4(lexeme);
                break;
            case 5:
                RESULT = runSemanticAction_5(lexeme);
                break;
            case 6:
                RESULT = runSemanticAction_6(lexeme);
                break;
            case 7:
                RESULT = runSemanticAction_7(lexeme);
                break;
            case 8:
                RESULT = runSemanticAction_8(lexeme);
                break;
            case 9:
                RESULT = runSemanticAction_9(lexeme);
                break;
            case 10:
                RESULT = runSemanticAction_10(lexeme);
                break;
            default:
        runDefaultTermAction();
                 break;
            }
            return RESULT;
        }
        public paper_dc_3.NExpr_c runSemanticAction_17()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            paper_dc_3.NExpr_c RESULT = null;
            
RESULT = new paper_dc_3.Padd_c(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public paper_dc_3.NExpr_c runSemanticAction_18()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            paper_dc_3.NExpr_c RESULT = null;
            
RESULT = new paper_dc_3.PexprTerm_c(_children[0]);

            return RESULT;
        }
        public paper_dc_3.NExpr_c runSemanticAction_19()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            paper_dc_3.NExpr_c RESULT = null;
            
RESULT = new paper_dc_3.Psub_c(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public paper_dc_3.NFactor_c runSemanticAction_20()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            paper_dc_3.NFactor_c RESULT = null;
            
RESULT = new paper_dc_3.Pconst_c(_children[0]);

            return RESULT;
        }
        public paper_dc_3.NFactor_c runSemanticAction_21()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            paper_dc_3.NFactor_c RESULT = null;
            
RESULT = new paper_dc_3.Pnested_c(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public paper_dc_3.NRoot_c runSemanticAction_22()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            paper_dc_3.NRoot_c RESULT = null;
            
RESULT = new paper_dc_3.Proot_c(_children[0]);

            return RESULT;
        }
        public paper_dc_3.NTerm_c runSemanticAction_23()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            paper_dc_3.NTerm_c RESULT = null;
            
RESULT = new paper_dc_3.Pmul_c(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public paper_dc_3.NTerm_c runSemanticAction_24()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            paper_dc_3.NTerm_c RESULT = null;
            
RESULT = new paper_dc_3.Pneg_c(_children[0], _children[1]);

            return RESULT;
        }
        public paper_dc_3.NTerm_c runSemanticAction_25()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            paper_dc_3.NTerm_c RESULT = null;
            
RESULT = new paper_dc_3.PtermFactor_c(_children[0]);

            return RESULT;
        }
        public paper_dc_3.TDash_t runSemanticAction_1(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            paper_dc_3.TDash_t RESULT = null;
            
RESULT = new paper_dc_3.TDash_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public paper_dc_3.THat_t runSemanticAction_2(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            paper_dc_3.THat_t RESULT = null;
            
RESULT = new paper_dc_3.THat_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public paper_dc_3.TIntLit_t runSemanticAction_3(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            paper_dc_3.TIntLit_t RESULT = null;
            
RESULT = new paper_dc_3.TIntLit_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public paper_dc_3.TLParen_t runSemanticAction_4(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            paper_dc_3.TLParen_t RESULT = null;
            
RESULT = new paper_dc_3.TLParen_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public paper_dc_3.TLineComment_P runSemanticAction_5(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            paper_dc_3.TLineComment_P RESULT = null;
            
RESULT = new paper_dc_3.TLineComment_P(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public paper_dc_3.TPlus_t runSemanticAction_6(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            paper_dc_3.TPlus_t RESULT = null;
            
RESULT = new paper_dc_3.TPlus_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public paper_dc_3.TRParen_t runSemanticAction_7(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            paper_dc_3.TRParen_t RESULT = null;
            
RESULT = new paper_dc_3.TRParen_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public paper_dc_3.TSlash_t runSemanticAction_8(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            paper_dc_3.TSlash_t RESULT = null;
            
RESULT = new paper_dc_3.TSlash_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public paper_dc_3.TStar_t runSemanticAction_9(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            paper_dc_3.TStar_t RESULT = null;
            
RESULT = new paper_dc_3.TStar_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public paper_dc_3.TWhiteSpace_t runSemanticAction_10(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            paper_dc_3.TWhiteSpace_t RESULT = null;
            
RESULT = new paper_dc_3.TWhiteSpace_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public int runDisambiguationAction(edu.umn.cs.melt.copper.runtime.io.InputPosition _pos,edu.umn.cs.melt.copper.runtime.engines.single.scanner.SingleDFAMatchData match)
        throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            return -1;
        }
    }
    public Semantics semantics;
    public java.lang.Object runSemanticAction(edu.umn.cs.melt.copper.runtime.io.InputPosition _pos,java.lang.Object[] _children,int _prod)
    throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
    {
        return semantics.runSemanticAction(_pos,_children,_prod);
    }
    public java.lang.Object runSemanticAction(edu.umn.cs.melt.copper.runtime.io.InputPosition _pos,edu.umn.cs.melt.copper.runtime.engines.single.scanner.SingleDFAMatchData _terminal)
    throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
    {
        return semantics.runSemanticAction(_pos,_terminal);
    }
    public int runDisambiguationAction(edu.umn.cs.melt.copper.runtime.io.InputPosition _pos,edu.umn.cs.melt.copper.runtime.engines.single.scanner.SingleDFAMatchData matches)
    throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
    {
        return semantics.runDisambiguationAction(_pos,matches);
    }
    public edu.umn.cs.melt.copper.runtime.engines.semantics.SpecialParserAttributes getSpecialAttributes()
    {
        return semantics.getSpecialAttributes();
    }
    public void startEngine(edu.umn.cs.melt.copper.runtime.io.InputPosition initialPos)
    throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
    {
         super.startEngine(initialPos);
         semantics = new Semantics();
    }

public static final byte[] symbolNamesHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\145\320\315\112\003\061" +
"\020\007\360\240\324\263\132\104\021\174\205\136\074\172\022\155" +
"\125\130\160\331\054\172\360\020\246\331\141\273\262\233\204\144" +
"\126\012\276\222\276\214\057\041\036\174\007\233\322\312\154\172" +
"\375\315\327\077\371\370\025\243\336\213\361\113\366\012\157\060" +
"\151\301\324\023\111\276\061\365\325\347\327\323\367\317\305\373" +
"\335\236\020\113\047\204\070\047\261\077\175\234\221\070\162\340" +
"\320\253\112\253\113\165\013\141\241\210\304\041\263\173\240\110" +
"\143\106\017\206\262\146\107\263\034\074\232\250\147\134\033\203" +
"\067\266\353\320\220\312\207\327\362\266\017\351\222\342\177\311" +
"\061\123\331\156\202\361\161\111\340\243\235\062\173\136\064\204" +
"\322\201\306\130\031\311\362\272\050\207\123\323\245\363\112\017" +
"\217\316\100\223\135\053\357\054\254\245\324\112\364\135\264\203" +
"\365\346\174\370\123\120\125\261\166\302\010\127\327\266\063\274" +
"\065\364\363\110\374\215\332\232\100\151\062\203\201\260\112\123" +
"\370\115\062\276\261\353\333\224\014\326\221\370\377\320\052\313" +
"\366\265\177\044\336\145\261\055\002\000\000"
});

public static final byte[] symbolDisplayNamesHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\145\320\315\112\003\061" +
"\020\007\360\264\340\135\055\242\010\336\074\367\342\315\036\375" +
"\206\075\054\135\321\203\260\303\064\073\154\127\166\223\220\314" +
"\112\301\127\322\227\361\045\304\203\357\140\043\255\314\306\353" +
"\057\223\231\377\314\333\267\332\351\275\232\074\145\317\370\202" +
"\323\026\115\075\055\330\067\246\236\275\177\074\174\176\235\274" +
"\336\214\225\132\071\245\324\061\253\321\051\253\075\207\216\074" +
"\124\032\316\316\057\061\054\201\131\355\012\273\105\216\064\021" +
"\164\147\070\153\376\151\226\243\047\023\365\110\152\143\350\302" +
"\166\035\031\206\174\070\055\157\373\220\066\231\377\065\331\027" +
"\132\264\233\140\362\173\301\350\243\035\012\173\134\066\114\205" +
"\103\115\361\145\124\016\177\134\255\234\007\075\034\170\215\232" +
"\355\257\312\312\271\265\234\332\075\371\056\332\270\054\007\027" +
"\002\254\252\350\007\202\150\075\151\133\057\113\103\277\210\044" +
"\166\003\155\115\340\044\025\030\012\114\125\222\000\374\046\225" +
"\354\330\365\155\112\206\352\110\342\056\300\353\054\333\115\177" +
"\000\152\010\263\376\043\002\000\000"
});

public static final byte[] symbolNumbersHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\201\051\332\323\167\127\202\132\331\253\115\113\231\030\030" +
"\052\012\030\030\030\244\030\110\000\012\130\260\003\003\003\023" +
"\020\063\003\061\043\016\032\044\317\010\000\114\374\315\365\203" +
"\000\000\000"
});

public static final byte[] productionLHSsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\201\051\332\323\167\127\202\132\331\253\115\113\231\030\030" +
"\052\012\030\030\030\270\024\030\030\270\201\230\007\011\363\102" +
"\061\037\020\363\303\060\000\002\314\353\116\103\000\000\000"
});

public static final byte[] parseTableHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\201\071\072\332\123\374\373\023\177\311\376\116\033\046\006" +
"\206\212\002\006\006\006\041\240\070\123\264\247\357\256\004\265" +
"\262\127\233\226\302\204\005\030\050\004\245\205\014\165\014\114" +
"\060\223\024\030\300\154\020\315\014\304\054\370\164\002\345\131" +
"\201\230\015\210\331\201\230\003\325\044\044\125\004\115\202\252" +
"\343\304\164\223\003\003\203\010\010\243\253\106\026\307\046\217" +
"\151\022\324\016\122\174\307\005\304\154\120\066\007\212\233\304" +
"\200\042\334\130\164\360\340\063\021\323\115\100\223\044\101\030" +
"\135\005\262\070\066\171\114\223\022\210\261\230\130\067\011\201" +
"\060\026\067\301\305\025\030\030\170\211\062\111\002\204\261\230" +
"\004\027\307\046\217\151\022\324\116\254\041\016\304\174\270\114" +
"\300\151\022\321\251\000\252\016\226\012\370\251\146\222\000\305" +
"\271\105\020\306\106\011\161\121\020\106\127\215\054\216\115\036" +
"\253\111\302\040\214\305\044\270\270\002\261\251\100\320\001\311" +
"\265\110\046\301\305\211\066\111\034\204\261\230\004\027\307\046" +
"\017\003\000\055\052\173\244\135\005\000\000"
});

public static final byte[] shiftableSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\070\332\047\053\261\054\121\257\264\044\063\107\317\051" +
"\263\044\070\265\304\072\127\122\203\345\174\237\113\001\023\003" +
"\103\105\001\003\003\203\120\161\021\203\000\272\252\274\277\035" +
"\165\226\046\253\025\231\031\030\243\031\130\222\062\113\212\113" +
"\030\230\242\275\052\012\200\206\202\150\005\226\255\102\033\113" +
"\047\303\314\140\144\200\202\212\342\102\206\072\006\246\122\020" +
"\311\212\220\140\261\302\045\141\201\103\202\355\061\311\106\045" +
"\223\154\224\042\251\072\160\033\365\210\144\347\122\057\110\310" +
"\222\000\000\301\175\044\167\042\002\000\000"
});

public static final byte[] layoutSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\070\332\047\053\261\054\121\257\264\044\063\107\317\051" +
"\263\044\070\265\304\072\127\122\203\345\174\237\113\001\023\003" +
"\103\105\001\003\003\203\120\161\021\203\000\272\252\274\277\035" +
"\165\226\046\253\025\231\031\030\243\031\130\222\062\113\212\113" +
"\030\230\242\275\052\012\200\206\202\150\005\226\255\102\033\113" +
"\047\303\314\140\144\200\202\212\342\102\206\072\006\246\122\020" +
"\311\212\220\140\121\030\225\030\144\022\000\237\243\127\004\042" +
"\002\000\000"
});

public static final byte[] prefixSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\070\332\047\053\261\054\121\257\264\044\063\107\317\051" +
"\263\044\070\265\304\072\127\122\203\345\174\237\113\001\023\003" +
"\103\105\001\003\003\203\120\161\021\203\000\272\252\274\277\035" +
"\165\226\046\253\025\231\031\030\243\031\130\222\062\113\212\113" +
"\030\230\242\275\052\012\200\206\202\150\005\226\255\102\033\113" +
"\047\303\314\140\144\200\202\212\342\102\206\072\006\246\122\020" +
"\311\072\052\061\210\045\000\042\204\111\304\042\002\000\000"
});

public static final byte[] prefixMapsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\044\072\332\047\053\261\054\121\257\264\044\063\107\317" +
"\051\263\044\070\265\304\132\362\322\273\215\346\317\356\030\061" +
"\061\060\124\024\060\060\060\010\001\025\012\143\121\227\053\251" +
"\301\162\276\317\245\000\246\216\273\000\001\112\013\031\352\030" +
"\230\106\005\151\052\010\000\226\136\303\362\305\001\000\000"
});

public static final byte[] terminalUsesHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\201\051\332\323\167\127\202\132\331\253\115\113\231\030\030" +
"\052\012\030\030\030\270\031\110\000\000\347\337\313\077\107\000" +
"\000\000"
});

public static final byte[] shiftableUnionHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\270" +
"\210\101\040\053\261\054\121\257\264\044\063\107\317\051\263\044" +
"\070\265\044\357\157\107\235\245\311\152\105\146\006\306\150\006" +
"\226\244\314\222\342\022\006\246\150\257\212\202\322\042\060\255" +
"\300\262\125\150\143\351\144\046\006\206\212\002\006\006\006\106" +
"\006\060\140\373\135\001\000\163\274\365\165\121\000\000\000"
});

public static final byte[] acceptSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\070\332\047\053\261\054\121\257\264\044\063\107\317\051" +
"\263\044\070\265\304\072\127\122\203\345\174\237\113\001\023\003" +
"\103\105\001\003\003\003\177\161\021\203\000\272\252\274\277\035" +
"\165\226\046\253\025\231\031\030\243\031\130\222\062\113\212\113" +
"\030\230\242\275\052\012\200\206\202\150\005\226\255\102\033\113" +
"\047\303\314\140\250\050\056\144\250\143\140\052\005\221\254\330" +
"\004\030\031\040\240\001\227\004\013\016\011\046\134\106\261\340" +
"\264\103\000\227\004\007\016\011\106\234\106\071\340\222\140\042" +
"\331\125\270\054\147\120\300\055\001\000\004\331\214\321\307\001" +
"\000\000"
});

public static final byte[] rejectSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\070\332\047\053\261\054\121\257\264\044\063\107\317\051" +
"\263\044\070\265\304\072\127\122\203\345\174\237\113\001\023\003" +
"\103\105\001\003\003\003\177\161\021\203\000\272\252\274\277\035" +
"\165\226\046\253\025\231\031\030\243\031\130\222\062\113\212\113" +
"\030\230\242\275\052\012\200\206\202\150\005\226\255\102\033\113" +
"\047\303\314\140\250\050\056\144\250\143\140\052\005\221\254\303" +
"\137\000\000\260\332\062\361\137\001\000\000"
});

public static final byte[] possibleSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\070\332\047\053\261\054\121\257\264\044\063\107\317\051" +
"\263\044\070\265\304\072\127\122\203\345\174\237\113\001\023\003" +
"\103\105\001\003\003\003\177\161\021\203\000\272\252\274\277\035" +
"\165\226\046\253\025\231\031\030\243\031\130\222\062\113\212\113" +
"\030\230\242\275\052\012\200\206\202\150\005\226\255\102\033\113" +
"\047\303\314\140\250\050\056\144\250\143\140\052\005\221\254\100" +
"\076\043\003\030\260\377\303\041\301\320\200\113\202\005\207\004" +
"\023\056\073\130\160\111\060\010\340\222\340\300\041\301\250\200" +
"\113\207\003\056\011\046\222\135\205\313\162\006\234\226\053\124" +
"\000\000\132\045\123\300\317\001\000\000"
});

public static final byte[] cMapHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\355\316\061\012\302\100" +
"\020\005\320\161\143\114\324\173\170\027\013\117\140\343\021\102" +
"\040\342\205\354\254\054\075\222\340\035\334\100\052\021\331\042" +
"\205\305\133\170\054\063\303\014\377\366\212\172\350\043\035\367" +
"\207\307\151\167\176\336\257\051\342\322\305\042\306\267\055\260" +
"\316\226\037\165\311\136\351\355\137\363\072\033\223\126\131\073" +
"\365\066\323\337\144\253\102\163\345\235\113\372\203\014\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\360\325\033\362\135" +
"\121\015\033\000\004\000"
});

public static final byte[] deltaHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\201\071\072\332\123\374\373\023\177\311\376\116\033\046\006" +
"\206\212\002\006\006\006\176\240\070\123\264\247\357\256\004\265" +
"\262\127\233\226\302\204\171\030\110\004\245\205\014\165\014\114" +
"\110\072\101\154\146\040\146\001\142\126\040\146\003\142\166\040" +
"\346\000\142\116\250\030\027\126\235\344\333\071\320\072\271\361" +
"\211\321\330\265\130\345\210\266\223\227\154\235\344\333\111\232" +
"\116\312\303\226\270\020\342\103\302\350\174\024\114\276\116\000" +
"\366\271\065\230\217\003\000\000"
});

public static void initArrays()
throws java.io.IOException,java.lang.ClassNotFoundException
{
    symbolNames = (String[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(symbolNamesHash);
    symbolDisplayNames = (String[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(symbolDisplayNamesHash);
    symbolNumbers = (int[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(symbolNumbersHash);
    productionLHSs = (int[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(productionLHSsHash);
    parseTable = (int[][]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(parseTableHash);
    shiftableSets = (java.util.BitSet[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(shiftableSetsHash);
    layoutSets = (java.util.BitSet[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(layoutSetsHash);
    prefixSets = (java.util.BitSet[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(prefixSetsHash);
    prefixMaps = (java.util.BitSet[][]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(prefixMapsHash);
    terminalUses = (int[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(terminalUsesHash);
    shiftableUnion = (java.util.BitSet) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(shiftableUnionHash);
    acceptSets = (java.util.BitSet[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(acceptSetsHash);
    rejectSets = (java.util.BitSet[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(rejectSetsHash);
    possibleSets = (java.util.BitSet[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(possibleSetsHash);
    cmap = (int[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(cMapHash);
    delta = (int[][]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(deltaHash);
    }
public Parser_paper_dc_3_parse() {}

		private static int TERMINAL_COUNT;
		private static int GRAMMAR_SYMBOL_COUNT;
		private static int SYMBOL_COUNT;
		private static int PARSER_STATE_COUNT;
		private static int SCANNER_STATE_COUNT;
		private static int DISAMBIG_GROUP_COUNT;
		
		private static int SCANNER_START_STATENUM;
		private static int PARSER_START_STATENUM;
		private static int EOF_SYMNUM;
		private static int EPS_SYMNUM;
		
		private static String[] symbolNames;
		private static String[] symbolDisplayNames;
		private static int[] symbolNumbers;
		private static int[] productionLHSs;
		
		private static int[][] parseTable;
		private static java.util.BitSet[] shiftableSets;
		private static java.util.BitSet[] layoutSets;
		private static java.util.BitSet[] prefixSets;
		private static java.util.BitSet[][] prefixMaps;
		private static int[] terminalUses;
		
		private static java.util.BitSet[] disambiguationGroups;
		
		private static java.util.BitSet shiftableUnion;
		
		private static java.util.BitSet[] acceptSets,rejectSets,possibleSets;
		
		private static int[][] delta;
		private static int[] cmap;
		
		public int getTERMINAL_COUNT() {
			return TERMINAL_COUNT;
		}
		public int getGRAMMAR_SYMBOL_COUNT() {
			return GRAMMAR_SYMBOL_COUNT;
		}
		public int getSYMBOL_COUNT() {
			return SYMBOL_COUNT;
		}
		public int getPARSER_STATE_COUNT() {
			return PARSER_STATE_COUNT;
		}
		public int getSCANNER_STATE_COUNT() {
			return SCANNER_STATE_COUNT;
		}
		public int getDISAMBIG_GROUP_COUNT() {
			return DISAMBIG_GROUP_COUNT;
		}
		public int getSCANNER_START_STATENUM() {
			return SCANNER_START_STATENUM;
		}
		public int getPARSER_START_STATENUM() {
			return PARSER_START_STATENUM;
		}
		public int getEOF_SYMNUM() {
			return EOF_SYMNUM;
		}
		public int getEPS_SYMNUM() {
			return EPS_SYMNUM;
		}
		public String[] getSymbolNames() {
			return symbolNames;
		}
		public String[] getSymbolDisplayNames() {
			return symbolDisplayNames;
		}
		public int[] getSymbolNumbers() {
			return symbolNumbers;
		}
		public int[] getProductionLHSs() {
			return productionLHSs;
		}
		public int[][] getParseTable() {
			return parseTable;
		}
		public java.util.BitSet[] getShiftableSets() {
			return shiftableSets;
		}
		public java.util.BitSet[] getLayoutSets() {
			return layoutSets;
		}
		public java.util.BitSet[] getPrefixSets() {
			return prefixSets;
		}
		public java.util.BitSet[][] getLayoutMaps() {
			return null;
		}
		public java.util.BitSet[][] getPrefixMaps() {
			return prefixMaps;
		}
		public int[] getTerminalUses() {
			return terminalUses;
		}
		public java.util.BitSet[] getDisambiguationGroups() {
			return disambiguationGroups;
		}
		public java.util.BitSet getShiftableUnion() {
			return shiftableUnion;
		}
		public java.util.BitSet[] getAcceptSets() {
			return acceptSets;
		}
		public java.util.BitSet[] getRejectSets() {
			return rejectSets;
		}
		public java.util.BitSet[] getPossibleSets() {
			return possibleSets;
		}
		public int[][] getDelta() {
			return delta;
		}
		public int[] getCmap() {
			return cmap;
		}	
    public paper_dc_3.NRoot_c parse(java.io.Reader input,String inputName)
    throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
    {
    this.charBuffer = edu.umn.cs.melt.copper.runtime.io.ScannerBuffer.instantiate(input);
    setupEngine();
    startEngine(edu.umn.cs.melt.copper.runtime.io.InputPosition.initialPos(inputName));
    paper_dc_3.NRoot_c parseTree = (paper_dc_3.NRoot_c) runEngine();
    return parseTree;
    }


          protected List<common.Terminal> tokenList = null;

          public void reset() {
            tokenList = new ArrayList<common.Terminal>();
          }

          public List<common.Terminal> getTokens() {
            return tokenList; // The way we reset this iterator when parsing again is to create a new list, so this is defacto immutable
          }
        

    static
    {
        TERMINAL_COUNT = 11;
        GRAMMAR_SYMBOL_COUNT = 16;
        SYMBOL_COUNT = 26;
        PARSER_STATE_COUNT = 18;
        SCANNER_STATE_COUNT = 15;
        DISAMBIG_GROUP_COUNT = 1;
        SCANNER_START_STATENUM = 1;
        PARSER_START_STATENUM = 1;
        EOF_SYMNUM = 0;
        EPS_SYMNUM = -1;
        try { initArrays(); }
        catch(java.io.IOException ex) { ex.printStackTrace(); System.exit(1); }
        catch(java.lang.ClassNotFoundException ex) { ex.printStackTrace(); System.exit(1); }
        disambiguationGroups = new java.util.BitSet[1];
    }

}
