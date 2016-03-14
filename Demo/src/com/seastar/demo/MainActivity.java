package com.seastar.demo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.seastar.spg.Interface.GameSdk;
import com.seastar.spg.sdk.Facebook;
import com.seastar.spg.sdk.Google;
import com.seastar.spg.sdk.Facebook.FacebookUserModel;
import com.seastar.spg.sdk.Google.GoogleUserModel;
import com.seastar.ppzj.gplaytw.R;

public class MainActivity extends Activity implements View.OnClickListener {

	// static {
	// System.loadLibrary("demo");
	// }
	//
	Facebook fb = new Facebook();
	Google google = new Google();

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
		Button btn11 = (Button) findViewById(R.id.btn8);
		Button btn12 = (Button) findViewById(R.id.btn9);

		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
		btn6.setOnClickListener(this);
		btn7.setOnClickListener(this);
		btn9.setOnClickListener(this);
		btn10.setOnClickListener(this);
		btn11.setOnClickListener(this);
		btn12.setOnClickListener(this);
		btn61.setOnClickListener(this);
		// 此处是必须在主activity onCreate调用的方法

		google.setCurActivity(this);
		
		fb.setCurActivity(this);

		google.init(new Google.OnInitCallBack() {

			@Override
			public void onInitCallBack(boolean success) {
				// TODO 自动生成的方法存根

			}
		});

		fb.init(new Facebook.OnInitCallBack() {

			@Override
			public void onInitCallBack(boolean isInit) {
				// TODO 自动生成的方法存根

			}
		});

	}

	List<String> peopleIds = new ArrayList();

	@Override
	public void onClick(View v) {
		// 此处时演示java怎么调用初始化方法
		// C++不使用这个
		if (v.getId() == R.id.login_btn) {

			fb.login(new Facebook.OnLoginCallBack() {

				@Override
				public void onLoginCallBack(boolean isLogin,
						FacebookUserModel user) {
					// TODO 自动生成的方法存根

				}
			});

		} else if (v.getId() == R.id.googlelogin_btn) {
			// 此处时演示java怎么调用初始化方法
			// C++不使用这个
			google.login(new Google.OnLoginCallBack() {

				@Override
				public void onLoginCallBack(boolean success,
						GoogleUserModel googleUserModel) {
					// TODO 自动生成的方法存根

				}
			});
			//
		} else if (v.getId() == R.id.pay_btn) {
			google.pay("", new Google.OnPayCallBack() {

				@Override
				public void onPayCallBack(boolean success, String sku,
						String itemType, String googleOrder, String signature,
						String purchaseOriginalData) {
					// TODO 自动生成的方法存根

				}
			});
		}

		// Google方法

		else if (v.getId() == R.id.btn_clock) {

			// 解锁成就
			google.unlockAchievement("CgkImNubjsIeEAIQAA");
		}

		else if (v.getId() == R.id.btn_add) {

			// 加分成就
			google.incrementAchievement("CgkImNubjsIeEAIQAA", 10);
		}

		else if (v.getId() == R.id.btn_top) {

			// 展示成就
			google.showAchievement();
		}

		else if (v.getId() == R.id.btn_top1) {

			// 排行榜
			google.updateScoreOnLeaderboard("CgkImNubjsIeEAIQBQ", 10);
		}
		// FaceBook方法

		else if (v.getId() == R.id.btn_) {

			// imageUri 图片uri （照片必须小于12MB大小并且需要安装版本7或更高版本的应用程序）
			// caption 图片标题
			// peopleIds 分享到的好友id
			// contentUri 内容uri
			// refUrl 推荐网址
			fb.sharePhotoByUri("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1457664404&di=6cc7676d96060e4223151860e1a5c8d5&src=http://i3.shouyou.itc.cn/v3/news/2014/05/30/05301132139854688.jpg", "caption", peopleIds, null, "",
					new Facebook.OnPhotoShareCallBack() {

						@Override
						public void onPhotoShareCallBack(boolean success) {
							// TODO 自动生成的方法存根

						}
					});
		} else if (v.getId() == R.id.btn6) {
			// url 链接
			// title 链接内容的标题
			// contentDescription 描述内容
			// imageUrl 缩略图图像的URL
			fb.shareUrl("url", "title", "contentDescription", "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1457664404&di=6cc7676d96060e4223151860e1a5c8d5&src=http://i3.shouyou.itc.cn/v3/news/2014/05/30/05301132139854688.jpg",
					peopleIds, new Facebook.OnUrlShareCallBack() {

						@Override
						public void onUrlShareCallBack(boolean success) {
							// TODO 自动生成的方法存根

						}
					});
		}

		else if (v.getId() == R.id.btn7) {

			// 向指定好友分享游戏
			fb.shareGame("title", "message", "", "ObjectId",
					new Facebook.OnGameShareCallBack() {

						@Override
						public void onGameShareCallBack(boolean success) {
							// TODO 自动生成的方法存根

						}
					});
		}

		else if (v.getId() == R.id.btn8) {

			// 分享到facebook主页（类似朋友圈）
			// fb.shareGameToFacebookHomepage("actionType", objectKey,
			// previewPropertyName, list, this);
			// applink 游戏链接
			// linkName 链接名称
			// caption 标题
			// description 内容
			// imageUrl 图片链接
			// peopleIds
			// toId
			// 另一种链接分享方式，使用web页面分享
			fb.shareFeed("applink", "linkName", "caption", "description",
					"http://img03.sogoucdn.com/app/a/100520093/2ad11b094c93197d-4afaf786506af54b-9fd277aea03c8387ebd35dcfba4ba3dd.jpg", peopleIds, "toId",
					new Facebook.OnShareFeedCallBack() {

						@Override
						public void onShareFeedCallBack(boolean success) {
							// TODO 自动生成的方法存根

						}
					});
		}

		else if (v.getId() == R.id.btn9) {

			// 邀请好友
			fb.inviteFriends("applinkUrl", "previewImageUrl",
					new Facebook.OnInviteCallBack() {

						@Override
						public void onInviteCallBack(boolean success) {
							// TODO 自动生成的方法存根

						}
					});
		}

		else if (v.getId() == R.id.btn10) {

			// 获取邀请的好友信息
			fb.requestInviteFriendsInfo("limit",
					new Facebook.OnInviteFriendsInfoCallBack() {

						@Override
						public void onInviteFriendsInfoCallBack(String graph) {
							// TODO 自动生成的方法存根

						}
					});
			//
		}

		else if (v.getId() == R.id.btn11) {

			fb.requestAllFriends("height", "width", "limit",
					new Facebook.OnAllFriendsCallBack() {

						@Override
						public void onAllFriendsCallBack(String graph) {
							// TODO 自动生成的方法存根

						}
					});
		}

		else if (v.getId() == R.id.btn12) {

			// 获取本应用中FB的好友信息
			fb.requestFriendsInApp("limit",
					new Facebook.OnFriendsInAppCallBack() {

						@Override
						public void onFriendsInAppCallBack(String graph) {
							// TODO 自动生成的方法存根

						}
					});
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		// 此处是必须在主activity onActivityResult调用的方法
		// GameSdk.onActivityResult(requestCode, resultCode, data);
		google.onActivityResult(requestCode, resultCode, data);
		fb.onActivityResult(requestCode, resultCode, data);

	}

}