import org.scalajs.dom.experimental.Response

import scala.scalajs.js
import scala.scalajs.js.Promise
import scala.scalajs.js.annotation.JSImport

// https://www.npmjs.com/package/open

// https://stackoverflow.com/questions/40818082/how-to-use-node-modules-in-scala-js

@js.native
@JSImport("open", JSImport.Namespace)
object NodeOpen extends js.Function1[String, js.Promise[Response]] {
  def apply(arg1: String): Promise[Response] = js.native
}
