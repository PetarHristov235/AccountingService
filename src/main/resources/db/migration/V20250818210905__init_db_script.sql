CREATE SEQUENCE IF NOT EXISTS account_sequence START 1 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS transfer_sequence START 1 INCREMENT 1;

CREATE TABLE IF NOT EXISTS public.accounts
(
    id               BIGINT PRIMARY KEY      DEFAULT nextval('account_sequence'),
    name             VARCHAR(255)   NOT NULL,
    iban             VARCHAR(34)    NOT NULL,
    status           VARCHAR(20)    NOT NULL,
    available_amount NUMERIC(19, 4) NOT NULL,
    created_on       TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_on      TIMESTAMP,
    version          BIGINT         NOT NULL,
    CONSTRAINT uk_accounts_iban_name UNIQUE (iban, name)
);


CREATE TABLE IF NOT EXISTS public.transfers
(
    id                     BIGINT PRIMARY KEY      DEFAULT nextval('transfer_sequence'),
    account_id             BIGINT         NOT NULL,
    beneficiary_account_id BIGINT,
    type                   VARCHAR(6)     NOT NULL,
    amount                 NUMERIC(19, 4) NOT NULL,
    created_on             TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_on            TIMESTAMP,
    CONSTRAINT fk_tx_account FOREIGN KEY (account_id) REFERENCES public.accounts (id),
    CONSTRAINT fk_tx_beneficiary FOREIGN KEY (beneficiary_account_id) REFERENCES public.accounts (id)
);

CREATE INDEX IF NOT EXISTS ix_tx_account ON public.transfers (account_id);
CREATE INDEX IF NOT EXISTS ix_tx_beneficiary ON public.transfers (beneficiary_account_id);