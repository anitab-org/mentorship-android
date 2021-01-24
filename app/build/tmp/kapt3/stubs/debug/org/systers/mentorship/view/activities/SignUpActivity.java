package org.systers.mentorship.view.activities;

import java.lang.System;

/**
 * * This activity will let the user to sign up into the system using name, username,
 * * email and password.
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\u0012\u0010\u0015\u001a\u00020\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0014H\u0014J\b\u0010\u0019\u001a\u00020\u0007H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0012\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lorg/systers/mentorship/view/activities/SignUpActivity;", "Lorg/systers/mentorship/view/activities/BaseActivity;", "()V", "confirmedPassword", "", "email", "isAvailableForBoth", "", "isAvailableToMentor", "name", "needsMentoring", "password", "signUpViewModel", "Lorg/systers/mentorship/viewmodels/SignUpViewModel;", "getSignUpViewModel", "()Lorg/systers/mentorship/viewmodels/SignUpViewModel;", "signUpViewModel$delegate", "Lkotlin/Lazy;", "username", "navigateToLoginActivity", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "validateDetails", "app_debug"})
public final class SignUpActivity extends org.systers.mentorship.view.activities.BaseActivity {
    private final kotlin.Lazy signUpViewModel$delegate = null;
    private java.lang.String name;
    private java.lang.String username;
    private java.lang.String email;
    private java.lang.String password;
    private java.lang.String confirmedPassword;
    private boolean isAvailableToMentor;
    private boolean needsMentoring;
    private boolean isAvailableForBoth;
    private java.util.HashMap _$_findViewCache;
    
    private final org.systers.mentorship.viewmodels.SignUpViewModel getSignUpViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    private final boolean validateDetails() {
        return false;
    }
    
    private final void navigateToLoginActivity() {
    }
    
    public SignUpActivity() {
        super();
    }
}