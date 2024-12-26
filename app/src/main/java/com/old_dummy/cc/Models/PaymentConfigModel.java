package com.old_dummy.cc.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaymentConfigModel {
    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("code")
    private String code;

    @Expose
    @SerializedName("status")
    private String status;

    @Expose
    @SerializedName("data")
    private Data data;

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

    public static class Data implements Parcelable {
        @Expose
        @SerializedName("support_number")
        private String supportNumber;

        @Expose
        @SerializedName("available_methods")
        private AvailableMethods availableMethods;

        @Expose
        @SerializedName("available_methods_details")
        private AvailableMethodsDetails availableMethodsDetails;

        protected Data(Parcel in) {
            supportNumber = in.readString();
            availableMethods = in.readParcelable(AvailableMethods.class.getClassLoader());
            availableMethodsDetails = in.readParcelable(AvailableMethodsDetails.class.getClassLoader());
        }

        public static final Creator<Data> CREATOR = new Creator<Data>() {
            @Override
            public Data createFromParcel(Parcel in) {
                return new Data(in);
            }

            @Override
            public Data[] newArray(int size) {
                return new Data[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(supportNumber);
            dest.writeParcelable(availableMethods, flags);
            dest.writeParcelable(availableMethodsDetails, flags);
        }

        public String getSupportNumber() {
            return supportNumber;
        }

        public AvailableMethods getAvailableMethods() {
            return availableMethods;
        }

        public AvailableMethodsDetails getAvailableMethodsDetails() {
            return availableMethodsDetails;
        }

        public static class AvailableMethods implements Parcelable {
            @Expose
            @SerializedName("bank_account")
            private Boolean bankAccount;

            @Expose
            @SerializedName("upi")
            private Boolean upi;

            @Expose
            @SerializedName("qr_code")
            private Boolean qrCode;

            @Expose
            @SerializedName("payment_gateway")
            private List<PaymentGateway> paymentGateway;

            protected AvailableMethods(Parcel in) {
                byte tmpBankAccount = in.readByte();
                bankAccount = tmpBankAccount == 0 ? null : tmpBankAccount == 1;
                byte tmpUpi = in.readByte();
                upi = tmpUpi == 0 ? null : tmpUpi == 1;
                byte tmpQrCode = in.readByte();
                qrCode = tmpQrCode == 0 ? null : tmpQrCode == 1;
                paymentGateway = in.createTypedArrayList(PaymentGateway.CREATOR);
            }

            public static final Creator<AvailableMethods> CREATOR = new Creator<AvailableMethods>() {
                @Override
                public AvailableMethods createFromParcel(Parcel in) {
                    return new AvailableMethods(in);
                }

                @Override
                public AvailableMethods[] newArray(int size) {
                    return new AvailableMethods[size];
                }
            };

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeByte((byte) (bankAccount == null ? 0 : bankAccount ? 1 : 2));
                dest.writeByte((byte) (upi == null ? 0 : upi ? 1 : 2));
                dest.writeByte((byte) (qrCode == null ? 0 : qrCode ? 1 : 2));
                dest.writeTypedList(paymentGateway);
            }

            public Boolean getBankAccount() {
                return bankAccount;
            }

            public Boolean getUpi() {
                return upi;
            }

            public Boolean getQrCode() {
                return qrCode;
            }

            public List<PaymentGateway> getPaymentGateway() {
                return paymentGateway;
            }

            public static class PaymentGateway implements Parcelable {
                @Expose
                @SerializedName("type")
                private String type;

                @Expose
                @SerializedName("video")
                private String video;

                @Expose
                @SerializedName("notice")
                private String notice;

                protected PaymentGateway(Parcel in) {
                    type = in.readString();
                    video = in.readString();
                    notice = in.readString();
                }

                public static final Creator<PaymentGateway> CREATOR = new Creator<PaymentGateway>() {
                    @Override
                    public PaymentGateway createFromParcel(Parcel in) {
                        return new PaymentGateway(in);
                    }

                    @Override
                    public PaymentGateway[] newArray(int size) {
                        return new PaymentGateway[size];
                    }
                };

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(type);
                    dest.writeString(video);
                    dest.writeString(notice);
                }

                public String getType() {
                    return type;
                }

                public String getVideo() {
                    return video;
                }

                public String getNotice() {
                    return notice;
                }
            }
        }

        public static class AvailableMethodsDetails implements Parcelable {
            @Expose
            @SerializedName("default_method")
            private String defaultMethod;

            @Expose
            @SerializedName("upi_limit")
            private String upiLimit;

            @Expose
            @SerializedName("amount_configuration")
            private String amountConfiguration;

            @Expose
            @SerializedName("upi")
            private Upi upi;

            @Expose
            @SerializedName("small_amount_upi")
            private SmallAmountUpi smallAmountUpi;

            @Expose
            @SerializedName("large_amount_upi")
            private LargeAmountUpi largeAmountUpi;

            @Expose
            @SerializedName("bank_account")
            private BankAccount bankAccount;

            @Expose
            @SerializedName("qr_code")
            private QrCode qrCode;

            protected AvailableMethodsDetails(Parcel in) {
                defaultMethod = in.readString();
                upiLimit = in.readString();
                amountConfiguration = in.readString();
                upi = in.readParcelable(Upi.class.getClassLoader());
                smallAmountUpi = in.readParcelable(SmallAmountUpi.class.getClassLoader());
                largeAmountUpi = in.readParcelable(LargeAmountUpi.class.getClassLoader());
                bankAccount = in.readParcelable(BankAccount.class.getClassLoader());
                qrCode = in.readParcelable(QrCode.class.getClassLoader());
            }

            public static final Creator<AvailableMethodsDetails> CREATOR = new Creator<AvailableMethodsDetails>() {
                @Override
                public AvailableMethodsDetails createFromParcel(Parcel in) {
                    return new AvailableMethodsDetails(in);
                }

                @Override
                public AvailableMethodsDetails[] newArray(int size) {
                    return new AvailableMethodsDetails[size];
                }
            };

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(defaultMethod);
                dest.writeString(upiLimit);
                dest.writeString(amountConfiguration);
                dest.writeParcelable(upi, flags);
                dest.writeParcelable(smallAmountUpi, flags);
                dest.writeParcelable(largeAmountUpi, flags);
                dest.writeParcelable(bankAccount, flags);
                dest.writeParcelable(qrCode, flags);
            }
            public String getDefaultMethod() {
                return defaultMethod;
            }

            public String getUpiLimit() {
                return upiLimit;
            }

            public String getAmountConfiguration() {
                return amountConfiguration;
            }

            public Upi getUpi() {
                return upi;
            }

            public SmallAmountUpi getSmallAmountUpi() {
                return smallAmountUpi;
            }

            public LargeAmountUpi getLargeAmountUpi() {
                return largeAmountUpi;
            }

            public BankAccount getBankAccount() {
                return bankAccount;
            }

            public QrCode getQrCode() {
                return qrCode;
            }

            public static class Upi implements Parcelable {
                @Expose
                @SerializedName("video")
                private String video;

                @Expose
                @SerializedName("notice")
                private String notice;

                protected Upi(Parcel in) {
                    video = in.readString();
                    notice = in.readString();
                }

                public static final Creator<Upi> CREATOR = new Creator<Upi>() {
                    @Override
                    public Upi createFromParcel(Parcel in) {
                        return new Upi(in);
                    }

                    @Override
                    public Upi[] newArray(int size) {
                        return new Upi[size];
                    }
                };

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(video);
                    dest.writeString(notice);
                }

                public String getVideo() {
                    return video;
                }

                public String getNotice() {
                    return notice;
                }
            }

            public static class SmallAmountUpi implements Parcelable {
                @Expose
                @SerializedName("upi_name")
                private String upi_name;

                @Expose
                @SerializedName("upi_id")
                private String upi_id;
                @Expose
                @SerializedName("remark")
                private String remark;
                @Expose
                @SerializedName("type")
                private String type;

                protected SmallAmountUpi(Parcel in) {
                    upi_name = in.readString();
                    upi_id = in.readString();
                    remark = in.readString();
                    type = in.readString();
                }

                public static final Creator<SmallAmountUpi> CREATOR = new Creator<SmallAmountUpi>() {
                    @Override
                    public SmallAmountUpi createFromParcel(Parcel in) {
                        return new SmallAmountUpi(in);
                    }

                    @Override
                    public SmallAmountUpi[] newArray(int size) {
                        return new SmallAmountUpi[size];
                    }
                };

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(upi_name);
                    dest.writeString(upi_id);
                    dest.writeString(remark);
                    dest.writeString(type);
                }

                public String getUpi_name() {
                    return upi_name;
                }

                public String getUpi_id() {
                    return upi_id;
                }

                public String getRemark() {
                    return remark;
                }

                public String getType() {
                    return type;
                }
            }

            public static class LargeAmountUpi implements Parcelable {
                @Expose
                @SerializedName("upi_name")
                private String upiName;

                @Expose
                @SerializedName("upi_id")
                private String upiId;
                @Expose
                @SerializedName("remark")
                private String remark;
                @Expose
                @SerializedName("type")
                private String type;
                @Expose
                @SerializedName("remark2")
                private String remark2;
                protected LargeAmountUpi(Parcel in) {
                    upiName = in.readString();
                    upiId = in.readString();
                    remark = in.readString();
                    type = in.readString();
                    remark2 = in.readString();
                }

                public static final Creator<LargeAmountUpi> CREATOR = new Creator<LargeAmountUpi>() {
                    @Override
                    public LargeAmountUpi createFromParcel(Parcel in) {
                        return new LargeAmountUpi(in);
                    }

                    @Override
                    public LargeAmountUpi[] newArray(int size) {
                        return new LargeAmountUpi[size];
                    }
                };

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(upiName);
                    dest.writeString(upiId);
                    dest.writeString(remark);
                    dest.writeString(type);
                    dest.writeString(remark2);
                }

                public String getUpiName() {
                    return upiName;
                }

                public String getUpiId() {
                    return upiId;
                }

                public String getRemark() {
                    return remark;
                }

                public String getType() {
                    return type;
                }

                public String getRemark2() {
                    return remark2;
                }
            }

            public static class BankAccount implements Parcelable {
                @Expose
                @SerializedName("video")
                private String video;

                @Expose
                @SerializedName("notice")
                private String notice;

                @Expose
                @SerializedName("bank_name")
                private String bankName;

                @Expose
                @SerializedName("account_holder_name")
                private String accountHolderName;

                @Expose
                @SerializedName("account_no")
                private String accountNo;

                @Expose
                @SerializedName("ifsc_code")
                private String ifscCode;

                protected BankAccount(Parcel in) {
                    video = in.readString();
                    notice = in.readString();
                    bankName = in.readString();
                    accountHolderName = in.readString();
                    accountNo = in.readString();
                    ifscCode = in.readString();
                }

                public static final Creator<BankAccount> CREATOR = new Creator<BankAccount>() {
                    @Override
                    public BankAccount createFromParcel(Parcel in) {
                        return new BankAccount(in);
                    }

                    @Override
                    public BankAccount[] newArray(int size) {
                        return new BankAccount[size];
                    }
                };

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(video);
                    dest.writeString(notice);
                    dest.writeString(bankName);
                    dest.writeString(accountHolderName);
                    dest.writeString(accountNo);
                    dest.writeString(ifscCode);
                }

                public String getVideo() {
                    return video;
                }

                public String getNotice() {
                    return notice;
                }

                public String getBankName() {
                    return bankName;
                }

                public String getAccountHolderName() {
                    return accountHolderName;
                }

                public String getAccountNo() {
                    return accountNo;
                }

                public String getIfscCode() {
                    return ifscCode;
                }
            }

            public static class QrCode implements Parcelable {
                @Expose
                @SerializedName("video")
                private String video;

                @Expose
                @SerializedName("notice")
                private String notice;
                @Expose
                @SerializedName("qr_image")
                private String qrImage;
                @Expose
                @SerializedName("qr_upi_id")
                private String qrUpiId;

                protected QrCode(Parcel in) {
                    video = in.readString();
                    notice = in.readString();
                    qrImage = in.readString();
                    qrUpiId = in.readString();
                }

                public static final Creator<QrCode> CREATOR = new Creator<QrCode>() {
                    @Override
                    public QrCode createFromParcel(Parcel in) {
                        return new QrCode(in);
                    }

                    @Override
                    public QrCode[] newArray(int size) {
                        return new QrCode[size];
                    }
                };

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(video);
                    dest.writeString(notice);
                    dest.writeString(qrImage);
                    dest.writeString(qrUpiId);
                }

                public String getVideo() {
                    return video;
                }

                public String getNotice() {
                    return notice;
                }

                public String getQrImage() {
                    return qrImage;
                }

                public String getQrUpiId() {
                    return qrUpiId;
                }
            }
        }
    }
}
