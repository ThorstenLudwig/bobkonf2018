(ns bobkonf.basic)


"Hallo Bobkonf"

(+ 21 21)


(clojure.core/empty? [1 2 3])

(list 1 2 3)

(map odd? [1 2 3])

(map (fn [x] (* x x)) (range 10))

(map #(* %1 %3) (range 10))

(reduce + (range 10))

(reduce #(+ %1 1) (range 10))

(def some-map {"java" "doof" "clojure" "toll"})

(get some-map "clojure")

(def some-map {:java "doof" :clojure "toll"})

(get some-map :clojure)

(:clojure some-map)

(def factorial
  (fn [n]
    (if (= n 0)
      1
      (* n
         (factorial (dec n))))))

(factorial 5)

(defn factorial [n]
  (if (= n 0)
    1
    (* n
       (factorial (dec n)))))
(factorial 5)

(defn factorial2
  ([n] (factorial2 n 1))
  ([n acc] (if (= n 0)
             acc
             (factorial2 (dec n)
                         (* n acc)))))

(factorial2 5)











