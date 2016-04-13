#ifndef _AD_H_
#define _AD_H_

#include <jni.h>
#include <string>
#include <vector>


void loadGoogleInterstitialAd();
void showGoogleInterstitialAd();

void loadFacebookInterstitialAd();
void showFacebookInterstitialAd();

void showVungleInterstitialAd();



extern "C" {

JNIEXPORT void JNICALL Java_com_seastar_ad_GoogleAd_onInterstitialAdLoaded(JNIEnv *env, jclass obj);
JNIEXPORT void JNICALL Java_com_seastar_ad_GoogleAd_onInterstitialAdFailedToLoad(JNIEnv *env, jclass obj);
JNIEXPORT void JNICALL Java_com_seastar_ad_GoogleAd_onInterstitialAdOpened(JNIEnv *env, jclass obj);
JNIEXPORT void JNICALL Java_com_seastar_ad_GoogleAd_onInterstitialAdClosed(JNIEnv *env, jclass obj);

JNIEXPORT void JNICALL Java_com_seastar_ad_FacebookAd_onInterstitialAdLoaded(JNIEnv *env, jclass obj);
JNIEXPORT void JNICALL Java_com_seastar_ad_FacebookAd_onInterstitialAdFailedToLoad(JNIEnv *env, jclass obj);
JNIEXPORT void JNICALL Java_com_seastar_ad_FacebookAd_onInterstitialAdOpened(JNIEnv *env, jclass obj);
JNIEXPORT void JNICALL Java_com_seastar_ad_FacebookAd_onInterstitialAdClosed(JNIEnv *env, jclass obj);


JNIEXPORT void JNICALL Java_com_seastar_ad_VungleAd_onIsInterstitialAdPlayable(JNIEnv *env, jclass obj, jint flags);
JNIEXPORT void JNICALL Java_com_seastar_ad_VungleAd_onInterstitialAdOpened(JNIEnv *env, jclass obj);
JNIEXPORT void JNICALL Java_com_seastar_ad_VungleAd_onInterstitialAdClosed(JNIEnv *env, jclass obj);

}

#endif
