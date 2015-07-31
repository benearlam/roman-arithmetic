(ns roman-arithmetic.core
  (:require [clojure.string :as string]))

(def subtractive-forms
  {:uncompact (array-map
                "iv" "iiii"
                "ix" "viiii")
   :compact   (array-map
                "viiiii" "x"
                "iiiii" "v")})

(def numeral-order
  {"m" 1
   "d" 2
   "c" 3
   "l" 4
   "x" 5
   "v" 6
   "i" 7})

(defn- compare-numeral
  [left right]
  (let [get-numeral-order #(get numeral-order (str %1))]
    (<
      (get-numeral-order left)
      (get-numeral-order right))))

(defn- apply-subtractive-forms
  [numeral type]
  (reduce
    (fn [s [k v]]
      (string/replace s k v))
    numeral
    (type subtractive-forms)))

(defn- uncompact
  [numeral]
  (apply-subtractive-forms numeral :uncompact))

(defn- compact
  [numeral]
  (apply-subtractive-forms numeral :compact))

(defn- sort-left-to-right
  [numeral]
  (->> numeral
       (sort (comp compare-numeral))
       (apply str)))

(defn add
  [left right]
  (->
    (str (uncompact left) (uncompact right))
    sort-left-to-right
    compact))
