package org.systers.mentorship.view.fragments;

import java.lang.System;

/**
 * * The fragment is responsible for changing User password
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0014H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lorg/systers/mentorship/view/fragments/ChangePasswordFragment;", "Landroidx/fragment/app/DialogFragment;", "()V", "changePasswordView", "Landroid/view/View;", "changePasswordViewModel", "Lorg/systers/mentorship/viewmodels/ChangePasswordViewModel;", "getChangePasswordViewModel", "()Lorg/systers/mentorship/viewmodels/ChangePasswordViewModel;", "changePasswordViewModel$delegate", "Lkotlin/Lazy;", "confirmPassword", "", "currentPassword", "newPassword", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "", "onResume", "validatePassword", "", "Companion", "app_debug"})
public final class ChangePasswordFragment extends androidx.fragment.app.DialogFragment {
    private final kotlin.Lazy changePasswordViewModel$delegate = null;
    private android.view.View changePasswordView;
    private java.lang.String currentPassword;
    private java.lang.String newPassword;
    private java.lang.String confirmPassword;
    public static final org.systers.mentorship.view.fragments.ChangePasswordFragment.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    private final org.systers.mentorship.viewmodels.ChangePasswordViewModel getChangePasswordViewModel() {
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
    
    private final boolean validatePassword() {
        return false;
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    public ChangePasswordFragment() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/systers/mentorship/view/fragments/ChangePasswordFragment$Companion;", "", "()V", "newInstance", "Lorg/systers/mentorship/view/fragments/ChangePasswordFragment;", "app_debug"})
    public static final class Companion {
        
        /**
         * * Creates an instance of ChangePasswordFragment
         */
        @org.jetbrains.annotations.NotNull()
        public final org.systers.mentorship.view.fragments.ChangePasswordFragment newInstance() {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}