package com.btc.redg.example.h2.helpers;

import com.btc.redg.models.ColumnModel;
import com.btc.redg.runtime.defaultvalues.pluggable.NumberProvider;

import java.math.BigDecimal;

public class CreditCardNumberDefaultValueProvider extends NumberProvider {

    @Override
    public <T> T getDefaultValue(final ColumnModel columnModel, final Class<T> aClass) {
        return convertNumber(getRandomCreditCardNumber(), aClass);
    }

    /**
     * This generates a credit card number that passes the Luhn algorithm check. These are not really valid numbers.
     * Do not use them for other purposes than testing.
     *
     * @return a 16 digit credit card number
     */
    private BigDecimal getRandomCreditCardNumber() {
        StringBuilder number = new StringBuilder();
        String prefix = CC_PREFIXES[(int) (CC_PREFIXES.length * Math.random())];
        number.append(prefix);

        while (number.length() < 15) {
            number.append((int) (Math.random() * 10));
        }

        // calculate checksum
        int sum = 0;
        for (int i = 0; i < number.length(); i++) {
            int value = Character.getNumericValue(number.charAt(i)) * (i % 2 + 1);

            sum += value % 10 + (value / 10);
        }
        sum *= 9;

        number.append(sum % 10);

        return new BigDecimal(number.toString());
    }

    private static String[] CC_PREFIXES = {"4532", "4716", "53", "55", "6011"};


}
