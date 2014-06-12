package vn.pp;

public interface ControlHandler {
	public void shareFb();
	public void shareTwitter();
	public void shareGplus();
	public void shareFbDetail(int level);
	public void shareGplusDetail(int level);
	//ads
	public void showAd(boolean show);
	public void showFullAd(boolean show);
	public boolean showExitAd(boolean show);
	
	public void showMoreApps();
	public void showRateApps();
	public void showLoading();
	
	public void showToast(String text);
	
}
