package huffman.algorithm.help

import huffman.domain.{InternalNode, Leaf, Node}
import huffman.util.BusinessConstant

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Dragos on 29.03.2016.
  */
class Tree(var root: InternalNode) {

  var leafs = ArrayBuffer.fill[Leaf](BusinessConstant.NR_CHAR)(null)

  makeCodes(root, ArrayBuffer[Int]())


  private def makeCodes(node: Node, code: ArrayBuffer[Int]) {
    node match {
      case x: Leaf =>
        val leaf = node.asInstanceOf[Leaf]
        leaf.code = code.clone()
        leafs(leaf.symbol) = leaf
      case x: InternalNode =>
        val internalNode = node.asInstanceOf[InternalNode]

        internalNode.children
          .zipWithIndex
          .foreach { case (entry, i) =>
            code += i
            makeCodes(entry, code)
            code.remove(code.size - 1)
          }
    }
  }

  def getCode(symbol: Int): ArrayBuffer[Int] = {
    leafs(symbol).code
  }
}
