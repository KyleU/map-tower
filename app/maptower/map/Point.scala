package maptower.map

import com.mongodb.BasicDBList
import com.mongodb.casbah.commons.MongoDBList

object PointHelper {
  def apply(objList: BasicDBList): Point = {
    new Point(objList.get(0).asInstanceOf[Double], objList.get(1).asInstanceOf[Double])
  }
}

case class Point(x: Double, y: Double) {
  def lng = x
  def lat = y
  def toObj = MongoDBList(x, y)

  def >(other: Point) = x > other.x && y > other.y
  def >=(other: Point) = x >= other.x && y >= other.y
  def <(other: Point) = x < other.x && y < other.y
  def <=(other: Point) = x <= other.x && y <= other.y

  override def toString = "[%f, %f]" format (x, y)
}