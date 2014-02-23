package nz.co.bigdavenz.ei.enchant

import net.minecraft.item.Item
import nz.co.bigdavenz.ei.core.traits.Enableable

/**
 * Created by David J. Dudson on 11/01/14.
 *
 * Sets inheriting Object as an enchantment, which can be applied to many things, including items and blocks,
 *
 * Has trait Enableable due to it being able to be disabled in the Config
 */
trait Enchantment extends Enableable {

  def validItemForEnchantment(item: Item): Boolean

  val name: String

  def getName: String = {
    name
  }
}
