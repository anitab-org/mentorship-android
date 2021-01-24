package org.systers.mentorship.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import org.systers.mentorship.models.HomeStatistics;

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

  protected FragmentHomeBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, NestedScrollView homeContainer, RecyclerView rvAchievements,
      SwipeRefreshLayout srlHome, AppCompatTextView tvAcceptedRequests,
      AppCompatTextView tvCompletedRelations, AppCompatTextView tvNoAchievements,
      AppCompatTextView tvNumberOfAcceptedRequests, AppCompatTextView tvNumberOfCompletedRelations,
      AppCompatTextView tvNumberOfPendingRequests, AppCompatTextView tvNumberOfRejectedRequests,
      AppCompatTextView tvPendingRequests, AppCompatTextView tvRecentAchievementsTitle,
      AppCompatTextView tvRejectedRequests, AppCompatTextView tvWelcome) {
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

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentHomeBinding>inflate(inflater, org.systers.mentorship.R.layout.fragment_home, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentHomeBinding>inflate(inflater, org.systers.mentorship.R.layout.fragment_home, null, false, component);
  }

  public static FragmentHomeBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static FragmentHomeBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (FragmentHomeBinding)bind(component, view, org.systers.mentorship.R.layout.fragment_home);
  }
}
