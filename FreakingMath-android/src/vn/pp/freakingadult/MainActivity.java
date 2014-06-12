package vn.pp.freakingadult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import vn.pp.ControlGGS;
import vn.pp.ControlHandler;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphObject;
import com.facebook.model.OpenGraphAction;
import com.facebook.model.OpenGraphObject;
import com.facebook.widget.FacebookDialog;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.plus.PlusShare;
import com.google.example.games.basegameutils.GameHelper;
import com.google.example.games.basegameutils.GameHelper.GameHelperListener;


public class MainActivity extends AndroidApplication implements
		GameHelperListener, ControlHandler, ControlGGS {
	
	private GameHelper gameHelper;

	private UiLifecycleHelper uiHelper;
	private static final String TAG = "MainActivity";
	
	// protected AdView adView; // small ad
	private final String ADCODE = "";

	private final int HIDE_ADS = 0;
	private final int SHOW_ADS = 1;
	private final int NOT_INSTALL_TWITTER = 2;
	private final int SHOW_LOADING_FACE = 3;
	private final int DISMIS_LOADING_FACE = 4;
	private final int NOT_SHARE_FACEBOOK = 5;
	private final int NOT_CONNECTION = 6;
	private final int SHOW_TOAST = 7;
	private final int SHOW_RATE_APP = 8;

	ProgressDialog prgd;

	private static final int REQ_START_SHARE = 2000;

	String packageName = "vn.pp.freakingadult";
	String urlGPlay = "https://play.google.com/store/apps/details?id="
			+ packageName;
	String urlRate = "market://details?id=" + packageName;

	public MainActivity() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// showHashKey(MainActivity.this, packageName);

		gameHelper = new GameHelper(this, GameHelper.CLIENT_ALL);// R.string.app_ggs_id
		// enable debug logs (if applicable)
		// if (DEBUG_BUILD) {
		// gameHelper.enableDebugLog(true, "GameHelper");
		// }

		uiHelper = new UiLifecycleHelper(this, callback);
		uiHelper.onCreate(savedInstanceState);

		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		// cfg.useGL20 = true;//false;

		RelativeLayout layout = new RelativeLayout(this);

		// Do the stuff that initialize() would do for you
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(
				WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

		// adView = new AdView(this, AdSize.SMART_BANNER, ADCODE); // Put in
		// your secret key here
		// adView.loadAd(new AdRequest(null));
		// Create an ad.
		// adView = new AdView(this);

		// adView.setAdSize(AdSize.BANNER);
		// adView.setAdUnitId(ADCODE);
		//
		// adView.setVisibility(View.VISIBLE);

		// Create an ad request. Check logcat output for the hashed device ID to
		// get test ads on a physical device.
		// AdRequest adRequest = new AdRequest.Builder()
		// // .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
		// // .addTestDevice("INSERT_YOUR_HASHED_DEVICE_ID_HERE")
		// .build();

		// Start loading the ad in the background.
		// adView.loadAd(adRequest);

		//
		// initialize(new BlockGame(this), cfg);

		View gameView = initializeForView(new BlockGame(this, this), cfg);

		layout.addView(gameView);
		// Config Ad PPS

	
		int heightAd = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
		int widthAd = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 320, getResources().getDisplayMetrics());

		RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
				widthAd,
				heightAd);
		adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		
		// Add the AdMob view
		// RelativeLayout.LayoutParams adParams = new
		// RelativeLayout.LayoutParams(
		// RelativeLayout.LayoutParams.WRAP_CONTENT,
		// RelativeLayout.LayoutParams.WRAP_CONTENT);
		// adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		// adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		//
		// layout.addView(adView, adParams);

		setContentView(layout);

		GameHelperListener listener = new GameHelper.GameHelperListener() {
			@Override
			public void onSignInSucceeded() {
				// handle sign-in succeess
				System.out.println("-------11-onSignInSucceeded-------");
			}

			@Override
			public void onSignInFailed() {
				System.out.println("-------11-onSignInFailed-------");
				// handle sign-in failure (e.g. show Sign In button)
			}

		};
		gameHelper.setup(listener);
		// gameHelper.setup(this);
	}

	@Override
	public void shareFb() {
		if (FacebookDialog.canPresentShareDialog(getApplicationContext(),
				FacebookDialog.ShareDialogFeature.SHARE_DIALOG)) {
			FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(
					this).setApplicationName("").setLink(urlGPlay)
					.setName("Nice game").build();
			uiHelper.trackPendingDialogCall(shareDialog.present());
		} else {
			// Toast.makeText(getApplicationContext(),
			// "Facebook app is not installed", Toast.LENGTH_LONG).show();
			handler.sendEmptyMessage(NOT_SHARE_FACEBOOK);
			// showToat.sendEmptyMessage(0);
		}
	}

	@Override
	public void showAd(boolean show) {
		Log.i(TAG, "handle show ads");

	}

	@Override
	public void showFullAd(boolean show) {
		Log.i(TAG, "handle show full");

	}
	
	@Override
	public boolean showExitAd(boolean show) {
	  
		return true;
	
	
	}

	private Session.StatusCallback callback = new Session.StatusCallback() {
		@Override
		public void call(Session session, SessionState state,
				Exception exception) {
			onSessionStateChange(session, state, exception);
		}
	};

	private void onSessionStateChange(Session session, SessionState state,
			Exception exception) {
		if (state.isOpened()) {

		} else if (state.isClosed()) {

		}
	}

	@Override
	public void onStart() {
		super.onStart();
		gameHelper.onStart(this);

		// EasyTracker.getInstance(this).activityStart(this); // Add this
		// method.
	}

	@Override
	public void onStop() {
		super.onStop();
		gameHelper.onStop();

		// EasyTracker.getInstance(this).activityStop(this); // Add this method.
	}

	@Override
	public void onResume() {
		super.onResume();
		uiHelper.onResume();
		
	}


	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		gameHelper.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data,
				new FacebookDialog.Callback() {
					@Override
					public void onError(FacebookDialog.PendingCall pendingCall,
							Exception error, Bundle data) {
						error.printStackTrace();
					}

					@Override
					public void onComplete(
							FacebookDialog.PendingCall pendingCall, Bundle data) {
					}
				});
	}

	@Override
	public void onPause() {
		// if (adView != null) {
		// adView.pause();
		// }
		super.onPause();
		// show exitAd

		uiHelper.onPause();
	
	}

	@Override
	public void onDestroy() {
		// Destroy the AdView.
		// if (adView != null) {
		// adView.destroy();
		// }
		super.onDestroy();
		uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		uiHelper.onSaveInstanceState(outState);
	}

	protected Handler moreAppsHadler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0: {
				startActivity(new Intent(Intent.ACTION_VIEW,
						Uri.parse("market://search?q=pub:Premier Prime Studio")));
				break;
			}
			}
		}
	};

	protected Handler googleToat = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0: {
				Toast.makeText(getApplicationContext(),
						"Google+ app is not installed", Toast.LENGTH_LONG)
						.show();
				break;
			}
			}
		}
	};

	protected Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_LOADING_FACE: {
				if (prgd == null) {
					prgd = new ProgressDialog(MainActivity.this);
				}
				prgd.setMessage("Loading...");
				prgd.show();
				break;
			}
			case DISMIS_LOADING_FACE: {
				if (prgd != null && prgd.isShowing()) {
					prgd.dismiss();
				}
				break;
			}
			case NOT_INSTALL_TWITTER: {
				Toast.makeText(getApplicationContext(),
						"Twitter app is not installed", Toast.LENGTH_LONG)
						.show();
				break;
			}
			case NOT_SHARE_FACEBOOK: {
				if (prgd != null && prgd.isShowing()) {
					prgd.dismiss();
				}
				Toast.makeText(getApplicationContext(), "Can't share Facebook",
				// "Facebook app is not installed",
						Toast.LENGTH_LONG).show();
				break;
			}
			case NOT_CONNECTION: {
				if (prgd != null && prgd.isShowing()) {
					prgd.dismiss();
				}
				Toast.makeText(getApplicationContext(), "No connection!",
						Toast.LENGTH_LONG).show();
				break;
			}
			case SHOW_TOAST: {
				String text = (String) msg.obj;
				if (text != null && !text.equals("")) {
					Toast.makeText(getApplicationContext(), text,
							Toast.LENGTH_LONG).show();
				}
				break;
			}
			case SHOW_RATE_APP: {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlRate)));
				break;
			}
			}
		}
	};

	@Override
	public void showMoreApps() {
		// TODO Auto-generated method stub
		moreAppsHadler.sendEmptyMessage(0);
	}

	@Override
	public void showRateApps() {
		Message msg = handler.obtainMessage(SHOW_RATE_APP);
		handler.sendMessage(msg);
	}

	@Override
	public void shareTwitter() {

		boolean haveTwitter = false;

		Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
		shareIntent.setType("text/plain");
		shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
				getString(R.string.app_name));
		shareIntent
				.putExtra(android.content.Intent.EXTRA_TEXT, R.drawable.icon);
		PackageManager pm = getPackageManager();
		List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent,
				0);
		for (final ResolveInfo app : activityList) {
			if (app.activityInfo.name.startsWith("com.twitter.android")) {
				// if
				// ("com.twitter.android.PostActivity".equals(app.activityInfo.name))
				// {
				final ActivityInfo activity = app.activityInfo;
				final ComponentName name = new ComponentName(
						activity.applicationInfo.packageName, activity.name);
				shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
				shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
				shareIntent.setComponent(name);
				startActivity(shareIntent);
				haveTwitter = true;
				break;
			}
		}
		if (!haveTwitter) {
			handler.sendEmptyMessage(NOT_INSTALL_TWITTER);
		}
	}

	@SuppressLint("SdCardPath")
	@Override
	public void shareGplus() {
		// TODO Auto-generated method stub
		if (isGooglePlusInstalled()) {
			Intent shareIntent = new PlusShare.Builder(this)
					.setType("text/plain").setText("Nice game!")
					.setContentUrl(Uri.parse("https://play.google.com/"))
					.getIntent();

			startActivityForResult(shareIntent, 0);
		} else {
			googleToat.sendEmptyMessage(0);
		}

	}

	@Override
	public void shareFbDetail(int level) {
		// TODO Auto-generated method stub
		if (!checkConnect(MainActivity.this)) {
			handler.sendEmptyMessage(NOT_CONNECTION);
			// showToat.sendEmptyMessage(1);
		} else if (FacebookDialog.canPresentShareDialog(
				getApplicationContext(),
				FacebookDialog.ShareDialogFeature.SHARE_DIALOG)) {
			publishFeedDialog(level);
		} else {
			// Toast.makeText(getApplicationContext(),
			// "Facebook app is not installed", Toast.LENGTH_LONG).show();
			handler.sendEmptyMessage(NOT_SHARE_FACEBOOK);
			// showToat.sendEmptyMessage(0);
		}
	}

	@SuppressLint("SdCardPath")
	@Override
	public void shareGplusDetail(int level) {
		// TODO Auto-generated method stub
		if (isGooglePlusInstalled()) {
			String pathFile = "/sdcard/screenshot_frkm.png";
			File tmpFile = new File(pathFile);
			String photoUri;
			try {
				photoUri = MediaStore.Images.Media.insertImage(
						getContentResolver(), tmpFile.getAbsolutePath(), null,
						null);
				Intent shareIntent = new PlusShare.Builder(this)
						.setText(
								"I have overcome the game's level " + level
										+ "  " + urlGPlay).setType("image/png")
						.setStream(Uri.parse(photoUri)).getIntent()
						.setPackage("com.google.android.apps.plus");
				startActivityForResult(shareIntent, REQ_START_SHARE);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			googleToat.sendEmptyMessage(0);
		}
	}

	private void publishFeedDialog(int level) {

		OpenGraphObject math = OpenGraphObject.Factory
				.createForPost("freaking_math_adult:math");
		math.setProperty("title", "Freaking math 18+");
		math.setProperty("url", urlGPlay);
		math.setProperty("description", "Together play and get your feeling.");

		String filepath = "/mnt/sdcard/screenshot_frkm.png";
		File imagefile = new File(filepath);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(imagefile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Bitmap bitmap = BitmapFactory.decodeStream(fis);
		List<Bitmap> images = new ArrayList<Bitmap>();
		images.add(bitmap);

		OpenGraphAction action = GraphObject.Factory
				.create(OpenGraphAction.class);
		// "game" --> object
		action.setProperty("math", math);

		FacebookDialog shareDialog = new FacebookDialog.OpenGraphActionDialogBuilder(
				this, action, "freaking_math_adult:play", "math")
				.setImageAttachmentsForObject("math", images, true).build();
		uiHelper.trackPendingDialogCall(shareDialog.present());

		handler.sendEmptyMessage(DISMIS_LOADING_FACE);
	}

	public static void showHashKey(Context context, String packagename) {
		try {
			PackageInfo info = context.getPackageManager().getPackageInfo(
					packagename, PackageManager.GET_SIGNATURES); // Your package
																	// name here
			for (Signature signature : info.signatures) {
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				String key = Base64.encodeToString(md.digest(), Base64.DEFAULT);
				Toast.makeText(context, "key: " + key, Toast.LENGTH_LONG)
						.show();
				System.out.println("---keyhash face:" + key);
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public boolean isGooglePlusInstalled() {
		try {
			getPackageManager().getApplicationInfo(
					"com.google.android.apps.plus", 0);
			return true;
		} catch (PackageManager.NameNotFoundException e) {
			return false;
		}
	}

	@Override
	public void showLoading() {
		handler.sendEmptyMessage(SHOW_LOADING_FACE);
	}

	public boolean checkConnect(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (manager == null) {
			return false;
		}
		boolean is3g = false;
		// For 3G check with device have card3g
		if (manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null) {
			is3g = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
					.isConnectedOrConnecting();
		}
		boolean isWifi = false;
		// For WiFi Check
		if (manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null) {
			isWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
					.isConnectedOrConnecting();
		}

		if (!is3g && !isWifi)
			return false;
		return true;
	}

	@Override
	public void showToast(String text) {
		Message msg = handler.obtainMessage(SHOW_TOAST, text);
		handler.sendMessage(msg);
	}

	@Override
	public boolean getSignedInGPGS() {
		return gameHelper.isSignedIn();
		// return false;
	}

	@Override
	public void loginGPGS() {
		try {
			runOnUiThread(new Runnable() {
				public void run() {
					System.out.println("------login GPGS---------");
					gameHelper.beginUserInitiatedSignIn();
				}
			});
		} catch (final Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void submitScoreGPGS(int score) {
		System.out.println("---submitScoreGPGS:" + score);
		if (getSignedInGPGS()) {
			System.out.println("---submitScoreGPGS: ok login");

			GoogleApiClient googleApiClient = gameHelper.getApiClient();
			Games.Leaderboards.submitScore(googleApiClient,
					"CgkIipzZ-qcNEAIQCA", (long) score);
			// Games.Leaderboards.submitScore(googleApiClient,
			// getString(R.string.leaderboard_highscores),(long)score);
		}
	}

	@Override
	public void unlockAchievementGPGS(String achievementId) {
		// gameHelper.getApiClient().unlockAchievement(achievementId);
	}

	@Override
	public void getLeaderboardGPGS() {
		if (getSignedInGPGS()) {
			startActivityForResult(Games.Leaderboards.getLeaderboardIntent(
					gameHelper.getApiClient(),
					getString(R.string.leaderboard_highscores)), 100);
		} else {
			loginGPGS();
		}
	}

	@Override
	public void getAchievementsGPGS() {
		// startActivityForResult(gameHelper.getApiClient().getAchievementsIntent(),
		// 101);
	}

	@Override
	public void onSignInFailed() {
		System.out.println("----------onSignInFailed--------");
	}

	@Override
	public void onSignInSucceeded() {
		System.out.println("----------onSignInSucceeded--------");
	}


}