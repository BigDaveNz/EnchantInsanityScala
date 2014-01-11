package nz.co.bigdavenz.ei.item

import nz.co.bigdavenz.ei.client.chat.Communicate

/**
 * Created by David J. Dudson on 11/01/14.
 */
object ItemInitializer {

  def init(){
    Communicate.withClient("Item's Initialized")
  }
}
