(ns rn.native-base-example-todo.subs
  (:require [re-frame.core :as rf]
            [steroid.subs :as subs]))
(rf/reg-sub
  :todos/all
  (fn [db _]
    (:todos db)))
