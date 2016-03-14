package com.seastar.spg.sdk;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.google.pay.IabHelper;
import com.google.pay.IabResult;
import com.google.pay.Inventory;
import com.google.pay.Purchase;
import com.google.pay.SkuDetails;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;

public class Google {

	private static final int REQUEST_CODE_SIGN_IN = 30000;
	private static final int REQUEST_CODE_ERROR_DIALOG = 30001;
	private static final int REQUEST_CODE_INTERACTIVE_POST = 30002;
	private static final int REQUEST_CODE_PAY = 10001;
	private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
	private static final String TAG = "SeaStar";

	public interface OnLoginCallBack {
		void onLoginCallBack(boolean success, GoogleUserModel googleUserModel);
	}

	public interface OnInitCallBack {
		void onInitCallBack(boolean success);
	}

	public interface OnQueryInventoryCallBack {
		void onQueryInventoryCallBack(boolean success, List<GoogleSkuModel> googleSkuModel);
	}

	public class GoogleUserModel {

		public String id;
		public String aboutMe;
		public String ageRange;
		public String birthday;
		public String braggingRights;
		public String cover;
		public String currentLocation;
		public String displayName;
		public String image;
		public String language;
		public String name;
		public String nickName;
		public String organizations;
		public String plusOneCount;
		public String url;

	}

	public interface OnQuerySkusDetailsCallBack {
		void onQuerySkusDetailsCallBack(boolean success, List<GoogleSkuModel> googleSkuModel);
	}

	public interface OnPayCallBack {
		void onPayCallBack(boolean success, String sku, String itemType, String googleOrder, String signature,
				String purchaseOriginalData);
	}

	public interface OnConsumeCallBack {
		void onConsumeCallBack(boolean success, String sku);
	}

	public class GoogleSkuModel {
		public String description; // ��Ʒ����
		public String price; // ��Ʒ��ʾ�۸�
		public String sku; // ��ƷID
		public String title; // ��Ʒ����
		public String type; // ��Ʒ ���� �������ѣ� ���¿���
		public String priceAmountMicros; // ��Ʒʵ�ʼ۸�
		public String priceCurrencyCode; // ��Ʒ�۸�λ
		public String googleOrder; // �ȸ趩����
		public String signature; // �ȸ�ǩ��
		public String purchaseOriginalData; // �ȸ轻����ϸ��Ϣ
		public String itemType;

	}

	private IabHelper helper;
	private OnLoginCallBack onLoginCallBack;

	private Activity activity = null;

	private GoogleApiClient googleApiClient;

	private GoogleApiClient.ConnectionCallbacks ConnectionCb = new GoogleApiClient.ConnectionCallbacks() {

		@Override
		public void onConnectionSuspended(int arg0) {
			Log.d(TAG, "Google::ConnectionCb::onConnectionSuspended ���µ�¼");
			if (googleApiClient != null)
				googleApiClient.connect();
		}

		@Override
		public void onConnected(Bundle arg0) {
			// TODO Auto-generated method stub

			Person person = Plus.PeopleApi.getCurrentPerson(googleApiClient);
			if (person != null) {
				Log.d(TAG, "Google::ConnectionCb::onConnected ��ȡ�˺���Ϣ�ɹ�����½�ɹ�");
				GoogleUserModel googleUserModel = new GoogleUserModel();

				googleUserModel.aboutMe = person.getAboutMe();
				googleUserModel.ageRange = person.getAgeRange().toString();
				googleUserModel.birthday = person.getBirthday();
				googleUserModel.braggingRights = person.getBraggingRights();
				googleUserModel.cover = person.getCover().toString();
				googleUserModel.currentLocation = person.getCurrentLocation();
				googleUserModel.displayName = person.getDisplayName();
				googleUserModel.id = person.getId();
				googleUserModel.image = person.getImage().toString();
				googleUserModel.language = person.getLanguage();
				googleUserModel.name = person.getName().toString();
				googleUserModel.nickName = person.getNickname();
				googleUserModel.organizations = person.getOrganizations().toString();
				googleUserModel.url = person.getUrl();

				onLoginCallBack.onLoginCallBack(true, googleUserModel);

			} else {
				Log.d(TAG, "Google::ConnectionCb::onConnected ��ȡ�˺���Ϣʧ�ܣ���½ʧ��");
				onLoginCallBack.onLoginCallBack(false, null);
			}

			onLoginCallBack = null;
		}
	};

