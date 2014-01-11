package nz.co.bigdavenz.ei.item

import net.minecraft.item.ItemPickaxe
import nz.co.bigdavenz.ei.core.traits.Enchantable
import net.minecraft.entity.player.EntityPlayer
import nz.co.bigdavenz.ei.enchant.Enchantment

/**
 * Created by David J. Dudson on 11/01/14.
 */
class EnchantedPick extends Item with Enchantable {

 def EnchantedPick(pick:ItemPickaxe, owner:EntityPlayer){


 }


  var enchantmentList: Enchantment = null
}
