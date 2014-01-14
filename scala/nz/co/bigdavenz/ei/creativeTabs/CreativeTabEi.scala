package nz.co.bigdavenz.ei.creativeTabs

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import nz.co.bigdavenz.ei.item.EiItems

/**
 * Created by David J. Dudson on 13/01/14.
 *
 * Creative tab used for Enchant Insanity
 */
class CreativeTabEi(label: String) extends CreativeTabs(label) {
  def getTabIconItem: Item = EiItems.eiTool

}
