#include <jni.h>
#include <android/log.h>

#include "JniHelper.h"
#include "GameSdk.h"

#define JAVA_CLASS "com/seastar/spg/Interface/GameSdk"

void Java_com_seastar_spg_Interface_GameSdk_onFbInitCb(JNIEnv *env, jclass obj,
		jint flags) {
	if (flags) {
		__android_log_print(ANDROID_LOG_INFO, "seastar", "fb init success");
		// init success proc
	} else {
		// init failure proc
	}
}

void Java_com_seastar_spg_Interface_GameSdk_onGoogleInitCb(JNIEnv *env,
		jclass obj, jint flags) {
	if (flags) {
		// init success proc
	} else {
		// init failure proc
	}
}

void Java_com_seastar_spg_Interface_GameSdk_onFbLoginCb(JNIEnv *env, jclass obj,
		jint flags, jstring user) {
	if (flags) {
		std::string strUser("");
		const char *pszUser = env->GetStringUTFChars(user, NULL);
		strUser = pszUser;
		env->ReleaseStringUTFChars(user, pszUser);

		// do login success proc
	} else {
		__android_log_print(ANDROID_LOG_INFO, "seastar", "fb login fail");
		// do login failure proc
	}

}

void Java_com_seastar_spg_Interface_GameSdk_onGoogleLoginCb(JNIEnv *env,
		jclass obj, jint flags, jstring user) {
	bool isLogined = false;
	std::string strUser("");

	if (flags == 1) {
		const char *pszUser = env->GetStringUTFChars(user, NULL);
		strUser = pszUser;
		env->ReleaseStringUTFChars(user, pszUser);
		// do login success proc
	} else {
		// do login failure proc
	}
}

void Java_com_seastar_spg_Interface_GameSdk_onGooglePayCb(JNIEnv *env,
		jclass obj, jint flags, jstring sku, jstring itemType,
		jstring googleOrder, jstring signature, jstring purchaseOriginalData) {
	if (flags) {
		std::string strSku("");
		std::string strItemType("");
		std::string strGoogleOrder("");
		std::string strSignature("");
		std::string strPurchaseOriginalData("");

		const char *pszSku = env->GetStringUTFChars(sku, NULL);
		const char *pszItemType = env->GetStringUTFChars(itemType, NULL);
		const char *pszGoogleOrder = env->GetStringUTFChars(googleOrder, NULL);
		const char *pszSignature = env->GetStringUTFChars(signature, NULL);
		const char *pszPurchaseOriginalData = env->GetStringUTFChars(
				purchaseOriginalData, NULL);

		strSku = pszSku;
		strItemType = pszItemType;
		strGoogleOrder = pszGoogleOrder;
		strSignature = pszSignature;
		strPurchaseOriginalData = pszPurchaseOriginalData;

		env->ReleaseStringUTFChars(sku, pszSku);
		env->ReleaseStringUTFChars(itemType, pszItemType);
		env->ReleaseStringUTFChars(googleOrder, pszGoogleOrder);
		env->ReleaseStringUTFChars(signature, pszSignature);
		env->ReleaseStringUTFChars(purchaseOriginalData,
				pszPurchaseOriginalData);

		// pay success proc
	} else {
		// pay failure proc
	}
}

void Java_com_seastar_spg_Interface_GameSdk_onGoogleConsumeCb(JNIEnv *env,
		jclass obj, jint flags, jstring sku) {
	if (flags) {
		std::string strSku("");
		const char *pszSku = env->GetStringUTFChars(sku, NULL);

		strSku = pszSku;

		env->ReleaseStringUTFChars(sku, pszSku);

		// consume success proc
	} else {
		// consume failure proc
	}

}

void Java_com_seastar_spg_Interface_GameSdk_onQueryGoogleInventory(JNIEnv *env,
		jclass obj, jint flags, jstring skus) {
	if (flags) {
		std::string strSkus("");
		const char *pszSkus = env->GetStringUTFChars(skus, NULL);

		strSkus = pszSkus;

		env->ReleaseStringUTFChars(skus, pszSkus);

		// query success proc
	} else {
		// query success failure proc
	}
}

void Java_com_seastar_spg_Interface_GameSdk_onFacebookSharePhoto(JNIEnv *env,
		jclass obj, jint flags) {
	if (flags) {
		// init success proc
	} else {
		// init failure proc
	}
}

