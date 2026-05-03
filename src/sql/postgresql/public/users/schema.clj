(ns sql.postgresql.public.users.schema
  (:require [bisql.schema :as bisql.schema]))

#_{:clojure-lsp/ignore [:clojure-lsp/unused-public-var]}
(def input
  [:map
   {:closed true}
   [:id {:optional true} [:or int? bisql.schema/malli-default-sentinel]]
   [:email string?]
   [:status string?]
   [:created-at [:or [:fn bisql.schema/offset-date-time?] bisql.schema/malli-default-sentinel]]])

#_{:clojure-lsp/ignore [:clojure-lsp/unused-public-var]}
(def input-all-required
  (bisql.schema/malli-map-all-entries-required input))

#_{:clojure-lsp/ignore [:clojure-lsp/unused-public-var]}
(def input-all-optional
  (bisql.schema/malli-map-all-entries-optional input))

#_{:clojure-lsp/ignore [:clojure-lsp/unused-public-var]}
(def output
  (bisql.schema/malli-map-all-entries-required
   (bisql.schema/malli-map-all-entries-strip-default-sentinel input)))
