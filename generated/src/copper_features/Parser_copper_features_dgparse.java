/*
 * Built at Fri Oct 20 03:57:21 UTC 2017
 * by Copper version 0.7.2,
 *      build 20170816-1437
 */
package copper_features;


import java.util.ArrayList;
import java.util.List;


public class Parser_copper_features_dgparse extends edu.umn.cs.melt.copper.runtime.engines.single.SingleDFAEngine<copper_features.NDGRoot,edu.umn.cs.melt.copper.runtime.logging.CopperParserException>
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
        copper_features_A(1),
        copper_features_B(2),
        copper_features_BangTerm(3),
        copper_features_Foo_t(4),
        copper_features_Id(5),
        copper_features_KnownTerm(6),
        copper_features_QuoteLiteral(7),
        copper_features_Space(8),
        copper_features_UnknownTerm(9);

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
        public Integer copper_features_Acount;
        public common.ConsCell copper_features_knownlist;

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
            
Integer __action___local_a_local;
__action___local_a_local = Integer.valueOf((int)0);
copper_features_Acount = __action___local_a_local;

            
copper_features_knownlist = ((common.ConsCell)core.Pnil.invoke());

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
            default:
        runDefaultTermAction();
                 break;
            }
            return RESULT;
        }
        public copper_features.NAOrB runSemanticAction_17()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            copper_features.NAOrB RESULT = null;
            
RESULT = new copper_features.Paorb_a(_children[0]);

            return RESULT;
        }
        public copper_features.NAOrB runSemanticAction_18()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            copper_features.NAOrB RESULT = null;
            
RESULT = new copper_features.Paorb_b(_children[0]);

            return RESULT;
        }
        public copper_features.NAOrBs runSemanticAction_19()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            copper_features.NAOrBs RESULT = null;
            
RESULT = new copper_features.Paorb_cons(_children[0], _children[1]);

            return RESULT;
        }
        public copper_features.NAOrBs runSemanticAction_20()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            copper_features.NAOrBs RESULT = null;
            
RESULT = new copper_features.Paorb_one(_children[0]);

            return RESULT;
        }
        public copper_features.NDGRoot runSemanticAction_21()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            copper_features.NDGRoot RESULT = null;
            
RESULT = new copper_features.Prt(_children[0]);

            return RESULT;
        }
        public copper_features.NDGRoot runSemanticAction_22()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            copper_features.NDGRoot RESULT = null;
            
RESULT = new copper_features.Prt2(_children[0]);

            return RESULT;
        }
        public copper_features.NUseDcl runSemanticAction_23()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            copper_features.NUseDcl RESULT = null;
            
RESULT = new copper_features.PdDcl(_children[0], _children[1]);
copper_features_knownlist = ((common.ConsCell)core.Pcons.invoke(((common.StringCatter)((copper_features.TUnknownTerm)((common.Node)RESULT).getChild(copper_features.PdDcl.i_l)).lexeme), copper_features_knownlist));

            return RESULT;
        }
        public copper_features.NUseDcl runSemanticAction_24()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            copper_features.NUseDcl RESULT = null;
            
RESULT = new copper_features.PkUse(_children[0]);

            return RESULT;
        }
        public copper_features.NUseDcl runSemanticAction_25()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            copper_features.NUseDcl RESULT = null;
            
RESULT = new copper_features.PunUse(_children[0]);

            return RESULT;
        }
        public copper_features.NUseDcls runSemanticAction_26()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            copper_features.NUseDcls RESULT = null;
            
RESULT = new copper_features.Pconsud(_children[0], _children[1]);

            return RESULT;
        }
        public copper_features.NUseDcls runSemanticAction_27()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            copper_features.NUseDcls RESULT = null;
            
