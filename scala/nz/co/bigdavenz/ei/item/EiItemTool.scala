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

  def createEiTool(consumedTool: Item, owner: EntityPlayer): EiItemTool = {

    var newTool: EiItemTool = EiItems.eiTool

    if (consumedTool.isInstanceOf[ItemTool]) {
      newTool.initialised = true
      newTool.consumedTool = consumedTool.asInstanceOf[ItemTool]
      newTool.consumedToolStack = new ItemStack(consumedTool)
      newTool.setOwner(owner)
      newTool.maxEnchantments = newTool.consumedTool.func_150913_i().getHarvestLevel
      newTool.setMaxDamage(consumedTool.getMaxDamage)
      newTool.setDamage(new ItemStack(newTool), newTool.consumedToolStack.getItemDamage)
      newTool.setUnlocalizedName(newTool.getOwnerName + "'s Enchanted " + consumedTool.getUnlocalizedName)
      newTool.onToolTypeCreate
      newTool
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
  val validToolTypes: List[String] = List("axe", "pick", "spade", "hoe", "sword")

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
