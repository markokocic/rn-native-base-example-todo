(ns rn.native-base-example-todo.todo-screen
  (:require [steroid.rn.core :as rn]
            [re-frame.core :as rf]
            [reagent.core :as r]
            [rn.native-base.core :as nb]
            [rn.native-base.easy-grid :as eg]))

(defn- todo-item [{:keys [key text checked]}]
  [nb/list-item {:key key}
   [nb/check-box {:checked  checked
                  :on-press #(rf/dispatch [:todos/check key])}]
   [nb/body {:style {:flex 1}}
    [nb/text {:style {:font-size 17 :font-weight :bold :text-decoration-line (if checked :line-through :none)}}
     (str text)]]
   [rn/touchable-opacity {:on-press #(rf/dispatch [:todos/delete key])}
    [nb/icon {:name "trash" :style {:font-size 30 :color :red}}]]])

(defn- todos []
  (let [todos (rf/subscribe [:todos/all])]
    [nb/container
     [rn/flat-list {:data @todos :render-fn todo-item}]]))

(defn- input-container []
  (let [value (r/atom "")]
    (fn []
      [nb/list-item {:no-indent true :style {:border-bottom-width 3}}
       [nb/input {:multiline      true
                  :placeholder    "What do you want to do today?" :placeholder-text-color "#abbabb"
                  :value          @value
                  :on-change-text (fn [v] (reset! value v) (r/flush))}]
       [rn/touchable-opacity {:on-press (fn [] (rf/dispatch [:todos/add @value]) (reset! value ""))}
        [nb/icon {:name  :add :type "MaterialIcons"
                  :style {:font-size 30 :color :blue}}]]])))

(defn screen []
  [nb/container
   [nb/header {:transparent true}
    [nb/left]
    [nb/body
     [nb/h1 {:style {:color :red}} "Todo List"]]]
   [nb/view {:style {:flex 1}}
    [input-container]
    [todos]]])
