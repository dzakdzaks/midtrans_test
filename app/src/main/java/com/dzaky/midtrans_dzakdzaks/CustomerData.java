package com.dzaky.midtrans_dzakdzaks;

import com.midtrans.sdk.corekit.core.TransactionRequest;
import com.midtrans.sdk.corekit.models.BankType;
import com.midtrans.sdk.corekit.models.CustomerDetails;
import com.midtrans.sdk.corekit.models.ItemDetails;
import com.midtrans.sdk.corekit.models.snap.Authentication;
import com.midtrans.sdk.corekit.models.snap.CreditCard;

import java.util.ArrayList;
import java.util.List;

public class CustomerData {
    public static String NAME = "dzakdzaks";
    public static String PHONE = "087349734832";
    public static String EMAIL = "emailgua@gmail.com";
    public static String ADDRESS = "jalan gua nomor gua rt gua";

    public static List<Product> getListProduct() {

        List<Product> list = new ArrayList<>();

        list.add(new Product("https://www.lenovo.com/medias/lenovo-flex-5-15-intel-black-hero.png?context=bWFzdGVyfHJvb3R8MjYzMTQ1fGltYWdlL3BuZ3xoMmQvaGZkLzk5Nzk1MTQ4ODAwMzAucG5nfDNlZWRlNGIwYTc5MjAyNjkzNDkzNTg2MWY0MjkzZDQ5N2E2MWJjOTk1Y2M5ZDJjZGIyNzRhYTE0OGFlYmE4MGE",
                "Laptop 1", 2, 10000, 9.0));

        list.add(new Product("https://quietpc.de/images/products/logitech-mk540-advanced.jpg",
                "Keyboard 1", 2, 5000, 5.0));

        list.add(new Product("https://quietpc.de/images/products/logitech-mk540-advanced.jpg",
                "Keyboard 1", 2, 5000, 7.0));

        list.add(new Product("https://www.lenovo.com/medias/lenovo-flex-5-15-intel-black-hero.png?context=bWFzdGVyfHJvb3R8MjYzMTQ1fGltYWdlL3BuZ3xoMmQvaGZkLzk5Nzk1MTQ4ODAwMzAucG5nfDNlZWRlNGIwYTc5MjAyNjkzNDkzNTg2MWY0MjkzZDQ5N2E2MWJjOTk1Y2M5ZDJjZGIyNzRhYTE0OGFlYmE4MGE",
                "Laptop 1", 2, 10000, 4.0));

        return list;
    }

    public static CustomerDetails getCustomerDetails() {

        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setFirstName(NAME);
        customerDetails.setEmail(EMAIL);
        customerDetails.setPhone(PHONE);
        return customerDetails;
    }

    public static TransactionRequest getTransactionRequest(String id, int price, int qty, String name) {
        TransactionRequest request = new TransactionRequest(String.valueOf(System.currentTimeMillis()), 20000);

        request.setCustomerDetails(getCustomerDetails());

        ItemDetails itemDetails = new ItemDetails(id, price, qty, name);
        ArrayList<ItemDetails> listItem = new ArrayList<>();
        listItem.add(itemDetails);

        request.setItemDetails(listItem);

        CreditCard creditCard = new CreditCard();
        creditCard.setSaveCard(false);
        creditCard.setAuthentication(Authentication.AUTH_RBA);
        creditCard.setBank(BankType.BCA);

        request.setCreditCard(creditCard);

        return request;
    }
}
