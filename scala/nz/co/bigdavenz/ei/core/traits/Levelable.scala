package nz.co.bigdavenz.ei.core.traits


/**
 * Created by David J. Dudson on 11/01/14.
 *
 * Trait that allows Object to hold Experience values.
 */
trait Levelable {

  private var xp: Float = 0.0f
  val getLevel: Int = calculateLevel()
  private var hasCheated: Boolean = false

  private var expPower: Double = 2
  private var expMultiplier: Double = 10

  def addXp(amount: Int, cheated: Boolean = false) {
    val previousLevel = getLevel
    xp += amount
    hasCheated = cheated
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
