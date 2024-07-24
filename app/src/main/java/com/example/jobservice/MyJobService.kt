package com.example.jobservice

import android.app.job.JobParameters
import android.app.job.JobService
import java.util.concurrent.BlockingDeque
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import kotlin.math.truncate

class MyJobService:JobService() {
    override fun onStartJob(p0: JobParameters?): Boolean {

        val threadPool = ThreadPoolExecutor(
            3,
            5,
            200,
            TimeUnit.MILLISECONDS,
            LinkedBlockingQueue()
        )

        threadPool.execute{
            for(i in 1..20000){
                println("Task1 1 value: $i")
            }
        }
        threadPool.execute{
            for(i in 1..20000){
                println("Task2 1 value: $i")
            }
        }
        threadPool.execute{
            for(i in 1..20000){
                println("Task3 1 value: $i")
            }
            jobFinished(p0, true)
        }
        return true
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        return true
    }
}