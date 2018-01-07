package com.goeuro.routeslurper

import groovy.json.JsonSlurper

/**
 * Created by enricomariotti on 3/14/17.
 */
class RouteSlurper {

    static def stations(def name) {
        def jsonSlurper = new JsonSlurper()
        jsonSlurper.parseText(new File(name).text)
    }

    static def stations = stations('citybus-routes.json')

    static def sql(entry) {
        println("-- ${entry.name}")
        println("SET @code_id = (SELECT busstation_code_id from busstation_code where service_fk = 151 AND stn_code = '19');")
        println("UPDATE busstation_code SET stn_code = \"${entry.code}\"  WHERE busstation_code_id = @code_id;")
        return
    }

    static void main(String[] args) {
        def intStations = stations.get("stations")
        intStations.each{entry ->
                sql(entry)
        }
    }

}
