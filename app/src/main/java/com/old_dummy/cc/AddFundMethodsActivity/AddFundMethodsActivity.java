package com.old_dummy.cc.AddFundMethodsActivity;

import static com.old_dummy.cc.Extras.Utility.BroadCastStringForAction;
import static com.old_dummy.cc.Extras.Utility.myReceiver;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;
import com.old_dummy.cc.Extras.SharPrefHelper;
import com.old_dummy.cc.Extras.Utility;
import com.old_dummy.cc.Extras.YourService;
import com.old_dummy.cc.LoginActivity.LoginActivity;
import com.old_dummy.cc.Models.FundMethodsModel;
import com.old_dummy.cc.Models.PaymentConfigModel;
import com.old_dummy.cc.Models.PaymentRequestModel;
import com.old_dummy.cc.R;
import com.old_dummy.cc.SplashActivity.SplashActivity;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dev.shreyaspatil.easyupipayment.EasyUpiPayment;
import dev.shreyaspatil.easyupipayment.listener.PaymentStatusListener;
import dev.shreyaspatil.easyupipayment.model.PaymentApp;
import dev.shreyaspatil.easyupipayment.model.TransactionDetails;

public class AddFundMethodsActivity extends AppCompatActivity implements PaymentStatusListener,  AddFundMethodContract.View {


