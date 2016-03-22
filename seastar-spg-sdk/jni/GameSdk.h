#ifndef _GAMESDK_H_
#define _GAMESDK_H_

#include <jni.h>
#include <string>
#include <vector>

void doFbInit();
void doGoogleInit();
void doFbLogin();
void doGoogleLogin();
void doFbLogout();
void doGoogleLogout();
void doGooglePay(std::string &sku);
void doGoogleConsume(std::string &sku, std::string &itemType, std::string &signature, std::string &purchaseOriginalData);
void queryGoogleInventory(std::vector<std::string> &skus);
void doFacebookSharePhoto(std::string &imageUri, std::string &caption,std::vector<std::string> &peopleIds, std::string &contentUri, std::string &refUrl);
void doFacebookShareLink(std::string &url, std::string &title,std::string &contentDescription, std::string &imageUrl, std::vector<std::string> &peopleIds);
void doInviteFriends(std::string &applinkUrl,std::string &previewImageUrl);
void doUnlockAchievement(std::string &achievementId);
void doIncrementAchievement(std::string &achievementId, int &score);
void doShowAchievement();
void doUpdateScoreOnLeaderboard(std::string &leaderboardId, int &score);
void doShowLeaderboard();
void doRequestAllFriends(std::string &height, std::string &width, std::string &limit);
void doRequestAllFriendsNextPage();
void doRequestAllFriendsPrevPage();
void doRequestFriendsInApp(std::string &limit);
void doRequestFriendsInAppNextPage();
void doRequestFriendsInAppPrevPage();


extern "C" {

JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSdk_onFbInitCb(JNIEnv *env, jclass obj, jint flags);
JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSdk_onGoogleInitCb(JNIEnv *env, jclass obj, jint flags);

JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSdk_onFbLoginCb(JNIEnv *env, jclass obj, jint flags, jstring user);
JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSdk_onGoogleLoginCb(JNIEnv *env, jclass obj,
		jint flags, jstring user);

JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSdk_onGooglePayCb(JNIEnv *env, jclass obj,
		jint flags, jstring sku, jstring itemType, jstring googleOrder, jstring signature, jstring purchaseOriginalData);
JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSdk_onGoogleConsumeCb(JNIEnv *env, jclass obj,
		jint flags, jstring sku);

JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSdk_onQueryGoogleInventory(JNIEnv *env, jclass obj,
		jint flags, jstring skus);
JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSdk_onFacebookSharePhoto(JNIEnv *env, jclass obj,jint flags);
JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSdk_onFacebookShareLink(JNIEnv *env, jclass obj,jint flags);
JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSdk_onInviteFriends(JNIEnv *env, jclass obj,jint flags);
JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSdk_onRequestAllFriends(JNIEnv *env, jclass obj,jstring graph);
JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSdk_onRequestAllFriendsNextPage(JNIEnv *env, jclass obj,jstring graph);
JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSdk_onRequestAllFriendsPrevPage(JNIEnv *env, jclass obj,jstring graph);
JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSdk_onRequestFriendsInApp(JNIEnv *env, jclass obj,jstring graph);
JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSdk_onRequestFriendsInAppNextPage(JNIEnv *env, jclass obj,jstring graph);
JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSdk_onRequestFriendsInAppPrevPage(JNIEnv *env, jclass obj,jstring graph);

}

#endif
