package com.sky.whitebear.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.media.MediaBrowserServiceCompat
import androidx.media.app.NotificationCompat.MediaStyle
import androidx.media.session.MediaButtonReceiver
import com.sky.whitebear.R

/**
 *
 * @author: charlotte
 * @date: 2023/2/28
 */
class MusicService : MediaBrowserServiceCompat() {

    private lateinit var mediaSession: MediaSessionCompat
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var notificationManager: NotificationManagerCompat

    override fun onCreate() {
        super.onCreate()
        Log.e("MusicService","MusicService 启动")



        // 初始化媒体会话和媒体播放器
        mediaSession = MediaSessionCompat(this, "MusicService")
        mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource("http://music.163.com/song/media/outer/url?id=25906124.mp3")

        // 设置媒体会话的回调
        mediaSession.setCallback(MediaSessionCallback())

        // 初始化通知管理器
        notificationManager = NotificationManagerCompat.from(this)

        // 启动媒体会话
        sessionToken = mediaSession.sessionToken

        mediaSession.isActive = true

        mediaPlayer.setOnPreparedListener {
            // 当音频准备好时，开始播放音乐
            mediaPlayer.start()
        }

        mediaPlayer.prepareAsync()
    }

    override fun onDestroy() {
        super.onDestroy()

        // 释放媒体播放器
        mediaPlayer.release()

        // 停止媒体会话
        mediaSession.release()
    }

    override fun onGetRoot(
        clientPackageName: String,
        clientUid: Int,
        rootHints: Bundle?
    ): BrowserRoot? {
        // 返回媒体浏览器的根节点
        return BrowserRoot("root", null)
    }

    override fun onLoadChildren(
        parentId: String,
        result: Result<MutableList<MediaBrowserCompat.MediaItem>>
    ) {
        // 返回媒体浏览器的子节点
        result.sendResult(mutableListOf())
    }

    inner class MediaSessionCallback : MediaSessionCompat.Callback() {

        override fun onPlay() {
            mediaPlayer.start()
            showNotification()
        }

        override fun onPause() {
            mediaPlayer.pause()
            showNotification()
        }

        override fun onStop() {
            mediaPlayer.stop()
            showNotification()
            stopSelf()
        }

    }

    private fun showNotification() {
        // 创建一个通知渠道
        val channelId = "my_channel"
        val channelName = "My Channel"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val notificationChannel = NotificationChannel(channelId, channelName, importance)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)

        val builder = NotificationCompat.Builder(this, channelId)
            .setStyle(
                MediaStyle()
                    .setMediaSession(mediaSession.sessionToken)
                    .setShowActionsInCompactView(0, 1, 2)
                    .setShowCancelButton(true)
                    .setCancelButtonIntent(
                        MediaButtonReceiver.buildMediaButtonPendingIntent(
                            this,
                            PlaybackStateCompat.ACTION_STOP
                        )
                    )
            )
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("音乐播放器")
            .setContentText("正在播放")

        builder.addAction(
            NotificationCompat.Action(
                R.drawable.previous,
                "上一首",
                MediaButtonReceiver.buildMediaButtonPendingIntent(
                    this,
                    PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS
                )
            )
        )

        builder.addAction(
            NotificationCompat.Action(
                R.drawable.pause,
                "暂停",
                MediaButtonReceiver.buildMediaButtonPendingIntent(
                    this,
                    PlaybackStateCompat.ACTION_PAUSE
                )
            )
        )

        builder.addAction(
            NotificationCompat.Action(
                R.drawable.next,
                "下一首",
                MediaButtonReceiver.buildMediaButtonPendingIntent(
                    this,
                    PlaybackStateCompat.ACTION_SKIP_TO_NEXT
                )
            )
        )

        var notification = builder.build()
        startForeground(1, notification)

    }
}