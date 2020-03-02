/*
 * Copyright 2018-2020 ProfunKtor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.profunktor.redis4cats.codecs.laws

import cats.laws._
import dev.profunktor.redis4cats.codecs.splits.SplitMono

final case class SplitMonoLaws[A, B](
    mono: SplitMono[A, B]
) {

  def identity(a: A): IsEq[A] =
    (mono.reverseGet compose mono.get)(a) <-> a

  def idempotence(b: B): IsEq[B] = {
    val f = mono.get compose mono.reverseGet
    f(b) <-> f(f(b))
  }

}
