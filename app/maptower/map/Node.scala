package maptower.map

import com.mongodb.casbah.commons.{ MongoDBObject => Obj, MongoDBList => ObjList }
import com.mongodb.{ BasicDBList, BasicDBObject }
import com.mongodb.casbah.Imports._

object Node {
  val empty = new Node(0, "The Middle of Nowhere", "unknown:unknown", new Point(0, 0))

  def apply(obj: Obj): Node = {
    var loc = obj.as[BasicDBList]("loc")
    new Node(obj.as[Int]("osmId"), obj.as[String]("name"), obj.as[String]("typ"), Point(loc), Tags.load(obj))
  }
}

case class Node(osmId: Int, name: String, typ: String, loc: Point, tags: Map[String, String] = Map()) extends Tags {
  def toObj = Obj("osmId" -> osmId, "name" -> name, "typ" -> typ, "loc" -> loc.toObj, "tags" -> tagsObjList)
}