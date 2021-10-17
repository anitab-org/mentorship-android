// Generated by data binding compiler. Do not edit!
package org.anitab.mentorship.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import java.lang.Deprecated;
import java.lang.Object;
import org.anitab.mentorship.R;
import org.anitab.mentorship.models.HomeStatistics;

public abstract class FragmentHomeBinding extends ViewDataBinding {
  @NonNull
  public final NestedScrollView homeContainer;

  @NonNull
  public final RecyclerView rvAchievements;

  @NonNull
  public final SwipeRefreshLayout srlHome;

  @NonNull
  public final AppCompatTextView tvAcceptedRequests;

  @NonNull
  public final AppCompatTextView tvCompletedRelations;

  @NonNull
  public final AppCompatTextView tvNoAchievements;

  @NonNull
  public final AppCompatTextView tvNumberOfAcceptedRequests;

  @NonNull
  public final AppCompatTextView tvNumberOfCompletedRelations;

  @NonNull
  public final AppCompatTextView tvNumberOfPendingRequests;

  @NonNull
  public final AppCompatTextView tvNumberOfRejectedRequests;

  @NonNull
  public final AppCompatTextView tvPendingRequests;

  @NonNull
  public final AppCompatTextView tvRecentAchievementsTitle;

  @NonNull
  public final AppCompatTextView tvRejectedRequests;

  @NonNull
  public final AppCompatTextView tvWelcome;

  @Bindable
  protected HomeStatistics mStats;

  protected FragmentHomeBinding(Object _bindingComponent, View _root, int _localFieldCount,
      NestedScrollView homeContainer, RecyclerView rvAchievements, SwipeRefreshLayout srlHome,
      AppCompatTextView tvAcceptedRequests, AppCompatTextView tvCompletedRelations,
      AppCompatTextView tvNoAchievements, AppCompatTextView tvNumberOfAcceptedRequests,
      AppCompatTextView tvNumberOfCompletedRelations, AppCompatTextView tvNumberOfPendingRequests,
      AppCompatTextView tvNumberOfRejectedRequests, AppCompatTextView tvPendingRequests,
      AppCompatTextView tvRecentAchievementsTitle, AppCompatTextView tvRejectedRequests,
      AppCompatTextView tvWelcome) {
    super(_bindingComponent, _root, _localFieldCount);
    this.homeContainer = homeContainer;
    this.rvAchievements = rvAchievements;
    this.srlHome = srlHome;
    this.tvAcceptedRequests = tvAcceptedRequests;
    this.tvCompletedRelations = tvCompletedRelations;
    this.tvNoAchievements = tvNoAchievements;
    this.tvNumberOfAcceptedRequests = tvNumberOfAcceptedRequests;
    this.tvNumberOfCompletedRelations = tvNumberOfCompletedRelations;
    this.tvNumberOfPendingRequests = tvNumberOfPendingRequests;
    this.tvNumberOfRejectedRequests = tvNumberOfRejectedRequests;
    this.tvPendingRequests = tvPendingRequests;
    this.tvRecentAchievementsTitle = tvRecentAchievementsTitle;
    this.tvRejectedRequests = tvRejectedRequests;
    this.tvWelcome = tvWelcome;
  }

  public abstract void setStats(@Nullable HomeStatistics stats);

  @Nullable
  public HomeStatistics getStats() {
    return mStats;
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_home, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentHomeBinding>inflateInternal(inflater, R.layout.fragment_home, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_home, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentHomeBinding>inflateInternal(inflater, R.layout.fragment_home, null, false, component);
  }

  public static FragmentHomeBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static FragmentHomeBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentHomeBinding)bind(component, view, R.layout.fragment_home);
  }
}