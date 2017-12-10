/*
 * Built at Fri Oct 20 03:57:44 UTC 2017
 * by Copper version 0.7.2,
 *      build 20170816-1437
 */
package cstast;


import java.util.ArrayList;
import java.util.List;


public class Parser_cstast_syntaxInterfaceParser extends edu.umn.cs.melt.copper.runtime.engines.single.SingleDFAEngine<silver.definition.concrete_syntax.ast.env_parser.NISyntaxInner,edu.umn.cs.melt.copper.runtime.logging.CopperParserException>
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
        silver_definition_concrete_syntax_ast_env_parser_AcodeTerm(1),
        silver_definition_concrete_syntax_ast_env_parser_AssocTerm(2),
        silver_definition_concrete_syntax_ast_env_parser_ClassesTerm(3),
        silver_definition_concrete_syntax_ast_env_parser_DisTerm(4),
        silver_definition_concrete_syntax_ast_env_parser_DomTerm(5),
        silver_definition_concrete_syntax_ast_env_parser_IgnoreTerm(6),
        silver_definition_concrete_syntax_ast_env_parser_LClassTerm(7),
        silver_definition_concrete_syntax_ast_env_parser_LayoutTerm(8),
        silver_definition_concrete_syntax_ast_env_parser_MarkingTerm(9),
        silver_definition_concrete_syntax_ast_env_parser_OperTerm(10),
        silver_definition_concrete_syntax_ast_env_parser_ParAttr(11),
        silver_definition_concrete_syntax_ast_env_parser_ParserTerm(12),
        silver_definition_concrete_syntax_ast_env_parser_ParsersTerm(13),
        silver_definition_concrete_syntax_ast_env_parser_PrecTerm(14),
        silver_definition_concrete_syntax_ast_env_parser_SubTerm(15),
        silver_definition_concrete_syntax_ast_env_parser_SyntaxTerm(16),
        silver_definition_env_env_parser_AllDepsTerm(17),
        silver_definition_env_env_parser_AnnoAt_t(18),
        silver_definition_env_env_parser_Anno_t(19),
        silver_definition_env_env_parser_BooleanTerm(20),
        silver_definition_env_env_parser_Comma_t(21),
        silver_definition_env_env_parser_CondBuildTerm(22),
        silver_definition_env_env_parser_DeclaredNameTerm(23),
        silver_definition_env_env_parser_DecoratedTerm(24),
        silver_definition_env_env_parser_DefaultTerm(25),
        silver_definition_env_env_parser_DefsTerm(26),
        silver_definition_env_env_parser_Eq(27),
        silver_definition_env_env_parser_EscapedStringTerm(28),
        silver_definition_env_env_parser_ExportedGrammarsTerm(29),
        silver_definition_env_env_parser_F_t(30),
        silver_definition_env_env_parser_FloatTerm(31),
        silver_definition_env_env_parser_ForwardTerm(32),
        silver_definition_env_env_parser_FunctionTerm(33),
        silver_definition_env_env_parser_GlobTerm(34),
        silver_definition_env_env_parser_GrammarSourceTerm(35),
        silver_definition_env_env_parser_GrammarTimeTerm(36),
        silver_definition_env_env_parser_IOTerm(37),
        silver_definition_env_env_parser_ITyVar(38),
        silver_definition_env_env_parser_Id_t(39),
        silver_definition_env_env_parser_InheritedTerm(40),
        silver_definition_env_env_parser_IntegerTerm(41),
        silver_definition_env_env_parser_LB_t(42),
        silver_definition_env_env_parser_LParent_t(43),
        silver_definition_env_env_parser_LocalTerm(44),
        silver_definition_env_env_parser_ModuleNamesTerm(45),
        silver_definition_env_env_parser_NonterminalTerm(46),
        silver_definition_env_env_parser_Num_t(47),
        silver_definition_env_env_parser_OccursTerm(48),
        silver_definition_env_env_parser_OptionalGrammarsTerm(49),
        silver_definition_env_env_parser_ProdAttrTerm(50),
        silver_definition_env_env_parser_ProductionTerm(51),
        silver_definition_env_env_parser_RB_t(52),
        silver_definition_env_env_parser_RParent_t(53),
        silver_definition_env_env_parser_RegExprDelim(54),
        silver_definition_env_env_parser_Semi(55),
        silver_definition_env_env_parser_SignatureElementTerm(56),
        silver_definition_env_env_parser_SignatureTerm(57),
        silver_definition_env_env_parser_Sigturnstile(58),
        silver_definition_env_env_parser_StringTerm(59),
        silver_definition_env_env_parser_SynthesizedTerm(60),
        silver_definition_env_env_parser_T_t(61),
        silver_definition_env_env_parser_TerminalTerm(62),
        silver_definition_env_env_parser_WS(63),
        silver_definition_regex_Choice_t(64),
        silver_definition_regex_EscapedChar_t(65),
        silver_definition_regex_Kleene_t(66),
        silver_definition_regex_Optional_t(67),
        silver_definition_regex_Plus_t(68),
        silver_definition_regex_Range_t(69),
        silver_definition_regex_RegexChar_t(70),
        silver_definition_regex_RegexLBrack_t(71),
        silver_definition_regex_RegexLParen_t(72),
        silver_definition_regex_RegexNot_t(73),
        silver_definition_regex_RegexRBrack_t(74),
        silver_definition_regex_RegexRParen_t(75),
        silver_definition_regex_RegexWildcard_t(76);

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
            case 122:
                RESULT = runSemanticAction_122();
                break;
            case 123:
                RESULT = runSemanticAction_123();
                break;
            case 124:
                RESULT = runSemanticAction_124();
                break;
            case 125:
                RESULT = runSemanticAction_125();
                break;
            case 126:
                RESULT = runSemanticAction_126();
                break;
            case 127:
                RESULT = runSemanticAction_127();
                break;
            case 128:
                RESULT = runSemanticAction_128();
                break;
            case 129:
                RESULT = runSemanticAction_129();
                break;
            case 130:
                RESULT = runSemanticAction_130();
                break;
            case 131:
                RESULT = runSemanticAction_131();
                break;
            case 132:
                RESULT = runSemanticAction_132();
                break;
            case 133:
                RESULT = runSemanticAction_133();
                break;
            case 134:
                RESULT = runSemanticAction_134();
                break;
            case 135:
                RESULT = runSemanticAction_135();
                break;
            case 136:
                RESULT = runSemanticAction_136();
                break;
            case 137:
                RESULT = runSemanticAction_137();
                break;
            case 138:
                RESULT = runSemanticAction_138();
                break;
            case 139:
                RESULT = runSemanticAction_139();
                break;
            case 140:
                RESULT = runSemanticAction_140();
                break;
            case 141:
                RESULT = runSemanticAction_141();
                break;
            case 142:
                RESULT = runSemanticAction_142();
                break;
            case 143:
                RESULT = runSemanticAction_143();
                break;
            case 144:
                RESULT = runSemanticAction_144();
                break;
            case 145:
                RESULT = runSemanticAction_145();
                break;
            case 146:
                RESULT = runSemanticAction_146();
                break;
            case 147:
                RESULT = runSemanticAction_147();
                break;
            case 148:
                RESULT = runSemanticAction_148();
                break;
            case 149:
                RESULT = runSemanticAction_149();
                break;
            case 150:
                RESULT = runSemanticAction_150();
                break;
            case 151:
                RESULT = runSemanticAction_151();
                break;
            case 152:
                RESULT = runSemanticAction_152();
                break;
            case 153:
                RESULT = runSemanticAction_153();
                break;
            case 154:
                RESULT = runSemanticAction_154();
                break;
            case 155:
                RESULT = runSemanticAction_155();
                break;
            case 156:
                RESULT = runSemanticAction_156();
                break;
            case 157:
                RESULT = runSemanticAction_157();
                break;
            case 158:
                RESULT = runSemanticAction_158();
                break;
            case 159:
                RESULT = runSemanticAction_159();
                break;
            case 160:
                RESULT = runSemanticAction_160();
                break;
            case 161:
                RESULT = runSemanticAction_161();
                break;
            case 162:
                RESULT = runSemanticAction_162();
                break;
            case 163:
                RESULT = runSemanticAction_163();
                break;
            case 164:
                RESULT = runSemanticAction_164();
                break;
            case 165:
                RESULT = runSemanticAction_165();
                break;
            case 166:
                RESULT = runSemanticAction_166();
                break;
            case 167:
                RESULT = runSemanticAction_167();
                break;
            case 168:
                RESULT = runSemanticAction_168();
                break;
            case 169:
                RESULT = runSemanticAction_169();
                break;
            case 170:
                RESULT = runSemanticAction_170();
                break;
            case 171:
                RESULT = runSemanticAction_171();
                break;
            case 172:
                RESULT = runSemanticAction_172();
                break;
            case 173:
                RESULT = runSemanticAction_173();
                break;
            case 174:
                RESULT = runSemanticAction_174();
                break;
            case 175:
                RESULT = runSemanticAction_175();
                break;
            case 176:
                RESULT = runSemanticAction_176();
                break;
            case 177:
                RESULT = runSemanticAction_177();
                break;
            case 178:
                RESULT = runSemanticAction_178();
                break;
            case 179:
                RESULT = runSemanticAction_179();
                break;
            case 180:
                RESULT = runSemanticAction_180();
                break;
            case 181:
                RESULT = runSemanticAction_181();
                break;
            case 182:
                RESULT = runSemanticAction_182();
                break;
            case 183:
                RESULT = runSemanticAction_183();
                break;
            case 184:
                RESULT = runSemanticAction_184();
                break;
            case 185:
                RESULT = runSemanticAction_185();
                break;
            case 186:
                RESULT = runSemanticAction_186();
                break;
            case 187:
                RESULT = runSemanticAction_187();
                break;
            case 188:
                RESULT = runSemanticAction_188();
                break;
            case 189:
                RESULT = runSemanticAction_189();
                break;
            case 190:
                RESULT = runSemanticAction_190();
                break;
            case 191:
                RESULT = runSemanticAction_191();
                break;
            case 192:
                RESULT = runSemanticAction_192();
                break;
            case 193:
                RESULT = runSemanticAction_193();
                break;
            case 194:
                RESULT = runSemanticAction_194();
                break;
            case 195:
                RESULT = runSemanticAction_195();
                break;
            case 196:
                RESULT = runSemanticAction_196();
                break;
            case 197:
                RESULT = runSemanticAction_197();
                break;
            case 198:
                RESULT = runSemanticAction_198();
                break;
            case 199:
                RESULT = runSemanticAction_199();
                break;
            case 200:
                RESULT = runSemanticAction_200();
                break;
            case 201:
                RESULT = runSemanticAction_201();
                break;
            case 202:
                RESULT = runSemanticAction_202();
                break;
            case 203:
                RESULT = runSemanticAction_203();
                break;
            case 204:
                RESULT = runSemanticAction_204();
                break;
            case 205:
                RESULT = runSemanticAction_205();
                break;
            case 206:
                RESULT = runSemanticAction_206();
                break;
            case 207:
                RESULT = runSemanticAction_207();
                break;
            case 208:
                RESULT = runSemanticAction_208();
                break;
            case 209:
                RESULT = runSemanticAction_209();
                break;
            case 210:
                RESULT = runSemanticAction_210();
                break;
            case 211:
                RESULT = runSemanticAction_211();
                break;
            case 212:
                RESULT = runSemanticAction_212();
                break;
            case 213:
                RESULT = runSemanticAction_213();
                break;
            case 214:
                RESULT = runSemanticAction_214();
                break;
            case 215:
                RESULT = runSemanticAction_215();
                break;
            case 216:
                RESULT = runSemanticAction_216();
                break;
            case 217:
                RESULT = runSemanticAction_217();
                break;
            case 218:
                RESULT = runSemanticAction_218();
                break;
            case 219:
                RESULT = runSemanticAction_219();
                break;
            case 220:
                RESULT = runSemanticAction_220();
                break;
            case 221:
                RESULT = runSemanticAction_221();
                break;
            case 222:
                RESULT = runSemanticAction_222();
                break;
            case 223:
                RESULT = runSemanticAction_223();
                break;
            case 224:
                RESULT = runSemanticAction_224();
                break;
            case 225:
                RESULT = runSemanticAction_225();
                break;
            case 226:
                RESULT = runSemanticAction_226();
                break;
            case 227:
                RESULT = runSemanticAction_227();
                break;
            case 228:
                RESULT = runSemanticAction_228();
                break;
            case 229:
                RESULT = runSemanticAction_229();
                break;
            case 230:
                RESULT = runSemanticAction_230();
                break;
            case 231:
                RESULT = runSemanticAction_231();
                break;
            case 232:
                RESULT = runSemanticAction_232();
                break;
            case 233:
                RESULT = runSemanticAction_233();
                break;
            case 234:
                RESULT = runSemanticAction_234();
                break;
            case 235:
                RESULT = runSemanticAction_235();
                break;
            case 236:
                RESULT = runSemanticAction_236();
                break;
            case 237:
                RESULT = runSemanticAction_237();
                break;
            case 238:
                RESULT = runSemanticAction_238();
                break;
            case 239:
                RESULT = runSemanticAction_239();
                break;
            case 240:
                RESULT = runSemanticAction_240();
                break;
            case 241:
                RESULT = runSemanticAction_241();
                break;
            case 242:
                RESULT = runSemanticAction_242();
                break;
            case 243:
                RESULT = runSemanticAction_243();
                break;
            case 244:
                RESULT = runSemanticAction_244();
                break;
            case 245:
                RESULT = runSemanticAction_245();
                break;
            case 246:
                RESULT = runSemanticAction_246();
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
            case 13:
                RESULT = runSemanticAction_13(lexeme);
                break;
            case 14:
                RESULT = runSemanticAction_14(lexeme);
                break;
            case 15:
                RESULT = runSemanticAction_15(lexeme);
                break;
            case 16:
                RESULT = runSemanticAction_16(lexeme);
                break;
            case 17:
                RESULT = runSemanticAction_17(lexeme);
                break;
            case 18:
                RESULT = runSemanticAction_18(lexeme);
                break;
            case 19:
                RESULT = runSemanticAction_19(lexeme);
                break;
            case 20:
                RESULT = runSemanticAction_20(lexeme);
                break;
            case 21:
                RESULT = runSemanticAction_21(lexeme);
                break;
            case 22:
                RESULT = runSemanticAction_22(lexeme);
                break;
            case 23:
                RESULT = runSemanticAction_23(lexeme);
                break;
            case 24:
                RESULT = runSemanticAction_24(lexeme);
                break;
            case 25:
                RESULT = runSemanticAction_25(lexeme);
                break;
            case 26:
                RESULT = runSemanticAction_26(lexeme);
                break;
            case 27:
                RESULT = runSemanticAction_27(lexeme);
                break;
            case 28:
                RESULT = runSemanticAction_28(lexeme);
                break;
            case 29:
                RESULT = runSemanticAction_29(lexeme);
                break;
            case 30:
                RESULT = runSemanticAction_30(lexeme);
                break;
            case 31:
                RESULT = runSemanticAction_31(lexeme);
                break;
            case 32:
                RESULT = runSemanticAction_32(lexeme);
                break;
            case 33:
                RESULT = runSemanticAction_33(lexeme);
                break;
            case 34:
                RESULT = runSemanticAction_34(lexeme);
                break;
            case 35:
                RESULT = runSemanticAction_35(lexeme);
                break;
            case 36:
                RESULT = runSemanticAction_36(lexeme);
                break;
            case 37:
                RESULT = runSemanticAction_37(lexeme);
                break;
            case 38:
                RESULT = runSemanticAction_38(lexeme);
                break;
            case 39:
                RESULT = runSemanticAction_39(lexeme);
                break;
            case 40:
                RESULT = runSemanticAction_40(lexeme);
                break;
            case 41:
                RESULT = runSemanticAction_41(lexeme);
                break;
            case 42:
                RESULT = runSemanticAction_42(lexeme);
                break;
            case 43:
                RESULT = runSemanticAction_43(lexeme);
                break;
            case 44:
                RESULT = runSemanticAction_44(lexeme);
                break;
            case 45:
                RESULT = runSemanticAction_45(lexeme);
                break;
            case 46:
                RESULT = runSemanticAction_46(lexeme);
                break;
            case 47:
                RESULT = runSemanticAction_47(lexeme);
                break;
            case 48:
                RESULT = runSemanticAction_48(lexeme);
                break;
            case 49:
                RESULT = runSemanticAction_49(lexeme);
                break;
            case 50:
                RESULT = runSemanticAction_50(lexeme);
                break;
            case 51:
                RESULT = runSemanticAction_51(lexeme);
                break;
            case 52:
                RESULT = runSemanticAction_52(lexeme);
                break;
            case 53:
                RESULT = runSemanticAction_53(lexeme);
                break;
            case 54:
                RESULT = runSemanticAction_54(lexeme);
                break;
            case 55:
                RESULT = runSemanticAction_55(lexeme);
                break;
            case 56:
                RESULT = runSemanticAction_56(lexeme);
                break;
            case 57:
                RESULT = runSemanticAction_57(lexeme);
                break;
            case 58:
                RESULT = runSemanticAction_58(lexeme);
                break;
            case 59:
                RESULT = runSemanticAction_59(lexeme);
                break;
            case 60:
                RESULT = runSemanticAction_60(lexeme);
                break;
            case 61:
                RESULT = runSemanticAction_61(lexeme);
                break;
            case 62:
                RESULT = runSemanticAction_62(lexeme);
                break;
            case 63:
                RESULT = runSemanticAction_63(lexeme);
                break;
            case 64:
                RESULT = runSemanticAction_64(lexeme);
                break;
            case 65:
                RESULT = runSemanticAction_65(lexeme);
                break;
            case 66:
                RESULT = runSemanticAction_66(lexeme);
                break;
            case 67:
                RESULT = runSemanticAction_67(lexeme);
                break;
            case 68:
                RESULT = runSemanticAction_68(lexeme);
                break;
            case 69:
                RESULT = runSemanticAction_69(lexeme);
                break;
            case 70:
                RESULT = runSemanticAction_70(lexeme);
                break;
            case 71:
                RESULT = runSemanticAction_71(lexeme);
                break;
            case 72:
                RESULT = runSemanticAction_72(lexeme);
                break;
            case 73:
                RESULT = runSemanticAction_73(lexeme);
                break;
            case 74:
                RESULT = runSemanticAction_74(lexeme);
                break;
            case 75:
                RESULT = runSemanticAction_75(lexeme);
                break;
            case 76:
                RESULT = runSemanticAction_76(lexeme);
                break;
            default:
        runDefaultTermAction();
                 break;
            }
            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifier runSemanticAction_122()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifier RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaLexerClassModifierDominates(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifier runSemanticAction_123()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifier RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaLexerClassModifierSubmits(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifiers runSemanticAction_124()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifiers RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaLexerClassModifiersNone(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifiers runSemanticAction_125()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifiers RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaLexerClassModifiersOne(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifiersInner runSemanticAction_126()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifiersInner RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaLexerClassModifierInnersCons(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifiersInner runSemanticAction_127()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifiersInner RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaLexerClassModifiersInnerOne(_children[0]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NIParser runSemanticAction_128()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NIParser RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaParser(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9], _children[10], _children[11], _children[12], _children[13], _children[14], _children[15], _children[16], _children[17]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NIParsers runSemanticAction_129()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NIParsers RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaParsersNone(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NIParsers runSemanticAction_130()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NIParsers RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaParsersSome(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NIParsersInner runSemanticAction_131()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NIParsersInner RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaParsersCons(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NIParsersInner runSemanticAction_132()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NIParsersInner RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaParsersOne(_children[0]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NIProductionModifier runSemanticAction_133()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NIProductionModifier RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaProductionModifierAcode(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NIProductionModifier runSemanticAction_134()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NIProductionModifier RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaProductionModifierLayout(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NIProductionModifier runSemanticAction_135()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NIProductionModifier RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaProductionModifierOperator(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NIProductionModifier runSemanticAction_136()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NIProductionModifier RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaProductionModifierPrecedence(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NIProductionModifiers runSemanticAction_137()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NIProductionModifiers RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaProductionModifiersNone(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NIProductionModifiers runSemanticAction_138()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NIProductionModifiers RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaProductionModifiersOne(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NIProductionModifiersInner runSemanticAction_139()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NIProductionModifiersInner RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaProductionModifierInnersCons(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NIProductionModifiersInner runSemanticAction_140()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NIProductionModifiersInner RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaProductionModifiersInnerOne(_children[0]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NISyntax runSemanticAction_141()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NISyntax RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaSyntaxNone(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NISyntax runSemanticAction_142()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NISyntax RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaSyntaxSome(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl runSemanticAction_143()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaSyntaxDisambig(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl runSemanticAction_144()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaSyntaxLclass(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl runSemanticAction_145()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaSyntaxNt(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl runSemanticAction_146()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaSyntaxPattr(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl runSemanticAction_147()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaSyntaxProd(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl runSemanticAction_148()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaSyntaxTerm(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NISyntaxInner runSemanticAction_149()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NISyntaxInner RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaSyntaxCons(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NISyntaxInner runSemanticAction_150()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NISyntaxInner RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaSyntaxOne(_children[0]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier runSemanticAction_151()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifierAcode(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier runSemanticAction_152()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifierAssoc(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier runSemanticAction_153()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifierClasses(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier runSemanticAction_154()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifierDominates(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier runSemanticAction_155()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifierIgnore(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier runSemanticAction_156()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifierMarking(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier runSemanticAction_157()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifierPrec(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier runSemanticAction_158()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifierSubmits(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiers runSemanticAction_159()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiers RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifiersNone(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiers runSemanticAction_160()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiers RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifiersOne(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiersInner runSemanticAction_161()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiersInner RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifierInnersCons(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiersInner runSemanticAction_162()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiersInner RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifiersInnerOne(_children[0]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIBool runSemanticAction_163()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIBool RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaFalse(_children[0]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIBool runSemanticAction_164()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIBool RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTrue(_children[0]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_165()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDclInfoAnno(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_166()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDclInfoAnnoInstance(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9], _children[10], _children[11], _children[12], _children[13]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_167()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDclInfoForward(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_168()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDclInfoFunction(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_169()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDclInfoGlobalValue(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_170()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDclInfoInherited(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_171()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDclInfoLocal(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_172()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDclInfoNonterminal(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9], _children[10], _children[11]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_173()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDclInfoOccurs(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9], _children[10], _children[11], _children[12], _children[13]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_174()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDclInfoProdAttr(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_175()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDclInfoProduction(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_176()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDclInfoSynthesized(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_177()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDclInfoTerminal(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDefs runSemanticAction_178()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDefs RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDefsNone(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDefs runSemanticAction_179()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDefs RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDefsOne(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDefsInner runSemanticAction_180()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDefsInner RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDefsInnerCons(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDefsInner runSemanticAction_181()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDefsInner RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDefsInnerOne(_children[0]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NILocation runSemanticAction_182()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NILocation RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaLocationInfo(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9], _children[10], _children[11], _children[12]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIName runSemanticAction_183()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIName RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaName(_children[0]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NINamedArgTypes runSemanticAction_184()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NINamedArgTypes RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaNamedArgTypeCons(_children[0], _children[1], _children[2], _children[3], _children[4]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NINamedArgTypes runSemanticAction_185()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NINamedArgTypes RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaNamedArgTypeNil();

            return RESULT;
        }
        public silver.definition.env.env_parser.NINamedSignature runSemanticAction_186()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NINamedSignature RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaNamedSignatureDcl(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NINamedSignatureElement runSemanticAction_187()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NINamedSignatureElement RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaNamedSignatureElementDcl(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NINamedSignatureElements runSemanticAction_188()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NINamedSignatureElements RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaNamedSignatureElementsNone(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NINamedSignatureElements runSemanticAction_189()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NINamedSignatureElements RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaNamedSignatureElementsOne(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NINamedSignatureElementsInner runSemanticAction_190()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NINamedSignatureElementsInner RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaNamedSignatureElementsInnerCons(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NINamedSignatureElementsInner runSemanticAction_191()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NINamedSignatureElementsInner RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaNamedSignatureElementsInnerOne(_children[0]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NINames runSemanticAction_192()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NINames RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaNamesNone(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NINames runSemanticAction_193()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NINames RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaNamesOne(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NINamesInner runSemanticAction_194()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NINamesInner RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaNamesInnerCons(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NINamesInner runSemanticAction_195()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NINamesInner RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaNamesInnerOne(_children[0]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIRoot runSemanticAction_196()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIRoot RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaRoot1(_children[0]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIRoot runSemanticAction_197()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIRoot RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaRoot2(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIRootPart runSemanticAction_198()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIRootPart RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaRootParsers(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIRootPart runSemanticAction_199()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIRootPart RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaRootSyntax(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIRootPart runSemanticAction_200()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIRootPart RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaRootAllDeps(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIRootPart runSemanticAction_201()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIRootPart RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaRootCondBuilds(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIRootPart runSemanticAction_202()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIRootPart RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaRootDeclaredName(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIRootPart runSemanticAction_203()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIRootPart RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaRootDefs(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIRootPart runSemanticAction_204()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIRootPart RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaRootExportedGrammars(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIRootPart runSemanticAction_205()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIRootPart RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaRootGrammarSource(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIRootPart runSemanticAction_206()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIRootPart RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaRootGrammarTime(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIRootPart runSemanticAction_207()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIRootPart RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaRootModuleNames(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIRootPart runSemanticAction_208()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIRootPart RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaRootOptionalGrammars(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIString runSemanticAction_209()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIString RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaString(_children[0]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITyVarDcls runSemanticAction_210()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITyVarDcls RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTyVarDclsNone(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITyVarDcls runSemanticAction_211()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITyVarDcls RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTyVarDclsOne(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITyVarDclsInner runSemanticAction_212()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITyVarDclsInner RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTyVarDclsInnerCons(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITyVarDclsInner runSemanticAction_213()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITyVarDclsInner RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTyVarDclsInnerOne(_children[0]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITypeRep runSemanticAction_214()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITypeRep RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTypeRepBoolean(_children[0]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITypeRep runSemanticAction_215()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITypeRep RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTypeRepDecorated(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITypeRep runSemanticAction_216()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITypeRep RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTypeRepFloat(_children[0]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITypeRep runSemanticAction_217()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITypeRep RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTypeRepFunction(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITypeRep runSemanticAction_218()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITypeRep RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTypeRepInteger(_children[0]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITypeRep runSemanticAction_219()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITypeRep RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTypeRepNonterminal(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITypeRep runSemanticAction_220()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITypeRep RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTypeRepString(_children[0]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITypeRep runSemanticAction_221()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITypeRep RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTypeRepTerminal(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITypeRep runSemanticAction_222()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITypeRep RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTypeRepVar(_children[0]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITypeReps runSemanticAction_223()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITypeReps RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTypeRepsNone(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITypeReps runSemanticAction_224()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITypeReps RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTypeRepsOne(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITypeRepsInner runSemanticAction_225()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITypeRepsInner RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTypeRepsInnerCons(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITypeRepsInner runSemanticAction_226()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITypeRepsInner RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTypeRepsInnerOne(_children[0]);

            return RESULT;
        }
        public silver.definition.regex.NRegex runSemanticAction_227()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegex RESULT = null;
            
RESULT = new silver.definition.regex.PregexChoice(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.regex.NRegex runSemanticAction_228()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegex RESULT = null;
            
RESULT = new silver.definition.regex.PregexEpsilon();

            return RESULT;
        }
        public silver.definition.regex.NRegex runSemanticAction_229()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegex RESULT = null;
            
RESULT = new silver.definition.regex.PregexSeq(_children[0]);

            return RESULT;
        }
        public silver.definition.regex.NRegexChar runSemanticAction_230()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexChar RESULT = null;
            
RESULT = new silver.definition.regex.PregexChar(_children[0]);

            return RESULT;
        }
        public silver.definition.regex.NRegexChar runSemanticAction_231()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexChar RESULT = null;
            
RESULT = new silver.definition.regex.PregexEscapedChar(_children[0]);

            return RESULT;
        }
        public silver.definition.regex.NRegexCharSet runSemanticAction_232()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexCharSet RESULT = null;
            
RESULT = new silver.definition.regex.PregexCharSetOne(_children[0]);

            return RESULT;
        }
        public silver.definition.regex.NRegexCharSet runSemanticAction_233()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexCharSet RESULT = null;
            
RESULT = new silver.definition.regex.PregexCharSetSnoc(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.regex.NRegexCharSetItem runSemanticAction_234()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexCharSetItem RESULT = null;
            
RESULT = new silver.definition.regex.PregexSetChar(_children[0]);

            return RESULT;
        }
        public silver.definition.regex.NRegexCharSetItem runSemanticAction_235()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexCharSetItem RESULT = null;
            
RESULT = new silver.definition.regex.PregexSetRange(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.regex.NRegexItem runSemanticAction_236()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexItem RESULT = null;
            
RESULT = new silver.definition.regex.PregexCharItem(_children[0]);

            return RESULT;
        }
        public silver.definition.regex.NRegexItem runSemanticAction_237()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexItem RESULT = null;
            
RESULT = new silver.definition.regex.PregexGroup(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.regex.NRegexItem runSemanticAction_238()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexItem RESULT = null;
            
RESULT = new silver.definition.regex.PregexSet(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.regex.NRegexItem runSemanticAction_239()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexItem RESULT = null;
            
RESULT = new silver.definition.regex.PregexSetInverted(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.regex.NRegexItem runSemanticAction_240()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexItem RESULT = null;
            
RESULT = new silver.definition.regex.PregexWildcard(_children[0]);

            return RESULT;
        }
        public silver.definition.regex.NRegexRepetition runSemanticAction_241()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexRepetition RESULT = null;
            
RESULT = new silver.definition.regex.PregexKleene(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.regex.NRegexRepetition runSemanticAction_242()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexRepetition RESULT = null;
            
RESULT = new silver.definition.regex.PregexOnce(_children[0]);

            return RESULT;
        }
        public silver.definition.regex.NRegexRepetition runSemanticAction_243()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexRepetition RESULT = null;
            
RESULT = new silver.definition.regex.PregexOptional(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.regex.NRegexRepetition runSemanticAction_244()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexRepetition RESULT = null;
            
RESULT = new silver.definition.regex.PregexPlus(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.regex.NRegexSeq runSemanticAction_245()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexSeq RESULT = null;
            
RESULT = new silver.definition.regex.PregexSeqOne(_children[0]);

            return RESULT;
        }
        public silver.definition.regex.NRegexSeq runSemanticAction_246()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexSeq RESULT = null;
            
RESULT = new silver.definition.regex.PregexSeqSnoc(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.TAcodeTerm runSemanticAction_1(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.TAcodeTerm RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.TAcodeTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.TAssocTerm runSemanticAction_2(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.TAssocTerm RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.TAssocTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.TClassesTerm runSemanticAction_3(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.TClassesTerm RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.TClassesTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.TDisTerm runSemanticAction_4(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.TDisTerm RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.TDisTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.TDomTerm runSemanticAction_5(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.TDomTerm RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.TDomTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.TIgnoreTerm runSemanticAction_6(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.TIgnoreTerm RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.TIgnoreTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.TLClassTerm runSemanticAction_7(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.TLClassTerm RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.TLClassTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.TLayoutTerm runSemanticAction_8(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.TLayoutTerm RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.TLayoutTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.TMarkingTerm runSemanticAction_9(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.TMarkingTerm RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.TMarkingTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.TOperTerm runSemanticAction_10(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.TOperTerm RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.TOperTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.TParAttr runSemanticAction_11(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.TParAttr RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.TParAttr(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.TParserTerm runSemanticAction_12(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.TParserTerm RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.TParserTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.TParsersTerm runSemanticAction_13(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.TParsersTerm RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.TParsersTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.TPrecTerm runSemanticAction_14(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.TPrecTerm RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.TPrecTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.TSubTerm runSemanticAction_15(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.TSubTerm RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.TSubTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.TSyntaxTerm runSemanticAction_16(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.TSyntaxTerm RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.TSyntaxTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TAllDepsTerm runSemanticAction_17(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TAllDepsTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TAllDepsTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TAnnoAt_t runSemanticAction_18(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TAnnoAt_t RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TAnnoAt_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TAnno_t runSemanticAction_19(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TAnno_t RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TAnno_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TBooleanTerm runSemanticAction_20(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TBooleanTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TBooleanTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TComma_t runSemanticAction_21(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TComma_t RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TComma_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TCondBuildTerm runSemanticAction_22(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TCondBuildTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TCondBuildTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TDeclaredNameTerm runSemanticAction_23(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TDeclaredNameTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TDeclaredNameTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TDecoratedTerm runSemanticAction_24(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TDecoratedTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TDecoratedTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TDefaultTerm runSemanticAction_25(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TDefaultTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TDefaultTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TDefsTerm runSemanticAction_26(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TDefsTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TDefsTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TEq runSemanticAction_27(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TEq RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TEq(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TEscapedStringTerm runSemanticAction_28(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TEscapedStringTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TEscapedStringTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TExportedGrammarsTerm runSemanticAction_29(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TExportedGrammarsTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TExportedGrammarsTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TF_t runSemanticAction_30(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TF_t RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TF_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TFloatTerm runSemanticAction_31(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TFloatTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TFloatTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TForwardTerm runSemanticAction_32(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TForwardTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TForwardTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TFunctionTerm runSemanticAction_33(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TFunctionTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TFunctionTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TGlobTerm runSemanticAction_34(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TGlobTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TGlobTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TGrammarSourceTerm runSemanticAction_35(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TGrammarSourceTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TGrammarSourceTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TGrammarTimeTerm runSemanticAction_36(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TGrammarTimeTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TGrammarTimeTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TIOTerm runSemanticAction_37(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TIOTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TIOTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TITyVar runSemanticAction_38(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TITyVar RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TITyVar(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TId_t runSemanticAction_39(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TId_t RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TId_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TInheritedTerm runSemanticAction_40(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TInheritedTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TInheritedTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TIntegerTerm runSemanticAction_41(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TIntegerTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TIntegerTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TLB_t runSemanticAction_42(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TLB_t RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TLB_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TLParent_t runSemanticAction_43(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TLParent_t RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TLParent_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TLocalTerm runSemanticAction_44(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TLocalTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TLocalTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TModuleNamesTerm runSemanticAction_45(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TModuleNamesTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TModuleNamesTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TNonterminalTerm runSemanticAction_46(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TNonterminalTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TNonterminalTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TNum_t runSemanticAction_47(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TNum_t RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TNum_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TOccursTerm runSemanticAction_48(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TOccursTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TOccursTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TOptionalGrammarsTerm runSemanticAction_49(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TOptionalGrammarsTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TOptionalGrammarsTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TProdAttrTerm runSemanticAction_50(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TProdAttrTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TProdAttrTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TProductionTerm runSemanticAction_51(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TProductionTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TProductionTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TRB_t runSemanticAction_52(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TRB_t RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TRB_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TRParent_t runSemanticAction_53(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TRParent_t RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TRParent_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TRegExprDelim runSemanticAction_54(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TRegExprDelim RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TRegExprDelim(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TSemi runSemanticAction_55(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TSemi RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TSemi(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TSignatureElementTerm runSemanticAction_56(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TSignatureElementTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TSignatureElementTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TSignatureTerm runSemanticAction_57(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TSignatureTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TSignatureTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TSigturnstile runSemanticAction_58(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TSigturnstile RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TSigturnstile(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TStringTerm runSemanticAction_59(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TStringTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TStringTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TSynthesizedTerm runSemanticAction_60(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TSynthesizedTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TSynthesizedTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TT_t runSemanticAction_61(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TT_t RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TT_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TTerminalTerm runSemanticAction_62(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TTerminalTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TTerminalTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TWS runSemanticAction_63(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TWS RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TWS(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.regex.TChoice_t runSemanticAction_64(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.TChoice_t RESULT = null;
            
RESULT = new silver.definition.regex.TChoice_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.regex.TEscapedChar_t runSemanticAction_65(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.TEscapedChar_t RESULT = null;
            
RESULT = new silver.definition.regex.TEscapedChar_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.regex.TKleene_t runSemanticAction_66(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.TKleene_t RESULT = null;
            
RESULT = new silver.definition.regex.TKleene_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.regex.TOptional_t runSemanticAction_67(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.TOptional_t RESULT = null;
            
RESULT = new silver.definition.regex.TOptional_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.regex.TPlus_t runSemanticAction_68(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.TPlus_t RESULT = null;
            
RESULT = new silver.definition.regex.TPlus_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.regex.TRange_t runSemanticAction_69(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.TRange_t RESULT = null;
            
RESULT = new silver.definition.regex.TRange_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.regex.TRegexChar_t runSemanticAction_70(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.TRegexChar_t RESULT = null;
            
RESULT = new silver.definition.regex.TRegexChar_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.regex.TRegexLBrack_t runSemanticAction_71(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.TRegexLBrack_t RESULT = null;
            
RESULT = new silver.definition.regex.TRegexLBrack_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.regex.TRegexLParen_t runSemanticAction_72(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.TRegexLParen_t RESULT = null;
            
RESULT = new silver.definition.regex.TRegexLParen_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.regex.TRegexNot_t runSemanticAction_73(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.TRegexNot_t RESULT = null;
            
RESULT = new silver.definition.regex.TRegexNot_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.regex.TRegexRBrack_t runSemanticAction_74(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.TRegexRBrack_t RESULT = null;
            
RESULT = new silver.definition.regex.TRegexRBrack_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.regex.TRegexRParen_t runSemanticAction_75(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.TRegexRParen_t RESULT = null;
            
RESULT = new silver.definition.regex.TRegexRParen_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.regex.TRegexWildcard_t runSemanticAction_76(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.TRegexWildcard_t RESULT = null;
            
RESULT = new silver.definition.regex.TRegexWildcard_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
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
            @SuppressWarnings("unused") final int silver_definition_env_env_parser_WS = 63;
            @SuppressWarnings("unused") final int silver_definition_regex_RegexChar_t = 70;
            
return (Integer)silver_definition_env_env_parser_WS;

        }
        public int disambiguate_1(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            @SuppressWarnings("unused") final int silver_definition_env_env_parser_RegExprDelim = 54;
            @SuppressWarnings("unused") final int silver_definition_regex_RegexChar_t = 70;
            
return (Integer)silver_definition_env_env_parser_RegExprDelim;

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
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\255\132\133\163\343\064" +
"\024\366\300\054\177\200\347\345\176\277\024\166\201\345\262\300" +
"\220\066\155\061\233\066\231\070\263\373\300\103\106\165\324\324" +
"\254\043\245\262\134\132\370\113\360\147\370\023\014\017\074\363" +
"\212\164\044\071\156\342\104\307\252\137\332\231\366\373\216\165" +
"\256\072\322\321\037\377\106\367\112\021\275\374\363\340\027\162" +
"\105\366\162\302\346\173\211\024\031\233\077\376\363\257\247\177" +
"\377\163\377\367\343\027\242\350\172\031\105\321\177\062\172\361" +
"\160\170\044\243\157\212\054\277\242\142\072\243\347\031\313\144" +
"\306\331\064\345\054\025\124\322\151\161\303\044\271\236\222\102" +
"\116\051\273\232\056\211\050\024\262\227\362\031\235\120\261\010" +
"\042\027\005\117\015\371\333\326\344\203\234\024\005\055\014\375" +
"\253\326\364\176\026\116\345\013\103\175\334\232\032\317\031\027" +
"\064\224\075\000\235\203\331\344\206\227\062\324\334\047\104\074" +
"\127\261\143\350\137\267\246\017\227\124\204\332\173\104\104\117" +
"\112\021\242\363\010\176\205\352\154\330\105\250\316\043\101\323" +
"\120\235\223\362\054\324\317\011\374\325\260\077\332\144\153\144" +
"\075\007\363\274\117\227\126\307\367\375\160\306\170\117\116\245" +
"\214\336\105\141\065\322\277\210\175\316\163\112\230\131\304\173" +
"\136\370\001\137\054\210\226\274\207\200\262\331\176\231\345\063" +
"\043\373\201\227\320\247\151\116\004\235\235\222\205\315\123\377" +
"\107\024\207\013\042\351\014\153\364\076\075\047\145\056\261\106" +
"\127\160\353\240\067\275\330\303\113\031\075\364\243\212\224\054" +
"\351\314\354\007\106\364\027\176\322\365\222\013\245\345\261\040" +
"\312\376\056\057\336\362\362\216\264\253\076\360\303\162\116\044" +
"\326\202\107\134\374\112\204\065\370\307\176\170\311\122\375\167" +
"\254\305\217\163\156\363\317\157\113\153\216\204\227\042\265\041" +
"\363\051\226\064\311\134\224\371\323\051\036\242\221\223\233\247" +
"\104\325\313\267\375\310\031\056\215\142\166\101\105\326\042\302" +
"\143\046\351\334\125\136\377\072\006\373\270\030\031\250\222\114" +
"\231\104\202\171\112\162\254\103\116\370\254\314\251\316\372\002" +
"\113\071\345\112\111\261\310\230\373\312\073\176\112\271\320\153" +
"\377\320\013\034\246\151\351\062\314\237\231\303\245\376\053\311" +
"\157\147\246\077\055\106\202\317\364\326\152\360\237\240\360\145" +
"\055\221\374\216\035\043\035\073\136\071\326\277\354\061\235\253" +
"\132\044\372\064\317\120\213\110\350\042\303\230\061\311\346\214" +
"\310\122\320\303\234\056\324\152\260\365\277\342\141\355\256\010" +
"\012\316\012\231\345\024\023\015\365\072\355\217\113\335\000\134" +
"\320\042\373\315\245\253\277\104\117\160\206\237\334\012\167\377" +
"\146\364\054\221\321\153\233\050\241\112\303\365\364\340\202\147" +
"\051\325\037\156\360\240\201\330\155\352\340\202\010\215\333\052" +
"\352\111\116\051\003\121\157\154\203\270\024\321\240\127\266\201" +
"\106\171\131\150\300\253\333\000\143\165\212\202\357\064\350\156" +
"\021\372\247\133\360\126\305\000\065\330\027\044\175\216\300\101" +
"\156\354\324\016\160\247\134\372\205\215\221\037\035\127\037\155" +
"\330\157\152\270\147\252\267\112\325\116\254\221\367\222\111\157" +
"\074\221\121\277\375\271\150\100\257\251\200\343\215\052\304\331" +
"\171\106\325\366\165\330\205\230\102\106\077\165\042\047\146\114" +
"\057\252\175\053\037\233\143\104\310\011\302\122\225\016\337\007" +
"\163\355\272\003\174\262\252\366\167\362\311\246\230\060\237\064" +
"\310\011\367\211\071\044\005\035\341\015\265\237\346\062\372\056" +
"\224\155\027\276\337\236\357\112\360\312\045\007\167\027\242\034" +
"\362\143\007\122\254\126\376\066\050\326\007\076\114\037\036\053" +
"\063\307\354\234\243\204\352\143\022\146\067\005\240\135\253\277" +
"\063\211\165\033\251\377\201\132\203\156\040\061\235\024\000\147" +
"\075\061\237\334\054\151\201\331\325\015\243\152\065\144\364\145" +
"\133\212\355\152\032\363\005\305\054\032\043\036\107\265\366\106" +
"\034\136\240\007\107\235\061\000\211\017\272\061\347\250\136\024" +
"\200\252\172\112\314\135\104\154\172\063\124\334\301\261\114\105" +
"\064\316\337\025\332\152\210\310\026\035\114\143\272\104\051\151" +
"\261\005\052\132\035\330\056\345\376\316\226\100\106\257\173\273" +
"\243\306\246\164\015\223\320\146\017\154\302\142\111\027\276\257" +
"\032\314\356\146\106\251\110\145\146\222\175\153\263\011\310\204" +
"\136\312\350\045\350\167\106\062\072\151\135\074\311\146\207\321" +
"\347\272\232\112\035\373\117\272\220\227\224\147\352\330\243\244" +
"\305\135\110\053\324\151\227\206\154\023\115\262\206\132\324\151" +
"\027\242\040\040\213\003\256\316\122\335\170\301\204\070\054\260" +
"\175\133\101\134\253\327\276\061\260\124\153\346\160\176\302\027" +
"\167\342\033\123\266\277\053\167\174\260\134\100\304\155\166\167" +
"\060\132\012\351\024\033\144\231\331\207\214\006\135\010\323\263" +
"\014\042\271\010\012\341\115\161\172\114\100\147\224\245\135\031" +
"\056\074\125\033\144\205\246\352\246\250\073\246\352\266\366\037" +
"\026\030\020\257\246\021\067\246\012\246\233\154\373\041\224\336" +
"\317\012\262\070\313\346\041\347\073\053\142\220\352\352\025\062" +
"\175\165\026\220\101\345\302\220\107\004\006\163\301\366\323\116" +
"\275\003\075\164\260\147\351\046\026\333\037\001\055\035\042\357" +
"\270\075\173\375\334\144\013\135\027\222\364\100\075\250\212\254" +
"\113\262\323\365\240\126\144\135\126\255\261\011\250\111\353\322" +
"\314\010\275\023\045\355\114\133\106\107\167\227\245\253\170\047" +
"\213\252\332\266\016\342\301\356\004\035\250\147\366\201\200\355" +
"\163\303\175\265\135\240\203\330\252\355\001\376\243\045\071\042" +
"\171\101\061\147\105\062\021\045\305\334\210\023\173\101\241\147" +
"\335\230\021\103\035\037\053\033\020\330\366\375\247\101\307\263" +
"\163\117\314\044\273\242\330\331\247\214\076\107\163\364\374\223" +
"\344\117\111\256\255\340\037\201\072\132\065\046\304\114\115\034" +
"\011\306\164\155\026\127\233\273\141\016\257\216\146\106\152\155" +
"\054\347\306\143\062\372\254\025\247\154\155\357\332\310\246\315" +
"\002\047\225\031\374\007\176\242\157\275\114\075\360\337\044\000" +
"\030\262\012\143\137\167\235\146\262\032\343\171\307\200\117\040" +
"\010\356\016\016\173\027\110\314\075\034\042\162\353\367\160\146" +
"\375\010\363\327\111\247\131\216\212\217\333\327\142\160\175\334" +
"\320\263\355\146\331\313\064\040\067\164\075\050\262\215\201\206" +
"\256\007\107\007\227\365\102\331\265\070\151\350\232\133\310\200" +
"\145\370\357\334\100\204\125\031\221\043\200\006\321\210\222\274" +
"\272\176\064\012\041\122\145\105\301\156\131\372\056\362\001\032" +
"\371\060\250\227\267\367\235\146\322\024\320\115\153\276\033\250" +
"\040\266\114\015\267\317\316\120\166\326\370\352\035\127\201\312" +
"\152\115\251\277\344\102\071\337\220\364\014\341\021\016\274\376" +
"\040\012\125\007\064\361\326\223\041\124\305\251\261\364\233\041" +
"\064\247\366\254\005\255\326\372\153\022\314\065\070\161\327\340" +
"\210\044\250\056\266\115\132\042\312\177\305\200\254\101\154\253" +
"\267\357\316\115\172\042\174\163\233\206\255\003\366\162\334\076" +
"\142\104\305\247\245\124\017\007\221\126\000\022\274\223\103\005" +
"\200\043\124\015\040\136\031\373\176\013\151\154\240\264\155\313" +
"\054\315\005\016\136\237\125\327\343\237\021\071\016\274\207\303" +
"\333\330\206\046\242\230\071\002\004\013\052\304\152\223\024\023" +
"\230\370\170\251\305\345\326\007\050\302\314\105\364\333\232\035" +
"\103\026\370\171\270\124\377\336\071\353\020\325\254\143\353\160" +
"\105\254\106\072\133\147\065\346\153\253\307\074\073\346\060\242" +
"\066\326\001\105\167\013\265\310\204\351\173\217\335\332\052\230" +
"\371\366\326\207\057\016\006\257\174\174\070\055\313\314\224\266" +
"\276\312\201\237\307\202\227\113\277\211\167\215\272\034\044\146" +
"\352\337\120\055\166\057\315\275\311\361\105\211\171\066\345\363" +
"\355\020\216\250\273\277\350\266\014\237\054\375\300\312\267\052" +
"\025\156\340\170\237\067\057\265\323\377\007\062\243\104\046\346" +
"\062\000\000"
});

public static final byte[] symbolDisplayNamesHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\255\132\133\163\334\064" +
"\024\166\073\303\057\340\271\100\157\224\136\050\155\271\225\264" +
"\145\110\262\111\060\335\046\231\365\116\373\300\114\167\124\257" +
"\262\061\365\112\033\131\016\011\374\045\370\063\374\011\206\007" +
"\236\171\105\107\222\275\336\135\257\165\254\365\113\062\223\174" +
"\337\261\316\125\107\072\372\343\337\340\203\134\004\037\376\334" +
"\377\205\234\223\207\051\141\223\207\221\024\011\233\074\373\363" +
"\257\327\177\377\163\355\367\203\253\101\160\061\013\202\340\077" +
"\031\134\271\045\203\255\054\111\317\251\330\032\323\223\204\045" +
"\062\341\154\053\346\054\026\124\322\121\166\311\044\271\330\042" +
"\231\334\242\354\174\064\043\042\123\310\355\230\217\351\220\212" +
"\251\027\071\313\170\154\310\317\133\223\167\123\222\145\064\063" +
"\364\247\255\351\275\304\237\312\247\206\372\254\065\065\234\060" +
"\056\250\057\273\257\165\366\146\223\113\236\113\137\163\277\042" +
"\342\275\212\034\103\377\256\065\375\150\106\205\257\275\217\211" +
"\330\226\122\370\350\174\254\177\371\352\154\330\231\257\316\307" +
"\202\306\276\072\107\371\073\137\077\107\372\257\206\375\140\225" +
"\255\220\013\071\230\246\075\072\263\072\336\165\303\031\343\333" +
"\162\044\145\160\007\205\005\244\173\021\073\234\247\224\060\263" +
"\210\317\234\360\135\076\235\022\220\374\020\001\145\343\235\074" +
"\111\307\106\366\143\047\241\107\343\224\010\072\076\044\123\233" +
"\247\356\217\050\016\027\104\322\061\326\350\075\172\102\362\124" +
"\142\215\256\340\326\101\067\235\330\275\063\031\074\161\243\262" +
"\230\314\350\330\354\006\106\364\327\156\322\305\214\013\245\345" +
"\201\040\312\376\105\136\334\162\362\366\301\125\367\334\260\224" +
"\023\211\265\340\076\027\277\022\141\015\376\271\033\236\263\030" +
"\376\216\265\370\101\312\155\376\271\155\151\315\021\361\134\304" +
"\066\144\036\141\111\303\244\210\062\167\072\205\107\150\344\360" +
"\362\065\121\365\362\266\033\071\306\245\121\310\116\251\110\132" +
"\104\170\310\044\235\024\225\327\275\216\376\016\056\106\372\252" +
"\044\123\046\221\140\036\223\024\353\220\127\174\234\247\024\262" +
"\076\303\122\016\271\122\122\114\023\126\174\345\123\067\045\237" +
"\302\332\357\073\201\107\161\234\027\031\346\316\314\243\031\374" +
"\225\244\213\231\351\116\213\143\301\307\260\265\032\374\027\050" +
"\174\136\111\044\267\143\007\110\307\016\346\216\165\057\173\100" +
"\047\252\026\211\036\115\023\324\042\042\072\115\060\146\214\222" +
"\011\043\062\027\164\057\245\123\265\032\154\375\057\171\130\273" +
"\053\202\202\263\114\046\051\305\104\103\265\116\273\343\022\032" +
"\200\123\232\045\277\025\351\352\056\321\103\234\341\207\013\341" +
"\356\336\214\336\104\062\370\144\025\045\124\151\270\330\332\075" +
"\345\111\114\341\303\065\036\064\020\273\115\355\236\022\001\270" +
"\265\242\136\246\224\062\055\352\306\072\110\221\042\000\372\150" +
"\035\350\070\315\063\000\174\274\016\060\120\147\050\375\235\032" +
"\335\055\002\176\026\013\136\253\230\106\365\167\004\211\337\043" +
"\160\072\067\032\265\323\270\103\056\335\302\006\310\217\016\312" +
"\217\326\354\067\025\334\033\325\133\305\152\047\006\344\225\267" +
"\062\350\265\077\023\365\351\005\025\372\150\243\212\160\162\222" +
"\120\265\165\355\165\041\046\223\301\117\235\310\011\031\203\105" +
"\265\157\343\103\163\204\360\071\075\130\252\322\341\173\157\256" +
"\135\267\207\117\346\225\176\043\237\254\212\361\363\111\215\034" +
"\177\237\230\003\222\327\361\335\120\173\161\052\203\027\276\154" +
"\273\360\235\366\374\242\374\316\135\262\273\271\020\345\220\037" +
"\073\220\142\265\162\267\100\041\034\366\060\075\170\250\314\034" +
"\262\023\216\022\012\107\044\314\116\252\201\166\255\356\256\044" +
"\204\026\022\376\201\132\003\064\217\230\056\112\003\307\333\142" +
"\062\274\234\321\014\263\243\033\106\331\146\310\340\333\266\024" +
"\333\321\324\346\013\212\231\325\106\074\216\152\355\215\070\270" +
"\350\376\033\165\276\320\110\174\320\015\070\107\365\241\032\250" +
"\252\247\304\334\103\204\246\057\103\305\235\076\222\251\210\306" +
"\371\273\104\133\015\021\331\002\301\064\240\063\224\222\026\233" +
"\241\242\265\000\333\245\134\153\154\007\144\160\335\331\031\325" +
"\066\244\113\230\210\326\173\140\025\026\112\072\165\175\325\140" +
"\232\033\031\245\042\225\211\111\366\265\215\246\106\106\364\114" +
"\006\127\337\252\146\347\225\301\215\346\270\321\122\341\034\251" +
"\302\071\232\133\163\104\126\273\213\036\207\112\052\041\356\137" +
"\166\041\057\312\337\251\343\216\222\026\166\041\055\123\247\134" +
"\132\156\021\033\312\072\002\121\207\135\210\322\301\230\355\162" +
"\165\206\352\306\013\046\274\365\002\237\266\227\127\264\171\057" +
"\174\251\326\314\376\374\210\117\067\342\033\123\076\367\346\153" +
"\313\171\104\334\152\147\247\107\112\145\227\270\231\054\063\363" +
"\220\101\277\013\141\060\303\040\222\013\257\020\136\025\007\343" +
"\001\072\246\054\356\312\160\376\251\132\043\313\067\125\127\105" +
"\155\230\252\353\132\177\275\100\217\170\065\115\270\061\225\067" +
"\335\144\333\017\276\364\136\222\221\351\273\144\122\236\355\332" +
"\213\350\307\120\275\312\251\253\207\005\244\127\271\060\344\143" +
"\242\007\162\336\366\003\247\156\100\137\030\350\265\247\233\130" +
"\174\346\113\327\221\167\320\236\275\174\146\262\205\256\013\111" +
"\060\110\367\252\042\313\222\354\124\335\253\025\131\226\125\151" +
"\154\074\152\322\262\064\063\072\357\104\111\073\313\226\301\376" +
"\346\262\240\212\167\262\250\262\155\353\040\036\354\116\320\201" +
"\172\146\037\360\330\076\127\334\127\331\005\072\210\255\312\036" +
"\160\147\125\032\060\253\354\175\222\146\264\074\047\066\041\207" +
"\042\247\345\115\170\023\320\136\116\300\214\273\034\055\040\361" +
"\241\262\001\321\333\376\043\064\317\316\073\313\011\066\206\142" +
"\147\236\062\370\012\315\201\271\047\111\137\223\024\254\360\004" +
"\115\053\307\203\345\264\004\101\322\343\271\066\213\253\314\333" +
"\312\203\053\202\146\106\151\155\054\127\214\305\144\360\145\053" +
"\116\336\332\336\225\121\115\233\005\016\113\063\334\103\160\350" +
"\211\255\007\167\161\140\235\125\030\373\026\127\151\046\253\061" +
"\236\057\030\372\023\010\102\161\377\266\160\017\330\104\060\167" +
"\160\210\310\255\336\301\231\365\043\314\137\045\035\046\051\052" +
"\076\026\257\304\364\325\161\115\317\326\314\262\027\151\232\134" +
"\323\365\240\310\066\006\152\272\036\034\135\273\154\333\227\135" +
"\211\223\232\256\271\205\014\275\214\373\070\021\126\145\104\216" +
"\150\264\026\215\050\311\363\253\107\243\020\042\125\346\024\354" +
"\226\005\367\220\217\321\310\047\136\275\274\275\353\064\123\046" +
"\217\156\032\370\305\060\005\261\145\002\334\076\067\103\331\031" +
"\360\345\373\255\014\225\325\100\251\276\340\102\071\337\220\140" +
"\176\360\015\016\274\374\020\012\125\007\200\270\360\124\010\125" +
"\161\052\054\170\053\204\346\124\236\263\240\325\132\176\105\122" +
"\136\300\066\021\213\053\160\104\022\224\227\332\046\055\021\345" +
"\277\144\350\254\101\154\253\213\367\346\046\075\021\276\131\244" +
"\141\353\200\275\030\267\217\027\121\361\151\051\345\203\101\244" +
"\025\064\111\277\217\103\005\100\101\050\033\100\274\062\366\335" +
"\026\322\330\232\322\266\055\263\264\042\160\360\372\314\273\236" +
"\007\150\216\176\007\207\267\261\015\115\104\061\053\010\072\130" +
"\120\041\126\231\242\230\300\304\307\113\045\056\157\256\222\364" +
"\150\302\374\064\157\152\312\001\313\032\324\336\114\375\273\062" +
"\347\130\003\323\163\216\353\216\357\201\175\153\312\104\365\153" +
"\363\107\074\265\373\331\222\270\210\112\255\150\263\120\213\214" +
"\030\334\173\064\153\253\140\346\333\267\135\060\375\272\307\205" +
"\003\131\146\236\164\243\021\167\040\170\076\163\233\130\272\064" +
"\205\021\027\123\377\326\325\242\171\151\305\133\034\127\224\230" +
"\347\122\056\337\036\351\043\152\363\027\213\055\303\045\013\036" +
"\126\271\126\245\302\115\073\336\345\315\063\160\372\377\131\212" +
"\347\373\334\062\000\000"
});

public static final byte[] symbolNumbersHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\201\051\332\323\167\127\202\132\331\253\115\113\231\030\030" +
"\052\012\030\030\030\276\063\214\002\222\201\302\020\303\016\014" +
"\014\114\100\314\002\305\040\066\063\024\063\002\261\020\026\061" +
"\026\064\214\056\017\343\163\000\061\033\024\163\100\061\027\036" +
"\163\140\146\340\062\027\206\101\146\360\141\061\027\104\363\100" +
"\345\270\220\304\271\260\230\305\013\245\131\035\040\141\300\005" +
"\065\017\227\137\220\335\300\104\042\306\145\016\013\224\146\207" +
"\322\154\150\342\350\172\230\241\156\145\104\303\114\110\362\214" +
"\016\250\341\210\054\217\342\036\000\372\045\042\223\367\003\000" +
"\000"
});

public static final byte[] productionLHSsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\235\303\071\016\202\000" +
"\020\100\321\011\211\367\260\362\056\026\270\357\240\340\276\053" +
"\202\040\025\267\261\263\262\364\110\046\336\301\061\371\205\041" +
"\032\227\227\274\323\115\062\241\057\206\225\067\057\156\056\272" +
"\236\217\206\110\354\211\110\222\025\061\165\201\105\226\130\326" +
"\025\126\131\113\255\263\301\046\133\157\266\331\371\320\242\315" +
"\056\173\177\164\350\262\257\007\172\310\221\036\353\011\247\234" +
"\161\316\005\227\077\134\351\065\067\334\176\321\343\216\376\323" +
"\200\173\206\214\136\074\244\306\217\167\323\104\231\234\023\002" +
"\000\000"
});

public static final byte[] parseTableHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\355\235\147\264\044\125" +
"\025\205\367\042\030\061\140\102\024\364\230\300\034\300\014\352" +
"\025\024\030\045\030\061\214\141\124\024\043\111\101\024\124\120" +
"\111\222\024\044\147\025\005\305\204\001\030\224\040\106\104\045" +
"\203\110\022\146\000\121\124\020\344\057\365\240\153\275\352\172" +
"\035\156\335\256\123\047\324\375\326\252\325\257\157\237\336\147" +
"\317\332\163\253\252\273\053\234\162\033\126\336\161\007\254\270" +
"\170\361\242\325\356\136\266\305\352\007\356\263\376\012\300\316" +
"\333\001\270\271\030\137\141\361\242\315\226\056\131\153\247\133" +
"\117\075\261\034\376\014\062\356\331\161\173\174\036\105\342\013" +
"\323\046\334\073\136\376\275\142\355\265\225\270\235\305\102\300" +
"\312\043\306\356\327\340\375\367\157\321\316\244\076\017\050\226" +
"\007\166\321\153\034\343\323\326\014\001\017\222\366\140\021\263" +
"\151\077\130\332\203\105\314\246\275\212\264\007\213\230\115\373" +
"\041\322\036\054\142\066\355\207\112\173\260\210\331\264\037\046" +
"\355\301\042\363\151\007\340\320\266\325\071\064\063\351\314\247" +
"\275\204\101\235\200\207\063\310\146\022\261\261\046\047\140\125" +
"\151\017\135\101\300\043\270\264\163\332\332\040\340\221\134\332" +
"\071\155\155\020\360\050\056\155\033\151\127\041\340\321\322\036" +
"\264\100\300\143\232\324\347\264\055\103\300\152\115\352\155\244" +
"\115\375\132\223\077\226\113\073\377\342\111\335\375\342\271\172" +
"\027\175\046\301\073\267\003\160\132\223\361\266\173\167\321\307" +
"\022\274\151\023\360\070\016\335\114\032\354\151\077\236\103\067" +
"\223\006\173\332\153\160\350\146\322\320\277\117\116\300\232\211" +
"\357\173\102\273\116\164\102\300\023\143\153\331\347\066\161\350" +
"\146\322\140\117\373\111\034\272\231\064\330\323\176\062\207\156" +
"\046\215\241\243\031\016\151\133\235\103\063\223\216\376\275\264" +
"\072\004\074\105\332\203\004\004\074\165\126\015\223\151\077\115" +
"\332\103\035\002\326\222\366\020\003\137\332\004\254\135\173\376" +
"\364\011\265\317\030\074\076\163\304\153\317\252\374\375\354\021" +
"\257\077\047\301\333\163\053\177\077\257\351\373\265\101\300\363" +
"\143\352\330\367\322\136\300\241\033\103\000\056\223\352\255\025" +
"\366\137\105\056\346\320\315\244\141\157\273\035\013\001\353\110" +
"\173\320\106\336\156\223\217\355\366\272\061\165\176\347\366\050" +
"\010\170\241\264\207\130\010\170\121\333\232\376\323\046\340\305" +
"\322\036\264\140\043\155\112\070\056\255\170\317\113\332\167\302" +
"\017\001\057\235\360\332\313\146\321\146\377\004\366\162\016\335" +
"\114\032\223\323\046\140\275\030\225\242\156\375\026\115\265\002" +
"\001\257\350\250\317\053\007\217\257\352\242\337\054\330\130\223" +
"\247\102\367\176\344\317\224\260\177\273\162\071\207\156\154\357" +
"\262\277\244\017\115\330\234\333\004\274\132\332\203\105\330\347" +
"\366\225\034\272\261\275\313\376\222\076\064\141\166\156\157\040" +
"\355\301\042\354\163\373\132\016\335\330\336\145\177\111\037\232" +
"\140\117\373\052\016\335\330\336\145\177\111\037\232\060\273\046" +
"\337\120\332\203\105\330\347\366\325\034\272\261\275\313\376\222" +
"\076\064\141\166\156\277\106\332\203\105\330\277\047\177\055\207" +
"\156\046\015\375\163\233\022\317\003\353\013\004\154\024\133\313" +
"\276\335\276\204\103\067\223\206\376\271\075\013\004\154\054\355" +
"\101\023\066\323\046\140\023\151\017\026\141\337\113\133\304\241" +
"\233\111\303\346\334\156\102\000\226\305\326\022\360\272\021\143" +
"\257\057\226\115\213\145\263\332\370\346\263\071\343\201\200\055" +
"\212\345\015\225\347\157\054\226\067\025\313\233\331\367\322\316" +
"\341\320\265\322\137\033\354\153\362\267\160\350\306\020\200\363" +
"\244\172\153\305\357\232\234\200\267\112\173\320\206\134\332\004" +
"\154\331\165\117\117\020\360\266\246\357\261\071\267\011\170\273" +
"\264\007\213\230\115\373\035\322\036\054\142\063\355\130\002\260" +
"\253\264\007\115\260\357\223\277\223\103\067\206\000\174\101\252" +
"\267\126\374\316\155\002\026\113\173\320\306\320\025\264\276\332" +
"\266\072\207\146\046\235\174\266\076\371\070\133\377\135\061\165" +
"\366\326\344\004\274\133\332\203\106\010\170\317\264\032\033\151" +
"\123\277\356\053\362\136\056\355\234\266\066\010\130\302\245\235" +
"\277\071\265\012\001\357\153\372\036\033\163\073\205\000\134\052" +
"\355\101\033\103\237\300\276\326\266\072\207\146\046\035\033\163" +
"\233\372\265\335\176\077\227\266\215\264\253\020\360\001\151\017" +
"\261\020\260\225\264\207\052\366\322\156\112\000\156\216\255\233" +
"\266\324\353\132\067\313\114\057\322\276\051\266\156\332\122\257" +
"\153\335\054\063\376\323\156\002\215\071\346\264\362\367\007\073" +
"\264\223\014\001\037\052\226\255\213\345\303\325\361\234\166\025" +
"\212\074\302\070\000\313\110\357\021\306\037\041\221\043\214\065" +
"\020\200\377\306\326\325\227\372\170\275\266\125\243\035\340\077" +
"\155\002\076\052\355\101\013\376\323\016\300\277\142\353\352\113" +
"\175\274\136\333\252\321\016\350\105\332\167\304\326\315\055\004" +
"\174\254\130\076\136\054\237\250\216\127\165\352\317\255\320\213" +
"\264\357\212\255\033\125\133\216\127\137\033\127\253\235\136\244" +
"\275\074\246\216\200\155\050\176\237\174\071\051\335\047\237\203" +
"\026\356\223\157\073\367\267\215\264\251\137\337\223\117\272\032" +
"\375\166\263\150\263\237\343\171\056\207\256\225\376\332\260\061" +
"\267\123\011\300\105\322\036\064\341\073\155\002\266\227\366\240" +
"\011\173\151\123\177\357\321\273\303\254\032\071\155\053\020\360" +
"\311\131\065\374\336\105\246\053\150\376\056\062\237\222\165\062" +
"\035\173\163\273\011\001\370\234\264\007\115\370\116\233\212\177" +
"\240\264\007\115\370\075\017\254\030\337\151\102\077\157\347\201" +
"\175\172\360\270\363\244\072\366\363\267\135\256\063\254\302\236" +
"\366\147\071\164\063\151\270\337\156\357\042\355\101\023\356\323" +
"\316\327\135\251\300\276\046\317\237\200\024\061\234\066\241\170" +
"\066\006\032\134\265\246\170\334\255\066\276\073\227\273\131\040" +
"\340\213\302\375\277\064\170\374\262\244\217\052\356\327\344\173" +
"\110\173\320\204\357\264\233\102\123\316\036\260\002\335\167\366" +
"\300\236\224\317\036\230\100\000\156\255\076\047\140\257\372\130" +
"\375\271\045\162\332\125\050\142\156\027\317\367\356\310\116\062" +
"\124\314\355\301\343\076\325\361\234\166\225\000\334\062\155\154" +
"\124\215\025\162\332\051\020\360\025\151\017\051\260\177\336\336" +
"\227\103\067\223\206\377\271\035\200\333\143\353\106\325\226\343" +
"\325\327\306\325\152\247\027\151\377\057\266\156\124\155\071\136" +
"\175\155\134\255\166\172\221\366\235\261\165\243\152\313\361\352" +
"\153\343\152\265\323\213\264\133\277\037\130\320\175\266\376\176" +
"\324\347\263\365\377\037\133\067\252\266\034\257\276\066\256\126" +
"\073\176\323\016\300\257\244\075\150\143\350\132\210\007\266\255" +
"\316\241\231\111\307\357\334\236\203\200\375\245\075\150\302\175" +
"\332\007\110\173\320\204\337\264\303\204\043\063\372\012\373\371" +
"\333\127\160\350\306\366\056\373\113\372\320\004\173\332\327\161" +
"\350\132\351\257\015\366\137\105\304\366\311\003\160\203\124\157" +
"\255\370\335\156\123\276\027\331\002\374\236\007\066\305\233\267" +
"\363\300\242\356\361\140\157\156\123\276\037\330\110\010\070\150" +
"\132\015\373\136\332\065\034\272\261\275\313\376\222\076\064\061" +
"\364\315\351\324\377\033\115\341\320\314\244\143\162\115\176\260" +
"\264\207\056\041\340\353\155\151\331\113\173\016\002\016\221\366" +
"\140\021\263\151\037\052\355\301\042\146\323\076\114\332\203\105" +
"\314\246\175\270\264\007\213\330\114\073\226\120\073\063\246\357" +
"\260\177\117\176\004\207\156\014\001\330\117\252\267\126\374\316" +
"\155\002\216\224\366\240\215\241\157\127\132\377\034\313\241\231" +
"\111\307\357\334\116\201\342\316\350\075\252\043\073\311\120\076" +
"\243\067\002\362\163\155\206\243\107\215\373\117\073\000\377\216" +
"\255\253\057\365\361\172\155\253\106\073\300\177\332\115\010\300" +
"\077\246\215\215\252\261\202\377\264\003\160\133\154\135\175\251" +
"\217\327\153\133\065\332\001\366\322\046\340\030\151\017\263\102" +
"\300\261\022\175\355\245\335\224\000\334\150\101\263\013\330\217" +
"\135\021\273\206\261\144\157\255\260\247\055\166\015\141\311\336" +
"\132\311\307\234\222\217\143\116\313\173\017\034\067\251\216\175" +
"\156\137\317\241\153\245\277\066\174\357\245\005\340\214\271\107" +
"\002\216\027\065\322\020\002\116\340\320\365\235\066\001\337\220" +
"\366\240\011\277\151\023\360\315\332\363\157\311\070\111\207\200" +
"\023\007\217\337\156\103\217\375\150\206\357\160\350\146\322\220" +
"\233\333\004\154\331\165\117\117\020\160\122\323\367\330\133\223" +
"\123\177\357\332\172\362\254\032\066\322\046\140\125\151\017\135" +
"\101\300\167\271\264\155\244\075\015\002\276\047\355\301\002\176" +
"\357\031\044\015\315\337\063\350\024\131\047\363\370\230\333\343" +
"\010\106\257\032\317\205\357\264\347\010\300\177\142\353\352\113" +
"\175\274\136\333\252\321\016\360\237\166\023\002\360\317\151\143" +
"\243\152\254\260\140\273\375\375\142\371\101\261\374\160\360\374" +
"\107\305\362\343\262\272\370\373\324\272\102\061\366\223\142\371" +
"\051\273\325\206\020\360\063\151\017\243\040\340\347\203\307\323" +
"\272\356\355\173\156\023\160\272\264\007\115\370\115\073\000\177" +
"\227\366\240\015\033\151\123\277\276\135\071\203\113\333\106\332" +
"\251\020\260\124\332\203\046\330\217\135\371\033\207\156\154\357" +
"\262\277\244\017\115\260\247\375\013\016\135\053\375\265\141\163" +
"\115\116\300\231\322\036\054\302\176\064\203\330\334\012\300\331" +
"\122\275\265\142\163\156\307\100\300\057\245\075\150\303\157\332" +
"\165\310\346\161\151\147\265\251\347\073\155\312\153\363\041\334" +
"\247\175\216\264\007\115\270\117\373\134\151\017\232\160\237\166" +
"\276\273\143\005\277\151\007\140\137\151\017\332\260\231\066\001" +
"\347\111\173\260\210\331\264\177\055\355\301\042\146\323\376\215" +
"\264\007\213\230\115\373\267\322\036\054\142\066\355\337\111\173" +
"\260\210\331\264\177\057\355\301\042\146\323\376\203\264\007\213" +
"\230\115\373\174\151\017\026\261\231\166\054\141\312\065\206\372" +
"\006\373\321\014\177\344\320\215\041\344\153\256\054\300\357\334" +
"\046\340\002\151\017\332\030\272\323\104\153\167\020\054\341\320" +
"\314\244\043\063\267\011\370\123\227\375\062\367\301\176\204\361" +
"\137\071\164\143\173\227\375\045\175\150\302\306\166\233\372\165" +
"\146\320\237\271\264\155\244\335\006\144\363\050\304\362\352\170" +
"\177\151\103\217\175\115\056\172\224\257\164\177\155\260\177\336" +
"\276\220\103\067\223\006\373\334\336\203\103\127\173\157\255\260" +
"\247\275\047\207\256\366\336\132\141\117\173\057\016\135\355\275" +
"\265\302\236\366\336\034\272\332\173\153\045\137\303\330\052\004" +
"\134\324\364\075\066\076\157\123\277\276\135\271\230\113\333\106" +
"\332\125\250\277\127\254\276\144\126\215\234\266\025\010\270\164" +
"\126\015\173\151\067\201\200\313\244\075\150\302\175\332\227\113" +
"\173\320\204\217\264\011\270\102\332\203\005\354\245\115\375\335" +
"\156\137\071\253\206\337\153\030\153\205\346\257\141\334\371\021" +
"\026\366\346\166\023\202\261\073\072\162\223\357\321\113\076\356" +
"\321\173\125\114\035\373\357\333\371\372\242\212\360\273\046\017" +
"\055\137\153\314\003\366\322\046\340\140\151\017\135\102\300\325" +
"\155\151\331\113\273\011\004\134\043\355\101\023\356\323\276\126" +
"\332\203\046\334\247\175\235\264\007\115\270\117\373\172\151\017" +
"\232\140\077\122\351\050\016\135\355\275\265\302\236\366\321\034" +
"\272\332\173\153\305\375\232\074\337\023\254\202\373\264\157\220" +
"\366\240\011\277\151\007\340\004\151\017\332\360\233\366\034\141" +
"\160\337\074\062\366\133\030\001\067\162\350\346\337\300\310\307" +
"\157\140\313\142\352\174\317\155\002\226\113\173\320\004\373\047" +
"\260\303\070\164\265\367\326\012\173\332\207\163\350\152\357\255" +
"\025\366\264\217\340\320\325\336\133\053\354\151\037\311\241\253" +
"\275\267\126\330\323\076\206\103\127\173\157\255\260\247\175\054" +
"\207\256\366\336\132\361\375\011\054\000\247\113\173\320\204\357" +
"\264\011\270\111\332\203\046\330\327\344\113\071\164\063\151\260" +
"\247\175\046\207\256\366\336\132\271\007\370\117\016\215\353\275" +
"\001\000"
});

public static final byte[] shiftableSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\325\230\055\117\303\100" +
"\030\307\377\053\045\251\054\240\046\110\016\207\132\020\030\202" +
"\151\026\060\004\207\234\142\011\142\004\226\261\165\144\152\351" +
"\120\050\102\302\027\230\007\001\002\117\206\301\361\021\220\050" +
"\076\002\201\156\204\260\222\356\376\327\076\055\051\025\253\270" +
"\137\237\267\273\347\345\166\363\206\371\156\033\113\265\335\303" +
"\375\323\375\112\327\157\034\125\252\015\177\357\300\337\074\056" +
"\257\332\317\027\133\055\013\350\265\000\274\166\332\160\177\123" +
"\315\367\363\376\306\372\365\312\034\112\065\330\365\206\337\361" +
"\141\325\166\172\255\120\350\370\255\354\373\305\273\356\325\267" +
"\214\360\035\175\172\235\023\364\141\165\307\277\363\143\340\301" +
"\361\302\227\163\071\023\010\340\350\045\374\023\040\174\024\112" +
"\042\040\140\052\304\200\315\274\240\000\121\301\267\173\055\134" +
"\126\074\222\271\002\056\074\346\146\356\066\174\001\272\363\040" +
"\335\254\047\124\253\003\127\037\007\171\044\205\066\234\201\110" +
"\010\074\006\270\074\057\114\274\010\324\154\140\352\353\170\340" +
"\226\106\222\025\220\077\220\120\004\043\101\045\210\163\123\001" +
"\104\202\330\310\210\211\013\243\170\033\264\022\150\352\261\314" +
"\032\033\351\352\001\161\123\243\221\314\304\013\155\031\344\105" +
"\114\334\263\114\000\022\152\261\233\006\066\060\067\275\351\345" +
"\345\217\024\100\104\201\125\045\100\071\346\330\107\124\224\137" +
"\122\024\363\144\022\070\360\230\002\240\173\241\040\055\101\024" +
"\310\142\124\203\260\251\061\340\311\245\271\311\002\105\201\014" +
"\042\311\124\114\074\330\266\146\003\106\052\176\036\226\070\166" +
"\235\001\124\002\003\034\161\123\113\225\070\011\001\132\100\142" +
"\044\310\053\255\142\022\050\100\155\240\143\222\142\200\270\261" +
"\146\320\067\371\260\007\361\025\246\000\067\167\005\002\344\077" +
"\344\004\221\357\151\166\307\125\030\157\172\075\256\055\122\025" +
"\134\102\242\011\044\256\004\031\105\122\007\360\123\255\230\212" +
"\141\064\224\311\317\303\300\160\273\365\000\031\335\345\063\155" +
"\100\045\100\330\365\274\351\345\124\107\156\022\204\121\123\140" +
"\203\301\136\220\100\121\025\362\142\116\113\220\270\302\014\100" +
"\200\014\272\236\034\340\233\125\200\166\120\004\300\050\222\212" +
"\001\172\033\300\133\222\243\225\140\060\036\004\132\033\314\016" +
"\055\121\221\305\034\225\063\000\132\355\363\377\337\136\101\012" +
"\360\206\142\164\041\225\124\173\203\231\026\342\274\310\077\222" +
"\162\100\176\046\263\261\101\013\014\011\140\066\313\345\034\207" +
"\042\000\324\115\012\040\251\015\237\363\174\343\211\047\036\000" +
"\000"
});

public static final byte[] layoutSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\355\314\261\016\301\120" +
"\000\205\341\243\332\135\230\154\154\246\116\026\261\211\111\154" +
"\306\116\225\030\052\110\265\367\112\247\306\150\262\170\014\026" +
"\203\347\360\030\046\217\040\321\073\030\330\155\377\031\316\131" +
"\276\234\313\123\201\315\324\212\246\313\170\027\207\326\044\253" +
"\160\224\230\331\302\014\327\355\236\177\077\216\123\117\052\122" +
"\111\217\074\123\343\127\155\136\207\162\320\077\167\353\252\105" +
"\362\347\211\311\215\274\150\122\244\325\251\333\216\177\153\136" +
"\355\351\363\121\355\167\212\174\253\122\236\165\035\070\260\007" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\300\037\301\033\225\154\142\040" +
"\047\036\000\000"
});

public static final byte[] prefixSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\355\314\041\016\302\100" +
"\000\104\321\241\264\236\200\302\201\103\125\141\010\216\240\010" +
"\016\131\125\022\104\011\220\155\273\113\252\252\121\030\216\001" +
"\006\301\071\070\006\212\043\220\320\012\004\170\334\037\061\143" +
"\136\346\362\124\340\062\165\242\371\072\336\307\241\263\311\046" +
"\234\044\166\261\262\343\155\167\340\337\217\123\343\111\205\221" +
"\364\310\063\265\176\325\356\165\050\107\303\163\277\251\106\044" +
"\177\231\330\334\312\213\146\205\251\116\353\355\371\267\366\325" +
"\235\076\037\325\176\247\310\123\225\362\134\335\001\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\376\015\336\324\126\140\103\047\036\000" +
"\000"
});

public static final byte[] prefixMapsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\355\314\041\022\001\001" +
"\030\200\321\177\166\070\001\145\157\040\155\120\004\315\210\044" +
"\161\323\006\141\015\063\313\354\032\311\021\234\104\161\022\063" +
"\262\114\342\014\024\115\024\204\367\345\157\336\361\021\355\146" +
"\023\335\074\237\054\212\155\221\065\165\271\314\106\145\075\233" +
"\327\303\364\362\074\015\156\327\176\022\261\253\042\342\376\036" +
"\073\137\276\125\332\153\235\017\343\352\363\115\253\137\326\254" +
"\143\037\011\026\213\305\142\261\130\054\026\213\305\142\261\130" +
"\054\026\213\305\142\261\130\054\026\213\305\142\261\130\054\026" +
"\213\305\142\261\130\054\026\213\305\142\261\130\054\026\213\305" +
"\142\261\130\054\026\213\305\142\261\130\054\026\213\305\142\261" +
"\130\054\026\213\305\142\377\207\175\001\226\104\111\225\314\116" +
"\000\000"
});

public static final byte[] terminalUsesHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\201\051\332\323\167\127\202\132\331\253\115\113\231\030\030" +
"\052\012\030\030\030\174\031\106\001\311\000\000\004\226\210\141" +
"\117\001\000\000"
});

public static final byte[] shiftableUnionHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\270" +
"\210\101\040\053\261\054\121\257\264\044\063\107\317\051\263\044" +
"\070\265\044\357\157\107\235\245\311\152\105\146\006\306\150\006" +
"\226\244\314\222\342\022\006\246\150\257\212\202\322\042\060\255" +
"\300\262\125\150\143\351\144\046\006\206\212\002\006\006\006\246" +
"\323\077\316\035\232\151\160\376\077\003\030\310\377\257\000\000" +
"\102\356\205\023\131\000\000\000"
});

public static final byte[] acceptSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\355\130\261\116\303\060" +
"\020\075\233\124\312\202\144\301\304\146\066\246\116\054\210\045" +
"\102\114\210\215\061\123\221\030\212\012\052\155\202\072\125\036" +
"\231\130\370\014\130\030\370\016\276\204\117\100\042\211\142\221" +
"\326\166\035\273\116\154\044\072\244\122\336\335\345\356\275\073" +
"\047\366\333\027\014\362\031\354\247\227\267\243\307\321\060\317" +
"\306\223\341\331\070\273\272\311\116\357\016\216\242\317\347\363" +
"\051\006\130\114\001\320\356\174\006\144\335\352\376\373\151\171" +
"\162\374\172\270\003\050\205\350\172\234\315\063\300\351\305\142" +
"\132\004\055\377\151\364\261\367\236\277\324\061\212\353\374\001" +
"\226\200\363\362\072\220\335\050\054\041\201\337\137\142\145\000" +
"\046\006\261\140\300\132\104\330\062\007\352\242\212\076\042\304" +
"\033\015\350\012\221\335\250\311\064\125\064\375\365\074\140\111" +
"\222\150\113\242\020\117\121\247\001\325\031\104\026\004\255\360" +
"\343\251\121\310\112\021\012\036\150\353\034\210\274\223\032\111" +
"\150\253\040\066\074\104\315\010\114\142\320\230\206\242\153\164" +
"\071\040\367\363\200\270\201\014\240\052\100\351\041\005\244\226" +
"\225\304\172\113\126\047\276\175\026\146\351\231\205\130\027\012" +
"\167\230\256\133\040\121\261\313\214\151\067\367\360\007\264\153" +
"\002\146\370\260\172\334\203\252\064\130\000\253\270\102\146\044" +
"\312\127\027\220\274\034\135\346\317\127\366\320\130\015\024\200" +
"\162\230\004\200\277\345\275\016\272\345\332\320\007\153\130\005" +
"\240\356\037\116\272\126\046\134\240\132\307\005\040\261\151\126" +
"\220\175\275\152\074\142\237\143\112\002\324\303\024\210\325\102" +
"\125\116\156\036\036\005\130\271\357\301\001\305\247\157\305\226" +
"\312\103\330\243\172\257\303\055\045\342\061\306\146\017\026\150" +
"\035\206\233\307\277\004\360\155\270\274\162\324\007\273\342\021" +
"\205\316\103\074\057\340\102\261\260\330\055\157\142\163\112\260" +
"\105\213\012\147\257\326\172\210\347\172\065\100\015\102\361\264" +
"\214\353\020\116\233\274\053\330\113\227\040\167\241\112\110\000" +
"\260\215\036\377\300\072\100\315\111\044\026\012\062\177\225\307" +
"\001\322\136\067\257\224\253\122\221\305\017\376\134\107\004\175" +
"\033\000\000"
});

public static final byte[] rejectSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\070\332\047\053\261\054\121\257\264\044\063\107\317\051" +
"\263\044\070\265\304\072\127\122\203\345\174\237\113\001\023\003" +
"\103\105\001\003\003\043\157\161\021\203\000\272\252\274\277\035" +
"\165\226\046\253\025\231\031\030\243\031\130\222\062\113\212\113" +
"\030\230\242\275\052\012\200\206\202\150\005\226\255\102\033\113" +
"\047\103\315\000\222\305\205\014\165\014\114\245\040\222\225\112" +
"\002\114\014\250\300\201\026\226\014\135\227\323\302\022\202\146" +
"\016\114\234\220\156\353\240\320\061\040\241\311\010\062\207\126" +
"\021\061\302\005\106\303\166\250\013\340\214\101\012\242\166\064" +
"\125\320\057\254\207\216\000\135\174\077\364\102\222\012\301\102" +
"\013\225\303\040\354\107\213\266\301\234\132\206\263\127\206\156" +
"\300\015\114\131\074\110\052\306\101\126\342\016\237\232\141\260" +
"\145\262\101\222\336\006\175\300\015\222\344\063\110\112\001\204" +
"\004\000\275\367\214\060\115\024\000\000"
});

public static final byte[] possibleSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\355\127\261\156\023\101" +
"\020\235\133\157\302\025\111\164\202\012\032\056\035\125\224\202" +
"\202\050\315\306\270\210\042\212\110\064\110\246\160\220\050\214" +
"\010\062\361\031\271\212\266\244\242\341\063\240\241\240\317\037" +
"\360\045\174\102\010\267\027\307\254\263\363\166\275\226\261\023" +
"\307\133\330\212\336\233\067\263\063\157\107\316\367\337\264\322" +
"\073\241\007\315\027\357\216\076\035\155\365\212\366\373\255\172" +
"\273\170\371\266\330\075\176\370\104\376\372\322\350\010\242\176" +
"\207\050\131\357\236\120\166\235\365\341\374\363\351\316\323\157" +
"\233\065\112\232\044\337\264\213\156\101\242\171\320\357\224\242" +
"\346\073\227\077\357\377\350\175\035\150\224\237\335\217\164\112" +
"\242\147\076\127\312\277\305\305\345\371\103\325\171\174\341\020" +
"\210\152\255\362\133\135\022\110\061\204\041\010\011\224\205\010" +
"\326\331\167\010\172\224\300\327\160\117\323\066\044\310\220\102" +
"\153\234\133\370\011\302\050\310\261\025\352\174\047\067\150\325" +
"\253\220\172\153\310\355\014\051\030\226\032\212\160\004\025\352" +
"\203\016\364\301\216\017\367\101\060\204\044\254\240\054\113\071" +
"\204\344\252\304\120\365\255\320\220\032\016\341\221\311\075\114" +
"\300\025\067\222\201\255\076\127\126\046\366\172\332\257\260\146" +
"\232\334\367\020\254\367\126\172\022\314\040\367\267\330\072\031" +
"\113\170\176\346\277\346\310\071\344\010\257\003\215\032\171\266" +
"\147\014\301\172\015\224\004\153\330\143\010\231\262\374\342\052" +
"\224\126\124\377\372\304\130\215\006\213\203\003\132\000\250\301" +
"\010\205\162\124\023\235\002\160\165\123\224\074\107\100\306\002" +
"\345\322\165\245\044\312\241\140\257\006\273\063\064\100\201\252" +
"\003\300\032\276\250\344\001\201\042\024\226\112\031\100\241\021" +
"\153\117\373\263\230\210\324\143\074\145\242\034\040\253\126\027" +
"\023\041\260\124\356\313\041\130\100\243\210\170\240\314\361\214" +
"\001\044\362\160\112\225\305\070\251\072\312\261\007\223\153\000" +
"\274\102\021\015\000\144\276\046\046\320\127\332\001\022\337\314" +
"\163\036\020\050\042\361\125\025\271\205\114\104\344\352\060\253" +
"\210\005\126\271\034\223\356\024\130\256\273\154\156\354\056\001" +
"\233\341\056\054\200\170\100\231\037\005\000\330\106\053\003\112" +
"\055\167\311\230\233\301\000\340\235\103\051\015\000\201\042\044" +
"\007\250\345\002\140\043\376\377\073\237\305\123\233\336\303\131" +
"\364\367\341\167\073\157\352\352\077\254\271\230\372\266\171\167" +
"\022\047\336\145\303\055\214\113\170\063\314\175\346\267\176\264" +
"\123\037\124\036\061\250\371\266\335\333\135\256\211\023\365\152" +
"\037\001\207\361\067\137\370\013\306\336\143\131\125\304\314\141" +
"\162\220\143\066\122\263\213\370\013\274\362\163\112\345\033\000" +
"\000"
});

public static final byte[] cMapHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\355\321\071\112\104\101" +
"\020\006\340\067\243\343\276\357\373\356\270\357\172\010\003\117" +
"\140\342\021\104\120\274\220\231\221\241\107\022\274\203\377\203" +
"\027\014\303\040\023\030\311\327\360\121\335\125\124\065\115\277" +
"\177\027\215\347\247\242\176\177\173\367\371\320\174\371\372\170" +
"\253\027\305\353\143\121\053\312\065\324\205\106\254\267\235\273" +
"\351\353\166\166\031\173\072\324\366\342\042\146\142\053\116\343" +
"\050\066\343\070\226\142\241\113\003\261\133\315\235\252\142\157" +
"\254\304\144\054\126\361\252\212\245\211\226\175\273\215\016\271" +
"\361\230\373\245\347\074\106\343\060\226\133\336\071\026\253\061" +
"\033\375\161\023\047\161\026\333\121\253\372\363\157\305\165\064" +
"\143\076\206\143\277\252\255\305\116\014\306\110\225\233\216\276" +
"\070\250\316\345\135\227\177\370\167\000\000\000\000\000\000\000" +
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
"\000\000\000\000\000\000\374\123\077\215\253\150\324\033\000\004" +
"\000"
});

public static final byte[] deltaHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\355\234\005\220\035\105" +
"\020\206\373\345\342\202\103\010\256\001\202\133\260\000\207\023" +
"\040\004\267\103\202\073\044\201\204\140\011\016\301\202\273\103" +
"\320\140\301\203\273\273\273\005\013\004\207\340\060\127\265\107" +
"\275\332\355\351\231\267\273\263\333\323\073\123\325\165\173\323" +
"\273\177\367\127\377\314\354\273\253\253\033\067\031\072\014\033" +
"\012\115\055\055\375\173\116\231\070\260\327\230\321\375\332\001" +
"\214\030\014\120\353\241\346\333\265\364\037\060\141\120\357\341" +
"\223\306\217\215\246\241\057\010\035\303\206\300\110\120\224\377" +
"\023\266\136\067\251\150\257\242\203\212\216\321\367\235\124\164" +
"\126\321\105\105\127\025\335\242\371\356\052\172\104\327\155\061" +
"\125\364\165\152\025\323\104\327\323\252\230\056\272\236\136\305" +
"\014\052\146\124\061\123\064\327\063\372\072\263\212\136\321\365" +
"\054\052\146\125\061\233\212\331\125\314\021\315\317\251\142\056" +
"\025\163\253\230\107\305\274\052\346\123\061\277\212\336\052\026" +
"\210\356\133\120\305\102\011\302\076\121\324\217\372\271\266\257" +
"\013\307\346\372\150\356\153\233\133\044\066\117\151\353\346\165" +
"\275\265\216\105\065\272\175\122\023\306\347\114\204\030\205\116" +
"\333\246\046\040\071\114\067\111\270\130\024\213\327\135\123\261" +
"\204\345\175\245\105\202\120\334\060\022\056\131\120\043\316\352" +
"\044\010\227\006\273\275\262\024\370\261\017\227\151\150\225\056" +
"\153\161\017\273\041\376\054\355\133\301\223\206\263\207\313\151" +
"\236\247\164\223\357\303\345\113\012\147\265\235\173\270\102\164" +
"\275\142\154\236\322\156\273\136\211\250\011\110\056\076\337\017" +
"\060\017\305\215\012\022\332\256\322\225\301\156\225\226\371\266" +
"\130\005\260\125\152\042\134\125\323\001\107\102\374\054\165\071" +
"\232\013\250\221\030\025\334\207\342\106\005\011\155\317\322\325" +
"\100\312\111\043\357\047\140\227\143\365\002\152\044\106\005\367" +
"\341\332\100\357\225\065\352\346\326\214\345\213\330\207\153\041" +
"\317\123\272\125\374\344\155\173\226\256\123\167\275\056\024\347" +
"\141\366\263\264\177\203\261\136\212\147\322\304\372\151\237\315" +
"\345\047\340\015\040\235\207\003\242\357\067\104\162\176\176\362" +
"\056\145\004\102\377\107\246\175\070\060\226\257\277\157\043\044" +
"\127\377\074\245\115\325\154\144\037\156\014\325\334\207\151\316" +
"\322\254\237\151\066\101\162\171\170\270\051\124\323\103\161\243" +
"\202\204\266\373\160\263\272\353\315\101\277\337\266\320\314\123" +
"\332\272\371\174\076\323\140\052\133\031\252\145\075\151\134\021" +
"\156\011\266\204\256\317\322\362\075\254\026\341\326\232\016\362" +
"\046\334\206\320\212\077\117\351\026\347\141\213\241\223\170\316" +
"\235\207\341\157\242\274\033\011\302\155\113\012\147\265\053\370" +
"\073\157\216\157\213\355\014\275\121\272\274\337\207\333\023\065" +
"\001\311\141\272\356\011\167\040\050\164\332\066\065\333\306\040" +
"\203\256\233\237\017\167\314\121\053\363\140\261\112\167\102\162" +
"\356\336\370\046\302\235\065\035\270\074\151\262\020\356\122\301" +
"\067\276\355\052\335\025\374\360\260\270\267\305\156\206\116\342" +
"\071\377\010\323\170\270\073\121\023\220\034\246\313\233\060\253" +
"\207\173\100\221\204\173\022\235\360\137\245\173\201\231\260\014" +
"\017\345\257\122\234\320\345\150\056\240\106\142\244\366\160\157" +
"\010\036\046\107\163\001\065\022\203\305\076\334\007\311\311\071" +
"\151\366\325\344\374\043\334\317\320\111\074\347\037\241\353\223" +
"\146\177\215\256\075\341\140\242\032\007\102\336\036\016\101\162" +
"\356\010\345\377\135\233\151\014\217\175\177\200\345\163\103\211" +
"\334\060\213\347\017\264\254\223\024\017\277\305\210\317\035\204" +
"\314\145\335\207\043\220\234\273\175\050\156\244\076\113\017\206" +
"\374\074\164\371\266\070\244\260\267\305\241\206\116\342\271\342" +
"\337\207\207\021\325\070\173\230\376\215\177\270\206\102\016\141" +
"\375\334\110\202\304\137\302\121\032\012\071\204\266\373\360\010" +
"\202\102\247\155\123\023\220\034\246\313\343\223\167\043\204\107" +
"\042\317\123\272\376\021\066\342\341\121\040\235\260\065\216\366" +
"\212\360\030\315\363\224\256\174\017\003\241\276\332\261\032\012" +
"\376\204\362\377\066\061\254\122\254\332\161\004\211\277\204\307" +
"\153\050\070\021\236\000\171\171\070\232\040\311\102\170\042\121" +
"\023\220\034\246\033\176\117\223\234\053\343\244\071\311\320\033" +
"\245\233\364\360\144\313\056\212\044\054\347\244\311\223\360\024" +
"\044\127\074\341\251\104\065\231\036\216\321\120\310\041\074\115" +
"\103\041\207\220\373\052\075\035\032\045\074\203\250\306\221\320" +
"\017\017\317\044\152\002\222\303\164\171\023\146\365\360\054\300" +
"\010\317\266\354\302\007\102\371\036\246\043\074\107\323\201\034" +
"\302\264\036\236\153\350\044\236\343\101\170\136\134\215\350\212" +
"\227\207\347\133\164\341\357\052\225\377\027\103\201\060\020\006" +
"\302\362\011\115\157\213\013\220\071\277\316\122\127\157\374\064" +
"\204\027\022\065\001\311\141\272\366\204\027\021\325\252\355\341" +
"\305\004\205\116\333\246\046\040\071\114\227\367\052\015\204\361" +
"\034\246\233\017\341\045\004\211\014\102\327\036\136\152\350\215" +
"\322\055\237\360\062\115\256\132\036\226\103\170\271\206\102\016" +
"\141\375\334\025\004\211\014\102\327\036\136\151\350\215\322\115" +
"\117\170\225\206\042\170\210\151\217\105\162\262\010\115\036\136" +
"\155\350\215\322\345\101\170\015\222\343\345\341\265\004\211\337" +
"\373\360\072\204\102\026\141\121\373\060\020\122\272\351\011\257" +
"\327\120\310\041\224\357\241\057\204\067\224\112\070\256\000\102" +
"\367\036\336\110\120\350\264\155\152\002\222\303\164\345\257\322" +
"\233\012\047\274\331\102\333\246\046\040\071\114\227\267\207\267" +
"\020\065\001\311\141\272\274\011\375\362\360\126\103\047\361\034" +
"\057\302\361\140\046\364\333\303\333\010\022\031\204\134\367\341" +
"\355\200\021\336\141\331\205\017\204\174\074\274\023\311\311\042" +
"\164\351\341\135\342\011\345\173\330\070\341\335\232\016\344\020" +
"\226\341\341\075\206\336\050\135\077\010\313\363\160\002\102\041" +
"\213\320\265\207\367\022\065\001\311\141\272\274\011\363\360\360" +
"\076\026\204\367\043\271\152\254\322\074\010\037\050\235\360\101" +
"\115\216\227\207\017\021\044\145\173\310\143\225\076\214\344\144" +
"\021\272\362\360\021\050\222\360\121\242\023\371\253\264\130\102" +
"\161\243\202\036\162\046\174\114\363\074\245\233\215\360\161\204" +
"\042\170\130\064\341\023\142\010\237\324\350\332\171\370\224\241" +
"\032\007\302\374\126\351\323\232\016\344\020\372\346\341\063\205" +
"\021\076\153\350\044\236\013\036\002\222\303\164\171\020\076\207" +
"\344\144\021\362\367\360\171\202\304\077\302\027\064\035\310\041" +
"\054\162\225\276\110\324\004\044\207\351\026\107\370\222\241\223" +
"\170\256\074\017\137\326\164\020\126\051\037\302\127\054\273\360" +
"\227\320\344\341\253\232\016\344\020\372\346\341\153\015\023\276" +
"\256\351\200\053\141\076\036\276\101\220\310\040\164\345\341\233" +
"\104\115\100\162\230\156\222\360\055\313\056\252\265\112\135\021" +
"\276\115\324\004\044\207\351\362\046\224\357\141\371\204\357\040" +
"\024\262\010\363\366\360\335\006\152\002\222\303\164\171\021\146" +
"\361\360\075\215\056\157\302\367\211\232\200\344\060\135\036\204" +
"\037\040\271\260\112\001\311\141\272\201\060\071\367\241\246\003" +
"\071\204\276\171\370\221\030\302\217\065\272\362\075\374\304\232" +
"\360\123\242\032\147\102\371\036\312\046\234\010\322\011\345\173" +
"\370\031\110\047\344\343\341\347\110\216\027\341\027\004\111\360" +
"\060\020\352\152\002\222\303\164\363\043\374\222\270\357\053\213" +
"\116\342\071\176\204\376\173\070\211\250\046\203\120\276\207\137" +
"\023\325\144\020\026\341\341\067\015\324\004\044\207\351\346\113" +
"\070\231\270\217\277\207\337\022\325\302\052\055\223\360\073\057" +
"\010\277\067\364\106\351\246\367\360\007\015\005\067\017\223\204" +
"\077\132\166\341\313\052\315\167\037\376\104\334\047\203\120\256" +
"\207\077\023\044\062\010\263\172\370\013\222\223\105\050\337\303" +
"\100\230\205\360\327\114\204\123\020\012\156\204\176\170\370\233" +
"\241\067\112\227\007\341\357\110\256\132\036\376\141\350\215\322" +
"\315\207\360\117\202\044\354\103\212\360\057\242\046\040\071\114" +
"\227\007\341\337\110\256\032\036\346\101\370\117\056\204\377\022" +
"\044\145\023\212\367\260\346\305\377\066\251\325\322\023\312\367" +
"\320\075\141\255\035\101\241\323\266\251\011\110\016\323\015\036" +
"\332\125\163\275\017\233\014\275\121\272\136\020\126\300\303\366" +
"\206\336\050\135\057\010\263\170\130\353\340\003\141\255\143\172" +
"\302\174\074\254\165\162\113\050\177\037\226\117\130\353\114\220" +
"\210\040\314\352\141\255\013\222\223\105\330\025\311\211\042\324" +
"\255\322\132\067\242\046\040\071\114\227\067\141\167\242\046\040" +
"\071\114\227\065\241\374\125\232\017\341\177\174\010\334\072\243" +
"\355\000\000"
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
public Parser_cstast_syntaxInterfaceParser() {}

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
    public silver.definition.concrete_syntax.ast.env_parser.NISyntaxInner parse(java.io.Reader input,String inputName)
    throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
    {
    this.charBuffer = edu.umn.cs.melt.copper.runtime.io.ScannerBuffer.instantiate(input);
    setupEngine();
    startEngine(edu.umn.cs.melt.copper.runtime.io.InputPosition.initialPos(inputName));
    silver.definition.concrete_syntax.ast.env_parser.NISyntaxInner parseTree = (silver.definition.concrete_syntax.ast.env_parser.NISyntaxInner) runEngine();
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
        TERMINAL_COUNT = 77;
        GRAMMAR_SYMBOL_COUNT = 121;
        SYMBOL_COUNT = 247;
        PARSER_STATE_COUNT = 231;
        SCANNER_STATE_COUNT = 269;
        DISAMBIG_GROUP_COUNT = 2;
        SCANNER_START_STATENUM = 1;
        PARSER_START_STATENUM = 1;
        EOF_SYMNUM = 0;
        EPS_SYMNUM = -1;
        try { initArrays(); }
        catch(java.io.IOException ex) { ex.printStackTrace(); System.exit(1); }
        catch(java.lang.ClassNotFoundException ex) { ex.printStackTrace(); System.exit(1); }
        disambiguationGroups = new java.util.BitSet[2];
        disambiguationGroups[0] = newBitVec(77,63,70);
        disambiguationGroups[1] = newBitVec(77,54,70);
    }

}
