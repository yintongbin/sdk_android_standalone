#include <jni.h>
#include <android/log.h>

#include "rapidjson/document.h"
#include "rapidjson/prettywriter.h"

#include "JniHelper.h"
#include "GameSdk.h"

#define JAVA_CLASS "com/seastar/spg/Interface/GameSdk"


void Java_com_seastar_spg_Interface_GameSdk_onFbInitCb(JNIEnv *env, jclass obj, jint flags)
{
	if (flags)
	{
		__android_log_print(ANDROID_LOG_INFO, "seastar", "fb init success");
		// init success proc
	}
	else
	{
		// init failure proc
	}
}


void Java_com_seastar_spg_Interface_GameSdk_onGoogleInitCb(JNIEnv *env, jclass obj, jint flags)
{
	if (flags)
	{
		// init success proc
	}
	else
	{
		// init failure proc
	}
}

void Java_com_seastar_spg_Interface_GameSdk_onFbLoginCb(JNIEnv *env, jclass obj, jint flags, jstring user)
{
	if (flags)
	{
		std::string strUser("");
		const char *pszUser = env->GetStringUTFChars(user, NULL);
		strUser = pszUser;
		env->ReleaseStringUTFChars(user, pszUser);

		rapidjson::Document doc;
		doc.Parse(strUser.c_str());

		std::string id = doc["id"].GetString();
		std::string name = doc["name"].GetString();
		std::string picture = doc["picture"].GetString();
		std::string email = doc["email"].GetString();
		std::string first_name = doc["first_name"].GetString();
		std::string last_name = doc["last_name"].GetString();
		std::string middle_name = doc["middle_name"].GetString();
		std::string gender = doc["gender"].GetString();
		std::string location = doc["location"].GetString();

		// do login success proc
	}
	else
	{
		__android_log_print(ANDROID_LOG_INFO, "seastar", "fb login fail");
		// do login failure proc
	}

}

void Java_com_seastar_spg_Interface_GameSdk_onGoogleLoginCb(JNIEnv *env, jclass obj, jint flags, jstring user)
{
	bool isLogined = false;
	std::string strUser("");

	if (flags == 1)
	{
		const char *pszUser = env->GetStringUTFChars(user, NULL);
		strUser = pszUser;
		env->ReleaseStringUTFChars(user, pszUser);

		rapidjson::Document doc;
		doc.Parse(strUser.c_str());

		std::string id = doc["id"].GetString();
		std::string ageRange = doc["ageRange"].GetString();
		std::string birthday = doc["birthday"].GetString();
		std::string currentLocation = doc["currentLocation"].GetString();
		std::string displayName = doc["displayName"].GetString();
		std::string image = doc["image"].GetString();
		std::string language = doc["language"].GetString();
		std::string name = doc["name"].GetString();
		std::string nickName = doc["nickName"].GetString();
		std::string url = doc["url"].GetString();

		// do login success proc
	}
	else
	{
		// do login failure proc
	}
}

void Java_com_seastar_spg_Interface_GameSdk_onGooglePayCb(JNIEnv *env, jclass obj, jint flags, jstring sku,
		jstring itemType, jstring googleOrder, jstring signature, jstring purchaseOriginalData)
{
	if (flags)
	{
		std::string strSku("");
		std::string strItemType("");
		std::string strGoogleOrder("");
		std::string strSignature("");
		std::string strPurchaseOriginalData("");

		const char *pszSku = env->GetStringUTFChars(sku, NULL);
		const char *pszItemType = env->GetStringUTFChars(itemType, NULL);
		const char *pszGoogleOrder = env->GetStringUTFChars(googleOrder, NULL);
		const char *pszSignature = env->GetStringUTFChars(signature, NULL);
		const char *pszPurchaseOriginalData = env->GetStringUTFChars(purchaseOriginalData, NULL);

		strSku = pszSku;
		strItemType = pszItemType;
		strGoogleOrder = pszGoogleOrder;
		strSignature = pszSignature;
		strPurchaseOriginalData = pszPurchaseOriginalData;

		env->ReleaseStringUTFChars(sku, pszSku);
		env->ReleaseStringUTFChars(itemType, pszItemType);
		env->ReleaseStringUTFChars(googleOrder, pszGoogleOrder);
		env->ReleaseStringUTFChars(signature, pszSignature);
		env->ReleaseStringUTFChars(purchaseOriginalData, pszPurchaseOriginalData);

		// pay success proc
	}
	else
	{
		// pay failure proc
	}
}

void Java_com_seastar_spg_Interface_GameSdk_onGoogleConsumeCb(JNIEnv *env, jclass obj, jint flags, jstring sku)
{
	if (flags)
	{
		std::string strSku("");
		const char *pszSku = env->GetStringUTFChars(sku, NULL);

		strSku = pszSku;

		env->ReleaseStringUTFChars(sku, pszSku);

		// consume success proc
	}
	else
	{
		// consume failure proc
	}

}

