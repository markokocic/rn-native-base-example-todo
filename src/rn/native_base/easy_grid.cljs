(ns rn.native-base.easy-grid
  (:require
    [rn.native-base.util :refer [adapt-class]]
    [react-native-easy-grid :as eg]))

(def grid (adapt-class eg/Grid))
(def col (adapt-class eg/Col))
(def row (adapt-class eg/Row))
