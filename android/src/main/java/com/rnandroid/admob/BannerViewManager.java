package com.rnandroid.admob;

import android.view.View;
import androidx.annotation.Nullable;

import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.annotations.ReactProp;

import com.google.android.gms.ads.AdSize;

import java.util.Map;

public class BannerViewManager extends ViewGroupManager<BannerView> {
  @Override
  public String getName() {
    return "BannerViewManager";
  }

  @Override
  protected BannerView createViewInstance(ThemedReactContext themedReactContext) {
    return new BannerView(themedReactContext);
  }

  @Override
  public void addView(BannerView parent, View child, int index) {
    throw new RuntimeException("AdMobView cannot have subviews");
  }

  @Override
  @Nullable
  public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
      MapBuilder.Builder<String, Object> builder = MapBuilder.builder();
      String[] events = {
          BannerView.EVENT_AD_SIZE_CHANGED,
          BannerView.EVENT_AD_LOAD_FAIL,
          BannerView.EVENT_AD_LOADED,
          BannerView.EVENT_AD_CLICKED,
          BannerView.EVENT_AD_OPENED,
          BannerView.EVENT_AD_CLOSED
      };
      for (int i = 0; i < events.length; i++) {
          builder.put(events[i], MapBuilder.of("registrationName", events[i]));
      }
      return builder.build();
  }

  @ReactProp(name = "adSize")
  public void setAdSize(final BannerView view, final String sizeString) {
      view.setAdSize(getAdSizeFromString(sizeString));
  }

  @ReactProp(name = "adUnitID")
  public void setAdUnitID(final BannerView view, final String adUnitID) {
      view.setAdUnitID(adUnitID);
  }

  private AdSize getAdSizeFromString(String adSize) {
    switch (adSize) {
        case "banner":
            return AdSize.BANNER;
        case "largeBanner":
            return AdSize.LARGE_BANNER;
        case "mediumRectangle":
            return AdSize.MEDIUM_RECTANGLE;
        case "fullBanner":
            return AdSize.FULL_BANNER;
        case "leaderBoard":
            return AdSize.LEADERBOARD;
        case "smartBanner":
            return AdSize.SMART_BANNER;
        default:
            return AdSize.SMART_BANNER;
    }
  }

}
