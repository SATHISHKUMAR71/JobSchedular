package com.example.jobservice

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        scheduleJob(this)
    }
    private fun scheduleJob(context: Context){
        val componentName = ComponentName(context,MyJobService::class.java)
        val jobInfo = JobInfo.Builder(1,componentName)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
            .setPeriodic(15*1000*60)
            .setPersisted(true)
            .build()
        val jobScheduler = context.getSystemService(JobScheduler::class.java)
        val resultCode = jobScheduler.schedule(jobInfo)
        println(resultCode)
    }

    private fun cancelJob(context: Context, jobId:Int){
        context.getSystemService(JobScheduler::class.java).cancel(jobId)
        println("Job Cancelled Successfully")
    }
}