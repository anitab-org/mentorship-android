package org.systers.mentorship.utils;

import java.lang.System;

/**
 * * A sealed class to represent error states
 * * in the EditProfileFragment. More error states
 * * can be added in the future as the application
 * * grows in complexity.
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lorg/systers/mentorship/utils/EditProfileFragmentErrorStates;", "", "()V", "EmptyNameError", "NameTooLongError", "NameTooShortError", "Lorg/systers/mentorship/utils/EditProfileFragmentErrorStates$EmptyNameError;", "Lorg/systers/mentorship/utils/EditProfileFragmentErrorStates$NameTooShortError;", "Lorg/systers/mentorship/utils/EditProfileFragmentErrorStates$NameTooLongError;", "app_debug"})
public abstract class EditProfileFragmentErrorStates {
    
    private EditProfileFragmentErrorStates() {
        super();
    }
    
    /**
     * * An object to represent the error case when the
     *     * entered name of the user is empty.
     */
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/systers/mentorship/utils/EditProfileFragmentErrorStates$EmptyNameError;", "Lorg/systers/mentorship/utils/EditProfileFragmentErrorStates;", "()V", "app_debug"})
    public static final class EmptyNameError extends org.systers.mentorship.utils.EditProfileFragmentErrorStates {
        public static final org.systers.mentorship.utils.EditProfileFragmentErrorStates.EmptyNameError INSTANCE = null;
        
        private EmptyNameError() {
            super();
        }
    }
    
    /**
     * * An object to represent the error case when the
     *     * entered name of the user is too short.
     */
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/systers/mentorship/utils/EditProfileFragmentErrorStates$NameTooShortError;", "Lorg/systers/mentorship/utils/EditProfileFragmentErrorStates;", "()V", "app_debug"})
    public static final class NameTooShortError extends org.systers.mentorship.utils.EditProfileFragmentErrorStates {
        public static final org.systers.mentorship.utils.EditProfileFragmentErrorStates.NameTooShortError INSTANCE = null;
        
        private NameTooShortError() {
            super();
        }
    }
    
    /**
     * * An object to represent the error case when the
     *     * entered name of the user is too long.
     */
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/systers/mentorship/utils/EditProfileFragmentErrorStates$NameTooLongError;", "Lorg/systers/mentorship/utils/EditProfileFragmentErrorStates;", "()V", "app_debug"})
    public static final class NameTooLongError extends org.systers.mentorship.utils.EditProfileFragmentErrorStates {
        public static final org.systers.mentorship.utils.EditProfileFragmentErrorStates.NameTooLongError INSTANCE = null;
        
        private NameTooLongError() {
            super();
        }
    }
}