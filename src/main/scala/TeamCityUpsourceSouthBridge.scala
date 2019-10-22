import io.scalajs.nodejs.http.Http._
import io.scalajs.nodejs.http.{IncomingMessage, ServerResponse}
import io.scalajs.nodejs.process

import scala.math._
import scala.util.{Failure, Success}

object TeamCityUpsourceSouthBridge {
  def main(): Unit = {
    println("TeamCityUpsourceSouthBridge")

    //      val base = "http://ocmaster.szeged.tigra.hu:8085/aeek-telemed-core-dr/file/ec3b67263dd7d3bb08bc9f520fcd6f1929226dfa/"
    val base = process.env.find { case (key, _) =>
      key == "UPSOURCE_PROJECT_URL"
    }.getOrElse {
      throw new Exception("Please provide the Upsource base url in the UPSOURCE_PROJECT_URL environment variable.")
    }._2 // value

    val port = 63339
    createServer { (req: IncomingMessage, res: ServerResponse) =>
      println(s"Teamcity sent: http://127.0.0.1:$port${req.url}")

      val arr = req.url.split("&")
      val file = arr.filter(_.startsWith("file=")).head.drop("file=".length)
      val line = arr.filter(_.startsWith("line=")).head.drop("line=".length)
      val lineMinus10 = max(0, line.toInt - 10 - 1) // It seems that Upsource  lines start at 0 , TeamCity lines start at 1
      //val url = base + file + "?nav=714:720:focused&line=0&preview=false"
      //val url = base + file + "?nav=1:2&line="+line+"&preview=false"
      val url = base + file + "?line=" + lineMinus10 + "&preview=false"
      println(s"Opening in Upsource: $url")
      println()

      import scala.concurrent.ExecutionContext.Implicits.global

      NodeOpen(url).toFuture.onComplete {
        case Success(response) =>
        //println(s"response: $response")
        //println(s"got response buffer: ${response.status}, length: ${response.headers.get("Content-Length")}")
        case Failure(exception) =>
          println(exception)
      }

      res.end()
    }.listen(port)
    println("Listening on port 63339")
  }
}
