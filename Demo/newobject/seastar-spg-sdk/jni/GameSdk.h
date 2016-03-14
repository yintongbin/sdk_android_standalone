#ifndef _GAMESDK_H_
#define _GAMESDK_H_

#include <jni.h>
#include <string>
#include <vector>

extern "C" {

void doFbInit();
void doGoogleInit();
void doFbLogin();
void doGoogleLogin();
void doFbLogout();
void doGoogleLogout();
void doGooglePay(std::string &sku);
void doGoogleConsume(std::string &sku, std::string &itemType, std::string &signature, std::string &purchaseOriginalData);
void queryGoogleInventory(std::vector<std::string> &skus);

JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSDK_onFbInitCb(JNIEnv *env, jclass obj, jint flags);
JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSDK_onGoogleInitCb(JNIEnv *env, jclass obj, jint flags);

JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSDK_onFbLoginCb(JNIEnv *env, jclass obj, jint flags, jstring user);
JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSDK_onGoogleLoginCb(JNIEnv *env, jclass obj,
		jint flags, jstring user);

JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSDK_onGooglePayCb(JNIEnv *env, jclass obj,
		jint flags, jstring sku, jstring itemType, jstring googleOrder, jstring signature, jstring purchaseOriginalData);
JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSDK_onGoogleConsumeCb(JNIEnv *env, jclass obj,
		jint flags, jstring sku);

JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSDK_onQueryGoogleInventory(JNIEnv *env, jclass obj,
		jint flags, jstring skus);
}

#endif
