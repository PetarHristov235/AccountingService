package com.accounting.demo.enumerator;

import lombok.Getter;

@Getter
public enum ApiExceptionEnum {
    //    GENERAL EXCEPTIONS
    ER_124("An internal error occurred. Please contact the support team.",500),

    //    ACCOUNT EXCEPTIONS,
    ACC_404("Account not found.", 404),
    ACC_32("Account is already frozen. Cannot perform this operation.",400),
    ACC_33("Account is already unfrozen. Cannot perform this operation.", 400),
    ACC_31("Account already exists with the same name or IBAN. " +
            "Please choose a different name or IBAN.", 400),
    ACC_34("Cannot create an account with negative balance. " +
            "Please provide a valid amount greater than or equal to zero.", 400),

    //    TRANSFER EXCEPTIONS
    TR_404("Transfer not found.", 404),
    TR_405("Not found any transfers for the given account.", 404),
    TR_406("Not found account by the account ID provided.", 400),
    TR_407("Not found beneficiary account by the beneficiary account ID provided.", 400),
    TR_408("Transfer amount must be greater than zero.", 400),
    TR_409("Cannot transfer to the same account.", 400),
    TR_410("Insufficient funds for the transfer. " +
            "Please ensure the account has enough balance to complete the transaction.", 400),
    TR_411("Insufficient funds in the beneficiary account. " +
            "Please ensure the beneficiary account has enough balance to receive the transfer.",
            400),
    TR_412("Invalid Transaction type", 400),
    TR_413("Cannot perform transfer from/to a frozen account.", 400);

    private final String description;
    private final int errorCode;
    ApiExceptionEnum(String description, int errorCode) {
        this.description = description;
        this.errorCode = errorCode;
    }

}