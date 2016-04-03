package huffman.algorithm.impl


import java.io.{BufferedInputStream, File, FileInputStream, FileOutputStream}

import huffman.algorithm.Huffman
import huffman.algorithm.help.{Frequency, Tree}
import huffman.util.{BitOutputStream, BusinessConstant, Util}


/**
  * Created by Dragos on 01.04.2016.
  */

class HuffmanAdaptiveEncoder(fin: File, fout: File, base: Int) extends Huffman {

  var input: BufferedInputStream = null
  var output: BitOutputStream = null

  var tree: Tree = null
  var frequency: Frequency = null

  var nrBitsOfBase: Int = Integer.toString(base - 1, 2).length
  var nbCh: Int = 0


  override def read(): Int = {
    input.read()
  }

  override def write(symbol: Int) {

    tree.getCode(symbol).foreach(x=>{

        /*var bits = Integer.toString(x, 2).split("").map(b=>b.toInt).toBuffer[Int]
        while (bits.size < nrBitsOfBase) {
          bits = ArrayBuffer(0) ++ bits
        }

        bits.foreach(b => output.writeBit(b))*/

        var length = 0
        while(x >> length > 0){ length+=1 }
        (length+1 to nrBitsOfBase).foreach( _ => output.writeBit(0))
        length-=1
        while(length >= 0){
          output.writeBit((x >> length) & 1)
          length-=1
        }
    })
  }

  override def onStart() {
    output = new BitOutputStream(new FileOutputStream(fout))
    input = new BufferedInputStream(new FileInputStream(fin))

    nbCh = 0
    frequency = new Frequency()
    this.tree = frequency.buildTree(base)
  }

  override def onComplete() {

    write(BusinessConstant.EOF)
    output.flushBit()

    output.close()
    input.close()
  }

  override def onAfterWrite(symbol: Int) {
    frequency.increment(symbol)
    nbCh += 1
    if (nbCh < BusinessConstant.RESET_VALUE && Util.isPowerOf2(nbCh) || nbCh % BusinessConstant.RESET_VALUE == 0) {
      this.tree = frequency.buildTree(base)
      frequency = new Frequency()

    }
  }

}