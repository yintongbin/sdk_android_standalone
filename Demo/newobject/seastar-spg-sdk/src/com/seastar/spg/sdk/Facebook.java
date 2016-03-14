package com.seastar.spg.sdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;

public class Facebook {

	public interface OnLoginCallBack {

		void onLoginCallBack(boolean isLogin, FacebookUserModel user);
	}

	public interface OnInitCallBack {
		void onInitCallBack(boolean isInit);
	}

	public class FacebookUserModel {
		public String id;
		public String name;
		public String picture;
		public String email;
		public String first_name;
		public String last_name;
		public String middle_name;
		public String name_format;
		public String third_party_id;
		public String gender;
		public String location;
		public String friends;
	}

	private Activity activity = null;
	public static final String TAG = "SeaStar";
	private CallbackManager callbackManager;

	private OnLoginCallBack onLoginCallBack;

	public void init(final OnInitCallBack oick) {

		FacebookSdk.sdkInitialize(activity.getApplicationContext(),
				new FacebookSdk.InitializeCallback() {
					@Override
					public void onInitialized() {
						if (FacebookSdk.isInitialized()) {
							Log.d(TAG, "Facebook::init, init success!");
							oick.onInitCallBack(true);
						} else {
							Log.d(TAG, "Facebook::init, init failed!");
							oick.onInitCallBack(false);
						}
					}
				});

		callbackManager = CallbackManager.Factory.create();
		LoginManager.getInstance().registerCallback(callbackManager,
				new FacebookCallback<LoginResult>() {
					@Override
					public void onSuccess(LoginResult loginResult) {
						Log.d(TAG, "Facebook::login login success!");
						getMyInfo();
					}

					@Override
					public void onCancel() {
						Log.d(TAG, "Facebook::login login cancel!");
						onLoginCallBack.onLoginCallBack(false, null);
						onLoginCallBack = null;
					}

					@Override
					public void onError(FacebookException e) {
						Log.d(TAG, "Facebook::login login error! : " + e.toString());
						onLoginCallBack.onLoginCallBack(false, null);
						onLoginCallBack = null;
					}
				});
	}

	public void login(OnLoginCallBack onLoginCallBack) {
		
		this.onLoginCallBack = onLoginCallBack;
		LoginManager.getInstance().logInWithReadPermissions((Activity) activity,
				Arrays.asList("public_profile", "user_friends"));
	}

	public void logout()
	{
		LoginManager.getInstance().logOut();
	}

	
	public void onActivityResult(final int requestCode, final int resultCode,
			final Intent data) {
		callbackManager.onActivityResult(requestCode, resultCode, data);
	}

	public void setCurActivity(Activity activity) {
		this.activity = activity;
	}
	
	/**
	 * 获取用户自己的信息
	 */
	private void getMyInfo() {
		if (AccessToken.getCurrentAccessToken() == null) {
			Log.d(TAG, "Facebook::getMyInfo facebook获取自己的信息失败，facebook没有登录");
			onLoginCallBack.onLoginCallBack(false, null);
			onLoginCallBack = null;
			return;
		}
		/*-----------------------------------获取用户的信息-------------------------------------------*/
		GraphRequest request = GraphRequest.newMeRequest(
				AccessToken.getCurrentAccessToken(),
				new GraphRequest.GraphJSONObjectCallback() {

					@Override
					public void onCompleted(JSONObject object,
							GraphResponse response) {
						// Application code
						Log.d(TAG, "Facebook::getMyInfo facebook获取用户的信息 :" + object.toString());
						if (response.getJSONObject() != null) {
							try {
								JSONObject json = response.getJSONObject();
								FacebookUserModel model = new FacebookUserModel();
								model.id = AccessToken.getCurrentAccessToken()
										.getUserId();
								model.name = json.get("name").toString();
								model.picture = json.get("picture").toString();
								model.first_name = json.get("first_name")
										.toString();
								model.last_name = json.get("last_name")
										.toString();
								model.middle_name = json.get("middle_name")
										.toString();
								model.name_format = json.get("name_format")
										.toString();
								model.third_party_id = json.get(
										"third_party_id").toString();
								model.gender = json.get("gender").toString();

								onLoginCallBack.onLoginCallBack(true, model);
								onLoginCallBack = null;
							} catch (JSONException ex) {
								ex.printStackTrace();
							}

						} else {
							onLoginCallBack.onLoginCallBack(false, null);
							onLoginCallBack = null;
						}
					}
				});
		Bundle parameters = new Bundle();
		parameters
				.putString(
						"fields",
						"id,name,picture,email,first_name,last_name,middle_name,name_format,third_party_id,gender,location,friends");
		request.setParameters(parameters);
		request.executeAsync();

	}
	
	
}
