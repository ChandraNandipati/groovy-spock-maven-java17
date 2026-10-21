package com.example.config

class ConfigLoader {

    static Properties getConfig(){

        String env =  System.getProperty("env","dev")

        String fileName = "config/${env}.properties"

        def inputStream = ConfigLoader.class.classLoader
                .getResourceAsStream(fileName)

        if (inputStream == null) {
            throw new RuntimeException(
                    "Configuration file not found: $fileName"
            )
        }


        Properties config = new Properties()

        inputStream.withCloseable {
            config.load(it)
        }

        return config
    }
}
