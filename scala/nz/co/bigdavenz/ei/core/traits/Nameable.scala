package nz.co.bigdavenz.ei.core.traits

import cpw.mods.fml.common.registry.LanguageRegistry

/**
 * Created by David J. Dudson on 11/01/14.
 *
 * Allows inheritors to be names and requires them to have a localised name
 */
trait Nameable {

  private var name: String = "Default Name"

  def setNameFromLang(key: String) {
    name = LanguageRegistry.instance().getStringLocalization(key: String)
  }

  val getName = name
}
