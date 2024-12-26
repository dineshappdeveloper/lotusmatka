package com.old_dummy.cc.StarlineChart;

import com.old_dummy.cc.Models.StarlineChartModel;

import java.util.List;

public interface StarlineChartContract {

    interface View{
        void apiResponse(List<StarlineChartModel.Data> data);
        void message(String msg);
        void error(String msg);
    }

    interface ViewModel{
        interface OnFinishedListener{
            void finished(List<StarlineChartModel.Data> data);
            void message(String msg);
            void error(String msg);
            void failure(Throwable t);
        }
        void callApi(OnFinishedListener onFinishedListener,String token);

    }

    interface Presenter{
        void api(String token);
    }
}
