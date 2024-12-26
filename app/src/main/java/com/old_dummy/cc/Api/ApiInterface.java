package com.old_dummy.cc.Api;

import com.old_dummy.cc.Models.AppDetailsModel;
import com.old_dummy.cc.Models.CommonModel;
import com.old_dummy.cc.Models.GalidesawarChartModel;
import com.old_dummy.cc.Models.GalidesawarGameListModel;
import com.old_dummy.cc.Models.GalidesawarWinModel;
import com.old_dummy.cc.Models.GameListModel;
import com.old_dummy.cc.Models.GameRateModel;
import com.old_dummy.cc.Models.HowToPlayModel;
import com.old_dummy.cc.Models.LoginModel;
import com.old_dummy.cc.Models.MainGameChartModel;
import com.old_dummy.cc.Models.PaymentConfigModel;
import com.old_dummy.cc.Models.PaymentRequestModel;
import com.old_dummy.cc.Models.StarLineWinModel;
import com.old_dummy.cc.Models.StarlineChartModel;
import com.old_dummy.cc.Models.StarlineGameListModel;
import com.old_dummy.cc.Models.TransferVerifyModel;
import com.old_dummy.cc.Models.UserStatusModel;
import com.old_dummy.cc.Models.WalletStatementModel;
import com.old_dummy.cc.Models.WinModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST(ApiUrl.signUp)
    Call<CommonModel> getSignUp(
            @Field("full_name") String name,
            @Field("mobile") String mobile,
            @Field("pin") String pin,
            @Field("password") String password,
            @Field("version") String version
    );

    @FormUrlEncoded
    @POST(ApiUrl.verify_user)
    Call<LoginModel> verifyCustomer(
            @Field("mobile") String mobile,
            @Field("mobile_token") String mobile_token,
            @Field("otp") String otp
    );


    @FormUrlEncoded
    @POST(ApiUrl.login)
    Call<LoginModel> getLogIn(
            @Field("mobile") String mobileNumber,
            @Field("password") String password,
            @Field("version") String version
    );

    @FormUrlEncoded
    @POST(ApiUrl.login_pin)
    Call<LoginModel> logInPin(
            @Header("token") String token,
            @Field("pin") String pin
    );

    @FormUrlEncoded
    @POST(ApiUrl.forgotPin)
    Call<CommonModel> forgotPinApi(
            @Field("mobile") String mobile
    );

    @FormUrlEncoded
    @POST(ApiUrl.user_status)
    Call<UserStatusModel> user_status(
            @Header("token") String token,
            @Field("string") String string
    );

    @FormUrlEncoded
    @POST(ApiUrl.forgot_password)
    Call<CommonModel> forgotPassword(
            @Field("mobile") String mobile
    );
    @FormUrlEncoded
    @POST(ApiUrl.verifyOtp)
    Call<LoginModel> verifyOtp(
            @Field("mobile") String PhoneNumber,
            @Field("otp") String otp
    );
    @FormUrlEncoded
    @POST(ApiUrl.resendOtp)
    Call<CommonModel> resendOtp(
            @Field("mobile") String mobile
    );
    @FormUrlEncoded
    @POST(ApiUrl.forgot_password_verify)
    Call<LoginModel> forgotPasswordVerify(
            @Header("token") String token,
            @Field("mobile") String PhoneNumber,
            @Field("mobile_token") String PhoneToken,
            @Field("password") String password

    );

    @FormUrlEncoded
    @POST(ApiUrl.create_pin)
    Call<LoginModel> createPin(
            @Header("token") String token,
            @Field("mobile_token") String MobileToken,
            @Field("mobile") String mobile,
            @Field("pin") String pin
    );

    @FormUrlEncoded
    @POST(ApiUrl.update_phonepe)
    Call<CommonModel> updatePhonePe(
            @Header("token") String token,
            @Field("phonepe") String phonePeNumber
    );

    @FormUrlEncoded
    @POST(ApiUrl.update_bank_details)
    Call<CommonModel> updateBankDetails(
            @Header("token") String token,
            @Field("account_holder_name") String accountHolderName,
            @Field("account_no") String accountNumber,
            @Field("ifsc_code") String ifscCode,
            @Field("bank_name") String bankName,
            @Field("branch_address") String branchAddress
    );

    @FormUrlEncoded
    @POST(ApiUrl.update_gpay)
    Call<CommonModel> updateGooglePay(
            @Header("token") String token,
            @Field("gpay") String googlePayNumber
    );

    @FormUrlEncoded
    @POST(ApiUrl.update_paytm)
    Call<CommonModel> updatePayTm(
            @Header("token") String token,
            @Field("paytm") String payTmNumber
    );

    @FormUrlEncoded
    @POST(ApiUrl.update_profile)
    Call<LoginModel> updateProfile(
            @Header("token") String token,
            @Field("email") String email,
            @Field("name") String name
    );

    @FormUrlEncoded
    @POST(ApiUrl.update_firebase_token)
    Call<CommonModel> updateFireBaseToken(
            @Header("token") String token,
            @Field("token_id") String tokenId
    );

    @FormUrlEncoded
    @POST(ApiUrl.get_user_details)
    Call<LoginModel> getUserDetails(
            @Header("token") String token,
            @Field("string") String string
    );

    @FormUrlEncoded
    @POST(ApiUrl.app_details)
    Call<AppDetailsModel> getAppDetails(
            @Header("token") String token,
            @Field("string") String string
    );


    @FormUrlEncoded
    @POST(ApiUrl.add_fund)
    Call<CommonModel> addFund(
            @Header("token") String token,
            @Field("points") String points,
            @Field("trans_status") String trans_status,
            @Field("trans_id") String trans_id
    );

    @FormUrlEncoded
    @POST(ApiUrl.transferPoints)
    Call<CommonModel> transferPoints(
            @Header("token") String token,
            @Field("points") String points,
            @Field("user_number") String userNumber
    );
    @FormUrlEncoded
    @POST(ApiUrl.transfer_verify)
    Call<TransferVerifyModel> transferVerify(
            @Header("token") String token,
            @Field("user_number") String userNumber
    );

    @FormUrlEncoded
    @POST(ApiUrl.how_to_play)
    Call<HowToPlayModel> howToPlay(
            @Header("token") String token,
            @Field("string") String string
    );
    @FormUrlEncoded
    @POST(ApiUrl.main_game_list)
    Call<GameListModel> mainGameList(
            @Header("token") String token,
            @Field("string") String string
    );
    @FormUrlEncoded
    @POST(ApiUrl.placeBid)
    Call<CommonModel> placeBid(
            @Header("token") String token,
            @Field("game_bids") String gameBids
    );
    @FormUrlEncoded
    @POST(ApiUrl.game_rate_list)
    Call<GameRateModel> gameRateList(
            @Header("token") String token,
            @Field("string") String string
    );

    @FormUrlEncoded
    @POST(ApiUrl.withdraw)
    Call<CommonModel> withdrawPoints(
            @Header("token") String token,
            @Field("points") String string,
            @Field("method") String method
    );

    @FormUrlEncoded
    @POST(ApiUrl.bid_history)
    Call<WinModel> bidHistory(
            @Header("token") String token,
            @Field("from_date") String fromDate,
            @Field("to_date") String toDate
    );
    @FormUrlEncoded
    @POST(ApiUrl.win_history)
    Call<WinModel> winHistory(
            @Header("token") String token,
            @Field("from_date") String fromDate,
            @Field("to_date") String toDate
    );

    @FormUrlEncoded
    @POST(ApiUrl.wallet_statement)
    Call<WalletStatementModel> walletStatement(
            @Header("token") String token,
            @Field("string") String string
    );

    @FormUrlEncoded
    @POST(ApiUrl.withdrawStatement)
    Call<WalletStatementModel> withdrawStatement(
            @Header("token") String token,
            @Field("string") String string
    );

    @FormUrlEncoded
    @POST(ApiUrl.starlineGame)
    Call<StarlineGameListModel> starLineGame(
            @Header("token") String token,
            @Field("string") String string
    );
    @FormUrlEncoded
    @POST(ApiUrl.galidesawrGame)
    Call<GalidesawarGameListModel> galidesawarGame(
            @Header("token") String token,
            @Field("string") String string
    );

    @FormUrlEncoded
    @POST(ApiUrl.starlinePlaceBid)
    Call<CommonModel> starlinePlaceBid(
            @Header("token") String token,
            @Field("game_bids") String gameBids
    );

    @FormUrlEncoded
    @POST(ApiUrl.galidesawrPlaceBid)
    Call<CommonModel> galidesawarPlaceBid(
            @Header("token") String token,
            @Field("game_bids") String gameBids
    );
    @FormUrlEncoded
    @POST(ApiUrl.starlineBidHistory)
    Call<StarLineWinModel> starLineBidHistory(
            @Header("token") String token,
            @Field("from_date") String fromDate,
            @Field("to_date") String toDate
    );

    @FormUrlEncoded
    @POST(ApiUrl.galidesawrBidHistory)
    Call<GalidesawarWinModel> galidesawrBidHistory(
            @Header("token") String token,
            @Field("from_date") String fromDate,
            @Field("to_date") String toDate
    );
    @FormUrlEncoded
    @POST(ApiUrl.starlineWinHistory)
    Call<StarLineWinModel> starLineWinHistory(
            @Header("token") String token,
            @Field("from_date") String fromDate,
            @Field("to_date") String toDate
    );
    @FormUrlEncoded
    @POST(ApiUrl.galidesawrWinHistory)
    Call<GalidesawarWinModel> galidesawrWinHistory(
            @Header("token") String token,
            @Field("from_date") String fromDate,
            @Field("to_date") String toDate
    );
    @FormUrlEncoded
    @POST(ApiUrl.readNotification)
    Call<CommonModel> readNotification(
            @Header("token") String token,
            @Field("readnoti") String one
    );
    @FormUrlEncoded
    @POST(ApiUrl.mainGameChart)
    Call<MainGameChartModel> mainGameChart(
            @Header("token") String token,
            @Field("game_id") String gameId
    );

    @FormUrlEncoded
    @POST(ApiUrl.starlineChart)
    Call<StarlineChartModel> starlineChart(
            @Header("token") String token,
            @Field("string") String string
    );

    @FormUrlEncoded
    @POST(ApiUrl.galiChart)
    Call<GalidesawarChartModel> galiChart(
            @Header("token") String token,
            @Field("string") String string
    );

    @FormUrlEncoded
    @POST(ApiUrl.paymentConfig)
    Call<PaymentConfigModel> paymentConfig(
            @Header("token") String token,
            @Field("string") String string
    );

    @FormUrlEncoded
    @POST(ApiUrl.paymentRequest)
    Call<PaymentRequestModel> paymentRequest(
            @Header("token") String token,
            @Field("amount") String amount,
            @Field("method_name") String methodName
    );

    @FormUrlEncoded
    @POST(ApiUrl.paymentReceive)
    Call<CommonModel> paymentReceive(
            @Header("token") String token,
            @Field("amount") String amount,
            @Field("method_name") String methodName,
            @Field("screenshot") String screenshot,
            @Field("transaction_id") String transactionId,
            @Field("status") String status,
            @Field("method_details") String methodDetails
    );
}
