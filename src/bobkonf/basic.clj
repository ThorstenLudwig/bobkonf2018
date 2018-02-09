(ns bobkonf.basic)


"Hallo Bobkonf"

(+ 2 1)

(clojure.core/empty? [])

[]

(list )

[1 2 3]

(list 1 2 3)

(map odd? [1 2 3])

(map (fn [x] (* x x)) (range 10))

(map #(* % %) (range 10))

(reduce + (range 10))

(reduce #(+ %1 %2) (range 10))

(def some-map {"java" "doof", "clojure" "toll"})

(get some-map "java")

(get some-map "clojure")

(def key-map {:java "doof" :clojure "toll"})

(get key-map :java)

(:java key-map)

(def factorial
  (fn [n]
    (if (= n 0)
      1
      (* n
         (factorial (dec n))))))

(factorial 5)

(defn factorial2 [n]
  (if (= n 0)
    1
    (* n
       (factorial2 (dec n)))))

(factorial2 5)


(defn factorial3
  ([n] (factorial3 n 1))
  ([n acc]
   (if (= n 0)
     acc
     (factorial3 (dec n)
                 (* acc n)))))

(factorial3 5)


