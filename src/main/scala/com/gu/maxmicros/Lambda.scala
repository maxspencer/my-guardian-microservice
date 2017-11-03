package com.gu.maxmicros

import java.io.{DataInputStream, InputStream, OutputStream}

import io.circe.syntax._
import java.nio.charset.StandardCharsets.UTF_8
import Math._

import scala.io.Source
import scala.util.{Failure, Success, Try}

object Lambda {

  def handler(in: InputStream, out: OutputStream) {
    try {
      val input = Source.fromInputStream(in).mkString
      System.out.println(input)

      val response = Try(input.toInt) match {
        case Success(number) => LambdaResponse(200, Map("Content-Type" -> "application/json"), primeStatus(number))
        case Failure(exception) => LambdaResponse(500, Map.empty, exception.toString)
      }

      out.write(response.asJson.noSpaces.getBytes(UTF_8))
    } finally {
      in.close()
      out.close()
    }
  }

  def primeStatus(number: Int): String = if (isPrime(number)) "this is a prime number" else "this is not a prime number"

  def isPrime(number: Int): Boolean = (2 to sqrt(number).toInt).forall(i => number % i != 0)
}