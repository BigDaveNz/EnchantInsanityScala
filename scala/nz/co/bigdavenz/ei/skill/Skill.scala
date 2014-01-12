package nz.co.bigdavenz.ei.skill

import nz.co.bigdavenz.ei.core.traits.{Nameable, Enableable, Levelable}

/**
 * Created by David J. Dudson on 11/01/14.
 */
abstract class Skill extends Levelable with Enableable with Nameable {

  def Skill(nameKey: String) {
    setNameFromLang(nameKey)
  }
}
