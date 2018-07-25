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
    private static final int SPAN_COUNT = 2;

    private List<File> mPathResource;
    private RecyclerView mRecycleImages;
    private ImageAdapter mImageAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rcv_image, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeComponents();
        setDataForRecycleView();
    }

    private void initializeComponents() {
        mRecycleImages = getActivity().findViewById(R.id.rcv_image);

        mRecycleImages.setLayoutManager(new GridLayoutManager(getActivity(), SPAN_COUNT));
        mRecycleImages.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    private void setDataForRecycleView() {

        mPathResource = getImageFileFromStorage(new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath()));
        mImageAdapter = new ImageAdapter(mPathResource, getActivity());
        mRecycleImages.setAdapter(mImageAdapter);
    }

    private List<File> getImageFileFromStorage(File root) {
        List<File> images = new ArrayList<>();
        File[] files = root.listFiles();
        File f;
        for (int i = 0; i < files.length; i++) {
            f = files[i];
            if (files[i].isDirectory()) {
                images.addAll(getImageFileFromStorage(f));
            } else {
                if (f.getName().endsWith(".jpg")) {
                    images.add(f);
                }
            }
        }
        return images;
    }
}

