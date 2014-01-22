package nz.co.bigdavenz.ei.item

import net.minecraft.item._
import cpw.mods.fml.common.registry.GameRegistry
import nz.co.bigdavenz.ei.item.tools._
import nz.co.bigdavenz.ei.lib.Reference
import net.minecraft.entity.player.EntityPlayer
import nz.co.bigdavenz.ei.core.chat.Communicate
import nz.co.bigdavenz.ei.EnchantInsanity

/**
 * Created by David J. Dudson on 11/01/14.
 *
 * Item List
 */
object EiItems {

  var eiPickaxe = new EnchantedPickaxe().setUnlocalizedName("eipick")
  var eiAxe = new EnchantedAxe().setUnlocalizedName("eiaxe")
  var eiShovel = new EnchantedShovel().setUnlocalizedName("eishovel")
  var eiHoe = new EnchantedHoe().setUnlocalizedName("eihoe")
  var eiShears = new EnchantedShears().setUnlocalizedName("eishears")

  def init() {
    registerItem(eiPickaxe)
    registerItem(eiAxe)
    registerItem(eiShovel)
    registerItem(eiHoe)
    registerItem(eiShears)
  }

  def registerItem(item: Item) {
    item.setCreativeTab(EnchantInsanity.tabEi)
    GameRegistry.registerItem(item, item.getUnlocalizedName, Reference.modId)
  }

  def createEiTool(consumedToolStack: ItemStack, owner: EntityPlayer): ItemStack = {
    if (consumedToolStack.getItem.isInstanceOf[ItemTool]) {
      val newTool: EiItemTool = getTool(consumedToolStack.getItem).asInstanceOf[EiItemTool]
      newTool.onConverted(consumedToolStack:ItemStack,owner)
      val newToolStack: ItemStack = new ItemStack(newTool)
      newToolStack.setItemDamage(newTool.consumedToolStack.getItemDamage)
      newToolStack.getItem.asInstanceOf[EiItem].onCreate
      newToolStack.func_151001_c(owner.getDisplayName + "'s Enchanted " + consumedToolStack.getDisplayName)
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
