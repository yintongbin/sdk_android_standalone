#include <jni.h>
#include <android/log.h>
#include "Ad.h"

#include "JniHelper.h"

void Java_com_seastar_ad_GoogleAd_onInterstitialAdLoaded(JNIEnv *env,
		jclass obj) {
	// ad loaded, can show
}

void Java_com_seastar_ad_GoogleAd_onInterstitialAdFailedToLoad(JNIEnv *env,
		jclass obj) {
	// ad loaded failed
}

void Java_com_seastar_ad_GoogleAd_onInterstitialAdOpened(JNIEnv *env,
		jclass obj) {
	// 开始播放广告，可以在此处暂停游戏
}

void Java_com_seastar_ad_GoogleAd_onInterstitialAdClosed(JNIEnv *env,
		jclass obj) {
	// 广告播放完毕，可以在此处恢复游戏
}

void Java_com_seastar_ad_FacebookAd_onInterstitialAdLoaded(JNIEnv *env,
		jclass obj) {
	// ad loaded, can show
}

void Java_com_seastar_ad_FacebookAd_onInterstitialAdFailedToLoad(JNIEnv *env,
		jclass obj) {
	// ad loaded failed
}

void Java_com_seastar_ad_FacebookAd_onInterstitialAdOpened(JNIEnv *env,
		jclass obj) {
	// 开始播放广告，可以在此处暂停游戏
}

void Java_com_seastar_ad_FacebookAd_onInterstitialAdClosed(JNIEnv *env,
		jclass obj) {
	// 广告播放完毕，可以在此处恢复游戏
}

void Java_com_seastar_ad_VungleAd_onIsInterstitialAdPlayable(JNIEnv *env,
		jclass obj, jint flags) {
	if (flags) {
		// 有可播放的广告，可以进行播放
	} else {
		// 没有可供播放的广告
	}
}

void Java_com_seastar_ad_VungleAd_onInterstitialAdOpened(JNIEnv *env,
		jclass obj) {
	// 开始播放广告，可以在此处暂停游戏
}

void Java_com_seastar_ad_VungleAd_onInterstitialAdClosed(JNIEnv *env,
		jclass obj) {
	// 广告播放完毕，可以在此处恢复游戏
}

void loadGoogleInterstitialAd() {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, "com/seastar/ad/GoogleAd",
			"loadInterstitialAd", "()V")) {
		t.env->CallStaticVoidMethod(t.classID, t.methodID);
		t.env->DeleteLocalRef(t.classID);
	}
}

void showGoogleInterstitialAd() {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, "com/seastar/ad/GoogleAd",
			"showInterstitialAd", "()V")) {
		t.env->CallStaticVoidMethod(t.classID, t.methodID);
		t.env->DeleteLocalRef(t.classID);
	}
}

void loadFacebookInterstitialAd() {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, "com/seastar/ad/FacebookAd",
			"loadInterstitialAd", "()V")) {
		t.env->CallStaticVoidMethod(t.classID, t.methodID);
		t.env->DeleteLocalRef(t.classID);
	}
}

void showFacebookInterstitialAd() {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, "com/seastar/ad/FacebookAd",
			"showInterstitialAd", "()V")) {
		t.env->CallStaticVoidMethod(t.classID, t.methodID);
		t.env->DeleteLocalRef(t.classID);
	}
}

void showVungleInterstitialAd() {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, "com/seastar/ad/VungleAd",
			"showInterstitialAd", "()V")) {
		t.env->CallStaticVoidMethod(t.classID, t.methodID);
		t.env->DeleteLocalRef(t.classID);
	}
}

