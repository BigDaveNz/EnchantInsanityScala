package nz.co.bigdavenz.ei.core.traits

/**
 * Created by David J. Dudson on 11/01/14.
 *
 * Allows item /block to be enchatable
 */

import nz.co.bigdavenz.ei.enchant.Enchantment
import scala.collection.mutable.Map
import nz.co.bigdavenz.ei.core.chat.Communicate

trait Enchantable extends Ownable with Nameable {

  private val enchantmentMap: Map[String, Enchantment] = Map.empty[String, Enchantment]
  var maxEnchantments = 1

  def addToMaxEnchants() {
    if (maxEnchantments <= 10) {
      maxEnchantments += 1
    } else {
      Communicate.withConsole("Attempted to set Max Enchantments to more then 10")
    }
  }

  def setMaxEnchantments(max: Int): Boolean = {
    if (max <= 10) {
      maxEnchantments = max
      true
    } else {
      false
    }
  }

  def addEnchantmentToList(enchantment: Enchantment) {
    if (enchantmentMap.size < maxEnchantments) {
      enchantmentMap.put(enchantment.getName, enchantment)
    } else {
      Communicate.withConsole("Attempted to add an enchanment, when only " + maxEnchantments + "was allowed")
    }
  }

  def removeEnchantment(enchantment: Enchantment) {
    enchantmentMap.remove(enchantment.getName)
  }
}
