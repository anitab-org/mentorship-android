package org.anitab.mentorship.view.activities;

import java.lang.System;

/**
 * An Activity class which other Activities can extend from. It provides some basic functions like
 * showing/hiding progress dialog bars, hiding keyboard etc.
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bH\u0004J\b\u0010\f\u001a\u00020\nH\u0004J\u0006\u0010\r\u001a\u00020\nJ\u001e\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0010J\b\u0010\u0014\u001a\u00020\nH\u0004J\u000e\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0017R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lorg/anitab/mentorship/view/activities/BaseActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "progressBar", "Lorg/anitab/mentorship/utils/ProgressBar;", "progressDialog", "Landroid/app/ProgressDialog;", "getRootView", "Landroid/view/View;", "hideKeyboard", "", "view", "hideProgressBar", "hideProgressDialog", "replaceFragment", "containerId", "", "fragment", "Landroidx/fragment/app/Fragment;", "title", "showProgressBar", "showProgressDialog", "message", "", "app_debug"})
public abstract class BaseActivity extends androidx.appcompat.app.AppCompatActivity {
    private android.app.ProgressDialog progressDialog;
    private org.anitab.mentorship.utils.ProgressBar progressBar;
    private java.util.HashMap _$_findViewCache;
    
    /**
     * Shows a [ProgressDialog]. This is used when fetching data from a remote source
     * @param message text to be shown while he progress dialog is being shown
     */
    public final void showProgressDialog(@org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    /**
     * Hides the [ProgressDialog]. This is called when fetching data from a remote source finishes
     */
    public final void hideProgressDialog() {
    }
    
    protected final void showProgressBar() {
    }
    
    protected final void hideProgressBar() {
    }
    
    protected final void hideKeyboard(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final android.view.View getRootView() {
        return null;
    }
    
    /**
     * The [fragment] is added to the container view with id [containerId]. The operation is
     * performed by the FragmentManager.
     */
    public final void replaceFragment(int containerId, @org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment fragment, int title) {
    }
    
    public BaseActivity() {
        super();
    }
}