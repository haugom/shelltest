package com.example.demo

import java.io.BufferedReader

import java.io.InputStream
import java.io.InputStreamReader
import java.util.function.Consumer


class SteamGobbler: Runnable {
    private var inputStream: InputStream? = null
    private var consumer: Consumer<String>? = null

    constructor(inputStream: InputStream?, consumer: Consumer<String>) {
        this.inputStream = inputStream
        this.consumer = consumer
    }

    @Override
    override fun run() {
        BufferedReader(InputStreamReader(inputStream)).lines()
            .forEach(consumer)
    }
}