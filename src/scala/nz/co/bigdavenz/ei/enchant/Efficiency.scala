package nz.co.bigdavenz.ei.enchant

import net.minecraft.item.{ItemSpade, ItemAxe, ItemPickaxe, Item}

/**
 * Created by David J. Dudson on 11/01/14.
 *
 * Efficeincy Enchantment, Mine blocks faster
 */
class Efficiency extends Enchantment {

  def validItemForEnchantment(item: Item): Boolean = {
    if (item.isInstanceOf[ItemPickaxe] || item.isInstanceOf[ItemAxe] || item.isInstanceOf[ItemSpade]) {
      true
    } else {
      false
    }
  }

  def skillLevelsMet: Boolean = {
    true
    //todo, set this up
  }

  val name: String = "Efficency"
}
