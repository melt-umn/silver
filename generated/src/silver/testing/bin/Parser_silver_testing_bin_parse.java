/*
 * Built at Fri Oct 20 03:57:13 UTC 2017
 * by Copper version 0.7.2,
 *      build 20170816-1437
 */
package silver.testing.bin;


import java.util.ArrayList;
import java.util.List;


public class Parser_silver_testing_bin_parse extends edu.umn.cs.melt.copper.runtime.engines.single.SingleDFAEngine<silver.testing.bin.NRun,edu.umn.cs.melt.copper.runtime.logging.CopperParserException>
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
        silver_testing_bin_BlockComment(1),
        silver_testing_bin_Colon_t(2),
        silver_testing_bin_CommandAlt_t(3),
        silver_testing_bin_Command_t(4),
        silver_testing_bin_Fail_t(5),
        silver_testing_bin_Jar_t(6),
        silver_testing_bin_LineComment(7),
        silver_testing_bin_Run_t(8),
        silver_testing_bin_Skip_t(9),
        silver_testing_bin_Suite_t(10),
        silver_testing_bin_Test_t(11),
        silver_testing_bin_WhiteSpace(12);

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
            case 12:
                RESULT = runSemanticAction_12(lexeme);
                break;
            default:
        runDefaultTermAction();
                 break;
            }
            return RESULT;
        }
        public silver.testing.bin.NOptionalFail runSemanticAction_17()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.testing.bin.NOptionalFail RESULT = null;
            
RESULT = new silver.testing.bin.PdoFail(_children[0]);

            return RESULT;
        }
        public silver.testing.bin.NOptionalFail runSemanticAction_18()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.testing.bin.NOptionalFail RESULT = null;
            
RESULT = new silver.testing.bin.PnoFail();

            return RESULT;
        }
        public silver.testing.bin.NRun runSemanticAction_19()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.testing.bin.NRun RESULT = null;
            
