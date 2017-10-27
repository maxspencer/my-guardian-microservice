package com.gu.maxmicros

import io.circe.Encoder
import io.circe.generic.extras.semiauto._

case class LambdaResponse(statusCode: Int, headers: Map[String, String], body: String)

object LambdaResponse {
  implicit val lambdaResponseEncoder : Encoder[LambdaResponse] = deriveEncoder
}
