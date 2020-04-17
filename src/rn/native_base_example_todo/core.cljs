(ns rn.native-base-example-todo.core
  (:require [steroid.rn.core :as rn]
            [re-frame.core :as rf]
            [rn.native-base-example-todo.todo-screen :as todo]))

(defn app-root []
  [todo/screen])

(defn init []
  (rf/dispatch-sync [:init-app-db])
  (rn/register-reload-comp "RnNativeBaseExampleTodo" app-root)) 