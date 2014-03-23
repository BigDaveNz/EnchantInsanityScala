package nz.co.bigdavenz.ei.item

import net.minecraft.item._
import cpw.mods.fml.common.registry.GameRegistry
import nz.co.bigdavenz.ei.lib.ModReference
import net.minecraft.entity.player.EntityPlayer
import nz.co.bigdavenz.ei.EnchantInsanity
import nz.co.bigdavenz.ei.item.weapons.{EiItemWeapon, EnchantedSword}

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
  var eiSword = new EnchantedSword().setUnlocalizedName("eisword")

  def init() {
    registerItem(eiPickaxe)
    registerItem(eiAxe)
    registerItem(eiShovel)
    registerItem(eiHoe)
    registerItem(eiShears)
    registerItem(eiSword)
  }

  def registerItem(item: Item) {
    item.setCreativeTab(EnchantInsanity.tabEi)
    GameRegistry.registerItem(item, item.getUnlocalizedName, ModReference.modId)
  }

  def createEiWeapon(consumedWeaponStack: ItemStack, owner: EntityPlayer): ItemStack = {
    consumedWeaponStack.getItem match {
      case _: ItemSword =>
        val newWeaponStack: ItemStack = new ItemStack(EiItems.eiSword.asInstanceOf[EiItemWeapon])
        newWeaponStack.getItem.asInstanceOf[EiItemWeapon].onConverted(consumedWeaponStack, newWeaponStack, owner)
        newWeaponStack
    }
  }

  def createEiTool(consumedToolStack: ItemStack, owner: EntityPlayer): ItemStack = {
    consumedToolStack.getItem match {
      case _: ItemTool =>
        val newToolStack: ItemStack = new ItemStack(getTool(consumedToolStack.getItem).asInstanceOf[EiItemTool])
        newToolStack.getItem.asInstanceOf[EiItemTool].onConverted(consumedToolStack, newToolStack, owner)
        newToolStack
      case _: ItemShears =>
        val newShearStack: ItemStack = new ItemStack(getTool(consumedToolStack.getItem).asInstanceOf[EiItemTool])
        newShearStack.getItem.asInstanceOf[EiItemTool].onConverted(consumedToolStack, newShearStack, owner)
        newShearStack
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
