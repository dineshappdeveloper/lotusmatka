package com.old_dummy.cc.MainGameChart;

import com.old_dummy.cc.Models.MainGameChartModel;

import java.util.List;

public interface MainGameChartContract {

    interface View{
        void apiResponse(List<MainGameChartModel.Data> data);
        void message(String msg);
    }

    interface ViewModel{
        interface OnFinishedListener{
            void finished(List<MainGameChartModel.Data> data);
            void message(String msg);
            void failure(Throwable t);
        }
        void callApi(OnFinishedListener onFinishedListener,String token, String gameID);

    }

    interface Presenter{
        void api(String token, String gameID);
    }
}
