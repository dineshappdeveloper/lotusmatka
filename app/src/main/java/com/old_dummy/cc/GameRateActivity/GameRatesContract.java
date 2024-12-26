package com.old_dummy.cc.GameRateActivity;

import com.old_dummy.cc.Models.GameRateModel;
import com.old_dummy.cc.Models.HowToPlayModel;

public interface GameRatesContract {
    interface View{
        void showProgressBar();
        void hideProgressBar();
        void gameRatesApiResponse(GameRateModel data);
        void howToPlayApiResponse(HowToPlayModel.Data data);
        void message(String msg);
        void destroy(String msg);
    }
    interface ViewModel{
        interface OnFinishedListener{
            void gameRatesFinished(GameRateModel data);
            void howToPlayFinished(HowToPlayModel.Data data);
            void message(String msg);
            void destroy(String msg);
            void failure(Throwable t);
        }
        void callGameRatesApi(OnFinishedListener onFinishedListener, String token);
        void callHowToPlayApi(OnFinishedListener onFinishedListener, String token);
    }
    interface Presenter{
        void gameRatesApi(String token);
        void howToPlayApi(String token);
    }
}