void Java_com_seastar_spg_Interface_GameSdk_onFacebookShareLink(JNIEnv *env,
		jclass obj, jint flags) {
	if (flags) {
		// init success proc
	} else {
		// init failure proc
	}
}

void Java_com_seastar_spg_Interface_GameSdk_onInviteFriends(JNIEnv *env,
		jclass obj, jint flags) {
	if (flags) {
		// init success proc
	} else {
		// init failure proc
	}
}

//{
//    "data": [
//        {
//            "id": "AaLItD8yDakYtH5KlZfUZdo4ianzhTjMybJssYdOiTyxl0b5spd7I-uS8soBA_RqLlayiqgeFBnC78h2h24gDCPCRdutsrsFHwUY1CLy9uYrKg",
//            "name": "AnMrie Padlan",
//            "picture": {
//                "data": {
//                    "is_silhouette": false,
//                    "url": "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-frc1/v/t1.0-1/p50x50/10432114_1507979422822314_195551535036305859_n.jpg?oh=d92b6f97ab5a01dab30518db281c8c80&oe=574E1EAE&__gda__=1464804961_cdd74cf23069f6cb132494d232f525ad"
//                }
//            }
//        },
//        {
//            "id": "AaJXzj6BCdyhV07EZje01YdZuKieWK_5v5cVqFjDRFDcitTMd_vBwWF44RhBs_0hhpyeZNYv0k9OEKueGDofiCudnQhpMuiQp6PlSaJHiDoBzQ",
//            "name": "Hai Yan",
//            "picture": {
//                "data": {
//                    "is_silhouette": false,
//                    "url": "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xtf1/v/t1.0-1/p50x50/946196_198102917202172_463520972857951054_n.jpg?oh=b5112cb2313d2f6b41b9610715d66102&oe=578D2365&__gda__=1468849143_60c526409492fa78a26f6772b040e8ec"
//                }
//            }
//        }
//    ],
//    "paging": {
//        "cursors": {
//            "before": "QWFMbkNVUzlUblI5WEtQQURLUmg1UDhCTWJOMVkwN1MtY3UzRjlmcmJScVZAPTi1CcjkwZAU0yX0NZAM0pRVzV0TEVqM0VUSEVkaF9fMksya1RYS2g0dHFtamhUa2VVQUJEd20wd2ZAVLUlNc3JfRWcZD",
//            "after": "QWFKWGlPb2xkaDhqd3hPQnBmazhTN0ZA3RlJsXzlKNW1rRm1aTHhSVHN4Nlk5eFVxd051YUVabHlsazlNNmN0UW9JOFJpbm1wQVQtTy1pQWNiaVFuMlUyN1A5TnhqNkZA1Yk9UTGhYZA0JNS2pXZAmcZD"
//        },
//        "previous": "https://graph.facebook.com/v2.5/197336133976850/taggable_friends?access_token=CAAIfJf9Fsk0BAMRisG8eTZAgHwozSmpt8OiEur8jn5JZCxLzGhiQK9uDwwzVUsfTgWYGKROBqBGRkGLKuNMMGEoVFGNfIBvZBdB8M3uIfcZAvIMTilFVTmKzRWsd2iHRqsz3OZCZCMfMfBLC38ZBxggWGZCjyZBfPOGoicUQMKVQ219AFVHUSvR4eVZBTkWuzokAuIEyFcQ57kiRVfPlIOA5xmiPUjoCVBhMJ4UwWvnUniqAZDZD&fields=id%2Cname%2Cpicture.height%280%29.width%280%29&limit=2&before=QWFMbkNVUzlUblI5WEtQQURLUmg1UDhCTWJOMVkwN1MtY3UzRjlmcmJScVZAPTi1CcjkwZAU0yX0NZAM0pRVzV0TEVqM0VUSEVkaF9fMksya1RYS2g0dHFtamhUa2VVQUJEd20wd2ZAVLUlNc3JfRWcZD"
//    }
//}
void JNICALL Java_com_seastar_spg_Interface_GameSdk_onRequestAllFriends(JNIEnv *env, jclass obj,jstring graph)
{
	std::string strGraph("");
	const char *pszGraph = env->GetStringUTFChars(graph, NULL);

	strGraph = pszGraph;

	env->ReleaseStringUTFChars(graph, pszGraph);

	__android_log_print(ANDROID_LOG_INFO, "seastar", "%s",strGraph.c_str());

}

