package dhg.logic.base

trait Tokens {

    def LAMBDA = "\\"; def LAMBDA_LIST = List("\\")

    //Punctuation
    val DOT = "."
    val OPEN = "("
    val CLOSE = ")"
    val COMMA = ","

    //Operations
    def NOT = "-"; def NOT_LIST = List("not", "-", "!")
    def AND = "&"; def AND_LIST = List("and", "&", "^")
    def OR = "|"; def OR_LIST = List("or", "|")
    def IMP = "->"; def IMP_LIST = List("implies", "->", "=>")
    def LIMP = "<-"; def LIMP_LIST = List("<-", "<=")
    def IFF = "<->"; def IFF_LIST = List("iff", "<->", "<=>")
    def EQ = "="; def EQ_LIST = List("=", "==")
    def NEQ = "!="; def NEQ_LIST = List("!=")
    
    protected def makeSymbolList(tokenList: Iterable[String]) = 
        tokenList.filter("""^[-\\\.\[\](),!&^|>=<\+:]+$""".r.findFirstIn(_).isDefined)

}

object Tokens extends Tokens {
    
}