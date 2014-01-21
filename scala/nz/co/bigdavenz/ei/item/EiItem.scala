package nz.co.bigdavenz.ei.item

import net.minecraft.item._
import nz.co.bigdavenz.ei.core.traits.Enchantable
import net.minecraft.client.renderer.texture.IIconRegister
import nz.co.bigdavenz.ei.lib.Reference
import net.minecraft.entity.player.EntityPlayer
import nz.co.bigdavenz.ei.core.chat.Communicate

/**
 * Created by David J. Dudson on 15/01/14.'
 *
 * Work Around for EI Items that do not have a specific tool material
 */

trait EiItem extends Item with Enchantable {


  var converted = false
  var droppable = true
  val consumedItem: Item = null
  val consumedItemStack: ItemStack = null
  val itemType: String

  def onCreate

  def setDroppable(drop: Boolean) {
    this.droppable = drop
  }

  override def registerIcons(iconRegister: IIconRegister) {
    iconRegister.registerIcon(Reference.modId + ":" + itemType)
  }

  override def onDroppedByPlayer(item: ItemStack, player: EntityPlayer): Boolean = {
    Communicate.withPlayer(getOwner, "You dropped qnd enchanted item?? Really?? Clumsy much!!")
    this.droppable
  }
}
