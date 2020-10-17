(ns constsscraper.core
  (:require [net.cgrand.enlive-html :as html]))

(defn contrib-url [username]
  (str "https://github.com/users/" username "/contributions"))

(def website-content-contibutions
  "Get website content from Hacker News
  Returns: list of html content as hash-maps"

  (html/html-resource (java.net.URL. (contrib-url "ampersanda"))))

(defn day-mapper [{:keys [attrs]}]
  (let [{:keys [fill data-count data-date]} attrs]
    {:color fill
     :count data-count
     :date  data-date}))

;(println (html/select website-content-contibutions [:.calendar-graph :.js-calendar-graph-svg :.day]))
(println
 (let [raw-days (html/select website-content-contibutions [:.calendar-graph :.js-calendar-graph-svg :.day])
       days (map day-mapper raw-days)
       weeks (partition 7 7 nil days)]
   (first weeks)))