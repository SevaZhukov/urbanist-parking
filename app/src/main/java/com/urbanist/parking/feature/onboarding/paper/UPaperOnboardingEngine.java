package com.urbanist.parking.feature.onboarding.paper;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.os.Build.VERSION;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;
import com.ramotion.paperonboarding.PaperOnboardingPage;
import com.ramotion.paperonboarding.R.drawable;
import com.ramotion.paperonboarding.R.id;
import com.ramotion.paperonboarding.R.layout;
import com.ramotion.paperonboarding.listeners.AnimatorEndListener;
import com.ramotion.paperonboarding.listeners.OnSwipeListener;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnChangeListener;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnLeftOutListener;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnRightOutListener;
import com.ramotion.paperonboarding.utils.PaperOnboardingEngineDefaults;
import com.urbanist.parking.R;

import java.util.ArrayList;

public class UPaperOnboardingEngine implements PaperOnboardingEngineDefaults {
    private final float dpToPixelsScaleFactor;
    private final RelativeLayout mRootLayout;
    private final FrameLayout mContentTextContainer;
    private final FrameLayout mContentIconContainer;
    private final FrameLayout mBackgroundContainer;
    private final LinearLayout mPagerIconsContainer;
    private final RelativeLayout mContentRootLayout;
    private final LinearLayout mContentCenteredContainer;
    private final Context mAppContext;
    private ArrayList<PaperOnboardingPage> mElements = new ArrayList();
    private int mActiveElementIndex = 0;
    private int mPagerElementActiveSize;
    private int mPagerElementNormalSize;
    private int mPagerElementLeftMargin;
    private int mPagerElementRightMargin;
    private PaperOnboardingOnChangeListener mOnChangeListener;
    private PaperOnboardingOnRightOutListener mOnRightOutListener;
    private PaperOnboardingOnLeftOutListener mOnLeftOutListener;

