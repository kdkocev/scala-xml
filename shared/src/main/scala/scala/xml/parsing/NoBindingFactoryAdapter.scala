/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2017, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |                                         **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

package scala
package xml
package parsing

import factory.NodeFactory

/**
 * nobinding adaptor providing callbacks to parser to create elements.
 *   implements hash-consing
 */
class NoBindingFactoryAdapter extends FactoryAdapter with NodeFactory[Elem] {
  /** True.  Every XML node may contain text that the application needs */
  def nodeContainsText(label: String) = true

  /** From NodeFactory.  Constructs an instance of scala.xml.Elem -- TODO: deprecate as in Elem */
  protected def create(pre: String, label: String, attrs: MetaData, scope: NamespaceBinding, children: Seq[Node]): Elem =
    if(pre == null)
      Elem(None, label, attrs, scope, children.isEmpty, children: _*)
    else
      Elem(Some(pre), label, attrs, scope, children.isEmpty, children: _*)

  /** From FactoryAdapter.  Creates a node. never creates the same node twice, using hash-consing.
     TODO: deprecate as in Elem, or forward to create?? */
  def createNode(pre: String, label: String, attrs: MetaData, scope: NamespaceBinding, children: List[Node]): Elem =
    if(pre == null)
      Elem(None, label, attrs, scope, children.isEmpty, children: _*)
    else
      Elem(Some(pre), label, attrs, scope, children.isEmpty, children: _*)

  /** Creates a text node. */
  def createText(text: String) = Text(text)

  /** Creates a processing instruction. */
  def createProcInstr(target: String, data: String) = makeProcInstr(target, data)
}
