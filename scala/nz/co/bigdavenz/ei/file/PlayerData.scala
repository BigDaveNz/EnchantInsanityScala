package nz.co.bigdavenz.ei.file

import nz.co.bigdavenz.ei.file.data.{StringData, DataPackage}

/**
 * Created by David J. Dudson on 11/01/14.
 *
 * Contains Information about Players using EI, Including Skill/LevelData
 */

class PlayerData(playerName: String) extends DataPackage(playerName) {

  val playerData: DataPackage = new DataPackage(playerName)

  def getPlayerData(playerName: String) = playerData.getContents(playerName)

  def onCreate {
    playerData.appendContents("Name", new StringData(playerName))
    playerData.appendContents("Tutorials", DefaultContents.getTutorials())
  }

  //  def createPlayerData(playerName: String) {
  //    Communicate.withConsole("Creating data for: " + playerName)
  //    val playerData: NBTTagCompound = new NBTTagCompound
  //
  //    playerData.setTag("Destroy", new NBTTagCompound)
  //    playerData.setTag("Place", new NBTTagCompound)
  //    playerData.setTag("Use", new NBTTagCompound)
  //    playerData.setTag("Craft", new NBTTagCompound)
  //    playerData.setTag("Player", new NBTTagCompound)
  //    playerData.setTag("Misc", new NBTTagCompound)
  //    playerData.setTag("Unlocks", new NBTTagCompound)
  //    playerData.setTag("Ownership", new NBTTagCompound)
  //    playerData.setTag("Tutorials", new NBTTagCompound)
  //
  //    setField(playerName, "Misc", "Cheated", false)
  //    setField(playerName, "Misc", "Save Version", ModReference.modVersion)
  //    setField(playerName, "Misc", "Status", getStatus(playerName)(0))
  //    setField(playerName, "Misc", "Alias", getStatus(playerName)(1))
  //    setField(playerName, "Misc", "Website", getStatus(playerName)(2))
  //
  //    setField(playerName, "Tutorials", "Commands", false)
  //    setField(playerName, "Tutorials", "Leveling", false)
  //    setField(playerName, "Tutorials", "Conversion", false)
  //    setField(playerName, "Tutorials", "Hotkey's", false)
  //    setField(playerName, "Tutorials", "Enchanting", false)
  //  }
}
