package com.maram.myexample.View.Utils;

import android.content.Context;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.widget.EditText;

import com.maram.myexample.R;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Marimuthu on 8/17/17.
 */

public class ProfileUtils {

    static ProfileUtils instanceObj;

    public static ProfileUtils getInstanceObj(){
        if(instanceObj == null){
            instanceObj = new ProfileUtils();
        }
        return instanceObj;
    }

    public String convertBurmeseToEnglishNumber(String burmseNumber) {

        StringBuilder englishNumber = new StringBuilder();

        if (burmseNumber.contains("1") || burmseNumber.contains("2") || burmseNumber.contains("3") || burmseNumber.contains("4") || burmseNumber.contains("5") || burmseNumber.contains("6") || burmseNumber.contains("7") || burmseNumber.contains("8") || burmseNumber.contains("9") || burmseNumber.contains("0")) {
            return burmseNumber;
        } else {

            for (int i = 0; i < burmseNumber.length(); i++) {
                char c = burmseNumber.charAt(i);
                switch (c) {
                    case '၁':
                        englishNumber.append("1");
                        break;
                    case '၂':
                        englishNumber.append("2");
                        break;
                    case '၃':
                        englishNumber.append("3");
                        break;
                    case '၄':
                        englishNumber.append("4");
                        break;
                    case '၅':
                        englishNumber.append("5");
                        break;
                    case '၆':
                        englishNumber.append("6");
                        break;
                    case '၇':
                        englishNumber.append("7");
                        break;
                    case '၈':
                        englishNumber.append("8");
                        break;
                    case '၉':
                        englishNumber.append("9");
                        break;
                    case '၀':
                        englishNumber.append("0");
                        break;
                    case '.':
                        englishNumber.append(".");
                        break;
                    case ',':
                        englishNumber.append(",");
                        break;

                }
            }
        }
        return englishNumber.toString();
    }

    public SpannableString formatedAmountWithComma(String amountva, Context context) {

        SpannableString ss1 = new SpannableString("");
        try {

            /*if (amountva.contains(context.getResources().getString(R.string.one)) ||
                    amountva.contains(context.getResources().getString(R.string.two)) ||
                    amountva.contains(context.getResources().getString(R.string.three)) ||
                    amountva.contains(context.getResources().getString(R.string.four)) ||
                    amountva.contains(context.getResources().getString(R.string.five)) ||
                    amountva.contains(context.getResources().getString(R.string.six)) ||
                    amountva.contains(context.getResources().getString(R.string.seven)) ||
                    amountva.contains(context.getResources().getString(R.string.eight)) ||
                    amountva.contains(context.getResources().getString(R.string.nine)) ||
                    amountva.contains(context.getResources().getString(R.string.ten))) {
                amountva = convertBurmeseToEnglishNumber(amountva);
            }*/


            if (amountva.contains(".00")) {
                amountva = amountva.replace(".00", "");
            }
            if (amountva.contains(".")) {
                BigDecimal amount = new BigDecimal(amountva);
                amount = amount.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                String am = amount.toString();
                //System.out.println(am);
                int index = am.indexOf(".");


                double amount1 = Double.parseDouble(am);
                DecimalFormat formatter1 = new DecimalFormat("#,###,###,###.00",
                        new DecimalFormatSymbols(Locale.ENGLISH));

                ss1 = new SpannableString(formatter1.format(amount1) + " " + context.getResources().getString(R.string.kyats));


            } else {

                if (amountva.contains("၁") || amountva.contains("၂") || amountva.contains("၃") || amountva.contains("၄") || amountva.contains("၅") || amountva.contains("၆") || amountva.contains("၇") || amountva.contains("၈") || amountva.contains("၉") || amountva.contains("၀")) {
                    amountva = convertBurmeseToEnglishNumber(amountva);
                }

                double amount = Double.parseDouble(amountva);
                DecimalFormat formatter = new DecimalFormat("#,###", new DecimalFormatSymbols(Locale.ENGLISH));
                String am = formatter.format(amount);
                if (am.contains(".00")) {
                    am = am.replace(".00", "");
                }
                ss1 = new SpannableString(am + " " + context.getResources().getString(R.string.kyats));

                String originalString = am.toString();
                Long longval;

                if (originalString.contains(",")) {
                    originalString = originalString.replaceAll(",", "");
                }
                longval = Long.parseLong(originalString);
                formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                formatter.applyPattern("#,###,###,###");
                String formattedString = formatter.format(longval);

                ss1 = new SpannableString(formattedString + " " + context.getResources().getString(R.string.kyats));

            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        return ss1;

    }

    public void setMaxLength(EditText editText, int maxLength) {
        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                String blockedChars = "/%#&\\;<>?[]\"^_|~";
                for (int i = start; i < end; i++) {
                    String character = String.valueOf(source.charAt(i));
                    if (character.equalsIgnoreCase("/") || character.equalsIgnoreCase("%") || character.equalsIgnoreCase("#") || character.equalsIgnoreCase("&") || character.equalsIgnoreCase("\\") || character.equalsIgnoreCase(";") || character.equalsIgnoreCase("<") || character.equalsIgnoreCase(">") || character.equalsIgnoreCase("?") || character.equalsIgnoreCase("[") || character.equalsIgnoreCase("]") || character.equalsIgnoreCase("\"") || character.equalsIgnoreCase("^") || character.equalsIgnoreCase("_") || character.equalsIgnoreCase("|") || character.equalsIgnoreCase("~") || character.equalsIgnoreCase("@") || character.equalsIgnoreCase("-")) {
                        return "";
                    }
                }
                return null;
            }
        };

        InputFilter[] fArray = new InputFilter[2];
        fArray[0] = new InputFilter.LengthFilter(maxLength);
        fArray[1] = filter;
        editText.setFilters(fArray);
    }

    public boolean checkminAmountLimit(String amt, int limit) {
        int amount = Integer.parseInt(amt);
        return !(amount > limit)? true: false;
    }


}
