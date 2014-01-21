package nz.co.bigdavenz.ei.item

import net.minecraft.item._
import cpw.mods.fml.common.registry.GameRegistry
import nz.co.bigdavenz.ei.item.tools._
import nz.co.bigdavenz.ei.lib.Reference
import net.minecraft.entity.player.EntityPlayer
import nz.co.bigdavenz.ei.core.chat.Communicate

/**
 * Created by David J. Dudson on 11/01/14.
 *
 * Item List
 */
object EiItems {

  val eiPickaxe = new EnchantedPickaxe().setUnlocalizedName("eipick")
  val eiAxe = new EnchantedAxe().setUnlocalizedName("eiaxe")
  val eiShovel = new EnchantedShovel().setUnlocalizedName("eishovel")
  val eiHoe = new EnchantedHoe().setUnlocalizedName("eihoe")
  val eiShears = new EnchantedShears().setUnlocalizedName("eishears")

  def init() {
    registerItem(eiPickaxe)
    registerItem(eiAxe)
    registerItem(eiShovel)
    registerItem(eiHoe)
    registerItem(eiShears)

  }

  def registerItem(item: Item) {
    GameRegistry.registerItem(item, item.getUnlocalizedName, Reference.modId)
  }

  def createEiTool(consumedToolStack: ItemStack, owner: EntityPlayer): ItemStack = {
    if (consumedToolStack.getItem.isInstanceOf[ItemTool]) {
      Communicate.withConsoleDebug("EI Tool being created from " + consumedToolStack.getDisplayName + " by " + owner.getDisplayName, "ToolCreation")
      val newTool: EiItemTool = getTool(consumedToolStack.getItem).asInstanceOf[EiItemTool]
      newTool.consumedToolStack = consumedToolStack
      newTool.converted = true
      newTool.consumedTool = consumedToolStack.getItem.asInstanceOf[ItemTool]
      newTool.consumedToolStack = consumedToolStack
      newTool.setOwner(owner)
      newTool.maxEnchantments = newTool.consumedTool.func_150913_i().getHarvestLevel
      newTool.setMaxDamage(newTool.consumedTool.getMaxDamage)
      val newToolStack: ItemStack = new ItemStack(newTool)
      newToolStack.setItemDamage(newTool.consumedToolStack.getItemDamage)
      newToolStack.getItem.asInstanceOf[EiItem].onCreate
      newToolStack.func_151001_c(newTool.getOwnerName + "'s Enchanted " + consumedToolStack.getDisplayName)
    } else {
      null
    }
  }

  def getTool(tool: Item): Item = {
    tool match {
      case _: ItemPickaxe => EiItems.eiPickaxe
      case _: ItemHoe => EiItems.eiHoe
      case _: ItemAxe => EiItems.eiAxe
      case _: ItemShears => EiItems.eiShears
      case _: ItemSpade => EiItems.eiShovel
      case _ => null
    }
  }
}