	private GoogleApiClient.OnConnectionFailedListener ConnectionFailCb = new GoogleApiClient.OnConnectionFailedListener() {

		public void onConnectionFailed(ConnectionResult result) {
			// TODO Auto-generated method stub

			if (result.hasResolution()) {
				try {
					// �����¼
					Log.d(TAG, "Google::ConnectionFailCb::onConnectionFailed ��¼ʧ�ܣ��ٽ��е�½");
					result.startResolutionForResult(activity, REQUEST_CODE_SIGN_IN);

				} catch (IntentSender.SendIntentException e) {
					e.printStackTrace();
					googleApiClient.connect();
				}
			} else {
				// ��¼ʧ��
				GoogleApiAvailability.getInstance().showErrorDialogFragment(activity, result.getErrorCode(),
						REQUEST_CODE_ERROR_DIALOG);
				onLoginCallBack.onLoginCallBack(false, null);
				onLoginCallBack = null;
			}
		}
	};

	public void setCurActivity(Activity activity) {
		this.activity = activity;
	}

	public void init(final OnInitCallBack oick) {

		try {
			// ��Ȩ
			GoogleApiClient.Builder builder = new GoogleApiClient.Builder(activity).addConnectionCallbacks(ConnectionCb)
					.addOnConnectionFailedListener(ConnectionFailCb).addApi(Plus.API).addScope(Plus.SCOPE_PLUS_LOGIN);
			googleApiClient = builder.build();

			// ֧��
			helper = new IabHelper(activity, "");

			helper.enableDebugLogging(false);

			if (!checkPlayServices(activity)) {
				oick.onInitCallBack(false);
				return;
			}

			helper.startSetup(new IabHelper.OnIabSetupFinishedListener() {

				@Override
				public void onIabSetupFinished(IabResult result) {
					if (result.isSuccess()) {
						oick.onInitCallBack(true);
					} else {
						oick.onInitCallBack(false);
					}
				}
			});
		} catch (Exception e) {
			Log.d(TAG, "�ȸ�֧��ϵͳ��ʼ��ʧ�ܣ��������������");
			Log.d(TAG, "1. �����ֻ����Ƿ��йȸ��ܣ����Բ鿴�Ƿ��йȸ��̵꣩");
			Log.d(TAG, "2. �����ֻ����Ƿ��йȸ��˺ţ����Խ���ȸ��̵꿴�Ƿ����˺ţ�");
			Log.d(TAG, "3. ����ȸ��˺��Ƿ��ܹ�֧�������Խ���ȸ��̵꿴�Ƿ����ܻ�Ǯ�����app��");
			Log.d(TAG, "4. ����ȸ��˺��Ƿ��ǲ����˺ţ�ȥ�ȸ��̨�鿴��");
			Log.d(TAG, "5. ����ȸ��˺��ǽ����˲������루ȥ�ȸ��̨��ȡ�����������Ӵ򿪣�");
			Log.d(TAG, "6. ����APKǩ���Ƿ����ύ���ȸ��APK��ͬ");
			Log.d(TAG, "7. ����APK VersionCode�Ƿ����ύ���ȸ��APK��ͬ");
			e.printStackTrace();
		}

	}

	public void login(OnLoginCallBack onLoginCallBack) {

		this.onLoginCallBack = onLoginCallBack;

		if (googleApiClient != null) {
			if (googleApiClient.isConnected()) {
				Log.d(TAG, "Google::login �Ѿ���Ȩ���������Ȩ�����¿�ʼ");
				// �Ѿ���Ȩ���ˣ���Ҫ�����Ȩ
				logout();
				googleApiClient.connect();
			} else if (googleApiClient.isConnecting()) {
				// ������Ȩ�У�ʲô������
				Log.d(TAG, "Google::login ������Ȩ�����Ժ󡣡���");
			} else {
				Log.d(TAG, "Google::login û����Ȩ������ʼ��Ȩ");
				googleApiClient.connect();
			}
		} else {
			Log.d(TAG, "Google::login ��Ȩ���󲻴��ڣ���Ȩʧ��");
			this.onLoginCallBack.onLoginCallBack(false, null);
			this.onLoginCallBack = null;
		}

	}

