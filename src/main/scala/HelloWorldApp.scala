import io.scalajs.nodejs._
import io.scalajs.nodejs.http.Http._
import io.scalajs.nodejs.http.{IncomingMessage, ServerResponse}

import scala.util.{Failure, Success}

object HelloWorldApp {
  def main(): Unit = {
    println("Hello world from Scala.js!")

    println("----")
    println("To demonstrate usage of Node.js api, here's some of your environment:")
    process.env.take(5).foreach {
      case (key, value) =>
        println(s"$key = $value")
    }

    println("----")
    //createServer { (req: ClientRequest, res: ServerResponse) =>
    createServer { (req: IncomingMessage, res: ServerResponse) =>
      println(req.url)
      res.write("Ola!")
      get("http://ocmaster.szeged.tigra.hu:8085/aeek-telemed-core-dr/file/ec3b67263dd7d3bb08bc9f520fcd6f1929226dfa/src/main/java/hu/lippert/telemed/data/dto/query/DeviceQuery.java", {
        serverResponse: ServerResponse =>
          println(serverResponse.statusCode)
      })

      val url = "http://ocmaster.szeged.tigra.hu:8085/~download?code=vimBiCmf&state=%2F%7Edownload.inline%2Faeek-telemed-core-dr%3Aec3b67263dd7d3bb08bc9f520fcd6f1929226dfa%3A%2Fsrc%2Fmain%2Fjava%2Fhu%2Flippert%2Ftelemed%2Fdata%2Fservice%2FAppSettingsService.java"

      import scala.concurrent.ExecutionContext.Implicits.global

      NodeFetch(url, null).toFuture.onComplete {
        case Success(response) =>
          println(s"got response buffer: ${response.status}, length: ${response.headers.get("Content-Length")}")
        case Failure(exception) =>
          println(exception)
      }


      NodeOpen(url).toFuture.onComplete {
        case Success(response) =>
          println(s"got response buffer: ${response.status}, length: ${response.headers.get("Content-Length")}")
        case Failure(exception) =>
          println(exception)
      }

      res.end()
    }.listen(63339)
    println("Listening on port 63339")
  }
}
