package com.gu.maxmicros

import java.io.{InputStream, OutputStream}
import io.circe.syntax._
import java.nio.charset.StandardCharsets.UTF_8

object Lambda {

  def handler(in: InputStream, out: OutputStream) {
    val response = LambdaResponse(200, Map("Content-Type" -> "application/json"), "Max says hello.")
    out.write(response.asJson.noSpaces.getBytes(UTF_8))
  }

}