import io.scalajs.nodejs.http.Http._
import io.scalajs.nodejs.http.{IncomingMessage, ServerResponse}
import io.scalajs.nodejs.process

import scala.util.{Failure, Success}

object TeamCityUpsourceSouthBridge {
  def main(): Unit = {
    println("TeamCityUpsourceSouthBridge")

    //      val base = "http://ocmaster.szeged.tigra.hu:8085/aeek-telemed-core-dr/file/ec3b67263dd7d3bb08bc9f520fcd6f1929226dfa/"
    val base = process.env.find { case (key, _) =>
      key == "UPSOURCE_URL"
    }.getOrElse {
      throw new Exception("Please provide the Upsource base url in the UPSOURCE_URL environment variable.")
    }._2 // value

    createServer { (req: IncomingMessage, res: ServerResponse) =>
      println(req.url)

      // http://ocmaster.szeged.tigra.hu:8085/aeek-telemed-core-dr/file/ec3b67263dd7d3bb08bc9f520fcd6f1929226dfa/src/main/java/hu/lippert/telemed/data/Application.java?nav=1014:1020:focused&line=0&preview=false
      val file = req.url.split("&").filter(_.startsWith("file=")).head.drop("file=".size)
      val url = base + file + "?nav=714:720:focused&line=0&preview=false"

      import scala.concurrent.ExecutionContext.Implicits.global

      NodeOpen(url).toFuture.onComplete {
        case Success(response) =>
          println(s"response: $response")
        //println(s"got response buffer: ${response.status}, length: ${response.headers.get("Content-Length")}")
        case Failure(exception) =>
          println(exception)
      }

      res.end()
    }.listen(63339)
    println("Listening on port 63339")
  }
}
