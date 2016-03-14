#include "JniHelper.h"
#include <android/log.h>
#include <string.h>
#include <pthread.h>

// 此文件在没有使用Cocos2d-X时使用

#define  LOG_TAG    "JniHelper"
#define  LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,__VA_ARGS__)


jint JNI_OnLoad(JavaVM *vm, void *reserved)
{
    JniHelper::setJavaVM(vm);

    return JNI_VERSION_1_4;
}

static pthread_key_t g_key;

JavaVM* JniHelper::_psJavaVM = NULL;

JavaVM* JniHelper::getJavaVM() {
    pthread_t thisthread = pthread_self();
    LOGD("JniHelper::getJavaVM(), pthread_self() = %ld", thisthread);
    return _psJavaVM;
}

void JniHelper::setJavaVM(JavaVM *javaVM) {
    pthread_t thisthread = pthread_self();
    LOGD("JniHelper::setJavaVM(%p), pthread_self() = %ld", javaVM, thisthread);
    _psJavaVM = javaVM;

    pthread_key_create(&g_key, NULL);
}

JNIEnv* JniHelper::cacheEnv(JavaVM* jvm) {
    JNIEnv* _env = NULL;
    // get jni environment
    jint ret = jvm->GetEnv((void**)&_env, JNI_VERSION_1_4);
    
    switch (ret) {
    case JNI_OK :
        // Success!
        pthread_setspecific(g_key, _env);
        return _env;
            
    case JNI_EDETACHED :
        // Thread not attached
            
        // TODO : If calling AttachCurrentThread() on a native thread
        // must call DetachCurrentThread() in future.
        // see: http://developer.android.com/guide/practices/design/jni.html
            
        if (jvm->AttachCurrentThread(&_env, NULL) < 0)
            {
                LOGD("Failed to get the environment using AttachCurrentThread()");

                return NULL;
            } else {
            // Success : Attached and obtained JNIEnv!
            pthread_setspecific(g_key, _env);
            return _env;
        }
            
    case JNI_EVERSION :
        // Cannot recover from this error
        LOGD("JNI interface version 1.4 not supported");
    default :
        LOGD("Failed to get the environment using GetEnv()");
        return NULL;
    }
}

JNIEnv* JniHelper::getEnv() {
    JNIEnv *_env = (JNIEnv *)pthread_getspecific(g_key);
    if (_env == NULL)
        _env = JniHelper::cacheEnv(_psJavaVM);
    return _env;
}


bool JniHelper::getStaticMethodInfo(JniMethodInfo &methodinfo,
                                    const char *className, 
                                    const char *methodName,
                                    const char *paramCode) {
    if ((NULL == className) ||
        (NULL == methodName) ||
        (NULL == paramCode)) {
        return false;
    }

    JNIEnv *pEnv = JniHelper::getEnv();
    if (!pEnv) {
        LOGD("Failed to get JNIEnv");
        return false;
    }
        
    jclass classID = pEnv->FindClass(className);
    if (! classID) {
        LOGD("Failed to find class %s", className);
        return false;
    }

    jmethodID methodID = pEnv->GetStaticMethodID(classID, methodName, paramCode);
    if (! methodID) {
        LOGD("Failed to find static method id of %s", methodName);
        return false;
    }
        
    methodinfo.classID = classID;
    methodinfo.env = pEnv;
    methodinfo.methodID = methodID;
    return true;
}

bool JniHelper::getMethodInfo(JniMethodInfo &methodinfo,
                              const char *className,
                              const char *methodName,
                              const char *paramCode) {
    if ((NULL == className) ||
        (NULL == methodName) ||
        (NULL == paramCode)) {
        return false;
    }

    JNIEnv *pEnv = JniHelper::getEnv();
    if (!pEnv) {
        return false;
    }

    jclass classID = pEnv->FindClass(className);
    if (! classID) {
        LOGD("Failed to find class %s", className);
        pEnv->ExceptionClear();
        return false;
    }

    jmethodID methodID = pEnv->GetMethodID(classID, methodName, paramCode);
    if (! methodID) {
        LOGD("Failed to find method id of %s", methodName);
        pEnv->ExceptionClear();
        return false;
    }

    methodinfo.classID = classID;
    methodinfo.env = pEnv;
    methodinfo.methodID = methodID;

    return true;
}

std::string JniHelper::jstring2string(jstring jstr) {
    if (jstr == NULL) {
        return "";
    }
    
    JNIEnv *pEnv = JniHelper::getEnv();
    if (!pEnv) {
        return NULL;
    }

    const char* chars = pEnv->GetStringUTFChars(jstr, NULL);
    std::string ret(chars);
    pEnv->ReleaseStringUTFChars(jstr, chars);

    return ret;
}
