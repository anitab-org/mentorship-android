package org.systers.mentorship.remote;

import java.lang.System;

/**
 * * Object to keep settings such as URL parts, regions, s3 bucket names.
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u000e\u001a\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010\u00a8\u0006\u0013"}, d2 = {"Lorg/systers/mentorship/remote/BaseUrl;", "", "()V", "DEVELOPMENT_URL", "", "EB_REGION", "LOCALHOST_PORT", "LOCALHOST_URL", "PRODUCTION_URL", "PROTOCOL_HTTPS", "PROTOCOL_S3", "S3_BUCKET_NAME", "S3_DEVELOPMENT_DIRECTORY", "S3_PRODUCTION_DIRECTORY", "apiBaseUrl", "getApiBaseUrl", "()Ljava/lang/String;", "s3BaseUrl", "getS3BaseUrl", "app_release"})
public final class BaseUrl {
    private static final java.lang.String PROTOCOL_HTTPS = "http://";
    private static final java.lang.String PRODUCTION_URL = "systers-mentorship";
    private static final java.lang.String DEVELOPMENT_URL = "systers-mentorship-dev";
    private static final java.lang.String EB_REGION = ".eu-central-1.elasticbeanstalk.com/";
    private static final java.lang.String PROTOCOL_S3 = "s3://";
    private static final java.lang.String S3_BUCKET_NAME = "systers-mentorship/";
    private static final java.lang.String S3_PRODUCTION_DIRECTORY = "prod/";
    private static final java.lang.String S3_DEVELOPMENT_DIRECTORY = "dev/";
    private static final java.lang.String LOCALHOST_URL = "10.0.2.2";
    private static final java.lang.String LOCALHOST_PORT = ":5000";
    public static final org.systers.mentorship.remote.BaseUrl INSTANCE = null;
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getApiBaseUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getS3BaseUrl() {
        return null;
    }
    
    private BaseUrl() {
        super();
    }
}