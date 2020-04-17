(ns rn.native-base-example-todo.core
  (:require [steroid.rn.core :as rn]))

(defn app-root []
  [rn/text "Hello"])

(defn init []
  (rn/register-reload-comp "RnNativeBaseExampleTodo" app-root)) 