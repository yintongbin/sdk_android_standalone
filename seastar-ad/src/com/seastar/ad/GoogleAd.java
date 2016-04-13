package com.seastar.ad;

import android.app.Activity;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class GoogleAd {

	private static InterstitialAd interstitialAd = null;
	private static Activity activity;

	public static void onCreate(Activity activity, String adUnitId) {
		GoogleAd.activity = activity;
		
		interstitialAd = new InterstitialAd(activity);
		interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

		interstitialAd.setAdListener(new AdListener() {
			@Override
			public void onAdClosed() {
				// Called when the user is about to return to the
				// application after clicking on an ad.
				Log.d(Constant.TAG, "GoogleAdMob::loadInterstitialAd" + " ad closed.");
				onInterstitialAdClosed();
			}

			@Override
			public void onAdFailedToLoad(int errorCode) {
				// Called when an ad request failed. The error code is
				// usually ERROR_CODE_INTERNAL_ERROR,
				// ERROR_CODE_INVALID_REQUEST, ERROR_CODE_NETWORK_ERROR, or
				// ERROR_CODE_NO_FILL.
				Log.d(Constant.TAG, "GoogleAdMob::loadInterstitialAd" + " ad load failed.");
				onInterstitialAdFailedToLoad();
			}

			@Override
			public void onAdLeftApplication() {
				// Called when an ad leaves the application (e.g., to go to
				// the browser).
				Log.d(Constant.TAG, "GoogleAdMob::loadInterstitialAd" + " open browser.");
			}

			@Override
			public void onAdOpened() {
				// Called when an ad opens an overlay that covers the
				// screen.
				Log.d(Constant.TAG, "GoogleAdMob::loadInterstitialAd" + " ad opened.");
				onInterstitialAdOpened();
			}

			@Override
			public void onAdLoaded() {

				// Called when an ad is received.
				Log.d(Constant.TAG, "GoogleAdMob::loadInterstitialAd" + " ad loaded.");

				onInterstitialAdLoaded();
			}
		});
	}

	public static void loadInterstitialAd() {
		
		activity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				// AdRequest adRequest = new AdRequest.Builder().addTestDevice("YOUR_DEVICE_HASH").build();
				if (interstitialAd != null && !interstitialAd.isLoading() && !interstitialAd.isLoaded()) {
					AdRequest adRequest = new AdRequest.Builder().build();

					interstitialAd.loadAd(adRequest);
				}
			}
		});
	}
	
	public static void showInterstitialAd() {
		activity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (interstitialAd != null && interstitialAd.isLoaded()) {
					interstitialAd.show();
				}
			}
		});
	}
	
	public static void onDestroy() {
		if (interstitialAd != null) {
			interstitialAd = null;
		}
		activity = null;
	}
	
	public static native void onInterstitialAdLoaded();
	public static native void onInterstitialAdFailedToLoad();
	public static native void onInterstitialAdOpened();
	public static native void onInterstitialAdClosed();

}
