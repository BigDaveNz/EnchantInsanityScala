package nz.co.bigdavenz.ei.file

import scala.collection.mutable.ListBuffer

import net.minecraft.entity.player.EntityPlayer
import nz.co.bigdavenz.ei.core.chat.Communicate

/**
 * Created by David J. Dudson on 17/03/14.
 *
 * Assists with XML Creation
 */
object XmlHelper {

  val mainPackage = new DataPackage()

  def getPlayersPackage = mainPackage.getPackage("Players")

  def getWorldsPackage = mainPackage.getPackage("Worlds")

  def getMiscPackage = mainPackage.getPackage("Misc")

  private def getNested(pack: DataPackage): ListBuffer[xml.Node] = {
    val nestedElements: ListBuffer[xml.Node] = new ListBuffer
    pack.getMap.foreach {
      case (key, value) => nestedElements.append(toXml(key, value))
    }
    nestedElements
  }

  def toXml(key: String, value: Any): xml.Node = {
    Communicate.withConsole("XML Serialisation for: " + key + "Value: " + value.getClass.toString)
    value match {
      case _: String => <String key={key} value={value.toString}/>
      case _: Int => <Int key={key} value={value.toString}/>
      case _: Boolean => <Boolean key={key} value={value.toString}/>
      case _: Float => <Float key={key} value={value.toString}/>
      case _: Double => <Double key={key} value={value.toString}/>
      case _: Short => <Short key={key} value={value.toString}/>
      case _: Long => <Long key={key} value={value.toString}/>
      case _: DataPackage => <DataPackage key={key}>
        {getNested(value.asInstanceOf[DataPackage])}
      </DataPackage>
      case _ => <Error/>
    }
  }

  def fromXml(node: xml.Node) {

  }

  def getTutorials: DataPackage = {
    val tutorials: DataPackage = new DataPackage
    tutorials.setContents("FirstLogin", false)
    tutorials
  }

  def setMainXmlContents {
    mainPackage.setContents("Players", new DataPackage())
    mainPackage.setContents("Worlds", new DataPackage())
    mainPackage.setContents("Misc", new DataPackage())
  }

  def getPlayerPackage(player: EntityPlayer): DataPackage = {
    getPlayerPackage(player, "Root")
  }

  def getPlayerPackage(player: EntityPlayer, subPackage: String): DataPackage = {
    subPackage match {
      case "Root" =>
        getPlayersPackage.getPackage(player.getDisplayName)
      case "Initial" =>
        getPlayersPackage.setContents(player.getDisplayName, new DataPackage())
        getPlayersPackage.getPackage(player.getDisplayName)
      case _ =>
        getPlayersPackage.getPackage(player.getDisplayName).getPackage(subPackage)
    }
  }

  def addNewPlayerToMainXml(player: EntityPlayer) {
    val newPlayerPackage = getPlayerPackage(player, "Initial")

    newPlayerPackage.setContents("Destroy", new DataPackage)
    newPlayerPackage.setContents("Use", new DataPackage)
    newPlayerPackage.setContents("Craft", new DataPackage)
    newPlayerPackage.setContents("Personal", new DataPackage)
    newPlayerPackage.setContents("Unlocks", new DataPackage)
    newPlayerPackage.setContents("Misc", new DataPackage)
    newPlayerPackage.setContents("Tutorials", getTutorials)
    Communicate.withConsole("Player Data Created for: " + player.getDisplayName)

  }

}
