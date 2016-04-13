#Seastar-Spg-Sdk接入文档
##配置流程:
###1.AndroidManifest.xml配置

####Google.Facebook 添加权限
    
     <!-- permission begin -->
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.GET_ACCOUNTS" />
    <uses-permission android:name="android.permission.GET_TASKS" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.RESTART_PACKAGES" />
    <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
    <uses-permission android:name="android.permission.USE_CREDENTIALS" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="com.android.vending.BILLING" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
       <!-- vungle ad permissions -->
    
####广告添加权限

    <receiver
    android:name="com.appsflyer.MultipleInstallBroadcastReceiver"
    android:exported="true">
     <intent-filter>
    <action android:name="com.android.vending.INSTALL_REFERRER" />
     </intent-filter>
    </receiver>
####application Activity配置添加
     <meta-data
    android:name="com.google.android.gms.games.APP_ID"
    android:value="@string/app_id" />
    <meta-data
    android:name="com.facebook.sdk.ApplicationId"
    android:value="@string/facebook_app_id" />
    <meta-data
    android:name="com.google.android.gms.version"
    android:value="@integer/google_play_services_version" />
    
    <activity
    android:name="com.facebook.FacebookActivity"
    android:configChanges="keyboard|keyboardHidden|screenLayout|screenSize|orientation"
    android:label="@string/app_name"
    android:theme="@android:style/Theme.Translucent.NoTitleBar" />
    
    <provider
    android:name="com.facebook.FacebookContentProvider"
    android:authorities="com.facebook.app.FacebookContentProvider597198010430029"
    android:exported="true" />
    
    
    <!-- This meta-data tag is required to use Google Play Services. -->
    <meta-data
    android:name="com.google.android.gms.version"
    android:value="@integer/google_play_services_version" />
    
    <!-- google ad activity -->
    <activity
    android:name="com.google.android.gms.ads.AdActivity"
    android:configChanges="keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize"
    android:theme="@android:style/Theme.Translucent" />
    
    <!-- fb ad activity -->
    <activity
    android:name="com.facebook.ads.InterstitialAdActivity"
    android:configChanges="keyboardHidden|orientation|screenSize" />
    
    <!-- vungle ad activity -->
    <activity
    android:name="com.vungle.publisher.FullScreenAdActivity"
    android:configChanges="keyboardHidden|orientation|screenSize"
    android:theme="@android:style/Theme.NoTitleBar.Fullscreen" />
    appsflys
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    
###2.string.xml配置
<resources>

    <string name="app_name">Demo</string>
    <string name="facebook_app_id">1511446565832047</string>
    <string name="app_id">952031205969</string>
</resources>

###3.项目依赖
#####seastar-spg-sdk依赖于facebook,google-play-service_lib.
#####seastar-ad依赖于google-play-service_lib.
#####您的项目依赖于seastar-spg-sdk，seastar-ad即可

###4.接口，方法，参数
#####seastar-spg-sdk
#####Java调用 com.seastar.spg.Interface.GameSdk.java

    //facebook初始化
    public static void doFbInit() 

    //google初始化
    public static void doGoogleInit() 

    //Facebook登录
    public static void doFbLogin() 

    //google登录
    public static void doGoogleLogin() 

    //facebook登出
    public static void doFbLogout() 

    //google登出
    public static void doGoogleLogout() 

    //google支付
    //sku 订单号
    public static void doGooglePay(String sku) 

    //google消费
    //sku订单号
    //itemType 支付类型
    //signature 支付前面
    //purchaseOriginalData 支付数据
    public static void doGoogleConsume(String sku, String itemType,
            String signature, String purchaseOriginalData) 

    // google查询购买后没有消费的商品
    //sku订单号
    public static void queryGoogleInventory(String skus) 

    //facebook分享图片
    //imageUrl 图片地址
    // caption 图片标题
    // peopleIds 分享到的好友id
    // contentUri 内容uri
    // refUrl 推荐网址
    public static void doFacebookSharePhoto(String imageUri, String caption,
    String peopleIds, String contentUri, String refUrl)

    //facebook分享链接
    // url 链接
    // title 链接内容的标题
    // contentDescription 描述内容
    // imageUrl 缩略图图像的URL
    // peopleIds 分享到的好友id
    public static void doFacebookShareLink(String url, String title,String contentDescription, String imageUrl, String peopleIds) 

    //facebook邀请好友
    //applinkUrl应用连接
    //previewImageUrl展示图片
    public static previewImageUrl展示图片void doInviteFriends(String applinkUrl, String previewImageUrl) 

    //google解锁成就
    //achievementId 成就ID
    public static void doUnlockAchievement(String achievementId)

    //google给成就加分
    //achievementId 成就ID
    //score 所加分数
    public static void doIncrementAchievement(String achievementId, int score) 

    //google排行版
    public static void doShowAchievement() 

    //google排行榜加分
    //leaderboardId 排行榜ID
    //score 所加分数
    public static void doUpdateScoreOnLeaderboard(String leaderboardId,
            int score) 

    //google展示排行榜
    public static void doShowLeaderboard() 

    //facebook获取所有好友
    //height头像高度
    //width 头像宽度
    //limit 好友所有数据
    public static void doRequestAllFriends(String height, String width,
            String limit) 

    //facebook获取好友下一页
    public static void doRequestAllFriendsNextPage() 

    //facebook获取好友 上一页
    public static void doRequestAllFriendsPrevPage() 

    //facebook获取本应用的好友
    //limit 好友所有数据
    public static void doRequestFriendsInApp(String limit) 

    //facebook获取本应用的好友下一页
    public static void doRequestFriendsInAppNextPage()

    //facebook获取本应用的好友下一页
    public static void doRequestFriendsInAppPrevPage()