void Java_com_seastar_spg_Interface_GameSdk_onQueryGoogleInventory(JNIEnv *env, jclass obj,
		jint flags, jstring skus)
{
	if (flags)
	{
		std::string strSkus("");
		const char *pszSkus = env->GetStringUTFChars(skus, NULL);

		strSkus = pszSkus;

		env->ReleaseStringUTFChars(skus, pszSkus);

		rapidjson::Document doc;
		doc.Parse(strSkus.c_str());

		const rapidjson::Value &value = doc["arr"];
		for (int i = 0; i < value.Size(); i++)
		{
			std::string description = value[i]["description"].GetString();
			std::string price = value[i]["price"].GetString();
			std::string sku = value[i]["sku"].GetString();
			std::string title = value[i]["title"].GetString();
			std::string type = value[i]["type"].GetString();
			std::string priceAmountMicros = value[i]["priceAmountMicros"].GetString();
			std::string priceCurrencyCode = value[i]["priceCurrencyCo de"].GetString();
			std::string googleOrder = value[i]["googleOrder"].GetString();
			std::string signature = value[i]["signature"].GetString();
			std::string purchaseOriginalData = value[i]["purchaseOriginalData"].GetString();

			// 每个循环一个购买的商品信息
		}

		

		// query success proc
	}
	else
	{
		// query success failure proc
	}
}


void Java_com_seastar_spg_Interface_GameSdk_onFacebookSharePhoto(JNIEnv *env, jclass obj, jint flags)
{
	if (flags)
	{
		// init success proc
	}
	else
	{
		// init failure proc
	}
}

void Java_com_seastar_spg_Interface_GameSdk_onFacebookShareLink(JNIEnv *env, jclass obj,jint flags)
{
	if (flags)
	{
		// init success proc
	}
	else
	{
		// init failure proc
	}
}

void Java_com_seastar_spg_Interface_GameSdk_onInviteFriends(JNIEnv *env, jclass obj,jint flags)
{
	if (flags)
	{
		// init success proc
	}
	else
	{
		// init failure proc
	}
}

void JNICALL Java_com_seastar_spg_Interface_GameSdk_onRequestAllFriends(JNIEnv *env, jclass obj,jstring graph)
{

}

void JNICALL Java_com_seastar_spg_Interface_GameSdk_onRequestAllFriendsNextPage(JNIEnv *env, jclass obj,jstring graph)
{

}

void JNICALL Java_com_seastar_spg_Interface_GameSdk_onRequestAllFriendsPrevPage(JNIEnv *env, jclass obj,jstring graph)
{

}

void JNICALL Java_com_seastar_spg_Interface_GameSdk_onRequestFriendsInApp(JNIEnv *env, jclass obj,jstring graph)
{

}

void JNICALL Java_com_seastar_spg_Interface_GameSdk_onRequestFriendsInAppNextPage(JNIEnv *env, jclass obj,jstring graph)
{

}

void JNICALL Java_com_seastar_spg_Interface_GameSdk_onRequestFriendsInAppPrevPage(JNIEnv *env, jclass obj,jstring graph)
{

}


void doFbInit()
{
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doFbInit", "()V"))
	{
		t.env->CallStaticVoidMethod(t.classID, t.methodID);
		t.env->DeleteLocalRef(t.classID);
	}
}

void doGoogleInit()
{
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doGoogleInit", "()V"))
	{
		t.env->CallStaticVoidMethod(t.classID, t.methodID);
		t.env->DeleteLocalRef(t.classID);
	}
}

void doFbLogin()
{
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doFbLogin", "()V"))
	{
		t.env->CallStaticVoidMethod(t.classID, t.methodID);
		t.env->DeleteLocalRef(t.classID);
	}
}

void doGoogleLogin()
{
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doGoogleLogin", "()V"))
	{
		t.env->CallStaticVoidMethod(t.classID, t.methodID);
		t.env->DeleteLocalRef(t.classID);
	}
}

void doFbLogout()
{
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doFbLogout", "()V"))
	{
		t.env->CallStaticVoidMethod(t.classID, t.methodID);
		t.env->DeleteLocalRef(t.classID);
	}
}

void doGoogleLogout()
{
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doGoogleLogout", "()V"))
	{
		t.env->CallStaticVoidMethod(t.classID, t.methodID);
		t.env->DeleteLocalRef(t.classID);
	}
}

void doGooglePay(std::string &sku)
{
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doGooglePay",
			"(Ljava/lang/String;)V"))
	{
		jstring jSku = t.env->NewStringUTF(sku.c_str());

		t.env->CallStaticVoidMethod(t.classID, t.methodID, jSku);

		t.env->DeleteLocalRef(jSku);

		t.env->DeleteLocalRef(t.classID);
	}
}

