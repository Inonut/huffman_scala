package huffman.domain;

class Node protected(var frequency: Int) extends Comparable[Node]{

	def this(){
		this(0)
	}

	override def compareTo(o: Node): Int = {
		frequency - o.frequency
	}
}
