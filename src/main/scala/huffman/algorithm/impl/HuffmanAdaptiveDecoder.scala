package huffman.algorithm.impl

import java.io.{BufferedOutputStream, File, FileInputStream, FileOutputStream}

import huffman.algorithm.Huffman
import huffman.algorithm.help.{Frequency, Tree}
import huffman.domain.{InternalNode, Leaf, Node}
import huffman.util.{BitInputStream, BusinessConstant, Util}

/**
 * Created by Dragos on 01.04.2016.
 */
class HuffmanAdaptiveDecoder(fin: File, fout: File, base: Int) extends Huffman{

    var input: BitInputStream = null
    var output: BufferedOutputStream = null

    var tree: Tree = null
    var frequency: Frequency = null

    val nrBitsOfBase: Int = Integer.toString(base-1,2).length
    var nbCh: Int = 0

    override def read(): Int = {
        var currentNode = tree.root
        while (true) {
            val bit = input.readBit(nrBitsOfBase)

            var nextNode: Node = null
            if(bit != -1) {
                nextNode = currentNode.children(bit)
            }else {
                return -1
            }

            nextNode match {
                case leaf: Leaf =>
                    return leaf.symbol
                case currentNode1: InternalNode =>
                    currentNode = nextNode.asInstanceOf[InternalNode]
                case _ =>
            }
        }

      -1
    }

    override  def write(symbol: Int) = {
        output.write(symbol)
    }

    override def onStart() = {
        output = new BufferedOutputStream(new FileOutputStream(fout))
        input = new BitInputStream(new FileInputStream(fin))

        nbCh = 0
        frequency = new Frequency()
        this.tree = frequency.buildTree(base)
    }

    override def onComplete() =  {
        output.close()
        input.close()
    }

    override def onAfterWrite(symbol: Int) = {
        frequency.increment(symbol)
        nbCh += 1
        if (nbCh < BusinessConstant.RESET_VALUE && Util.isPowerOf2(nbCh) || nbCh % BusinessConstant.RESET_VALUE == 0) {
            this.tree = frequency.buildTree(base)
            frequency = new Frequency()
        }
    }


}
