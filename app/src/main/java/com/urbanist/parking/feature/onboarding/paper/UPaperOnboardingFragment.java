package com.urbanist.parking.feature.onboarding.paper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ramotion.paperonboarding.PaperOnboardingPage;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnChangeListener;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnLeftOutListener;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnRightOutListener;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.urbanist.parking.R;

/**
 * Ready to use PaperOnboarding fragment
 */
public class UPaperOnboardingFragment extends Fragment {

    private static final String ELEMENTS_PARAM = "elements";

    private PaperOnboardingOnChangeListener mOnChangeListener;
    private PaperOnboardingOnRightOutListener mOnRightOutListener;
    private PaperOnboardingOnLeftOutListener mOnLeftOutListener;
    private ArrayList<PaperOnboardingPage> mElements;


    public static UPaperOnboardingFragment newInstance(ArrayList<PaperOnboardingPage> elements) {
        UPaperOnboardingFragment fragment = new UPaperOnboardingFragment();
        Bundle args = new Bundle();
        args.putSerializable(ELEMENTS_PARAM, elements);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mElements = (ArrayList<PaperOnboardingPage>) getArguments().get(ELEMENTS_PARAM);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.onboarding_main_layout, container, false);

        // create engine for onboarding element
        UPaperOnboardingEngine mPaperOnboardingEngine = new UPaperOnboardingEngine(view.findViewById(R.id.onboardingRootView), mElements, getActivity());
        // set listeners
        mPaperOnboardingEngine.setOnChangeListener(mOnChangeListener);
        mPaperOnboardingEngine.setOnLeftOutListener(mOnLeftOutListener);
        mPaperOnboardingEngine.setOnRightOutListener(mOnRightOutListener);

        return view;
    }

    public void setElements(ArrayList<PaperOnboardingPage> elements) {
        this.mElements = elements;
    }

    public ArrayList<PaperOnboardingPage> getElements() {
        return mElements;
    }

    public void setOnChangeListener(PaperOnboardingOnChangeListener onChangeListener) {
        this.mOnChangeListener = onChangeListener;
    }

    public void setOnRightOutListener(PaperOnboardingOnRightOutListener onRightOutListener) {
        this.mOnRightOutListener = onRightOutListener;
    }

    public void setOnLeftOutListener(PaperOnboardingOnLeftOutListener onLeftOutListener) {
        this.mOnLeftOutListener = onLeftOutListener;
    }

}