RESULT = new copper_features.Poneud(_children[0]);

            return RESULT;
        }
        public copper_features.TA runSemanticAction_1(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            copper_features.TA RESULT = null;
            
RESULT = new copper_features.TA(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);
copper_features_Acount = Integer.valueOf(copper_features_Acount + Integer.valueOf((int)1));
virtualLocation.setFileName(new common.StringCatter((common.StringCatter)new common.StringCatter(virtualLocation.getFileName()), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(virtualLocation.getLine())), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(".")), (common.StringCatter)new common.StringCatter(String.valueOf(virtualLocation.getColumn())))))).toString());

            return RESULT;
        }
        public copper_features.TB runSemanticAction_2(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            copper_features.TB RESULT = null;
            
RESULT = new copper_features.TB(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);
virtualLocation.setLine(virtualLocation.getColumn());
virtualLocation.setColumn(copper_features_Acount);

            return RESULT;
        }
        public copper_features.TBangTerm runSemanticAction_3(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            copper_features.TBangTerm RESULT = null;
            
RESULT = new copper_features.TBangTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public copper_features.TFoo_t runSemanticAction_4(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            copper_features.TFoo_t RESULT = null;
            
RESULT = new copper_features.TFoo_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public copper_features.TId runSemanticAction_5(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            copper_features.TId RESULT = null;
            
RESULT = new copper_features.TId(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public copper_features.TKnownTerm runSemanticAction_6(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            copper_features.TKnownTerm RESULT = null;
            
RESULT = new copper_features.TKnownTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public copper_features.TQuoteLiteral runSemanticAction_7(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            copper_features.TQuoteLiteral RESULT = null;
            
RESULT = new copper_features.TQuoteLiteral(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public copper_features.TSpace runSemanticAction_8(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            copper_features.TSpace RESULT = null;
            
RESULT = new copper_features.TSpace(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public copper_features.TUnknownTerm runSemanticAction_9(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            copper_features.TUnknownTerm RESULT = null;
            
RESULT = new copper_features.TUnknownTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public int runDisambiguationAction(edu.umn.cs.melt.copper.runtime.io.InputPosition _pos,edu.umn.cs.melt.copper.runtime.engines.single.scanner.SingleDFAMatchData match)
        throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            String lexeme = match.lexeme;
            if(match.terms.equals(disambiguationGroups[0])) return disambiguate_0(lexeme);
            else if(match.terms.equals(disambiguationGroups[1])) return disambiguate_1(lexeme);
            else return -1;
        }
        public int disambiguate_0(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            @SuppressWarnings("unused") final int copper_features_Foo_t = 4;
            @SuppressWarnings("unused") final int copper_features_Id = 5;
            
return (Integer)(((Boolean)core.PcontainsBy.invoke(core.PstringEq.factory, new common.StringCatter(lexeme), ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("foo")), ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("bar")), ((common.ConsCell)core.Pnil.invoke()))))))) ? copper_features_Foo_t : copper_features_Id);

        }
        public int disambiguate_1(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            @SuppressWarnings("unused") final int copper_features_KnownTerm = 6;
            @SuppressWarnings("unused") final int copper_features_UnknownTerm = 9;
            
return (Integer)(((Boolean)core.PcontainsBy.invoke(core.PstringEq.factory, new common.StringCatter(lexeme), copper_features_knownlist)) ? copper_features_KnownTerm : copper_features_UnknownTerm);

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
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\165\221\115\116\002\101" +
"\020\205\053\032\334\053\370\027\341\012\054\330\272\202\040\304" +
"\110\202\060\203\033\027\223\146\246\044\010\166\115\172\252\325" +
"\304\053\351\145\270\204\161\341\035\154\232\205\111\167\261\375" +
"\136\352\365\173\257\077\177\241\146\015\324\037\107\317\352\125" +
"\265\327\112\057\332\011\233\245\136\134\177\155\036\276\177\132" +
"\037\303\003\200\367\022\000\232\014\207\067\343\001\303\161\116" +
"\145\211\046\173\102\305\326\140\225\165\005\326\143\270\210\230" +
"\163\117\321\274\060\234\206\322\200\050\143\206\223\220\337\026" +
"\014\227\041\274\323\364\246\167\106\315\120\233\130\142\034\055" +
"\031\215\132\013\357\044\245\312\221\341\052\344\063\275\372\067" +
"\255\045\151\167\232\062\064\242\242\143\323\023\114\267\270\142" +
"\070\013\171\177\070\045\142\101\230\125\330\317\135\274\163\131" +
"\160\136\107\076\302\275\160\253\310\314\063\265\117\230\013\163" +
"\171\041\047\135\011\137\342\065\322\050\114\157\134\362\172\014" +
"\073\302\056\205\157\023\341\225\253\043\314\145\265\347\121\201" +
"\155\104\133\010\007\056\237\055\376\000\374\072\352\322\252\002" +
"\000\000"
});

public static final byte[] symbolDisplayNamesHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\165\221\115\116\002\101" +
"\020\205\013\022\017\240\340\137\304\235\153\026\056\165\045\041" +
"\022\043\211\021\204\215\311\164\232\231\222\040\330\065\251\251" +
"\126\023\257\244\227\361\022\306\205\167\260\141\026\046\135\355" +
"\366\173\251\327\357\275\176\377\201\055\317\320\272\037\076\332" +
"\147\333\135\131\067\357\216\205\027\156\176\376\361\071\375\372" +
"\076\176\033\064\001\136\113\000\350\010\064\116\004\266\163\052" +
"\113\144\363\200\126\074\143\165\166\221\140\075\201\003\305\202" +
"\367\035\362\223\300\156\054\135\022\031\021\330\211\371\125\041" +
"\160\030\303\153\107\057\256\066\352\304\332\255\047\301\341\102" +
"\220\355\052\361\316\270\264\071\012\034\305\174\342\226\177\246" +
"\215\114\240\255\112\336\160\057\141\270\306\225\300\136\314\373" +
"\203\021\221\044\204\111\205\375\074\104\333\117\013\301\253\231" +
"\145\372\316\130\342\231\261\377\011\063\075\123\055\344\344\052" +
"\375\025\265\106\016\365\344\206\103\352\226\206\247\172\023\123" +
"\154\232\050\274\014\125\364\124\306\273\015\127\005\326\021\175" +
"\221\070\010\371\174\361\013\351\042\313\111\240\002\000\000"
});

