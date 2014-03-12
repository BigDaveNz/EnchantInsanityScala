package nz.co.bigdavenz.ei.file

import net.minecraft.nbt.NBTTagCompound
import nz.co.bigdavenz.ei.core.chat.Communicate
import nz.co.bigdavenz.ei.file.data.DataPackage
import nz.co.bigdavenz.ei.lib.ModReference
import scala.collection.mutable.HashMap

/**
 * Created by David J. Dudson on 11/01/14.
 *
 * Contains Information about Players using EI, Including Skill/LevelData
 */

object PlayerData {

  var playerData: DataPackage = new DataPackage("Players", HashMap.empty)

  //val compoundNameList: List[String] = List("Destroy", "Place", "Use", "Craft", "Player", "Misc", "Unlocks", "Ownership", "Tutorials")

  def getPlayerData(playerName: String) = playerData.getContents(playerName)

  def checkCompoundForTag(compound: NBTTagCompound, tagName: String, failMessage: String): Boolean = {
    try {
      compound.hasKey(tagName) match {
        case true =>
          true
        case false =>
          Communicate.withConsoleWarning("NBT", failMessage)
          false
      }
    }
    catch {
      case _: NullPointerException =>
        false
    }
  }

  def getStatus(playerName: String): List[String] = {
    playerName.toLowerCase match {
      case "bigdavenz" => List("Important", "BigDaveNz", "bigdavenz.co.nz")
      case "direwolf20" => List("Important", "Direwolf20", "youtube.com/direwolf20")
      case "vaht" => List("Important", "Tahg", "twitch.tv/tahg")
      case "grum" => List("Important", "Grum")
      case "notch" => List("Important", "Notch")
      case "slowpoke101" => List("Forgecraft", "ModPack Dev")
      case _ => List("Insignifigant")
      //todo add websites once I have Internet
    }
  }

  def createPlayerData(playerName: String) {
    Communicate.withConsole("Creating data for: " + playerName)
    val playerData: NBTTagCompound = new NBTTagCompound

    playerData.setTag("Destroy", new NBTTagCompound)
    playerData.setTag("Place", new NBTTagCompound)
    playerData.setTag("Use", new NBTTagCompound)
    playerData.setTag("Craft", new NBTTagCompound)
    playerData.setTag("Player", new NBTTagCompound)
    playerData.setTag("Misc", new NBTTagCompound)
    playerData.setTag("Unlocks", new NBTTagCompound)
    playerData.setTag("Ownership", new NBTTagCompound)
    playerData.setTag("Tutorials", new NBTTagCompound)

    setField(playerName, "Misc", "Cheated", false)
    setField(playerName, "Misc", "Save Version", ModReference.modVersion)
    setField(playerName, "Misc", "Status", getStatus(playerName)(0))
    setField(playerName, "Misc", "Alias", getStatus(playerName)(1))
    setField(playerName, "Misc", "Website", getStatus(playerName)(2))

    setField(playerName, "Tutorials", "Commands", false)
    setField(playerName, "Tutorials", "Leveling", false)
    setField(playerName, "Tutorials", "Conversion", false)
    setField(playerName, "Tutorials", "Hotkey's", false)
    setField(playerName, "Tutorials", "Enchanting", false)
  }

  def getPlayerData: NBTTagCompound = {
    players
  }

  def getField(playerName: String, compoundName: String, fieldName: String): Any = {

    var returnValue: Any = null
    for (dataType <- NBTTypeEnum.values)
      try {
        returnValue = getField(playerName, compoundName, fieldName, dataType)
      } catch {
        case _: ClassCastException =>
      } finally {
        returnValue match {
          case null =>
            Communicate.withConsoleWarning("NBT", "No value of any data type found in: " + playerName + " - " + compoundName + " - " + fieldName)
          case _ =>
        }
      }
    returnValue
  }

  def setField(playerName: String, compoundName: String, fieldName: String, value: Any) {
    val compound: NBTTagCompound = getSubData(playerName, compoundName)
    Communicate.withConsoleDebug("Modifying field: " + playerName + " - " + compoundName + " - " + fieldName + ". from: " + getField(playerName, compoundName, fieldName) + ", To: " + value)
    value match {
      case _: Int =>
        compound.setInteger(fieldName, value.asInstanceOf[Int])
      case _: String =>
        compound.setString(fieldName, value.asInstanceOf[String])
      case _: Boolean =>
        compound.setBoolean(fieldName, value.asInstanceOf[Boolean])
      case _: Double =>
        compound.setDouble(fieldName, value.asInstanceOf[Double])
      case _: Float =>
        compound.setFloat(fieldName, value.asInstanceOf[Float])
      case _ =>
        Communicate.withConsoleWarning("NBT", "Attempted to set field with invalid data type: " + value.getClass.toString)
    }
  }

  def setPlayerData(data: NBTTagCompound) {
    players = data
    Communicate.withConsole("Player Data Loaded/Overridden")
  }
}
