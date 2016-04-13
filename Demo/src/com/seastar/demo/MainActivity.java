package com.seastar.demo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.seastar.spg.Interface.GameSdk;
import com.seastar.spg.sdk.Facebook;
import com.seastar.spg.sdk.Google;
import com.seastar.spg.sdk.Google.GoogleUserModel;
import com.seastar.gplay.carrot.R;

public class MainActivity extends Activity implements View.OnClickListener {

	static {
		System.loadLibrary("demo");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btn1 = (Button) findViewById(R.id.googlelogin_btn);
		Button btn2 = (Button) findViewById(R.id.login_btn);
		Button btn3 = (Button) findViewById(R.id.pay_btn);
		Button btn4 = (Button) findViewById(R.id.btn_add);
		Button btn5 = (Button) findViewById(R.id.btn_clock);
		Button btn6 = (Button) findViewById(R.id.btn_top);
		Button btn61 = (Button) findViewById(R.id.btn_top1);
		Button btn7 = (Button) findViewById(R.id.btn_);
		Button btn9 = (Button) findViewById(R.id.btn6);
		Button btn10 = (Button) findViewById(R.id.btn7);
		Button btn12 = (Button) findViewById(R.id.btn9);
		Button btn11 = (Button) findViewById(R.id.btn10);
		Button btn13 = (Button) findViewById(R.id.btn11);
		Button btn14 = (Button) findViewById(R.id.btn12);
		Button btn15 = (Button) findViewById(R.id.btn13);
		Button btn16 = (Button) findViewById(R.id.btn14);
		Button btn17 = (Button) findViewById(R.id.btn15);

		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
		btn6.setOnClickListener(this);
		btn7.setOnClickListener(this);
		btn9.setOnClickListener(this);
		btn10.setOnClickListener(this);
		btn12.setOnClickListener(this);
		btn61.setOnClickListener(this);
		btn11.setOnClickListener(this);
		btn13.setOnClickListener(this);
		btn14.setOnClickListener(this);
		btn15.setOnClickListener(this);
		btn16.setOnClickListener(this);
		btn17.setOnClickListener(this);
		// 此处是必须在主activity onCreate调用的方法

		GameSdk.setCurActivity(this);
		GameSdk.init();
		GameSdk.doFbInit();
		GameSdk.doGoogleInit();

	}
 
	@Override
	public void onClick(View v) {

		if (v.getId() == R.id.login_btn) {

			GameSdk.doFbLogin();

		} else if (v.getId() == R.id.googlelogin_btn) {

			GameSdk.doGoogleLogin();
			//
		} else if (v.getId() == R.id.pay_btn) {

			GameSdk.doGooglePay("carrot.handful_1");
		}

		else if (v.getId() == R.id.btn_clock) {

			GameSdk.doUnlockAchievement("CgkImNubjsIeEAIQAQ");
		}

		else if (v.getId() == R.id.btn_add) {

			GameSdk.doIncrementAchievement("CgkImNubjsIeEAIQAQ", 0);
		}

		else if (v.getId() == R.id.btn_top) {

			GameSdk.doShowAchievement();
		}

		else if (v.getId() == R.id.btn_top1) {

			GameSdk.doUpdateScoreOnLeaderboard("CgkImNubjsIeEAIQBQ", 0);
		}
		// FaceBook方法

		else if (v.getId() == R.id.btn_) {
			GameSdk.doFacebookSharePhoto(
					"file:///mnt/sdcard/blinkfeed_LANDSCAPE.jpg", "百度图片", "",
					null, null);
		} else if (v.getId() == R.id.btn6) {
			GameSdk.doFacebookShareLink(
					"http://www.baidu.com",
					"百度分享",
					"百度分享描述",
					"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo_top_ca79a146.png",
					"");

		} else if (v.getId() == R.id.btn7) {

			GameSdk.doInviteFriends(
					"http://www.baidu.com",
					"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo_top_ca79a146.png");
		}

		else if (v.getId() == R.id.btn9) {

		}

		else if (v.getId() == R.id.btn10) {
			// 获取邀请的好友信息
			GameSdk.doRequestAllFriends("0", "0", "2");

		} else if (v.getId() == R.id.btn11) {
			GameSdk.doRequestAllFriendsPrevPage();
		}

		else if (v.getId() == R.id.btn12) {

			GameSdk.doRequestAllFriendsNextPage();
		} else if (v.getId() == R.id.btn13) {
			GameSdk.doRequestFriendsInApp("2");
		} else if (v.getId() == R.id.btn13) {
			GameSdk.doRequestFriendsInAppPrevPage();

		} else if (v.getId() == R.id.btn13) {
			GameSdk.doRequestFriendsInAppNextPage();
		}
		
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		// 此处是必须在主activity onActivityResult调用的方法
		GameSdk.onActivityResult(requestCode, resultCode, data);

	}


	public static native void Login();
	
}