package nz.co.bigdavenz.ei.enchant

import net.minecraft.item.Item
import nz.co.bigdavenz.ei.skill.Skill

/**
 * Created by David J. Dudson on 11/01/14.
 */
class Efficiency extends Enchantment{
  private var expPower: Double = _
  private var expMultiplier: Double = _
  private var enabled: Boolean = _
  val name: String = _
  val mapOfRequiredSkillLevels: Map[Skill, Int] = _

  var listOfEnchantableItems: List[Item] = _
}
