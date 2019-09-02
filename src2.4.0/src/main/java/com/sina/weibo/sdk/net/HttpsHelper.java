package com.sina.weibo.sdk.net;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

class HttpsHelper {
    private static SSLSocketFactory sslSocketFactory;

    public static class KeyStoresTrustManagerEX implements X509TrustManager {
        protected ArrayList<X509TrustManager> x509TrustManagers = new ArrayList();

        protected KeyStoresTrustManagerEX(KeyStore... keyStoreArr) {
            ArrayList arrayList = new ArrayList();
            try {
                TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                instance.init((KeyStore) null);
                arrayList.add(instance);
                for (KeyStore keyStore : keyStoreArr) {
                    TrustManagerFactory instance2 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                    instance2.init(keyStore);
                    arrayList.add(instance2);
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    for (TrustManager trustManager : ((TrustManagerFactory) it.next()).getTrustManagers()) {
                        if (trustManager instanceof X509TrustManager) {
                            this.x509TrustManagers.add((X509TrustManager) trustManager);
                        }
                    }
                }
                if (this.x509TrustManagers.size() == 0) {
                    throw new RuntimeException("Couldn't find any X509TrustManagers");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            ((X509TrustManager) this.x509TrustManagers.get(0)).checkClientTrusted(x509CertificateArr, str);
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            Iterator it = this.x509TrustManagers.iterator();
            while (it.hasNext()) {
                try {
                    ((X509TrustManager) it.next()).checkServerTrusted(x509CertificateArr, str);
                    return;
                } catch (CertificateException unused) {
                }
            }
        }

        public X509Certificate[] getAcceptedIssuers() {
            ArrayList arrayList = new ArrayList();
            Iterator it = this.x509TrustManagers.iterator();
            while (it.hasNext()) {
                arrayList.addAll(Arrays.asList(((X509TrustManager) it.next()).getAcceptedIssuers()));
            }
            return (X509Certificate[]) arrayList.toArray(new X509Certificate[arrayList.size()]);
        }
    }

    HttpsHelper() {
    }

    private static Certificate getCertificate(String str, Context context) throws CertificateException, IOException {
        CertificateFactory instance = CertificateFactory.getInstance("X.509");
        InputStream open = context.getAssets().open(str);
        try {
            Certificate generateCertificate = instance.generateCertificate(open);
            return generateCertificate;
        } finally {
            if (open != null) {
                open.close();
            }
        }
    }

    public static SSLSocketFactory getSSL(Context context) {
        if (sslSocketFactory != null) {
            return sslSocketFactory;
        }
        try {
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            instance.load(null, null);
            instance.setCertificateEntry("get_global_ca", getCertificate("geo_global_ca.cer", context));
            SSLContext instance2 = SSLContext.getInstance("TLS");
            TrustManager[] trustManagerArr = new TrustManager[1];
            trustManagerArr[0] = new KeyStoresTrustManagerEX(instance);
            instance2.init(null, trustManagerArr, null);
            sslSocketFactory = instance2.getSocketFactory();
        } catch (Exception unused) {
        }
        return sslSocketFactory;
    }
}