	public void logout() {

		if (googleApiClient != null && googleApiClient.isConnected()) {
			googleApiClient.clearDefaultAccountAndReconnect();
			googleApiClient.disconnect();
		}
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == REQUEST_CODE_SIGN_IN) {
			// Previous resolution intent no longer in progress.
			Log.d(TAG, "Google::onActivityResult reqeuest code sign in");

			if (resultCode == Activity.RESULT_OK) {
				// After resolving a recoverable error, now retry connect().
				// Note that it's possible
				// mGoogleApiClient is already connected or connecting due to
				// rotation / Activity
				// restart while user is walking through the (possibly full
				// screen) resolution
				// Activities. We should always reconnect() and ignore earlier
				// connection attempts
				// started before completion of the resolution. (With only one
				// exception, a
				// connect() attempt started late enough in the resolution flow
				// and it actually
				// succeeded)
				Log.d(TAG, "Google::onActivityResult reqeuest result ok");
				if (!googleApiClient.isConnected()) {
					Log.d(TAG, "Google::onActivityResult reqeuest result ok reconnect");
					googleApiClient.reconnect();
				}
			} else {
				// No longer in the middle of resolving sign-in errors.
				Log.d(TAG, "Google::onActivityResult reqeuest result error");
				if (resultCode == Activity.RESULT_CANCELED) {
					// mSignInStatus.setText(getString(R.string.signed_out_status));
				} else {
					// mSignInStatus.setText(getString(R.string.sign_in_error_status));
					// Log.w(TAG, "Error during resolving recoverable error.");
				}
			}
		} else if (requestCode == REQUEST_CODE_INTERACTIVE_POST) {
			if (resultCode != Activity.RESULT_OK) {
				Log.d(TAG, "Google::onActivityResult ����ʧ��");

			} else {
				Log.d(TAG, "Google::onActivityResult ����ɹ�");

			}

		} else if (requestCode == REQUEST_CODE_PAY) {
			helper.handleActivityResult(requestCode, resultCode, data);
		}
	}

	// ��ѯ�����û�����ѵ���Ʒ
	public void queryInventoryAsync(final List<String> skuList,
			final OnQueryInventoryCallBack onQueryInventoryCallBack) {
		Log.d(TAG, "Google::queryInventoryAsync ��ѯ�û����е���Ʒ��Ϣ");
		try {

			helper.queryInventoryAsync(new IabHelper.QueryInventoryFinishedListener() {
				public void onQueryInventoryFinished(IabResult result, Inventory inventory) {

					ArrayList<GoogleSkuModel> trans = new ArrayList<GoogleSkuModel>();

					if (result.isFailure()) {
						// Handle failure
						System.out
								.println(TAG + " Google::queryInventoryAsync, ��ѯ�ڹȸ蹺�����Ʒʧ��, ԭ��:" + result.getMessage());
						onQueryInventoryCallBack.onQueryInventoryCallBack(false, trans);
					} else {
						System.out.println(TAG + " Google::queryInventoryAsync, ��ѯ�ڹȸ蹺�����Ʒ�ɹ�");
						// ©������
						for (String sku : skuList) {

							if (inventory.hasDetails(sku)) {
								SkuDetails detail = inventory.getSkuDetails(sku);
								GoogleSkuModel googleSkuModel = new GoogleSkuModel();
								Log.d(TAG, detail.toString());
								googleSkuModel.description = detail.getDescription();
								googleSkuModel.price = detail.getPrice();
								googleSkuModel.sku = detail.getSku();
								googleSkuModel.title = detail.getTitle();
								googleSkuModel.type = detail.getType();
								googleSkuModel.priceAmountMicros = (new BigDecimal(detail.getPriceAmountMicros())
										.divide(new BigDecimal(1000000))).toString();
								googleSkuModel.priceCurrencyCode = detail.getPriceCurrencyCode();
								// �洢����δ������
								if (inventory.hasPurchase(sku)) {
									Purchase purchase = inventory.getPurchase(sku);
									System.out.println(
											TAG + " Google::queryInventoryAsync, ��֤����Ϣ��" + purchase.getOriginalJson());
									googleSkuModel.googleOrder = purchase.getOrderId();
									googleSkuModel.signature = purchase.getSignature();
									googleSkuModel.purchaseOriginalData = purchase.getOriginalJson();
									googleSkuModel.itemType = purchase.getItemType();
									System.out.println(TAG + " Google::queryInventoryAsync, ��Ʒ��Ϣ��" + detail.toString());
								}

								trans.add(googleSkuModel);
							}

						}

						onQueryInventoryCallBack.onQueryInventoryCallBack(true, trans);

					}
				}
			}

			);
		} catch (Exception e) {
			Log.d(TAG, "��ѯ�û����е���Ʒ��Ϣʧ�ܣ��쳣��Ϣ��" + e.getMessage());
			Log.d(TAG, "�������������");
			Log.d(TAG, "1. �����ֻ����Ƿ��йȸ��ܣ����Բ鿴�Ƿ��йȸ��̵꣩");
			Log.d(TAG, "2. �����ֻ����Ƿ��йȸ��˺ţ����Խ���ȸ��̵꿴�Ƿ����˺ţ�");
			Log.d(TAG, "3. ����ȸ��˺��Ƿ��ܹ�֧�������Խ���ȸ��̵꿴�Ƿ����ܻ�Ǯ�����app��");
			Log.d(TAG, "4. ����ȸ��˺��Ƿ��ǲ����˺ţ�ȥ�ȸ��̨�鿴��");
			Log.d(TAG, "5. ����ȸ��˺��ǽ����˲������루ȥ�ȸ��̨��ȡ�����������Ӵ򿪣�");
			Log.d(TAG, "6. ����APKǩ���Ƿ����ύ���ȸ��APK��ͬ");
			Log.d(TAG, "7. ����APK VersionCode�Ƿ����ύ���ȸ��APK��ͬ");
			Log.d(TAG, "8. �����Ƿ��ڹȸ��̨��ȷ��������Ʒ");
			e.printStackTrace();
		}
	}

	// ��ѯ��Ʒ��Ϣ
	public void querySkus(ArrayList<String> skus, final OnQuerySkusDetailsCallBack onQuerySkusDetailsCallBack) {
		try {
			helper.querySkuDetailsAsync(skus, IabHelper.ITEM_TYPE_INAPP,
					new IabHelper.QuerySkuDetailsFinishedListener() {

						@Override
						public void onQuerySkuDetailsFinished(IabResult result, List<SkuDetails> skuDetails) {

							if (result.isFailure()) {
								if (onQuerySkusDetailsCallBack != null)
									onQuerySkusDetailsCallBack.onQuerySkusDetailsCallBack(false,
											new ArrayList<GoogleSkuModel>());
							} else {

								ArrayList<GoogleSkuModel> objgsm = new ArrayList<GoogleSkuModel>();

								for (SkuDetails skudetail : skuDetails) {
									BigDecimal mFloat = new BigDecimal(skudetail.getPriceAmountMicros())
											.divide(new BigDecimal(1000000));
									System.out.println(TAG + "Google::querySkus, ��ùȸ��̨���õ���Ʒ:" + skudetail.toString());

									GoogleSkuModel inf = new GoogleSkuModel();
									inf.description = skudetail.getDescription();
									inf.price = skudetail.getPrice();
									inf.sku = skudetail.getSku();
									inf.title = skudetail.getTitle();
									inf.type = skudetail.getType();
									inf.priceAmountMicros = mFloat.toString();
									inf.priceCurrencyCode = skudetail.getPriceCurrencyCode();

									objgsm.add(inf);
								}

								onQuerySkusDetailsCallBack.onQuerySkusDetailsCallBack(true, objgsm);

							}
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
			Log.d(TAG, "��ȡ�ȸ��̨���õ���Ʒ�б�ʧ��");
			Log.d(TAG, "�������������");
			Log.d(TAG, "1. �����ֻ����Ƿ��йȸ��ܣ����Բ鿴�Ƿ��йȸ��̵꣩");
			Log.d(TAG, "2. �����ֻ����Ƿ��йȸ��˺ţ����Խ���ȸ��̵꿴�Ƿ����˺ţ�");
			Log.d(TAG, "3. ����ȸ��˺��Ƿ��ܹ�֧�������Խ���ȸ��̵꿴�Ƿ����ܻ�Ǯ�����app��");
			Log.d(TAG, "4. ����ȸ��˺��Ƿ��ǲ����˺ţ�ȥ�ȸ��̨�鿴��");
			Log.d(TAG, "5. ����ȸ��˺��ǽ����˲������루ȥ�ȸ��̨��ȡ�����������Ӵ򿪣�");
			Log.d(TAG, "6. ����APKǩ���Ƿ����ύ���ȸ��APK��ͬ");
			Log.d(TAG, "7. ����APK VersionCode�Ƿ����ύ���ȸ��APK��ͬ");
			Log.d(TAG, "8. �����Ƿ��ڹȸ��̨��ȷ��������Ʒ");
		}
	}

	// ֧�� sku��ƷID
	public void pay(final String sku, final OnPayCallBack onPayCallBack) {
		try {
			// Ϊ�˷�ֹ�ȸ蹺��ɹ�������û���յ���ȷ���������֣���Ҫ�ѵ�ǰ���ݴ洢
			Log.d(TAG, "Google::pay");
			helper.launchPurchaseFlow(activity, sku, REQUEST_CODE_PAY, new IabHelper.OnIabPurchaseFinishedListener() {
				public void onIabPurchaseFinished(IabResult result, Purchase purchase) {

					if (result.isFailure()) {
						// Handle error
						Log.d(TAG, result.getMessage());
						onPayCallBack.onPayCallBack(false, sku, "", "", "", "");

					} else if ((purchase.getSku().equals(sku))) {

						onPayCallBack.onPayCallBack(true, sku, purchase.getItemType(), purchase.getOrderId(),
								purchase.getSignature(), purchase.getOriginalJson());
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			Log.d(TAG, "�����ȸ�֧��ʧ��");
			Log.d(TAG, "�������������");
			Log.d(TAG, "1. �����ֻ����Ƿ��йȸ��ܣ����Բ鿴�Ƿ��йȸ��̵꣩");
			Log.d(TAG, "2. �����ֻ����Ƿ��йȸ��˺ţ����Խ���ȸ��̵꿴�Ƿ����˺ţ�");
			Log.d(TAG, "3. ����ȸ��˺��Ƿ��ܹ�֧�������Խ���ȸ��̵꿴�Ƿ����ܻ�Ǯ�����app��");
			Log.d(TAG, "4. ����ȸ��˺��Ƿ��ǲ����˺ţ�ȥ�ȸ��̨�鿴��");
			Log.d(TAG, "5. ����ȸ��˺��ǽ����˲������루ȥ�ȸ��̨��ȡ�����������Ӵ򿪣�");
			Log.d(TAG, "6. ����APKǩ���Ƿ����ύ���ȸ��APK��ͬ");
			Log.d(TAG, "7. ����APK VersionCode�Ƿ����ύ���ȸ��APK��ͬ");
			Log.d(TAG, "8. �����Ƿ��ڹȸ��̨��ȷ��������Ʒ");
		}

	}

	public void consume(final String sku, String itemType, String signature,
			String purchaseOriginalData, final OnConsumeCallBack onConsumeCallBack) {
		Purchase purchase;
		try {
			purchase = new Purchase(itemType, purchaseOriginalData, signature);

			helper.consumeAsync(purchase, new IabHelper.OnConsumeFinishedListener() {
				public void onConsumeFinished(Purchase purchase, IabResult result) {
					if (result.isSuccess()) {
						Log.d(TAG, " ��Ʒ�ڹȸ����ѳɹ�");
						// ���ѳɹ�����Ҫȥƽ̨������֤
						onConsumeCallBack.onConsumeCallBack(true, sku);
					} else {
						Log.d(TAG, " ��Ʒ�ڹȸ�����ʧ�ܣ�ԭ��" + result.getMessage());
						onConsumeCallBack.onConsumeCallBack(false, sku);

					}
				}
			});
		} catch (JSONException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}

	public void onDestroy() {
		if (helper != null) {
			helper.dispose();
			helper = null;
		}
	}

	private boolean checkPlayServices(Activity activity) {
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
		if (resultCode != ConnectionResult.SUCCESS) {
			if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
				GooglePlayServicesUtil.getErrorDialog(resultCode, activity, PLAY_SERVICES_RESOLUTION_REQUEST).show();
			} else {

			}
			return false;
		}
		return true;
	}

}