    ProgressBar progressBarWebView;
    ProgressBar progressBar;
    WebView webView;
    MaterialTextView dataConText,amountTV,noticeTitle,noticeText, qrUpiId,uploadText,bankName,ifscCode,accountNumber,accountHolderName;
    IntentFilter mIntentFilter;
    Utility utility;
    PaymentApp paymentApp;
    AddFundMethodContract.Presenter presenter;
    List<FundMethodsModel> tabList;
    FundMethodsTabAdapter tabAdapter;
    RecyclerView tabRecycler;
    PaymentConfigModel.Data data;
    LinearLayout qrCodeMethod,bankMethod,amountCard,transIdCard;
    String amount,upiId,payeeName,remark, methodDetails,methodName,transactionId ;
    NestedScrollView scrollView;
    ShapeableImageView qrCodeImage,imagePreview;
    MaterialCardView uploadCard;
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int PAYMENT_APP_REQUEST = 1;
    private Uri imageUri;
    private String screenshot;
    TextInputEditText inputTransctionID,inputAmount;
    ConstraintLayout pointLyt;
    int reducedAmount;
    boolean click= true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_color));
        setContentView(R.layout.activity_fund_methods);
        intIDs();
        configureToolbar();
    }

    private void intIDs() {

        progressBarWebView = findViewById(R.id.progressBarWebView);
        progressBar = findViewById(R.id.progressBar);
        webView = findViewById(R.id.webView);
        presenter = new FundMethodsPresenter(this);
        dataConText = findViewById(R.id.dataConText);
        tabRecycler = findViewById(R.id.tabRecycler);
        amountTV = findViewById(R.id.amount);
        noticeTitle = findViewById(R.id.noticeTitle);
        noticeText = findViewById(R.id.noticeText);
        qrCodeMethod = findViewById(R.id.qrCodeMethod);
        bankMethod = findViewById(R.id.bankMethod);
        scrollView = findViewById(R.id.scrollView);
        qrCodeImage = findViewById(R.id.qr_code);
        qrUpiId = findViewById(R.id.upiId);
        uploadCard = findViewById(R.id.uploadCard);
        amountCard = findViewById(R.id.amountCard);
        transIdCard = findViewById(R.id.transIdCard);
        uploadText = findViewById(R.id.uploadText);
        imagePreview = findViewById(R.id.imagePreview);
        inputTransctionID = findViewById(R.id.inputTransctionID);
        inputAmount = findViewById(R.id.inputAmount);
        ifscCode = findViewById(R.id.ifscCode);
        accountNumber = findViewById(R.id.accountNumber);
        accountHolderName = findViewById(R.id.accountHolderName);
        bankName = findViewById(R.id.bankName);
        pointLyt = findViewById(R.id.pointLyt);
        utility = new Utility(dataConText);
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(BroadCastStringForAction);
        Intent serviceIntent = new Intent(this, YourService.class);
        startService(serviceIntent);
        data =  getIntent().getParcelableExtra("configData");
        amount =  getIntent().getStringExtra("amount");
        tabList = new ArrayList<>();
        assert data != null;
        if (data.getAvailableMethods().getUpi()){
            tabList.add(new FundMethodsModel(
                    ContextCompat.getDrawable(this,R.drawable.other_upi),
                    "upi", data.getAvailableMethodsDetails().getUpi().getNotice(),
                    "!!UPI Notice!!", data.getAvailableMethodsDetails().getUpi().getVideo()));
        }
        if (data.getAvailableMethods().getQrCode()){
            tabList.add(new FundMethodsModel(
                    ContextCompat.getDrawable(this,R.drawable.qr_code),
                    "qr_code",data.getAvailableMethodsDetails().getQrCode().getNotice(),
                    "!!QR Code Notice!!",data.getAvailableMethodsDetails().getQrCode().getVideo()));
        }
        if (data.getAvailableMethods().getBankAccount()){
            tabList.add(new FundMethodsModel(
                    ContextCompat.getDrawable(this,R.drawable.bank_update),
                    "bank_account",
                    data.getAvailableMethodsDetails().getBankAccount().getNotice(),
                    "!!Bank Notice!!", data.getAvailableMethodsDetails().getBankAccount().getVideo()));
        }
        if (data.getAvailableMethods().getPaymentGateway()!=null){
            for(int i =0; i<data.getAvailableMethods().getPaymentGateway().size();i++){
                tabList.add(new FundMethodsModel(
                        ContextCompat.getDrawable(this,R.drawable.other_upi),
                        data.getAvailableMethods().getPaymentGateway().get(i).getType(),
                        data.getAvailableMethods().getPaymentGateway().get(i).getNotice(),
                        "!!Upi Notice!!",
                        data.getAvailableMethods().getPaymentGateway().get(i).getVideo()
                        ));
            }
        }



        if(!Objects.equals(data.getAvailableMethodsDetails().getAmountConfiguration(), "1")){
            Random random = new Random();
            int randomNumber = random.nextInt(6); // Generates a random number between 0 and 5
            reducedAmount = Integer.parseInt(amount) - randomNumber;
        }else {
            reducedAmount = Integer.parseInt(amount);
        }
        methodName = data.getAvailableMethodsDetails().getDefaultMethod();
        defaultMethod(data.getAvailableMethodsDetails().getDefaultMethod());

        tabAdapter = new FundMethodsTabAdapter(this,methodName, tabList, (method, notice, noticeTitle1, videoLink) -> {
            noticeTitle.setText(noticeTitle1);
            noticeText.setText(notice);
            scrollView.scrollTo(0, 0);
            loadVideo(videoLink);
            methodName = method;
            inputTransctionID.setText("");
            inputAmount.setText("");
            screenshot = "";
            imagePreview.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            uploadText.setText("+Upload Screenshot");
            defaultMethod(method);

        });
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        tabRecycler.setLayoutManager(layoutManager2);
        tabRecycler.setAdapter(tabAdapter);

    }

    void defaultMethod(String method){
        switch (method){
            case "upi":
                pointLyt.setVisibility(View.VISIBLE);
                qrCodeMethod.setVisibility(View.GONE);
                bankMethod.setVisibility(View.GONE);
                uploadCard.setVisibility(View.GONE);
                amountCard.setVisibility(View.GONE);
                transIdCard.setVisibility(View.GONE);
                noticeTitle.setText("!!UPI Notice!!");
                noticeText.setText(data.getAvailableMethodsDetails().getUpi().getNotice());
                loadVideo(data.getAvailableMethodsDetails().getUpi().getVideo());
                amountTV.setText(String.valueOf(reducedAmount));
                if(Integer.parseInt(amount)<Integer.parseInt(data.getAvailableMethodsDetails().getUpiLimit())){
                    upiId = data.getAvailableMethodsDetails().getSmallAmountUpi().getUpi_id();
                    payeeName = data.getAvailableMethodsDetails().getSmallAmountUpi().getUpi_name();
                    remark = data.getAvailableMethodsDetails().getSmallAmountUpi().getRemark();
                    methodDetails = data.getAvailableMethodsDetails().getSmallAmountUpi().getType();
                }else {
                    upiId = data.getAvailableMethodsDetails().getLargeAmountUpi().getUpiId();
                    payeeName = data.getAvailableMethodsDetails().getLargeAmountUpi().getUpiName();
                    remark = data.getAvailableMethodsDetails().getLargeAmountUpi().getRemark();
                    methodDetails = data.getAvailableMethodsDetails().getLargeAmountUpi().getType();
                }
                break;
            case "qr_code":
                uploadCard.setVisibility(View.VISIBLE);
                pointLyt.setVisibility(View.VISIBLE);
                qrCodeMethod.setVisibility(View.VISIBLE);
                transIdCard.setVisibility(View.VISIBLE);
                bankMethod.setVisibility(View.GONE);
                amountCard.setVisibility(View.VISIBLE);
                noticeTitle.setText("!!QR Code Notice!!");
                noticeText.setText(data.getAvailableMethodsDetails().getQrCode().getNotice());
                loadVideo(data.getAvailableMethodsDetails().getQrCode().getVideo());
                qrUpiId.setText(data.getAvailableMethodsDetails().getQrCode().getQrUpiId());
                amountTV.setText(String.valueOf(reducedAmount));
              /*  String remark =
                        "Amount: "+reducedAmount+
                                "\n User Name: "+ SharPrefHelper.getSignUpData(this, SharPrefHelper.KEY_PERSON_NAME)+
                                "\nUser Number: "+SharPrefHelper.getSignUpData(this, SharPrefHelper.KEY_MOBILE_NUMBER)+
                                "\n"+getString(R.string.app_name);
                String upiLink = "upi://pay?pa="+data.getAvailableMethodsDetails().getQrCode().getQrUpiId()+"&tn="+remark+"&am="+amount+"&cu=INR";
                Bitmap myBitmap = QRCode.from(upiLink).bitmap();
                qrCodeImage.setImageBitmap(myBitmap);*/
                Glide.with(this)
                        .load(data.getAvailableMethodsDetails().getQrCode().getQrImage())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(qrCodeImage);
                break;
            case "bank_account":
                qrCodeMethod.setVisibility(View.GONE);
                pointLyt.setVisibility(View.GONE);
                bankMethod.setVisibility(View.VISIBLE);
                uploadCard.setVisibility(View.VISIBLE);
                amountCard.setVisibility(View.VISIBLE);
                transIdCard.setVisibility(View.VISIBLE);
                noticeTitle.setText("!!Bank Notice!!");
                noticeText.setText(data.getAvailableMethodsDetails().getBankAccount().getNotice());
                loadVideo(data.getAvailableMethodsDetails().getBankAccount().getVideo());
                ifscCode.setText(data.getAvailableMethodsDetails().getBankAccount().getIfscCode());
                accountNumber.setText(data.getAvailableMethodsDetails().getBankAccount().getAccountNo());
                accountHolderName.setText(data.getAvailableMethodsDetails().getBankAccount().getAccountHolderName());
                bankName.setText(data.getAvailableMethodsDetails().getBankAccount().getBankName());
                break;
            default:
                noticeTitle.setText("!!Notice!!");
                for(int i =0; i<data.getAvailableMethods().getPaymentGateway().size();i++){
                    noticeText.setText(data.getAvailableMethods().getPaymentGateway().get(i).getNotice());
                    loadVideo(data.getAvailableMethods().getPaymentGateway().get(i).getVideo());
                }
                pointLyt.setVisibility(View.VISIBLE);
                qrCodeMethod.setVisibility(View.GONE);
                bankMethod.setVisibility(View.GONE);
                uploadCard.setVisibility(View.GONE);
                amountCard.setVisibility(View.GONE);
                transIdCard.setVisibility(View.GONE);
                break;
        }
    }

    void loadVideo(String url){
        webView.setWebViewClient(new WebViewClint());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Win64; x64; rv:46.0) Gecko/20100101 Firefox/68.0");
        webView.getSettings().setGeolocationEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setSupportMultipleWindows(true);
        webView.getSettings().setNeedInitialFocus(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setInitialScale(100);
        webView.loadUrl(url);

        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    progressBarWebView.setVisibility(View.GONE);
                }
            }
        });

    }
    private void configureToolbar() {
        MaterialToolbar toolbar = findViewById(R.id.appbarLayout).findViewById(R.id.toolbar);
        toolbar.setTitle("Add Fund Methods");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        uploadCard.setOnClickListener(view -> {
            openImageSelector();
        });
    }
    private void openImageSelector() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    public void submitPoints(View view) {
        if(click) {
            if (YourService.isOnline(this)) {
                switch (methodName) {
                    case "upi":
                        click=false;
                        paymentDialog();
                        break;
                    case "qr_code":
                        if (TextUtils.isEmpty(screenshot)) {
                            Toast.makeText(this, "Please Upload Screenshot", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (TextUtils.isEmpty(inputAmount.getText())) {
                            Toast.makeText(this, "Enter Amount", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (TextUtils.isEmpty(inputTransctionID.getText())) {
                            Toast.makeText(this, "Enter Transaction/UTR ID", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        click=false;
                        presenter.apiPaymentReceive(SharPrefHelper.getLogInToken(this), inputAmount.getText().toString(), methodName, screenshot, inputTransctionID.getText().toString(), "");
                    /*if (data.getAvailableMethodsDetails().getAmountConfiguration().equals("3")){
                        presenter.apiPaymentReceive(SharPrefHelper.getLogInToken(this), String.valueOf(reducedAmount),methodName, screenshot,inputTransctionID.getText().toString(),"");
                    }else {
                        presenter.apiPaymentReceive(SharPrefHelper.getLogInToken(this), amount,methodName, screenshot,inputTransctionID.getText().toString(),"");
                    }*/

                        break;
                    case "bank_account":
                        if (TextUtils.isEmpty(screenshot)) {
                            Toast.makeText(this, "Please Upload Screenshot", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (TextUtils.isEmpty(inputAmount.getText())) {
                            Toast.makeText(this, "Enter Amount", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (TextUtils.isEmpty(inputTransctionID.getText())) {
                            Toast.makeText(this, "Enter Transaction/UTR ID", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        click=false;
                        presenter.apiPaymentReceive(SharPrefHelper.getLogInToken(this), inputAmount.getText().toString(), methodName, screenshot, inputTransctionID.getText().toString(), "");
                        break;
                    default:
                        click=false;
                        presenter.apiPaymentRequest(SharPrefHelper.getLogInToken(this), amount, methodName);
                        break;
                }

            } else
                Toast.makeText(this, getString(R.string.check_your_internet_connection), Toast.LENGTH_SHORT).show();
        }
    }



    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void setClipboard(Context context, String text) {
        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(context, "Copy: "+text, Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onRestart() {
        super.onRestart();
        registerReceiver(myReceiver, mIntentFilter, Context.RECEIVER_NOT_EXPORTED);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myReceiver);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(myReceiver, mIntentFilter, Context.RECEIVER_NOT_EXPORTED);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void apiResponsePaymentReceive(String message) {
        click = true;
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void apiResponsePaymentRequest(PaymentRequestModel.Data data) {
        Uri uri = Uri.parse(data.getUpi_url());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivityForResult(Intent.createChooser(intent, "Select Payment App"), PAYMENT_APP_REQUEST);
    }

    @Override
    public void message(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void destroy(String msg) {
        SharPrefHelper.setClearData(this);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
        finish();
    }

    public void copy(View view) {
        switch (view.getTag().toString()){
            case "qrUpiId":
                setClipboard(this, qrUpiId.getText().toString());
                break;
            case "bankName":
                setClipboard(this, data.getAvailableMethodsDetails().getBankAccount().getBankName());
                break;
            case "HolderName":
                setClipboard(this, data.getAvailableMethodsDetails().getBankAccount().getAccountHolderName());
                break;
            case "accountNumber":
                setClipboard(this, data.getAvailableMethodsDetails().getBankAccount().getAccountNo());
                break;
            case "ifscCode":
                setClipboard(this, data.getAvailableMethodsDetails().getBankAccount().getIfscCode());
                break;
        }

    }
    private void paymentDialog() {
        transactionId = "TID" + System.currentTimeMillis();
        amount = String.valueOf(reducedAmount)+".0";
        // a PAYMENT INITIALIZATION
        EasyUpiPayment.Builder builder = new EasyUpiPayment.Builder(this)
                .with(PaymentApp.ALL)
                .setPayeeVpa(upiId)
                .setPayeeName(payeeName)
                .setTransactionId(transactionId)
                .setTransactionRefId(transactionId)
                .setPayeeMerchantCode("")
                .setDescription(remark)
                .setAmount(amount);

        // END INITIALIZATION
        try {
            // Build instance
            EasyUpiPayment  easyUpiPayment = builder.build();

            // Register Listener for Events
            easyUpiPayment.setPaymentStatusListener(this);

            // Start payment / transaction
            easyUpiPayment.startPayment();
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Error "+exception.getMessage());

        }
    }

    @Override
    public void onTransactionCompleted(@NotNull TransactionDetails transactionDetails) {
        switch (transactionDetails.getTransactionStatus()) {
            case SUCCESS:
                if (data.getAvailableMethodsDetails().getAmountConfiguration().equals("3")){
                    onTransactionSuccess(String.valueOf(reducedAmount));
                }else {
                    onTransactionSuccess(amount);
                }

                break;
            case FAILURE:
                onTransactionFailed();
                break;
            case SUBMITTED:
                onTransactionSubmitted();
                break;
        }
    }

    @Override
    public void onTransactionCancelled() {
        // Payment Cancelled by User
        toast("Cancelled by user");
    }

    private void onTransactionSuccess(String amount) {
        // Payment Success
        toast("Success");

        presenter.apiPaymentReceive(SharPrefHelper.getLogInToken(this), amount,methodName,"",transactionId,methodDetails);
    }

    private void onTransactionSubmitted() {
        // Payment Pending
        toast("Pending | Submitted");
    }

    private void onTransactionFailed() {
        // Payment Failed
        toast("Failed");
    }


    private static class WebViewClint extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            return true;
        }
    }
    private void performOCROnImage(Bitmap bitmap) {
        InputImage image = InputImage.fromBitmap(bitmap, 0);
        TextRecognizer recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);

        recognizer.process(image)
                .addOnSuccessListener(this::processTextRecognitionResult)
                .addOnFailureListener(e -> {
                    e.printStackTrace();
                    Toast.makeText(this, "Failed to extract text", Toast.LENGTH_SHORT).show();
                });
    }

    private void processTextRecognitionResult(Text texts) {
        String resultText = texts.getText();
        Log.d("OCR Result", "Extracted Text: " + resultText);

        // Now you can parse the resultText to find the UTR or Transaction ID
        String utr = findUTRInText(resultText);
        if (utr != null) {
            inputTransctionID.setText(utr);
            Toast.makeText(this, "UTR Found: " + utr, Toast.LENGTH_LONG).show();
        } else {
            inputTransctionID.setText("");
            Toast.makeText(this, "UTR Not Found", Toast.LENGTH_SHORT).show();
        }
        findAmountInText(resultText);
    }
    String[] textsToMatch = {"100", "150", "200", "250", "300", "350", "400", "450", "500", "550", "600",
            "1,000", "1,500", "1,200", "2,000", "2,500", "3,000", "3,500", "4,500", "5,000", "5,500", "10,000",};
    private void findAmountInText(String text) {
        for (String i : textsToMatch) {
            if (text.contains(i)) {
                inputAmount.setText(i);
            }
        }
    }
    private String findUTRInText(String text) {
        String utrPattern = "\\b(UTR: |UPI transaction ID|UPI Ref No: )\\b\\s*[:#-]?\\s*([A-Za-z0-9]+)";
        Pattern pattern = Pattern.compile(utrPattern);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group(2); // Return the UTR or Transaction ID found
        }
        return null; // Return null if no UTR or Transaction ID is found
    }

    private String encodeImageToBase64(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        byte[] imageBytes = outputStream.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imagePreview.setVisibility(View.VISIBLE);
                imagePreview.setImageBitmap(bitmap);
                uploadText.setText("Change Screenshot");
                screenshot = encodeImageToBase64(bitmap);
                performOCROnImage(bitmap);
            } catch (IOException e) {
                System.out.println("upload Error "+e.getMessage());
            }
        }
        if (requestCode == PAYMENT_APP_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            System.out.println("Sitaram "+data.getData());
        }
    }

}