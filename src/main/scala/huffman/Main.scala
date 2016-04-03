package huffman

import java.io.{BufferedInputStream, File, FileInputStream}

import huffman.algorithm.impl.{HuffmanAdaptiveDecoder, HuffmanAdaptiveEncoder}
import huffman.domain.{InternalNode, Leaf}
import huffman.util.{BitInputStream, BusinessConstant, Util}

import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.io.Source

/**
  * Created by Dragos on 02.04.2016.
  */
object Main {

  class Test protected(x: Int) {
    print(x)
  }

  def main(args: Array[String]) {

    var base = 2

    val path = "F:\\MEGA\\IdeaProjects\\huffman_scala\\"

    val fin = new File(path +"testCompress.txt")
    val fout = new File(path +"testCompress.huff_png")
    val fout2 = new File(path +"testCompress2.txt")

    val encoder = new HuffmanAdaptiveEncoder(fin, fout, base)
    encoder.execute()

    println("----------")

    val decoder = new HuffmanAdaptiveDecoder(fout, fout2, base)
    decoder.execute()


/*    var list = ArrayBuffer(1,2,3)
    list = ArrayBuffer(0) ++ ArrayBuffer(1,2,3)

    list.foreach(x=>print(x))*/

    /*Util.maloc(0) match {
      case isEmpty => print("sdsd")
      case list => list.foreach(x=>print(x))
    }*/


    /*var list = Array.fill[Byte](5)(1)
    list(2) = 3

    list.foreach(x=>println(x))*/

   /* var list = Integer.toString(11,2).split("").map(x=>new Integer(x))

    list.foreach(x=>print(x))*/


  }
}