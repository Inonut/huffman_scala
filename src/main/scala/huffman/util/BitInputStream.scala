package huffman.util

import java.io.{BufferedInputStream, InputStream}

/**
  * Created by Dragos on 02.04.2016.
  */
class BitInputStream(is:InputStream) extends BufferedInputStream(is){

  private var nextByte = -1
  private var nbBits = 0

  def readBit(): Int = {

    if (nbBits == 0) {
      nextByte = super.read()
      if (nextByte == -1) {
        return -1
      }
      nbBits = 8
    }
    nbBits -= 1

    (nextByte >> nbBits) & 1
  }

  def readBit(nrBits: Int):Int = {

    var result = 0

    (1 to nrBits).foreach(i => {
      val b = readBit()
      if(b == -1){
        if(i == 1){
          return -1
        } else {
          return result
        }
      }
      result = (result << 1) | b
    })

    result

    /*if(nrBits == 1){
      return readBit()
    } else {
      var result = 0

      (1 to nrBits).map(_ => readBit()).filter(_ != -1).toList match {
        case Nil => return -1
        case list => list.foreach(b => result = (result << 1) | b)
      }

      return result
    }*/
  }
}