RESULT = new silver.testing.bin.Prun(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.testing.bin.NRun runSemanticAction_20()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.testing.bin.NRun RESULT = null;
            
RESULT = new silver.testing.bin.PrunTestSuite(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.testing.bin.NRun runSemanticAction_21()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.testing.bin.NRun RESULT = null;
            
RESULT = new silver.testing.bin.Prun_alternate(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.testing.bin.NRun runSemanticAction_22()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.testing.bin.NRun RESULT = null;
            
RESULT = new silver.testing.bin.PskipRun(_children[0], _children[1]);

            return RESULT;
        }
        public silver.testing.bin.TBlockComment runSemanticAction_1(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.testing.bin.TBlockComment RESULT = null;
            
RESULT = new silver.testing.bin.TBlockComment(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.testing.bin.TColon_t runSemanticAction_2(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.testing.bin.TColon_t RESULT = null;
            
RESULT = new silver.testing.bin.TColon_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.testing.bin.TCommandAlt_t runSemanticAction_3(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.testing.bin.TCommandAlt_t RESULT = null;
            
RESULT = new silver.testing.bin.TCommandAlt_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.testing.bin.TCommand_t runSemanticAction_4(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.testing.bin.TCommand_t RESULT = null;
            
RESULT = new silver.testing.bin.TCommand_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.testing.bin.TFail_t runSemanticAction_5(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.testing.bin.TFail_t RESULT = null;
            
RESULT = new silver.testing.bin.TFail_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.testing.bin.TJar_t runSemanticAction_6(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.testing.bin.TJar_t RESULT = null;
            
RESULT = new silver.testing.bin.TJar_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.testing.bin.TLineComment runSemanticAction_7(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.testing.bin.TLineComment RESULT = null;
            
RESULT = new silver.testing.bin.TLineComment(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.testing.bin.TRun_t runSemanticAction_8(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.testing.bin.TRun_t RESULT = null;
            
RESULT = new silver.testing.bin.TRun_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.testing.bin.TSkip_t runSemanticAction_9(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.testing.bin.TSkip_t RESULT = null;
            
RESULT = new silver.testing.bin.TSkip_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.testing.bin.TSuite_t runSemanticAction_10(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.testing.bin.TSuite_t RESULT = null;
            
RESULT = new silver.testing.bin.TSuite_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.testing.bin.TTest_t runSemanticAction_11(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.testing.bin.TTest_t RESULT = null;
            
RESULT = new silver.testing.bin.TTest_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.testing.bin.TWhiteSpace runSemanticAction_12(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.testing.bin.TWhiteSpace RESULT = null;
            
RESULT = new silver.testing.bin.TWhiteSpace(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
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
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\165\220\115\112\103\101" +
"\020\204\033\045\271\200\340\117\060\232\023\344\002\256\142\060" +
"\202\004\042\231\207\056\134\014\155\062\304\061\223\236\307\274" +
"\176\101\360\112\172\031\057\041\056\274\203\323\021\262\352\331" +
"\015\174\125\075\125\365\361\013\235\066\301\321\323\364\025\267" +
"\070\014\110\253\241\341\344\151\165\365\371\365\360\375\323\177" +
"\277\075\000\170\253\001\340\204\341\360\146\066\141\270\154\174" +
"\330\272\144\331\065\234\205\366\331\223\275\016\161\261\036\307" +
"\315\306\021\063\364\024\305\070\206\110\226\165\273\070\221\226" +
"\243\300\242\070\057\053\004\237\051\170\202\076\010\073\125\330" +
"\035\046\101\027\012\232\172\162\373\324\232\167\336\122\351\113" +
"\263\366\265\055\224\065\255\147\127\062\126\371\055\254\257\260" +
"\307\227\154\064\065\056\034\103\307\124\243\171\245\017\066\253" +
"\331\107\302\040\275\031\216\365\344\014\335\335\211\173\075\307" +
"\062\376\273\065\106\261\174\071\311\145\055\124\006\322\155\127" +
"\236\141\240\053\054\006\166\211\120\044\332\164\115\236\065\147" +
"\377\003\065\072\375\143\231\002\000\000"
});

public static final byte[] symbolDisplayNamesHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\165\220\101\112\003\101" +
"\020\105\053\001\117\020\210\032\114\342\302\165\016\060\256\064" +
"\140\100\002\102\106\164\041\114\123\046\115\154\323\251\036\172" +
"\152\202\220\053\351\145\274\204\270\360\016\351\122\011\004\152" +
"\166\015\357\375\352\372\365\376\003\107\165\204\316\323\364\025" +
"\067\070\362\110\313\121\316\321\321\362\362\343\363\341\353\273" +
"\277\235\264\001\336\112\000\070\146\150\135\060\014\053\347\067" +
"\066\146\154\053\116\132\366\354\050\273\366\141\276\032\207\365" +
"\332\022\063\364\024\143\034\174\040\303\172\134\222\110\213\053" +
"\317\142\234\065\033\202\117\025\174\203\316\013\073\121\330\055" +
"\106\101\003\005\115\035\331\375\326\132\166\126\123\323\227\371" +
"\312\225\246\241\154\136\073\266\115\301\373\364\026\326\127\330" +
"\343\113\012\346\045\316\155\272\165\241\037\353\256\144\027\010" +
"\275\164\146\350\352\133\063\264\213\142\377\277\371\247\046\121" +
"\263\010\177\111\215\121\070\230\172\300\242\114\035\352\100\072" +
"\375\226\146\070\327\015\203\236\155\044\024\245\247\050\125\072" +
"\147\332\173\007\115\060\024\307\217\002\000\000"
});

public static final byte[] symbolNumbersHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\201\051\332\323\167\127\202\132\331\253\115\113\231\030\030" +
"\052\012\030\030\030\304\031\310\000\012\110\330\201\201\201\011" +
"\210\031\035\040\154\146\050\146\001\211\003\000\335\377\010\150" +
"\167\000\000\000"
});

public static final byte[] productionLHSsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\201\051\332\323\167\127\202\132\331\253\115\113\231\030\030" +
"\052\012\030\030\030\330\025\030\030\170\201\230\017\212\371\221" +
"\061\000\141\120\362\142\067\000\000\000"
});

public static final byte[] parseTableHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\201\071\072\332\123\374\373\023\177\311\376\116\033\046\006" +
"\206\212\002\006\006\006\076\240\070\123\264\247\357\256\004\265" +
"\262\127\233\226\302\204\005\030\050\004\245\205\014\165\014\114" +
"\270\114\122\140\000\313\201\201\003\003\203\020\220\317\014\025" +
"\147\101\122\303\012\304\154\370\115\102\006\100\223\004\351\342" +
"\046\166\342\335\204\244\223\203\164\067\241\231\300\211\117\036" +
"\141\122\002\261\216\042\150\222\003\003\203\030\165\114\302\245" +
"\102\201\201\201\213\074\223\200\072\271\241\064\017\171\156\162" +
"\140\140\020\041\105\047\141\067\101\335\303\113\276\111\100\067" +
"\011\123\307\115\100\223\104\051\061\011\000\010\052\320\304\065" +
"\004\000\000"
});

public static final byte[] shiftableSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\070\332\047\053\261\054\121\257\264\044\063\107\317\051" +
"\263\044\070\265\304\072\127\122\203\345\174\237\113\001\023\003" +
"\103\105\001\003\003\003\137\161\021\203\000\272\252\274\277\035" +
"\165\226\046\253\025\231\031\030\243\031\130\222\062\113\212\113" +
"\030\230\242\275\052\012\200\206\202\150\005\226\255\102\033\113" +
"\047\303\314\140\144\200\202\212\342\102\206\072\006\246\122\020" +
"\311\212\220\220\136\204\103\102\260\211\124\035\042\270\164\340" +
"\064\112\240\231\144\211\103\270\044\246\221\154\124\027\031\256" +
"\002\000\170\242\337\121\276\001\000\000"
});

public static final byte[] layoutSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\070\332\047\053\261\054\121\257\264\044\063\107\317\051" +
"\263\044\070\265\304\072\127\122\203\345\174\237\113\001\023\003" +
"\103\105\001\003\003\003\137\161\021\203\000\272\252\274\277\035" +
"\165\226\046\253\025\231\031\030\243\031\130\222\062\113\212\113" +
"\030\230\242\275\052\012\200\206\202\150\005\226\255\102\033\113" +
"\047\303\314\140\144\200\202\212\342\102\206\072\006\246\122\020" +
"\311\212\220\020\150\032\225\040\101\002\000\354\071\114\311\276" +
"\001\000\000"
});

public static final byte[] prefixSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\070\332\047\053\261\054\121\257\264\044\063\107\317\051" +
"\263\044\070\265\304\072\127\122\203\345\174\237\113\001\023\003" +
"\103\105\001\003\003\003\137\161\021\203\000\272\252\274\277\035" +
"\165\226\046\253\025\231\031\030\243\031\130\222\062\113\212\113" +
"\030\230\242\275\052\012\200\206\202\150\005\226\255\102\033\113" +
"\047\303\314\140\144\200\202\212\342\102\206\072\006\246\122\020" +
"\311\072\052\101\246\004\000\304\215\262\202\276\001\000\000"
});

public static final byte[] prefixMapsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\044\072\332\047\053\261\054\121\257\264\044\063\107\317" +
"\051\263\044\070\265\304\132\362\322\273\215\346\317\356\030\061" +
"\061\060\124\024\060\060\060\360\001\025\012\143\121\227\053\251" +
"\301\162\276\317\245\000\246\216\267\000\031\224\026\062\324\061" +
"\060\215\012\023\041\014\000\337\133\343\155\215\001\000\000"
});

