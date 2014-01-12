package nz.co.bigdavenz.ei.core.registry

import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.item.Item
import net.minecraft.block.Block
import nz.co.bigdavenz.ei.core.traits.Nameable

/**
 * Created by David J. Dudson on 12/01/14.
 *
 * Allows Various Things to be registered
 */
trait Register extends Nameable {

  def registerItem(item: Item) {
    GameRegistry.registerItem(item, getName)

  }

  def registerBlock(block: Block) {
    GameRegistry.registerBlock(block, getName)
  }

  def name() {

  }
}