    public UPaperOnboardingEngine(View rootLayout, ArrayList<PaperOnboardingPage> contentElements, Context appContext) {
        if (contentElements != null && !contentElements.isEmpty()) {
            this.mElements.addAll(contentElements);
            this.mAppContext = appContext.getApplicationContext();
            this.mRootLayout = (RelativeLayout)rootLayout;
            this.mContentTextContainer = (FrameLayout)rootLayout.findViewById(id.onboardingContentTextContainer);
            this.mContentIconContainer = (FrameLayout)rootLayout.findViewById(id.onboardingContentIconContainer);
            this.mBackgroundContainer = (FrameLayout)rootLayout.findViewById(id.onboardingBackgroundContainer);
            this.mPagerIconsContainer = (LinearLayout)rootLayout.findViewById(id.onboardingPagerIconsContainer);
            this.mContentRootLayout = (RelativeLayout)this.mRootLayout.getChildAt(1);
            this.mContentCenteredContainer = (LinearLayout)this.mContentRootLayout.getChildAt(0);
            this.dpToPixelsScaleFactor = this.mAppContext.getResources().getDisplayMetrics().density;
            this.initializeStartingState();
            this.mRootLayout.setOnTouchListener(new OnSwipeListener(this.mAppContext) {
                public void onSwipeLeft() {
                    UPaperOnboardingEngine.this.toggleContent(false);
                }

                public void onSwipeRight() {
                    UPaperOnboardingEngine.this.toggleContent(true);
                }
            });
            this.mRootLayout.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    if (VERSION.SDK_INT >= 16) {
                        UPaperOnboardingEngine.this.mRootLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    } else {
                        UPaperOnboardingEngine.this.mRootLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }

                    UPaperOnboardingEngine.this.mPagerElementActiveSize = UPaperOnboardingEngine.this.mPagerIconsContainer.getHeight();
                    UPaperOnboardingEngine.this.mPagerElementNormalSize = Math.min(UPaperOnboardingEngine.this.mPagerIconsContainer.getChildAt(0).getHeight(), UPaperOnboardingEngine.this.mPagerIconsContainer.getChildAt(UPaperOnboardingEngine.this.mPagerIconsContainer.getChildCount() - 1).getHeight());
                    MarginLayoutParams layoutParams = (MarginLayoutParams) UPaperOnboardingEngine.this.mPagerIconsContainer.getChildAt(0).getLayoutParams();
                    UPaperOnboardingEngine.this.mPagerElementLeftMargin = layoutParams.leftMargin;
                    UPaperOnboardingEngine.this.mPagerElementRightMargin = layoutParams.rightMargin;
                    UPaperOnboardingEngine.this.mPagerIconsContainer.setX((float) UPaperOnboardingEngine.this.calculateNewPagerPosition(0));
                    UPaperOnboardingEngine.this.mContentCenteredContainer.setY((float)((UPaperOnboardingEngine.this.mContentRootLayout.getHeight() - UPaperOnboardingEngine.this.mContentCenteredContainer.getHeight()) / 2));
                }
            });
        } else {
            throw new IllegalArgumentException("No content elements provided");
        }
    }

    protected int calculateNewPagerPosition(int newActiveElement) {
        ++newActiveElement;
        if (newActiveElement <= 0) {
            newActiveElement = 1;
        }

        int pagerActiveElemCenterPosX = this.mPagerElementActiveSize / 2 + newActiveElement * this.mPagerElementLeftMargin + (newActiveElement - 1) * (this.mPagerElementNormalSize + this.mPagerElementRightMargin);
        return this.mRootLayout.getWidth() / 2 - pagerActiveElemCenterPosX;
    }

    protected int[] calculateCurrentCenterCoordinatesOfPagerElement(int activeElementIndex) {
        int y = (int)(this.mPagerIconsContainer.getY() + (float)(this.mPagerIconsContainer.getHeight() / 2));
        if (activeElementIndex >= this.mPagerIconsContainer.getChildCount()) {
            return new int[]{this.mRootLayout.getWidth() / 2, y};
        } else {
            View pagerElem = this.mPagerIconsContainer.getChildAt(activeElementIndex);
            int x = (int)(this.mPagerIconsContainer.getX() + pagerElem.getX() + (float)(pagerElem.getWidth() / 2));
            return new int[]{x, y};
        }
    }

    protected void initializeStartingState() {
        for(int i = 0; i < this.mElements.size(); ++i) {
            PaperOnboardingPage PaperOnboardingPage = (PaperOnboardingPage)this.mElements.get(i);
            ViewGroup bottomBarIconElement = this.createPagerIconElement(PaperOnboardingPage.getBottomBarIconRes(), i == 0);
            this.mPagerIconsContainer.addView(bottomBarIconElement);
        }

        PaperOnboardingPage activeElement = this.getActiveElement();
        ViewGroup initialContentText = this.createContentTextView(activeElement);
        this.mContentTextContainer.addView(initialContentText);
        ImageView initContentIcon = this.createContentIconView(activeElement);
        this.mContentIconContainer.addView(initContentIcon);
        this.mRootLayout.setBackgroundColor(activeElement.getBgColor());
    }

    protected void toggleContent(boolean prev) {
        int oldElementIndex = this.mActiveElementIndex;
        PaperOnboardingPage newElement = prev ? this.toggleToPreviousElement() : this.toggleToNextElement();
        if (newElement == null) {
            if (prev && this.mOnLeftOutListener != null) {
                this.mOnLeftOutListener.onLeftOut();
            }

            if (!prev && this.mOnRightOutListener != null) {
                this.mOnRightOutListener.onRightOut();
            }

        } else {
            int newPagerPosX = this.calculateNewPagerPosition(this.mActiveElementIndex);
            AnimatorSet bgAnimation = this.createBGAnimatorSet(newElement.getBgColor());
            Animator pagerMoveAnimation = ObjectAnimator.ofFloat(this.mPagerIconsContainer, "x", new float[]{this.mPagerIconsContainer.getX(), (float)newPagerPosX});
            pagerMoveAnimation.setDuration(700L);
            AnimatorSet pagerIconAnimation = this.createPagerIconAnimation(oldElementIndex, this.mActiveElementIndex);
            ViewGroup newContentText = this.createContentTextView(newElement);
            this.mContentTextContainer.addView(newContentText);
            AnimatorSet contentTextShowAnimation = this.createContentTextShowAnimation(this.mContentTextContainer.getChildAt(this.mContentTextContainer.getChildCount() - 2), newContentText);
            ImageView newContentIcon = this.createContentIconView(newElement);
            this.mContentIconContainer.addView(newContentIcon);
            AnimatorSet contentIconShowAnimation = this.createContentIconShowAnimation(this.mContentIconContainer.getChildAt(this.mContentIconContainer.getChildCount() - 2), newContentIcon);
            Animator centerContentAnimation = this.createContentCenteringVerticalAnimation(newContentText, newContentIcon);
            centerContentAnimation.start();
            bgAnimation.start();
            pagerMoveAnimation.start();
            pagerIconAnimation.start();
            contentIconShowAnimation.start();
            contentTextShowAnimation.start();
            if (this.mOnChangeListener != null) {
                this.mOnChangeListener.onPageChanged(oldElementIndex, this.mActiveElementIndex);
            }

        }
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

    protected AnimatorSet createBGAnimatorSet(final int color) {
        final View bgColorView = new ImageView(this.mAppContext);
        bgColorView.setLayoutParams(new LayoutParams(this.mRootLayout.getWidth(), this.mRootLayout.getHeight()));
        bgColorView.setBackgroundColor(color);
        this.mBackgroundContainer.addView(bgColorView);
        int[] pos = this.calculateCurrentCenterCoordinatesOfPagerElement(this.mActiveElementIndex);
        float finalRadius = this.mRootLayout.getWidth() > this.mRootLayout.getHeight() ? (float)this.mRootLayout.getWidth() : (float)this.mRootLayout.getHeight();
        AnimatorSet bgAnimSet = new AnimatorSet();
        Animator fadeIn = ObjectAnimator.ofFloat(bgColorView, "alpha", new float[]{0.0F, 1.0F});
        if (VERSION.SDK_INT >= 21) {
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(bgColorView, pos[0], pos[1], 0.0F, finalRadius);
            circularReveal.setInterpolator(new AccelerateInterpolator());
            bgAnimSet.playTogether(new Animator[]{circularReveal, fadeIn});
        } else {
            bgAnimSet.playTogether(new Animator[]{fadeIn});
        }

        bgAnimSet.setDuration(450L);
        bgAnimSet.addListener(new AnimatorEndListener() {
            public void onAnimationEnd(Animator animation) {
                UPaperOnboardingEngine.this.mRootLayout.setBackgroundColor(color);
                bgColorView.setVisibility(8);
                UPaperOnboardingEngine.this.mBackgroundContainer.removeView(bgColorView);
            }
        });
        return bgAnimSet;
    }

    private AnimatorSet createContentTextShowAnimation(final View currentContentText, View newContentText) {
        int positionDeltaPx = this.dpToPixels(50);
        AnimatorSet animations = new AnimatorSet();
        Animator currentContentMoveUp = ObjectAnimator.ofFloat(currentContentText, "y", new float[]{0.0F, (float)(-positionDeltaPx)});
        currentContentMoveUp.setDuration(200L);
        currentContentMoveUp.addListener(new AnimatorEndListener() {
            public void onAnimationEnd(Animator animation) {
                UPaperOnboardingEngine.this.mContentTextContainer.removeView(currentContentText);
            }
        });
        Animator currentContentFadeOut = ObjectAnimator.ofFloat(currentContentText, "alpha", new float[]{1.0F, 0.0F});
        currentContentFadeOut.setDuration(200L);
        animations.playTogether(new Animator[]{currentContentMoveUp, currentContentFadeOut});
        Animator newContentMoveUp = ObjectAnimator.ofFloat(newContentText, "y", new float[]{(float)positionDeltaPx, 0.0F});
        newContentMoveUp.setDuration(800L);
        Animator newContentFadeIn = ObjectAnimator.ofFloat(newContentText, "alpha", new float[]{0.0F, 1.0F});
        newContentFadeIn.setDuration(800L);
        animations.playTogether(new Animator[]{newContentMoveUp, newContentFadeIn});
        animations.setInterpolator(new DecelerateInterpolator());
        return animations;
    }

    protected AnimatorSet createContentIconShowAnimation(final View currentContentIcon, View newContentIcon) {
        int positionDeltaPx = this.dpToPixels(50);
        AnimatorSet animations = new AnimatorSet();
        Animator currentContentMoveUp = ObjectAnimator.ofFloat(currentContentIcon, "y", new float[]{0.0F, (float)(-positionDeltaPx)});
        currentContentMoveUp.setDuration(200L);
        currentContentMoveUp.addListener(new AnimatorEndListener() {
            public void onAnimationEnd(Animator animation) {
                UPaperOnboardingEngine.this.mContentIconContainer.removeView(currentContentIcon);
            }
        });
        Animator currentContentFadeOut = ObjectAnimator.ofFloat(currentContentIcon, "alpha", new float[]{1.0F, 0.0F});
        currentContentFadeOut.setDuration(200L);
        animations.playTogether(new Animator[]{currentContentMoveUp, currentContentFadeOut});
        Animator newContentMoveUp = ObjectAnimator.ofFloat(newContentIcon, "y", new float[]{(float)positionDeltaPx, 0.0F});
        newContentMoveUp.setDuration(800L);
        Animator newContentFadeIn = ObjectAnimator.ofFloat(newContentIcon, "alpha", new float[]{0.0F, 1.0F});
        newContentFadeIn.setDuration(800L);
        animations.playTogether(new Animator[]{newContentMoveUp, newContentFadeIn});
        animations.setInterpolator(new DecelerateInterpolator());
        return animations;
    }

    protected Animator createContentCenteringVerticalAnimation(View newContentText, View newContentIcon) {
        newContentText.measure(MeasureSpec.makeMeasureSpec(this.mContentCenteredContainer.getWidth(), -2147483648), -2);
        int measuredContentTextHeight = newContentText.getMeasuredHeight();
        newContentIcon.measure(-2, -2);
        int measuredContentIconHeight = newContentIcon.getMeasuredHeight();
        int newHeightOfContent = measuredContentIconHeight + measuredContentTextHeight + ((MarginLayoutParams)this.mContentTextContainer.getLayoutParams()).topMargin;
        Animator centerContentAnimation = ObjectAnimator.ofFloat(this.mContentCenteredContainer, "y", new float[]{this.mContentCenteredContainer.getY(), (float)((this.mContentRootLayout.getHeight() - newHeightOfContent) / 2)});
        centerContentAnimation.setDuration(800L);
        centerContentAnimation.setInterpolator(new DecelerateInterpolator());
        return centerContentAnimation;
    }

    protected AnimatorSet createPagerIconAnimation(int oldIndex, int newIndex) {
        AnimatorSet animations = new AnimatorSet();
        animations.setDuration(350L);
        final ViewGroup oldActiveItem = (ViewGroup)this.mPagerIconsContainer.getChildAt(oldIndex);
        final android.widget.LinearLayout.LayoutParams oldActiveItemParams = (android.widget.LinearLayout.LayoutParams)oldActiveItem.getLayoutParams();
        ValueAnimator oldItemScaleDown = ValueAnimator.ofInt(new int[]{this.mPagerElementActiveSize, this.mPagerElementNormalSize});
        oldItemScaleDown.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                oldActiveItemParams.height = (Integer)valueAnimator.getAnimatedValue();
                oldActiveItemParams.width = (Integer)valueAnimator.getAnimatedValue();
                oldActiveItem.requestLayout();
            }
        });
        View oldActiveIcon = oldActiveItem.getChildAt(1);
        Animator oldActiveIconFadeOut = ObjectAnimator.ofFloat(oldActiveIcon, "alpha", new float[]{1.0F, 0.0F});
        ImageView oldActiveShape = (ImageView)oldActiveItem.getChildAt(0);
        oldActiveShape.setImageResource(oldIndex - newIndex > 0 ? drawable.onboarding_pager_circle_icon : drawable.onboarding_pager_round_icon);
        Animator oldActiveShapeFadeIn = ObjectAnimator.ofFloat(oldActiveShape, "alpha", new float[]{0.0F, 0.5F});
        animations.playTogether(new Animator[]{oldItemScaleDown, oldActiveIconFadeOut, oldActiveShapeFadeIn});
        final ViewGroup newActiveItem = (ViewGroup)this.mPagerIconsContainer.getChildAt(newIndex);
        final android.widget.LinearLayout.LayoutParams newActiveItemParams = (android.widget.LinearLayout.LayoutParams)newActiveItem.getLayoutParams();
        ValueAnimator newItemScaleUp = ValueAnimator.ofInt(new int[]{this.mPagerElementNormalSize, this.mPagerElementActiveSize});
        newItemScaleUp.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                newActiveItemParams.height = (Integer)valueAnimator.getAnimatedValue();
                newActiveItemParams.width = (Integer)valueAnimator.getAnimatedValue();
                newActiveItem.requestLayout();
            }
        });
        View newActiveIcon = newActiveItem.getChildAt(1);
        Animator newActiveIconFadeIn = ObjectAnimator.ofFloat(newActiveIcon, "alpha", new float[]{0.0F, 1.0F});
        ImageView newActiveShape = (ImageView)newActiveItem.getChildAt(0);
        Animator newActiveShapeFadeOut = ObjectAnimator.ofFloat(newActiveShape, "alpha", new float[]{0.5F, 0.0F});
        animations.playTogether(new Animator[]{newItemScaleUp, newActiveShapeFadeOut, newActiveIconFadeIn});
        animations.setInterpolator(new DecelerateInterpolator());
        return animations;
    }

    protected ViewGroup createPagerIconElement(int iconDrawableRes, boolean isActive) {
        LayoutInflater vi = LayoutInflater.from(this.mAppContext);
        FrameLayout bottomBarElement = (FrameLayout)vi.inflate(layout.onboarding_pager_layout, this.mPagerIconsContainer, false);
        ImageView elementShape = (ImageView)bottomBarElement.getChildAt(0);
        ImageView elementIcon = (ImageView)bottomBarElement.getChildAt(1);
        elementIcon.setImageResource(iconDrawableRes);
        if (isActive) {
            android.widget.LinearLayout.LayoutParams layoutParams = (android.widget.LinearLayout.LayoutParams)bottomBarElement.getLayoutParams();
            layoutParams.width = this.mPagerIconsContainer.getLayoutParams().height;
            layoutParams.height = this.mPagerIconsContainer.getLayoutParams().height;
            elementShape.setAlpha(0.0F);
            elementIcon.setAlpha(1.0F);
        } else {
            elementShape.setAlpha(0.5F);
            elementIcon.setAlpha(0.0F);
        }

        return bottomBarElement;
    }

    protected ViewGroup createContentTextView(PaperOnboardingPage PaperOnboardingPage) {
        LayoutInflater vi = LayoutInflater.from(this.mAppContext);
        ViewGroup contentTextView = (ViewGroup)vi.inflate(layout.onboarding_text_content_layout, this.mContentTextContainer, false);
        TextView contentTitle = (TextView)contentTextView.getChildAt(0);
        contentTitle.setText(PaperOnboardingPage.getTitleText());
        contentTitle.setTextColor(R.color.white);
        TextView contentText = (TextView)contentTextView.getChildAt(1);
        contentText.setText(PaperOnboardingPage.getDescriptionText());
        contentText.setTextColor(R.color.white);
        return contentTextView;
    }

    protected ImageView createContentIconView(PaperOnboardingPage PaperOnboardingPage) {
        ImageView contentIcon = new ImageView(this.mAppContext);
        contentIcon.setImageResource(PaperOnboardingPage.getContentIconRes());
        android.widget.FrameLayout.LayoutParams iconLP = new android.widget.FrameLayout.LayoutParams(-2, -2);
        iconLP.gravity = 17;
        contentIcon.setLayoutParams(iconLP);
        return contentIcon;
    }

    public int getActiveElementIndex() {
        return this.mActiveElementIndex;
    }

    protected PaperOnboardingPage getActiveElement() {
        return this.mElements.size() > this.mActiveElementIndex ? (PaperOnboardingPage)this.mElements.get(this.mActiveElementIndex) : null;
    }

    protected PaperOnboardingPage toggleToPreviousElement() {
        if (this.mActiveElementIndex - 1 >= 0) {
            --this.mActiveElementIndex;
            return this.mElements.size() > this.mActiveElementIndex ? (PaperOnboardingPage)this.mElements.get(this.mActiveElementIndex) : null;
        } else {
            return null;
        }
    }

    protected PaperOnboardingPage toggleToNextElement() {
        if (this.mActiveElementIndex + 1 < this.mElements.size()) {
            ++this.mActiveElementIndex;
            return this.mElements.size() > this.mActiveElementIndex ? (PaperOnboardingPage)this.mElements.get(this.mActiveElementIndex) : null;
        } else {
            return null;
        }
    }

    protected int dpToPixels(int dpValue) {
        return (int)((float)dpValue * this.dpToPixelsScaleFactor + 0.5F);
    }
}
