package nz.co.bigdavenz.ei.item

import net.minecraft.item.Item
import cpw.mods.fml.common.registry.GameRegistry

/**
 * Created by David J. Dudson on 11/01/14.
 *
 * Item List
 */
object EiItems {

  var eiTool = new EiItemTool

  def init() {
    registerItem(eiTool)
  }

  def registerItem(item: Item) {
    GameRegistry.registerItem(item, item.getUnlocalizedName)
  }
}
