package nz.co.bigdavenz.ei.enchant

import net.minecraft.item.Item
import nz.co.bigdavenz.ei.core.traits.{Nameable, Enableable, Levelable}
import nz.co.bigdavenz.ei.skill.Skill
import net.minecraft.entity.player.EntityPlayer

/**
 * Created by David J. Dudson on 11/01/14.
 */
trait Enchantment extends Levelable with Enableable with Nameable {

  val mapOfRequiredSkillLevels: Map[Skill, Int]

  var listOfEnchantableItems: List[Item]

  def canEnchant(item: Item, player:EntityPlayer) {
    if (listOfEnchantableItems.contains(item) && levelsMet()) {

    }
  }

  def levelsMet(): Boolean = {
     mapOfRequiredSkillLevels.foreach{

       case (k,v) =>
           v
     }
  }


}