void JNICALL Java_com_seastar_spg_Interface_GameSdk_onRequestAllFriendsNextPage(JNIEnv *env, jclass obj,jstring graph)
{
	std::string strGraph("");
	const char *pszGraph = env->GetStringUTFChars(graph, NULL);

	strGraph = pszGraph;

	env->ReleaseStringUTFChars(graph, pszGraph);

	__android_log_print(ANDROID_LOG_INFO, "seastar", "%s",strGraph.c_str());
}

void JNICALL Java_com_seastar_spg_Interface_GameSdk_onRequestAllFriendsPrevPage(JNIEnv *env, jclass obj,jstring graph)
{
	std::string strGraph("");
	const char *pszGraph = env->GetStringUTFChars(graph, NULL);

	strGraph = pszGraph;

	env->ReleaseStringUTFChars(graph, pszGraph);

	__android_log_print(ANDROID_LOG_INFO, "seastar", "%s",strGraph.c_str());
}

void JNICALL Java_com_seastar_spg_Interface_GameSdk_onRequestFriendsInApp(JNIEnv *env, jclass obj,jstring graph)
{
	std::string strGraph("");
	const char *pszGraph = env->GetStringUTFChars(graph, NULL);

	strGraph = pszGraph;

	env->ReleaseStringUTFChars(graph, pszGraph);

	__android_log_print(ANDROID_LOG_INFO, "seastar", "%s",strGraph.c_str());
}

void JNICALL Java_com_seastar_spg_Interface_GameSdk_onRequestFriendsInAppNextPage(JNIEnv *env, jclass obj,jstring graph)
{
	std::string strGraph("");
	const char *pszGraph = env->GetStringUTFChars(graph, NULL);

	strGraph = pszGraph;

	env->ReleaseStringUTFChars(graph, pszGraph);

	__android_log_print(ANDROID_LOG_INFO, "seastar", "%s",strGraph.c_str());
}

void JNICALL Java_com_seastar_spg_Interface_GameSdk_onRequestFriendsInAppPrevPage(JNIEnv *env, jclass obj,jstring graph)
{
	std::string strGraph("");
	const char *pszGraph = env->GetStringUTFChars(graph, NULL);

	strGraph = pszGraph;

	env->ReleaseStringUTFChars(graph, pszGraph);

	__android_log_print(ANDROID_LOG_INFO, "seastar", "%s",strGraph.c_str());
}

void doFbInit() {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doFbInit", "()V")) {
		t.env->CallStaticVoidMethod(t.classID, t.methodID);
		t.env->DeleteLocalRef(t.classID);
	}
}

void doGoogleInit() {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doGoogleInit", "()V")) {
		t.env->CallStaticVoidMethod(t.classID, t.methodID);
		t.env->DeleteLocalRef(t.classID);
	}
}

void doFbLogin() {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doFbLogin", "()V")) {
		t.env->CallStaticVoidMethod(t.classID, t.methodID);
		t.env->DeleteLocalRef(t.classID);
	}
}

void doGoogleLogin() {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doGoogleLogin", "()V")) {
		t.env->CallStaticVoidMethod(t.classID, t.methodID);
		t.env->DeleteLocalRef(t.classID);
	}
}

void doFbLogout() {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doFbLogout", "()V")) {
		t.env->CallStaticVoidMethod(t.classID, t.methodID);
		t.env->DeleteLocalRef(t.classID);
	}
}

void doGoogleLogout() {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doGoogleLogout",
			"()V")) {
		t.env->CallStaticVoidMethod(t.classID, t.methodID);
		t.env->DeleteLocalRef(t.classID);
	}
}

void doGooglePay(std::string &sku) {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doGooglePay",
			"(Ljava/lang/String;)V")) {
		jstring jSku = t.env->NewStringUTF(sku.c_str());

		t.env->CallStaticVoidMethod(t.classID, t.methodID, jSku);

		t.env->DeleteLocalRef(jSku);

		t.env->DeleteLocalRef(t.classID);
	}
}

