package nz.co.bigdavenz.ei.file

import scala.xml.transform.{RewriteRule, RuleTransformer}

import net.minecraft.entity.player.EntityPlayer
import nz.co.bigdavenz.ei.core.chat.Communicate
import xml._

/**
 * Created by David J. Dudson on 17/03/14.
 *
 * Assists with XML Creation
 */
object XmlHelper {

  var mainPackage = new DataPackage()

  def getPlayersPackage: DataPackage = mainPackage.getSingleOfType[DataPackage]("Players")

  def getWorldsPackage: DataPackage = mainPackage.getSingleOfType[DataPackage]("Worlds")

  def getMiscPackage: DataPackage = mainPackage.getSingleOfType[DataPackage]("Misc")

  private def getNested(key: String, pack: DataPackage): Node = {
    val oldXML: Node = toElem(key)
    var newXML: Node = toElem(key)
    pack.foreach {
      case (k: String, v: Any) =>
        newXML = new RuleTransformer(new AddChildrenTo(newXML.label, toXml(k, v))).transform(oldXML).head
    }
    newXML
  }

  def toXml(key: String, value: Any): Node = {
    Communicate.withConsole("XML Serialisation for: " + key + "Value: " + value.getClass.toString)
    value match {
      case _: DataPackage => getNested(key, value.asInstanceOf[DataPackage])
      case _ if testAnyVal(value) => toElem(value.getClass.toString, value)
      case _ => <Error/>
    }
  }

  def addChild(n: Node, newChild: Node) = n match {
    case Elem(prefix, label, attribs, scope, child@_*) =>
      Elem(prefix, label, attribs, scope, child ++ newChild: _*)
    case _ => sys.error("Can only add children to elements!")
  }

  class AddChildrenTo(label: String, newChild: Node) extends RewriteRule {
    override def transform(n: Node) = n match {
      case n@Elem(_, `label`, _, _, _*) => addChild(n, newChild)
      case other => other
    }
  }

  def testAnyVal[T: Manifest](t: T) = manifest[T] <:< manifest[AnyVal]

  def toElem(tag: String, value: Any = null, tagInner: Node = null): Elem = {
    Elem(null, tag, null, TopScope, tagInner)
  }

  def getTutorials: DataPackage = {
    val tutorials: DataPackage = new DataPackage
    tutorials.getHeadOfType[Boolean]("FirstLogin")
    tutorials
  }

  def setMainXmlContents {
    mainPackage.getSingleOfType[DataPackage]("Players")
    mainPackage.getSingleOfType[DataPackage]("Worlds")
    mainPackage.getSingleOfType[DataPackage]("Misc")
  }

  def getPlayerPackage(player: EntityPlayer): DataPackage = {
    getPlayersPackage.getSingleOfType[DataPackage](player.getDisplayName)
  }

  def getPlayerPackage(player: EntityPlayer, subPackage: String): DataPackage = {
    getPlayersPackage.getSingleOfType[DataPackage](player.getDisplayName).getSingleOfType[DataPackage](subPackage)
  }

  def addNewPlayerToMainXml(player: EntityPlayer) {

    val newPlayerPackage = getPlayerPackage(player, "Initial")

    newPlayerPackage.getSingleOfType[DataPackage]("Destroy")
    newPlayerPackage.getSingleOfType[DataPackage]("Use")
    newPlayerPackage.getSingleOfType[DataPackage]("Craft")
    newPlayerPackage.getSingleOfType[DataPackage]("Personal")
    newPlayerPackage.getSingleOfType[DataPackage]("Unlocks")
    newPlayerPackage.getSingleOfType[DataPackage]("Misc")
    newPlayerPackage.getSingleOfType[DataPackage]("Tutorials")
    Communicate.withConsole("Player Data Created for: " + player.getDisplayName)
  }

}
