package dhg.logic.fol

import dhg.logic.base.Tokens

trait FolTokens extends Tokens {

    //Quantifiers
    val EXISTS = "exists"; def EXISTS_LIST = List("some", "exists", "exist")
    val ALL = "all"; def ALL_LIST = List("all", "forall")

    //Collections of tokens
    def BINOPS = AND_LIST ++ OR_LIST ++ IMP_LIST ++ LIMP_LIST ++ IFF_LIST
    def PUNCT = List(DOT, OPEN, CLOSE, COMMA)
    def QUANTS = EXISTS_LIST ++ ALL_LIST

    def TOKENS = BINOPS ++ EQ_LIST ++ NEQ_LIST ++ QUANTS ++ LAMBDA_LIST ++ PUNCT ++ NOT_LIST

    //Special
    def SYMBOLS = this.makeSymbolList(TOKENS)

}

object FolTokens extends FolTokens {

}