###C++调用
    //facebook初始化
    void doFbInit();
    对应回调
    //flags返回状态
    JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSdk_onFbInitCb(JNIEnv *env, jclass obj, jint flags);

    //google初始化 
    void doGoogleInit();
    对应回调
    //flags返回状态
    JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSdk_onGoogleInitCb(JNIEnv *env, jclass obj, jint flags);

    //Facebook登录
    void doFbLogin();
    对应回调
    //flags 返回状态
    //user 用户信息
    JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSdk_onFbLoginCb(JNIEnv *env, jclass obj, jint flags, jstring user);

    //google登录
    void doGoogleLogin();
    对应回调
    //flags 返回状态
    //user 用户信息
    JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSdk_onGoogleLoginCb(JNIEnv *env, jclass obj, jint flags, jstring user);

    //facebook登出
    void doFbLogout();

    //google登出
    void doGoogleLogout();

    //google支付
    //sku订单号
    void doGooglePay(std::string &sku);
    对应回调
    //flags返回状态
    //sku订单号
    //itemType支付类型
    //googleOrdere Google订单号
    //signature支付签名
    //purchaseOriginalData支付数据
    JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSdk_onGooglePayCb(JNIEnv *env, jclass obj,jint flags, jstring sku, jstring itemType, jstring googleOrder, jstring signature, jstring purchaseOriginalData);

    //google消费
    //sku订单号
    //itemType支付类型 
    //signature支付签名
    //purchaseOriginalData支付数据
    void doGoogleConsume(std::string &sku, std::string &itemType, std::string &signature, std::string &purchaseOriginalData);
    对应回调
    //flags返回状态
    //sku订单号
    JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSdk_onGoogleConsumeCb(JNIEnv *env, jclass obj,jint flags, jstring sku);

    //google查询没有消费的商品
    //sku订单号
    void queryGoogleInventory(std::vector<std::string> &skus);
    对应回调
    //flags返回状态
    //sku订单号
    JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSdk_onQueryGoogleInventory(JNIEnv *env, jclass obj,jint flags, jstring skus);

    //facebook分享图片
    //imageUrl 图片地址
    // caption 图片标题
    // peopleIds 分享到的好友id
    // contentUri 内容uri
    // refUrl 推荐网址
    void doFacebookSharePhoto(std::string &imageUri, std::string &caption,std::vector<std::string> &peopleIds, std::string &contentUri, std::string &refUrl);
    对应回调
    //flags返回状态
    JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSdk_onFacebookSharePhoto(JNIEnv *env, jclass obj,jint flags);

    //facebook分享链接
    // url 链接
    // title 链接内容的标题
    // contentDescription 描述内容
    // imageUrl 缩略图图像的URL
    // peopleIds 分享到的好友id
    void doFacebookShareLink(std::string &url, std::string &title,std::string &contentDescription, std::string &imageUrl, std::vector<std::string> &peopleIds);
    对应回调
    //flags返回状态
    JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSdk_onFacebookShareLink(JNIEnv *env, jclass obj,jint flags);

    //Facebook邀请好友
    //applinkUrl应用连接
    //previewImageUrl展示图片
    void doInviteFriends(std::string &applinkUrl,std::string &previewImageUrl);
    对应回调
    //flags返回状态
    JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSdk_onInviteFriends(JNIEnv *env, jclass obj,jint flags);

    //google解锁成就
    //achievementId成就ID
    void doUnlockAchievement(std::string &achievementId);

    //google成就加分
    //achievementId 成就ID
    //score 所加分数
    void doIncrementAchievement(std::string &achievementId, int &score);

    //google显示排行
    void doShowAchievement();

    //排行榜加分
    //leaderboardId 排行榜ID
    //score 所加分数
    void doUpdateScoreOnLeaderboard(std::string &leaderboardId, int &score);

    //展示排行榜
    void doShowLeaderboard();

    //facebook获取好友列表
    //height头像高度
    //width 头像宽度
    //limit 条目
    void doRequestAllFriends(std::string &height, std::string &width, std::string &limit);
    对应回调
    //graph好友列表数据
    JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSdk_onRequestAllFriends(JNIEnv *env, jclass obj,jstring graph);

    //facebook好友列表下一页
    void doRequestAllFriendsNextPage();
    对应回调
    //graph好友列表数据
    JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSdk_onRequestAllFriendsNextPage(JNIEnv *env, jclass obj,jstring graph);

    //facebook好友列表上一页
    void doRequestAllFriendsPrevPage();
    对应回调
    //graph好友列表数据
    JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSdk_onRequestAllFriendsPrevPage(JNIEnv *env, jclass obj,jstring graph);

    //facebook获取本应用好友列表
    //limit条目
    void doRequestFriendsInApp(std::string &limit);
    对应回调
    //graph好友列表数据
    JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSdk_onRequestFriendsInApp(JNIEnv *env, jclass obj,jstring graph);

    //facebook本应用好友列表下一页
    void doRequestFriendsInAppNextPage();
    对应回调
    //graph好友列表数据
    JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSdk_onRequestFriendsInAppNextPage(JNIEnv *env, jclass obj,jstring graph);

    //facebook本应用好友列表上一页
    void doRequestFriendsInAppPrevPage();
    对应回调
    //graph好友列表数据
    JNIEXPORT void JNICALL Java_com_seastar_spg_Interface_GameSdk_onRequestFriendsInAppPrevPage(JNIEnv *env, jclass obj,jstring graph);
