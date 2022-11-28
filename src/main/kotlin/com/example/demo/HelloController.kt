package com.example.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


@RestController
class HelloController {
    @GetMapping("/")
    fun index(): String {

        val isWindows = false
        val builder = ProcessBuilder()
        if (isWindows) {
            builder.command("cmd.exe", "/c", "dir");
        } else {
            builder.command("sh", "-c", "ls; sleep 10");
        }
        builder.directory(File("/bin"))
        val process = builder.start()
        val streamGobbler = SteamGobbler(process.getInputStream(), System.out::println)
        val future = Executors.newSingleThreadExecutor().submit(streamGobbler)
        val exitCode = process.waitFor()
        assert(exitCode == 0)
        future.get(10, TimeUnit.SECONDS)
        return "hello from spring boot"
    }
}