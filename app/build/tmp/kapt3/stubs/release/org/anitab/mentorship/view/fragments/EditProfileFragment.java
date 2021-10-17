package org.anitab.mentorship.view.fragments;

import java.lang.System;

/**
 * The fragment is responsible for editing the User's profile
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 ,2\u00020\u0001:\u0001,B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J&\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020#H\u0016J\b\u0010$\u001a\u00020 H\u0016J\u0010\u0010%\u001a\u00020 2\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u001d\u0010&\u001a\b\u0012\u0004\u0012\u00020(0\'2\b\u0010)\u001a\u0004\u0018\u00010*H\u0002\u00a2\u0006\u0002\u0010+R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006-"}, d2 = {"Lorg/anitab/mentorship/view/fragments/EditProfileFragment;", "Landroidx/fragment/app/DialogFragment;", "()V", "builder", "Landroidx/appcompat/app/AlertDialog;", "getBuilder", "()Landroidx/appcompat/app/AlertDialog;", "setBuilder", "(Landroidx/appcompat/app/AlertDialog;)V", "currentUser", "Lorg/anitab/mentorship/models/User;", "editProfileBinding", "Lorg/anitab/mentorship/databinding/FragmentEditProfileBinding;", "onDismissListener", "Landroid/content/DialogInterface$OnDismissListener;", "profileViewModel", "Lorg/anitab/mentorship/viewmodels/ProfileViewModel;", "getProfileViewModel", "()Lorg/anitab/mentorship/viewmodels/ProfileViewModel;", "profileViewModel$delegate", "Lkotlin/Lazy;", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "", "onDismiss", "dialog", "Landroid/content/DialogInterface;", "onResume", "setOnDismissListener", "validateProfileInput", "", "Lorg/anitab/mentorship/utils/EditProfileFragmentErrorStates;", "name", "", "(Ljava/lang/String;)[Lorg/anitab/mentorship/utils/EditProfileFragmentErrorStates;", "Companion", "app_release"})
public final class EditProfileFragment extends androidx.fragment.app.DialogFragment {
    private final kotlin.Lazy profileViewModel$delegate = null;
    private org.anitab.mentorship.databinding.FragmentEditProfileBinding editProfileBinding;
    private android.content.DialogInterface.OnDismissListener onDismissListener;
    private org.anitab.mentorship.models.User currentUser;
    public androidx.appcompat.app.AlertDialog builder;
    private static org.anitab.mentorship.models.User tempUser;
    @org.jetbrains.annotations.NotNull()
    public static final org.anitab.mentorship.view.fragments.EditProfileFragment.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    private final org.anitab.mentorship.viewmodels.ProfileViewModel getProfileViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.appcompat.app.AlertDialog getBuilder() {
        return null;
    }
    
    public final void setBuilder(@org.jetbrains.annotations.NotNull()
    androidx.appcompat.app.AlertDialog p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.app.Dialog onCreateDialog(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    private final org.anitab.mentorship.utils.EditProfileFragmentErrorStates[] validateProfileInput(java.lang.String name) {
        return null;
    }
    
    public final void setOnDismissListener(@org.jetbrains.annotations.Nullable()
    android.content.DialogInterface.OnDismissListener onDismissListener) {
    }
    
    @java.lang.Override()
    public void onDismiss(@org.jetbrains.annotations.NotNull()
    android.content.DialogInterface dialog) {
    }
    
    public EditProfileFragment() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lorg/anitab/mentorship/view/fragments/EditProfileFragment$Companion;", "", "()V", "tempUser", "Lorg/anitab/mentorship/models/User;", "newInstance", "Lorg/anitab/mentorship/view/fragments/EditProfileFragment;", "user", "app_release"})
    public static final class Companion {
        
        /**
         * Creates an instance of EditProfileFragment
         */
        @org.jetbrains.annotations.NotNull()
        public final org.anitab.mentorship.view.fragments.EditProfileFragment newInstance(@org.jetbrains.annotations.NotNull()
        org.anitab.mentorship.models.User user) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}