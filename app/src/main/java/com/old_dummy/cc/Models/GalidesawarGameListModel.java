package com.old_dummy.cc.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GalidesawarGameListModel {

    @Expose
    @SerializedName("message")
    String message;

    @Expose
    @SerializedName("code")
    String code;

    @Expose
    @SerializedName("status")
    String status;

    @Expose
    @SerializedName("data")
    Data data;

    public static class Data{
        @Expose
        @SerializedName("gali_disawar_chart")
        String galidesawrChart;

        @Expose
        @SerializedName("gali_disawar_rates")
        List<GalidesawarRates> galidesawrRates;

        @Expose
        @SerializedName("gali_disawar_game")
        List<GalidesawarGame> galidesawrGame;

        public static class GalidesawarRates{
            @Expose
            @SerializedName("cost_amount")
            String cost_amount;
            @Expose
            @SerializedName("name")
            String name;
            @Expose
            @SerializedName("earning_amount")
            String earning_amount;

            public String getCost_amount() {
                return cost_amount;
            }

            public String getName() {
                return name;
            }

            public String getEarning_amount() {
                return earning_amount;
            }
        }
        public static class GalidesawarGame {
            @Expose
            @SerializedName("id")
            String id;
            @Expose
            @SerializedName("name")
            String name;
            @Expose
            @SerializedName("result")
            String result;
            @Expose
            @SerializedName("play")
            boolean play;
            @Expose
            @SerializedName("time")
            String time;

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getResult() {
                return result;
            }

            public boolean isPlay() {
                return play;
            }

            public String  getTime() {
                return time;
            }
        }

        public String getGalidesawrChart() {
            return galidesawrChart;
        }

        public List<GalidesawarRates> getGalidesawrRates() {
            return galidesawrRates;
        }

        public List<GalidesawarGame> getGalidesawrGame() {
            return galidesawrGame;
        }
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public Data getData() {
        return data;
    }
}
