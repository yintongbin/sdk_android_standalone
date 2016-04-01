package com.seastar.spg.Interface;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

import com.appsflyer.AFInAppEventParameterName;
import com.appsflyer.AFInAppEventType;
import com.appsflyer.AppsFlyerLib;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class Appsflyer {

	private static Handler handler;
	private static Activity activity;

	public static void init(final Activity activity) {
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 0:
					doAppsflyer_initImpl(msg);
					break;
				case 1:
					doAppsflyer_setUserIdImpl(msg);
					break;
				case 2:
					doAppsflyer_registerImpl();
					break;
				case 3:
					doAppsflyer_loginImpl();
					break;
				case 4:
					doAppsflyer_purchaseImpl(msg);
					break;
				case 5:
					doAppsflyer_levelUpImpl(msg);
					break;
				case 6:
					doAppsflyer_achievementUnlockedImpl(msg);
					break;
				case 7:
					doAppsflyer_tutorialCompleteImpl();
					break;
				case 8:
					doAppsflyer_shareImpl(msg);
					break;
				case 9:
					doAppsflyer_inviteImpl();
					break;
				case 10:
					doAppsflyer_customImpl(msg);
					break;
				default:
					super.handleMessage(msg);
					break;
				}
			}
		};
	}

	/**
	 * 初始化 必须在每个activity的onCreate中调用此方法
	 * 
	 * @param activity
	 * @param devkey
	 */
	public void doAppsflyer_init(String devkey) {

		Message msg = new Message();
		Bundle bundle = new Bundle();
		bundle.putString("devkey", devkey);
		msg.what = 0;
		msg.obj = bundle;
		handler.sendMessage(msg);
	}

	/**
	 * 设置玩家id
	 * 
	 * @param userId
	 */
	public void doAppsflyer_setUserId(String userId) {
		// AppsFlyerLib.setAppUserId(userId);
		Message msg = new Message();
		Bundle bundle = new Bundle();
		bundle.putString("userId", userId);
		msg.what = 1;
		msg.obj = bundle;
		handler.sendMessage(msg);
	}

	/**
	 * 注册
	 * 
	 * @param activity
	 */
	public void doAppsflyer_register() {
		// 预定义或者自定义的参数
		Message msg = new Message();
		msg.what = 2;
		handler.sendMessage(msg);
	}

	/**
	 * 登录
	 * 
	 * @param activity
	 */
	public void doAppsflyer_login() {
		Message msg = new Message();
		msg.what = 3;
		handler.sendMessage(msg);
	}

	/**
	 * 购买
	 * 
	 * @param activity
	 * @param revenue
	 * @param contentId
	 * @param contentType
	 * @param currency
	 */
	public void doAppsflyer_purchase(float revenue, String contentId,
			String contentType, String currency) {
		Message msg = new Message();
		Bundle bundle = new Bundle();
		bundle.putFloat("revenue", revenue);
		bundle.putString("contentId", contentId);
		bundle.putString("contentType", contentType);
		bundle.putString("currency", currency);
		msg.what = 4;
		msg.obj = bundle;
		handler.sendMessage(msg);
	}

	/**
	 * 游戏等级事件
	 * 
	 * @param activity
	 * @param level
	 *            等级
	 * @param score
	 *            分数
	 */
	public void doAppsflyer_levelUp(String level, String score) {
		Message msg = new Message();
		Bundle bundle = new Bundle();
		bundle.putString("level", level);
		bundle.putString("score", score);
		msg.what = 5;
		msg.obj = bundle;
		handler.sendMessage(msg);
	}

	/**
	 * 成就解锁
	 * 
	 * @param description
	 */
	public void doAppsflyer_achievementUnlocked(String description) {
		Message msg = new Message();
		Bundle bundle = new Bundle();
		bundle.putString("description", description);
		msg.what = 6;
		msg.obj = bundle;
		handler.sendMessage(msg);
	}

	/**
	 * 新手引导
	 */
	public void doAppsflyer_tutorialComplete() {
		Message msg = new Message();
		msg.what = 7;
		handler.sendMessage(msg);
	}

	/**
	 * 分享
	 * 
	 * @param description
	 */
	public void doAppsflyer_share(String description) {
		Message msg = new Message();
		Bundle bundle = new Bundle();
		bundle.putString("description", description);
		msg.what = 8;
		msg.obj = bundle;
		handler.sendMessage(msg);
	}

	/**
	 * 邀请
	 */
	public void doAppsflyer_invite() {
		Message msg = new Message();
		msg.what = 9;
		handler.sendMessage(msg);
	}

	/**
	 * 
	 * @param activity
	 * @param eventName
	 *            可以为任何字符串，不过appsflyer有推荐字符串
	 */
	public void doAppsflyer_custom(String afInAppEventType, String content) {
		// eventValue 为事件参数
		Message msg = new Message();
		Bundle bundle = new Bundle();
		bundle.putString("afInAppEventType", afInAppEventType);
		bundle.putString("content", content);
		msg.what = 10;
		msg.obj = bundle;
		handler.sendMessage(msg);

	}

	public static void doAppsflyer_initImpl(Message msg) {

		final Bundle bundle = (Bundle) msg.obj;
		AppsFlyerLib.getInstance().init(activity, bundle.getString("devkey"));
	}

	/**
	 * 设置玩家id
	 * 
	 * @param userId
	 */
	public static void doAppsflyer_setUserIdImpl(Message msg) {

		final Bundle bundle = (Bundle) msg.obj;
		AppsFlyerLib.getInstance()
				.setCustomerUserId(bundle.getString("userId"));
	}

	/**
	 * 注册
	 * 
	 * @param activity
	 */
	public static void doAppsflyer_registerImpl() {
		Map<String, Object> eventValues = new HashMap<String, Object>();
		// 预定义或者自定义的参数
		eventValues.put(AFInAppEventParameterName.REGSITRATION_METHOD, "");
		AppsFlyerLib.getInstance().trackEvent(activity.getApplicationContext(),
				AFInAppEventType.COMPLETE_REGISTRATION, eventValues);
	}

	/**
	 * 登录
	 * 
	 * @param activity
	 */
	public static void doAppsflyer_loginImpl() {
		Map<String, Object> eventValues = new HashMap<String, Object>();
		AppsFlyerLib.getInstance().trackEvent(activity, AFInAppEventType.LOGIN,
				eventValues);
	}

	/**
	 * 购买
	 * 
	 * @param activity
	 * @param revenue
	 * @param contentId
	 * @param contentType
	 * @param currency
	 */
	public static void doAppsflyer_purchaseImpl(Message msg) {
		final Bundle bundle = (Bundle) msg.obj;

		Map<String, Object> eventValue = new HashMap<String, Object>();
		eventValue.put(AFInAppEventParameterName.REVENUE,
				bundle.getString("revenue"));
		eventValue.put(AFInAppEventParameterName.CONTENT_ID,
				bundle.getString("contentId"));
		eventValue.put(AFInAppEventParameterName.CONTENT_TYPE,
				bundle.getString("contentType"));
		eventValue.put(AFInAppEventParameterName.CURRENCY,
				bundle.getString("currency"));

		AppsFlyerLib.getInstance().trackEvent(activity.getApplicationContext(),
				AFInAppEventType.PURCHASE, eventValue);
	}

	/**
	 * 游戏等级事件
	 * 
	 * @param activity
	 * @param level
	 *            等级
	 * @param score
	 *            分数
	 */
	public static void doAppsflyer_levelUpImpl(Message msg) {
		final Bundle bundle = (Bundle) msg.obj;

		Map<String, Object> eventValue = new HashMap<String, Object>();
		eventValue.put(AFInAppEventParameterName.LEVEL,
				bundle.getString("level"));
		eventValue.put(AFInAppEventParameterName.SCORE,
				bundle.getString("score"));
		AppsFlyerLib.getInstance().trackEvent(activity.getApplicationContext(),
				AFInAppEventType.LEVEL_ACHIEVED, eventValue);
	}

	/**
	 * 成就解锁
	 * 
	 * @param description
	 */
	public static void doAppsflyer_achievementUnlockedImpl(Message msg) {
		final Bundle bundle = (Bundle) msg.obj;
		Map<String, Object> eventValue = new HashMap<String, Object>();
		eventValue.put(AFInAppEventParameterName.DESCRIPTION,
				bundle.getString("description"));
		AppsFlyerLib.getInstance().trackEvent(activity.getApplicationContext(),
				AFInAppEventType.ACHIEVEMENT_UNLOCKED, eventValue);
	}

	/**
	 * 新手引导
	 */
	public static void doAppsflyer_tutorialCompleteImpl() {
		Map<String, Object> eventValue = new HashMap<String, Object>();
		AppsFlyerLib.getInstance().trackEvent(activity.getApplicationContext(),
				AFInAppEventType.TUTORIAL_COMPLETION, eventValue);
	}

	/**
	 * 分享
	 * 
	 * @param description
	 */
	public static void doAppsflyer_shareImpl(Message msg) {
		final Bundle bundle = (Bundle) msg.obj;
		Map<String, Object> eventValue = new HashMap<String, Object>();
		eventValue.put(AFInAppEventParameterName.DESCRIPTION,
				bundle.getString("description"));
		AppsFlyerLib.getInstance().trackEvent(activity.getApplicationContext(),
				AFInAppEventType.SHARE, eventValue);
	}

	/**
	 * 邀请
	 */
	public static void doAppsflyer_inviteImpl() {
		Map<String, Object> eventValue = new HashMap<String, Object>();
		AppsFlyerLib.getInstance().trackEvent(activity.getApplicationContext(),
				AFInAppEventType.INVITE, eventValue);
	}

	/**
	 * 
	 * @param activity
	 * @param eventName
	 *            可以为任何字符串，不过appsflyer有推荐字符串
	 */
	public static void doAppsflyer_customImpl(Message msg) {
		// eventValue 为事件参数
		final Bundle bundle = (Bundle) msg.obj;
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(bundle.getString("content"));

			Iterator<String> keyIter = jsonObject.keys();
			String key;
			Object value;
			Map<String, Object> eventValue = new HashMap<String, Object>();

			while (keyIter.hasNext()) {
				key = keyIter.next();
				value = jsonObject.get(key);
				eventValue.put(key, value);
			}
			AppsFlyerLib.getInstance().trackEvent(activity,
					bundle.getString("afInAppEventType"), eventValue);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	
}
