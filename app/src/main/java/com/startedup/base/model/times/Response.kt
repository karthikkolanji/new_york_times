package com.startedup.base.model.times

class Response {
    var copyright: String? = null
    var lastUpdated: String? = null
    var section: String? = null
    var results: List<ResultsItem>? = null
    var numResults: Int = 0
    var status: String? = null

    override fun toString(): String {
        return "Response{" +
                "copyright = '" + copyright + '\''.toString() +
                ",last_updated = '" + lastUpdated + '\''.toString() +
                ",section = '" + section + '\''.toString() +
                ",results = '" + results + '\''.toString() +
                ",num_results = '" + numResults + '\''.toString() +
                ",status = '" + status + '\''.toString() +
                "}"
    }
}