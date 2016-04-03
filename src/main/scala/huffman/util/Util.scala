package huffman.util

import javafx.util.StringConverter

import scalafx.beans.binding.Bindings
import scalafx.beans.property.{IntegerProperty, StringProperty}


/**
 * Created by Dragos on 4/1/2016.
 */
object Util {

    val numeric = "([0-9]+)".r

    def isPowerOf2(x: Int): Boolean = {
        x > 0 && (x & -x) == x
    }
}
