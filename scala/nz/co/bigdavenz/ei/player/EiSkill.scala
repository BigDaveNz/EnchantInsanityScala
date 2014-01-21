package nz.co.bigdavenz.ei.player

import scala.collection.mutable.ListBuffer
import nz.co.bigdavenz.ei.skill.Skill

/**
 * Created by David J. Dudson on 16/01/14.
 *
 * Housing SkillList etc/Initialising Skills
 */
object EiSkill {

  var listOfSkills:ListBuffer[Skill] = new ListBuffer[Skill]

  val toolcare:Skill = new Skill("Toolcare")
  val efficiency:Skill = new Skill("Efficiency")
  val fitness:Skill = new Skill("Fitness")
  val swimming:Skill = new Skill("Swimming")
}
