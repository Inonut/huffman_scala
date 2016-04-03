package huffman.util



/**
 * Created by Dragos on 4/1/2016.
 */
object Util {

    def isPowerOf2(x: Int): Boolean = {
        x > 0 && (x & -x) == x
    }
}
