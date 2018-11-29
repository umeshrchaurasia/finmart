package com.datacomp.magicfinmart.webviews;

import android.content.Context;
import android.os.Message;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebChromeclient extends WebChromeClient {
    Context context;
    WebView webView;

    public MyWebChromeclient(Context context, WebView newWebView) {
        this.context = context;
        this.webView = newWebView;
    }

    @Override
    public boolean onCreateWindow(WebView view, boolean isDialog,
                                  boolean isUserGesture, Message resultMsg) {

        WebView newWebView = new WebView(context);
        view.addView(newWebView);
        WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
        transport.setWebView(newWebView);
        resultMsg.sendToTarget();

        newWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                //Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                //browserIntent.setData(Uri.parse(url));
                //context.startActivity(browserIntent);
                return false;
            }
        });
        return true;
    }

}