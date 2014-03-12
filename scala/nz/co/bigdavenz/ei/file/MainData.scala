package nz.co.bigdavenz.ei.file

import java.io._
import nz.co.bigdavenz.ei.file.data.DataPackage

/**
 * Created by David J. Dudson on 10/02/14.
 *
 * All data is in this file
 */

object MainData {

  def save {
    val in: ObjectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream("MainData.ei")))
    PlayerData.playerData = in.readObject match {
      case dat: DataPackage => dat
      case _ => throw new Exception("Data read was not of type DataPackage")
    }
    in.close()
  }


  def load {
    val out: ObjectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("MainData.ei")))
    out.writeObject(PlayerData.playerData)
    out.close()
  }
}
