package com.socrata.decima.actors

import akka.actor.{Actor, ActorRef, Terminated}

object Reaper {
  case class WatchMe(ref: ActorRef)
}

class Reaper extends Actor {
  import Reaper._

  final def receive = {
    case WatchMe(ref) =>
      context.watch(ref)
    case Terminated(ref) =>
      // A bit naive, essentially relying on a supervisor
      // to restart the poller in the event of any failures
      context.system.shutdown()
  }
}
