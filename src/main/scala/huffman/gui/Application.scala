package huffman.gui

import java.io.File
import javafx.beans.binding.Bindings
import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.util.StringConverter

import huffman.domain.ApplicationModel
import huffman.service.impl.ApplicationServiceImpl
import huffman.util.Util

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.{HPos, Insets, Pos, VPos}
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label, TextField}
import scalafx.scene.layout.{ColumnConstraints, GridPane, RowConstraints, StackPane}
import scalafx.scene.paint.Color

/**
  * Created by Dragos on 03.04.2016.
  */
object Application extends JFXApp{

  val appModel = new ApplicationModel()
  val appService = new ApplicationServiceImpl()
  appService.appModel = appModel


  this.stage = new PrimaryStage {
    title = "Huffman"
    resizable = false
    scene = new Scene(450,300) {
      fill = Color.LightGreen

      root = new StackPane{
        children = List(
          new GridPane {
            hgap = 10
            vgap = 10
            alignment = Pos.Center
            padding = Insets.apply(5)
            columnConstraints = List(
              new ColumnConstraints{
                halignment = HPos.Right
              },
              new ColumnConstraints{
                halignment = HPos.Left
              }
            )
            rowConstraints = List(
              new RowConstraints{
                valignment = VPos.Center
              },
              new RowConstraints{
                valignment = VPos.Center
              },
              new RowConstraints{
                valignment = VPos.Center
              },
              new RowConstraints{
                valignment = VPos.Center
              }
            )
            children = List(
              new Label("Baza"){
                GridPane.setRowIndex(this,0)
                GridPane.setColumnIndex(this,0)
              },
              new TextField{
                GridPane.setRowIndex(this,0)
                GridPane.setColumnIndex(this,1)
                maxWidth = 50
                Bindings.bindBidirectional(text.delegate, appModel.base.delegate, new StringConverter[Number](){
                  override def fromString(string: String): Number = string match {
                    case Util.numeric(s) => string.toInt
                    case _ => 0
                  }
                  override def toString(obj: Number): String = obj match {
                    case null => ""
                    case _ => obj.toString
                  }
                })
                text.addListener(new ChangeListener[String]() {
                  override def changed(observable: ObservableValue[_ <: String], oldValue: String, newValue: String): Unit = {
                    newValue match {
                      case Util.numeric(_) | "" => null
                      case _ => text.value = oldValue
                    }
                  }
                })
              },
              new Button("Alege calea"){
                GridPane.setRowIndex(this,1)
                GridPane.setColumnIndex(this,0)
                onAction = handle { appService.uploadFileFrom() }
              },
              new Label(){
                GridPane.setRowIndex(this,1)
                GridPane.setColumnIndex(this,1)
                maxWidth = 200
                Bindings.bindBidirectional(text.delegate, appModel.fileFrom.delegate, new StringConverter[File](){
                  override def fromString(string: String): File = new File(string)
                  override def toString(obj: File): String = obj match {
                    case null => ""
                    case _ => obj.getName
                  }
                })
              },
              new Button("Executa"){
                GridPane.setRowIndex(this,2)
                GridPane.setColumnIndex(this,0)
                GridPane.setColumnSpan(this,2)
                disable.bind(Bindings.or(appModel.fileFrom.delegate.isNull(), appModel.base.delegate.lessThan(2)))
                onAction = handle {
                  appService.uploadFileTo()
                  appService.execute()
                }
              },
              new Label(){
                GridPane.setRowIndex(this,3)
                GridPane.setColumnIndex(this,0)
                GridPane.setColumnSpan(this,2)
                visible.bind(appModel.execTime.delegate.isNotEqualTo(0))
                text.bind(Bindings.format("S-a executat in %d milisecunde.",appModel.execTime.delegate).delegate)
              }
            )
          }
        )
      }
    }
  }
}
