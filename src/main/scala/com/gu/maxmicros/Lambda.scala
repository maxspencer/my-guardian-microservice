package com.gu.maxmicros

import java.io.{DataInputStream, InputStream, OutputStream}

import io.circe.syntax._
import java.nio.charset.StandardCharsets.UTF_8
import Math._

object Lambda {

  def handler(in: InputStream, out: OutputStream) {
    val dis = new DataInputStream(in)
    val number = dis.readInt()
    val numberStatus = if (isPrime(number)) "this is a prime number" else "this is not a prime number"

    val response = LambdaResponse(200, Map("Content-Type" -> "application/json"), numberStatus)
    out.write(response.asJson.noSpaces.getBytes(UTF_8))
  }

  def isPrime(number: Int): Boolean = (2 to sqrt(number).toInt).forall(i => number % i != 0)
}