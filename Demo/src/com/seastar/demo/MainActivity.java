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
		// �˴��Ǳ�������activity onCreate���õķ���

		google.setCurActivity(this);
		
		fb.setCurActivity(this);

		google.init(new Google.OnInitCallBack() {

			@Override
			public void onInitCallBack(boolean success) {
				// TODO �Զ����ɵķ������

			}
		});

		fb.init(new Facebook.OnInitCallBack() {

			@Override
			public void onInitCallBack(boolean isInit) {
				// TODO �Զ����ɵķ������

			}
		});

	}

	List<String> peopleIds = new ArrayList();

	@Override
	public void onClick(View v) {
		// �˴�ʱ��ʾjava��ô���ó�ʼ������
		// C++��ʹ�����
		if (v.getId() == R.id.login_btn) {

			fb.login(new Facebook.OnLoginCallBack() {

				@Override
				public void onLoginCallBack(boolean isLogin,
						FacebookUserModel user) {
					// TODO �Զ����ɵķ������

				}
			});

		} else if (v.getId() == R.id.googlelogin_btn) {
			// �˴�ʱ��ʾjava��ô���ó�ʼ������
			// C++��ʹ�����
			google.login(new Google.OnLoginCallBack() {

				@Override
				public void onLoginCallBack(boolean success,
						GoogleUserModel googleUserModel) {
					// TODO �Զ����ɵķ������

				}
			});
			//
		} else if (v.getId() == R.id.pay_btn) {
			google.pay("", new Google.OnPayCallBack() {

				@Override
				public void onPayCallBack(boolean success, String sku,
						String itemType, String googleOrder, String signature,
						String purchaseOriginalData) {
					// TODO �Զ����ɵķ������

				}
			});
		}

		// Google����

		else if (v.getId() == R.id.btn_clock) {

			// �����ɾ�
			google.unlockAchievement("CgkImNubjsIeEAIQAA");
		}

		else if (v.getId() == R.id.btn_add) {

			// �ӷֳɾ�
			google.incrementAchievement("CgkImNubjsIeEAIQAA", 10);
		}

		else if (v.getId() == R.id.btn_top) {

			// չʾ�ɾ�
			google.showAchievement();
		}

		else if (v.getId() == R.id.btn_top1) {

			// ���а�
			google.updateScoreOnLeaderboard("CgkImNubjsIeEAIQBQ", 10);
		}
		// FaceBook����

		else if (v.getId() == R.id.btn_) {

			// imageUri ͼƬuri ����Ƭ����С��12MB��С������Ҫ��װ�汾7����߰汾��Ӧ�ó���
			// caption ͼƬ����
			// peopleIds �����ĺ���id
			// contentUri ����uri
			// refUrl �Ƽ���ַ
			fb.sharePhotoByUri("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1457664404&di=6cc7676d96060e4223151860e1a5c8d5&src=http://i3.shouyou.itc.cn/v3/news/2014/05/30/05301132139854688.jpg", "caption", peopleIds, null, "",
					new Facebook.OnPhotoShareCallBack() {

						@Override
						public void onPhotoShareCallBack(boolean success) {
							// TODO �Զ����ɵķ������

						}
					});
		} else if (v.getId() == R.id.btn6) {
			// url ����
			// title �������ݵı���
			// contentDescription ��������
			// imageUrl ����ͼͼ���URL
			fb.shareUrl("url", "title", "contentDescription", "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1457664404&di=6cc7676d96060e4223151860e1a5c8d5&src=http://i3.shouyou.itc.cn/v3/news/2014/05/30/05301132139854688.jpg",
					peopleIds, new Facebook.OnUrlShareCallBack() {

						@Override
						public void onUrlShareCallBack(boolean success) {
							// TODO �Զ����ɵķ������

						}
					});
		}

		else if (v.getId() == R.id.btn7) {

			// ��ָ�����ѷ�����Ϸ
			fb.shareGame("title", "message", "", "ObjectId",
					new Facebook.OnGameShareCallBack() {

						@Override
						public void onGameShareCallBack(boolean success) {
							// TODO �Զ����ɵķ������

						}
					});
		}

		else if (v.getId() == R.id.btn8) {

			// ����facebook��ҳ����������Ȧ��
			// fb.shareGameToFacebookHomepage("actionType", objectKey,
			// previewPropertyName, list, this);
			// applink ��Ϸ����
			// linkName ��������
			// caption ����
			// description ����
			// imageUrl ͼƬ����
			// peopleIds
			// toId
			// ��һ�����ӷ���ʽ��ʹ��webҳ�����
			fb.shareFeed("applink", "linkName", "caption", "description",
					"http://img03.sogoucdn.com/app/a/100520093/2ad11b094c93197d-4afaf786506af54b-9fd277aea03c8387ebd35dcfba4ba3dd.jpg", peopleIds, "toId",
					new Facebook.OnShareFeedCallBack() {

						@Override
						public void onShareFeedCallBack(boolean success) {
							// TODO �Զ����ɵķ������

						}
					});
		}

		else if (v.getId() == R.id.btn9) {

			// �������
			fb.inviteFriends("applinkUrl", "previewImageUrl",
					new Facebook.OnInviteCallBack() {

						@Override
						public void onInviteCallBack(boolean success) {
							// TODO �Զ����ɵķ������

						}
					});
		}

		else if (v.getId() == R.id.btn10) {

			// ��ȡ����ĺ�����Ϣ
			fb.requestInviteFriendsInfo("limit",
					new Facebook.OnInviteFriendsInfoCallBack() {

						@Override
						public void onInviteFriendsInfoCallBack(String graph) {
							// TODO �Զ����ɵķ������

						}
					});
			//
		}

		else if (v.getId() == R.id.btn11) {

			fb.requestAllFriends("height", "width", "limit",
					new Facebook.OnAllFriendsCallBack() {

						@Override
						public void onAllFriendsCallBack(String graph) {
							// TODO �Զ����ɵķ������

						}
					});
		}

		else if (v.getId() == R.id.btn12) {

			// ��ȡ��Ӧ����FB�ĺ�����Ϣ
			fb.requestFriendsInApp("limit",
					new Facebook.OnFriendsInAppCallBack() {

						@Override
						public void onFriendsInAppCallBack(String graph) {
							// TODO �Զ����ɵķ������

						}
					});
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		// �˴��Ǳ�������activity onActivityResult���õķ���
		// GameSdk.onActivityResult(requestCode, resultCode, data);
		google.onActivityResult(requestCode, resultCode, data);
		fb.onActivityResult(requestCode, resultCode, data);

	}

}