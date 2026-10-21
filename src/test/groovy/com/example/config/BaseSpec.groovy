package com.example.config

import spock.lang.Shared
import spock.lang.Specification

abstract class BaseSpec extends Specification {

    @Shared
    Properties config = ConfigLoader.getConfig()

}
