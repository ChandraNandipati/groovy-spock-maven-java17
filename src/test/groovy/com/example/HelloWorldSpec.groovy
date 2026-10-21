package com.example

import spock.lang.Specification
import spock.lang.Unroll

class HelloWorldSpec extends Specification {

    @Unroll
    def "Addition of #a and #b should be #expected"(){

        expect:
        a + b == expected


        where:
        a  | b  || expected
        10 | 20 || 30
        5  | 15 || 20
        20 | 30 || 50
        1  | 2  || 3


    }


}
