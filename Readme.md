This project is a step by step example how to start `react-native` project using Clojurescript, `shadow-cljs` and r`eagent` libraries. It aslo uses `native-base` as react-native component library. 

# Setup Instructions

## Step 0 - Install prerequisites
Have installed `nodejs`, `npm` and `yarn`.

## Step 1 - Create a barebone react-native project
```
npx react-native init RnNativeBaseExampleTodo
```
This will create a sample `react-native` project.

## Step 2 - Create a Clojurescript file with an init method
Create a new file in `src/rn/native_base_example_todo/core.cljs` with the following content
```Clojure
(ns rn.native-base-example-todo.core
  (:require [steroid.rn.core :as rn]))

(defn app-root []
  [rn/text "Hello"])

(defn init []
  (rn/register-reload-comp "RnNativeBaseExampleTodo" app-root)) 
```

## Step 3 - Add shadow-cljs config file
Create file `shadow-cljs.edn` with the following content:
```Clojure
{:deps   true
 :builds {:dev
          {:target     :react-native
           :init-fn    rn.native-base-example-todo.core/init
           :output-dir "app"
           :devtools   {:autoload   true
                        :after-load steroid.rn.core/reload
                        ;:preloads   [re-frisk-rn.preload]
                        }}}}
```
This is the basic shadow-cljs configuration which refers to `dep.edn` file for managing dependencies. It is also possible to configure shadow-cljs as a standalone config, or to use Leiningen `project.clj` instead of `deps.end`. Both aproaches are documented in shadow-cljs documentatino and are left as an exercise for the reader.

Also create a clojure tools configuration `deps.edn`
```Clojure
{:deps  {org.clojure/clojure       {:mvn/version "1.10.1"}
         org.clojure/clojurescript {:mvn/version "1.10.520"}
         thheller/shadow-cljs      {:mvn/version "2.8.96"}
         rn-shadow-steroid         {:mvn/version "0.1.1"}
         reagent                   {:mvn/version "0.10.0" :exclusions [cljsjs/react cljsjs/react-dom]}
         re-frame                  {:mvn/version "0.12.0"}
         ;re-frisk-rn               {:mvn/version "0.1.1"}
         }
 :paths ["src"]}
```
If you get compilation errors later when trying to compile Clojurescript code, please make sure that you have properly configured shadow-cljs, clojure and clojurescript versions, as explained in shadow-cljs home page.

## Step 4 - Configure react native to use Clojurescript
Update the `index.js` file to point to our compilation target. Replace the file content with the following:
```Javascript
import "./app/index.js";
```
This will tell react native launcher to use our code. At that point Javascript source code can be deleted.
```
rm -f App.js
```

## Step 5 - Compiling and starting the application

### Build Clojurescript source
Run the following command in the console:
```
npx shadow-cljs watch dev
```
This can take a while, but needs to be done only once. After that, it will quickly re-compile source on every change.

### Start react native
Run the following command in another terminal window:
```
npx react-native start
```

### Start mobile device
Just execute `npx react-native run-android` or `npx react-native run-ios`

### Enable shadow-cljs reloading
Once the application is started, you should disable react-native reloading feature. Start debug menu and disable the "Fast Refresh" option. Once disabled, shadow-cljs will be responsible for reloading the application code, which is much faster and keeps application state between reloads.

## Step 6 - happy coding
That's it. You can now continue on your own coding react-native application in Clojurescript.

You may also want to take a look at this repo for a samle application that use `native-base` library.

## Step 6 - Add `native-base`
In order to use any react-native library, it needs to be added by your favorite node package manager. Use either npm or yarn.
```
yarn add native-base
```
```
npm install --save native-base
```
It also needs to be linked to the react-native
```
npx react-native link
```
After adding, it's required to restart react-native and rebuild and restart application.


# Credits

Heavy lifting is done using the excelent `rn-shadow-steroid` library. Take a look at it for more documentation.
