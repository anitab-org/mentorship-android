package org.anitab.mentorship.view.activities;

import java.lang.System;

/**
 * This activity will let the user to sign up into the system using name, username,
 * email and password.
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001e\u001a\u00020\u001fH\u0002J\u0012\u0010 \u001a\u00020\u001f2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0014J\b\u0010#\u001a\u00020\u001fH\u0014J\b\u0010$\u001a\u00020\bH\u0002J\b\u0010%\u001a\u00020\u001fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\rX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\rX\u0082D\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u001a\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\rX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\rX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lorg/anitab/mentorship/view/activities/SignUpActivity;", "Lorg/anitab/mentorship/view/activities/BaseActivity;", "()V", "confirmedPassword", "", "email", "emailPattern", "hasEmptyFields", "", "isAvailableForBoth", "isAvailableToMentor", "name", "nameMaxLength", "", "nameMinLength", "namePattern", "needsMentoring", "password", "passwordMaxLength", "passwordMinLength", "signUpViewModel", "Lorg/anitab/mentorship/viewmodels/SignUpViewModel;", "getSignUpViewModel", "()Lorg/anitab/mentorship/viewmodels/SignUpViewModel;", "signUpViewModel$delegate", "Lkotlin/Lazy;", "username", "usernameMaxLength", "usernameMinLength", "usernamePattern", "navigateToLoginActivity", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "validateDetails", "validateDetailsOnRuntime", "app_debug"})
public final class SignUpActivity extends org.anitab.mentorship.view.activities.BaseActivity {
    private final kotlin.Lazy signUpViewModel$delegate = null;
    private java.lang.String name;
    private java.lang.String username;
    private java.lang.String email;
    private java.lang.String password;
    private java.lang.String confirmedPassword;
    private boolean isAvailableToMentor = false;
    private boolean needsMentoring = false;
    private boolean isAvailableForBoth = false;
    private boolean hasEmptyFields = true;
    private final java.lang.String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private final java.lang.String usernamePattern = "^[a-zA-Z0-9_]+$";
    private final java.lang.String namePattern = "^[a-zA-Z\\s\\-]+$";
    private final int usernameMaxLength = 30;
    private final int usernameMinLength = 5;
    private final int nameMaxLength = 30;
    private final int nameMinLength = 2;
    private final int passwordMaxLength = 64;
    private final int passwordMinLength = 8;
    private java.util.HashMap _$_findViewCache;
    
    private final org.anitab.mentorship.viewmodels.SignUpViewModel getSignUpViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    private final void validateDetailsOnRuntime() {
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