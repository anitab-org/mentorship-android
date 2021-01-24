package org.systers.mentorship.databinding;
import org.systers.mentorship.R;
import org.systers.mentorship.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentEditProfileBindingImpl extends FragmentEditProfileBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.imgUserAvatar, 13);
        sViewsWithIds.put(R.id.tiName, 14);
        sViewsWithIds.put(R.id.tiBio, 15);
        sViewsWithIds.put(R.id.tiSlack, 16);
        sViewsWithIds.put(R.id.tiLocation, 17);
        sViewsWithIds.put(R.id.tiOccupation, 18);
        sViewsWithIds.put(R.id.tiOrganization, 19);
        sViewsWithIds.put(R.id.tiSkills, 20);
        sViewsWithIds.put(R.id.tiInterests, 21);
    }
    // views
    @NonNull
    private final android.widget.ScrollView mboundView0;
    @NonNull
    private final com.google.android.material.textfield.TextInputEditText mboundView10;
    @NonNull
    private final com.google.android.material.textfield.TextInputEditText mboundView11;
    @NonNull
    private final com.google.android.material.textfield.TextInputEditText mboundView12;
    @NonNull
    private final com.google.android.material.textfield.TextInputEditText mboundView3;
    @NonNull
    private final com.google.android.material.textfield.TextInputEditText mboundView6;
    @NonNull
    private final com.google.android.material.textfield.TextInputEditText mboundView7;
    @NonNull
    private final com.google.android.material.textfield.TextInputEditText mboundView8;
    @NonNull
    private final com.google.android.material.textfield.TextInputEditText mboundView9;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener mboundView10androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of user.organization
            //         is user.setOrganization((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(mboundView10);
            // localize variables for thread safety
            // user != null
            boolean userJavaLangObjectNull = false;
            // user
            org.systers.mentorship.models.User user = mUser;
            // user.organization
            java.lang.String userOrganization = null;



            userJavaLangObjectNull = (user) != (null);
            if (userJavaLangObjectNull) {




                user.setOrganization(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener mboundView11androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of user.skills
            //         is user.setSkills((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(mboundView11);
            // localize variables for thread safety
            // user != null
            boolean userJavaLangObjectNull = false;
            // user
            org.systers.mentorship.models.User user = mUser;
            // user.skills
            java.lang.String userSkills = null;



            userJavaLangObjectNull = (user) != (null);
            if (userJavaLangObjectNull) {




                user.setSkills(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener mboundView12androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of user.interests
            //         is user.setInterests((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(mboundView12);
            // localize variables for thread safety
            // user != null
            boolean userJavaLangObjectNull = false;
            // user
            org.systers.mentorship.models.User user = mUser;
            // user.interests
            java.lang.String userInterests = null;



            userJavaLangObjectNull = (user) != (null);
            if (userJavaLangObjectNull) {




                user.setInterests(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener mboundView3androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of user.name
            //         is user.setName((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(mboundView3);
            // localize variables for thread safety
            // user.name
            java.lang.String userName = null;
            // user != null
            boolean userJavaLangObjectNull = false;
            // user
            org.systers.mentorship.models.User user = mUser;



            userJavaLangObjectNull = (user) != (null);
            if (userJavaLangObjectNull) {




                user.setName(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener mboundView6androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of user.bio
            //         is user.setBio((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(mboundView6);
            // localize variables for thread safety
            // user != null
            boolean userJavaLangObjectNull = false;
            // user.bio
            java.lang.String userBio = null;
            // user
            org.systers.mentorship.models.User user = mUser;



            userJavaLangObjectNull = (user) != (null);
            if (userJavaLangObjectNull) {




                user.setBio(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener mboundView7androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of user.slackUsername
            //         is user.setSlackUsername((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(mboundView7);
            // localize variables for thread safety
            // user.slackUsername
            java.lang.String userSlackUsername = null;
            // user != null
            boolean userJavaLangObjectNull = false;
            // user
            org.systers.mentorship.models.User user = mUser;



            userJavaLangObjectNull = (user) != (null);
            if (userJavaLangObjectNull) {




                user.setSlackUsername(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener mboundView8androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of user.location
            //         is user.setLocation((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(mboundView8);
            // localize variables for thread safety
            // user != null
            boolean userJavaLangObjectNull = false;
            // user.location
            java.lang.String userLocation = null;
            // user
            org.systers.mentorship.models.User user = mUser;



            userJavaLangObjectNull = (user) != (null);
            if (userJavaLangObjectNull) {




                user.setLocation(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener mboundView9androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of user.occupation
            //         is user.setOccupation((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(mboundView9);
            // localize variables for thread safety
            // user != null
            boolean userJavaLangObjectNull = false;
            // user
            org.systers.mentorship.models.User user = mUser;
            // user.occupation
            java.lang.String userOccupation = null;



            userJavaLangObjectNull = (user) != (null);
            if (userJavaLangObjectNull) {




                user.setOccupation(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener switchAvailableToMentorandroidCheckedAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of user.availableToMentor
            //         is user.setAvailableToMentor((java.lang.Boolean) callbackArg_0)
            boolean callbackArg_0 = switchAvailableToMentor.isChecked();
            // localize variables for thread safety
            // user.availableToMentor
            java.lang.Boolean userAvailableToMentor = null;
            // user != null
            boolean userJavaLangObjectNull = false;
            // user
            org.systers.mentorship.models.User user = mUser;



            userJavaLangObjectNull = (user) != (null);
            if (userJavaLangObjectNull) {




                user.setAvailableToMentor(((java.lang.Boolean) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener switchNeedsMentoringandroidCheckedAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of user.needMentoring
            //         is user.setNeedMentoring((java.lang.Boolean) callbackArg_0)
            boolean callbackArg_0 = switchNeedsMentoring.isChecked();
            // localize variables for thread safety
            // user != null
            boolean userJavaLangObjectNull = false;
            // user
            org.systers.mentorship.models.User user = mUser;
            // user.needMentoring
            java.lang.Boolean userNeedMentoring = null;



            userJavaLangObjectNull = (user) != (null);
            if (userJavaLangObjectNull) {




                user.setNeedMentoring(((java.lang.Boolean) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener tvEmailandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of user.email
            //         is user.setEmail((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(tvEmail);
            // localize variables for thread safety
            // user.email
            java.lang.String userEmail = null;
            // user != null
            boolean userJavaLangObjectNull = false;
            // user
            org.systers.mentorship.models.User user = mUser;



            userJavaLangObjectNull = (user) != (null);
            if (userJavaLangObjectNull) {




                user.setEmail(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener tvUsernameandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of user.username
            //         is user.setUsername((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(tvUsername);
            // localize variables for thread safety
            // user != null
            boolean userJavaLangObjectNull = false;
            // user
            org.systers.mentorship.models.User user = mUser;
            // user.username
            java.lang.String userUsername = null;



            userJavaLangObjectNull = (user) != (null);
            if (userJavaLangObjectNull) {




                user.setUsername(((java.lang.String) (callbackArg_0)));
            }
        }
    };

    public FragmentEditProfileBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 22, sIncludes, sViewsWithIds));
    }
    private FragmentEditProfileBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.appcompat.widget.AppCompatImageView) bindings[13]
            , (android.widget.Switch) bindings[4]
            , (android.widget.Switch) bindings[5]
            , (com.google.android.material.textfield.TextInputLayout) bindings[15]
            , (com.google.android.material.textfield.TextInputLayout) bindings[21]
            , (com.google.android.material.textfield.TextInputLayout) bindings[17]
            , (com.google.android.material.textfield.TextInputLayout) bindings[14]
            , (com.google.android.material.textfield.TextInputLayout) bindings[18]
            , (com.google.android.material.textfield.TextInputLayout) bindings[19]
            , (com.google.android.material.textfield.TextInputLayout) bindings[20]
            , (com.google.android.material.textfield.TextInputLayout) bindings[16]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[1]
            );
        this.mboundView0 = (android.widget.ScrollView) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView10 = (com.google.android.material.textfield.TextInputEditText) bindings[10];
        this.mboundView10.setTag(null);
        this.mboundView11 = (com.google.android.material.textfield.TextInputEditText) bindings[11];
        this.mboundView11.setTag(null);
        this.mboundView12 = (com.google.android.material.textfield.TextInputEditText) bindings[12];
        this.mboundView12.setTag(null);
        this.mboundView3 = (com.google.android.material.textfield.TextInputEditText) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView6 = (com.google.android.material.textfield.TextInputEditText) bindings[6];
        this.mboundView6.setTag(null);
        this.mboundView7 = (com.google.android.material.textfield.TextInputEditText) bindings[7];
        this.mboundView7.setTag(null);
        this.mboundView8 = (com.google.android.material.textfield.TextInputEditText) bindings[8];
        this.mboundView8.setTag(null);
        this.mboundView9 = (com.google.android.material.textfield.TextInputEditText) bindings[9];
        this.mboundView9.setTag(null);
        this.switchAvailableToMentor.setTag(null);
        this.switchNeedsMentoring.setTag(null);
        this.tvEmail.setTag(null);
        this.tvUsername.setTag(null);
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
        if (BR.user == variableId) {
            setUser((org.systers.mentorship.models.User) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setUser(@Nullable org.systers.mentorship.models.User User) {
        this.mUser = User;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.user);
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
        java.lang.String userOccupation = null;
        java.lang.String userUsername = null;
        java.lang.String userName = null;
        java.lang.String userSlackUsername = null;
        java.lang.String userInterests = null;
        java.lang.String userLocation = null;
        java.lang.Boolean userNeedMentoring = null;
        java.lang.String userEmail = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxUserNeedMentoring = false;
        java.lang.String userOrganization = null;
        org.systers.mentorship.models.User user = mUser;
        java.lang.String userBio = null;
        java.lang.Boolean userAvailableToMentor = null;
        java.lang.String userSkills = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxUserAvailableToMentor = false;

        if ((dirtyFlags & 0x3L) != 0) {



                if (user != null) {
                    // read user.occupation
                    userOccupation = user.getOccupation();
                    // read user.username
                    userUsername = user.getUsername();
                    // read user.name
                    userName = user.getName();
                    // read user.slackUsername
                    userSlackUsername = user.getSlackUsername();
                    // read user.interests
                    userInterests = user.getInterests();
                    // read user.location
                    userLocation = user.getLocation();
                    // read user.needMentoring
                    userNeedMentoring = user.getNeedMentoring();
                    // read user.email
                    userEmail = user.getEmail();
                    // read user.organization
                    userOrganization = user.getOrganization();
                    // read user.bio
                    userBio = user.getBio();
                    // read user.availableToMentor
                    userAvailableToMentor = user.getAvailableToMentor();
                    // read user.skills
                    userSkills = user.getSkills();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(user.needMentoring)
                androidxDatabindingViewDataBindingSafeUnboxUserNeedMentoring = androidx.databinding.ViewDataBinding.safeUnbox(userNeedMentoring);
                // read androidx.databinding.ViewDataBinding.safeUnbox(user.availableToMentor)
                androidxDatabindingViewDataBindingSafeUnboxUserAvailableToMentor = androidx.databinding.ViewDataBinding.safeUnbox(userAvailableToMentor);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView10, userOrganization);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView11, userSkills);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView12, userInterests);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView3, userName);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView6, userBio);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView7, userSlackUsername);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView8, userLocation);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView9, userOccupation);
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(this.switchAvailableToMentor, androidxDatabindingViewDataBindingSafeUnboxUserAvailableToMentor);
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(this.switchNeedsMentoring, androidxDatabindingViewDataBindingSafeUnboxUserNeedMentoring);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvEmail, userEmail);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvUsername, userUsername);
        }
        if ((dirtyFlags & 0x2L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.mboundView10, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, mboundView10androidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.mboundView11, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, mboundView11androidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.mboundView12, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, mboundView12androidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.mboundView3, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, mboundView3androidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.mboundView6, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, mboundView6androidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.mboundView7, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, mboundView7androidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.mboundView8, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, mboundView8androidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.mboundView9, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, mboundView9androidTextAttrChanged);
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setListeners(this.switchAvailableToMentor, (android.widget.CompoundButton.OnCheckedChangeListener)null, switchAvailableToMentorandroidCheckedAttrChanged);
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setListeners(this.switchNeedsMentoring, (android.widget.CompoundButton.OnCheckedChangeListener)null, switchNeedsMentoringandroidCheckedAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.tvEmail, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, tvEmailandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.tvUsername, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, tvUsernameandroidTextAttrChanged);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): user
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}