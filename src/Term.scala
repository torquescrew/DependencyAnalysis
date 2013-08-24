/**
 * Created with IntelliJ IDEA.
 * User: tobysuggate
 * Date: 19/08/13
 * Time: 8:39 PM
 */
abstract class Term

case class Operator(term: String) extends Term {

}

case class Type(term: String) extends Term {

}

case class Keyword(term: String) extends Term {

}


