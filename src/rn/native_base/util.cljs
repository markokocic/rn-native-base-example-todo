(ns rn.native-base.util
  (:require [reagent.core :as r]))

(defn adapt-class [class]
  (when class
    (r/adapt-react-class class)))
