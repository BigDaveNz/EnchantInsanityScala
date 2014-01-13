package nz.co.bigdavenz.ei.item

import net.minecraft.item.Item
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.init.Items

/**
 * Created by David J. Dudson on 11/01/14.
 *
 * Item List
 */
object EiItems {

  var enchantedPickaxe: Item = new EnchantedPick(Items.wooden_pickaxe).setUnlocalizedName("Enchanted Pickaxe")

  def init() {
    registerItem(enchantedPickaxe)
  }

  def registerItem(item: Item) {
    GameRegistry.registerItem(item, item.getUnlocalizedName)
  }
}
