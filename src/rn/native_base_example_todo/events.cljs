(ns rn.native-base-example-todo.events
  (:require [re-frame.core :as rf]
            [steroid.fx :as fx]))

(rf/reg-event-db
  :init-app-db
  (fn [_ _]
    {:todos
     (list
       {:text "Sample checked" :key 1 :checked true}
       {:text "Sample unchecked" :key 2 :checked false}
       {:text "Sample long text that will overflow at one point and be long more that on line" :key 3 :checked false})
     }))

(rf/reg-event-db
  :todos/add
  (fn [db [_ val]]
    (update-in db [:todos] #(conj (:todos db) {:text val :key (js/Date.now) :checked false}))))

(rf/reg-event-db
  :todos/check
  (fn [db [_ val]]
    (update-in db [:todos]
               #(map (fn [item]
                       (if (= val (:key item))
                         (update-in item [:checked] not)
                         item))
                     (db :todos)))
    ))

(rf/reg-event-db
  :todos/delete
  (fn [db [_ val]]
    (update-in db [:todos] (fn [] (remove #(= (:key %) val) (:todos db))))))
