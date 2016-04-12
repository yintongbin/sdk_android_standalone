#ifndef _GAMESDK_H_
#define _GAMESDK_H_

#include <jni.h>
#include <string>
#include <vector>

void doAppsflyer_init(std::string &devkey);
void doAppsflyer_setUserId(std::string &userId);
void doAppsflyer_register();
void doAppsflyer_login();
void doAppsflyer_purchase(float &revenue, std::string &contentId,std::string &contentType, std::string &currency);
void doAppsflyer_levelUp(std::string &level,std::string &score);
void doAppsflyer_achievementUnlocked(std::string &description);
void doAppsflyer_tutorialComplete();
void doAppsflyer_share(std::string &description);
void doAppsflyer_invite();
void doAppsflyer_custom(std::string &afInAppEventType, std::string &content);

#endif
