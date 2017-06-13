/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2017, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala
package xml

/**
 * This object provides an extractor method to match a qualified node with
 *  its namespace URI
 *
 *  @author  Burak Emir
 *  @version 1.0
 */
object QNode {
  // TODO: Fix getOrElse(null)
  def unapplySeq(n: Node) = Some((n.scope.getURI(n.prefix.getOrElse(null)), n.label, n.attributes, n.child))
}
