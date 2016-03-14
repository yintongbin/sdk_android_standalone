package com.seastar.spg.Interface;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.seastar.spg.sdk.*;
import com.seastar.spg.sdk.Facebook.FacebookUserModel;
import com.seastar.spg.sdk.Facebook.OnInitCallBack;
import com.seastar.spg.sdk.Facebook.OnLoginCallBack;
import com.seastar.spg.sdk.Google.GoogleSkuModel;
import com.seastar.spg.sdk.Google.GoogleUserModel;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class GameSdk {

	private static Facebook fb = new Facebook();
	private static Google google = new Google();
	private static Handler handler;

	public static void setCurActivity(Activity activity) {
		fb.setCurActivity(activity);
		google.setCurActivity(activity);
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 0:
					fb.init(new Facebook.OnInitCallBack() {

						@Override
						public void onInitCallBack(boolean isInit) {
							onFbInitCb(isInit ? 1 : 0);
						}
					});
					break;
				case 1:
					google.init(new Google.OnInitCallBack() {

						@Override
						public void onInitCallBack(boolean isInit) {
							onGoogleInitCb(isInit ? 1 : 0);
						}
					});
					break;
				case 2:
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

									onFbLoginCb(1, obj.toString());
								} catch (JSONException e) {
									e.printStackTrace();
								}
							} else {
								onFbLoginCb(0, "");
							}
						}
					});
					break;
				case 3:
					google.login(new Google.OnLoginCallBack() {

						@Override
						public void onLoginCallBack(boolean success, GoogleUserModel googleUserModel) {
							// TODO 自动生成的方法存根
							if (success) {
								try {
									JSONObject obj = new JSONObject();
									obj.put("id", googleUserModel.id);
									obj.put("ageRange", googleUserModel.ageRange);
									obj.put("birthday", googleUserModel.birthday);
									obj.put("currentLocation", googleUserModel.currentLocation);
									obj.put("displayName", googleUserModel.displayName);
									obj.put("image", googleUserModel.image);
									obj.put("language", googleUserModel.language);
									obj.put("name", googleUserModel.name);
									obj.put("nickName", googleUserModel.nickName);
									obj.put("url", googleUserModel.url);

									onGoogleLoginCb(1, obj.toString());
								} catch (JSONException e) {
									e.printStackTrace();
								}

							} else {
								onGoogleLoginCb(0, "");
							}
						}
					});
					break;
				case 4:
					fb.logout();
					break;
				case 5:
					google.logout();
					break;
				case 6:
					google.pay(String.valueOf(msg.obj), new Google.OnPayCallBack() {

						@Override
						public void onPayCallBack(boolean success, String sku, String itemType, String googleOrder,
								String signature, String purchaseOriginalData) {
							// TODO 自动生成的方法存根
							onGooglePayCb(success ? 1 : 0, sku, itemType, googleOrder, signature, purchaseOriginalData);
						}
					});
					break;
				case 7:
					Bundle bundle = (Bundle) msg.obj;
					google.consume(bundle.getString("sku"), bundle.getString("itemtype"), bundle.getString("signature"),
							bundle.getString("purchaseOriginalData"), new Google.OnConsumeCallBack() {

						@Override
						public void onConsumeCallBack(boolean success, String sku) {
							// TODO 自动生成的方法存根
							onGoogleConsumeCb(success ? 1 : 0, sku);

						}
					});
					break;
				case 8:
					String skus = String.valueOf(msg.obj);
					
					List<String> skusList = new ArrayList<>();
					String[] skusArr = skus.split(" ");
					for (int i = 0; i < skusArr.length; i++) {
						skusList.add(skusArr[i]);
					}

					google.queryInventoryAsync(skusList, new Google.OnQueryInventoryCallBack() {

						@Override
						public void onQueryInventoryCallBack(boolean success, List<GoogleSkuModel> googleSkuModels) {
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
										s.put("priceAmountMicros", sku.priceAmountMicros);
										s.put("priceCurrencyCode", sku.priceCurrencyCode);
										s.put("googleOrder", sku.googleOrder);
										s.put("signature", sku.signature);
										s.put("purchaseOriginalData", sku.purchaseOriginalData);

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

	public static void doGoogleConsume(String sku, String itemType, String signature, String purchaseOriginalData) {
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

	/*
	public static void query_Skus(final ArrayList<String> skulist, OnQueryInventoryCallBack onQueryInventoryCallBack) {
		google.querySkus(skulist, new Google.OnQuerySkusDetailsCallBack() {

			String skus;

			@Override
			public void onQuerySkusDetailsCallBack(boolean success, List<GoogleSkuModel> googleSkuModel) { // TODO
																											// 自动生成的方法存根

				if (success) {
					for (int i = 0; i < skulist.size(); i++) {
						if (i == skulist.size() - 1) {
							skus = skulist.get(i);
						} else {
							skus = skulist.get(i) + " ";
						}
					}

					querySkus(skus, new Google.OnQueryInventoryCallBack() {

						@Override
						public void onQueryInventoryCallBack(boolean success, List<GoogleSkuModel> googleSkuModel) { // TODO
																														// 自动生成的方法存根

						}
					});
				} else {
					querySkus(null, null);
				}
			}

		});

	}
	*/

	public static native void onFbInitCb(int flags);

	public static native void onGoogleInitCb(int flags);

	public static native void onFbLoginCb(int flags, String fbUser);

	public static native void onGoogleLoginCb(int flags, String googleUser);

	public static native void onGooglePayCb(int flags, String sku, String itemType, String googleOrder,
			String signature, String purchaseOriginalData);

	public static native void onGoogleConsumeCb(int flags, String sku);

	public static native void onQueryGoogleInventory(int flags, String skus);

}
