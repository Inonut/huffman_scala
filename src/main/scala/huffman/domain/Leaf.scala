package huffman.domain

import scala.collection.mutable.ArrayBuffer

class Leaf(var symbol: Int, frequency: Int) extends Node(frequency) {

	var code: ArrayBuffer[Int] = null
	
	def this(symbol: Int){
		this(symbol,1)
	}

}
