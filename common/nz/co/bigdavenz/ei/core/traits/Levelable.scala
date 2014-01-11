package nz.co.bigdavenz.ei.core.traits

/**
 * Created by David J. Dudson on 11/01/14.
 */
trait Levelable {

  private var xp:Float = 0.0f
  val getLevel:Int = calculateLevel()
  private var hasCheated:Boolean = false

  private var expPower:Double
  private var expMultiplier:Double

  def addXp(amount: Int, cheated:Boolean = false){
    val previousLevel = getLevel
    xp += amount
    hasCheated = cheated
    val currentLevel = getLevel
    if (previousLevel != currentLevel){
      levelUpdate()
    }
  }

  def levelUpdate(){}

  def getXP(): Int = {
    xp.toInt
  }

  def calculateLevel(): Int = {
    (expMultiplier * Math.exp(expPower)).toInt
  }
}
