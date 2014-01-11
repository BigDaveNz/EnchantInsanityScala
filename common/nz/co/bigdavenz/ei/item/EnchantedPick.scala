package nz.co.bigdavenz.ei.item

import net.minecraft.item.ItemPickaxe
import nz.co.bigdavenz.ei.core.traits.Enchantable
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item.ToolMaterial
import nz.co.bigdavenz.ei.lib.Reference

/**
 * Created by David J. Dudson on 11/01/14.
 */
class EnchantedPick extends ItemPickaxe(ToolMaterial.WOOD) with Enchantable {

  def EnchantedPick(pick: ItemPickaxe, owner: EntityPlayer) {
    this.setName("Enchanted " + pick.getUnlocalizedName)
    this.toolMaterial = pick.toolMaterial
    this.setTextureName(Reference.modId + ":EnchantedPickaxe.png")
    this.setOwner(owner)
  }
}
