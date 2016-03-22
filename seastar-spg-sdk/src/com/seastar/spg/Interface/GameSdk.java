package com.seastar.spg.Interface;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.seastar.spg.sdk.*;
import com.seastar.spg.sdk.Facebook.FacebookUserModel;
import com.seastar.spg.sdk.Facebook.OnInviteCallBack;
import com.seastar.spg.sdk.Facebook.OnPhotoShareCallBack;
import com.seastar.spg.sdk.Google.GoogleSkuModel;
import com.seastar.spg.sdk.Google.GoogleUserModel;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class GameSdk {

	private static Facebook fb = new Facebook();
	private static Google google = new Google();
	private static Handler handler;

	public static void setCurActivity(Activity activity) {
		fb.setCurActivity(activity);
		google.setCurActivity(activity);
	}

	public static void init() {
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 0:
					doFacebookInitImpl();
					break;
				case 1:
					doGoogleInitImpl();
					break;
				case 2:
					doFacebookLoginImpl();
					break;
				case 3:
					doGoogleLoginImpl();
					break;
				case 4:
					doFacebookLogoutImpl();
					break;
				case 5:
					doGoogleLogoutImpl();
					break;
				case 6:
					doGooglePayImpl(msg);
					break;
				case 7:
					doGoogleConsumeImpl(msg);
					break;
				case 8:
					queryGoogleInventoryImpl(msg);
					break;
				case 9:
					doFacebookSharePhotoImpl(msg);
					break;
				case 10:
					doFacebookShareLinkImpl(msg);
					break;
				case 11:
					doInviteFriendsImpl(msg);
					break;
				case 12:
					doUnlockAchievementImpl(msg);
					break;
				case 13:
					doIncrementAchievementImpl(msg);
					break;
				case 14:
					doShowAchievementImpl();
					break;
				case 15:
					doUpdateScoreOnLeaderboard(msg);
					break;
				case 16:
					doShowLeaderboardImpl();
					break;
				case 17:
					doRequestAllFriendsImpl(msg);
					break;
				case 18:
					doRequestAllFriendsNextPageImpl();
					break;
				case 19:
					doRequestAllFriendsPrevPageImpl();
					break;
				case 20:
					doRequestFriendsInAppImpl(msg);
					break;
				case 21:
					doRequestFriendsInAppNextPageImpl();
					break;
				case 22:
					doRequestFriendsInAppPrevPageImpl();
					break;
				default:
					super.handleMessage(msg);
					break;
				}
			}
		};
	}
	
	public static void doFbInit() {
		handler.sendEmptyMessage(0);
	}

	public static void doGoogleInit() {
		handler.sendEmptyMessage(1);
	}

	public static void doFbLogin() {
		handler.sendEmptyMessage(2);
	}

	public static void doGoogleLogin() {
		handler.sendEmptyMessage(3);
	}

	public static void doFbLogout() {
		handler.sendEmptyMessage(4);
	}

	public static void doGoogleLogout() {
		handler.sendEmptyMessage(5);
	}

	public static void doGooglePay(String sku) {
		Message msg = new Message();
		msg.what = 6;
		msg.obj = sku;
		handler.sendMessage(msg);
	}

	public static void doGoogleConsume(String sku, String itemType,
			String signature, String purchaseOriginalData) {
		Bundle bundle = new Bundle();
		bundle.putString("sku", sku);
		bundle.putString("itemtype", itemType);
		bundle.putString("signature", signature);
		bundle.putString("purchaseOriginalData", purchaseOriginalData);

		Message msg = new Message();
		msg.what = 7;
		msg.obj = bundle;
		handler.sendMessage(msg);
	}

	public static void queryGoogleInventory(String skus) {
		Message msg = new Message();
		msg.what = 8;
		msg.obj = skus;
		handler.sendMessage(msg);
	}

	public static void doFacebookSharePhoto(String imageUri, String caption,
			String peopleIds, String contentUri, String refUrl) {
		Message msg = new Message();
		Bundle bundle = new Bundle();
		bundle.putString("imageUri", imageUri);
		bundle.putString("caption", caption);
		bundle.putString("peopleIds", peopleIds);
		bundle.putString("contentUri", contentUri);
		msg.what = 9;
		msg.obj = bundle;
		handler.sendMessage(msg);
	}

	public static void doFacebookShareLink(String url, String title,
			String contentDescription, String imageUrl, String peopleIds) {
		Message msg = new Message();
		Bundle bundle = new Bundle();
		bundle.putString("url", url);
		bundle.putString("title", title);
		bundle.putString("contentDescription", contentDescription);
		bundle.putString("imageUrl", imageUrl);
		bundle.putString("peopleIds", peopleIds);
		msg.what = 10;
		msg.obj = bundle;
		handler.sendMessage(msg);
	}

	public static void doInviteFriends(String applinkUrl, String previewImageUrl) {
		Message msg = new Message();
		Bundle bundle = new Bundle();
		bundle.putString("applinkUrl", applinkUrl);
		bundle.putString("previewImageUrl", previewImageUrl);
		msg.what = 11;
		msg.obj = bundle;
		handler.sendMessage(msg);
	}

	public static void doUnlockAchievement(String achievementId) {
		Message msg = new Message();
		Bundle bundle = new Bundle();
		bundle.putString("achievementId", achievementId);
		msg.what = 12;
		msg.obj = bundle;
		handler.sendMessage(msg);
	}

	public static void doIncrementAchievement(String achievementId, int score) {
		Message msg = new Message();
		Bundle bundle = new Bundle();
		bundle.putString("achievementId", achievementId);
		bundle.putInt("score", score);
		msg.what = 13;
		msg.obj = bundle;
		handler.sendMessage(msg);
	}

	public static void doShowAchievement() {
		Message msg = new Message();

		msg.what = 14;
		handler.sendMessage(msg);
	}

	public static void doUpdateScoreOnLeaderboard(String leaderboardId,
			int score) {
		Message msg = new Message();
		Bundle bundle = new Bundle();
		bundle.putString("leaderboardId", leaderboardId);
		bundle.putInt("score", score);

		msg.what = 15;
		msg.obj = bundle;
		handler.sendMessage(msg);
	}

	public static void doShowLeaderboard() {
		Message msg = new Message();
		msg.what = 16;
		handler.sendMessage(msg);
	}
	
	public static void doRequestAllFriends(String height, String width, String limit)
	{
		Message msg = new Message();
		Bundle bundle = new Bundle();
		bundle.putString("height", height);
		bundle.putString("width", width);
		bundle.putString("limit", limit);
		msg.what = 17;
		handler.sendMessage(msg);
	}
	
	public static void doRequestAllFriendsNextPage()
	{
		Message msg = new Message();
		msg.what = 18;
		handler.sendMessage(msg);
	}
	
	public static void doRequestAllFriendsPrevPage()
	{
		Message msg = new Message();
		msg.what = 19;
		handler.sendMessage(msg);
	}

	public static void doRequestFriendsInApp(String limit)
	{
		Message msg = new Message();
		Bundle bundle = new Bundle();
		bundle.putString("limit", limit);
		msg.what = 20;
		handler.sendMessage(msg); 
	}
	
	public static void doRequestFriendsInAppNextPage(String graph)
	{
		Message msg = new Message();
		msg.what = 21;
		handler.sendMessage(msg);
	}

	public static void doRequestFriendsInAppPrevPage(String graph)
	{
		Message msg = new Message();
		msg.what = 22;
		handler.sendMessage(msg);
	}

	private static void doFacebookInitImpl() {
		fb.init(new Facebook.OnInitCallBack() {

			@Override
			public void onInitCallBack(boolean isInit) {
				onFbInitCb(isInit ? 1 : 0);
			}
		});
	}

	private static void doGoogleInitImpl() {
		google.init(new Google.OnInitCallBack() {

			@Override
			public void onInitCallBack(boolean isInit) {
				onGoogleInitCb(isInit ? 1 : 0);
			}
		});
	}

	private static void doFacebookLoginImpl() {
		fb.login(new Facebook.OnLoginCallBack() {

			@Override
			public void onLoginCallBack(boolean isLogin, FacebookUserModel user) {
				// TODO 自动生成的方法存根

				if (isLogin) {
					try {
						JSONObject obj = new JSONObject();
						obj.put("id", user.id);
						obj.put("name", user.name);
						obj.put("picture", user.picture);
						obj.put("email", user.email);
						obj.put("first_name", user.first_name);
						obj.put("last_name", user.last_name);
						obj.put("middle_name", user.middle_name);
						obj.put("gender", user.gender);
						obj.put("location", user.location);

						// onFbLoginCb(1, obj.toString());
					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else {
					onFbLoginCb(0, "");
				}
			}
		});
	}

	private static void doGoogleLoginImpl() {
		google.login(new Google.OnLoginCallBack() {

			@Override
			public void onLoginCallBack(boolean success,
					GoogleUserModel googleUserModel) {
				// TODO 自动生成的方法存根
				if (success) {
					try {
						
						JSONObject obj = new JSONObject();
						obj.put("id", googleUserModel.id);
						obj.put("ageRange", googleUserModel.ageRange);
						obj.put("birthday", googleUserModel.birthday);
						obj.put("currentLocation",
								googleUserModel.currentLocation);
						obj.put("displayName", googleUserModel.displayName);
						obj.put("image", googleUserModel.image);
						obj.put("language", googleUserModel.language);
						obj.put("name", googleUserModel.name);
						obj.put("nickName", googleUserModel.nickName);
						obj.put("url", googleUserModel.url);
						Log.d("googleLoginimpl",obj.toString() );
						onGoogleLoginCb(1, obj.toString());
					} catch (JSONException e) {
						e.printStackTrace();
					}

				} else {
					onGoogleLoginCb(0, "");
				}
			}
		});
	}

	private static void doFacebookLogoutImpl() {
		fb.logout();
	}

	private static void doGoogleLogoutImpl() {
		google.logout();
	}

	private static void doGooglePayImpl(Message msg) {
		google.pay(String.valueOf(msg.obj), new Google.OnPayCallBack() {

			@Override
			public void onPayCallBack(boolean success, String sku,
					String itemType, String googleOrder, String signature,
					String purchaseOriginalData) {
				// TODO 自动生成的方法存根
				onGooglePayCb(success ? 1 : 0, sku, itemType, googleOrder,
						signature, purchaseOriginalData);
			}
		});
	}

	private static void doGoogleConsumeImpl(Message msg) {
		Bundle bundle = (Bundle) msg.obj;
		google.consume(bundle.getString("sku"), bundle.getString("itemtype"),
				bundle.getString("signature"),
				bundle.getString("purchaseOriginalData"),
				new Google.OnConsumeCallBack() {

					@Override
					public void onConsumeCallBack(boolean success, String sku) {
						// TODO 自动生成的方法存根
						onGoogleConsumeCb(success ? 1 : 0, sku);

					}
				});
	}

	private static void queryGoogleInventoryImpl(Message msg) {
		String skus = String.valueOf(msg.obj);

		List<String> skusList = new ArrayList<>();
		String[] skusArr = skus.split(" ");
		for (int i = 0; i < skusArr.length; i++) {
			skusList.add(skusArr[i]);
		}

		google.queryInventoryAsync(skusList,
				new Google.OnQueryInventoryCallBack() {

					@Override
					public void onQueryInventoryCallBack(boolean success,
							List<GoogleSkuModel> googleSkuModels) {
						if (success) {
							try {

								JSONObject obj = new JSONObject();
								JSONArray arr = new JSONArray();
								obj.put("skus", arr);

								for (GoogleSkuModel sku : googleSkuModels) {
									JSONObject s = new JSONObject();
									s.put("description", sku.description);
									s.put("price", sku.price);
									s.put("sku", sku.sku);
									s.put("title", sku.title);
									s.put("type", sku.type);
									s.put("priceAmountMicros",
											sku.priceAmountMicros);
									s.put("priceCurrencyCode",
											sku.priceCurrencyCode);
									s.put("googleOrder", sku.googleOrder);
									s.put("signature", sku.signature);
									s.put("purchaseOriginalData",
											sku.purchaseOriginalData);

									arr.put(s);
								}

								onQueryGoogleInventory(1, obj.toString());
							} catch (JSONException e) {
								e.printStackTrace();
							}

						} else {
							onQueryGoogleInventory(0, "");
						}
					}
				});

	}

	private static void doFacebookSharePhotoImpl(Message msg) {
		final Bundle bundle = (Bundle) msg.obj;
		ArrayList<String> peopleIds = new ArrayList<>();

		if (bundle.getString("peopleIds") != "") {
			String[] array = bundle.getString("peopleIds").split(" ");

			for (int i = 0; i < array.length; i++) {
				peopleIds.add(array[i]);
			}
		} else {
			peopleIds = null;
		}
		fb.sharePhotoByUri(bundle.getString("imageUri"),
				bundle.getString("caption"), peopleIds,
				bundle.getString("contentUri"), bundle.getString("refUrl"),
				new Facebook.OnPhotoShareCallBack() {

					@Override
					public void onPhotoShareCallBack(boolean success) {
						onFacebookShareLink(success ? 1 : 0);

					}
				});
	}

	private static void doFacebookShareLinkImpl(Message msg) {
		final Bundle bundle = (Bundle) msg.obj;
		ArrayList<String> peopleIds = new ArrayList<>();

		if (bundle.getString("peopleIds") != "") {
			String[] array = bundle.getString("peopleIds").split(" ");

			for (int i = 0; i < array.length; i++) {
				peopleIds.add(array[i]);
			}
		} else {
			peopleIds = null;
		}

		fb.shareUrl(bundle.getString("url"), bundle.getString("title"),
				bundle.getString("contentDescription"),
				bundle.getString("imageUrl"), peopleIds,
				new Facebook.OnUrlShareCallBack() {

					@Override
					public void onUrlShareCallBack(boolean success) {
						onFacebookShareLink(success ? 1 : 0);

					}
				});

	}

	private static void doInviteFriendsImpl(Message msg) {
		final Bundle bundle = (Bundle) msg.obj;

		fb.inviteFriends(bundle.getString("applinkUrl"),
				bundle.getString("previewImageUrl"),
				new Facebook.OnInviteCallBack() {

					@Override
					public void onInviteCallBack(boolean success) {
						onInviteFriends(success ? 1 : 0);
					}
				});

	}

	private static void doUnlockAchievementImpl(Message msg) {
		final Bundle bundle = (Bundle) msg.obj;
		google.unlockAchievement(bundle.getString("achievementId"));
		onUnlockAchievement(bundle.getString("achievementId"));
	}

	private static void doIncrementAchievementImpl(Message msg) {
		final Bundle bundle = (Bundle) msg.obj;
		google.incrementAchievement(bundle.getString("achievementId"),
				bundle.getShort("score"));
		onIncrementAchievement(bundle.getString("achievementId"),
				bundle.getShort("score"));
	}

	private static void doShowAchievementImpl() {
		google.showAchievement();
		onShowAchievement();
	}

	private static void doUpdateScoreOnLeaderboard(Message msg) {
		final Bundle bundle = (Bundle) msg.obj;
		google.updateScoreOnLeaderboard(bundle.getString("leaderboardId"),
				Integer.parseInt(bundle.getString("score")));
		onUpdateScoreOnLeaderboard(bundle.getString("leaderboardId"),
				Integer.parseInt(bundle.getString("score")));
	}

	private static void doShowLeaderboardImpl() {
		google.showAchievement();
		onShowLeaderboard();
	}

	private static void doRequestAllFriendsImpl(Message msg) {
		final Bundle bundle = (Bundle) msg.obj;
		fb.requestAllFriends(bundle.getString("height"),
				bundle.getString("width"), bundle.getString("limit"),
				new Facebook.OnAllFriendsCallBack() {

					@Override
					public void onAllFriendsCallBack(String graph) {
						// TODO 自动生成的方法存根
						onRequestAllFriends(graph);
					}
				});
	}

	private static void doRequestAllFriendsNextPageImpl() {

		fb.requestAllFriendsNextPage(new Facebook.OnAllFriendsCallBack() {

			@Override
			public void onAllFriendsCallBack(String graph) {
				// TODO 自动生成的方法存根
				onRequestAllFriendsNextPage(graph);
			}
		});
	}

	private static void doRequestAllFriendsPrevPageImpl() {
		fb.requestAllFriendsPrevPage(new Facebook.OnAllFriendsCallBack() {

			@Override
			public void onAllFriendsCallBack(String graph) {
				// TODO 自动生成的方法存根
				onRequestAllFriendsPrevPage(graph);
			}
		});
	}

	private static void doRequestFriendsInAppImpl(Message msg) {
		final Bundle bundle = (Bundle) msg.obj;
		fb.requestFriendsInApp(bundle.getString("limit"),
				new Facebook.OnFriendsInAppCallBack() {

					@Override
					public void onFriendsInAppCallBack(String graph) {
						// TODO 自动生成的方法存根
						onFriendsInAppCallBack(graph);	
					}
				});
	}

	private static void doRequestFriendsInAppNextPageImpl() {
		fb.requestFriendsInAppNextPage(new Facebook.OnFriendsInAppCallBack() {

			@Override
			public void onFriendsInAppCallBack(String graph) {
				// TODO 自动生成的方法存根
				doRequestFriendsInAppNextPage(graph);
			}
		});
	}

	private static void doRequestFriendsInAppPrevPageImpl() {
		fb.requestFriendsInAppPrevPage(new Facebook.OnFriendsInAppCallBack() {

			@Override
			public void onFriendsInAppCallBack(String graph) {
				// TODO 自动生成的方法存根
				doRequestFriendsInAppPrevPage(graph);
			}
		});
	}

	public static void onActivityResult(int requestCode, int resultCode,
			Intent data) {
		fb.onActivityResult(requestCode, resultCode, data);
		google.onActivityResult(requestCode, resultCode, data);
	}

	public static native void onFbInitCb(int flags);

	public static native void onGoogleInitCb(int flags);

	public static native void onFbLoginCb(int flags, String fbUser);

	public static native void onGoogleLoginCb(int flags, String googleUser);

	public static native void onGooglePayCb(int flags, String sku,
			String itemType, String googleOrder, String signature,
			String purchaseOriginalData);

	public static native void onGoogleConsumeCb(int flags, String sku);

	public static native void onQueryGoogleInventory(int flags, String skus);

	public static native void onFacebookSharePhoto(int flags);

	public static native void onFacebookShareLink(int flags);

	public static native void onInviteFriends(int flags);

	public static native void onUnlockAchievement(String achievementId);

	public static native void onIncrementAchievement(String achievementId,
			int score);

	public static native void onShowAchievement();

	public static native void onUpdateScoreOnLeaderboard(String leaderboardId,
			int score);

	public static native void onShowLeaderboard();

	public static native void onRequestAllFriends(String graph);

	public static native void onRequestAllFriendsNextPage(String graph);

	public static native void onRequestAllFriendsPrevPage(String graph);

	public static native void onRequestFriendsInApp(String graph);

	public static native void onRequestFriendsInAppNextPage(String graph);

	public static native void onRequestFriendsInAppPrevPage(String graph);

}
