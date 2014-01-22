package nz.co.bigdavenz.ei.skill

import nz.co.bigdavenz.ei.core.traits.{Enableable, Levelable}
import nz.co.bigdavenz.ei.player.EiSkill

/**
 * Created by David J. Dudson on 11/01/14.
 *
 * Basic Skill class
 */


class Skill(name: String) extends Levelable with Enableable with Nameable {

  def Skill(nameKey: String, multiplier: Double = 10, power: Double = 2) {
    setNameFromLang(nameKey)
    setExpPower(power)
    setExpMultiplier(multiplier)
    EiSkill.listOfSkills.append(this)
  }
}
