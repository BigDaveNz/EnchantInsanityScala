package nz.co.bigdavenz.ei.item

import net.minecraft.item._
import nz.co.bigdavenz.ei.core.chat.Communicate
import nz.co.bigdavenz.ei.core.traits.Enchantable
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.client.renderer.texture.IIconRegister
import nz.co.bigdavenz.ei.lib.Reference
import net.minecraft.init.Items

/**
 * Created by David J. Dudson on 15/01/14.'
 *
 * Work Around for EI Items that do not have a specific tool material
 */

class EiItemTool extends EiItem with Enchantable {

  var consumedTool: ItemTool = null
  var consumedToolStack: ItemStack = null
  var initialised = false

  def EiItemTool() {
    this.setUnlocalizedName("EIToolUnregistered")
  }

  def createEiTool(consumedTool: ItemTool, owner: EntityPlayer) {
    this.initialised = true
    this.consumedTool = consumedTool
    this.consumedToolStack = new ItemStack(consumedTool)
    this.setOwner(owner)
    this.maxEnchantments = consumedTool.func_150913_i().getHarvestLevel
    this.setMaxDamage(consumedTool.getMaxDamage)
    this.setDamage(new ItemStack(this), consumedToolStack.getItemDamage)
    this.setUnlocalizedName("Enchanted " + consumedTool.getUnlocalizedName)
  }

  var getToolType: String = {
    val pick = Items.diamond_pickaxe.toString
    val shovel = Items.diamond_shovel.toString
    val axe = Items.diamond_axe.toString
    consumedTool.getClass.toString match {

      case pick => "EnchantedPickaxe"
      case shovel => "EnchantedSpade"
      case axe => "EnchantedAxe"
      case _ =>
        Communicate.withConsole("Unnacounted for Tool Class:" + consumedTool.getClass)
        null
    }
  }

  def getHarvestLevel {
    if (initialised) {
      consumedTool.func_150913_i().getHarvestLevel
    }
  }

  override def registerIcons(iconRegister: IIconRegister) {
    if (this.initialised.equals(false)) {
      iconRegister.registerIcon(Reference.modId + ":enchantedpickaxe")
    } else {
      if (getToolType.equalsIgnoreCase("EnchantedPickaxe"))
        iconRegister.registerIcon(Reference.modId + ":" + getToolType)
    }
  }
}
