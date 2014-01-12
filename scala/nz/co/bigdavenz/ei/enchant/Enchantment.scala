package nz.co.bigdavenz.ei.enchant

import net.minecraft.item.Item
import nz.co.bigdavenz.ei.core.traits.{Nameable, Enableable, Levelable}

/**
 * Created by David J. Dudson on 11/01/14.
 *
 * Sets inheriting Object as an enchantment, which can be applied to many things, including items and blocks,
 *
 * Has trait Levelable due to enchantments being able to gain xp for each enchantment
 * Has trait Enableable due to it being able to be disabled in the Config
 * Has trait Nameable due to all Enchantments requiring a name.
 */
trait Enchantment extends Levelable with Enableable with Nameable {

  def validItemForEnchantment(item: Item): Boolean

  def canEnchant(item: Item): Boolean = {
    if (skillLevelsMet && validItemForEnchantment(item)) {
      true
    } else {
      false
    }
  }

  def skillLevelsMet: Boolean
}
