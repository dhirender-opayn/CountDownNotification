package com.example.countdownnotification

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentProvider
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.Toast
import androidx.annotation.ContentView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // declaring variables
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"
   lateinit var  adapterView :ListAdapter
   val listofarray = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        btn.setOnClickListener{
            listofarray.add(edttext.text.toString())
        }
        setAdapter()

    }
    private fun setAdapter(){

        adapterView = ListAdapter(listofarray,this){
            notificationAlert(listofarray[it])
            Toast.makeText(this,listofarray[it].toString(), Toast.LENGTH_LONG).show()
        }
        rvlists.adapter = adapterView

    }


    private fun notificationAlert( taskname:String){
        val intent = Intent(this, afterNotification::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId, description, NotificationManager .IMPORTANCE_HIGH)
            notificationChannel.lightColor = Color.BLUE
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)
            builder = Notification.Builder(this, channelId).setContentTitle(taskname +
                    " is Finished").setContentText("Task Completed Notification").setSmallIcon(R.drawable.ic_launcher_background).setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable
                .ic_launcher_background)).setContentIntent(pendingIntent)
        }
        notificationManager.notify(12345, builder.build())

    }
}