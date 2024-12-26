package com.old_dummy.cc.GalidesawarChart;

import com.old_dummy.cc.Models.GalidesawarChartModel;

import java.util.List;

public interface GalidesawarChartContract {

    interface View{
        void apiResponse(List<GalidesawarChartModel.Data> data);
        void message(String msg);
        void error(String msg);
    }

    interface ViewModel{
        interface OnFinishedListener{
            void finished(List<GalidesawarChartModel.Data> data);
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
