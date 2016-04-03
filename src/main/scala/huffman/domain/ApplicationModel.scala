package huffman.domain

import java.io.File

import scalafx.beans.property.{BooleanProperty, IntegerProperty, ObjectProperty, StringProperty}

/**
  * Created by Dragos on 03.04.2016.
  */
class ApplicationModel {

  val fileFrom: ObjectProperty[File] = new ObjectProperty[File]()
  val fileTo: ObjectProperty[File] = new ObjectProperty[File]()

  val base = new IntegerProperty()
  val extension = new StringProperty()
  val orinialName = new StringProperty()

  val isCompress = new BooleanProperty()
  val execTime = new IntegerProperty()
  val sevedBytes = new IntegerProperty()
}
