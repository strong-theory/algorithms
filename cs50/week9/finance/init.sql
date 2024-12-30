DROP TABLE user_stocks;

CREATE TABLE user_stocks(
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    user_id INTEGER NOT NULL,
    symbol TEXT NOT NULL,
    shares INTEGER NOT NULL,
    price REAL NOT NULL,
    total REAL NOT NULL,
    transaction_type TEXT CHECK( transaction_type IN ('B','S') ) NOT NULL,
    transaction_date  DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE UNIQUE INDEX user_stocks_idx ON user_stocks(id, user_id);
