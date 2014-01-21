package nz.co.bigdavenz.ei.core.traits


/**
 * Created by David J. Dudson on 11/01/14.
 *
 * Trait that allows Object to hold Experience values.
 */


trait Levelable extends Ownable{

  private var xp: Float = 0.0f
  val getLevel: Int = calculateLevel()

  private var expPower: Double = 1.0
  private var expMultiplier: Double = 1.0

  def addXp(amount: Int, xpType:String) {
    val previousLevel = getLevel
    xp += amount
    val currentLevel = getLevel
    if (previousLevel != currentLevel) {
      onLevelUpdate()
    }
  }

  def setExpPower(power: Double) {
    expPower = power
  }

  def setExpMultiplier(multiplier: Double) {
    expMultiplier = multiplier
  }

  def onLevelUpdate() {}

  def getXP: Int = {
    xp.toInt
  }

  def calculateLevel(): Int = {
    (expMultiplier * Math.exp(expPower)).toInt
  }
}
