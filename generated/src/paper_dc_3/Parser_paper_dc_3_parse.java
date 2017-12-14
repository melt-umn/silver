/*
 * Built at Thu Dec 14 15:58:27 UTC 2017
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
        paper_dc_3_Square_t(9),
        paper_dc_3_Star_t(10),
        paper_dc_3_WhiteSpace_t(11);

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
            case 26:
                RESULT = runSemanticAction_26();
                break;
            case 27:
                RESULT = runSemanticAction_27();
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
            case 11:
                RESULT = runSemanticAction_11(lexeme);
                break;
            default:
        runDefaultTermAction();
                 break;
            }
            return RESULT;
        }
        public paper_dc_3.NExpr_c runSemanticAction_18()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            paper_dc_3.NExpr_c RESULT = null;
            
RESULT = new paper_dc_3.Padd_c(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public paper_dc_3.NExpr_c runSemanticAction_19()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            paper_dc_3.NExpr_c RESULT = null;
            
RESULT = new paper_dc_3.PexprTerm_c(_children[0]);

            return RESULT;
        }
        public paper_dc_3.NExpr_c runSemanticAction_20()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            paper_dc_3.NExpr_c RESULT = null;
            
RESULT = new paper_dc_3.Psub_c(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public paper_dc_3.NFactor_c runSemanticAction_21()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            paper_dc_3.NFactor_c RESULT = null;
            
RESULT = new paper_dc_3.Pconst_c(_children[0]);

            return RESULT;
        }
        public paper_dc_3.NFactor_c runSemanticAction_22()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            paper_dc_3.NFactor_c RESULT = null;
            
RESULT = new paper_dc_3.Pnested_c(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public paper_dc_3.NFactor_c runSemanticAction_23()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            paper_dc_3.NFactor_c RESULT = null;
            
RESULT = new paper_dc_3.Psquare_c(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public paper_dc_3.NRoot_c runSemanticAction_24()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            paper_dc_3.NRoot_c RESULT = null;
            
RESULT = new paper_dc_3.Proot_c(_children[0]);

            return RESULT;
        }
        public paper_dc_3.NTerm_c runSemanticAction_25()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            paper_dc_3.NTerm_c RESULT = null;
            
RESULT = new paper_dc_3.Pmul_c(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public paper_dc_3.NTerm_c runSemanticAction_26()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            paper_dc_3.NTerm_c RESULT = null;
            
RESULT = new paper_dc_3.Pneg_c(_children[0], _children[1]);

            return RESULT;
        }
        public paper_dc_3.NTerm_c runSemanticAction_27()
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
        public paper_dc_3.TSquare_t runSemanticAction_9(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            paper_dc_3.TSquare_t RESULT = null;
            
RESULT = new paper_dc_3.TSquare_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public paper_dc_3.TStar_t runSemanticAction_10(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            paper_dc_3.TStar_t RESULT = null;
            
RESULT = new paper_dc_3.TStar_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public paper_dc_3.TWhiteSpace_t runSemanticAction_11(final String lexeme)
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
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\145\320\301\112\304\060" +
"\020\006\340\240\254\147\165\021\075\350\053\354\305\243\047\321" +
"\135\025\012\226\246\350\301\103\030\323\320\255\264\111\114\046" +
"\262\340\053\351\313\370\022\342\301\167\260\251\273\062\215\327" +
"\257\223\177\376\316\333\067\233\004\307\246\017\331\023\274\300" +
"\254\005\135\317\070\272\106\327\147\357\037\167\237\137\047\257" +
"\127\133\214\255\054\143\354\030\331\366\374\166\201\154\317\202" +
"\125\116\124\122\234\212\113\360\113\201\310\166\211\135\003\106" +
"\232\022\272\321\230\065\377\064\313\301\051\035\365\210\152\243" +
"\325\205\351\072\245\121\344\343\155\171\033\174\032\122\374\205" +
"\354\023\345\355\272\030\035\345\317\241\237\215\112\103\071\202" +
"\213\166\110\354\176\331\240\342\026\344\060\075\341\345\171\121" +
"\216\137\315\127\326\011\071\316\137\200\104\063\050\235\054\214" +
"\301\324\112\345\272\150\073\103\162\076\276\037\124\125\374\166" +
"\100\110\365\333\066\157\350\250\017\217\221\350\237\113\243\075" +
"\246\315\264\362\250\252\124\375\357\075\222\156\156\335\227\356" +
"\351\102\233\222\126\165\044\172\065\354\033\156\156\360\003\351" +
"\135\052\035\131\002\000\000"
});

public static final byte[] symbolDisplayNamesHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\145\320\277\116\303\060" +
"\020\006\160\267\022\073\120\041\030\140\143\356\302\006\043\377" +
"\245\014\121\215\350\200\024\353\160\254\064\050\261\215\175\106" +
"\225\170\045\170\031\136\002\061\360\016\304\245\105\147\263\376" +
"\364\371\356\363\275\175\263\255\340\330\344\241\170\202\027\230" +
"\166\240\233\051\107\327\352\346\354\375\343\376\363\353\350\365" +
"\172\314\330\322\062\306\016\221\215\216\221\355\130\260\312\211" +
"\132\212\223\323\013\360\013\201\310\266\211\335\000\106\232\020" +
"\272\325\130\264\377\264\050\301\051\035\365\200\152\253\325\271" +
"\351\173\245\121\224\351\266\262\013\076\037\062\373\033\262\113" +
"\224\167\353\142\064\312\237\303\220\215\112\207\162\004\027\155" +
"\237\330\174\321\242\342\026\344\052\075\252\322\027\227\113\353" +
"\204\114\147\137\201\104\263\122\232\234\031\203\271\335\051\327" +
"\107\033\127\125\162\067\001\165\035\175\217\220\032\066\155\362" +
"\064\352\303\143\044\362\143\041\215\366\230\265\022\132\171\124" +
"\165\256\376\367\016\151\057\341\326\135\351\236\076\164\071\151" +
"\325\104\042\327\022\070\064\334\374\377\007\031\061\213\140\117" +
"\002\000\000"
});

public static final byte[] symbolNumbersHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\201\051\332\323\167\127\202\132\331\253\115\113\231\030\030" +
"\052\012\030\030\030\144\030\110\004\012\130\260\003\003\003\023" +
"\020\063\003\061\043\032\315\202\304\006\251\141\004\000\147\136" +
"\336\115\213\000\000\000"
});

public static final byte[] productionLHSsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\201\051\332\323\167\127\202\132\331\253\115\113\231\030\030" +
"\052\012\030\030\030\270\025\030\030\170\200\230\027\011\363\041" +
"\141\176\040\026\200\141\000\072\267\317\336\107\000\000\000"
});

public static final byte[] parseTableHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\255\224\071\016\302\060" +
"\020\105\107\141\337\267\020\266\046\025\247\240\240\245\100\034" +
"\040\015\107\000\044\020\025\075\025\247\240\243\242\344\074\124" +
"\110\324\264\314\040\133\212\355\061\070\050\221\276\342\374\174" +
"\075\177\313\116\056\117\310\155\067\220\211\242\131\357\165\137" +
"\014\116\307\211\007\260\137\001\100\200\276\027\315\346\267\345" +
"\170\367\270\236\245\335\202\024\256\355\032\016\340\111\132\010" +
"\237\061\335\063\250\254\236\106\057\247\075\347\121\005\124\021" +
"\125\122\151\261\224\023\115\170\145\263\333\024\240\113\322\323" +
"\272\317\145\114\232\230\047\351\112\053\250\202\030\133\126\052" +
"\336\126\155\035\176\167\303\025\364\221\120\143\250\365\277\150" +
"\043\222\236\320\175\056\143\322\226\256\005\134\273\165\110\114" +
"\067\305\017\001\032\116\264\041\211\241\051\076\227\061\151\142" +
"\136\166\027\120\115\033\341\053\055\351\171\153\205\326\363\226" +
"\224\046\074\111\153\247\112\353\244\362\325\373\162\254\354\151" +
"\100\322\323\272\317\145\114\232\230\307\266\247\326\377\006\117" +
"\303\071\375\151\254\165\254\213\342\207\256\247\267\115\142\150" +
"\212\357\114\033\220\030\232\342\163\031\226\326\043\061\064\305" +
"\347\062\362\172\003\306\155\340\366\335\006\000\000"
});

public static final byte[] shiftableSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\070\332\047\053\261\054\121\257\264\044\063\107\317\051" +
"\263\044\070\265\304\072\127\122\203\345\174\237\113\001\023\003" +
"\103\105\001\003\003\203\130\161\021\203\000\272\252\274\277\035" +
"\165\226\046\253\025\231\031\030\243\031\130\222\062\113\212\113" +
"\030\230\242\275\052\012\200\206\202\150\005\226\255\102\033\113" +
"\047\303\314\140\144\200\202\212\342\102\206\072\006\246\122\020" +
"\311\212\220\340\262\302\045\141\201\103\202\347\061\251\106\161" +
"\030\340\222\110\046\325\016\016\105\122\165\340\066\352\021\311" +
"\101\102\272\004\311\201\210\323\125\244\173\020\237\004\000\242" +
"\254\263\274\206\002\000\000"
});

public static final byte[] layoutSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\070\332\047\053\261\054\121\257\264\044\063\107\317\051" +
"\263\044\070\265\304\072\127\122\203\345\174\237\113\001\023\003" +
"\103\105\001\003\003\203\130\161\021\203\000\272\252\274\277\035" +
"\165\226\046\253\025\231\031\030\243\031\130\222\062\113\212\113" +
"\030\230\242\275\052\012\200\206\202\150\005\226\255\102\033\113" +
"\047\303\314\140\144\200\202\212\342\102\206\072\006\246\122\020" +
"\311\212\220\340\120\030\225\030\201\022\000\133\355\214\102\206" +
"\002\000\000"
});

public static final byte[] prefixSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\070\332\047\053\261\054\121\257\264\044\063\107\317\051" +
"\263\044\070\265\304\072\127\122\203\345\174\237\113\001\023\003" +
"\103\105\001\003\003\203\130\161\021\203\000\272\252\274\277\035" +
"\165\226\046\253\025\231\031\030\243\031\130\222\062\113\212\113" +
"\030\230\242\275\052\012\200\206\202\150\005\226\255\102\033\113" +
"\047\303\314\140\144\200\202\212\342\102\206\072\006\246\122\020" +
"\311\072\052\061\302\045\000\004\054\361\042\206\002\000\000"
});

public static final byte[] prefixMapsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\044\072\332\047\053\261\054\121\257\264\044\063\107\317" +
"\051\263\044\070\265\304\132\362\322\273\215\346\317\356\030\061" +
"\061\060\124\024\060\060\060\210\001\025\012\143\121\227\053\251" +
"\301\162\276\317\245\000\246\216\247\000\011\224\026\062\324\061" +
"\060\215\212\016\017\121\000\235\101\251\053\057\002\000\000"
});

public static final byte[] terminalUsesHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\201\051\332\323\167\127\202\132\331\253\115\113\231\030\030" +
"\052\012\030\030\030\170\030\110\004\000\323\066\073\150\113\000" +
"\000\000"
});

public static final byte[] shiftableUnionHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\270" +
"\210\101\040\053\261\054\121\257\264\044\063\107\317\051\263\044" +
"\070\265\044\357\157\107\235\245\311\152\105\146\006\306\150\006" +
"\226\244\314\222\342\022\006\246\150\257\212\202\322\042\060\255" +
"\300\262\125\150\143\351\144\046\006\206\212\002\006\006\006\106" +
"\006\060\340\373\135\001\000\313\355\346\173\121\000\000\000"
});

public static final byte[] acceptSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\070\332\047\053\261\054\121\257\264\044\063\107\317\051" +
"\263\044\070\265\304\072\127\122\203\345\174\237\113\001\023\003" +
"\103\105\001\003\003\203\140\161\021\203\000\272\252\274\277\035" +
"\165\226\046\253\025\231\031\030\243\031\130\222\062\113\212\113" +
"\030\230\242\275\052\012\200\206\202\150\005\226\255\102\033\113" +
"\047\303\314\140\250\050\056\144\250\143\140\052\005\221\254\330" +
"\004\030\031\040\240\001\227\004\013\321\106\010\340\222\340\300" +
"\041\301\210\323\050\007\034\022\034\070\165\060\341\220\140\301" +
"\245\203\011\247\121\270\234\313\240\100\262\253\024\052\000\232" +
"\144\230\106\361\001\000\000"
});

public static final byte[] rejectSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\070\332\047\053\261\054\121\257\264\044\063\107\317\051" +
"\263\044\070\265\304\072\127\122\203\345\174\237\113\001\023\003" +
"\103\105\001\003\003\203\140\161\021\203\000\272\252\274\277\035" +
"\165\226\046\253\025\231\031\030\243\031\130\222\062\113\212\113" +
"\030\230\242\275\052\012\200\206\202\150\005\226\255\102\033\113" +
"\047\303\314\140\250\050\056\144\250\143\140\052\005\221\254\243" +
"\002\014\025\000\236\135\314\021\201\001\000\000"
});

public static final byte[] possibleSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\070\332\047\053\261\054\121\257\264\044\063\107\317\051" +
"\263\044\070\265\304\072\127\122\203\345\174\237\113\001\023\003" +
"\103\105\001\003\003\203\140\161\021\203\000\272\252\274\277\035" +
"\165\226\046\253\025\231\031\030\243\031\130\222\062\113\212\113" +
"\030\230\242\275\052\012\200\206\202\150\005\226\255\102\033\113" +
"\047\303\314\140\250\050\056\144\250\143\140\052\005\221\254\100" +
"\076\043\003\030\360\377\303\041\301\320\200\113\202\005\207\004" +
"\023\056\073\030\004\160\111\160\340\220\140\124\300\245\303\001" +
"\207\004\007\116\313\231\160\110\260\340\322\201\333\037\270\234" +
"\313\200\313\271\270\135\245\120\001\000\370\167\217\112\001\002" +
"\000\000"
});

public static final byte[] cMapHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\355\317\075\012\102\061" +
"\014\000\340\130\175\376\173\015\357\342\340\011\134\074\202\010" +
"\212\027\162\163\162\364\110\202\167\260\017\072\071\110\207\016" +
"\042\137\340\043\064\041\041\275\275\242\073\035\043\355\066\333" +
"\307\176\175\176\336\257\051\342\162\210\101\364\261\252\260\310" +
"\246\037\357\232\271\332\335\337\372\243\254\277\164\236\115\112" +
"\155\126\362\070\353\052\265\272\267\225\324\160\327\262\344\341" +
"\017\374\013\000\000\000\000\000\000\000\000\000\000\000\000\000" +
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
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\200" +
"\077\360\006\207\273\001\046\033\000\004\000"
});

public static final byte[] deltaHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\201\071\072\332\123\374\373\023\177\311\376\116\033\046\006" +
"\206\212\002\006\006\006\101\240\070\123\264\247\357\256\004\265" +
"\262\127\233\226\302\204\371\030\310\004\245\205\014\165\014\114" +
"\110\046\200\330\314\100\314\002\304\254\100\314\006\304\354\100" +
"\314\001\304\234\100\314\005\304\334\120\066\166\023\050\167\003" +
"\175\115\340\241\223\033\170\051\066\201\201\200\034\275\103\222" +
"\037\033\173\310\247\007\342\114\040\075\066\005\360\140\254\362" +
"\064\210\013\222\335\000\000\175\026\144\140\213\004\000\000"
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
        TERMINAL_COUNT = 12;
        GRAMMAR_SYMBOL_COUNT = 17;
        SYMBOL_COUNT = 28;
        PARSER_STATE_COUNT = 22;
        SCANNER_STATE_COUNT = 17;
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
