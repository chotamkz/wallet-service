CREATE TABLE accounts (
    id         uuid           PRIMARY KEY DEFAULT uuidv7(),
    owner_id   uuid           NOT NULL,
    balance    numeric(19, 2) NOT NULL DEFAULT 0,
    created_at timestamptz    NOT NULL DEFAULT now(),

    CONSTRAINT accounts_balance_non_negative CHECK (balance >= 0)
);
