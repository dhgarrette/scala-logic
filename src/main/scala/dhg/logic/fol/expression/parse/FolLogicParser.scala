package dhg.logic.fol.expression.parse

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.MapBuilder
import scala.collection.mutable.HashMap
import dhg.logic.base.expression.parse.BaseLogicParser
import dhg.logic.top.expression.parse.ExpectedMoreTokensException
import dhg.logic.top.expression.Variable
import dhg.logic.fol.expression._
import dhg.logic.fol.FolTokens

class FolLogicParser(keywords: FolTokens = FolTokens, quoteChars: List[(Char, Char, Char, Boolean)] = List.empty) extends BaseLogicParser[FolExpression](quoteChars) {

    override protected def makeOperatorPrecedence(): Map[Option[String], Int] = {
        val operatorPrecedence = new MapBuilder[Option[String], Int, Map[Option[String], Int]](Map[Option[String], Int]())
        keywords.LAMBDA_LIST foreach { operatorPrecedence += Some(_) -> 1 }
        keywords.NOT_LIST foreach { operatorPrecedence += Some(_) -> 2 }
        operatorPrecedence += APP -> 3
        keywords.EQ_LIST ++ keywords.NEQ_LIST foreach { operatorPrecedence += Some(_) -> 4 }
        keywords.QUANTS foreach { operatorPrecedence += Some(_) -> 5 }
        keywords.AND_LIST foreach { operatorPrecedence += Some(_) -> 6 }
        keywords.OR_LIST foreach { operatorPrecedence += Some(_) -> 7 }
        keywords.IMP_LIST ++ keywords.LIMP_LIST foreach { operatorPrecedence += Some(_) -> 8 }
        keywords.IFF_LIST foreach { operatorPrecedence += Some(_) -> 9 }
        operatorPrecedence += None -> 10
        return operatorPrecedence.result
    }

    override protected def getAllSymbols() =
        keywords.SYMBOLS

    override protected def isvariable(tok: String) =
        !keywords.TOKENS.contains(tok)

    override protected def handle(tok: String, context: Option[String]): FolExpression = {
        if (this.isvariable(tok))
            return this.handlevariable(tok, context)

        else if (keywords.NOT_LIST.contains(tok))
            return this.handlenegation(tok, context)

        else if (keywords.LAMBDA_LIST.contains(tok))
            return this.handlelambda(tok, context)

        else if (keywords.QUANTS.contains(tok))
            return this.handlequant(tok, context)

        else if (keywords.OPEN == tok)
            return this.handleopen(tok, context)

        throw new RuntimeException
    }

    override protected def makeNegatedExpression(expression: FolExpression) =
        FolNegatedExpression(expression)

    override protected def makeEqualityExpression(first: FolExpression, second: FolExpression): FolExpression =
        return FolEqualityExpression(first, second)

    override protected def get_BooleanExpression_factory(tok: String): Option[((FolExpression, FolExpression) => FolBooleanExpression)] = {
        if (keywords.AND_LIST.contains(tok))
            return Some(((e1: FolExpression, e2: FolExpression) => FolAndExpression(e1, e2)))
        else if (keywords.OR_LIST.contains(tok))
            return Some(((e1: FolExpression, e2: FolExpression) => FolOrExpression(e1, e2)))
        else if (keywords.IMP_LIST.contains(tok))
            return Some(((e1: FolExpression, e2: FolExpression) => FolIfExpression(e1, e2)))
        else if (keywords.LIMP_LIST.contains(tok))
            return Some(((e1: FolExpression, e2: FolExpression) => FolIfExpression(e2, e1)))
        else if (keywords.IFF_LIST.contains(tok))
            return Some(((e1: FolExpression, e2: FolExpression) => FolIffExpression(e1, e2)))
        else
            return None
    }

    protected def handlequant(tok: String, context: Option[String]): FolExpression = {
        // Expression is a quantified expression: some x.M
        val factory = this.get_QuantifiedExpression_factory(tok)

        if (!this.inRange(0))
            throw new ExpectedMoreTokensException(Some(this.getCurrentIndex + 2), Some("Variable and Expression expected following quantifier '" + tok + "'."))
        val vars = this.get_next_token_variable("quantified") +: get_additional_bound_vars("quantified")
        if (this.inRange(0) && this.getToken(0) == keywords.DOT)
            this.nextToken() //swallow the dot

        return vars.foldRight(this.parseExpression(Some(tok)))(this.makeQuanifiedExpression(factory, _, _))
    }

    protected def get_QuantifiedExpression_factory(tok: String): ((Variable, FolExpression) => FolQuantifiedExpression) = {
        if (keywords.EXISTS_LIST.contains(tok))
            return ((v: Variable, term: FolExpression) => FolExistsExpression(v, term))
        else if (keywords.ALL_LIST.contains(tok))
            return ((v: Variable, term: FolExpression) => FolAllExpression(v, term))
        else
            this.assertToken(tok, keywords.QUANTS)
        throw new RuntimeException
    }

    protected def makeQuanifiedExpression(factory: ((Variable, FolExpression) => FolQuantifiedExpression), variable: Variable, term: FolExpression) = factory(variable, term)

    override protected def makeApplicationExpression(function: FolExpression, argument: FolExpression) =
        FolApplicationExpression(function: FolExpression, argument: FolExpression)

    override protected def makeVariableExpression(name: String) =
        FolVariableExpression(Variable(name))

    override protected def makeLambdaExpression(variable: Variable, term: FolExpression) =
        FolLambdaExpression(variable, term)

}
