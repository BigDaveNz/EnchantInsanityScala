package nz.co.bigdavenz.ei.item

import nz.co.bigdavenz.ei.EnchantInsanity
import net.minecraft.item.Item

/**
 * Created by David J. Dudson on 15/01/14.]
 *
 * The base for all Items in Enchant Insanity
 */
class EiItem extends Item {

  def EiItem {
    this.setCreativeTab(EnchantInsanity.tabEi)
  }
}
