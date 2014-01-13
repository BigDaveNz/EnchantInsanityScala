package nz.co.bigdavenz.ei.item

import net.minecraft.item.Item
import cpw.mods.fml.common.registry.GameRegistry

/**
 * Created by David J. Dudson on 11/01/14.
 *
 * Item List
 */
object EiItems {

  def registerItems() {
    GameRegistry.registerItem(enchantedPickaxe, "Enchanted Pickaxe")
  }

  final val enchantedPickaxe: Item = Item.field_150901_e.getObject("Enchanted Pickaxe").asInstanceOf[Item]
}
