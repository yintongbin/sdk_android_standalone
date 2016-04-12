#include <jni.h>
#include <android/log.h>

#include "rapidjson/document.h"
#include "rapidjson/prettywriter.h"

#include "JniHelper.h"
#include "APPSFLYER.h"

#define JAVA_CLASS "com/seastar/spg/Interface/Appsflyer"

void doAppsflyer_init(std::string &devkey) {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doAppsflyer_init",
			"(Ljava/lang/String;)V")) {
		jstring jdevkey = t.env->NewStringUTF(devkey.c_str());
		t.env->CallStaticVoidMethod(t.classID, t.methodID, jdevkey);
		t.env->DeleteLocalRef(jdevkey);
		t.env->DeleteLocalRef(t.classID);
	}
}
void doAppsflyer_setUserId(std::string &userId) {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doAppsflyer_setUserId",
			"(Ljava/lang/String;)V")) {
		jstring juserId = t.env->NewStringUTF(userId.c_str());
		t.env->CallStaticVoidMethod(t.classID, t.methodID, juserId);
		t.env->DeleteLocalRef(juserId);
		t.env->DeleteLocalRef(t.classID);
	}
}
void doAppsflyer_register() {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doAppsflyer_register",
			"()V")) {
		t.env->CallStaticVoidMethod(t.classID, t.methodID);
		t.env->DeleteLocalRef(t.classID);
	}
}
void doAppsflyer_login() {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doAppsflyer_login",
			"()V")) {
		t.env->CallStaticVoidMethod(t.classID, t.methodID);
		t.env->DeleteLocalRef(t.classID);
	}
}
void doAppsflyer_purchase(float &revenue, std::string &contentId,
		std::string &contentType, std::string &currency) {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doAppsflyer_purchase",
			"(FLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V")) {
		jstring jcontentId = t.env->NewStringUTF(contentId.c_str());
		jstring jcontentType = t.env->NewStringUTF(contentType.c_str());
		jstring jcurrency = t.env->NewStringUTF(currency.c_str());
		t.env->CallStaticVoidMethod(t.classID, t.methodID, revenue, jcontentId,
				jcontentType, jcurrency);
		t.env->DeleteLocalRef(jcontentId);
		t.env->DeleteLocalRef(jcontentType);
		t.env->DeleteLocalRef(jcurrency);
		t.env->DeleteLocalRef(t.classID);
	}
}
void doAppsflyer_levelUp(std::string &level, std::string &score) {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doAppsflyer_levelUp",
			"(Ljava/lang/String;Ljava/lang/String;)V")) {
		jstring jlevel = t.env->NewStringUTF(level.c_str());
		jstring jscore = t.env->NewStringUTF(score.c_str());
		t.env->CallStaticVoidMethod(t.classID, t.methodID, jlevel, jscore);
		t.env->DeleteLocalRef(jlevel);
		t.env->DeleteLocalRef(jscore);
		t.env->DeleteLocalRef(t.classID);
	}
}
void doAppsflyer_achievementUnlocked(std::string &description) {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doAppsflyer_achievementUnlocked",
			"(Ljava/lang/String;)V")) {
		jstring jdescription = t.env->NewStringUTF(description.c_str());
		t.env->CallStaticVoidMethod(t.classID, t.methodID, jdescription);
		t.env->DeleteLocalRef(jdescription);
		t.env->DeleteLocalRef(t.classID);
	}
}
void doAppsflyer_tutorialComplete() {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doAppsflyer_tutorialComplete",
			"()V")) {
		t.env->CallStaticVoidMethod(t.classID, t.methodID);
		t.env->DeleteLocalRef(t.classID);
	}
}
void doAppsflyer_share(std::string &description) {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doAppsflyer_share",
			"(Ljava/lang/String;)V")) {
		jstring jdescription = t.env->NewStringUTF(description.c_str());
		t.env->CallStaticVoidMethod(t.classID, t.methodID, jdescription);
		t.env->DeleteLocalRef(jdescription);
		t.env->DeleteLocalRef(t.classID);
	}
}
void doAppsflyer_invite() {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doAppsflyer_invite",
			"()V")) {
		t.env->CallStaticVoidMethod(t.classID, t.methodID);
		t.env->DeleteLocalRef(t.classID);
	}
}
void doAppsflyer_custom(std::string &afInAppEventType, std::string &content) {
	JniMethodInfo t;
	if (JniHelper::getStaticMethodInfo(t, JAVA_CLASS, "doAppsflyer_share",
			"(Ljava/lang/String;Ljava/lang/String;)V")) {
		jstring jafInAppEventType = t.env->NewStringUTF(afInAppEventType.c_str());
		jstring jcontent = t.env->NewStringUTF(content.c_str());
		t.env->CallStaticVoidMethod(t.classID, t.methodID, jafInAppEventType,jcontent);
		t.env->DeleteLocalRef(jafInAppEventType);
		t.env->DeleteLocalRef(jcontent);
		t.env->DeleteLocalRef(t.classID);
	}
}

