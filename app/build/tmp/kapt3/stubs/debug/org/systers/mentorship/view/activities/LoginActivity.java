package org.systers.mentorship.view.activities;

import java.lang.System;

/**
 * * This activity will let the user to login using username/email and password.
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u000fH\u0002J\u0012\u0010\u0011\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u000fH\u0014J\b\u0010\u0015\u001a\u00020\u0016H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lorg/systers/mentorship/view/activities/LoginActivity;", "Lorg/systers/mentorship/view/activities/BaseActivity;", "()V", "loginViewModel", "Lorg/systers/mentorship/viewmodels/LoginViewModel;", "getLoginViewModel", "()Lorg/systers/mentorship/viewmodels/LoginViewModel;", "loginViewModel$delegate", "Lkotlin/Lazy;", "password", "", "textWatcher", "Landroid/text/TextWatcher;", "username", "checkFieldsForEmptyValues", "", "login", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "validateCredentials", "", "app_debug"})
public final class LoginActivity extends org.systers.mentorship.view.activities.BaseActivity {
    private final kotlin.Lazy loginViewModel$delegate = null;
    private java.lang.String username;
    private java.lang.String password;
    private final android.text.TextWatcher textWatcher = null;
    private java.util.HashMap _$_findViewCache;
    
    private final org.systers.mentorship.viewmodels.LoginViewModel getLoginViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void checkFieldsForEmptyValues() {
    }
    
    private final boolean validateCredentials() {
        return false;
    }
    
    private final void login() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    public LoginActivity() {
        super();
    }
}