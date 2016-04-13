package com.seastar.ad;

import android.app.Activity;
import android.util.Log;

import com.vungle.publisher.EventListener;
import com.vungle.publisher.VunglePub;

public class VungleAd {

	private static Activity activity;

	public static void onCreate(Activity activity, String vungleAppId) {
		VungleAd.activity = activity;
		VunglePub.getInstance().init(activity, vungleAppId);

		VunglePub.getInstance().setEventListeners(new EventListener() {

			@Override
			public void onVideoView(boolean isCompletedView, int watchedMillis,
					int videoDurationMillis) {
				// TODO Auto-generated method stub
				// 每当有广告完成时调用。如果至少>80%的视频都已观看，则isCompletedView为true，这也算作是一次完整观看。
				// watchedMillis指最长的视频观看时间(如果用户回放了该视频)。
				Log.d(Constant.TAG, "VungleAd::onCreate" + " onVideoView, isCompletedView:" + isCompletedView + " watchedMillis:" + watchedMillis + " videoDurationMillis:" + videoDurationMillis);
			}

			@Override
			public void onAdUnavailable(String arg0) {
				// TODO Auto-generated method stub
				// 当VunglePub.playAd()被调用时调用，但是没有可用于播放的广告
				Log.d(Constant.TAG, "VungleAd::onCreate" + " onAdUnavailable, reason:" + arg0);
			}

			@Override
			public void onAdStart() {
				// TODO Auto-generated method stub
				// 播放广告前调用
				Log.d(Constant.TAG, "VungleAd::onCreate" + " onAdStart");
				onInterstitialAdOpened();
			}

			@Override
			public void onAdPlayableChanged(boolean isAdPlayable) {
				// TODO Auto-generated method stub
				// 当可播放性（playability）状态改变时调用。如果isAdPlayable为true，则表明你现在可以播放广告。
				// 如果为false，则你还无法播放广告。
				Log.d(Constant.TAG, "VungleAd::onCreate" + " onAdPlayableChanged, isAdPlayable:" + isAdPlayable);
				onIsInterstitialAdPlayable(isAdPlayable ? 1 : 0);
			}

			@Override
			public void onAdEnd(boolean arg0) {
				// TODO Auto-generated method stub
				// 当用户离开广告返回到你的应用时调用
				Log.d(Constant.TAG, "VungleAd::onCreate" + " onAdEnd");
				onInterstitialAdClosed();
			}
		});
	}

	public static void showInterstitialAd() {
		activity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (VunglePub.getInstance().isAdPlayable()) {
					VunglePub.getInstance().playAd();
				}
			}
		});
	}

	public static void onPause() {
		VunglePub.getInstance().onPause();
	}

	public static void onResume() {
		VunglePub.getInstance().onResume();
	}

	public static void onDestroy() {
		VunglePub.getInstance().clearEventListeners();
		activity = null;
	}

	public static native void onIsInterstitialAdPlayable(int isAdPlayable);

	public static native void onInterstitialAdOpened();

	public static native void onInterstitialAdClosed();
}
