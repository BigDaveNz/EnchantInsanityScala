package nz.co.bigdavenz.ei.item

import net.minecraft.item._
import net.minecraft.entity.player.EntityPlayer
import nz.co.bigdavenz.ei.core.chat.Communicate
import net.minecraft.entity.EntityLivingBase

/**
 * Created by David J Dudson on 21/01/14.
 *
 * Trait for handling tools created via Ei Conversion
 */
trait EiItemTool extends EiItem {

  var consumedTool: ItemTool = null
  var consumedToolStack: ItemStack = null

  override def getHarvestLevel(itemStack: ItemStack, toolClass: String): Int = {
    if (converted) {
      consumedTool.func_150913_i().getHarvestLevel
    } else {
      0
    }
  }

  override def hitEntity(itemStack: ItemStack, attackedEntity: EntityLivingBase, attacker: EntityLivingBase): Boolean = {
    this.consumedTool.hitEntity(itemStack, attackedEntity, attacker)

  }
}
