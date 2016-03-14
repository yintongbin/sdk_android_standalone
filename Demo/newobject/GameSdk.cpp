#include <jni.h>
#include <string>
#include <vector>

#include "rapidjson/document.h"
#include "rapidjson/prettywriter.h"

#include "JniHelper.h"
#include "GameSdk.h"

#define JAVA_CLASS "com/seastar/spg/Interface/GameSDK"


void Java_com_seastar_spg_Interface_GameSDK_onFbInitCb(JNIEnv *env, jclass obj, jint flags)
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

void Java_com_seastar_spg_Interface_GameSDK_onGoogleInitCb(JNIEnv *env, jclass obj, jint flags)
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

void Java_com_seastar_spg_Interface_GameSDK_onFbLoginCb(JNIEnv *env, jclass obj, jint flags, jstring user)
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
		// do login failure proc
	}

}

void Java_com_seastar_spg_Interface_GameSDK_onGoogleLoginCb(JNIEnv *env, jclass obj, jint flags, jstring user)
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

void Java_com_seastar_spg_Interface_GameSDK_onGooglePayCb(JNIEnv *env, jclass obj, jint flags, jstring sku,
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

void Java_com_seastar_spg_Interface_GameSDK_onGoogleConsumeCb(JNIEnv *env, jclass obj, jint flags, jstring sku)
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

void Java_com_seastar_spg_Interface_GameSDK_onQueryGoogleInventory(JNIEnv *env, jclass obj,
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
			std::string priceCurrencyCode = value[i]["priceCurrencyCode"].GetString();
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

