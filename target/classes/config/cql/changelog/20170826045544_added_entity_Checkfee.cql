
CREATE TABLE IF NOT EXISTS checkfee (
    id uuid,
    feeamount int,
    reason text,
    status text,
    PRIMARY KEY(id)
);