void doGoogleConsume(std::string &sku, std::string &itemType, std::string &signature, std::string &purchaseOriginalData)
{
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doGoogleConsume",
			"(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V"))
	{
		jstring jSku = t.env->NewStringUTF(sku.c_str());
		jstring jItemType = t.env->NewStringUTF(itemType.c_str());
		jstring jSignature = t.env->NewStringUTF(signature.c_str());
		jstring jPurchaseOriginalData = t.env->NewStringUTF(purchaseOriginalData.c_str());

		t.env->CallStaticVoidMethod(t.classID, t.methodID, jSku, jItemType, jSignature, jPurchaseOriginalData);

		t.env->DeleteLocalRef(jSku);
		t.env->DeleteLocalRef(jItemType);
		t.env->DeleteLocalRef(jSignature);
		t.env->DeleteLocalRef(jPurchaseOriginalData);

		t.env->DeleteLocalRef(t.classID);
	}
}

void queryGoogleInventory(std::vector<std::string> &skus)
{
	if (skus.size() == 0)
		return;

	std::string strSku;

	for (int i = 0; i < skus.size(); i++)
	{
		strSku = strSku + " " + skus[i];
	}
	strSku = strSku.substr(1, strSku.length());

	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "queryGoogleInventory",
			"(Ljava/lang/String;)V"))
	{
		jstring jSku = t.env->NewStringUTF(strSku.c_str());

		t.env->CallStaticVoidMethod(t.classID, t.methodID, jSku);

		t.env->DeleteLocalRef(jSku);

		t.env->DeleteLocalRef(t.classID);
	}
}

void doFacebookSharePhoto(std::string &imageUri, std::string &caption,std::vector<std::string> &peopleIds, std::string &contentUri, std::string &refUrl)
{
	std::string strPeopleIds;

	for (int i = 0;i< peopleIds.size();i++)
	{
		strPeopleIds = strPeopleIds+ " "+strPeopleIds[i];
	}
	strPeopleIds = strPeopleIds.substr(1,strPeopleIds.length());

	JniMethodInfo t;
		if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doFacebookSharePhoto",
				"(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V"))
		{
			jstring jimageUri = t.env->NewStringUTF(imageUri.c_str());
			jstring jcaption = t.env->NewStringUTF(caption.c_str());
			jstring jstrPeopleIds = t.env->NewStringUTF(strPeopleIds.c_str());
			jstring jcontentUri = t.env->NewStringUTF(contentUri.c_str());
			jstring jrefUrl = t.env->NewStringUTF(refUrl.c_str());

			t.env->CallStaticVoidMethod(t.classID, t.methodID, jimageUri,jcaption,jstrPeopleIds,jcontentUri,jrefUrl);

			t.env->DeleteLocalRef(jimageUri);
			t.env->DeleteLocalRef(jcaption);
			t.env->DeleteLocalRef(jstrPeopleIds);
			t.env->DeleteLocalRef(jcontentUri);
			t.env->DeleteLocalRef(jrefUrl);

		    t.env->DeleteLocalRef(t.classID);

		}

}

void doFacebookShareLink(std::string &url, std::string &title,std::string &contentDescription, std::string &imageUrl, std::string &peopleIds)
{
	std::string strPeopleIds;

	for (int i = 0;i< peopleIds.size();i++)
	{
		strPeopleIds = strPeopleIds+" "+ strPeopleIds[i];
	}
	strPeopleIds = strPeopleIds.substr(1,strPeopleIds.length());

	JniMethodInfo t;
		if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doFacebookShareLink",
				"(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V"))
		{
			jstring jurl = t.env->NewStringUTF(url.c_str());
			jstring jtitle = t.env->NewStringUTF(title.c_str());
			jstring jcontentDescription = t.env->NewStringUTF(contentDescription.c_str());
			jstring jimageUrl = t.env->NewStringUTF(imageUrl.c_str());
			jstring jpeopleIds = t.env->NewStringUTF(strPeopleIds.c_str());

			t.env->CallStaticVoidMethod(t.classID, t.methodID, jurl,jtitle,jcontentDescription,jimageUrl,jpeopleIds);

			t.env->DeleteLocalRef(jurl);
			t.env->DeleteLocalRef(jtitle);
			t.env->DeleteLocalRef(jcontentDescription);
			t.env->DeleteLocalRef(jimageUrl);
			t.env->DeleteLocalRef(jpeopleIds);

		    t.env->DeleteLocalRef(t.classID);

		}

}

