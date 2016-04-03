package huffman.service.impl

import java.io.File

import huffman.algorithm.impl.{HuffmanAdaptiveDecoder, HuffmanAdaptiveEncoder}
import huffman.domain.ApplicationModel
import huffman.service.ApplicationService
import huffman.util.BusinessConstant

import scalafx.stage.{DirectoryChooser, FileChooser}

/**
  * Created by Dragos on 03.04.2016.
  */
class ApplicationServiceImpl extends ApplicationService{

  var appModel: ApplicationModel = null

  override def uploadFileFrom() = {

    val dialog = new FileChooser()
    var file: File = dialog.showOpenDialog(null)

    if(file != null){

      var concat = file.getName.split("\\.")
      var extension = concat(1)
      var info = extension.split("_")

      appModel.orinialName.value = concat(0)
      appModel.fileFrom.value = file
      if(info(0) == BusinessConstant.EXTENSION_HUFF){
        appModel.extension.value = info(1)
        appModel.base.value = info(2).toInt
        appModel.isCompress.value = false
      } else {
        appModel.extension.value = info(0)
        appModel.isCompress.value = true
      }

    }

  }

  override def execute(): Unit = {
    var t = System.currentTimeMillis()
    if(appModel.fileTo.value != null){
      if(appModel.isCompress.value){
        appModel.fileTo.value = appModel.fileTo.value.toPath.resolve(appModel.orinialName.value + "." + BusinessConstant.EXTENSION_HUFF + "_" + appModel.extension.value + "_" + appModel.base.value).toFile
        new HuffmanAdaptiveEncoder(appModel.fileFrom.value, appModel.fileTo.value, appModel.base.value).execute()
      } else {
        new HuffmanAdaptiveDecoder(appModel.fileFrom.value, appModel.fileTo.value, appModel.base.value).execute()
      }
    }
    appModel.execTime.value = System.currentTimeMillis() - t
  }

  override def uploadFileTo(): Unit = {

    var file: File = null

    if(appModel.isCompress.value){
      val dialog = new DirectoryChooser()
      file = dialog.showDialog(null)
    } else {
      val dialog = new FileChooser()
      dialog.setInitialFileName(appModel.orinialName.value + "." + appModel.extension.value)
      file = dialog.showSaveDialog(null)
    }

    appModel.fileTo.value = file
  }
}
