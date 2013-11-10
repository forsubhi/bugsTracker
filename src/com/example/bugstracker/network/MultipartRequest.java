package com.example.bugstracker.network;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import ch.boye.httpclientandroidlib.entity.mime.MultipartEntity;
import ch.boye.httpclientandroidlib.entity.mime.content.FileBody;
import ch.boye.httpclientandroidlib.entity.mime.content.StringBody;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.example.bugstracker.app.BugTrackerApp;



public class MultipartRequest extends Request<String> {

    private MultipartEntity entity = new MultipartEntity();

    private static final String FILE_PART_NAME = "resim";
    private static final String REVIEW_TITLE_PART_NAME = "subject";
    private static final String REVIEW_BODY_PART_NAME = "data";

    private final Response.Listener<String> mListener;

    public MultipartRequest(String url, Response.ErrorListener errorListener, Response.Listener<String> listener)
    {
        super(Method.POST, url, errorListener);
        mListener = listener;
        
        buildMultipartEntity();
        
        setRetryPolicy(new DefaultRetryPolicy(
                1000, 
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, 
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    private void buildMultipartEntity()
    {
    	
    	BugTrackerApp.imageFile.renameTo(new File(BugTrackerApp.imageFile.getAbsolutePath().replace(".tmp", ".png")));

    	entity.addPart(FILE_PART_NAME, new FileBody(BugTrackerApp.imageFile));

    	BugTrackerApp.imageFile.renameTo(new File(BugTrackerApp.imageFile.getAbsolutePath().replace(".png", ".tmp")));

    	try
        {
            entity.addPart(REVIEW_TITLE_PART_NAME,new StringBody(BugTrackerApp.reviewTitle));
            entity.addPart(REVIEW_BODY_PART_NAME, new StringBody(BugTrackerApp.reviewBody));
        }
        catch (UnsupportedEncodingException e)
        {
            VolleyLog.e("UnsupportedEncodingException");
        }
    }

    @Override
    public String getBodyContentType()
    {
        return entity.getContentType().getValue();
    }

    @Override
    public byte[] getBody() throws AuthFailureError
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try
        {
            entity.writeTo(bos);
        }
        catch (IOException e)
        {
            VolleyLog.e("IOException writing to ByteArrayOutputStream");
        }
        return bos.toByteArray();
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response)
    {
        return Response.success("Uploaded", getCacheEntry());
    }

    @Override
    protected void deliverResponse(String response)
    {
        mListener.onResponse(response);
    }
}