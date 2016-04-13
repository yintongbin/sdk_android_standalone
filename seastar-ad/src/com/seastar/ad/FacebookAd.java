package com.seastar.ad;

import android.app.Activity;
import android.util.Log;

import com.facebook.ads.*;

public class FacebookAd {

	private static InterstitialAd interstitialAd;
	private static Activity activity;
	private static String placementId;

	public static void onCreate(Activity activity, String placementId) {
		FacebookAd.activity = activity;
		FacebookAd.placementId = placementId;

		// the hashed ID that is printed to the log cat when you first make a
		// request to load an ad on a device.
		// AdSettings.addTestDevice("hash id");
	}

	// 在任何现实广告的地方使用
	public static void loadInterstitialAd() {

		activity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (interstitialAd != null) {
					interstitialAd.destroy();
					interstitialAd = null;
				}

				interstitialAd = new InterstitialAd(activity, placementId);
				interstitialAd.setAdListener(new InterstitialAdListener() {

					@Override
					public void onError(Ad arg0, AdError arg1) {
						// TODO Ad failed to load
						Log.d(Constant.TAG, "FacebookAd::loadInterstitialAd"
								+ " ad load failed. " + arg1.getErrorMessage());
						onInterstitialAdFailedToLoad();
					}

					@Override
					public void onAdLoaded(Ad arg0) {
						// Ad is loaded and ready to be displayed
						// You can now display the full screen ad using this code:
						Log.d(Constant.TAG, "FacebookAd::loadInterstitialAd"
								+ " ad loaded.");
						
						onInterstitialAdLoaded();
					}

					@Override
					public void onAdClicked(Ad arg0) {
						// Use this function as indication for a user's click on the ad.
						Log.d(Constant.TAG, "FacebookAd::loadInterstitialAd"
								+ " ad clicked.");
					}

					@Override
					public void onInterstitialDisplayed(Ad arg0) {
						// Where relevant, use this function to pause your app's flow
						Log.d(Constant.TAG, "FacebookAd::loadInterstitialAd"
								+ " ad opened.");
						onInterstitialAdOpened();
					}

					@Override
					public void onInterstitialDismissed(Ad arg0) {
						// Use this function to resume your app's flow
						Log.d(Constant.TAG, "FacebookAd::loadInterstitialAd"
								+ " ad closed.");
						onInterstitialAdClosed();
					}
				});

				interstitialAd.loadAd();
			}
		});
	}
	
	public static void showInterstitialAd() {
		activity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (interstitialAd != null && interstitialAd.isAdLoaded()) {
					interstitialAd.show();
				}
			}
		});
	}

	public static void onDestroy() {
		if (interstitialAd != null) {
			interstitialAd.destroy();
			interstitialAd = null;
		}
		activity = null;
	}

	public static native void onInterstitialAdLoaded();
	public static native void onInterstitialAdFailedToLoad();
	public static native void onInterstitialAdOpened();
	public static native void onInterstitialAdClosed();
}
