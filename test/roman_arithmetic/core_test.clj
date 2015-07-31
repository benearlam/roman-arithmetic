(ns roman-arithmetic.core-test
  (:require [clojure.test :refer :all]
            [roman-arithmetic.core :refer :all]
            [midje.sweet :refer :all]))

(facts "`add`"
       (fact "simple concat"
         (add "i" "i") => "ii")

       (fact "first subtractive"
             (add "i" "iv") => "v")

       (fact "next subtractive"
             (add "i" "ix") => "x")

       (fact "x is greater i and therefore sits to the left of it"
             (add "i" "xi") => "xii")

       (fact "c is greater v and therefore sits to the left of it"
             (add "v" "c") => "cv")

       (fact "m is greater v and therefore sits to the left of it"
             (add "v" "m") => "mv"))
