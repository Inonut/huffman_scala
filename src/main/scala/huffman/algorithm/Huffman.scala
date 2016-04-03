package huffman.algorithm

import huffman.util.BusinessConstant

/**
 * Created by Dragos on 01.04.2016.
 */
trait Huffman {

    def execute() {
        val t = System.nanoTime()

        onStart()
        while (true) {
            onBeforeRead()
            //t = System.nanoTime()
            val symbol = read()
            //println("read: " + (System.nanoTime() - t))
            //println (symbol)
            if (symbol == -1 || symbol == BusinessConstant.EOF){
                onComplete()
                println("execute " + (System.nanoTime() - t))
                return
            }
            //t = System.nanoTime()
            write(symbol)
           // println("write: " + (System.nanoTime() - t))
           // t = System.nanoTime()
            onAfterWrite(symbol)
           // println("after write: " + (System.nanoTime() - t))

        }
    }

    def onStart(){}

    def onComplete(){}

    def onBeforeRead(){}

    def onAfterWrite(symbol: Int){}

    def read(): Int

    def write(symbol: Int)
}