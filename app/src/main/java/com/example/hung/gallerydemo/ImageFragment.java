package com.example.hung.gallerydemo;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageFragment extends Fragment {

    private List<File> mPathResource;
    private RecyclerView mRcvImage;
    private ImageAdapter mImageAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rcv_image, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeComponents();
        setDataForRecycleView();
    }

    private void initializeComponents() {
        mRcvImage = getActivity().findViewById(R.id.rcv_image);

        mRcvImage.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRcvImage.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    private void setDataForRecycleView() {

        mPathResource = getUrlFromStorage(new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath()));
        mImageAdapter = new ImageAdapter(mPathResource, getActivity());
        mRcvImage.setAdapter(mImageAdapter);
    }

    private List<File> getUrlFromStorage(File root) {
        List<File> a = new ArrayList<>();
        File[] files = root.listFiles();
        File f;
        for (int i = 0; i < files.length; i++) {
            f = files[i];
            if (files[i].isDirectory()) {
                a.addAll(getUrlFromStorage(f));
            } else {
                if (f.getName().endsWith(".jpg")) {
                    a.add(f);
                }
            }
        }
        return a;
    }


}

