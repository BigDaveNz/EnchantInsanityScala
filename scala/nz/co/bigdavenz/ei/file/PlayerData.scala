package nz.co.bigdavenz.ei.file

import net.minecraft.nbt.NBTTagCompound
import nz.co.bigdavenz.ei.core.chat.Communicate
import nz.co.bigdavenz.ei.lib.{GeneralReference, ModReference}


/**
 * Created by David J. Dudson on 11/01/14.
 *
 * Contains Information about Players using EI, Including Skill/LevelData
 */

object PlayerData {

  var players: NBTTagCompound = new NBTTagCompound

  val dataTypes: List[String] = List(GeneralReference.intClassToString, GeneralReference.booleanClassToString, GeneralReference.doubleClassToString, GeneralReference.floatClassToString, GeneralReference.stringClassToString)

  val compoundNameList: List[String] = List("Destroy", "Place", "Use", "Craft", "Player", "Misc", "Unlocks", "Ownership", "Tutorials")

  def getSubData(playerName: String, compoundName: String): NBTTagCompound = {
    checkCompoundForTag(players, playerName, "Tried to access sub compound for a player who doesnt exist!!!") match {
      case true =>
        val playerCompound: NBTTagCompound = players.getCompoundTag(playerName)
        playerCompound.getCompoundTag(compoundName)
      case false =>
        null
    }
  }

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
    dataTypes.foreach {
      dataType: String =>
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
    }
    returnValue
  }

  def getField(playerName: String, compoundName: String, fieldName: String, fieldType: String) {
    val subData: NBTTagCompound = getSubData(playerName, compoundName)
    checkCompoundForTag(subData, fieldName, "Attempted To get an Int field that doesn't exist:" + playerName + " - " + compoundName + " - " + fieldName) match {

      case true =>

        fieldType match {
          case GeneralReference.intClassToString =>
            subData.getInteger(fieldName)
          case GeneralReference.stringClassToString =>
            subData.getString(fieldName)
          case GeneralReference.booleanClassToString =>
            subData.getBoolean(fieldName)
          case GeneralReference.doubleClassToString =>
            subData.getDouble(fieldName)
          case GeneralReference.floatClassToString =>
            subData.getFloat(fieldName)
          case _ =>
            Communicate.withConsoleWarning("NBT", "Attempted to get field with invalid data type: " + fieldType)
        }
      case _ =>
    }
  }

  def setField(playerName: String, compoundName: String, fieldName: String, value: Any) {
    val compound: NBTTagCompound = getSubData(playerName, compoundName)
    Communicate.withConsoleDebug("Modifying field: " + playerName + " - " + compoundName + " - " + fieldName + ". from: " + getField(playerName, compoundName, fieldName, value.getClass.toString) + ", To: " + value)
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
