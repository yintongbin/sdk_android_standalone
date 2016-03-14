#ifndef __ANDROID_JNI_HELPER_H__
#define __ANDROID_JNI_HELPER_H__

// 此文件在没有使用Cocos2d-X时使用

#include <jni.h>
#include <string>


typedef struct JniMethodInfo_
{
    JNIEnv *    env;
    jclass      classID;
    jmethodID   methodID;
} JniMethodInfo;

class JniHelper
{
public:
    static void setJavaVM(JavaVM *javaVM);
    static JavaVM* getJavaVM();
    static JNIEnv* getEnv();

    static bool getStaticMethodInfo(JniMethodInfo &methodinfo,
                                    const char *className,
                                    const char *methodName,
                                    const char *paramCode);
    static bool getMethodInfo(JniMethodInfo &methodinfo,
                              const char *className,
                              const char *methodName,
                              const char *paramCode);

    static std::string jstring2string(jstring str);

private:
    static JNIEnv* cacheEnv(JavaVM* jvm);

    static JavaVM* _psJavaVM;
};

#endif // __ANDROID_JNI_HELPER_H__