public static final byte[] symbolNumbersHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\201\051\332\323\167\127\202\132\331\253\115\113\231\030\030" +
"\052\012\030\030\030\144\030\210\004\012\070\260\003\003\003\023" +
"\020\063\102\061\062\033\235\017\146\003\000\126\214\321\245\213" +
"\000\000\000"
});

public static final byte[] productionLHSsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\201\051\332\323\167\127\202\132\331\253\115\113\231\030\030" +
"\052\012\030\030\030\170\024\030\030\270\200\230\033\212\171\240" +
"\230\027\212\371\220\060\077\010\003\000\067\154\140\136\113\000" +
"\000\000"
});

public static final byte[] parseTableHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\201\071\072\332\123\374\373\023\177\311\376\116\033\046\006" +
"\206\212\002\006\006\260\070\123\264\247\357\256\004\265\262\127" +
"\233\226\302\204\005\030\050\004\245\205\014\165\014\114\330\114" +
"\122\140\140\140\002\142\146\174\272\201\362\054\230\046\071\060" +
"\060\210\121\307\115\100\223\104\251\143\122\002\045\306\000\001" +
"\000\373\361\244\121\233\001\000\000"
});

public static final byte[] shiftableSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\070\332\047\053\261\054\121\257\264\044\063\107\317\051" +
"\263\044\070\265\304\072\127\122\203\345\174\237\113\001\023\003" +
"\103\105\001\003\003\003\153\161\021\203\000\272\252\274\277\035" +
"\165\226\046\253\025\231\031\030\243\031\130\222\062\113\212\113" +
"\030\230\242\275\052\012\200\206\202\150\005\226\255\102\033\113" +
"\047\303\314\140\144\200\202\212\342\102\206\072\006\246\122\020" +
"\311\212\220\140\064\300\045\301\110\206\004\000\001\156\055\006" +
"\335\000\000\000"
});

