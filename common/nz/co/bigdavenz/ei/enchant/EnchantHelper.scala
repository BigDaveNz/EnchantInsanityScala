package nz.co.bigdavenz.ei.enchant

import net.minecraft.item.{ItemTool, Item}

/**
 * Created by David J. Dudson on 11/01/14.
 */
object EnchantHelper {

  def isTool(item:Item):Boolean = item.isInstanceOf[ItemTool]
}
