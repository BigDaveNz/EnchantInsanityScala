package nz.co.bigdavenz.ei.item

import net.minecraft.item._
import nz.co.bigdavenz.ei.core.chat.Communicate
import nz.co.bigdavenz.ei.core.traits.Enchantable
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.client.renderer.texture.IIconRegister
import nz.co.bigdavenz.ei.lib.Reference
import nz.co.bigdavenz.ei.EnchantInsanity

/**
 * Created by David J. Dudson on 15/01/14.'
 *
 * Work Around for EI Items that do not have a specific tool material
 */

object EiItemTool {

  def createEiTool(consumedToolStack: ItemStack, owner: EntityPlayer): ItemStack = {

    val newTool = EiItems.eiTool

    if (consumedToolStack.getItem.isInstanceOf[ItemTool]) {
      Communicate.withConsoleDebug("EI Tool being created", "ToolCreation")
      newTool.initialised = true
      newTool.consumedTool = consumedToolStack.getItem.asInstanceOf[ItemTool]
      newTool.consumedToolStack = consumedToolStack
      newTool.setOwner(owner)
      newTool.maxEnchantments = newTool.consumedTool.func_150913_i().getHarvestLevel
      newTool.setMaxDamage(newTool.consumedTool.getMaxDamage)
      newTool.setUnlocalizedName(newTool.getOwnerName + "'s Enchanted " + newTool.consumedToolStack.getDisplayName)
      newTool.onToolTypeCreate
      val newToolStack: ItemStack = new ItemStack(EiItems.eiTool)
      newToolStack.setItemDamage(newTool.consumedToolStack.getItemDamage)
      newToolStack
    } else {
      null
    }
  }

}

class EiItemTool extends Item with Enchantable {

  var consumedTool: ItemTool = null
  var consumedToolStack: ItemStack = null
  var initialised = false
  var droppable = true
  val validToolTypes: List[String] = List("axe", "pick", "shovel", "hoe", "sword")

  def EiItemTool() {
    this.setUnlocalizedName("EIToolUnregistered")
    this.setTextureName("eitool")
    this.setCreativeTab(EnchantInsanity.tabEi)
  }


  var getToolType: String = {
    if (initialised) {
      this.consumedTool match {
        case _: ItemAxe => "axe"
        case _: ItemPickaxe => "pick"
        case _: ItemSpade => "shovel"
        case _: ItemHoe => "hoe"
        case _: ItemSword => "sword"
        case _ =>
          Communicate.withConsole("Unnacounted for Tool Class:" + consumedTool.toString)
          "tool"
      }
    } else {
      "uninitialized"
    }
  }

  def getHarvestLevel {
    if (initialised) {
      consumedTool.func_150913_i().getHarvestLevel
    }
  }

  def onToolTypeCreate {
    Communicate.withPlayer(getOwner, "Hey! You just made an Enchanted Pick!!! Look after it! It was made with the same stats as a " + consumedTool.getItemStackDisplayName(consumedToolStack))
    Communicate.withPlayer(getOwner, this.getDamage(consumedToolStack) + "/" + this.getMaxDamage + ": UsesLeft/MaxUses")

    this.getToolType match {
      case "pick" => this.setTextureName("pick")
      case "spade" => this.setTextureName("shovel")
      case "hoe" => this.setTextureName("hoe")
      case "sword" => this.setTextureName("sword")
      case "axe" => this.setTextureName("axe")
      case _ => this.setTextureName("tool")
    }
  }

  override def onDroppedByPlayer(item: ItemStack, player: EntityPlayer): Boolean = {
    Communicate.withPlayer(getOwner, "You dropped it??? Seriously! Didn't I tell you to look after it!")
    this.droppable
  }


  override def registerIcons(iconRegister: IIconRegister) {
    validToolTypes.foreach(toolType => iconRegister.registerIcon(Reference.modId + ":" + toolType))
  }
}