public static final byte[] terminalUsesHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\201\051\332\323\167\127\202\132\331\253\115\113\231\030\030" +
"\052\012\030\030\030\170\031\310\000\000\132\346\100\064\117\000" +
"\000\000"
});

public static final byte[] shiftableUnionHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\270" +
"\210\101\040\053\261\054\121\257\264\044\063\107\317\051\263\044" +
"\070\265\044\357\157\107\235\245\311\152\105\146\006\306\150\006" +
"\226\244\314\222\342\022\006\246\150\257\212\202\322\042\060\255" +
"\300\262\125\150\143\351\144\046\006\206\212\002\006\006\006\106" +
"\006\060\220\377\137\001\000\210\341\156\002\121\000\000\000"
});

public static final byte[] acceptSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\070\332\047\053\261\054\121\257\264\044\063\107\317\051" +
"\263\044\070\265\304\072\127\122\203\345\174\237\113\001\023\003" +
"\103\105\001\003\003\203\115\161\021\203\000\272\252\274\277\035" +
"\165\226\046\253\025\231\031\030\243\031\130\222\062\113\212\113" +
"\030\230\242\275\052\012\200\206\202\150\005\226\255\102\033\113" +
"\047\303\314\140\250\050\056\144\250\143\140\052\005\221\254\100" +
"\076\043\003\004\160\320\136\102\140\040\055\047\303\125\074\203" +
"\321\125\324\014\304\206\201\214\017\011\164\011\134\036\143\034" +
"\310\324\101\202\004\316\210\241\142\060\023\035\150\014\002\044" +
"\233\255\201\103\202\203\036\241\311\104\320\143\204\005\010\131" +
"\302\204\323\166\234\201\345\101\276\053\060\174\004\225\140\341" +
"\250\000\000\072\164\023\013\364\005\000\000"
});