public static final byte[] layoutSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\070\332\047\053\261\054\121\257\264\044\063\107\317\051" +
"\263\044\070\265\304\072\127\122\203\345\174\237\113\001\023\003" +
"\103\105\001\003\003\003\153\161\021\203\000\272\252\274\277\035" +
"\165\226\046\253\025\231\031\030\243\031\130\222\062\113\212\113" +
"\030\230\242\275\052\012\200\206\202\150\005\226\255\102\033\113" +
"\047\303\314\140\144\200\202\212\342\102\206\072\006\246\122\020" +
"\311\212\220\140\244\252\004\000\360\345\064\072\335\000\000\000" +
""
});

public static final byte[] prefixSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\070\332\047\053\261\054\121\257\264\044\063\107\317\051" +
"\263\044\070\265\304\072\127\122\203\345\174\237\113\001\023\003" +
"\103\105\001\003\003\003\153\161\021\203\000\272\252\274\277\035" +
"\165\226\046\253\025\231\031\030\243\031\130\222\062\113\212\113" +
"\030\230\242\275\052\012\200\206\202\150\005\226\255\102\033\113" +
"\047\303\314\140\144\200\202\212\342\102\206\072\006\246\122\020" +
"\311\112\053\011\000\276\324\070\271\335\000\000\000"
});

public static final byte[] prefixMapsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\044\072\332\047\053\261\054\121\257\264\044\063\107\317" +
"\051\263\044\070\265\304\132\362\322\273\215\346\317\356\030\061" +
"\061\060\124\024\060\060\200\025\012\143\121\227\053\251\301\162" +
"\276\317\245\000\246\216\253\000\016\112\013\031\352\030\230\250" +
"\047\006\000\213\205\306\214\257\000\000\000"
});

public static final byte[] terminalUsesHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\201\051\332\323\167\127\202\132\331\253\115\113\231\030\030" +
"\052\012\030\030\030\270\030\210\004\000\374\370\371\111\103\000" +
"\000\000"
});

public static final byte[] shiftableUnionHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\270" +
"\210\101\040\053\261\054\121\257\264\044\063\107\317\051\263\044" +
"\070\265\044\357\157\107\235\245\311\152\105\146\006\306\150\006" +
"\226\244\314\222\342\022\006\246\150\257\212\202\322\042\060\255" +
"\300\262\125\150\143\351\144\046\006\206\212\002\006\006\006\106" +
"\006\060\140\064\254\000\000\062\225\257\101\121\000\000\000"
});

public static final byte[] acceptSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\070\332\047\053\261\054\121\257\264\044\063\107\317\051" +
"\263\044\070\265\304\072\127\122\203\345\174\237\113\001\023\003" +
"\103\105\001\003\003\003\117\161\021\203\000\272\252\274\277\035" +
"\165\226\046\253\025\231\031\030\243\031\130\222\062\113\212\113" +
"\030\230\242\275\052\012\200\206\202\150\005\226\255\102\033\113" +
"\047\303\314\140\250\050\056\144\250\143\140\052\005\221\254\100" +
"\076\043\003\004\050\340\220\140\112\302\045\221\200\103\202\021" +
"\247\035\015\270\214\112\301\245\203\203\124\313\311\220\050\250" +
"\000\000\043\143\123\142\204\001\000\000"
});