void doInviteFriends(std::string &applinkUrl,std::string &previewImageUrl)
{

	JniMethodInfo t;
		if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doInviteFriends",
				"(Ljava/lang/String;Ljava/lang/String;)V"))
		{
			jstring japplinkUrl = t.env->NewStringUTF(applinkUrl.c_str());
			jstring jpreviewImageUrl = t.env->NewStringUTF(previewImageUrl.c_str());

			t.env->CallStaticVoidMethod(t.classID, t.methodID, japplinkUrl,jpreviewImageUrl);

			t.env->DeleteLocalRef(japplinkUrl);
			t.env->DeleteLocalRef(jpreviewImageUrl);


		    t.env->DeleteLocalRef(t.classID);

		}
}

void doUnlockAchievement(std::string &achievementId)
{
	JniMethodInfo t;
			if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doUnlockAchievement",
					"(Ljava/lang/String;)V"))
			{
				jstring jachievementId = t.env->NewStringUTF(achievementId.c_str());

				t.env->CallStaticVoidMethod(t.classID, t.methodID, jachievementId);

				t.env->DeleteLocalRef(jachievementId);

			    t.env->DeleteLocalRef(t.classID);

			}
}

void  doIncrementAchievement(std::string &achievementId, int score)
{
	JniMethodInfo t;
			if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doIncrementAchievement",
					"(Ljava/lang/String;I)V"))
			{
				jstring jachievementId = t.env->NewStringUTF(achievementId.c_str());

				t.env->CallStaticVoidMethod(t.classID, t.methodID, jachievementId,score);

				t.env->DeleteLocalRef(jachievementId);

			    t.env->DeleteLocalRef(t.classID);

			}
}

void doShowAchievement()
{
	JniMethodInfo t;
			if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doShowAchievement",
					"()V"))
			{
				t.env->CallStaticVoidMethod(t.classID, t.methodID);

			    t.env->DeleteLocalRef(t.classID);
			}
}

void doUpdateScoreOnLeaderboard(std::string leaderboardId, int score)
{
	JniMethodInfo t;
			if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doUpdateScoreOnLeaderboard",
					"(Ljava/lang/String;I)V"))
			{
				jstring jleaderboardId = t.env->NewStringUTF(leaderboardId.c_str());

				t.env->CallStaticVoidMethod(t.classID, t.methodID, jleaderboardId,score);

				t.env->DeleteLocalRef(jleaderboardId);

			    t.env->DeleteLocalRef(t.classID);

			}
}

void doShowLeaderboard()
{
	JniMethodInfo t;
			if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doShowLeaderboard",
					"()V"))
			{
				t.env->CallStaticVoidMethod(t.classID, t.methodID);

			    t.env->DeleteLocalRef(t.classID);
			}
}

void doRequestAllFriends(std::string &height, std::string &width, std::string &limit)
{
	JniMethodInfo t;
			if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doRequestAllFriends",
					"(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V"))
			{
				jstring jheight = t.env->NewStringUTF(height.c_str());
				jstring jwidth = t.env->NewStringUTF(width.c_str());
				jstring jlimit = t.env->NewStringUTF(limit.c_str());

				t.env->CallStaticVoidMethod(t.classID, t.methodID, jheight, jwidth, jlimit);

				t.env->DeleteLocalRef(jheight);
				t.env->DeleteLocalRef(jwidth);
				t.env->DeleteLocalRef(jlimit);

			    t.env->DeleteLocalRef(t.classID);
			}
}

void doRequestAllFriendsNextPage()
{
	JniMethodInfo t;
			if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doRequestAllFriendsNextPage",
					"()V"))
			{
				t.env->CallStaticVoidMethod(t.classID, t.methodID);

			    t.env->DeleteLocalRef(t.classID);
			}
}

void doRequestAllFriendsPrevPage()
{
	JniMethodInfo t;
			if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doRequestAllFriendsPrevPage",
					"()V"))
			{
				t.env->CallStaticVoidMethod(t.classID, t.methodID);

			    t.env->DeleteLocalRef(t.classID);
			}
}

void doRequestFriendsInApp(std::string &limit)
{
	JniMethodInfo t;
			if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doRequestFriendsInApp",
					"(Ljava/lang/String;)V"))
			{
				jstring jlimit = t.env->NewStringUTF(limit.c_str());

				t.env->CallStaticVoidMethod(t.classID, t.methodID,jlimit);

				t.env->DeleteLocalRef(jlimit);
			    t.env->DeleteLocalRef(t.classID);
			}
}

void doRequestFriendsInAppNextPage()
{
	JniMethodInfo t;
			if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doRequestFriendsInAppNextPage",
					"()V"))
			{
				t.env->CallStaticVoidMethod(t.classID, t.methodID);

			    t.env->DeleteLocalRef(t.classID);
			}
}

void doRequestFriendsInAppPrevPage()
{
	JniMethodInfo t;
			if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doRequestFriendsInAppPrevPage",
					"()V"))
			{
				t.env->CallStaticVoidMethod(t.classID, t.methodID);

			    t.env->DeleteLocalRef(t.classID);
			}
}



