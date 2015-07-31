package dhg.logic.discourse.candc.call

trait Boxer {

    def callBoxer(candcOut: String, args: Map[String,String] = Map(), verbose: Boolean = false): String

}