void doGoogleConsume(std::string &sku, std::string &itemType,
		std::string &signature, std::string &purchaseOriginalData) {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doGoogleConsume",
			"(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V")) {
		jstring jSku = t.env->NewStringUTF(sku.c_str());
		jstring jItemType = t.env->NewStringUTF(itemType.c_str());
		jstring jSignature = t.env->NewStringUTF(signature.c_str());
		jstring jPurchaseOriginalData = t.env->NewStringUTF(
				purchaseOriginalData.c_str());

		t.env->CallStaticVoidMethod(t.classID, t.methodID, jSku, jItemType,
				jSignature, jPurchaseOriginalData);

		t.env->DeleteLocalRef(jSku);
		t.env->DeleteLocalRef(jItemType);
		t.env->DeleteLocalRef(jSignature);
		t.env->DeleteLocalRef(jPurchaseOriginalData);

		t.env->DeleteLocalRef(t.classID);
	}
}

void queryGoogleInventory(std::vector<std::string> &skus) {
	if (skus.size() == 0)
		return;

	std::string strSku;

	for (int i = 0; i < skus.size(); i++) {
		strSku = strSku + " " + skus[i];
	}
	strSku = strSku.substr(1, strSku.length());

	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "queryGoogleInventory",
			"(Ljava/lang/String;)V")) {
		jstring jSku = t.env->NewStringUTF(strSku.c_str());

		t.env->CallStaticVoidMethod(t.classID, t.methodID, jSku);

		t.env->DeleteLocalRef(jSku);

		t.env->DeleteLocalRef(t.classID);
	}
}

void doFacebookSharePhoto(std::string &imageUri, std::string &caption,
		std::vector<std::string> &peopleIds, std::string &contentUri,
		std::string &refUrl) {
	std::string strPeopleIds;

	for (int i = 0; i < peopleIds.size(); i++) {
		strPeopleIds = strPeopleIds + " " + strPeopleIds[i];
	}
	strPeopleIds = strPeopleIds.substr(1, strPeopleIds.length());

	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doFacebookSharePhoto",
			"(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V")) {
		jstring jimageUri = t.env->NewStringUTF(imageUri.c_str());
		jstring jcaption = t.env->NewStringUTF(caption.c_str());
		jstring jstrPeopleIds = t.env->NewStringUTF(strPeopleIds.c_str());
		jstring jcontentUri = t.env->NewStringUTF(contentUri.c_str());
		jstring jrefUrl = t.env->NewStringUTF(refUrl.c_str());

		t.env->CallStaticVoidMethod(t.classID, t.methodID, jimageUri, jcaption,
				jstrPeopleIds, jcontentUri, jrefUrl);

		t.env->DeleteLocalRef(jimageUri);
		t.env->DeleteLocalRef(jcaption);
		t.env->DeleteLocalRef(jstrPeopleIds);
		t.env->DeleteLocalRef(jcontentUri);
		t.env->DeleteLocalRef(jrefUrl);

		t.env->DeleteLocalRef(t.classID);

	}

}

void doFacebookShareLink(std::string &url, std::string &title,
		std::string &contentDescription, std::string &imageUrl,
		std::string &peopleIds) {
	std::string strPeopleIds;

	for (int i = 0; i < peopleIds.size(); i++) {
		strPeopleIds = strPeopleIds + " " + strPeopleIds[i];
	}
	strPeopleIds = strPeopleIds.substr(1, strPeopleIds.length());

	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doFacebookShareLink",
			"(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V")) {
		jstring jurl = t.env->NewStringUTF(url.c_str());
		jstring jtitle = t.env->NewStringUTF(title.c_str());
		jstring jcontentDescription = t.env->NewStringUTF(
				contentDescription.c_str());
		jstring jimageUrl = t.env->NewStringUTF(imageUrl.c_str());
		jstring jpeopleIds = t.env->NewStringUTF(strPeopleIds.c_str());

		t.env->CallStaticVoidMethod(t.classID, t.methodID, jurl, jtitle,
				jcontentDescription, jimageUrl, jpeopleIds);

		t.env->DeleteLocalRef(jurl);
		t.env->DeleteLocalRef(jtitle);
		t.env->DeleteLocalRef(jcontentDescription);
		t.env->DeleteLocalRef(jimageUrl);
		t.env->DeleteLocalRef(jpeopleIds);

		t.env->DeleteLocalRef(t.classID);

	}

}

