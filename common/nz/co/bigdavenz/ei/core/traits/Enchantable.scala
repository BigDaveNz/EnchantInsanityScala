package nz.co.bigdavenz.ei.core.traits

/**
 * Created by David J. Dudson on 11/01/14.
 */

import nz.co.bigdavenz.ei.enchant.Enchantment

trait Enchantable extends Nameable{

  var enchantmentList: List[Enchantment]
}
