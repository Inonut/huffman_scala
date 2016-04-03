package huffman.domain

import scala.collection.mutable.ArrayBuffer

class InternalNode(var children: ArrayBuffer[Node], frequency: Int) extends Node(frequency) {

	def this(children: ArrayBuffer[Node]) {
		this(children, children.map(n => n.frequency).sum)
	}
}
