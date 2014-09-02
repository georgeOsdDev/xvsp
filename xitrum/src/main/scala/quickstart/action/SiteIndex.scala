package quickstart.action

import xitrum.annotation.GET

@GET("")
class SiteIndex extends DefaultLayout {
  def execute() {
    at("msg") = "Hello World"
    at("title") = "Welcome to Xitrum"
    respondView()
  }
}
