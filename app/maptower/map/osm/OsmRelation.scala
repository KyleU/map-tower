package maptower.map.osm

import com.mongodb.casbah.commons.{ MongoDBObject => Obj, MongoDBList => ObjList }
import com.mongodb.{ BasicDBList, BasicDBObject }
import com.mongodb.casbah.Imports._

object OsmRelationHelper {
  def apply(obj: Obj) = {
    val memberObjs: ObjList = obj.as[MongoDBList]("members")
    val members = memberObjs map { o =>
      val obj: Obj = o.asInstanceOf[BasicDBObject]
      new OsmRelationMember(obj.as[String]("type"), obj.as[Int]("ref"), obj.as[String]("role"))
    }
    new OsmRelation(obj.as[Int]("osmId"), members toSeq, OsmTags(obj))
  }
}

case class OsmRelationMember(relationType: String, ref: Int, role: String)

case class OsmRelation(osmId: Int, members: Seq[OsmRelationMember], tags: Map[String, String] = Map.empty)
