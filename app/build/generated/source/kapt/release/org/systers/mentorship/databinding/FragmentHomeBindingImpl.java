package org.systers.mentorship.databinding;
import org.systers.mentorship.R;
import org.systers.mentorship.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentHomeBindingImpl extends FragmentHomeBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.homeContainer, 6);
        sViewsWithIds.put(R.id.tvPendingRequests, 7);
        sViewsWithIds.put(R.id.tvAcceptedRequests, 8);
        sViewsWithIds.put(R.id.tvRejectedRequests, 9);
        sViewsWithIds.put(R.id.tvCompletedRelations, 10);
        sViewsWithIds.put(R.id.tvRecentAchievementsTitle, 11);
        sViewsWithIds.put(R.id.rvAchievements, 12);
        sViewsWithIds.put(R.id.tvNoAchievements, 13);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentHomeBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }
    private FragmentHomeBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.core.widget.NestedScrollView) bindings[6]
            , (androidx.recyclerview.widget.RecyclerView) bindings[12]
            , (androidx.swiperefreshlayout.widget.SwipeRefreshLayout) bindings[0]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[8]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[10]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[13]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[3]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[5]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[2]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[4]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[7]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[11]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[9]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[1]
            );
        this.srlHome.setTag(null);
        this.tvNumberOfAcceptedRequests.setTag(null);
        this.tvNumberOfCompletedRelations.setTag(null);
        this.tvNumberOfPendingRequests.setTag(null);
        this.tvNumberOfRejectedRequests.setTag(null);
        this.tvWelcome.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.stats == variableId) {
            setStats((org.systers.mentorship.models.HomeStatistics) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setStats(@Nullable org.systers.mentorship.models.HomeStatistics Stats) {
        this.mStats = Stats;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.stats);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        int statsCompletedRelations = 0;
        java.lang.String statsJavaLangObjectNullTvWelcomeAndroidStringWelcomeJavaLangStringStatsNameJavaLangStringJavaLangStringWelcome = null;
        java.lang.String stringValueOfStatsPendingRequests = null;
        boolean statsJavaLangObjectNull = false;
        java.lang.String statsName = null;
        java.lang.String stringValueOfStatsCompletedRelations = null;
        int statsPendingRequests = 0;
        java.lang.String tvWelcomeAndroidStringWelcomeJavaLangStringStatsName = null;
        int statsAcceptedRequests = 0;
        java.lang.String statsJavaLangObjectNullStringValueOfStatsAcceptedRequestsStringValueOfInt0 = null;
        java.lang.String statsJavaLangObjectNullStringValueOfStatsRejectedRequestsStringValueOfInt0 = null;
        int statsRejectedRequests = 0;
        java.lang.String statsJavaLangObjectNullStringValueOfStatsPendingRequestsStringValueOfInt0 = null;
        java.lang.String stringValueOfStatsRejectedRequests = null;
        java.lang.String stringValueOfStatsAcceptedRequests = null;
        java.lang.String statsJavaLangObjectNullStringValueOfStatsCompletedRelationsStringValueOfInt0 = null;
        java.lang.String tvWelcomeAndroidStringWelcomeJavaLangStringStatsNameJavaLangString = null;
        org.systers.mentorship.models.HomeStatistics stats = mStats;

        if ((dirtyFlags & 0x3L) != 0) {



                // read stats != null
                statsJavaLangObjectNull = (stats) != (null);
            if((dirtyFlags & 0x3L) != 0) {
                if(statsJavaLangObjectNull) {
                        dirtyFlags |= 0x8L;
                        dirtyFlags |= 0x20L;
                        dirtyFlags |= 0x80L;
                        dirtyFlags |= 0x200L;
                        dirtyFlags |= 0x800L;
                }
                else {
                        dirtyFlags |= 0x4L;
                        dirtyFlags |= 0x10L;
                        dirtyFlags |= 0x40L;
                        dirtyFlags |= 0x100L;
                        dirtyFlags |= 0x400L;
                }
            }
        }
        // batch finished

        if ((dirtyFlags & 0x800L) != 0) {

                if (stats != null) {
                    // read stats.completedRelations
                    statsCompletedRelations = stats.getCompletedRelations();
                }


                // read String.valueOf(stats.completedRelations)
                stringValueOfStatsCompletedRelations = java.lang.String.valueOf(statsCompletedRelations);
        }
        if ((dirtyFlags & 0x8L) != 0) {

                if (stats != null) {
                    // read stats.name
                    statsName = stats.getName();
                }


                // read ((@android:string/welcome) + (", ")) + (stats.name)
                tvWelcomeAndroidStringWelcomeJavaLangStringStatsName = ((tvWelcome.getResources().getString(R.string.welcome)) + (", ")) + (statsName);


                // read (((@android:string/welcome) + (", ")) + (stats.name)) + ("!")
                tvWelcomeAndroidStringWelcomeJavaLangStringStatsNameJavaLangString = (tvWelcomeAndroidStringWelcomeJavaLangStringStatsName) + ("!");
        }
        if ((dirtyFlags & 0x200L) != 0) {

                if (stats != null) {
                    // read stats.pendingRequests
                    statsPendingRequests = stats.getPendingRequests();
                }


                // read String.valueOf(stats.pendingRequests)
                stringValueOfStatsPendingRequests = java.lang.String.valueOf(statsPendingRequests);
        }
        if ((dirtyFlags & 0x20L) != 0) {

                if (stats != null) {
                    // read stats.acceptedRequests
                    statsAcceptedRequests = stats.getAcceptedRequests();
                }


                // read String.valueOf(stats.acceptedRequests)
                stringValueOfStatsAcceptedRequests = java.lang.String.valueOf(statsAcceptedRequests);
        }
        if ((dirtyFlags & 0x80L) != 0) {

                if (stats != null) {
                    // read stats.rejectedRequests
                    statsRejectedRequests = stats.getRejectedRequests();
                }


                // read String.valueOf(stats.rejectedRequests)
                stringValueOfStatsRejectedRequests = java.lang.String.valueOf(statsRejectedRequests);
        }

        if ((dirtyFlags & 0x3L) != 0) {

                // read stats != null ? (((@android:string/welcome) + (", ")) + (stats.name)) + ("!") : "Welcome!"
                statsJavaLangObjectNullTvWelcomeAndroidStringWelcomeJavaLangStringStatsNameJavaLangStringJavaLangStringWelcome = ((statsJavaLangObjectNull) ? (tvWelcomeAndroidStringWelcomeJavaLangStringStatsNameJavaLangString) : ("Welcome!"));
                // read stats != null ? String.valueOf(stats.acceptedRequests) : String.valueOf(0)
                statsJavaLangObjectNullStringValueOfStatsAcceptedRequestsStringValueOfInt0 = ((statsJavaLangObjectNull) ? (stringValueOfStatsAcceptedRequests) : (java.lang.String.valueOf(0)));
                // read stats != null ? String.valueOf(stats.rejectedRequests) : String.valueOf(0)
                statsJavaLangObjectNullStringValueOfStatsRejectedRequestsStringValueOfInt0 = ((statsJavaLangObjectNull) ? (stringValueOfStatsRejectedRequests) : (java.lang.String.valueOf(0)));
                // read stats != null ? String.valueOf(stats.pendingRequests) : String.valueOf(0)
                statsJavaLangObjectNullStringValueOfStatsPendingRequestsStringValueOfInt0 = ((statsJavaLangObjectNull) ? (stringValueOfStatsPendingRequests) : (java.lang.String.valueOf(0)));
                // read stats != null ? String.valueOf(stats.completedRelations) : String.valueOf(0)
                statsJavaLangObjectNullStringValueOfStatsCompletedRelationsStringValueOfInt0 = ((statsJavaLangObjectNull) ? (stringValueOfStatsCompletedRelations) : (java.lang.String.valueOf(0)));
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvNumberOfAcceptedRequests, statsJavaLangObjectNullStringValueOfStatsAcceptedRequestsStringValueOfInt0);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvNumberOfCompletedRelations, statsJavaLangObjectNullStringValueOfStatsCompletedRelationsStringValueOfInt0);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvNumberOfPendingRequests, statsJavaLangObjectNullStringValueOfStatsPendingRequestsStringValueOfInt0);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvNumberOfRejectedRequests, statsJavaLangObjectNullStringValueOfStatsRejectedRequestsStringValueOfInt0);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvWelcome, statsJavaLangObjectNullTvWelcomeAndroidStringWelcomeJavaLangStringStatsNameJavaLangStringJavaLangStringWelcome);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): stats
        flag 1 (0x2L): null
        flag 2 (0x3L): stats != null ? (((@android:string/welcome) + (", ")) + (stats.name)) + ("!") : "Welcome!"
        flag 3 (0x4L): stats != null ? (((@android:string/welcome) + (", ")) + (stats.name)) + ("!") : "Welcome!"
        flag 4 (0x5L): stats != null ? String.valueOf(stats.acceptedRequests) : String.valueOf(0)
        flag 5 (0x6L): stats != null ? String.valueOf(stats.acceptedRequests) : String.valueOf(0)
        flag 6 (0x7L): stats != null ? String.valueOf(stats.rejectedRequests) : String.valueOf(0)
        flag 7 (0x8L): stats != null ? String.valueOf(stats.rejectedRequests) : String.valueOf(0)
        flag 8 (0x9L): stats != null ? String.valueOf(stats.pendingRequests) : String.valueOf(0)
        flag 9 (0xaL): stats != null ? String.valueOf(stats.pendingRequests) : String.valueOf(0)
        flag 10 (0xbL): stats != null ? String.valueOf(stats.completedRelations) : String.valueOf(0)
        flag 11 (0xcL): stats != null ? String.valueOf(stats.completedRelations) : String.valueOf(0)
    flag mapping end*/
    //end
}