public static final byte[] rejectSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\070\332\047\053\261\054\121\257\264\044\063\107\317\051" +
"\263\044\070\265\304\072\127\122\203\345\174\237\113\001\023\003" +
"\103\105\001\003\003\203\115\161\021\203\000\272\252\274\277\035" +
"\165\226\046\253\025\231\031\030\243\031\130\222\062\113\212\113" +
"\030\230\242\275\052\012\200\206\202\150\005\226\255\102\033\113" +
"\047\303\314\140\250\050\056\144\250\143\140\052\005\221\254\124" +
"\022\140\144\200\000\016\132\030\076\100\002\324\364\323\160\014" +
"\237\321\140\030\076\176\002\000\074\303\101\134\204\004\000\000" +
""
});

public static final byte[] possibleSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\070\332\047\053\261\054\121\257\264\044\063\107\317\051" +
"\263\044\070\265\304\072\127\122\203\345\174\237\113\001\023\003" +
"\103\105\001\003\003\203\115\161\021\203\000\272\252\274\277\035" +
"\165\226\046\253\025\231\031\030\243\031\130\222\062\113\212\113" +
"\030\230\242\275\052\012\200\206\202\150\005\226\255\102\033\113" +
"\047\303\314\140\250\050\056\144\250\143\140\052\005\221\254\100" +
"\076\043\003\030\310\377\303\041\301\350\201\103\202\041\003\227" +
"\004\056\035\002\034\270\164\340\222\340\300\151\171\027\016\011" +
"\066\234\072\044\160\271\012\127\220\060\360\220\352\101\334\141" +
"\105\162\040\342\014\053\234\316\305\035\126\134\270\044\072\160" +
"\110\260\340\062\212\211\344\320\045\103\102\200\366\241\113\106" +
"\130\221\056\301\064\220\301\216\053\020\161\113\014\316\100\044" +
"\075\164\111\226\040\043\330\111\017\104\252\072\027\000\361\325" +
"\245\277\064\006\000\000"
});

