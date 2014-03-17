package nz.co.bigdavenz.ei.file

import nz.co.bigdavenz.ei.tutorial.Tutorial

/**
 * Created by David J. Dudson on 14/03/14.
 *
 * Holds default contents for certain data types
 */
object DefaultContents {

  def getTutorials() {
    new Tutorial() {}
  }


}