public static final byte[] rejectSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\070\332\047\053\261\054\121\257\264\044\063\107\317\051" +
"\263\044\070\265\304\072\127\122\203\345\174\237\113\001\023\003" +
"\103\105\001\003\003\003\117\161\021\203\000\272\252\274\277\035" +
"\165\226\046\253\025\231\031\030\243\031\130\222\062\113\212\113" +
"\030\230\242\275\052\012\200\206\202\150\005\226\255\102\033\113" +
"\047\303\314\140\250\050\056\144\250\143\140\052\005\221\254\103" +
"\114\000\000\114\262\040\116\054\001\000\000"
});

public static final byte[] possibleSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\070\332\047\053\261\054\121\257\264\044\063\107\317\051" +
"\263\044\070\265\304\072\127\122\203\345\174\237\113\001\023\003" +
"\103\105\001\003\003\003\117\161\021\203\000\272\252\274\277\035" +
"\165\226\046\253\025\231\031\030\243\031\130\222\062\113\212\113" +
"\030\230\242\275\052\012\200\206\202\150\005\226\255\102\033\113" +
"\047\303\314\140\250\050\056\144\250\143\140\052\005\221\254\100" +
"\076\043\003\030\060\377\303\041\301\224\204\113\042\001\207\004" +
"\043\056\073\030\032\160\031\225\202\113\007\007\056\035\005\244" +
"\272\012\267\216\202\012\000\021\047\306\303\204\001\000\000"
});

public static final byte[] cMapHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\355\321\261\015\202\120" +
"\020\200\341\343\001\312\044\354\142\301\004\064\216\140\110\064" +
"\056\144\147\105\351\110\046\356\300\243\043\206\202\016\212\357" +
"\113\376\342\222\053\056\271\367\057\352\373\020\251\277\164\237" +
"\153\373\370\216\257\024\361\274\105\021\107\120\345\316\271\172" +
"\357\103\166\064\177\342\224\053\027\065\177\363\132\151\303\316" +
"\062\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
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
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\330\144" +
"\002\006\050\273\157\033\000\004\000"
});

public static final byte[] deltaHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\201\071\072\332\123\374\373\023\177\311\376\116\033\046\006" +
"\206\212\002\006\006\006\036\240\070\123\264\247\357\256\004\265" +
"\262\127\233\226\302\204\071\031\210\000\245\205\014\165\014\114" +
"\110\252\101\154\146\050\146\001\142\126\040\146\003\142\166\040" +
"\346\300\120\315\211\204\031\220\304\300\064\205\252\111\163\067" +
"\151\252\151\351\022\020\315\105\264\331\244\207\040\067\165\314" +
"\006\000\075\165\034\003\121\002\000\000"
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
public Parser_copper_features_dgparse() {}

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
    public copper_features.NDGRoot parse(java.io.Reader input,String inputName)
    throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
    {
    this.charBuffer = edu.umn.cs.melt.copper.runtime.io.ScannerBuffer.instantiate(input);
    setupEngine();
    startEngine(edu.umn.cs.melt.copper.runtime.io.InputPosition.initialPos(inputName));
    copper_features.NDGRoot parseTree = (copper_features.NDGRoot) runEngine();
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
        TERMINAL_COUNT = 10;
        GRAMMAR_SYMBOL_COUNT = 16;
        SYMBOL_COUNT = 28;
        PARSER_STATE_COUNT = 5;
        SCANNER_STATE_COUNT = 12;
        DISAMBIG_GROUP_COUNT = 2;
        SCANNER_START_STATENUM = 1;
        PARSER_START_STATENUM = 1;
        EOF_SYMNUM = 0;
        EPS_SYMNUM = -1;
        try { initArrays(); }
        catch(java.io.IOException ex) { ex.printStackTrace(); System.exit(1); }
        catch(java.lang.ClassNotFoundException ex) { ex.printStackTrace(); System.exit(1); }
        disambiguationGroups = new java.util.BitSet[2];
        disambiguationGroups[0] = newBitVec(10,4,5);
        disambiguationGroups[1] = newBitVec(10,6,9);
    }

}