public static final byte[] cMapHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\355\321\273\155\002\101" +
"\024\005\320\141\015\030\203\301\140\314\247\002\172\041\160\005" +
"\044\224\200\220\260\150\310\031\021\041\045\041\321\003\157\244" +
"\015\126\004\260\044\104\147\244\243\073\237\067\363\202\071\134" +
"\122\153\267\115\305\152\371\173\132\057\376\316\307\377\042\245" +
"\375\046\065\122\036\355\032\336\303\244\262\156\326\274\127\367" +
"\355\234\077\017\352\172\225\171\047\114\303\340\211\076\263\073" +
"\147\235\027\311\275\076\156\372\346\034\335\324\175\205\242\262" +
"\036\207\126\230\207\267\162\157\130\146\267\314\374\233\337\341" +
"\063\364\357\364\007\000\000\000\000\000\000\000\000\000\000\000" +
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
"\000\200\207\256\046\022\317\073\033\000\004\000"
});

public static final byte[] deltaHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\335\226\271\116\303\100" +
"\020\206\107\016\061\367\025\216\140\302\141\010\204\373\010\367" +
"\221\027\110\201\170\200\064\171\004\100\012\242\242\247\342\051" +
"\350\250\050\171\036\052\044\152\132\154\311\226\176\257\361\332" +
"\216\327\043\073\053\175\232\231\365\172\177\355\071\373\376\103" +
"\305\316\003\025\132\255\146\371\367\353\326\170\175\151\150\104" +
"\117\167\104\324\260\352\265\126\363\346\263\135\173\374\376\170" +
"\163\253\015\142\050\235\173\172\046\015\324\154\277\140\321\147" +
"\121\164\254\056\370\256\355\167\374\001\307\272\014\072\166\310" +
"\142\330\361\107\154\353\123\033\005\164\260\372\077\165\266\035" +
"\003\137\104\167\372\034\167\333\044\126\023\375\011\126\265\170" +
"\143\323\201\311\000\077\016\045\214\245\152\052\040\251\132\267" +
"\063\071\225\170\046\145\114\203\077\303\066\266\131\141\234\262" +
"\261\225\175\152\163\041\030\021\332\270\314\133\124\260\316\247" +
"\206\245\024\340\307\051\236\377\162\272\047\371\157\256\205\304" +
"\152\213\231\035\033\262\024\111\015\127\070\375\173\022\113\372" +
"\047\100\305\114\056\123\324\165\063\201\025\041\106\126\045\337" +
"\220\052\306\076\265\065\305\170\372\114\155\117\256\107\232\111" +
"\125\152\265\110\152\274\071\100\245\332\106\250\032\157\016\250" +
"\204\260\031\241\215\313\226\130\227\343\214\143\263\335\225\332" +
"\116\346\306\266\233\130\315\044\316\173\222\127\155\017\330\027" +
"\142\344\000\374\103\111\273\072\306\076\265\043\240\052\304\310" +
"\261\344\133\140\037\071\312\070\047\304\161\002\116\111\325\171" +
"\343\315\070\052\157\345\063\261\116\372\302\123\136\174\152\347" +
"\224\247\034\220\145\065\223\070\357\111\136\065\336\133\231\367" +
"\315\305\233\003\170\325\056\200\272\020\043\227\340\137\111\332" +
"\171\372\120\166\336\256\051\373\247\273\227\137\346\275\274\047" +
"\123\055\274\353\366\007\212\371\373\007\361\031\000\000"
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
public Parser_silver_testing_bin_parse() {}

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
    public silver.testing.bin.NRun parse(java.io.Reader input,String inputName)
    throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
    {
    this.charBuffer = edu.umn.cs.melt.copper.runtime.io.ScannerBuffer.instantiate(input);
    setupEngine();
    startEngine(edu.umn.cs.melt.copper.runtime.io.InputPosition.initialPos(inputName));
    silver.testing.bin.NRun parseTree = (silver.testing.bin.NRun) runEngine();
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
        TERMINAL_COUNT = 13;
        GRAMMAR_SYMBOL_COUNT = 16;
        SYMBOL_COUNT = 23;
        PARSER_STATE_COUNT = 14;
        SCANNER_STATE_COUNT = 60;
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
