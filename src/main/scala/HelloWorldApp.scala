import io.scalajs.nodejs._
import io.scalajs.nodejs.http.Http._
import io.scalajs.nodejs.http.{ClientRequest, ServerResponse}

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
    createServer { (req: ClientRequest, res: ServerResponse) =>
      res.write("Ola!")
      res.end()
    }.listen(63330)
    println("Listening on port 63330")
  }
}
