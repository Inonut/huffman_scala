package huffman.algorithm.help


import java.util.PriorityQueue

import huffman.domain.{InternalNode, Leaf, Node}
import huffman.util.BusinessConstant

import scala.collection.mutable.ArrayBuffer

class Frequency {

  var frequencies = ArrayBuffer.fill[Int](BusinessConstant.NR_CHAR)(1)

  def increment(symbol: Int) {
    frequencies(symbol) += 1
  }

  def buildTree(base: Int): Tree = {
    val pqueue = new PriorityQueue[Node]()

    frequencies.zipWithIndex.foreach { case (entry, i) =>
      if (entry > 0) {
        pqueue.add(new Leaf(i, entry))
      }
    }

    while (pqueue.size > 1) {
      var nodes = ArrayBuffer[Node]()
      (1 to base).foreach(_ =>
        if (pqueue.size > 0) {
          nodes += pqueue.remove()
        }
      )
      pqueue.add(new InternalNode(nodes, nodes.map(e => e.frequency).sum))
    }

    new Tree(pqueue.remove().asInstanceOf[InternalNode])
  }

}