void doInviteFriends(std::string &applinkUrl, std::string &previewImageUrl) {

	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doInviteFriends",
			"(Ljava/lang/String;Ljava/lang/String;)V")) {
		jstring japplinkUrl = t.env->NewStringUTF(applinkUrl.c_str());
		jstring jpreviewImageUrl = t.env->NewStringUTF(previewImageUrl.c_str());

		t.env->CallStaticVoidMethod(t.classID, t.methodID, japplinkUrl,
				jpreviewImageUrl);

		t.env->DeleteLocalRef(japplinkUrl);
		t.env->DeleteLocalRef(jpreviewImageUrl);

		t.env->DeleteLocalRef(t.classID);

	}
}

void doUnlockAchievement(std::string &achievementId) {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doUnlockAchievement",
			"(Ljava/lang/String;)V")) {
		jstring jachievementId = t.env->NewStringUTF(achievementId.c_str());

		t.env->CallStaticVoidMethod(t.classID, t.methodID, jachievementId);

		t.env->DeleteLocalRef(jachievementId);

		t.env->DeleteLocalRef(t.classID);

	}
}

void doIncrementAchievement(std::string &achievementId, int score) {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doIncrementAchievement",
			"(Ljava/lang/String;I)V")) {
		jstring jachievementId = t.env->NewStringUTF(achievementId.c_str());

		t.env->CallStaticVoidMethod(t.classID, t.methodID, jachievementId,
				score);

		t.env->DeleteLocalRef(jachievementId);

		t.env->DeleteLocalRef(t.classID);

	}
}

void doShowAchievement() {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doShowAchievement",
			"()V")) {
		t.env->CallStaticVoidMethod(t.classID, t.methodID);

		t.env->DeleteLocalRef(t.classID);
	}
}

void doUpdateScoreOnLeaderboard(std::string leaderboardId, int score) {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS,
			"doUpdateScoreOnLeaderboard", "(Ljava/lang/String;I)V")) {
		jstring jleaderboardId = t.env->NewStringUTF(leaderboardId.c_str());

		t.env->CallStaticVoidMethod(t.classID, t.methodID, jleaderboardId,
				score);

		t.env->DeleteLocalRef(jleaderboardId);

		t.env->DeleteLocalRef(t.classID);

	}
}

void doShowLeaderboard() {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doShowLeaderboard",
			"()V")) {
		t.env->CallStaticVoidMethod(t.classID, t.methodID);

		t.env->DeleteLocalRef(t.classID);
	}
}

void doRequestAllFriends(std::string &height, std::string &width,
		std::string &limit) {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doRequestAllFriends",
			"(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V")) {
		jstring jheight = t.env->NewStringUTF(height.c_str());
		jstring jwidth = t.env->NewStringUTF(width.c_str());
		jstring jlimit = t.env->NewStringUTF(limit.c_str());

		t.env->CallStaticVoidMethod(t.classID, t.methodID, jheight, jwidth,
				jlimit);

		t.env->DeleteLocalRef(jheight);
		t.env->DeleteLocalRef(jwidth);
		t.env->DeleteLocalRef(jlimit);

		t.env->DeleteLocalRef(t.classID);
	}
}

void doRequestAllFriendsNextPage() {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS,
			"doRequestAllFriendsNextPage", "()V")) {
		t.env->CallStaticVoidMethod(t.classID, t.methodID);

		t.env->DeleteLocalRef(t.classID);
	}
}

void doRequestAllFriendsPrevPage() {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS,
			"doRequestAllFriendsPrevPage", "()V")) {
		t.env->CallStaticVoidMethod(t.classID, t.methodID);

		t.env->DeleteLocalRef(t.classID);
	}
}

void doRequestFriendsInApp(std::string &limit) {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doRequestFriendsInApp",
			"(Ljava/lang/String;)V")) {
		jstring jlimit = t.env->NewStringUTF(limit.c_str());

		t.env->CallStaticVoidMethod(t.classID, t.methodID, jlimit);

		t.env->DeleteLocalRef(jlimit);
		t.env->DeleteLocalRef(t.classID);
	}
}

void doRequestFriendsInAppNextPage() {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS,
			"doRequestFriendsInAppNextPage", "()V")) {
		t.env->CallStaticVoidMethod(t.classID, t.methodID);

		t.env->DeleteLocalRef(t.classID);
	}
}

void doRequestFriendsInAppPrevPage() {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS,
			"doRequestFriendsInAppPrevPage", "()V")) {
		t.env->CallStaticVoidMethod(t.classID, t.methodID);

		t.env->DeleteLocalRef(t.classID);
	}
}

